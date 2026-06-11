/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package interfaz;
import dominio.*;
import java.util.ArrayList;
import java.io.*;
import javax.swing.*;

public class VentanaReportes extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaReportes.class.getName());

    private Sistema sistema;
    
    private ArrayList<Cliente> clientesMostrados;
    
    public VentanaReportes(Sistema unSistema) {
        initComponents();
        sistema = unSistema;
        cargarDatosIniciales();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnelReportes = new javax.swing.JTabbedPane();
        pnlCliente = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstClientes = new javax.swing.JList<>();
        lblPendientes = new javax.swing.JLabel();
        lblEnviados = new javax.swing.JLabel();
        lblRecibidos = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblPendientesRes = new javax.swing.JLabel();
        lblEnviadosRes = new javax.swing.JLabel();
        lblRecibidosRes = new javax.swing.JLabel();
        lblTotalRes = new javax.swing.JLabel();
        pnlLogTransacciones = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        btnBorrarLog = new javax.swing.JButton();
        pnlEstados = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lstClientes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstClientes);

        lblPendientes.setText("Pendientes:");

        lblEnviados.setText("Enviados:");

        lblRecibidos.setText("Recibidos:");

        lblTotal.setText("Total:");

        lblPendientesRes.setText("-");

        lblEnviadosRes.setText("-");

        lblRecibidosRes.setText("-");

        lblTotalRes.setText("-");

        javax.swing.GroupLayout pnlClienteLayout = new javax.swing.GroupLayout(pnlCliente);
        pnlCliente.setLayout(pnlClienteLayout);
        pnlClienteLayout.setHorizontalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlClienteLayout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotalRes))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlClienteLayout.createSequentialGroup()
                        .addComponent(lblRecibidos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRecibidosRes))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlClienteLayout.createSequentialGroup()
                        .addComponent(lblEnviados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblEnviadosRes))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlClienteLayout.createSequentialGroup()
                        .addComponent(lblPendientes)
                        .addGap(57, 57, 57)
                        .addComponent(lblPendientesRes)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        pnlClienteLayout.setVerticalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlClienteLayout.createSequentialGroup()
                        .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPendientes)
                            .addComponent(lblPendientesRes))
                        .addGap(18, 18, 18)
                        .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEnviados)
                            .addComponent(lblEnviadosRes))
                        .addGap(18, 18, 18)
                        .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRecibidos)
                            .addComponent(lblRecibidosRes))
                        .addGap(18, 18, 18)
                        .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotal)
                            .addComponent(lblTotalRes)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        pnelReportes.addTab("Consulta por Cliente", pnlCliente);

        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane1.setViewportView(txtLog);

        btnBorrarLog.setText("Borrar Log");
        btnBorrarLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarLogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLogTransaccionesLayout = new javax.swing.GroupLayout(pnlLogTransacciones);
        pnlLogTransacciones.setLayout(pnlLogTransaccionesLayout);
        pnlLogTransaccionesLayout.setHorizontalGroup(
            pnlLogTransaccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogTransaccionesLayout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addComponent(btnBorrarLog)
                .addContainerGap(242, Short.MAX_VALUE))
            .addGroup(pnlLogTransaccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlLogTransaccionesLayout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlLogTransaccionesLayout.setVerticalGroup(
            pnlLogTransaccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLogTransaccionesLayout.createSequentialGroup()
                .addContainerGap(296, Short.MAX_VALUE)
                .addComponent(btnBorrarLog)
                .addGap(40, 40, 40))
            .addGroup(pnlLogTransaccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlLogTransaccionesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(89, Short.MAX_VALUE)))
        );

        pnelReportes.addTab("Log de Transacciones", pnlLogTransacciones);

        javax.swing.GroupLayout pnlEstadosLayout = new javax.swing.GroupLayout(pnlEstados);
        pnlEstados.setLayout(pnlEstadosLayout);
        pnlEstadosLayout.setHorizontalGroup(
            pnlEstadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );
        pnlEstadosLayout.setVerticalGroup(
            pnlEstadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 359, Short.MAX_VALUE)
        );

        pnelReportes.addTab("Paquetes por estado", pnlEstados);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnelReportes, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnelReportes, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBorrarLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarLogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarLogActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new VentanaReportes(new Sistema()).setVisible(true));
    }
    private void cargarDatosIniciales() {
    cargarListaClientes();
    cargarLog();
}

