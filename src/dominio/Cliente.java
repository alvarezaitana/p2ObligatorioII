/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */

package dominio;
import java.io.Serializable;

public class Cliente implements Serializable {
    
    private String nombre;
    private String celular;
    private String correo;

    public Cliente() {
        this.setNombre("");
        this.setCelular("");
        this.setCorreo("");
    }

    public Cliente(String unNombre, String unCelular, String unCorreo) {
        this.setNombre(unNombre);
        this.setCelular(unCelular);
        this.setCorreo(unCorreo);
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String unCorreo) {
        correo = unCorreo;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
}