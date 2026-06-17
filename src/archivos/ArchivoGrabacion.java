/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package archivos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

public class ArchivoGrabacion {
    private Formatter salida;

    public ArchivoGrabacion(String nombreArchivo) throws IOException {
        salida = new Formatter(nombreArchivo);
    }

    public ArchivoGrabacion(String nombreArchivo, boolean extender) throws IOException {
        FileWriter archivo = new FileWriter(nombreArchivo, extender);
        salida = new Formatter(archivo);
    }

    public void grabarLinea(String linea) {
        salida.format("%s%n", linea);
    }

    public void cerrar() {
        salida.close();
    }
}

