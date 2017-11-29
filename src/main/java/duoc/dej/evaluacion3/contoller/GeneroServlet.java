/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.dej.evaluacion3.contoller;

import duoc.dej.evaluacion3.entity.Genero;
import duoc.dej.evaluacion3.exception.GeneroNFE;
import duoc.dej.evaluacion3.service.GeneroService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ejb.EJBException;

/**
 *
 * @author Matias
 */
@WebServlet(name = "GeneroServlet", urlPatterns = {"/GeneroServlet"})
public class GeneroServlet extends HttpServlet {

    @EJB
    GeneroService gs;

    public final String JSP_LISTAR = "/WEB-INF/jsp/genero/listar.jsp";
    public final String JSP_CREAR = "/WEB-INF/jsp/genero/crear.jsp";

    Logger lg = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        op = op != null ? op : "";
        switch (op) {
            case "crear":
                crear(req, resp);
                break;
            case "eliminar":
                eliminar(req, resp);
                break;
            case "listar":
            default:
                listar(req, resp);
        }
    }

    private void crear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSP_CREAR).forward(req, resp);
    }

    private void eliminar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> ers = new ArrayList<>();
        List<String> msjs = new ArrayList<>();
        String er = "";
        String msj = "";

        String sId = req.getParameter("id");
        Long gId = 0L;
        try {
            gId = Long.parseLong(sId);
            gs.eliminar(gId);
            msj = String.format("Genero eliminado correctamente!!! ID %s", gId);
            lg.log(Level.INFO, msj);
            req.setAttribute("mensajes", msj);
            msjs.add(msj);
        } catch (NumberFormatException nfe) {
            er = String.format("Formato invalido!!!");
            lg.log(Level.SEVERE, er);
            ers.add(er);
        } catch (GeneroNFE ex) {
            er = String.format("NO se encontro el genero!!!");
            lg.log(Level.SEVERE, er);
            ers.add(er);
        }
        listar(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Genero> generos = gs.listar();
        req.setAttribute("generos", generos);
        req.getRequestDispatcher(JSP_LISTAR).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("id");
        if (sId == null || sId.isEmpty()) {
            postCrear(req, resp);
        }
    }

    private void postCrear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> ers = new ArrayList<>();
        List<String> msjs = new ArrayList<>();
        String er = "";
        String msj = "";

        String nom = req.getParameter("genero");
        String desc = req.getParameter("descripcion");
        Genero g = new Genero(nom, desc);

        ValidatorFactory f = Validation.buildDefaultValidatorFactory();
        Validator vali = f.getValidator();
        Set<ConstraintViolation<Genero>> listaV = vali.validate(g);
        for (ConstraintViolation<Genero> v : listaV) {
            ers.add(v.getPropertyPath().toString() + ":" + v.getMessage());
        }
        if (ers.size() > 0) {
            req.setAttribute("errores", ers);
        } else {
            g = gs.crear(g);
            msj = String.format("Genero creado correctamente", g.getNombre(), g.getId());
            msjs.add(msj);
            req.setAttribute("mensajes", msjs);
        }
        req.getRequestDispatcher(JSP_CREAR).forward(req, resp);
    }

}
