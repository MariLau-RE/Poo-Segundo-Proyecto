/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.entidades.Items;

import clases.ObjectCollision;
import clases.Sprite;
import clases.entidades.Entidad;

/**
 *
 * @author Josué Alvarez M
 */
public abstract class Item extends Entidad{
    protected String nombre;
    protected int cantidad = 1;
    protected int cantidadMax;
    protected int tipo = 0;
    protected Entidad entidad; //quien posee el objeto
    
    public Item(String nombre, String ID, Sprite sprite, int vida, int vidaMax, int ataque, int movimiento, int ruido) {
        super(ID, sprite, ITEM, vida, vidaMax, ataque, movimiento, ruido, false);
        
        this.cantidadMax = 64;
        this.nombre = nombre;
    }

    public Item(String nombre, String ID, Sprite sprite, int vida, int vidaMax, int ataque, int movimiento, int ruido, int cantidadMax) {
        super(ID, sprite, ITEM, vida, vidaMax, ataque, movimiento, ruido, false);
        
        this.cantidadMax = cantidadMax;
        this.nombre = nombre;
    }
    
    public abstract void usar();
    
    public abstract Item newInstance();
    
    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getCantidadMax() {
        return cantidadMax;
    }

    public int getTipo() {
        return tipo;
    }

    public Entidad getEntidad() {
        return entidad;
    }
    
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidadMax(int cantidadMax) {
        this.cantidadMax = cantidadMax;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
    
    public int agregarCantidad(int cantidad){
        int cantidadSobrante = 0;
        if(cantidad > 0){
            this.cantidad += cantidad;
            if(this.cantidad > this.cantidadMax){
                cantidadSobrante = this.cantidad - this.cantidadMax;
                this.cantidad = this.cantidadMax;
            }
                
        }
        else{
            this.cantidad += cantidad;
            if(this.cantidad < 0){
                cantidadSobrante = -1*this.cantidad;
                this.cantidad = 0;
            }
        }
        
        return cantidadSobrante;
    }

    @Override
    public abstract void Colision(ObjectCollision objeto);

    @Override
    public abstract void run();
    
    
    
}
