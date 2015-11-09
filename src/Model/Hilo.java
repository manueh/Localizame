/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author 2_4
 */
public class Hilo {
    
    private int idHilo;
    Thread h;
    private double coordx, coordy;
    private String id;
    
    public Hilo(String _id, int _coordx, int _coordy){
        id = _id;
        coordx = _coordx;
        coordy = _coordy;
    }
    
    public void setPosicionX(int _posicionX){
        coordx = _posicionX;
    }
    
    public void setPosicionY(int _posicionY){
        coordy = _posicionY;
    }
    
    public void setID(String _id){
        id = _id;
    }
    
    public double getPosicionX(){
        return coordx;
    }
    
    public double getPosicionY(){
        return coordy;
    }
    
    public String getID(){
        return id;
    }
    
    
}
