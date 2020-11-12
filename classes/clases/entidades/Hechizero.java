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
 * @author Patatina
 */
public class Hechizero extends Personaje {

    public Hechizero() {
        super("hechizero", new Sprite(Partida.dirImagenes + "/personajes/Hechizero.png"), 200, 200, 100, 2, 3, false);
    }
    

    @Override
    public void Colision(ObjectCollision objeto) {}

    @Override
    public void run() {
    }

    @Override
    public Entidad newInstance() {
        return new Hechizero();
    }

    @Override
    public void atacar(Entidad entidad) {
        entidad.agregarVida(-ataque);
    }
    
}