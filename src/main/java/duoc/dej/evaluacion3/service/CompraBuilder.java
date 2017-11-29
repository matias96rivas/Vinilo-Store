/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.dej.evaluacion3.service;

import duoc.dej.evaluacion3.entity.Compra;
import duoc.dej.evaluacion3.entity.LineaCompra;
import duoc.dej.evaluacion3.entity.Vinilo;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Matias
 */@Stateless
public class CompraBuilder implements Serializable{
     
     Compra compra;
     
     @EJB
     ViniloService vs;

    public CompraBuilder() {
        compra = new Compra();
    }
    
    public CompraBuilder agregar(Long id, int cant){
        Vinilo v = vs.listarXID(id);
        LineaCompra lc = new LineaCompra(v, cant, v.getPrecio());
        compra.getlCom().add(lc);
        return this;        
    }
    
    public Compra build(){
        Compra c = compra;
        compra = new Compra();
        return c;
    } 
}
