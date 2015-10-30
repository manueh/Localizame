/*
 * Ventana principal de la aplicación
 */
package View;

import java.awt.CardLayout;
import java.awt.event.ActionListener;

public class PrincipalWindow extends javax.swing.JFrame {

    /**
     * Creates new form PrincipalWindow
     */
    
    private final Initial initial;
    private final Esperando waiting;
    private final CardLayout cl;
    
    public PrincipalWindow() {
        /**
         * Declaramos los paneles que vamos a usar
         */
        initial = new Initial();
        waiting = new Esperando();
        
        /**
         * Cambiamos el Layout para trabajar con distintos paneles.
         */
        cl = new CardLayout();
        
        initComponents();
        /**
         * Mostramos el panel inicial del programa
         */
        this.setLayout(cl);
        add(initial, "initial");
        add(waiting, "waiting");
        cl.show(this.getContentPane(), "initial");
        
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    /**
     * Función para establecer los listeners del botón
     * @param al 
     */
    public void setActionListener (ActionListener al){
        initial.setActionListener(al);
    }
    /**
     * Función para cambiar los paneles que tenemos que mostrar
     * @param name Nombre del panel a mostrar
     */
    public void swapPanel(String name){
        cl.show(this.getContentPane(), name);
    }
    /**
     * Función para obtener el valor del TextField
     * @return 
     */
    public int getNumClientes(){
        return initial.getNumClientes();
    }
    
    public int getNumGrupos(){
        return initial.getNumGrupos();
    }
    
    public int getNumCiclos(){
        return initial.getNumCiclos();
    }
}
