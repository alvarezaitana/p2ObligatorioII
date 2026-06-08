
/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package dominio;
import java.util.*;
import java.io.*;

public class Sistema implements Serializable{

    // Identificador de version de la clase para serializacion
    private static final long serialVersionUID = 1L;
    
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Funcionario> listaFuncionarios;
    private ArrayList<Tarifa> listaTarifas;
    private ArrayList<Paquete> listaPaquetes;
    private ArrayList<Envio> listaEnvios;
    private int proximoNumeroEnvio;
    private static final String ARCHIVO_TARIFAS = "Tarifas.txt";
    private static final String ARCHIVO_SISTEMA = "sistema.dat"; ////////////////////////////////////

    public Sistema() {
        listaClientes = new ArrayList<>();
        listaFuncionarios = new ArrayList<>();
        listaTarifas = new ArrayList<>();
        listaPaquetes = new ArrayList<>();
        listaEnvios = new ArrayList<>();
        proximoNumeroEnvio = 1;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }
    
    public ArrayList<Tarifa> getListaTarifas() {
        return listaTarifas;
    }
    public ArrayList<Paquete> getListaPaquetes() {
        return listaPaquetes;
    }
    public ArrayList<Envio> getListaEnvios() {
        return listaEnvios;
    }

    public int getProximoNumeroEnvio() {
        return proximoNumeroEnvio;
    }

    public void setProximoNumeroEnvio(int unProximoNumeroEnvio) {
        proximoNumeroEnvio = unProximoNumeroEnvio;
    }

    public boolean existeNombre(String unNombre) {
        boolean existe = false;

        for (int i = 0; i < this.getListaClientes().size(); i++) {
            Cliente cliente = this.getListaClientes().get(i);
            if (cliente.getNombre().equalsIgnoreCase(unNombre)) {
                existe = true;
            }
        }
        for (int i = 0; i < this.getListaFuncionarios().size(); i++) {
            Funcionario funcionario = this.getListaFuncionarios().get(i);
            if (funcionario.getNombre().equalsIgnoreCase(unNombre)) {
                existe = true;
            }
        }
        return existe;
    }

    public boolean existeNumeroFuncionario(int unNumeroFuncionario) {
        boolean existe = false;

        for (int i = 0; i < this.getListaFuncionarios().size(); i++) {
            Funcionario funcionario = this.getListaFuncionarios().get(i);
            if (funcionario.getNumeroFuncionario() == unNumeroFuncionario) {
                existe = true;
            }
        }
        return existe;
    }

    public boolean agregarCliente(Cliente unCliente) {
        boolean agregado = false;

        if (!this.existeNombre(unCliente.getNombre())) {
            this.getListaClientes().add(unCliente);
            agregado = true;
        }
        return agregado;
    }

    public boolean agregarFuncionario(Funcionario unFuncionario) {
        boolean agregado = false;

        if (!this.existeNombre(unFuncionario.getNombre()) && 
            !this.existeNumeroFuncionario(unFuncionario.getNumeroFuncionario())) {
                    this.getListaFuncionarios().add(unFuncionario);
                    agregado = true;
        }
        return agregado;
    }
    public void agregarTarifa(Tarifa unaTarifa) {
        this.getListaTarifas().add(unaTarifa);
    }

    public Tarifa buscarTarifaPorZona(String unaZona) {
        Tarifa tarifaEncontrada = null;

        for (int i = 0; i < this.getListaTarifas().size(); i++) {
            Tarifa tarifa = this.getListaTarifas().get(i);
            if (tarifa.getZona().equalsIgnoreCase(unaZona)) {
                tarifaEncontrada = tarifa;
            }
        }
        return tarifaEncontrada;
    }

    public int calcularPrecio(String unaZona, int pesoGramos) {
        int precio = 0;
        Tarifa tarifa = this.buscarTarifaPorZona(unaZona);

        if (tarifa != null) {
            precio = tarifa.calcularPrecio(pesoGramos);
        }
        return precio;
    }

    public void actualizarTarifas(double porcentaje) {
        for (int i = 0; i < this.getListaTarifas().size(); i++) {
            Tarifa tarifa = this.getListaTarifas().get(i);
            tarifa.actualizarPorcentaje(porcentaje);
        }
    }
    
