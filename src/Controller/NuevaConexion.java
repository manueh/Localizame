/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Hilo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2_4
 */
public class NuevaConexion extends Thread{
    
    private Object objeto;
    private final Socket sock;
    private ObjectInputStream entrada;
    private DataInputStream mens;
    private DataOutputStream salida;
    private String mensaje;
    private final int nombre;
    private double coordX, coordY;
    /*private String idHilo = "";
    private int posicionX = 0;
    private int posicionY = 0;*/
    private Hilo hilo;
    
    public NuevaConexion (Socket _socket, int _nombre){
        sock = _socket;
        nombre = _nombre;
        //hilo = new Hilo("",0,0);
        System.out.println("SOCKET -->"+nombre);
    }
    
    @Override
    public void run(){
        try {
            System.out.println("LLEGO AL START");
            entrada = new ObjectInputStream(new DataInputStream(sock.getInputStream()));
            mens = new DataInputStream(new DataInputStream(sock.getInputStream()));
            salida = new DataOutputStream(sock.getOutputStream());
            System.out.println("ENTRADA: "+mens.toString());
            if(entrada.readUTF() != null){
                try {
                    hilo = (Hilo) entrada.readObject();
                    coordX = hilo.getPosicionX();
                    coordY = hilo.getPosicionY();
                    System.out.println("x: " + coordX + "   Y: " + coordY);
                    //objeto = (Hilo) entrada.readObject();
                
                } catch (ClassNotFoundException ex) {
                    System.out.println("[ERROR]  Imposible realizar el casteo");
                }
                salida.writeUTF("Mantente a la espera.");
            }
            
            salida.writeUTF("Conectado en el socket " + nombre + ".");
           
        } catch (IOException ex) {
            System.out.println("[ERROR] Socket " + nombre + " finalizado.");
        }
    }

    public Hilo getHilo(){
        return hilo;
    }
}
