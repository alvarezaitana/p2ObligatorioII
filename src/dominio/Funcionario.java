/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package dominio;
import java.io.Serializable; 

public class Funcionario implements Serializable{
    
    // Identificador de version de la clase para serializacion
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String celular;
    private int numeroFuncionario;
    private int anioIngreso;

    public Funcionario() {
        this.setNombre("");
        this.setCelular("");
        this.setNumeroFuncionario(0);
        this.setAnioIngreso(0);
    }

    public Funcionario(String unNombre, String unCelular, int unNumeroFuncionario, int unAnioIngreso) {
        this.setNombre(unNombre);
        this.setCelular(unCelular);
        this.setNumeroFuncionario(unNumeroFuncionario);
        this.setAnioIngreso(unAnioIngreso);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String unNombre) {
        nombre = unNombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String unCelular) {
        celular = unCelular;
    }

    public int getNumeroFuncionario() {
        return numeroFuncionario;
    }

    public void setNumeroFuncionario(int unNumeroFuncionario) {
        numeroFuncionario = unNumeroFuncionario;
    }

    public int getAnioIngreso() {
        return anioIngreso;
    }

    public void setAnioIngreso(int unAnioIngreso) {
        anioIngreso = unAnioIngreso;
    }

    @Override
    public String toString() {
        return this.getNumeroFuncionario() + " - " + this.getNombre();
    }
}