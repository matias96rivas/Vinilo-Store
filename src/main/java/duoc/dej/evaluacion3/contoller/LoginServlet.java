/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.dej.evaluacion3.contoller;

import duoc.dej.evaluacion3.entity.Usuario;
import duoc.dej.evaluacion3.service.UserService;
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
 * @author LC1300XXXX
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    Logger lg = Logger.getLogger(this.getClass().getSimpleName());
    
    
    @EJB
    UserService us;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        if("logout".equals(op)) {
            req.getSession().invalidate();
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("uname");
        String pass = req.getParameter("psw");
        Usuario u = us.findUser(user, pass);
        List<String> errores    = new ArrayList<>();
        List<String> mensajes   = new ArrayList<>();
        String error = "";
        String mensaje = "";
        if(u == null) {
            error = "Usuario y/o contraseña inválidos";
            lg.log(Level.SEVERE, error);
            errores.add(error);
        }
        
        if(errores.size() > 0) {
            req.setAttribute("errores", errores);
            req.getRequestDispatcher("/jspf/error.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("usuario", u);
            resp.sendRedirect("indexAdmin.jsp");
        }
    }
    
    

}
