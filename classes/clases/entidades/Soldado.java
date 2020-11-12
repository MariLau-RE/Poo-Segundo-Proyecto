/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.entidades;

import clases.ObjectCollision;
import clases.Partida;
import clases.Sprite;

/**
 *
 * @author Josué Alvarez M
 */
public class Soldado extends Personaje{

    public Soldado() {
        super("soladado", new Sprite(Partida.dirImagenes + "/personajes/soldado.png"), 100, 100, 50, 2, 3, false);
    }
    

    @Override
    public void Colision(ObjectCollision objeto) {}

    @Override
    public void run() {
    }

    @Override
    public Entidad newInstance() {
        return new Soldado();
    }

    @Override
    public void atacar(Entidad entidad) {
        entidad.agregarVida(-ataque);
    }
    
}
