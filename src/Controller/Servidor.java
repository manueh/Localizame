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
    private NuevaConexion[] vectorConexiones;
        
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
       vectorConexiones = new NuevaConexion[totalClients];
       iniciarServer();
    }
    
    private void iniciarServer(){
        try{
            //Creamos el servidor y le pasamos el puerto en el que escucha
            ss = new ServerSocket(portNumber);
            System.out.println("Servidor a la espera...");
            //Combrobamos que no tengamos más conexiones de las que tenemos que tener
            while(numeroConexiones < modelo.getNumClientes()){
                //Mientras nos estén llegando conexiones, las aceptamos
                sock = ss.accept();
                //Creamos un nuevo socket al que le damos esa conexión y un id numérico
                NuevaConexion conexion = new NuevaConexion(sock, nombre);
                conexion.start();
                vectorConexiones[numeroConexiones] = conexion;
                numeroConexiones++;
                nombre++;
            }
            System.out.println("TODAS LAS CONEXIONES CREADAS CORRECTAMENTE");
            
            vectorConexiones.notifyAll();
            
            for(int i = 0; i <= numeroConexiones; i++){
                //vectorConexiones[i].Empezar();
                //vectorConexiones[i].RecepcionHilo();
            }
        }catch (SocketTimeoutException ste){
            //Si el Timeout llega a 0, salta el error
            System.out.println("[ERROR] Tiempo agotado.Conexiones realizadas: " + numeroConexiones);
        }catch (Exception e){
            //Cada vez que termina una conexión lo mostramos por pantalla.
            numeroConexiones--;            
            System.out.println("[Excp] Conexión cerrada.");
        }
    }
    
}
