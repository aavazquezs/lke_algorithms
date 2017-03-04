/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.gitae.mdem.irt.clases;

/**
 *
 * @author administrador
 */
public class Item {
    private double dificultad;
    private double discriminador;
    private double probabilidadAdivinar;
    private int respuesta;

    public Item(double dificultad, double discriminador, double probabilidadAdivinar, int respuesta) {
        this.dificultad = dificultad;
        this.discriminador = discriminador;
        this.probabilidadAdivinar = probabilidadAdivinar;
        this.respuesta = respuesta;
    }

    public double getDificultad() {
        return dificultad;
    }

    public double getDiscriminador() {
        return discriminador;
    }

    public void setDificultad(double dificultad) {
        this.dificultad = dificultad;
    }

    public void setDiscriminador(double discriminador) {
        this.discriminador = discriminador;
    }  

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    public double getProbabilidadAdivinar() {
        return probabilidadAdivinar;
    }

    public void setProbabilidadAdivinar(double probabilidadAdivinar) {
        this.probabilidadAdivinar = probabilidadAdivinar;
    }
    
    
}
