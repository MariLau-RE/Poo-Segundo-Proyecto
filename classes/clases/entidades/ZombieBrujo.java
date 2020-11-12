/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.entidades;

import clases.Casilla;
import clases.ObjectCollision;
import clases.Partida;
import clases.Sprite;
import clases.Tablero;
import java.util.ArrayList;

/**
 *
 * @author Josué Alvarez M
 */
public class ZombieBrujo extends Zombie{
    private Entidad entidad;
    private int cantidad  = 0;
    private int frecuencia = 0;
    private int contFrecuencia = 0;
    
    public ZombieBrujo(int cantidad, int frecuencia) {
        super("zombieBrujo", new Sprite(Partida.dirImagenes + "/zombies/zombieBrujo.png"), 75, 75, 0, 1, 0, false);
        
        this.cantidad = cantidad;
        this.frecuencia = frecuencia;
        
        this.entidad = new ZombieComun();
    }
    
    public ZombieBrujo(int cantidad, int frecuencia, Entidad entidad) {
        super("zombieBrujo", new Sprite(Partida.dirImagenes + "/zombies/zombieBrujo.png"), 75, 75, 0, 1, 0, false);
        
        this.vida = 50;
        
        this.cantidad = cantidad;
        this.frecuencia = frecuencia;
        
        this.entidad = entidad;
    }
    
    public void generarEntidad(int cantidad){
        ArrayList<Casilla> casillasRededor = Tablero.getCasillas(Tablero.getCasillasCoordenada(sprite.getX(), sprite.getY()));
        
        Casilla c;
        int cont;
        for (int i = 0; i < cantidad; i++) {
            cont = 0;
            while(casillasRededor.size() > 0){
                c = Tablero.getCasillaAleatoria(casillasRededor);
                
                if(c == null) // si no hay casillas disponibles
                    break;
                
                if(c.getEntidad() == null)
                    cont = 1;
                else if(c.getEntidad().getTipoEntidad() == Entidad.ITEM)
                    cont = 1;
                
                if(cont == 1){
                    Entidad nuevaEntidad = entidad.newInstance();
                    Tablero.addEntidad(nuevaEntidad);
                    
                    c.setEntidadNueva(nuevaEntidad);
                    c.getEntidad().start();
                    break;
                }
            }
        }
        
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public int getContFrecuencia() {
        return contFrecuencia;
    }
    
    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public void setContFrecuencia(int contFrecuencia) {
        this.contFrecuencia = contFrecuencia;
    }
    
    @Override
    public void Colision(ObjectCollision objeto) {}

    @Override
    public void run() {}

    @Override
    public Entidad newInstance() {
        return new ZombieBrujo(cantidad, frecuencia, entidad);
    }

    @Override
    public void ejecutarTurno() {
        ArrayList<Casilla> casillasRededor = Tablero.getCasillas(Tablero.getCasillasCoordenada(sprite.getX(), sprite.getY()));
        int cont = 0;
        for (Casilla c : casillasRededor) {
            if(c.getEntidad() != null)
                if(c.getEntidad().getTipoEntidad() == Entidad.PERSONAJE){
                    cont = 1;
                    break;
                }
            if(c.getRuido() > 0){
                cont = 1;
                break;
            }
        }
        
        if(cont == 1){
            int pos = 0;
            while (pos < casillasRededor.size()) {
                Casilla casillaMenor = null; // casilla con menor nivel de ruido
                for (Casilla c : casillasRededor) {
                    if(casillaMenor == null)
                        casillaMenor = c;
                    else if(casillaMenor.getRuido() > c.getRuido()){
                        casillaMenor = c;
                    }
                }
                if(casillaMenor.getRuido() >= 0){
                    if(casillaMenor.puedeAcceder(this)){
                        if(casillaMenor.getEntidad() == null){
                            moverACasilla(casillaMenor);
                            break;
                        }
                        else if(casillaMenor.getEntidad().getTipoEntidad() == Entidad.ITEM){
                            moverACasilla(casillaMenor);
                            break;
                        }
                    }
                    // si la casilla no estaba disponible
                    casillasRededor.remove(casillaMenor);
                    pos--;
                }
                else
                    break;


                pos++;
            }
        }
        else{
            contFrecuencia ++;
            if(contFrecuencia >= frecuencia){
                generarEntidad(cantidad);
                contFrecuencia = 0;
            }
        }
    }
    
}
