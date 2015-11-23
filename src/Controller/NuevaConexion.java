/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Paquete;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2_4
 */
public class NuevaConexion implements Runnable, Serializable{
    /**
     * Variables para la conexión y el envío y recepción de mensajes
     */
    private final Socket sock;
    private ObjectInputStream entradaObj = null;
    private DataInputStream entradatxt;
    private ObjectOutputStream salidaObj;
    private DataOutputStream salidatxt;
    private Paquete p;

    
    /**
     * Mensajes que enviamos
     */
    private final String conectado = "Conexión creada, mantente a la espera.";
    private final String empezar = "Comenzar";
    private final String recibido = "Coordenadas recibidas";
    
    private final int nombre;
    private double coordX, coordY;
    private int id;
    
    public NuevaConexion (Socket _socket, int _nombre){
        sock = _socket;
        nombre = _nombre;
    }
    
    @Override
    public synchronized void run(){
        try {
            salidatxt = new DataOutputStream(sock.getOutputStream());
            salidatxt.writeUTF(conectado);
            salidatxt.flush();
            
            entradatxt = new DataInputStream(sock.getInputStream());
            
            System.out.println("ENTRADA: "+entradatxt.readUTF());
            
        } catch (IOException ex) {
            System.out.println("[ERROR] Socket " + nombre + " finalizado.");
        }
        try {
            this.wait(100);
        } catch (InterruptedException ex) {
            System.out.println("NO ME PUEDO PONER EN ESPERA");
        }
    }
    
    public synchronized void Empezar(){
        System.out.println("VAMOS A EMPEZAR LA RECEPCIÓN: "+nombre);
        try {
            salidatxt.writeUTF(empezar);
            salidatxt.flush();
            RecepcionPaquete();
        } catch (IOException ex) {
            System.out.println("[ERROR] No se ha podido enviar el mensaje de empezar.");
        }
    }
    
    public void RecepcionPaquete(){
        Object aux;
        try {
            System.out.println("Socket "+ nombre+": Intentamos recibir el paquete");
            entradaObj = new ObjectInputStream(sock.getInputStream());
            try {
                aux = entradaObj.readObject();
                p = (Paquete)aux;
                System.out.println("Paquete Recibido");
                
                id = p.getID();
                coordX = p.getX();
                coordY = p.getY();

                System.out.println("Paquete "+ id +" creado con las coordenadas X = "+coordX+" Y = "+coordY);
                
            } catch (ClassNotFoundException ex) {
                System.out.println("[ERROR]  Imposible realizar el casteo del socket "+nombre);
                ex.printStackTrace();
            }
            try {
                salidatxt = new DataOutputStream(sock.getOutputStream());
                salidatxt.writeUTF("Mantente a la espera.");
                salidatxt.flush();
            } catch (IOException ex) {
                System.out.println("[ERROR]  No se puede enviar el segundo mensaje.");
            }
        } catch (IOException ex) {
            System.out.println("[ERROR] No se ha obtenido el objeto");
        }
        
    }
    public int getNombre() {
        return nombre;
    }
    
    public void EnviarPaquete(Paquete p){
        try {
            salidatxt = new DataOutputStream(sock.getOutputStream());
            salidatxt.writeUTF("Te envio a tus vecinos.");
            salidatxt.flush();
            
            salidaObj = new ObjectOutputStream(sock.getOutputStream());
            salidaObj.writeObject(p);
            salidaObj.flush();
        } catch (IOException ex) {
            Logger.getLogger(NuevaConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Paquete getP() {
        return p;
    }
    
    public void EnviarNumVecinos(int x){
        String aux;
        aux = String.valueOf(x);
        try {
            salidatxt = new DataOutputStream(sock.getOutputStream());
            salidatxt.writeUTF("Numero Vecinos");
            salidatxt.flush();
            
            salidatxt = new DataOutputStream(sock.getOutputStream());
            salidatxt.writeUTF(aux);
            salidatxt.flush();
        } catch (IOException ex) {
            Logger.getLogger(NuevaConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