    public boolean existePaquete(String unIdentificador) {
        boolean existe = false;

        for (int i = 0; i < this.getListaPaquetes().size(); i++) {
            Paquete paquete = this.getListaPaquetes().get(i);
            if (paquete.getIdentificador().equalsIgnoreCase(unIdentificador)) {
                existe = true;
            }
        }
        return existe;
    }

    public boolean agregarPaquete(Paquete unPaquete) {
        boolean agregado = false;

        if (!this.existePaquete(unPaquete.getIdentificador())) {
            this.getListaPaquetes().add(unPaquete);
            agregado = true;
        }
        return agregado;
    }

    public String darZonaDepartamento(String departamento) {
        String zona = "";

        if (departamento.equalsIgnoreCase("Artigas")
                || departamento.equalsIgnoreCase("Salto")
                || departamento.equalsIgnoreCase("Paysandu")
                || departamento.equalsIgnoreCase("Paysandú")
                || departamento.equalsIgnoreCase("Rivera")
                || departamento.equalsIgnoreCase("Tacuarembo")
                || departamento.equalsIgnoreCase("Tacuarembó")) {

            zona = "NORTE";
        } else {
            if (departamento.equalsIgnoreCase("Rio Negro")
                    || departamento.equalsIgnoreCase("Río Negro")
                    || departamento.equalsIgnoreCase("Soriano")
                    || departamento.equalsIgnoreCase("Colonia")
                    || departamento.equalsIgnoreCase("San Jose")
                    || departamento.equalsIgnoreCase("San José")) {

                zona = "OESTE";
            } else {
                if (departamento.equalsIgnoreCase("Cerro Largo")
                        || departamento.equalsIgnoreCase("Treinta y Tres")
                        || departamento.equalsIgnoreCase("Lavalleja")
                        || departamento.equalsIgnoreCase("Rocha")
                        || departamento.equalsIgnoreCase("Maldonado")) {

                    zona = "ESTE";
                } else {
                    if (departamento.equalsIgnoreCase("Durazno")
                            || departamento.equalsIgnoreCase("Flores")
                            || departamento.equalsIgnoreCase("Florida")
                            || departamento.equalsIgnoreCase("Canelones")
                            || departamento.equalsIgnoreCase("Montevideo")) {

                        zona = "SUR";
                    }
                }
            }
        }
        return zona;
    }
    
    public ArrayList<Paquete> darPaquetesPendientesPorZona(String unaZona) {
        ArrayList<Paquete> paquetes = new ArrayList<>();

        for (int i = 0; i < this.getListaPaquetes().size(); i++) {
            Paquete paquete = this.getListaPaquetes().get(i);
            if (paquete.getEstado().equalsIgnoreCase("pendiente")
                && paquete.getZona().equalsIgnoreCase(unaZona)) {
                        paquetes.add(paquete);
            }
        }

        return paquetes;
    }

    public Envio crearEnvio(String unaFecha, Funcionario unFuncionario, String unaZona, ArrayList<Paquete> paquetesSeleccionados) {
        Envio envio = new Envio(this.getProximoNumeroEnvio(), unaFecha, unFuncionario, unaZona);

        for (int i = 0; i < paquetesSeleccionados.size(); i++) {
            Paquete paquete = paquetesSeleccionados.get(i);
            envio.agregarPaquete(paquete);
            paquete.setEstado("enviado");
        }
        this.getListaEnvios().add(envio);
        this.setProximoNumeroEnvio(this.getProximoNumeroEnvio() + 1);
        return envio;
    }

    public boolean existeNombreEnOtro(String unNombre, Cliente clienteActual, Funcionario funcionarioActual) {
        boolean existe = false;
        int i = 0;

        while (i < this.getListaClientes().size() && !existe) {
            Cliente cliente = this.getListaClientes().get(i);

            if (cliente != clienteActual && cliente.getNombre().equalsIgnoreCase(unNombre)) {
                existe = true;
            }

            i = i + 1;
        }

        i = 0;
        while (i < this.getListaFuncionarios().size() && !existe) {
            Funcionario funcionario = this.getListaFuncionarios().get(i);

            if (funcionario != funcionarioActual && funcionario.getNombre().equalsIgnoreCase(unNombre)) {
                existe = true;
            }

            i = i + 1;
        }

        return existe;
    }

