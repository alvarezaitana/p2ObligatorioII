/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */

package archivos;

import dominio.*;
import java.io.*;
import java.util.Scanner;
import java.net.URISyntaxException;

public class ManejadorArchivos {

    private static final String ARCHIVO_TARIFAS = "Tarifas.txt";
    private static final String ARCHIVO_SISTEMA = "sistema.dat";
    private static final String ARCHIVO_LOG = "Transacciones.log";

    private static File obtenerArchivo(String nombreArchivo) {
        File archivo = null;

        try {
            File ubicacion = new File(ManejadorArchivos.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI());

            File carpeta;

            if (ubicacion.isFile()) {
                carpeta = ubicacion.getParentFile();
            } else {
                carpeta = ubicacion;

                if (carpeta.getName().equals("classes")) {
                    carpeta = carpeta.getParentFile();
                }

                if (carpeta.getName().equals("build")) {
                    carpeta = carpeta.getParentFile();
                }
            }

            archivo = new File(carpeta, nombreArchivo);

        } catch (URISyntaxException e) {
            archivo = new File(nombreArchivo);
        }

        return archivo;
    }

    public static void cargarTarifasTxt(Sistema sistema) throws IOException {
        sistema.getListaTarifas().clear();

        Scanner entrada = new Scanner(obtenerArchivo(ARCHIVO_TARIFAS));

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
                sistema.agregarTarifa(tarifa);
            }
        }

        entrada.close();
    }

    public static void grabarTarifasTxt(Sistema sistema) throws IOException {
        PrintWriter salida = new PrintWriter(new FileWriter(obtenerArchivo(ARCHIVO_TARIFAS), false));

        for (int i = 0; i < sistema.getListaTarifas().size(); i = i + 1) {
            Tarifa tarifa = sistema.getListaTarifas().get(i);

            salida.println(tarifa.getZona() + "#"
                    + tarifa.getCat1() + ","
                    + tarifa.getCat2() + ","
                    + tarifa.getCat3() + ","
                    + tarifa.getCat4());
        }

        salida.close();
    }

    public static void guardarSistema(Sistema sistema) {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(
                    new FileOutputStream(obtenerArchivo(ARCHIVO_SISTEMA)));

            salida.writeObject(sistema);
            salida.close();

        } catch (IOException e) {
            System.out.println("Error al guardar el sistema: " + e.getMessage());
        }
    }

    public static Sistema cargarSistema() {
        Sistema sistema;
        File archivo = obtenerArchivo(ARCHIVO_SISTEMA);

        if (archivo.exists()) {
            try {
                ObjectInputStream entrada = new ObjectInputStream(
                        new FileInputStream(archivo));

                sistema = (Sistema) entrada.readObject();
                entrada.close();

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("No se pudo cargar el sistema anterior: " + e.getMessage());
                sistema = new Sistema();
            }
        } else {
            sistema = new Sistema();
        }

        return sistema;
    }

    public static void registrarTransaccion(String descripcion) {
        try {
            java.time.LocalDateTime ahora = java.time.LocalDateTime.now();

            String linea = String.format("%02d/%02d/%02d %02d:%02d – %s",
                    ahora.getDayOfMonth(),
                    ahora.getMonthValue(),
                    ahora.getYear() % 100,
                    ahora.getHour(),
                    ahora.getMinute(),
                    descripcion);

            PrintWriter salida = new PrintWriter(new FileWriter(obtenerArchivo(ARCHIVO_LOG), true));
            salida.println(linea);
            salida.close();

        } catch (IOException e) {
            System.out.println("Error al escribir log: " + e.getMessage());
        }
    }

    public static String leerLog() {
        String texto = "";
        File archivo = obtenerArchivo(ARCHIVO_LOG);

        try {
            if (!archivo.exists()) {
                texto = "El archivo de log está vacío.";
            } else {
                Scanner entrada = new Scanner(archivo);

                while (entrada.hasNextLine()) {
                    texto = texto + entrada.nextLine() + "\n";
                }

                entrada.close();

                if (texto.isEmpty()) {
                    texto = "El archivo de log está vacío.";
                }
            }

        } catch (IOException e) {
            texto = "Error al leer el archivo de log.";
        }

        return texto;
    }

    public static void borrarLog() throws IOException {
        PrintWriter salida = new PrintWriter(new FileWriter(obtenerArchivo(ARCHIVO_LOG), false));
        salida.close();
    }
}
