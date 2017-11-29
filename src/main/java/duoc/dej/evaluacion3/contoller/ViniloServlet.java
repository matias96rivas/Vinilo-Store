/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.dej.evaluacion3.contoller;

import duoc.dej.evaluacion3.entity.Artista;
import duoc.dej.evaluacion3.entity.Genero;
import duoc.dej.evaluacion3.entity.Vinilo;
import duoc.dej.evaluacion3.exception.ArtistaNFE;
import duoc.dej.evaluacion3.exception.GeneroNFE;
import duoc.dej.evaluacion3.exception.ViniloNFE;
import duoc.dej.evaluacion3.service.ArtistaService;
import duoc.dej.evaluacion3.service.GeneroService;
import duoc.dej.evaluacion3.service.ViniloService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matias
 */
@WebServlet(name = "ViniloServlet", urlPatterns = {"/ViniloServlet"})
public class ViniloServlet extends HttpServlet {

    @EJB
    ViniloService vs;
    @EJB
    GeneroService gs;
    @EJB
    ArtistaService as;

    private final String JSP_LISTAR_VINILOS = "/WEB-INF/jsp/vinilo/listar.jsp";
    private final String JSP_CREAR = "/WEB-INF/jsp/vinilo/crear.jsp";
    Logger lg = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        op = op != null ? op : "";
        switch (op) {
            case "crear":
                req.setAttribute("generos", gs.listar());
                req.setAttribute("artistas", as.listar());
                req.getRequestDispatcher(JSP_CREAR).forward(req, resp);
                break;
            case "buscar":
                buscar(req, resp);
                break;
            case "elminar":
                eliminar(req, resp);
                break;
            case "listar":
            default:
                listar(req, resp);
        }
    }

    private void buscar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String vB =req.getParameter("vinilo");
        String sGenero = req.getParameter("genero");
        String sArtista = req.getParameter("artista");
        Long gId = null;
        Long aId = null;
        try {
            if (sGenero != null || sArtista !=null) {
                gId = Long.parseLong(sGenero);
                aId = Long.parseLong(sArtista);
            }
        } catch (NumberFormatException nfe) {
            lg.log(Level.INFO, "El genero o el artista no son validos");
        }
        List<Vinilo> vinilos = vs.buscar(vB, gId);
        listar(req, resp, vinilos);
    }

    private void eliminar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> ers =  new ArrayList<>();
        List<String> msjs = new ArrayList<>();
        String er = "";
        String msj = "";
        
        String sId =req.getParameter("id");
        Long vId = 0L;
        try {
            vId = Long.parseLong(sId);
            vs.eliminar(vId);
            msj = String.format("Vinilo eliminado correctamente", vId);
            lg.log(Level.INFO, msj);
            req.setAttribute("mensajes", msjs);
            msjs.add(msj);
        } catch (NumberFormatException nfe) {
            er = String.format("ID invalido!!!");
            lg.log(Level.SEVERE, er);
            ers.add(msj);
        }catch(ViniloNFE ex){
            er = String.format("Vinilo no encontrado");
            lg.log(Level.SEVERE, er);
            ers.add(er);
        }
        listar(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp, List<Vinilo> vinilos) throws ServletException, IOException {
        List<Genero> g = gs.listar();
        List<Artista> a = as.listar();
        
        req.setAttribute("vinilos", vinilos);
        req.setAttribute("generos", g);
        req.setAttribute("artistas", a);
        req.getRequestDispatcher(JSP_LISTAR_VINILOS).forward(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Vinilo> v = vs.listar();
        listar(req, resp, v);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> ers = new ArrayList<>();
        List<String> msjs = new ArrayList<>();
        String er = "";
        String msj = "";
        
        String nom = req.getParameter("vinilo");
        String sGenero = req.getParameter("genero");
        String sArtista = req.getParameter("artista");
        String sPrecio = req.getParameter("precio");
        String img = req.getParameter("img");
        String desc = req.getParameter("descripcion");
        Long precio = 0L;
        Genero g = null;
        Artista a = null;
        try {
            Long gId = Long.parseLong(sGenero);
            Long aId = Long.parseLong(sArtista);
            g = gs.listarXID(gId);
            a = as.listarXID(aId);
            if(g == null) throw new GeneroNFE("Genero no encontrado");
            if(a == null) throw new ArtistaNFE("Artista no encontrado");
            precio = Long.parseLong(sPrecio);
            Vinilo v = new Vinilo(nom, img, desc, precio, g, a);
            v = vs.crear(v);
            msj = String.format("Vinilo creado correctamente", v.getNombre(), v.getId());
            msjs.add(msj);
        } catch (NumberFormatException nfe) {
            ers.add("Formato invalido");
        }catch(GeneroNFE eg){
            ers.add(eg.getMessage());
        }catch(ArtistaNFE ea){
            ers.add(ea.getMessage());
        }         
        req.setAttribute("errores", ers);
        req.setAttribute("mensajes", msjs);
        req.getRequestDispatcher(JSP_CREAR).forward(req, resp);
    }
    
    

}
