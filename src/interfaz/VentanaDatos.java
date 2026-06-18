/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package interfaz;
import dominio.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import archivos.ManejadorArchivos;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VentanaDatos extends javax.swing.JFrame implements Observer {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaDatos.class.getName());
    private Sistema sistema;
    private ArrayList<Cliente> clientesMostrados;
    private ArrayList<Funcionario> funcionariosMostrados;

        public VentanaDatos(Sistema unSistema) {
        initComponents();
        sistema = unSistema;
        sistema.agregarObserver(this);
        cargarDatosIniciales();
        configurarEventos();
        
    }
    private void cargarDatosIniciales() {
        cargarListaClientes();
        cargarListaFuncionarios();
        cargarTablaTarifas();
    }

    private void configurarEventos() {
        btnAgregarClie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarCliente();
            }
        });

        btnModificarClie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarCliente();
            }
        });

        btnLimpiarClie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarCliente();
            }
        });

        lstClientes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                seleccionarCliente(evt);
            }
        });

        btnAgFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarFuncionario();
            }
        });

        btnModFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarFuncionario();
            }
        });

        btnLimFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarFuncionario();
            }
        });

        lstFun.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                seleccionarFuncionario(evt);
            }
        });

        btnActualizarTar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarTarifas();
            }
        });
    }
    private void cargarListaClientes() {
        clientesMostrados = new ArrayList<Cliente>();

        for (int i = 0; i < sistema.getListaClientes().size(); i = i + 1) {
            clientesMostrados.add(sistema.getListaClientes().get(i));
        }

        Collections.sort(clientesMostrados, new Comparator<Cliente>() {
            public int compare(Cliente cliente1, Cliente cliente2) {
                return cliente1.getNombre().compareToIgnoreCase(cliente2.getNombre());
            }
        });

        String[] datos = new String[clientesMostrados.size()];

        for (int i = 0; i < clientesMostrados.size(); i = i + 1) {
            datos[i] = clientesMostrados.get(i).getNombre();
        }

        lstClientes.setListData(datos);
    }
    private void cargarListaFuncionarios() {
        funcionariosMostrados = new ArrayList<Funcionario>();

        for (int i = 0; i < sistema.getListaFuncionarios().size(); i = i + 1) {
            funcionariosMostrados.add(sistema.getListaFuncionarios().get(i));
        }

        Collections.sort(funcionariosMostrados, new Comparator<Funcionario>() {
            public int compare(Funcionario funcionario1, Funcionario funcionario2) {
                return funcionario2.getAnioIngreso() - funcionario1.getAnioIngreso();
            }
        });

        String[] datos = new String[funcionariosMostrados.size()];

        for (int i = 0; i < funcionariosMostrados.size(); i = i + 1) {
            datos[i] = funcionariosMostrados.get(i).getNombre();
        }

        lstFun.setListData(datos);
    }

    private void cargarTablaTarifas() {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Zona");
        modelo.addColumn("< 1 kg");
        modelo.addColumn("1 a <5 kg");
        modelo.addColumn("5 a <10 kg");
        modelo.addColumn(">=10kg");

        for (int i = 0; i < sistema.getListaTarifas().size(); i = i + 1) {
            Tarifa tarifa = sistema.getListaTarifas().get(i);

            Object[] fila = {
                tarifa.getZona(),
                tarifa.getCat1(),
                tarifa.getCat2(),
                tarifa.getCat3(),
                tarifa.getCat4()
            };

            modelo.addRow(fila);
        }

        tablaTarifas.setModel(modelo);
    }

    private boolean hayVacio(String dato1, String dato2, String dato3) {
        boolean hay = false;

        if (dato1.isEmpty() || dato2.isEmpty() || dato3.isEmpty()) {
            hay = true;
        }

        return hay;
    }

    private boolean hayVacio(String dato1, String dato2, String dato3, String dato4) {
        boolean hay = false;

        if (dato1.isEmpty() || dato2.isEmpty() || dato3.isEmpty() || dato4.isEmpty()) {
            hay = true;
        }

        return hay;
    }
    private boolean soloDigitos(String texto) {
        boolean ok = true;
        int i = 0;

        if (texto.isEmpty()) {
            ok = false;
        }

        while (i < texto.length() && ok) {
            if (!Character.isDigit(texto.charAt(i))) {
                ok = false;
            }
            i = i + 1;
        }

        return ok;
    }

    private boolean anioCuatroDigitos(String textoAnio) {
        boolean ok = false;

        if (soloDigitos(textoAnio) && textoAnio.length() == 4) {
            ok = true;
        }

        return ok;
    }

    private void agregarCliente() {
        String nombre = txtNomClie.getText().trim();
        String celular = txtCelClie.getText().trim();
        String correo = txtCorreoClie.getText().trim();

        if (hayVacio(nombre, celular, correo)) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los datos del cliente.");
        } else if (!soloDigitos(celular)) {
            JOptionPane.showMessageDialog(this, "El celular debe contener solo números.");
        } else {
            Cliente cliente = new Cliente(nombre, celular, correo);
            boolean agregado = sistema.agregarCliente(cliente);

            if (agregado) {
                ManejadorArchivos.registrarTransaccion("Ingreso de cliente " + nombre);  // REGISTRAR EL LOG
                JOptionPane.showMessageDialog(this, "Cliente agregado correctamente.");
                cargarListaClientes();
                limpiarCliente();
            } else {
                JOptionPane.showMessageDialog(this, "Ya existe un cliente o funcionario con ese nombre.");
            }
        }
    }
    private void seleccionarCliente(javax.swing.event.ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {
            int posicion = lstClientes.getSelectedIndex();

            if (posicion >= 0) {
                Cliente cliente = clientesMostrados.get(posicion);

                txtNomClie.setText(cliente.getNombre());
                txtCelClie.setText(cliente.getCelular());
                txtCorreoClie.setText(cliente.getCorreo());
            }
        }
    }
    private void modificarCliente() {
        int posicion = lstClientes.getSelectedIndex();

        if (posicion < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente de la lista.");
        } else {
            Cliente cliente = clientesMostrados.get(posicion);

            String nombre = txtNomClie.getText().trim();
            String celular = txtCelClie.getText().trim();
            String correo = txtCorreoClie.getText().trim();

           if (hayVacio(nombre, celular, correo)) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los datos del cliente.");
            } else if (!soloDigitos(celular)) {
                JOptionPane.showMessageDialog(this, "El celular debe contener solo números.");
            } else {
                boolean modificado = sistema.modificarCliente(cliente, nombre, celular, correo);

                if (modificado) {
                    ManejadorArchivos.registrarTransaccion("Modificación de cliente " + nombre);  // REGISTRAR LOG
                    JOptionPane.showMessageDialog(this, "Cliente modificado correctamente.");
                    cargarListaClientes();
                    limpiarCliente();
                } else {
                    JOptionPane.showMessageDialog(this, "Ya existe otro cliente o funcionario con ese nombre.");
                }
            }
        }
    }

    private void limpiarCliente() {
        txtNomClie.setText("");
        txtCelClie.setText("");
        txtCorreoClie.setText("");
        lstClientes.clearSelection();
    }
    private void agregarFuncionario() {
        String nombre = txtNomFun.getText().trim();
        String celular = txtCelFun.getText().trim();
        String textoNumero = txtNumFun.getText().trim();
        String textoAnio = txtAnoIng.getText().trim();

        if (hayVacio(nombre, celular, textoNumero, textoAnio)) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los datos del funcionario.");
        } else if (!soloDigitos(celular)) {
            JOptionPane.showMessageDialog(this, "El celular debe contener solo números.");
        } else if (!soloDigitos(textoNumero)) {
            JOptionPane.showMessageDialog(this, "El número de funcionario debe contener solo números.");
        } else if (!anioCuatroDigitos(textoAnio)) {
            JOptionPane.showMessageDialog(this, "El año de ingreso debe tener 4 cifras.");
        } else {
            try {
                int numero = Integer.parseInt(textoNumero);
                int anio = Integer.parseInt(textoAnio);

                Funcionario funcionario = new Funcionario(nombre, celular, numero, anio);
                boolean agregado = sistema.agregarFuncionario(funcionario);

                if (agregado) {
                    ManejadorArchivos.registrarTransaccion("Ingreso de funcionario " + nombre);  // REGISTRAR EL LOG
                    JOptionPane.showMessageDialog(this, "Funcionario agregado correctamente.");
                    cargarListaFuncionarios();
                    limpiarFuncionario();
                } else {
                    JOptionPane.showMessageDialog(this, "Ya existe un cliente/funcionario con ese nombre o un funcionario con ese número.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El número de funcionario y el año de ingreso deben ser numéricos.");
            }
        }
    }
    private void seleccionarFuncionario(javax.swing.event.ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {
            int posicion = lstFun.getSelectedIndex();

            if (posicion >= 0) {
                Funcionario funcionario = funcionariosMostrados.get(posicion);

                txtNomFun.setText(funcionario.getNombre());
                txtCelFun.setText(funcionario.getCelular());
                txtNumFun.setText("" + funcionario.getNumeroFuncionario());
                txtAnoIng.setText("" + funcionario.getAnioIngreso());
            }
        }
    }
    private void modificarFuncionario() {
        int posicion = lstFun.getSelectedIndex();

        if (posicion < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un funcionario de la lista.");
        } else {
            Funcionario funcionario = funcionariosMostrados.get(posicion);

            String nombre = txtNomFun.getText().trim();
            String celular = txtCelFun.getText().trim();
            String textoNumero = txtNumFun.getText().trim();
            String textoAnio = txtAnoIng.getText().trim();

           if (hayVacio(nombre, celular, textoNumero, textoAnio)) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los datos del funcionario.");
            } else if (!soloDigitos(celular)) {
                JOptionPane.showMessageDialog(this, "El celular debe contener solo números.");
            } else if (!soloDigitos(textoNumero)) {
                JOptionPane.showMessageDialog(this, "El número de funcionario debe contener solo números.");
            } else if (!anioCuatroDigitos(textoAnio)) {
                JOptionPane.showMessageDialog(this, "El año de ingreso debe tener 4 cifras.");
            } else {
                try {
                    int numero = Integer.parseInt(textoNumero);
                    int anio = Integer.parseInt(textoAnio);

                    boolean modificado = sistema.modificarFuncionario(funcionario, nombre, celular, numero, anio);

                    if (modificado) {
                        ManejadorArchivos.registrarTransaccion("Modificación de funcionario " + nombre);  // REGISTRAR LOG
                        JOptionPane.showMessageDialog(this, "Funcionario modificado correctamente.");
                        cargarListaFuncionarios();
                        limpiarFuncionario();
                    } else {
                        JOptionPane.showMessageDialog(this, "Ya existe otro cliente/funcionario con ese nombre o funcionario con ese número.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "El número de funcionario y el año de ingreso deben ser numéricos.");
                }
            }
        }
    }

    private void limpiarFuncionario() {
        txtNomFun.setText("");
        txtCelFun.setText("");
        txtNumFun.setText("");
        txtAnoIng.setText("");
        lstFun.clearSelection();
    }
    private void actualizarTarifas() {
        String textoPorcentaje = txtPorcentaje.getText().trim();

        if (textoPorcentaje.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un porcentaje.");
        } else {
            try {
                double porcentaje = Double.parseDouble(textoPorcentaje);

                sistema.actualizarTarifas(porcentaje);
                ManejadorArchivos.grabarTarifasTxt(sistema);
                ManejadorArchivos.registrarTransaccion("Actualización de tarifas con " + porcentaje + "%");  // REGISTRAR EL LOG
                cargarTablaTarifas();

                txtPorcentaje.setText("");

                JOptionPane.showMessageDialog(this, "Tarifas actualizadas correctamente.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El porcentaje debe ser numérico.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el archivo de tarifas.");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDatos = new javax.swing.JTabbedPane();
        pnlClientes = new javax.swing.JPanel();
        lblClieReg = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstClientes = new javax.swing.JList();
        lstNom = new javax.swing.JLabel();
        lstCel = new javax.swing.JLabel();
        lstCorreo = new javax.swing.JLabel();
        txtNomClie = new javax.swing.JTextField();
        txtCelClie = new javax.swing.JTextField();
        txtCorreoClie = new javax.swing.JTextField();
        btnAgregarClie = new javax.swing.JButton();
        btnModificarClie = new javax.swing.JButton();
        btnLimpiarClie = new javax.swing.JButton();
        lblDatosClie = new javax.swing.JLabel();
        pnlFuncionarios = new javax.swing.JPanel();
        lblFunReg = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstFun = new javax.swing.JList<>();
        lblDatosFun = new javax.swing.JLabel();
        lblNomFun = new javax.swing.JLabel();
        lblCelFun = new javax.swing.JLabel();
        lblNumFun = new javax.swing.JLabel();
        lblAnoIng = new javax.swing.JLabel();
        txtNomFun = new javax.swing.JTextField();
        txtCelFun = new javax.swing.JTextField();
        txtNumFun = new javax.swing.JTextField();
        txtAnoIng = new javax.swing.JTextField();
        btnAgFun = new javax.swing.JButton();
        btnModFun = new javax.swing.JButton();
        btnLimFun = new javax.swing.JButton();
        pnlTarifas = new javax.swing.JPanel();
        lblTarActuales = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaTarifas = new javax.swing.JTable();
        lblPorcentaje = new javax.swing.JLabel();
        txtPorcentaje = new javax.swing.JTextField();
        signo = new javax.swing.JLabel();
        btnActualizarTar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos");

        panelDatos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        lblClieReg.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblClieReg.setText("Clientes Registrados");

        lstClientes.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstClientes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstClientesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstClientes);

        lstNom.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lstNom.setText("Nombre:");

        lstCel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lstCel.setText("Celular:");

        lstCorreo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lstCorreo.setText("Correo:");

        txtCorreoClie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoClieActionPerformed(evt);
            }
        });

        btnAgregarClie.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregarClie.setText("Agregar");
        btnAgregarClie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClieActionPerformed(evt);
            }
        });

        btnModificarClie.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificarClie.setText("Modificar");

        btnLimpiarClie.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimpiarClie.setText("Limpiar");

        lblDatosClie.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDatosClie.setText("Datos del Cliente");

        javax.swing.GroupLayout pnlClientesLayout = new javax.swing.GroupLayout(pnlClientes);
        pnlClientes.setLayout(pnlClientesLayout);
        pnlClientesLayout.setHorizontalGroup(
            pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClieReg)
                    .addGroup(pnlClientesLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlClientesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                        .addComponent(btnModificarClie)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiarClie, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(pnlClientesLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(lblDatosClie, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlClientesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lstNom, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lstCel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lstCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomClie, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCelClie, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorreoClie, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlClientesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregarClie, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))))
        );
        pnlClientesLayout.setVerticalGroup(
            pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClientesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClieReg)
                    .addComponent(lblDatosClie))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlClientesLayout.createSequentialGroup()
                        .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomClie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lstNom))
                        .addGap(12, 12, 12)
                        .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lstCel)
                            .addComponent(txtCelClie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lstCorreo)
                            .addComponent(txtCorreoClie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarClie))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarClie)
                    .addComponent(btnLimpiarClie))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        panelDatos.addTab("Clientes", pnlClientes);

        lblFunReg.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFunReg.setText("Funcionarios registrados");

        lstFun.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstFun);

        lblDatosFun.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDatosFun.setText("Datos del funcionario");

        lblNomFun.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNomFun.setText("Nombre:");

        lblCelFun.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCelFun.setText("Celular:");

        lblNumFun.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNumFun.setText("Nº de funcionario:");

        lblAnoIng.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAnoIng.setText("Año de ingreso:");

        txtCelFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelFunActionPerformed(evt);
            }
        });

        btnAgFun.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgFun.setText("Agregar");

        btnModFun.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModFun.setText("Modificar");

        btnLimFun.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimFun.setText("Limpiar");

        javax.swing.GroupLayout pnlFuncionariosLayout = new javax.swing.GroupLayout(pnlFuncionarios);
        pnlFuncionarios.setLayout(pnlFuncionariosLayout);
        pnlFuncionariosLayout.setHorizontalGroup(
            pnlFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFuncionariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFunReg)
                    .addGroup(pnlFuncionariosLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(pnlFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFuncionariosLayout.createSequentialGroup()
                        .addComponent(lblDatosFun)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlFuncionariosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnModFun)
                        .addGap(50, 50, 50)
                        .addComponent(btnLimFun, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFuncionariosLayout.createSequentialGroup()
                        .addGroup(pnlFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlFuncionariosLayout.createSequentialGroup()
                                .addComponent(lblAnoIng)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAnoIng, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlFuncionariosLayout.createSequentialGroup()
                                .addGap(0, 2, Short.MAX_VALUE)
                                .addGroup(pnlFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnAgFun, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblCelFun)
                                        .addGroup(pnlFuncionariosLayout.createSequentialGroup()
                                            .addGroup(pnlFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblNumFun)
                                                .addComponent(lblNomFun))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(pnlFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtNomFun)
                                                .addComponent(txtCelFun)
                                                .addComponent(txtNumFun, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(73, 73, 73)))
                .addContainerGap())
        );
        pnlFuncionariosLayout.setVerticalGroup(
            pnlFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFuncionariosLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFunReg)
                    .addComponent(lblDatosFun))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFuncionariosLayout.createSequentialGroup()
                        .addGroup(pnlFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNomFun)
                            .addComponent(txtNomFun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCelFun)
                            .addComponent(txtCelFun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumFun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumFun))
                        .addGap(11, 11, 11)
                        .addGroup(pnlFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAnoIng)
                            .addComponent(txtAnoIng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAgFun)
                .addGap(45, 45, 45)
                .addGroup(pnlFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimFun)
                    .addComponent(btnModFun))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        panelDatos.addTab("Funcionarios", pnlFuncionarios);

        lblTarActuales.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTarActuales.setText("Tarifas actuales");

        tablaTarifas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Zona", " < 1 kg ", "1 a <5 kg ", " 5 a <10 kg ", ">=10kg"
            }
        ));
        jScrollPane3.setViewportView(tablaTarifas);

        lblPorcentaje.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPorcentaje.setText("Porcentaje de actualizacion:");

        signo.setText("%");

        btnActualizarTar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizarTar.setText("Actualizar tarifas");

        javax.swing.GroupLayout pnlTarifasLayout = new javax.swing.GroupLayout(pnlTarifas);
        pnlTarifas.setLayout(pnlTarifasLayout);
        pnlTarifasLayout.setHorizontalGroup(
            pnlTarifasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTarifasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTarifasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                    .addGroup(pnlTarifasLayout.createSequentialGroup()
                        .addGroup(pnlTarifasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTarifasLayout.createSequentialGroup()
                                .addComponent(lblPorcentaje)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(signo))
                            .addComponent(lblTarActuales, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTarifasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnActualizarTar)
                .addGap(104, 104, 104))
        );
        pnlTarifasLayout.setVerticalGroup(
            pnlTarifasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTarifasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTarActuales)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(pnlTarifasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPorcentaje)
                    .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(signo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizarTar)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        panelDatos.addTab("Tarifas", pnlTarifas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDatos)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCorreoClieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoClieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoClieActionPerformed

    private void txtCelFunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelFunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelFunActionPerformed

    private void btnAgregarClieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarClieActionPerformed

    private void lstClientesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstClientesValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_lstClientesValueChanged



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarTar;
    private javax.swing.JButton btnAgFun;
    private javax.swing.JButton btnAgregarClie;
    private javax.swing.JButton btnLimFun;
    private javax.swing.JButton btnLimpiarClie;
    private javax.swing.JButton btnModFun;
    private javax.swing.JButton btnModificarClie;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAnoIng;
    private javax.swing.JLabel lblCelFun;
    private javax.swing.JLabel lblClieReg;
    private javax.swing.JLabel lblDatosClie;
    private javax.swing.JLabel lblDatosFun;
    private javax.swing.JLabel lblFunReg;
    private javax.swing.JLabel lblNomFun;
    private javax.swing.JLabel lblNumFun;
    private javax.swing.JLabel lblPorcentaje;
    private javax.swing.JLabel lblTarActuales;
    private javax.swing.JLabel lstCel;
    private javax.swing.JList lstClientes;
    private javax.swing.JLabel lstCorreo;
    private javax.swing.JList<String> lstFun;
    private javax.swing.JLabel lstNom;
    private javax.swing.JTabbedPane panelDatos;
    private javax.swing.JPanel pnlClientes;
    private javax.swing.JPanel pnlFuncionarios;
    private javax.swing.JPanel pnlTarifas;
    private javax.swing.JLabel signo;
    private javax.swing.JTable tablaTarifas;
    private javax.swing.JTextField txtAnoIng;
    private javax.swing.JTextField txtCelClie;
    private javax.swing.JTextField txtCelFun;
    private javax.swing.JTextField txtCorreoClie;
    private javax.swing.JTextField txtNomClie;
    private javax.swing.JTextField txtNomFun;
    private javax.swing.JTextField txtNumFun;
    private javax.swing.JTextField txtPorcentaje;
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void actualizar() {
        cargarListaClientes();
        cargarListaFuncionarios();
        cargarTablaTarifas();
    }
}
