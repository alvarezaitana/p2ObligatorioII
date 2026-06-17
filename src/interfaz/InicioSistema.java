/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package interfaz;

import dominio.*;
import archivos.ManejadorArchivos;
import javax.swing.JOptionPane;
import java.io.IOException;

public class InicioSistema extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(InicioSistema.class.getName());

    public InicioSistema() {
        initComponents();
        btnUltEjecucion.setSelected(true);
    }

    private void abrirMenuPrincipal(Sistema sistema) {
        MenuPrincipal menu = new MenuPrincipal(sistema);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
        this.dispose();
    }

    private void iniciarSistema() {
        Sistema sistema = null;

        try {
            if (btnUltEjecucion.isSelected()) {
                sistema = ManejadorArchivos.cargarSistema();
            } else {
                sistema = new Sistema();
            }

            ManejadorArchivos.cargarTarifasTxt(sistema);
            abrirMenuPrincipal(sistema);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoInicio = new javax.swing.ButtonGroup();
        btnUltEjecucion = new javax.swing.JRadioButton();
        btnSistNuevo = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Comenzar con ...");

        grupoInicio.add(btnUltEjecucion);
        btnUltEjecucion.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnUltEjecucion.setSelected(true);
        btnUltEjecucion.setText("Los datos de la última ejecución");
        btnUltEjecucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltEjecucionActionPerformed(evt);
            }
        });

        grupoInicio.add(btnSistNuevo);
        btnSistNuevo.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnSistNuevo.setText("Un Sistema nuevo");
        btnSistNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSistNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSistNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUltEjecucion))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(btnUltEjecucion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSistNuevo)
                .addContainerGap(133, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUltEjecucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltEjecucionActionPerformed
        iniciarSistema();
    }//GEN-LAST:event_btnUltEjecucionActionPerformed

    private void btnSistNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSistNuevoActionPerformed
        iniciarSistema();
    }//GEN-LAST:event_btnSistNuevoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new InicioSistema().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnSistNuevo;
    private javax.swing.JRadioButton btnUltEjecucion;
    private javax.swing.ButtonGroup grupoInicio;
    // End of variables declaration//GEN-END:variables
}
