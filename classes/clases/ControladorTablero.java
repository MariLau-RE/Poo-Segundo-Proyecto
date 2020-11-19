/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static clases.Tablero.casillas;
import static clases.Tablero.getCasillaAleatoria;
import static clases.Tablero.getCasillas;
import static clases.Tablero.getCasillasCoordenada;
import clases.entidades.Base;
import clases.entidades.Entidad;
import clases.entidades.Explorador;
import clases.entidades.FabricaEntidades;
import clases.entidades.Hechicero;
import clases.entidades.Items.Item;
import clases.entidades.Personaje;
import clases.entidades.Soldado;
import clases.entidades.Zombie;
import clases.entidades.ZombieBrujo;
import clases.entidades.ZombieJefe;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Josué Alvarez M
 */
public class ControladorTablero extends Thread{
    public static ArrayList<Entidad> entidades = new ArrayList();
    public static ArrayList<Personaje> personajes = new ArrayList();
    public static ArrayList<Zombie> zombies = new ArrayList();
    
    private int fila, columna; // posicion de la base
    public static Base base;
    
    public static void addEntidad(Entidad entidad){
        if(entidad.getTipoEntidad() == Entidad.PERSONAJE)
            personajes.add((Personaje) entidad);
        if(entidad.getTipoEntidad() == Entidad.ZOMBIE)
            zombies.add((Zombie) entidad);
        
        entidades.add(entidad);
        entidad.getSprite().getLabel().addMouseListener(Partida.cursor);
    }
    
    public static void removeEntidad(Entidad entidad){
        if(entidad.getTipoEntidad() == Entidad.PERSONAJE)
            personajes.remove((Personaje) entidad);
        if(entidad.getTipoEntidad() == Entidad.ZOMBIE)
            zombies.remove((Zombie) entidad);
        
        entidades.remove(entidad);
    }
    
    
    /**
     * Pocisiona la entidad en una casilla aleatoria valida
     * 
     * @param entidad 
     */
    private void posicionarEntidadAleatoriamente(Entidad entidad){
        Casilla casilla;
        while(true){
            casilla = getCasillaAleatoria();
            
            if(casilla == null)
                break;
            
            if(casilla.puedeAcceder(entidad)){
                casilla.setEntidadNueva(entidad);
                break;
            }
        }
    }
    
    /**
     * Construye la base en el tablero
     */
    private void iniciarBase(){
        // se definen las casillas con terreno alto
        getCasillas(fila, columna).setTerreno(Casilla.TERRENO_ALTO);
        getCasillas(fila, columna + 1).setTerreno(Casilla.TERRENO_ALTO);
        getCasillas(fila, columna + 3).setTerreno(Casilla.TERRENO_ALTO);
        getCasillas(fila, columna + 4).setTerreno(Casilla.TERRENO_ALTO);
        getCasillas(fila + 1, columna + 4).setTerreno(Casilla.TERRENO_ALTO);
        getCasillas(fila + 3, columna + 4).setTerreno(Casilla.TERRENO_ALTO);
        getCasillas(fila + 4, columna + 4).setTerreno(Casilla.TERRENO_ALTO);
        getCasillas(fila + 4, columna + 3).setTerreno(Casilla.TERRENO_ALTO);
        getCasillas(fila + 4, columna + 1).setTerreno(Casilla.TERRENO_ALTO);
        getCasillas(fila + 4, columna).setTerreno(Casilla.TERRENO_ALTO);
        getCasillas(fila + 3, columna).setTerreno(Casilla.TERRENO_ALTO);
        getCasillas(fila + 1, columna).setTerreno(Casilla.TERRENO_ALTO);
        
        iniciarPersonajes();
    }
    
    /**
     * Genera los personajes en el tablero
     */
    private void iniciarPersonajes(){
        base = (Base) FabricaEntidades.newEntidad("base");
        base.getSprite().getLabel().addMouseListener(Partida.cursor);
        getCasillas(fila + 2, columna + 2).setEntidadNueva(base);
        
        Soldado p1 = (Soldado) FabricaEntidades.newEntidad("soldado");
        getCasillas(fila + 1, columna + 1).setEntidadNueva(p1);
        addEntidad(p1);
        
        Explorador p2 = (Explorador) FabricaEntidades.newEntidad("explorador");
        getCasillas(fila + 1, columna + 3).setEntidadNueva(p2);
        addEntidad(p2);
        
        Hechicero p3 = (Hechicero) FabricaEntidades.newEntidad("hechicero");
        getCasillas(fila + 3, columna + 1).setEntidadNueva(p3);
        addEntidad(p3);
    }
    
