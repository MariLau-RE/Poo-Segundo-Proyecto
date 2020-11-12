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
public class ZombieJefe extends Zombie{

    public ZombieJefe() {
        super("zombieJefe", new Sprite(Partida.dirImagenes + "/zombies/zombieJefe.png"), 200, 200, 25, 2, 3, true);
    }
    

    @Override
    public void Colision(ObjectCollision objeto) {}

    @Override
    public void run() {}

    @Override
    public Entidad newInstance() {
        return new ZombieJefe();
    }
    
}
