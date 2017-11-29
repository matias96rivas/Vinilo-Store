/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.dej.evaluacion3.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Matias
 */
@Entity
public class Artista implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique = true, nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String pais;
    @Column(nullable = false)
    private boolean estado;
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private String web;
    @NotNull
    @JoinColumn
    @ManyToOne
    private Genero genero;

    public Artista() {
    }

    public Artista(String nombre, String pais, boolean estado, String descripcion, String web, Genero genero) {
        this.nombre = nombre;
        this.pais = pais;
        this.estado = estado;
        this.descripcion = descripcion;
        this.web = web;
        this.genero = genero;
    }

    public Artista(Long id, String nombre, String pais, boolean estado, String descripcion, String web, Genero genero) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.estado = estado;
        this.descripcion = descripcion;
        this.web = web;
        this.genero = genero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    
}
