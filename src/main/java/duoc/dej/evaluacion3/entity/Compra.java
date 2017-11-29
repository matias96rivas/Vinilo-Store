/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.dej.evaluacion3.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Matias
 */
@Entity
public class Compra implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    @CollectionTable(name = "LINEACOMPRA")
    private List<LineaCompra> lCom = new ArrayList<>();

    public Compra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LineaCompra> getlCom() {
        return lCom;
    }

    public void setlCom(List<LineaCompra> lCom) {
        this.lCom = lCom;
    }
    
    
    public void quitar(Long vID){
        List<LineaCompra> sacar = new ArrayList<>();
        for(LineaCompra lc:getlCom()){
            if (Objects.equals(lc.getVinilo().getId(), vID)) {
                sacar.add(lc);
            }
        }
        getlCom().removeAll(sacar);
    }

    public Long total(){
        Long total = 0L;
        for(LineaCompra lc: lCom){
            total += lc.getPrecio() * lc.getCantidad();
        }
        return total;
    }
    
    public Long IVA(){
        return Math.round(0.19d * total().doubleValue());
    }
    
    public Long totalIVA(){
        return total() + IVA();
    }
    
    
}
