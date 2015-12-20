/*
*   Clase servidor a la que se conectan todos los clientes. Es el encargado de 
*   recibir y enviar las posiciones de cada uno de ellos al resto de vecinos.
*/
package Controller;

import Model.Model;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    /**
     * Variables con las cantidades de vecinos minima y máxima que puede tener 
     * cada grupo del servidor
     */
    private ServerSocket ss;
    private Socket sock;
    private int nombre = 0;
        
    /**
     * Puerto en el que escucha el servidor
     */
    private final int portNumber = 5000;
    /*
    *   Variables necesarias para el manejo de las conexiones
    */
    private int numeroConexiones = 0;
    private int numfila = 0;
    private int numColumna = 0;
    private int numGrupo = 0;
    private int numTotalClientes = 0;
    private final Model modelo;
    private Grupo vectorGrupos[];
    
    
    /**
     * Constructor con parámetros
     * @param totalClients  define el número total de clientes que van a conectarse
     * @param _modelo       instancia del modelo para tener acceso a los datos
     */
    public Servidor(int totalClients, Model _modelo){
       modelo = _modelo;
       vectorGrupos = new Grupo[modelo.getNumGrupos()];
       numTotalClientes = totalClients;
       iniciarServer();
    }
    
    private synchronized void iniciarServer(){
        int contadoraux = 0;
        int contGrupos = 0;
        int cliPorGrupos = modelo.getNumCPG();
        try{
            //Creamos el servidor y le pasamos el puerto en el que escucha
            ss = new ServerSocket(portNumber);
            
            System.out.println("Servidor a la espera...");
            
            while(numeroConexiones < numTotalClientes){
                //Creamos un nuevo socket al que le damos esa conexión y un id numérico
                Grupo grupo = new Grupo(numGrupo, cliPorGrupos);
                vectorGrupos[contGrupos] = grupo;
                numGrupo++;
                
                while(contadoraux < cliPorGrupos){
                    //Mientras nos estén llegando conexiones, las aceptamos
                    sock = ss.accept();
                    grupo.nuevoCliente(sock, nombre);
                    //Añadimos las conexiones en el modelo para tener organizados los grupos
                    modelo.addConexion(grupo.getNuevaConexion(nombre), numfila, numColumna);
                    
                    contadoraux++;
                    if(numColumna == modelo.getNumCPG()-1){
                        numfila++;
                        numColumna = 0;
                    }else{
                        numColumna++;
                    }
                    //Incrementamos el contador de conexiones y el nombre del socket
                    numeroConexiones++;
                    nombre++;
                }
                contadoraux = 0;
                contGrupos++;
            }
            System.out.println("GRUPOS CREADOS CORRECTAMENTE.");
            for(int i = 0 ; i < vectorGrupos.length;i++){
                vectorGrupos[i].start();
            }
            modelo.EnviarNumVecinos();
            
        }catch (SocketTimeoutException ste){
            //Si el Timeout llega a 0, salta el error
            System.out.println("[ERROR] Tiempo agotado.Conexiones realizadas: " + numeroConexiones);
            
            
        }catch (Exception e){
            //Cada vez que termina una conexión lo mostramos por pantalla.
            numeroConexiones--;            
            System.out.println("[Excp] Conexión cerrada.");
        }
    }
    
    public void FinalizarConexiones(){
        try {
            sock.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