// =====================================================================
// CONSULTA POR CLIENTE
// =====================================================================
private void cargarListaClientes() {
    clientesMostrados = new ArrayList<Cliente>();

    for (int i = 0; i < sistema.getListaClientes().size(); i = i + 1) {
        clientesMostrados.add(sistema.getListaClientes().get(i));
    }

    String[] datos = new String[clientesMostrados.size()];
    for (int i = 0; i < clientesMostrados.size(); i = i + 1) {
        datos[i] = clientesMostrados.get(i).getNombre();
    }

    lstClientes.setListData(datos);

    lstClientes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
        public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            if (!evt.getValueIsAdjusting()) {
                mostrarPaquetesPorCliente();
            }
        }
    });
}

private void mostrarPaquetesPorCliente() {
    int posicion = lstClientes.getSelectedIndex();

    if (posicion < 0) {
        return;
    }

    Cliente cliente = clientesMostrados.get(posicion);
    int[] totales = sistema.darPaquetesPorCliente(cliente);

    lblPendientesRes.setText("" + totales[0]);
    lblEnviadosRes.setText("" + totales[1]);
    lblRecibidosRes.setText("" + totales[2]);
    lblTotalRes.setText("" + (totales[0] + totales[1] + totales[2]));
}

// =====================================================================
// LOG DE TRANSACCIONES
// =====================================================================
private void cargarLog() {
    try {
        java.io.File archivo = new java.io.File("Transacciones.log");

        if (!archivo.exists()) {
            txtLog.setText("El archivo de log está vacío.");
            return;
        }

        StringBuilder contenido = new StringBuilder();
        java.util.Scanner scanner = new java.util.Scanner(archivo);

        while (scanner.hasNextLine()) {
            contenido.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        if (contenido.length() == 0) {
            txtLog.setText("El archivo de log está vacío.");
        } else {
            txtLog.setText(contenido.toString());
            txtLog.setCaretPosition(0);
        }

    } catch (java.io.IOException e) {
        txtLog.setText("Error al leer el archivo de log.");
    }

    btnBorrarLog.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            borrarLog();
        }
    });
}

private void borrarLog() {
    int opcion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea borrar todo el contenido del log?",
            "Confirmar borrado",
            JOptionPane.YES_NO_OPTION);

    if (opcion != JOptionPane.YES_OPTION) {
        return;
    }

    try {
        java.io.PrintWriter pw = new java.io.PrintWriter(
                new java.io.FileWriter("Transacciones.log", false));
        pw.close();
        txtLog.setText("El archivo de log está vacío.");
        JOptionPane.showMessageDialog(this, "Log borrado correctamente.");
    } catch (java.io.IOException e) {
        JOptionPane.showMessageDialog(this, "Error al borrar el log.");
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrarLog;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEnviados;
    private javax.swing.JLabel lblEnviadosRes;
    private javax.swing.JLabel lblPendientes;
    private javax.swing.JLabel lblPendientesRes;
    private javax.swing.JLabel lblRecibidos;
    private javax.swing.JLabel lblRecibidosRes;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalRes;
    private javax.swing.JList<String> lstClientes;
    private javax.swing.JTabbedPane pnelReportes;
    private javax.swing.JPanel pnlCliente;
    private javax.swing.JPanel pnlEstados;
    private javax.swing.JPanel pnlLogTransacciones;
    private javax.swing.JTextArea txtLog;
    // End of variables declaration//GEN-END:variables
}
