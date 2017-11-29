/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.dej.evaluacion3.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author Matias
 */
@Embeddable
public class LineaCompra implements Serializable{
    
    @ManyToOne
    Vinilo vinilo;
    @Column(nullable = false)
    int cantidad;
    @Column(nullable = false)
    Long precio;

    public LineaCompra() {
    }

    public LineaCompra(Vinilo vinilo, int cantidad, Long precio) {
        this.vinilo = vinilo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public LineaCompra(Vinilo vinilo, int cantidad) {
        this.vinilo = vinilo;
        this.cantidad = cantidad;
        this.precio = vinilo.getPrecio();
    }

    public Vinilo getVinilo() {
        return vinilo;
    }

    public void setVinilo(Vinilo vinilo) {
        this.vinilo = vinilo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }
    
    
    
    public Long subTotal(){
        return cantidad * precio;
    }
    
}
