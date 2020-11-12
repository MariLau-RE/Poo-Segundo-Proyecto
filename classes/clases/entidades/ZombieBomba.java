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
public class ZombieBomba extends Zombie{

    public ZombieBomba() {
        super("zombieBomba", new Sprite(Partida.dirImagenes + "/zombies/zombieBomba.png"), 15, 15, 25, 4, 0, false);
    }

    @Override
    public void Colision(ObjectCollision objeto) {}

    @Override
    public void run() {}

    @Override
    public Entidad newInstance() {
        return new ZombieBomba();
    }
    
}
