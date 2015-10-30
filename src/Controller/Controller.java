/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Model;
import View.PrincipalWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jorge
 */
public class Controller implements ActionListener{
    private final PrincipalWindow view;
    private Model model;
    private Servidor server;
    private int numGrupos;
    private int numClientes;
    private int numCiclos;
    
    
    public Controller (PrincipalWindow _view){
        view = _view;
        view.setActionListener((ActionListener)this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String s = ae.getActionCommand();
        
        if(s.equals("comenzar")){
            try{
                numClientes = view.getNumClientes();
                numGrupos = view.getNumGrupos();
                numCiclos = view.getNumCiclos();
                
                view.swapPanel("waiting");
                
                if(comprobarNumero(numClientes, numGrupos)){
                    model = new Model(numClientes, numGrupos);
                    this.iniciar(numClientes, model);
                }
            }catch(Exception e){
                System.out.println("ERROR EN EL CAMBIO DE VENTANA");
            }
            
            
        }
    }
    
    private void iniciar(int valor, Model model){
        server = new Servidor(valor, model);
    }
    
    /**
     * Funcion para comprobar que los grupos pueden ser del mismo número de clientes
     * @param totalClients
     * @return 
     */
    private boolean comprobarNumero(int totalClients, int numGrupos){
        boolean correcto = false;
        if(totalClients%numGrupos == 0){
            correcto = true;
        }else{
            System.out.println("Número de clientes insuficiente.");
        }
        return correcto;
    }
    
}
