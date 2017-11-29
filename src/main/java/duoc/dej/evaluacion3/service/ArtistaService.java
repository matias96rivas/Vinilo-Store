/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.dej.evaluacion3.service;

import duoc.dej.evaluacion3.entity.Artista;
import duoc.dej.evaluacion3.exception.ArtistaNFE;
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
public class ArtistaService {
    
    @PersistenceContext
    EntityManager em;
    
    Logger lg = Logger.getLogger(this.getClass().getSimpleName());

    public ArtistaService() {
    }
    
    public Artista crear(Artista a){
        em.persist(a);
        return a;
    }
    
    public List<Artista> listar(){
        TypedQuery<Artista> q = em.createQuery("SELECT a FROM Artista a", Artista.class);
        return q.getResultList();
    }
    
    public Artista listarXID(Long id){
        return em.find(Artista.class, id);
    }
    
    public List<Artista> buscar(String nom, Long gId){
        if (nom != null && !nom.isEmpty() && gId != null && gId > 0) {
            String jpql = "SELECT a FROM Artista a WHERE LOWER(a.nombre) LIKE :nombre AND a.genero.id = :generoId";
            TypedQuery<Artista> q = em.createQuery(jpql, Artista.class);
            q.setParameter("nombre", "%" + nom + "%");
            return q.getResultList();
        }
        if (nom != null && !nom.isEmpty()) {
            String jpql = "SELECT a FROM Artista a WHERE LOWER(a.nombre) LIKE :nombre";
            TypedQuery<Artista> q = em.createQuery(jpql, Artista.class);
            q.setParameter("nombre", "%" + nom + "%");
            return q.getResultList();
        }
        if (nom == null || nom.isEmpty()) {
            if (gId != null && gId > 0) {
                String jpql = "SELECT a FROM Artista WHERE a.genero.id =:generoId";
                TypedQuery<Artista> q = em.createQuery(jpql, Artista.class);
                return q.getResultList();
            }
        }
        return listar();
    }
    
    public void eliminar(Long id) throws ArtistaNFE{
        Artista a = listarXID(id);
        if (id == null) {
            String msjE = String.format("Artista no encontrado", id);
            lg.log(Level.SEVERE, msjE);
            throw new ArtistaNFE(msjE);
        }
        em.remove(a);
        
    }
    
    
}
