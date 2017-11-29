/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.dej.evaluacion3.contoller;

import duoc.dej.evaluacion3.entity.Artista;
import duoc.dej.evaluacion3.entity.Genero;
import duoc.dej.evaluacion3.exception.ArtistaNFE;
import duoc.dej.evaluacion3.exception.GeneroNFE;
import duoc.dej.evaluacion3.service.ArtistaService;
import duoc.dej.evaluacion3.service.GeneroService;
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
@WebServlet(name = "ArtistaServlet", urlPatterns = {"/ArtistaServlet"})
public class ArtistaServlet extends HttpServlet {

    @EJB
    ArtistaService as;
    @EJB
    GeneroService gs;

    private final String JSP_LISTAR_ARTISTAS = "/WEB-INF/jsp/artista/listar.jsp";
    private final String JSP_CREAR = "/WEB-INF/jsp/artista/crear.jsp";

    Logger lg = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        op = op != null ? op : "";
        switch (op) {
            case "crear":
                req.setAttribute("generos", gs.listar());
                req.getRequestDispatcher(JSP_CREAR).forward(req, resp);
                break;
            case "buscar":
                buscar(req, resp);
                break;
            case "eliminar":
                eliminar(req, resp);
                break;
            case "listar":
            default:
                listar(req, resp);
        }
    }

    private void buscar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String aBuscado = req.getParameter("artista");
        String sGenero = req.getParameter("generoId");
        Long gId = null;
        try {
            if (sGenero != null) {
                gId = Long.parseLong(sGenero);
            }
        } catch (NumberFormatException nfe) {
            lg.log(Level.INFO, "ID de genero no valido");
        }
        List<Artista> artistas = as.buscar(aBuscado, gId);
        listar(req, resp, artistas);
    }

    private void eliminar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> ers = new ArrayList<>();
        List<String> msjs = new ArrayList<>();
        String er = "";
        String msj = "";
        
        String sId = req.getParameter("id");
        Long aId = 0L;
        try {
            aId= Long.parseLong(sId);
            as.eliminar(aId);
            msj =String.format("Artista eliminado correctamente!!!", aId);
            lg.log(Level.INFO, msj);
            req.setAttribute("mensajes", msjs);
            msjs.add(msj);
        } catch (NumberFormatException nfe) {
            er = String.format("Formato no valido");
            lg.log(Level.SEVERE, er);
            ers.add(er);
        }catch(ArtistaNFE ex){
            er = String.format("Artista no encontrado!!!");
            lg.log(Level.SEVERE, er);
            ers.add(er);
        }
        listar(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp, List<Artista> artistas) throws ServletException, IOException {
        List<Genero> generos = gs.listar();
        req.setAttribute("artistas", artistas);
        req.setAttribute("generos", generos);
        req.getRequestDispatcher(JSP_LISTAR_ARTISTAS).forward(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Artista> artistas = as.listar();
        listar(req, resp, artistas);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> ers = new ArrayList<>();
        List<String> msjs = new ArrayList<>();
        String er = "";
        String msj = "";
        
        String nom = req.getParameter("artista");
        String sGenero = req.getParameter("genero");
        String pais =  req.getParameter("pais");
        String est = req.getParameter("estado");
        String desc = req.getParameter("descripcion");
        String web = req.getParameter("web");
        Genero g = null;
        boolean estado = true;
        try {
            Long gId = Long.parseLong(sGenero);
            g = gs.listarXID(gId);
            if(g == null) throw new GeneroNFE("NO se encontro el genero del artista");
            estado = Boolean.parseBoolean(est);
            Artista a = new Artista(nom, pais, estado, desc, web, g);
            a = as.crear(a);
            msj = String.format("Artista creado correctamente", a.getNombre(), a.getId());
            msjs.add(msj);
        } catch (NumberFormatException nfe) {
            ers.add("Formato no valido");
        }catch(GeneroNFE ex){
            ers.add(ex.getMessage());
        }
        req.setAttribute("errores", ers);
        req.setAttribute("mensajes", msjs);
        req.getRequestDispatcher(JSP_CREAR).forward(req, resp);
    }

    
}
