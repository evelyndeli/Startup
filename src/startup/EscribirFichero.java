/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import java.io.FileWriter;

/**
 *
 * @author evely_001
 */
public class EscribirFichero {
    public EscribirFichero(String[] args) {

            FileWriter fichero = null;
            try {

                    fichero = new FileWriter("fichero_escritura.txt");

                    // Escribimos linea a linea en el fichero
                    for (String linea : args) {
                            fichero.write(linea + "\n");
                    }

                    fichero.close();

            } catch (Exception ex) {
                    System.out.println("Mensaje de la excepción: " + ex.getMessage());
            }
    }
}
