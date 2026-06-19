/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package interfaz;
import dominio.*;
import archivos.ManejadorArchivos;
import java.util.ArrayList;
import java.io.*;
import javax.swing.*;

public class VentanaReportes extends javax.swing.JFrame implements Observer{
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaReportes.class.getName());

    private Sistema sistema;
    
    private ArrayList<Cliente> clientesMostrados;
    
    public VentanaReportes(Sistema unSistema) {
        initComponents();
        sistema = unSistema;
        sistema.agregarObserver(this);
        cargarDatosIniciales();
        addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent evt) {
            sistema.quitarObserver(VentanaReportes.this);
        }
    });
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
        jScrollPane3 = new javax.swing.JScrollPane();
        tblEstados = new javax.swing.JTable();
        pnlMapa = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnelReportes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        lstClientes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstClientes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstClientesValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstClientes);

        lblPendientes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPendientes.setText("Pendientes:");

        lblEnviados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEnviados.setText("Enviados:");

        lblRecibidos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRecibidos.setText("Recibidos:");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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
                .addGap(43, 43, 43)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEnviados)
                    .addComponent(lblPendientes)
                    .addComponent(lblRecibidos)
                    .addComponent(lblTotal))
                .addGap(74, 74, 74)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPendientesRes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRecibidosRes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEnviadosRes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalRes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(267, 267, 267))
        );
        pnlClienteLayout.setVerticalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlClienteLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlClienteLayout.createSequentialGroup()
                                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblPendientes)
                                    .addComponent(lblPendientesRes))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblEnviadosRes))
                            .addComponent(lblEnviados))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRecibidos)
                            .addComponent(lblRecibidosRes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalRes)
                            .addComponent(lblTotal)))
                    .addGroup(pnlClienteLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(226, Short.MAX_VALUE))
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
                .addGap(394, 394, 394)
                .addComponent(btnBorrarLog)
                .addContainerGap(411, Short.MAX_VALUE))
            .addGroup(pnlLogTransaccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlLogTransaccionesLayout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlLogTransaccionesLayout.setVerticalGroup(
            pnlLogTransaccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLogTransaccionesLayout.createSequentialGroup()
                .addContainerGap(381, Short.MAX_VALUE)
                .addComponent(btnBorrarLog)
                .addGap(59, 59, 59))
            .addGroup(pnlLogTransaccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlLogTransaccionesLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(117, Short.MAX_VALUE)))
        );

        pnelReportes.addTab("Log de Transacciones", pnlLogTransacciones);

        tblEstados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblEstados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblEstados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEstadosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblEstados);

        pnlMapa.setMinimumSize(new java.awt.Dimension(300, 350));
        pnlMapa.setPreferredSize(new java.awt.Dimension(300, 400));

        javax.swing.GroupLayout pnlMapaLayout = new javax.swing.GroupLayout(pnlMapa);
        pnlMapa.setLayout(pnlMapaLayout);
        pnlMapaLayout.setHorizontalGroup(
            pnlMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
        );
        pnlMapaLayout.setVerticalGroup(
            pnlMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlEstadosLayout = new javax.swing.GroupLayout(pnlEstados);
        pnlEstados.setLayout(pnlEstadosLayout);
        pnlEstadosLayout.setHorizontalGroup(
            pnlEstadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEstadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(pnlMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );
        pnlEstadosLayout.setVerticalGroup(
            pnlEstadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEstadosLayout.createSequentialGroup()
                .addGroup(pnlEstadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEstadosLayout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEstadosLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(pnlMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pnelReportes.addTab("Paquetes por estado", pnlEstados);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnelReportes)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnelReportes, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBorrarLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarLogActionPerformed
        borrarLog();
    }//GEN-LAST:event_btnBorrarLogActionPerformed

    private void lstClientesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstClientesValueChanged
        if (!evt.getValueIsAdjusting()) {
            mostrarPaquetesPorCliente();
        }
    }//GEN-LAST:event_lstClientesValueChanged

    private void tblEstadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEstadosMouseClicked
        clickCeldaEstados(evt);
    }//GEN-LAST:event_tblEstadosMouseClicked

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
    cargarTablaEstados();
    configurarMapa();
}

// =====================================================================
// CONSULTA POR CLIENTE
// =====================================================================
    private void cargarListaClientes() {
        clientesMostrados = new ArrayList<Cliente>();

        for (int i = 0; i < sistema.getListaClientes().size(); i = i + 1) {
            clientesMostrados.add(sistema.getListaClientes().get(i));
        }

        java.util.Collections.sort(clientesMostrados, new java.util.Comparator<Cliente>() {
            public int compare(Cliente cliente1, Cliente cliente2) {
                return cliente1.getNombre().compareToIgnoreCase(cliente2.getNombre());
            }
        });

        String[] datos = new String[clientesMostrados.size()];
        for (int i = 0; i < clientesMostrados.size(); i = i + 1) {
            datos[i] = clientesMostrados.get(i).getNombre();
        }

        lstClientes.setListData(datos);
        limpiarDatosCliente();
    }

    private void mostrarPaquetesPorCliente() {
        int posicion = lstClientes.getSelectedIndex();

        if (posicion < 0) {
            limpiarDatosCliente();
        } else {
            Cliente cliente = clientesMostrados.get(posicion);
            int[] totales = sistema.darPaquetesPorCliente(cliente);

            lblPendientesRes.setText("" + totales[0]);
            lblEnviadosRes.setText("" + totales[1]);
            lblRecibidosRes.setText("" + totales[2]);
            lblTotalRes.setText("" + (totales[0] + totales[1] + totales[2]));
        }
    }
    private void limpiarDatosCliente() {
        lblPendientesRes.setText("-");
        lblEnviadosRes.setText("-");
        lblRecibidosRes.setText("-");
        lblTotalRes.setText("-");
    }

