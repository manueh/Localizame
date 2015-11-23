/*
 * Modelo de la aplicación. Este guardará todas las conexiones entrantes y la 
 * posición de cada uno de ellos.
 */
package Model;

import Controller.NuevaConexion;
import java.util.ArrayList;

 
public class Model {
    private NuevaConexion[][] conexiones; //array donde se guardan los hilos
    private int numClientes;
    private int numGrupos;
    private int numCPG;
    
    public Model(int _numClientes, int _numGrupos){ //se le da el tamanyo al array
        numClientes = _numClientes;
        numGrupos = _numGrupos;
        numCPG = numClientes / numGrupos;
        try{
            conexiones = new NuevaConexion[numGrupos][numCPG];
            System.out.println("Numero de grupos " + numGrupos + "Numero MAX: " + numCPG);
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
    
    public void mostrarArrayGrupos(){
        System.out.println("Array de conexiones: \n\n");
        for(int i = 0; i < numGrupos; i++){
            for(int j = 0; j < numCPG; j++){
                System.out.println("Grupo: "+i);
                System.out.println("Número de cliente: "+j);
                System.out.println("Identificador del cliente: "+conexiones[i][j].getNombre()+"\n");
            }
        }
    }
    
    public void addConexion(NuevaConexion nueva, int fila, int columna){
        System.out.println("FILA: "+fila);
        System.out.println("COLUMNA: "+columna);
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
                for(int k = 0; k < numCPG; k++){
                    if(aux != k){
                        
                        paqueteaux = conexiones[i][k].getP();
                        conexiones[i][k].EnviarPaquete(paqueteaux);
                    }
                }
            }
        }
    }
    
    public void EnviarNumVecinos(){
        for(int i = 0; i < numGrupos; i++){
            for(int j = 0; j < numCPG; j++){
                conexiones[i][j].EnviarNumVecinos(numCPG);
            }
        }
    }
}
