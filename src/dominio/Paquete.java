/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package dominio;
import java.io.Serializable;

public class Paquete implements Serializable{

    // Identificador de version de la clase para serializacion
    private static final long serialVersionUID = 1L;
    
    private String identificador;
    private Cliente cliente;
    private String fecha;
    private String destinatario;
    private String direccion;
    private String departamento;
    private String zona;
    private int pesoGramos;
    private int precio;
    private String estado;

    public Paquete() {
        this.setIdentificador("");
        this.setCliente(null);
        this.setFecha("");
        this.setDestinatario("");
        this.setDireccion("");
        this.setDepartamento("");
        this.setZona("");
        this.setPesoGramos(0);
        this.setPrecio(0);
        this.setEstado("pendiente");
    }

    public Paquete(String unIdentificador, Cliente unCliente, String unaFecha,
            String unDestinatario, String unaDireccion, String unDepartamento,
            String unaZona, int unPesoGramos, int unPrecio) {

        this.setIdentificador(unIdentificador);
        this.setCliente(unCliente);
        this.setFecha(unaFecha);
        this.setDestinatario(unDestinatario);
        this.setDireccion(unaDireccion);
        this.setDepartamento(unDepartamento);
        this.setZona(unaZona);
        this.setPesoGramos(unPesoGramos);
        this.setPrecio(unPrecio);
        this.setEstado("pendiente");
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String unIdentificador) {
        identificador = unIdentificador;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente unCliente) {
        cliente = unCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String unaFecha) {
        fecha = unaFecha;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String unDestinatario) {
        destinatario = unDestinatario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String unaDireccion) {
        direccion = unaDireccion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String unDepartamento) {
        departamento = unDepartamento;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String unaZona) {
        zona = unaZona;
    }

    public int getPesoGramos() {
        return pesoGramos;
    }

    public void setPesoGramos(int unPesoGramos) {
        pesoGramos = unPesoGramos;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int unPrecio) {
        precio = unPrecio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String unEstado) {
        estado = unEstado;
    }

    @Override
    public String toString() {
        return this.getIdentificador() + " - Cliente: " + this.getCliente()
                + " - Destinatario: " + this.getDestinatario()
                + " - Departamento: " + this.getDepartamento()
                + " - Zona: " + this.getZona()
                + " - Peso: " + this.getPesoGramos() + " gramos"
                + " - Precio: $" + this.getPrecio()
                + " - Estado: " + this.getEstado();
    }
}