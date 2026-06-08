/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */

package dominio;
import java.io.Serializable;

public class Tarifa implements Serializable{

    // Identificador de version de la clase para serializacion
    private static final long serialVersionUID = 1L;
    
    private String zona;
    private int cat1;
    private int cat2;
    private int cat3;
    private int cat4;

    public Tarifa() {
        this.setZona("");
        this.setCat1(0);
        this.setCat2(0);
        this.setCat3(0);
        this.setCat4(0);
    }

    public Tarifa(String unaZona, int unaCat1, int unaCat2, int unaCat3, int unaCat4) {
        this.setZona(unaZona);
        this.setCat1(unaCat1);
        this.setCat2(unaCat2);
        this.setCat3(unaCat3);
        this.setCat4(unaCat4);
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String unaZona) {
        zona = unaZona;
    }

    public int getCat1() {
        return cat1;
    }

    public void setCat1(int unaCat1) {
        cat1 = unaCat1;
    }

    public int getCat2() {
        return cat2;
    }

    public void setCat2(int unaCat2) {
        cat2 = unaCat2;
    }

    public int getCat3() {
        return cat3;
    }

    public void setCat3(int unaCat3) {
        cat3 = unaCat3;
    }

    public int getCat4() {
        return cat4;
    }

    public void setCat4(int unaCat4) {
        cat4 = unaCat4;
    }

    public int calcularPrecio(int pesoGramos) {
        int precio = 0;

        if (pesoGramos < 1000) {
            precio = this.getCat1();
        } else {
            if (pesoGramos < 5000) {
                precio = this.getCat2();
            } else {
                if (pesoGramos < 10000) {
                    precio = this.getCat3();
                } else {
                    precio = this.getCat4();
                }
            }
        }

        return precio;
    }

    public void actualizarPorcentaje(double porcentaje) {
        this.setCat1((int) Math.round(this.getCat1() + this.getCat1() * porcentaje / 100));
        this.setCat2((int) Math.round(this.getCat2() + this.getCat2() * porcentaje / 100));
        this.setCat3((int) Math.round(this.getCat3() + this.getCat3() * porcentaje / 100));
        this.setCat4((int) Math.round(this.getCat4() + this.getCat4() * porcentaje / 100));
    }

    @Override
    public String toString() {
        return this.getZona() + ": " + this.getCat1() + ", " + this.getCat2() + ", "
                + this.getCat3() + ", " + this.getCat4();
    }
}