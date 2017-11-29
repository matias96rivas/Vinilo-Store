/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.dej.evaluacion3.service;

import duoc.dej.evaluacion3.entity.Usuario;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author LC1300XXXX
 */
@Stateless
public class UserService implements Serializable{
    
    static final long serialVersionUID = 99L;
    
    @PersistenceContext
    private EntityManager em;

    Logger lg = Logger.getLogger(this.getClass().getSimpleName());
    
    public Usuario crear(Usuario u){
        em.persist(u);
        return u;        
    }
    
    public Usuario findUser(String usuario, String password) {
        String jpql = "SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.contrasena = :contrasena";
        TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
        query.setParameter("usuario", usuario);
        query.setParameter("contrasena", password);
        try {
            Usuario usu = query.getSingleResult();
            return usu;
        } catch (NoResultException nre) {
            return null;
        }
    }
}