    /**
     * Genera los enemigos en el tablero
     */
    private void iniciarEnemigos(){
        ZombieBrujo zb1 = (ZombieBrujo) FabricaEntidades.newEntidad("zombieBrujo");
        zb1.setEntidad(FabricaEntidades.newEntidad("zombieBomba"));
        zb1.setFrecuencia(2);
        posicionarEntidadAleatoriamente(zb1);
        addEntidad(zb1);
        
        ZombieBrujo zb2 = (ZombieBrujo) FabricaEntidades.newEntidad("zombieBrujo");
        posicionarEntidadAleatoriamente(zb2);
        addEntidad(zb2);
        
        ZombieBrujo zb3 = (ZombieBrujo) FabricaEntidades.newEntidad("zombieBrujo");
        posicionarEntidadAleatoriamente(zb3);
        addEntidad(zb3);
    }
    
    private void iniciarMapa(){
        fila = 4;
        columna = 8;
        
        iniciarBase();
        iniciarEnemigos();
        
        for (int i = 0; i < 50; i++) {
            getCasillaAleatoria().setTerreno(Casilla.TERRENO_ALTO);
        }
        
    }
    
    /**
     * Verifica si una entidad a muerto y de ser así esta se elimina del tablero.
     */
    private void verificarMuertes(){
        if(base.getVida() == 0){
            for (Entidad e : entidades) {
                e.agregarVida(-e.getVidaMax());
            }
        }
        
        int cont = 0;
        while(cont == 0){
            cont = 1;
            for (Entidad e : entidades) {
                // la entidad no tiene vida
                if(e.getVida() == 0){
                        
                    Casilla c = getCasillasCoordenada(e.getSprite().getX(), e.getSprite().getY());
                    
                    // oculta la informacion de la entidad
                    Partida.cursor.ocultarInformacion();
                    
                    c.setEntidad(null);
                    if(e.getTipoEntidad() == Entidad.ZOMBIE){
                        Zombie z = (Zombie) e;
                        
                        // De no tener un item hay unas posibilidades de que obtenga uno para soltar
                        if(z.getItem() == null){
                            Random r = new Random();
                            if(r.nextInt(100) + 1 <= 15){
                                ArrayList<Entidad> items = FabricaEntidades.newEntidad(Entidad.ITEM);
                                z.setItem((Item) items.get(r.nextInt(items.size())));
                            }
                        }
                        
                        if(z.getItem() != null)
                            c.setEntidadNueva(z.getItem()); // suelta el item
                        
                    }
                    
                    Partida.ventana.panelPrincipal.remove(e.getSprite().getLabel());
                    removeEntidad(e);
                    
                    cont = 0;
                    break;
                }
            }
        }
    }
    
    private void limpiarRuido(){
        for (Casilla casilla : casillas) {
            casilla.setRuido(0);
        }
    }
    
    private void ejecutarTurnos(){
        for (Personaje personaje : personajes) {
            base.ejecutarTurno();
            personaje.ejecutarTurno();
            verificarMuertes();
        }
        
        int pos = 0;
        int zombiesCant = zombies.size();
        while(pos < zombies.size() && zombies.size() > 0){
            zombies.get(pos).ejecutarTurno();
            verificarMuertes();
            
            if(zombies.size() < zombiesCant){
                pos -= zombiesCant - zombies.size() - 1;
            }
            pos++;
        }
        
        limpiarRuido();
    }
    
    @Override
    public void run(){
        iniciarMapa();
        int turno = 1;
        while(true){
            ejecutarTurnos();
            turno++;
            if(personajes.isEmpty()){
                while(true){
                    try {
                        System.out.println("FIN DE PARTIDA! TURNO: " + turno);
                        sleep(500);
                    } catch (Exception e) {}
                }
            }
            
            if(zombies.isEmpty()){
                iniciarEnemigos();
                ZombieJefe zj = (ZombieJefe) FabricaEntidades.newEntidad("zombieJefe");
                getCasillaAleatoria().setEntidadNueva(zj);
                addEntidad(zj);
                
                // limpia las casillas
                for (Casilla c : casillas) {
                    if(c.getEntidad() != null)
                        if(c.getEntidad().getTipoEntidad() == Entidad.PERSONAJE)
                            c.setEntidad(null);
                }
                
                // elimina las imagenes de las entidades
                for (Personaje p : personajes) {
                    Partida.ventana.panelPrincipal.remove(p.getSprite().getLabel());
                }
                Partida.ventana.panelPrincipal.remove(base.getSprite().getLabel());
                
                // remueve las entidades de las listas
                base = null;
                personajes.removeAll(personajes);
                iniciarPersonajes();
            }
        }
    }
}
