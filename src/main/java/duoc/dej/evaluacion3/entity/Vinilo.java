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
public class Vinilo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique = false, nullable = false)
    private String nombre;
    private String img;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    @NotNull
    @Column(nullable = false)
    private Long precio;
    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private Genero genero;
    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private Artista artista;

    public Vinilo() {
    }

    public Vinilo(String nombre, String img, String descripcion, Long precio, Genero genero, Artista artista) {
        this.nombre = nombre;
        this.img = img;
        this.descripcion = descripcion;
        this.precio = precio;
        this.genero = genero;
        this.artista = artista;
    }

    public Vinilo(Long id, String nombre, String img, String descripcion, Long precio, Genero genero, Artista artista) {
        this.id = id;
        this.nombre = nombre;
        this.img = img;
        this.descripcion = descripcion;
        this.precio = precio;
        this.genero = genero;
        this.artista = artista;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
    
    
}
