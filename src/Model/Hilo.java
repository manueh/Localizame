/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Jorge
 */
public class Hilo {
    
    private int posicionX;
    private int posicionY;
    private String id;
    
    public Hilo(String _id, int _posicionX, int _posicionY){
        id = _id;
        posicionX = _posicionX;
        posicionY = _posicionY;
    }
    
    public void setPosicionX(int _posicionX){
        posicionX = _posicionX;
    }
    
    public void setPosicionY(int _posicionY){
        posicionY = _posicionY;
    }
    
    public void setID(String _id){
        id = _id;
    }
    
    public int getPosicionX(){
        return posicionX;
    }
    
    public int getPosicionY(){
        return posicionY;
    }
    
    public String getID(){
        return id;
    }
    
    
}
