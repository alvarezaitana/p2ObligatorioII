/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package dominio;
import java.io.Serializable;
import java.util.ArrayList;

public class Envio implements Serializable{

    // Identificador de version de la clase para serializacion
    private static final long serialVersionUID = 1L;
    
    private int numero;
    private String fecha;
    private Funcionario funcionario;
    private String zona;
    private ArrayList<Paquete> listaPaquetes;
    private boolean recibido;
    private ArrayList<Paquete> listaPaquetesEntregados;

    public Envio() {
        this.setNumero(0);
        this.setFecha("");
        this.setFuncionario(null);
        this.setZona("");
        listaPaquetes = new ArrayList<>();
        this.setRecibido(false);
        listaPaquetesEntregados = new ArrayList<>();
    }

    public Envio(int unNumero, String unaFecha, Funcionario unFuncionario, String unaZona) {
        this.setNumero(unNumero);
        this.setFecha(unaFecha);
        this.setFuncionario(unFuncionario);
        this.setZona(unaZona);
        listaPaquetes = new ArrayList<>();
        this.setRecibido(false);
        listaPaquetesEntregados = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int unNumero) {
        numero = unNumero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String unaFecha) {
        fecha = unaFecha;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario unFuncionario) {
        funcionario = unFuncionario;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String unaZona) {
        zona = unaZona;
    }

    public ArrayList<Paquete> getListaPaquetes() {
        return listaPaquetes;
    }

    public boolean getRecibido() {
        return recibido;
    }

    public void setRecibido(boolean estaRecibido) {
        recibido = estaRecibido;
    }

    public ArrayList<Paquete> getListaPaquetesEntregados() {
        return listaPaquetesEntregados;
    }

    public void agregarPaquete(Paquete unPaquete) {
        this.getListaPaquetes().add(unPaquete);
    }

    public void quitarPaquete(Paquete unPaquete) {
        this.getListaPaquetes().remove(unPaquete);
    }

    public void agregarPaqueteEntregado(Paquete unPaquete) {
        this.getListaPaquetesEntregados().add(unPaquete);
    }

    public int darPesoTotalGramos() {
        int total = 0;

        for (int i = 0; i < this.getListaPaquetes().size(); i++) {
            Paquete paquete = this.getListaPaquetes().get(i);
            total = total + paquete.getPesoGramos();
        }
        return total;
    }

    public int darMontoTotal() {
        int total = 0;

        for (int i = 0; i < this.getListaPaquetes().size(); i++) {
            Paquete paquete = this.getListaPaquetes().get(i);
            total = total + paquete.getPrecio();
        }
        return total;
    }

    public int darCantidadPaquetes() {
        return this.getListaPaquetes().size();
    }

    @Override
    public String toString() {
        return "Envio " + this.getNumero()
                + " - Fecha: " + this.getFecha()
                + " - Funcionario: " + this.getFuncionario()
                + " - Zona: " + this.getZona()
                + " - Cantidad paquetes: " + this.darCantidadPaquetes()
                + " - Total: $" + this.darMontoTotal();
    }
}