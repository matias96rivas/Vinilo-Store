/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.dej.evaluacion3.service;

import duoc.dej.evaluacion3.entity.Vinilo;
import duoc.dej.evaluacion3.exception.ViniloNFE;
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
public class ViniloService {
    
    @PersistenceContext
    EntityManager em;
    
    Logger lg = Logger.getLogger(this.getClass().getSimpleName());

    public ViniloService() {
    }
    
    public Vinilo crear(Vinilo v){
        em.persist(v);
        return v;
    }
    
    public List<Vinilo> listar(){
        TypedQuery<Vinilo> q = em.createQuery("SELECT v FROM Vinilo v", Vinilo.class);
        return q.getResultList();
    }
    
    public Vinilo listarXID(Long id){
        return em.find(Vinilo.class, id);
    }
    
    public List<Vinilo> buscar(String nom, Long gid){
        if (nom != null && !nom.isEmpty() && gid != null && gid > 0) {
            String jpql = "SELECT v FROM Vinilo v WHERE LOWER(v.nombre) LIKE :nombre AND v.genero.id = :generoId";
            TypedQuery<Vinilo> q = em.createQuery(jpql, Vinilo.class);
            q.setParameter("nombre", "%" + nom + "%");
            q.setParameter("generoId", "%" + gid + "%");
            return q.getResultList();
        }
        if (nom != null && !nom.isEmpty()) {
            String jpql = "SELECT v FROM Vinilo v WHERE LOWER(v.nombre) LIKE :nombre";
            TypedQuery<Vinilo> q = em.createQuery(jpql, Vinilo.class);
            q.setParameter("nombre", "%"+ nom +"%");
            return q.getResultList();
        }
        if (nom == null || nom.isEmpty()) {
            if (gid != null && gid >0) {
                String jpql = "SELECT v FROM Vinilo v WHERE v.genero.id = :generoId";
                TypedQuery<Vinilo> q = em.createQuery(jpql, Vinilo.class);
                q.setParameter("generoId", gid);
                return q.getResultList();
            }
        }
        return listar();
    }
    
    public void eliminar(Long id) throws ViniloNFE{
        Vinilo v = listarXID(id);
        if (v == null) {
            String msjE = String.format("Vinilo no encontrado", id);
            lg.log(Level.SEVERE, msjE);
            throw new ViniloNFE(msjE);
        }
        
    }
}
