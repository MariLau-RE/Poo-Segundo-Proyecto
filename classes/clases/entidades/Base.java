/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.entidades;

import clases.ObjectCollision;
import clases.Partida;
import clases.Puntos;
import clases.Sprite;
import clases.Tablero;

/**
 *
 * @author Josué Alvarez M
 */
public class Base extends Entidad{
    private int curacion;
    private int curacionPropia;
    private int alcance;
    
    public Base() {
        super("base", new Sprite(Partida.dirImagenes + "/base/base.gif"), Entidad.BASE, 100, 100, 0, 0, 3, false);
        
        alcance = 3;
        curacion = 5;
        curacionPropia = 2;
    }

    @Override
    public void ejecutarTurno() {
        generarRuido();
        agregarVida(curacionPropia);
        for (Entidad e : Tablero.entidades) {
            if(e.getTipoEntidad() == Entidad.PERSONAJE){
                double distancia = Puntos.distanciaPuntos(e.getSprite().getX(), e.getSprite().getY(), sprite.getX(), sprite.getY());
                double alcance = this.alcance * Tablero.getCasillas(0).getSprite().getImageIcon().getIconWidth();
                
                if(alcance >= distancia)
                    e.agregarVida(curacion);
            }
        }
    }

    @Override
    public Entidad newInstance() {
        return new Base();
    }

    @Override
    public void Colision(ObjectCollision objeto) {}

    @Override
    public void run() {}
    
}
