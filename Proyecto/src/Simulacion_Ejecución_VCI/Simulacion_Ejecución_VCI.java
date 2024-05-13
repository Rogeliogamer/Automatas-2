package Simulacion_Ejecución_VCI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author Rogelio Perez Guevara
 * @author Equipo 2
 * @version 09/05/2024
 */
public class Simulacion_Ejecución_VCI {
    public static void main(String[] args) {
        String archivoVariables = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\TS.txt"; // Nombre del archivo TXT de variables
        String archivoOperaciones = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\CV.txt"; // Nombre del archivo TXT de operaciones
        Map<String, Object> pila = new LinkedHashMap<>();
        Map<String, Object> variables = new HashMap<>();

        // Leer el archivo de variables y almacenar las variables
        leerVariables(archivoVariables, variables);

        // Procesar el archivo de operaciones
        procesarOperaciones(archivoOperaciones, pila, variables);
    }
    
    // Método para leer el archivo de variables y almacenar las variables en el mapa
    private static void leerVariables(String archivoVariables, Map<String, Object> variables) {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoVariables))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    String nombreVariable = partes[0];
                    String valor = partes[2];
                    variables.put(nombreVariable, valor);
                } else {
                    System.out.println("Línea mal formada en el archivo de variables: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Método para procesar el archivo de operaciones
    private static void procesarOperaciones(String archivoOperaciones, Map<String, Object> pila, Map<String, Object> variables) {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoOperaciones))) {
            String linea;
            String string2 = null;
            Object numtoken2 = null;
            String string1 = null;
            Object numtoken1 = null;
            Map.Entry<String, Object> entry2;
            Map.Entry<String, Object> entry1;
            Map.Entry<String, Object> lastEntry1;
            Map.Entry<String, Object> lastEntry2;
            String str1 = null;
            String str2 = null;
            double numeroDouble1;
            double numeroDouble2;
            
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    String operacion = partes[0];
                    String tipoDato = partes[1];
                    String valor = partes[2];
                
                    // Realizar la operación correspondiente
                    switch (operacion) {
                        case "+":
                            // Variables para almacenar el String y el Object
                            string2 = null;
                            numtoken2 = null;
                            
                            // Obtener la ultima entrada del mapa
                            lastEntry1 = null;
                            for (Map.Entry<String, Object> entry : pila.entrySet()) {
                                lastEntry1 = entry;
                            }
                            if (lastEntry1 != null) {
                                string2 = lastEntry1.getKey();
                                numtoken2 = lastEntry1.getValue();
                            }
                            
                            // Eliminar la entrada del mapa
                            pila.remove(string2);
                            
                            // Variables para almacenar el String y el Object
                            string1 = null;
                            numtoken1 = null;
                            
                            // Obtener la ultima entrada del mapa
                            lastEntry2 = null;
                            for (Map.Entry<String, Object> entry : pila.entrySet()) {
                                lastEntry2 = entry;
                            }
                            if (lastEntry2 != null)
                            {
                                string1 = lastEntry2.getKey();
                                numtoken1 = lastEntry2.getValue();
                            }
                            
                            // Eliminar la entrada del mapa
                            pila.remove(string1);
                            
                            // Verificar si la clave contiene solo números
                            if (!string2.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string2);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    str2 = (String) value;
                                    numtoken2 = str2;
                                }
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string1.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string1);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    str1 = (String) value;
                                    numtoken1 = str1;
                                }
                            }
                            
                            if (numtoken1 instanceof Double) {
                                str1 = numtoken1+"";
                                str2 = numtoken2+"";
                            }
                            else if (numtoken1 instanceof String) {
                                str1 = (String) numtoken1;
                                str2 = (String) numtoken2;
                            }
                            
                            // Convertir a double
                            numeroDouble1 = Double.parseDouble(str1);
                            numeroDouble2 = Double.parseDouble(str2);
                            
                            double suma = numeroDouble1 + numeroDouble2;
                            pila.put(""+suma, suma);
                            
                            break;
                        case "/":
                            // Variables para almacenar el String y el Object
                            string2 = null;
                            numtoken2 = null;
                            
                            // Obtener la ultrima entrada del mapa
                            lastEntry1 = null;
                            for (Map.Entry<String, Object> entry : pila.entrySet()) {
                                lastEntry1 = entry;
                            }
                            if (lastEntry1 != null)
                            {
                                string2 = lastEntry1.getKey();
                                numtoken2 = lastEntry1.getValue();
                            }
                            
                            // Eliminar la entrada del mapa
                            pila.remove(string2);
                            
                            // Variables para almacenar el String y el Object
                            string1 = null;
                            numtoken1 = null;
                            
                            // Obtener la ultima entrada del mapa
                            lastEntry2 = null;
                            for (Map.Entry<String, Object> entry : pila.entrySet()) {
                                lastEntry2 = entry;
                            }
                            if (lastEntry2 != null) {
                                string1 = lastEntry2.getKey();
                                numtoken1 = lastEntry2.getValue();
                            }
                            
                            // Eliminar la entrada del mapa
                            pila.remove(string1);
                            
                            /// Verificar si la clave contiene solo números
                            if (!string2.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string2);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    str2 = (String) value;
                                    numtoken2 = str2;
                                }
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string1.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string1);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    str1 = (String) value;
                                    numtoken1 = str1;
                                }
                            }
                            
                            if (numtoken1 instanceof Double) {
                                str1 = numtoken1+"";
                                str2 = numtoken2+"";
                            }
                            else if (numtoken1 instanceof String) {
                                str1 = (String) numtoken1;
                                str2 = (String) numtoken2;
                            }
                            
                            // Convertir a double
                            numeroDouble1 = Double.parseDouble(str1);
                            numeroDouble2 = Double.parseDouble(str2);
                            
                            double division = numeroDouble1 / numeroDouble2;
                            pila.put(""+division, division);
                            
                            break;
                        case "*":
                            // Variables para almacenar el String y el Object
                            string2 = null;
                            numtoken2 = null;
                            
                            // Obtener la última entrada del mapa
                            lastEntry1 = null;
                            for (Map.Entry<String, Object> entry : pila.entrySet()) {
                                lastEntry1 = entry;
                            }
                            if (lastEntry1 != null) {
                                string2 = lastEntry1.getKey();
                                numtoken2 = lastEntry1.getValue();
                            }
                            
                            // Eliminar la entrada del mapa
                            pila.remove(string2);
                            
                            // Variables para almacenar el String y el Object
                            string1 = null;
                            numtoken1 = null;
                            
                            // Obtener la última entrada del mapa
                            lastEntry2 = null;
                            for (Map.Entry<String, Object> entry : pila.entrySet()) {
                                lastEntry2 = entry;
                            }
                            if (lastEntry2 != null) {
                                string1 = lastEntry2.getKey();
                                numtoken1 = lastEntry2.getValue();
                            }
                            
                            // Eliminar la entrada del mapa
                            pila.remove(string1);
                            
                            // Verificar si la clave contiene solo números
                            if (!string2.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string2);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    str2 = (String) value;
                                    numtoken2 = str2;
                                }
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string1.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string1);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    str1 = (String) value;
                                    numtoken1 = str1;
                                }
                            }
                            
                            if (numtoken1 instanceof Double) {
                                str1 = numtoken1+"";
                                str2 = numtoken2+"";
                            }
                            else if (numtoken1 instanceof String) {
                                str1 = (String) numtoken1;
                                str2 = (String) numtoken2;
                            }
                            
                            // Convertir a double
                            numeroDouble1 = Double.parseDouble(str1);
                            numeroDouble2 = Double.parseDouble(str2);
                            
                            double multiplicacion = numeroDouble1 * numeroDouble2;
                            pila.put(""+multiplicacion, multiplicacion);
                            
                            break;
                        case "=":
                            // Variables para almacenar el String y el Object
                            string2 = null;
                            numtoken2 = null;
                            
                            // Obtener la ultima entrada del mapa
                            lastEntry1 = null;
                            for (Map.Entry<String, Object> entry : pila.entrySet()) {
                                lastEntry1 = entry;
                            }
                            if (lastEntry1 != null) {
                                string2 = lastEntry1.getKey();
                                numtoken2 = lastEntry1.getValue();
                            }
                            
                            // Eliminar la entrada del mapa
                            pila.remove(string2);
                            
                            // Variables para almacenar el String y el Object
                            string1 = null;
                            numtoken1 = null;
                            
                            // Obtener la ultima entrada del mapa
                            lastEntry2 = null;
                            for (Map.Entry<String, Object> entry : pila.entrySet()) {
                                lastEntry2 = entry;
                            }
                            if (lastEntry2 != null) {
                                string1 = lastEntry2.getKey();
                                numtoken1 = lastEntry2.getValue();
                            }
                            
                            // Eliminar la entrada del mapa
                            pila.remove(string1);
                            
                            // Verificar si la clave contiene solo números
                            if (!string2.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string2);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    str2 = (String) value;
                                    numtoken2 = str2;
                                }
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string1.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string1);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    str1 = (String) value;
                                    numtoken1 = str1;
                                }
                            }
                            
                            variables.put(string1, numtoken2);                           
                            
                            break;
                        default:
                            pila.put(operacion, operacion);
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
