/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.dej.evaluacion3.contoller;

import duoc.dej.evaluacion3.entity.Compra;
import duoc.dej.evaluacion3.entity.LineaCompra;
import duoc.dej.evaluacion3.entity.Vinilo;
import duoc.dej.evaluacion3.exception.ViniloNFE;
import duoc.dej.evaluacion3.service.CompraBuilder;
import duoc.dej.evaluacion3.service.CompraService;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Matias
 */
@WebServlet(name = "CarritoServlet", urlPatterns = {"/CarritoServlet"})
public class CarritoServlet extends HttpServlet {

    @EJB
    CompraBuilder cb;
    @EJB
    CompraService cs;
    @EJB
    ViniloService vs;
    
    private final static String JSP_CARRITO = "/WEB-INF/jsp/carrito/carrito.jsp";
    private final static String JSP_BOLETA = "/WEB-INF/jsp/carrito/boleta.jsp";
    Logger lg = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        
        HttpSession session = req.getSession();
        Compra c = (Compra) session.getAttribute("compra");
        Long vId = 0L;
        
        List<String> ers = new ArrayList<>();
        List<String> msjs =  new ArrayList<>();
        String er = "";
        String msj = "";
        
        if ("sacar".equals(op)) {
            String sVID = req.getParameter("viniloId");
            try {
                vId = Long.parseLong(sVID);
                c.quitar(vId);
                msj = String.format("Vinilo quitado del carro", vId);
                msjs.add(msj);
                lg.log(Level.INFO, msj);
            } catch (NumberFormatException nfe) {
                er = "Error al sacar el vinilo del carro";
                lg.log(Level.SEVERE, er);
                ers.add(er);
            }
        }
        
        req.setAttribute("errores", ers);
        req.setAttribute("mensajes", msjs);
        req.setAttribute("compra", c);
        if ("compra".equals(op)) {
            c = cs.crear(c);
            req.setAttribute("compra", c);
            req.getRequestDispatcher(JSP_BOLETA).forward(req, resp);
        }else{
            req.getRequestDispatcher(JSP_CARRITO).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> ers = new ArrayList<>();
        List<String> msjs = new ArrayList<>();
        String er = "";
        String msj = "";
        
        String sCant = req.getParameter("cantidad");
        String sVID = req.getParameter("vinilo");
        Vinilo v = null;
        int cant = 1;
        try {
            Long vId = Long.parseLong(sVID);
            v = vs.listarXID(vId);
            if (v == null) {
                throw new ViniloNFE();
            }
        } catch (NumberFormatException nfe) {
            er = "Error de formato en el ID";
            lg.log(Level.SEVERE, er);
            ers.add(er);
        }catch(ViniloNFE vnfe){
            er = "Vinilo no encontrado!!";
            lg.log(Level.SEVERE, er);
            ers.add(er);
        }
        try {
            cant = Integer.parseInt(sCant);
        } catch (NumberFormatException nfe) {
            cant = 1;
        }
        
        HttpSession session = req.getSession();
        Compra c = (Compra) session.getAttribute("compra");
        if (c == null) {
            c = cb.agregar(v.getId(), cant).build();
        }else{
            c.getlCom().add(new LineaCompra(v, cant));
        }
        session.setAttribute("compra", c);
        req.setAttribute("compra", c);
        req.setAttribute("mensajes", msjs);
        req.setAttribute("errores", ers);
        req.getRequestDispatcher(JSP_CARRITO).forward(req, resp);
            
    }
    
    
    

}
