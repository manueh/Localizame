/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.ActionListener;

/**
 *
 * @author Jorge
 */
public class Initial extends javax.swing.JPanel {

    /**
     * Creates new form Initial
     */
    public Initial() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLNumClients = new javax.swing.JLabel();
        jTFNumClients = new javax.swing.JTextField();
        jBstart = new javax.swing.JButton();
        jLnumGrupos = new javax.swing.JLabel();
        jTFnumGrupos = new javax.swing.JTextField();
        jLNumCiclos = new javax.swing.JLabel();
        jTNumCiclos = new javax.swing.JTextField();

        jLNumClients.setText("Introduce el número de clientes:");

        jTFNumClients.setText("100");
        jTFNumClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNumClientsActionPerformed(evt);
            }
        });

        jBstart.setText("Iniciar servidor");
        jBstart.setActionCommand("comenzar");
        jBstart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBstartActionPerformed(evt);
            }
        });

        jLnumGrupos.setText("Introduce el número de grupos:");

        jTFnumGrupos.setText("10");
        jTFnumGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFnumGruposActionPerformed(evt);
            }
        });

        jLNumCiclos.setText("Introduce el número de ciclos:");

        jTNumCiclos.setText("3");
        jTNumCiclos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNumCiclosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBstart, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLNumClients)
                            .addComponent(jLnumGrupos)
                            .addComponent(jLNumCiclos))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFNumClients, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                            .addComponent(jTFnumGrupos)
                            .addComponent(jTNumCiclos))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNumClients)
                    .addComponent(jTFNumClients, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLnumGrupos)
                    .addComponent(jTFnumGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNumCiclos)
                    .addComponent(jTNumCiclos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jBstart, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTFNumClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNumClientsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNumClientsActionPerformed

    private void jBstartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBstartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBstartActionPerformed

    private void jTFnumGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFnumGruposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFnumGruposActionPerformed

    private void jTNumCiclosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNumCiclosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTNumCiclosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBstart;
    private javax.swing.JLabel jLNumCiclos;
    private javax.swing.JLabel jLNumClients;
    private javax.swing.JLabel jLnumGrupos;
    private javax.swing.JTextField jTFNumClients;
    private javax.swing.JTextField jTFnumGrupos;
    private javax.swing.JTextField jTNumCiclos;
    // End of variables declaration//GEN-END:variables

    public void setActionListener(ActionListener al){
        jBstart.addActionListener(al);
    }
    
    public int getNumClientes(){
        return Integer.parseInt(jTFNumClients.getText());
    }
    
    public int getNumGrupos(){
        return Integer.parseInt(jTFnumGrupos.getText());
    }
    
    public int getNumCiclos(){
        return Integer.parseInt(jTNumCiclos.getText());
    }
}
