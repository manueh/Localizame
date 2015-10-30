package Controller;

import Model.Model;
import java.net.*;

/**
 *
 * @author Grupo 2_4
 */
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
    private int numeroConexiones = 0;
    private final Model modelo;
    
    /**
     * Constructor con parámetros
     * @param totalClients  define el número total de clientes que van a conectarse
     * @param _modelo       instancia del modelo para tener acceso a los datos
     */
    public Servidor(int totalClients, Model _modelo){
       modelo = _modelo;
       iniciarServer();
    }
    
    private void iniciarServer(){
        try{
            //Creamos el servidor y le pasamos el puerto en el que escucha
            ss = new ServerSocket(portNumber);
            System.out.println("Servidor a la espera...");
            //Combrobamos que no tengamos más conexiones de las que tenemos que tener
            while(numeroConexiones <= modelo.getNumClientes()){
                //Mientras nos estén llegando conexiones, las aceptamos
                sock = ss.accept();
                //Creamos un nuevo socket al que le damos esa conexión y un id numérico
                NuevaConexion conexion = new NuevaConexion(sock, nombre);
                conexion.start();
                modelo.addHilo(conexion.crearHilo());
                numeroConexiones++;
                nombre++;
            }
            
        }catch (SocketTimeoutException ste){
            //Si el Timeout llega a 0, salta el error
            System.out.println("Tiempo agotado.Conexiones realizadas: "+numeroConexiones);
        }catch (Exception e){
            
            numeroConexiones--;
            //Cada vez que termina una conexión lo mostramos por pantalla.
            System.out.println("Conexión cerrada.");
        }
    }
    
}