    public boolean existeNumeroEnOtroFuncionario(int unNumero, Funcionario funcionarioActual) {
        boolean existe = false;
        int i = 0;

        while (i < this.getListaFuncionarios().size() && !existe) {
            Funcionario funcionario = this.getListaFuncionarios().get(i);

            if (funcionario != funcionarioActual && funcionario.getNumeroFuncionario() == unNumero) {
                existe = true;
            }

            i = i + 1;
        }

        return existe;
    }

    public boolean modificarCliente(Cliente unCliente, String unNombre, String unCelular, String unCorreo) {
        boolean modificado = false;

        if (!this.existeNombreEnOtro(unNombre, unCliente, null)) {
            unCliente.setNombre(unNombre);
            unCliente.setCelular(unCelular);
            unCliente.setCorreo(unCorreo);
            modificado = true;
        }

        return modificado;
    }

    public boolean modificarFuncionario(Funcionario unFuncionario, String unNombre, String unCelular, int unNumero, int unAnio) {
        boolean modificado = false;

        if (!this.existeNombreEnOtro(unNombre, null, unFuncionario)) {
            if (!this.existeNumeroEnOtroFuncionario(unNumero, unFuncionario)) {
                unFuncionario.setNombre(unNombre);
                unFuncionario.setCelular(unCelular);
                unFuncionario.setNumeroFuncionario(unNumero);
                unFuncionario.setAnioIngreso(unAnio);
                modificado = true;
            }
        }

        return modificado;
    }
    public void cargarTarifasTxt() throws IOException {
        this.getListaTarifas().clear();
        Scanner entrada = new Scanner(new File(ARCHIVO_TARIFAS));

        while (entrada.hasNextLine()) {
            String linea = entrada.nextLine().trim();

            if (!linea.isEmpty()) {
                String[] partes = linea.split("#");
                String zona = partes[0].trim();
                String[] categorias = partes[1].split(",");

                int cat1 = Integer.parseInt(categorias[0].trim());
                int cat2 = Integer.parseInt(categorias[1].trim());
                int cat3 = Integer.parseInt(categorias[2].trim());
                int cat4 = Integer.parseInt(categorias[3].trim());

                Tarifa tarifa = new Tarifa(zona, cat1, cat2, cat3, cat4);
                this.agregarTarifa(tarifa);
            }
        }
        entrada.close();
    }

    public void grabarTarifasTxt() throws IOException {
        PrintWriter salida = new PrintWriter(new FileWriter(ARCHIVO_TARIFAS));

        for (int i = 0; i < this.getListaTarifas().size(); i = i + 1) {
            Tarifa tarifa = this.getListaTarifas().get(i);
            salida.println(tarifa.getZona() + "#"
                    + tarifa.getCat1() + ","
                    + tarifa.getCat2() + ","
                    + tarifa.getCat3() + ","
                    + tarifa.getCat4());
        }
        salida.close();
    }
    

    public void guardarSistema() {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO_SISTEMA));
            salida.writeObject(this);
            salida.close();
        } catch (IOException e) {
            // Si falla el guardado, el programa sigue funcionando
            // El error se puede ver en consola durante desarrollo
            System.out.println("Error al guardar el sistema: " + e.getMessage());
        }
    }

    public static Sistema cargarSistema() {
        Sistema sistema = null;
        File archivo = new File(ARCHIVO_SISTEMA);

        if (archivo.exists()) {
            try {
                ObjectInputStream entrada = new ObjectInputStream(
                    new FileInputStream(archivo));
                sistema = (Sistema) entrada.readObject();
                entrada.close();
            } catch (IOException | ClassNotFoundException e) {
                // Si el archivo está corrupto o la clase cambió,
                // arrancamos con un sistema nuevo
                System.out.println("No se pudo cargar el sistema anterior: " + e.getMessage());
                sistema = new Sistema();
            }
        } else {
            // Primera vez que se ejecuta: no existe sistema.dat
            sistema = new Sistema();
        }

        return sistema;
    }

    public void registrarTransaccion(String descripcion) {
    try {
        java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
        String linea = String.format("%02d/%02d/%02d %02d:%02d – %s",
            ahora.getDayOfMonth(),
            ahora.getMonthValue(),
            ahora.getYear() % 100,
            ahora.getHour(),
            ahora.getMinute(),
            descripcion);
        PrintWriter pw = new PrintWriter(new FileWriter("Transacciones.log", true));
        pw.println(linea);
        pw.close();
    } catch (IOException e) {
        System.out.println("Error al escribir log: " + e.getMessage());
    }
  }
    
}

    
