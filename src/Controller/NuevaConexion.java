/*
 * Clase que gestiona la conexión de un cliente con un socket en concreto.
 *
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
public class NuevaConexion extends Thread implements  Serializable{
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
    private final String finciclo = "Fin Ciclo";
    private final String finejecucion = "Finalizar";
    
    private final int nombre;
    private double coordX, coordY;
    private String idHilo;
    private String idPaquete;
    
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
            idHilo = entradatxt.readUTF();
            
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
        try {
            salidatxt.writeUTF(empezar);
            salidatxt.flush();
            try {
                sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(NuevaConexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            RecepcionPaquete();
        } catch (IOException ex) {
            System.out.println("[ERROR] No se ha podido enviar el mensaje de empezar.");
        }
    }
    
    public void RecepcionPaquete(){
        Object aux;
        try {
            entradaObj = new ObjectInputStream(sock.getInputStream());
            try {
                aux = entradaObj.readObject();
                p = (Paquete)aux;
                
                idPaquete = p.getID();
                coordX = p.getX();
                coordY = p.getY();
                
            } catch (ClassNotFoundException ex) {
                System.out.println("[ERROR]  Imposible realizar el casteo del socket "+nombre);
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
    
    public void EnviarPaquete(Paquete _p){
        try {
            salidatxt = new DataOutputStream(sock.getOutputStream());
            salidatxt.writeUTF("Te envio a tus vecinos.");
            salidatxt.flush();
            salidaObj = new ObjectOutputStream(sock.getOutputStream());
            salidaObj.writeObject(_p);
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
        
        try {
            salidatxt = new DataOutputStream(sock.getOutputStream());
            salidatxt.writeUTF("Numero Vecinos");
            salidatxt.flush();
            
            aux = String.valueOf(x);
            salidatxt = new DataOutputStream(sock.getOutputStream());
            salidatxt.writeUTF(aux);
            salidatxt.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(NuevaConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public boolean EsperarRecibidos(){
        boolean recibidos = false;
        try {
            entradatxt = new DataInputStream(sock.getInputStream());
            String mensaje = entradatxt.readUTF();
            while(!"Todos Recibidos".equals(mensaje)){
                try {
                    this.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(NuevaConexion.class.getName()).log(Level.SEVERE, null, ex);
                }
                entradatxt = new DataInputStream(sock.getInputStream());
                mensaje = entradatxt.readUTF();
            }
            recibidos = true;
        } catch (IOException ex) {
            System.out.println("NO HE PODIDO LEER EL MENSAJE");
            Logger.getLogger(NuevaConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recibidos;
    }
    
    public void FinCiclo(){
        try {
            salidatxt = new DataOutputStream(sock.getOutputStream());
            salidatxt.writeUTF(finciclo);
            salidatxt.flush();
        } catch (IOException ex) {
            Logger.getLogger(NuevaConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void FinEjecucion(){
        try {
            salidatxt = new DataOutputStream(sock.getOutputStream());
            salidatxt.writeUTF(finejecucion);
            salidatxt.flush();
        } catch (IOException ex) {
            Logger.getLogger(NuevaConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
