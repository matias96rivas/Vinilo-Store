/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.dej.evaluacion3.service;

import duoc.dej.evaluacion3.entity.Compra;
import duoc.dej.evaluacion3.exception.CompraNFE;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Matias
 */
@Stateless
public class CompraService implements Serializable{
    
    @PersistenceContext
    private EntityManager em;
    
    Logger lg = Logger.getLogger(this.getClass().getSimpleName());
    
    public Compra crear(Compra c){
        em.persist(c);
        return c;
    }
    
    public Compra listarXID(Long id){
        return em.find(Compra.class, id);
    }
    
    public List<Compra> listar(){
        TypedQuery<Compra> q = em.createQuery("SELECT c FROM Compra c", Compra.class);
        return q.getResultList();
    }
    
    public void eliminar(Long id) throws CompraNFE{
        Compra c = listarXID(id);
        if (c == null) {
            String msjE = String.format("Compra no encontrada", c);
            lg.log(Level.SEVERE, msjE);
            throw new CompraNFE(msjE);
        }
        em.remove(c);
    }
    
}
