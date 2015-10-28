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
import java.net.Socket;

/**
 *
 * @author Jorge
 */
public class NuevaConexion extends Thread{
    
    private final Socket sock;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private String mensaje;
    private final int nombre;
    private final String idHilo = "";
    private int posicionX = 0;
    private int posicionY = 0;
    
    public NuevaConexion (Socket _socket, int _nombre){
        sock = _socket;
        nombre = _nombre;
    }
    
    @Override
    public void run(){
        try {
            entrada = new DataInputStream(new DataInputStream(sock.getInputStream()));
            mensaje = entrada.readUTF();
            System.out.println("Proceso: "+mensaje+" conectado en el socket "+nombre);
            salida = new DataOutputStream(sock.getOutputStream());
            salida.writeUTF("Conectado en el socket "+nombre+".");
            
        } catch (IOException ex) {
            System.out.println("[ERROR] Socket "+nombre+" finalizado.");
        }
    }
    
    public Hilo crearHilo(){
        Hilo h = new Hilo(idHilo, posicionX, posicionY);
        return h;
    }
}
