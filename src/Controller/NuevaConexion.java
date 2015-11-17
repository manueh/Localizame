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
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2_4
 */
public class NuevaConexion extends Thread{
    /**
     * Variables para la conexión y el envío y recepción de mensajes
     */
    private Object objeto;
    private final Socket sock;
    private ObjectInputStream entradaObj;
    private DataInputStream entradatxt;
    private ObjectOutputStream salidaObj;
    private DataOutputStream salidatxt;
    
    /**
     * Mensajes que enviamos
     */
    private final String conectado = "Conexión creada, mantente a la espera.";
    private final String empezar = "Comenzar envio de coordenadas";
    private final String recibido = "Coordenadas recibidas";
    
    private final int nombre;
    private double coordX, coordY;
    private Hilo hilo;
    
    
    public NuevaConexion (Socket _socket, int _nombre){
        sock = _socket;
        nombre = _nombre;
        System.out.println("SOCKET -->"+nombre);
    }
    
    @Override
    public synchronized void run(){
        try {
            salidatxt = new DataOutputStream(sock.getOutputStream());
            salidatxt.writeUTF(conectado);
            salidatxt.flush();
            
            entradatxt = new DataInputStream(sock.getInputStream());
            this.wait();
            System.out.println("Salida: "+conectado);
            System.out.println("ENTRADA: "+entradatxt.readUTF());
            
        } catch (IOException ex) {
            System.out.println("[ERROR] Socket " + nombre + " finalizado.");
        } catch (InterruptedException ex) {
            Logger.getLogger(NuevaConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Hilo getHilo(){
        return hilo;
    }
    
    public void Empezar(){
        System.out.println("VAMOS A EMPEZAR CONEXION: "+nombre);
        try {
            salidatxt = new DataOutputStream(sock.getOutputStream());
            salidatxt.writeUTF(empezar);
            salidatxt.flush();
        } catch (IOException ex) {
            System.out.println("[ERROR] No se ha podido enviar el mensaje de empezar.");
        }
    }
    
    public void RecepcionHilo(){
        try {
            System.out.println("Intentamos recepcionar el hilo");
            entradaObj = new ObjectInputStream(sock.getInputStream());
            try {
                hilo = (Hilo) entradaObj.readObject();
                coordX = hilo.getPosicionX();
                coordY = hilo.getPosicionY();

                System.out.println("HILO "+ hilo.getID()+" creado con las coordenadas X = "+hilo.getPosicionX()+" Y = "+hilo.getPosicionY());
                
            } catch (ClassNotFoundException ex) {
                System.out.println("[ERROR]  Imposible realizar el casteo");
            }

            try {
                System.out.println("MANDO EL MENSAJE DE ESPERA OTRA VEZ");
                salidatxt = new DataOutputStream(sock.getOutputStream());
                salidatxt.writeUTF("Mantente a la espera.");
                salidatxt.flush();
            } catch (IOException ex) {
                System.out.println("[ERROR]  No se puede enviar el segundo mensaje.");
            }
        } catch (IOException ex) {
            System.out.println("[ERROR]");
        }
    }
}
