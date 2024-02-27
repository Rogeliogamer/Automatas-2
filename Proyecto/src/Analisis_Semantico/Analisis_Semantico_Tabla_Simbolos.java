package Analisis_Semantico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author Equipo 2
 * @version 06/02/202
 */
public class Analisis_Semantico_Tabla_Simbolos
{
    public static void main (String [] args)
    {
        //Se recibe la ruta del archivo de la tabla de Tokens y se guarda en una variable tipo String llamada "archivoEntrada"
        String archivoEntrada = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Tokens.txt";
        //Se recibe la ruta del archivo de la tabla de simbolos y se guarda en una variable tipo String llamada "archivoSalida"
        String archivoSalida = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Simbolos.txt";
        //Se recibe la ruta del archivo de la tabla de Tokens Nueva y se guarda en una variable tipo String llamada "archivoNuevoTokens"
        String archivoNuevoTokens = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Tokens Nueva.txt";
        //Variable para llevar el control de tipo de dato de los tokens validos
        String valor = null;
        //Lee el contenido del archivo
        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida));
             BufferedWriter bwn = new BufferedWriter(new FileWriter(archivoNuevoTokens))) {
            //Variable para almacenar el contenido linea por linea
            String linea;
            //Variable contador para la nueva tabka de tokens
            int count = 0;
            //Se inicializa un ciclo para dar lectura al archivo de entrada linea por linea
            while ((linea = br.readLine()) != null) {
                //Se utiliza como separador la "," y se divide en 4 partes
                String[] partes = linea.split(",", 4);
                //Cada parte dividida se guarda en una variable String
                String primeraParte = partes[0].trim();
                String segundaParte = partes[1].trim();
                String terceraParte = partes[2].trim();
                String cuartaParte = partes[3].trim();
                //Si encuentra en la primera parte una declaracion de tipo de dato cambia el valor de la variable "valor"
                if(primeraParte.equals("entero"))
                {
                    valor = "0";
                }
                else if(primeraParte.equals("real"))
                {
                    valor = "0.0";
                }
                else if(primeraParte.equals("cadena"))
                {
                    valor = "Null";
                }
                else if(primeraParte.equals("logico"))
                {
                    valor = "True";
                }
                
                //Si en el la segunda parte encuentra un tipo de datos entre -51 y -54
                //Escribe en el archivo de salida la primera y segunda parte, el valor
                //asignado y Main todo separado por la ","
                if (segundaParte.equals("-51") || segundaParte.equals("-52") || segundaParte.equals("-53") || segundaParte.equals("-54"))
                {
                    bw.write(primeraParte + ",");
                    bw.write(segundaParte + ",");
                    bw.write(valor + ",");
                    bw.write("Main\n");
                    //bwn.write(primeraParte + ",");
                    //bwn.write(segundaParte + ",");
                    //bwn.write(count++ + ",");
                    //bwn.write(cuartaParte + "\n");
                }
                /*else
                {
                    bwn.write(primeraParte + ",");
                    bwn.write(segundaParte + ",");
                    bwn.write(terceraParte + ",");
                    bwn.write(cuartaParte + "\n");
                }*/
            }
            //Imprime un mensaje para dar por completado el proceso
            System.out.println("Se ha generado el archivo: " + archivoSalida);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Llama al metodo para eliminar la duplicidad
        try {
            eliminarLineasRepetidas(archivoSalida);
            System.out.println("Procesamiento completado.");
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }
    
    //Metodo para eliminar elementos duplicados en la generacion de la tabla de simbolos
    //El metodo recibe el archivo de salida
    public static void eliminarLineasRepetidas(String inputFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        Set<String> firstParts = new HashSet<>();
        StringBuilder result = new StringBuilder();
        //Variable para almacenar el contenido linea por linea
        String line;
        //Se inicializa un ciclo para dar lectura al archivo de salida linea por linea
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",", 4); // Divide la línea en cuatro partes utilizando ","
            String firstPart = parts[0]; // La primera parte antes de la ","
            if (!firstParts.contains(firstPart)) {
                // Si esta es la primera vez que vemos esta primera parte, la agregamos al resultado
                result.append(line).append("\n");
                firstParts.add(firstPart);
            }
        }
        // Cerrar el archivo
        reader.close();
        // Escribir el resultado de vuelta al archivo de entrada
        BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
        writer.write(result.toString());
        writer.close();
    }
}