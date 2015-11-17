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
    private ObjectInputStream entradaObj;
    private DataInputStream entradatxt;
    private ObjectOutputStream salidaObj;
    private DataOutputStream salidatxt;
    
    /**
     * Mensajes que enviamos
     */
    private final String conectado = "Conexión creada, mantente a la espera.";
    private final String empezar = "Comenzar";
    private final String recibido = "Coordenadas recibidas";
    
    private final int nombre;
    private double coordX, coordY;
    private Hilo hilo;
    
    
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
            this.wait();
            System.out.println("HE DESPERTADO "+nombre);
            Empezar();
        } catch (InterruptedException ex) {
            System.out.println("NO ME PUEDO PONER EN ESPERA");
        }
    }

    public Hilo getHilo(){
        return hilo;
    }
    
    public synchronized void Empezar(){
        System.out.println("VAMOS A EMPEZAR CONEXION: "+nombre);
        try {
            
            System.out.println("HE ENTRADO EN EL TRY "+ nombre);
            salidatxt.writeUTF(empezar);
            salidatxt.flush();
            System.out.println("JUSTO ANTES DEL NOTIFY ALL");
            RecepcionHilo();
            System.out.println("HE PASADO EL NOTIFYALL");
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
                System.out.println("HILO CREADO");
                coordX = hilo.getPosicionX();
                coordY = hilo.getPosicionY();

                System.out.println("HILO "+ hilo.getID()+" creado con las coordenadas X = "+hilo.getPosicionX()+" Y = "+hilo.getPosicionY());
                
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
            System.out.println("[ERROR]");
        }
    }
    
    public synchronized void Despertar(){
        this.notifyAll();
    }
    
    
}
