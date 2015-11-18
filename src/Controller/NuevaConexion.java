/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Hilo;
import Model.Paquete;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author 2_4
 */
public class NuevaConexion implements Runnable{
    /**
     * Variables para la conexión y el envío y recepción de mensajes
     */
    private Object objeto;
    private final Socket sock;
    private ObjectInputStream entradaObj = null;
    private DataInputStream entradatxt;
    private ObjectOutputStream salidaObj = null;
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
            //salidatxt.close();
            
            entradatxt = new DataInputStream(sock.getInputStream());
            
            System.out.println("ENTRADA: "+entradatxt.readUTF());
            
        } catch (IOException ex) {
            System.out.println("[ERROR] Socket " + nombre + " finalizado.");
        }
        try {
            this.wait(5000);
            System.out.println("HE DESPERTADO "+nombre);
            Empezar();
        } catch (InterruptedException ex) {
            System.out.println("NO ME PUEDO PONER EN ESPERA");
        }
    }
    
    public synchronized void Empezar(){
        System.out.println("VAMOS A EMPEZAR CONEXION: "+nombre);
        try {
            salidatxt.writeUTF(empezar);
            salidatxt.flush();
            RecepcionPaquete();
        } catch (IOException ex) {
            System.out.println("[ERROR] No se ha podido enviar el mensaje de empezar.");
        }
    }
    
    public void RecepcionPaquete(){
        Object ent = null;
        try {
            System.out.println("Socket "+ nombre+": Intentamos recepcionar el hilo");
            entradaObj = new ObjectInputStream(sock.getInputStream());
            try {
                ent = (Object) entradaObj.readObject();
                System.out.println("Paquete Recibido");
                p = (Paquete)ent;
                id = p.getID();
                coordX = p.getX();
                coordY = p.getY();

                System.out.println("Paquete "+ p.getID()+" creado con las coordenadas X = "+coordX+" Y = "+coordY);
                
            } catch (ClassNotFoundException ex) {
                System.out.println("[ERROR]  Imposible realizar el casteo");
            }
            
            System.out.println("SUPERADO EL TRY DE LA CREACION DEL HILO");

            try {
                System.out.println("MANDO EL MENSAJE DE ESPERA OTRA VEZ");
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
    
    public synchronized void Despertar(){
        this.notifyAll();
    }
    
    
}
