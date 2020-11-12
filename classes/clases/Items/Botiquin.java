/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.entidades.Items;

import clases.ObjectCollision;
import clases.Partida;
import clases.Sprite;

/**
 *
 * @author Josué Alvarez M
 */
public class Botiquin extends Item{

    public Botiquin() {
        super("Botiquin", "item_botiquin", new Sprite(Partida.dirImagenes + "/items/botiquin.png"), 1, 1, 0, 0, 0);
    }

    @Override
    public void usar() {
        entidad.agregarVida(50);
    }

    @Override
    public Item newInstance() {
        return new Botiquin();
    }

    @Override
    public void Colision(ObjectCollision objeto) {}

    @Override
    public void run() {}

    @Override
    public void ejecutarTurno() {}
    
}
