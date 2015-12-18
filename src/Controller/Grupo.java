/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
public class Grupo extends Thread{
    private int iDgrupo;
    private int numConexiones;
    private int contador = 0;
    private Socket sock;
    private int nombre = 0;
    private ArrayList<NuevaConexion> vectorConexiones;
    
    public Grupo(int _iDgrupo ,int _numConexiones){
        iDgrupo = _iDgrupo;
        numConexiones = _numConexiones;
        vectorConexiones = new ArrayList<NuevaConexion>();
    }
    
    public int getiDgrupo() {
        return iDgrupo;
    }

    public void setiDgrupo(int iDgrupo) {
        this.iDgrupo = iDgrupo;
    }

    public int getNumConexiones() {
        return numConexiones;
    }

    public void setNumConexiones(int numConexiones) {
        this.numConexiones = numConexiones;
    }
    
    public void nuevoCliente(Socket sock, int nombre){
        NuevaConexion conexion = new NuevaConexion(sock, nombre);
        
        vectorConexiones.add(conexion);
        contador++;
    }
    
    
    public NuevaConexion getNuevaConexion(int id){
        int i;
        Object aux = null;
        for(i = 0; i < numConexiones; i++){
            if(id == vectorConexiones.get(i).getNombre()){
                aux = vectorConexiones.get(i);
                i = numConexiones;
            }
        }
        return (NuevaConexion)aux;
    }
    
    @Override
    public synchronized void run(){
        for(int j = 0; j < numConexiones; j++){
            vectorConexiones.get(j).start();
        }
    }
}
