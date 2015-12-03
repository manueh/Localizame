/*
 * Modelo de la aplicación. Este guardará todas las conexiones entrantes y la 
 * posición de cada uno de ellos.
 */
package Model;

import Controller.NuevaConexion;
import java.util.ArrayList;

 
public class Model {
    private NuevaConexion[][] conexiones; //Matriz donde se guardan los hilos
    private int numClientes;
    private int numGrupos;
    private int numCPG;
    
    public Model(int _numClientes, int _numGrupos){
        numClientes = _numClientes;
        numGrupos = _numGrupos;
        numCPG = numClientes / numGrupos;
        try{
            conexiones = new NuevaConexion[numGrupos][numCPG];
        }catch (Exception e){}
}

    public int getNumCPG() {
        return numCPG;
    }

    public void setNumCPG(int numCPG) {
        this.numCPG = numCPG;
    }
    
    public void setNumClientes(int _numClientes){
        numClientes = _numClientes;
    }
    
    public void setNumGrupos(int _numGrupos){
        numGrupos = _numGrupos;
    }
    
    public int getNumClientes(){
        return numClientes;
    }
    
    public int getNumGrupos(){
        return numGrupos;
    }
    
    public ArrayList getGrupoTrabajo(int grupo){
        ArrayList grupoTrabajo = new ArrayList();
        
            for(int i = 0; i < numCPG; i++){
                grupoTrabajo.add(conexiones[grupo][i]);
            }
        
        return grupoTrabajo;
    }
    
    /*public void mostrarArrayGrupos(){
        System.out.println("Array de conexiones: \n\n");
        for(int i = 0; i < numGrupos; i++){
            for(int j = 0; j < numCPG; j++){
                System.out.println("Grupo: "+i);
                System.out.println("Número de cliente: "+j);
                System.out.println("Identificador de la conexión: "+conexiones[i][j].getNombre());
                System.out.println("Identificador del proceso conectado: "+conexiones[i][j].getIDHilo()+"\n");
            }
        }
    }*/
    
    public void addConexion(NuevaConexion nueva, int fila, int columna){
        conexiones[fila][columna] = nueva;                
    }
    
    public void Empezar(){
        for(int i = 0; i < numGrupos; i++){
            for(int j = 0; j < numCPG; j++){
                conexiones[i][j].Empezar();
            }
        }
    }
    
    public void EnviarPaquete(){
        int aux;
        Paquete paqueteaux;
        
        for(int i = 0; i < numGrupos; i++){
            for(int j = 0; j < numCPG; j++){
                aux = j;
                paqueteaux = conexiones[i][j].getP();
                for(int k = 0; k < numCPG; k++){
                    if(aux != k){
                        conexiones[i][k].EnviarPaquete(paqueteaux);
                    }
                }
            }
        }
        EsperarRecibidos();
    }
    
    public void EnviarNumVecinos(){
        for(int i = 0; i < numGrupos; i++){
            for(int j = 0; j < numCPG; j++){
                conexiones[i][j].EnviarNumVecinos(numCPG-1);
            }
        }
    }
    
    public void EsperarRecibidos(){
        System.out.println("ENTRO EN ESPERAR RECIBIDOS");
        int contador = 0;
        int aux = 0;
        boolean[] cerrados;
        
        cerrados = new boolean[numGrupos];
        for(int i = 0; i < numGrupos; i++){
            for(int j = 0; j < numCPG; j++){
                aux = j;
                if(conexiones[i][j].EsperarRecibidos()){
                    contador++;
                }
                if(contador == numCPG){
                    cerrados[i] = true;
                    for(int o = 0; o < numCPG; o++){
                        conexiones[i][o].CerrarConexion();
                    }
                }
            }
            
            contador = 0;
        }
        
        for (int i = 0; i < cerrados.length; i++){
            if(!cerrados[i]){
                for(int j = 0; j < numCPG; j++){
                    aux = j;
                    if(conexiones[i][j].EsperarRecibidos()){
                        contador++;
                    }
                }
                if(contador == numCPG){
                    cerrados[i] = true;
                    for(int o = 0; o < numCPG; o++){
                        conexiones[i][o].CerrarConexion();
                    }
                }
                contador = 0;
            }
        }
        System.out.println("Salgo de ESPERAR RECIBIDOS");
    }
}