// =====================================================================
// LOG DE TRANSACCIONES
// =====================================================================
    private void cargarLog() {
        txtLog.setText(ManejadorArchivos.leerLog());
        txtLog.setCaretPosition(0);
    }

    private void borrarLog() {
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea borrar todo el contenido del log?",
                "Confirmar borrado",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            try {
                ManejadorArchivos.borrarLog();
                cargarLog();
                JOptionPane.showMessageDialog(this, "Log borrado correctamente.");
            } catch (java.io.IOException e) {
                JOptionPane.showMessageDialog(this, "Error al borrar el log.");
            }
        }
    }

    // =====================================================================
    // PAQUETES POR ESTADO
    // =====================================================================
    private void cargarTablaEstados() {
        String[] columnas = {"Zona", "Pendiente", "Enviado", "Recibido", "Total"};
        String[] zonas = {"NORTE", "OESTE", "ESTE", "SUR"};

        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (int i = 0; i < zonas.length; i = i + 1) {
            String zona = zonas[i];
            int pendientes = sistema.contarPaquetesPorZonaYEstado(zona, "pendiente");
            int enviados = sistema.contarPaquetesPorZonaYEstado(zona, "enviado");
            int recibidos = sistema.contarPaquetesPorZonaYEstado(zona, "recibido");
            int total = pendientes + enviados + recibidos;

            modelo.addRow(new Object[]{zona, pendientes, enviados, recibidos, total});
        }

        tblEstados.setModel(modelo);
    }

    private void clickCeldaEstados(java.awt.event.MouseEvent evt) {
        int fila = tblEstados.getSelectedRow();
        int columna = tblEstados.getSelectedColumn();

        if (fila < 0 || columna <= 0 || columna == 4) {
            return;
        }

        String zona = (String) tblEstados.getValueAt(fila, 0);
        String[] estados = {"pendiente", "enviado", "recibido"};
        String estado = estados[columna - 1];

        int cantClientes = sistema.contarClientesDistintosPorZonaYEstado(zona, estado);
        ArrayList<String> departamentos = sistema.darDepartamentosConPaquetes(zona, estado);

        String textoDeps = "";
        if (departamentos.isEmpty()) {
            textoDeps = "Ninguno";
        } else {
            for (int i = 0; i < departamentos.size(); i = i + 1) {
                if (i > 0) {
                    textoDeps = textoDeps + ", ";
                }
                textoDeps = textoDeps + departamentos.get(i);
            }
        }

        JOptionPane.showMessageDialog(this,
                "Zona: " + zona + " — Estado: " + estado.toUpperCase() + "\n\n"
                + "Clientes distintos: " + cantClientes + "\n"
                + "Departamentos: " + textoDeps,
                "Detalle de celda",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void configurarMapa() {
        pnlMapa.removeAll();
        pnlMapa.setLayout(null);

        java.net.URL imgUrl = getClass().getResource("/recursos/mapa_uruguay.png");
        if (imgUrl != null) {
            javax.swing.ImageIcon icono = new javax.swing.ImageIcon(imgUrl);
            java.awt.Image imgEscalada = icono.getImage()
                    .getScaledInstance(300, 400, java.awt.Image.SCALE_SMOOTH);
            JLabel lblFondo = new JLabel(new javax.swing.ImageIcon(imgEscalada));
            lblFondo.setBounds(0, 0, 300, 400);
            pnlMapa.add(lblFondo);
        }

        agregarEtiquetaZona("NORTE", 100, 50, pnlMapa);
        agregarEtiquetaZona("OESTE", 40, 220, pnlMapa);
        agregarEtiquetaZona("ESTE", 190, 220, pnlMapa);
        agregarEtiquetaZona("SUR", 110, 290, pnlMapa);

        pnlMapa.revalidate();
        pnlMapa.repaint();
    }

private void agregarEtiquetaZona(String zona, int x, int y, javax.swing.JPanel panel) {
        int pendientes = sistema.contarPaquetesPorZonaYEstado(zona, "pendiente");
        int enviados = sistema.contarPaquetesPorZonaYEstado(zona, "enviado");
        int recibidos = sistema.contarPaquetesPorZonaYEstado(zona, "recibido");

        String tooltip = "<html><b>" + zona + "</b><br>"
                + "Pendientes: " + pendientes + "<br>"
                + "Enviados: " + enviados + "<br>"
                + "Recibidos: " + recibidos + "</html>";

        JLabel lbl = new JLabel(zona);
        lbl.setBounds(x, y, 80, 25);
        lbl.setOpaque(true);
        lbl.setBackground(new java.awt.Color(255, 255, 180));
        lbl.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.DARK_GRAY));
        lbl.setHorizontalAlignment(JLabel.CENTER);
        lbl.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
        lbl.setToolTipText(tooltip);

        javax.swing.ToolTipManager.sharedInstance().setInitialDelay(0);

        panel.add(lbl);
        panel.setComponentZOrder(lbl, 0);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrarLog;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
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
    private javax.swing.JPanel pnlMapa;
    private javax.swing.JTable tblEstados;
    private javax.swing.JTextArea txtLog;
    // End of variables declaration//GEN-END:variables
    @Override
    public void actualizar() {
        cargarListaClientes();
        cargarTablaEstados();
        configurarMapa();
        cargarLog();
    }
}
