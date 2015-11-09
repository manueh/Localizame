/*
 * Modelo de la aplicación. Este guardará todas las conexiones entrantes y la 
 * posición de cada uno de ellos.
 */
package Model;

import java.util.ArrayList;

 
public class Model {
    private Hilo[][] conexiones; //array donde se guardan los hilos
    private int numClientes;
    private int numGrupos;
    private int numCPG;
    
    public Model(int _numClientes, int _numGrupos){ //se le da el tamanyo al array
        numClientes = _numClientes;
        numGrupos = _numGrupos;
        numCPG = numClientes / numGrupos;
        try{
            conexiones = new Hilo[numGrupos][numClientes];
        }catch (Exception e){}
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
        
            for(int i = 0; i <= numCPG; i++){
                grupoTrabajo.add(conexiones[grupo][i]);
            }
        
        return grupoTrabajo;
    }
    
    public void addHilo(Hilo h){ //servidor o controlador le manda al modelo el numero de hilos
        for(int i = 0; i < numGrupos; i++){
            for(int j = 0; j < numCPG; j++){
                if(conexiones[i][j] == null)
                    conexiones[i][j]= h;
            }
        }
        mostrarArrayGrupos();
    }
    
    public void mostrarArrayGrupos(){
        System.out.println("Array de conexiones: \n\n");
        for(int i = 0; i < numGrupos; i++){
            for(int j = 0; j < numCPG; j++){
                System.out.println("Grupo: "+i);
                System.out.println("Número de cliente: "+j);
                System.out.println("Identificador del cliente: "+conexiones[i][j].getID()+"\n");
            }
        }
    }
}
