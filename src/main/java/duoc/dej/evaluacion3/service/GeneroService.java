/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.dej.evaluacion3.service;

import duoc.dej.evaluacion3.entity.Genero;
import duoc.dej.evaluacion3.exception.GeneroNFE;
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
 * @author Matias Rivas
 */
@Stateless
public class GeneroService implements Serializable{
    
    @PersistenceContext
    EntityManager em;
    
    Logger lg = Logger.getLogger(this.getClass().getSimpleName());

    public GeneroService() {
    }
    
    public Genero crear(Genero g){
        em.persist(g);
        return g;
    }
    
    public Genero listarXID(Long id){
        Genero g = em.find(Genero.class, id);
        return g;
    }
    
    public List<Genero> listar(){
        TypedQuery<Genero> q = em.createQuery("SELECT g FROM Genero g", Genero.class);
        return q.getResultList();
    }
    
    public void eliminar(Long id) throws GeneroNFE{
        Genero g = listarXID(id);
        if (g == null) {
            String msjE = String.format("Genero no encontrado!!!", id);
            lg.log(Level.SEVERE, msjE);
            throw new GeneroNFE(msjE);
        }
    }
    
}
