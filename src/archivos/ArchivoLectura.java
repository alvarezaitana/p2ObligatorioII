/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package archivos;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class ArchivoLectura {
    private Scanner entrada;
    private String linea;

    public ArchivoLectura(String nombreArchivo) throws IOException {
        entrada = new Scanner(Paths.get(nombreArchivo));
    }

    public boolean hayMasLineas() {
        boolean hay = false;
        linea = null;

        if (entrada.hasNextLine()) {
            linea = entrada.nextLine();
            hay = true;
        }

        return hay;
    }

    public String linea() {
        return linea;
    }

    public void cerrar() {
        entrada.close();
    }
}
