/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Manu
 */
import java.io.Serializable;


public class Paquete implements Serializable{
    private double coordx, coordy; 
    private int id;
    
    public Paquete(int id, double coordx,double coordy){
        this.id = id;
        this.coordx = coordx;
        this.coordy = coordy;
    }
    
    public void setX(double coordx){
        this.coordx = coordx;
    }
    
    public double getX(){
        return this.coordx;
    }
    
    public void setY(double coordy){
        this.coordy = coordy;
    }
    
    public double getY(){
        return this.coordy;
    }
    
    public void setID(int id){
        this.id = id;
    }
    
     public int getID(){
        return this.id;
    }
    
}