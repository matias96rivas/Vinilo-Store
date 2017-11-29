/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.dej.evaluacion3.contoller;

import duoc.dej.evaluacion3.entity.Artista;
import duoc.dej.evaluacion3.entity.Compra;
import duoc.dej.evaluacion3.entity.Genero;
import duoc.dej.evaluacion3.entity.Usuario;
import duoc.dej.evaluacion3.entity.Vinilo;
import duoc.dej.evaluacion3.service.ArtistaService;
import duoc.dej.evaluacion3.service.CompraBuilder;
import duoc.dej.evaluacion3.service.CompraService;
import duoc.dej.evaluacion3.service.GeneroService;
import duoc.dej.evaluacion3.service.UserService;
import duoc.dej.evaluacion3.service.ViniloService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "SetupServlet", urlPatterns = {"/SetupServlet"})
public class SetupServlet extends HttpServlet {

    @EJB
    GeneroService gs;
    @EJB
    ViniloService vs;
    @EJB
    ArtistaService as;
    @EJB
    CompraService cs;
    @EJB
    CompraBuilder cb;
    @EJB
    UserService us;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> ers = new ArrayList<>();
        List<String> msjs = new ArrayList<>();
        
        try {
            //generos
            Genero g = new Genero("Hip-Hop", "");
            g = gs.crear(g);
            msjs.add(String.format("Genero creado correctamente!!!", g.getNombre(), g.getId()));
            
            Genero g1 = new Genero("Electro", "");
            g1 = gs.crear(g1);
            msjs.add(String.format("Genero creado correctamente!!!", g1.getNombre(), g1.getId()));
            
            Genero g2 = new Genero("Pop", "");
            g2 = gs.crear(g2);
            msjs.add(String.format("Genero creado correctamente!!!", g2.getNombre(), g2.getId()));
            
            //Artistas
            Artista a = new Artista("Mente Sabia Cru", "Chile", true, "Mente Sabia Cru es un nombre del hip-hop de los años 2000 proveniente de la zona norte de Santiago.", "https://es-la.facebook.com/MenteSabiaCru/", g);
            a = as.crear(a);
            msjs.add(String.format("Artista creado correctamente!!!", a.getNombre(), a.getId()));
            
            Artista a1 = new Artista("Daft Punk", "Francia", true, "Daft Punk es un dúo de productores formado por los músicos franceses Guy-Manuel de Homem-Christo y Thomas Bangalter.", "https://daftpunk.com/", g1);
            a1 = as.crear(a1);
            msjs.add(String.format("Artista crado correctamente!!!", a1.getNombre(), a1.getId()));
            
            Artista a2 = new Artista("Michael Jackson", "EE.UU", false, "Michael Joseph Jackson​​ fue un cantante estadounidense, compositor, productor discográfico, bailarín, actor y filántropo", "http://www.michaeljackson.com/", g2);
            a2 = as.crear(a2);
            msjs.add(String.format("Artista creado correctamente!!!", a2.getNombre(), a2.getId()));
            
            //Vinilos
            String img = "https://2.bp.blogspot.com/-3mhu84piH5k/WIavgKbXQ1I/AAAAAAAAZqU/Z-GJBu7Qg6Ev-VYQhL7-gT83p2ctzfQiQCLcB/s400/Portada.jpg";
            String img2 = "https://images-na.ssl-images-amazon.com/images/I/61Ia07wdZQL._SY355_.jpg";
            String img3 = "https://images-na.ssl-images-amazon.com/images/I/514no7ZRHBL._SX355_.jpg";
            
            Vinilo v = new Vinilo("Elefante", img, "El 2016, terminados sus discos solitarios, volvieron con un nuevo disco, Elefante, y con el leal apoyo de su importante circuito de público.", 5000L, g, a);
            v = vs.crear(v);
            msjs.add(String.format("Vinilo creado correctamente", v.getNombre(), v.getId()));
            
            Vinilo v1 = new Vinilo("Random Access Memories", img2, "Es el cuarto álbum de estudio del dúo francés Daft Punk.", 35000L, g1, a1);
            v1 = vs.crear(v1);
            msjs.add(String.format("Vinilo crado correctamente", v1.getNombre(), v1.getId()));
            
            Vinilo v2 = new Vinilo("Billie Jean", img3, "Billie Jean estaba basada en los grupos de chicas que él y sus hermanos encontraron mientras estaba en The Jackson 5.", 39990L, g2, a2);
            v2 = vs.crear(v2);
            msjs.add(String.format("Vinilo creado correctamente", v2.getNombre(), v2.getId()));
            
            //usurio
            Usuario u = new Usuario("admin", "admin");
            u = us.crear(u);
            msjs.add(String.format("Usuario Admin creado correctamente", u.getId()));
            
            
        } catch (Exception e) {
            ers.add(e.getMessage());
        }
        req.setAttribute("mensajes", msjs);
        req.setAttribute("errores", ers);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
    
    
}
