/*
 * Modelo de la aplicación. Este guardará todas las conexiones entrantes y la 
 * posición de cada uno de ellos.
 */
package Model;

import java.util.ArrayList;

 
public class Model {
    private Hilo[][] conexiones;
    private int numClientes;
    private int numGrupos;
    
    public Model(int _numClientes, int _numGrupos){
        numClientes = _numClientes;
        numGrupos = _numGrupos;
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
        
            for(int i = 0; i < numGrupos;i++){
                grupoTrabajo.add(conexiones[i][grupo]);
            }
        
        return grupoTrabajo;
    }
    
    public void addHilo(Hilo h){
        
    }
}
