
/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package dominio;
import java.util.*;
import java.io.*;

public class Sistema extends Observable implements Serializable {

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
            notificarObservers();
        }
        return agregado;
    }

    public boolean agregarFuncionario(Funcionario unFuncionario) {
        boolean agregado = false;

        if (!this.existeNombre(unFuncionario.getNombre()) && 
            !this.existeNumeroFuncionario(unFuncionario.getNumeroFuncionario())) {
                    this.getListaFuncionarios().add(unFuncionario);
                    agregado = true;
                    notificarObservers();
        }
        return agregado;
    }
    public void agregarTarifa(Tarifa unaTarifa) {
        this.getListaTarifas().add(unaTarifa);
        notificarObservers();
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
        notificarObservers();
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
            notificarObservers();
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
        notificarObservers();
        return envio;
    }
    
    public void registrarRecepcion(Envio unEnvio, ArrayList<Paquete> paquetesEntregados) {
        ArrayList<Paquete> copiaPaquetes = new ArrayList<Paquete>();

        for (int i = 0; i < unEnvio.getListaPaquetes().size(); i = i + 1) {
            copiaPaquetes.add(unEnvio.getListaPaquetes().get(i));
        }

        unEnvio.getListaPaquetesEntregados().clear();

        for (int i = 0; i < copiaPaquetes.size(); i = i + 1) {
            Paquete paquete = copiaPaquetes.get(i);

            if (paquetesEntregados.contains(paquete)) {
                paquete.setEstado("recibido");
                unEnvio.agregarPaqueteEntregado(paquete);
            } else {
                paquete.setEstado("pendiente");
                unEnvio.quitarPaquete(paquete);
            }
        }

        unEnvio.setRecibido(true);
        notificarObservers();
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
            notificarObservers();
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
                notificarObservers();
            }
        }

        return modificado;
    }
        public int contarPaquetesPorZonaYEstado(String unaZona, String unEstado) {
        int cantidad = 0;

        for (int i = 0; i < this.getListaPaquetes().size(); i = i + 1) {
            Paquete paquete = this.getListaPaquetes().get(i);

            if (paquete.getZona().equalsIgnoreCase(unaZona)
                    && paquete.getEstado().equalsIgnoreCase(unEstado)) {
                cantidad = cantidad + 1;
            }
        }

        return cantidad;
    }

    public int contarClientesDistintosPorZonaYEstado(String unaZona, String unEstado) {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        for (int i = 0; i < this.getListaPaquetes().size(); i = i + 1) {
            Paquete paquete = this.getListaPaquetes().get(i);

            if (paquete.getZona().equalsIgnoreCase(unaZona)
                    && paquete.getEstado().equalsIgnoreCase(unEstado)) {

                Cliente cliente = paquete.getCliente();

                if (!clientes.contains(cliente)) {
                    clientes.add(cliente);
                }
            }
        }

        return clientes.size();
    }

    public ArrayList<String> darDepartamentosConPaquetes(String unaZona, String unEstado) {
        ArrayList<String> departamentos = new ArrayList<String>();

        for (int i = 0; i < this.getListaPaquetes().size(); i = i + 1) {
            Paquete paquete = this.getListaPaquetes().get(i);

            if (paquete.getZona().equalsIgnoreCase(unaZona)
                    && paquete.getEstado().equalsIgnoreCase(unEstado)) {

                String departamento = paquete.getDepartamento();

                if (!departamentos.contains(departamento)) {
                    departamentos.add(departamento);
                }
            }
        }

        return departamentos;
    }

    public int[] darPaquetesPorCliente(Cliente unCliente) {
        int pendientes = 0;
        int enviados = 0;
        int recibidos = 0;

        for (int i = 0; i < this.getListaPaquetes().size(); i = i + 1) {
            Paquete paquete = this.getListaPaquetes().get(i);

            if (paquete.getCliente() == unCliente) {
                if (paquete.getEstado().equalsIgnoreCase("pendiente")) {
                    pendientes = pendientes + 1;
                } else {
                    if (paquete.getEstado().equalsIgnoreCase("enviado")) {
                        enviados = enviados + 1;
                    } else {
                        if (paquete.getEstado().equalsIgnoreCase("recibido")) {
                            recibidos = recibidos + 1;
                        }
                    }
                }
            }
        }

        int[] totales = {pendientes, enviados, recibidos};
        return totales;
    }
    
}

    
