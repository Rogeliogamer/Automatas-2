package Simulacion_Ejecución_VCI;

import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Rogelio Perez Guevara
 * @author Equipo 2
 * @version 14/05/2024
 */
public class Simulacion_Ejecución_VCI {
    static String archivoVariables = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\TS2.txt"; // Nombre del archivo TXT de variables
    static String archivoOperaciones = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\CV2.txt"; // Nombre del archivo TXT de operaciones
    static Map<String, Object> variables = new HashMap<>();
    static Stack<String> lexema = new Stack<>();
    static Stack<String> valorlexema = new Stack<>();
    static Stack<Integer> apuntador = new Stack<>();
    
    public static void main(String[] args)
    {
        // Leer el archivo de variables y almacenar las variables
        leerVariables(archivoVariables, variables);
        
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoOperaciones)))
        {
            String linea;
            String string2 = null;
            String numtoken2 = null;
            int apu2 = -1;
            String string1 = null;
            String numtoken1 = null;
            int apu1 = -1;
            Map.Entry<String, Object> entry2;
            Map.Entry<String, Object> entry1;
            Map.Entry<String, Object> lastEntry1;
            Map.Entry<String, Object> lastEntry2;
            String str1 = null;
            String str2 = null;
            double numeroDouble1;
            double numeroDouble2;
            int apu = 0;
            
            while ((linea = lector.readLine()) != null)
            {
                String[] partes = linea.split(",");
                
                String lexematoken = partes[0];
                
                // Realizar la operación correspondiente
                    switch (lexematoken)
                    {
                        case "*":
                            // Obtener la ultima entrada de las pilas
                            // Verificar si la pilas no están vacías
                            if (!lexema.isEmpty() && !valorlexema.isEmpty())
                            {
                                string2 = lexema.pop();
                                numtoken2 = valorlexema.pop();
                            }
                            
                            // Verificar si la pilas no están vacías
                            if (!lexema.isEmpty() && !valorlexema.isEmpty())
                            {
                                string1 = lexema.pop();
                                numtoken1 = valorlexema.pop();
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string2.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string2);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    numtoken2 = (String) value;
                                }
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string1.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string1);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    numtoken1 = (String) value; 
                                }
                            }
                            
                            // Convertir a double
                            numeroDouble1 = Double.parseDouble(numtoken1);
                            numeroDouble2 = Double.parseDouble(numtoken2);
                            
                            double multiplicacion = numeroDouble1 * numeroDouble2;
                            lexema.push(""+multiplicacion);
                            valorlexema.push(""+multiplicacion);
                            
                            string1 = null;
                            numtoken1 = null;
                            apu1 = -1;
                            string2 = null;
                            numtoken2 = null;
                            apu2 = -1;
                            numeroDouble1 = 0;
                            numeroDouble2 = 0;
                            multiplicacion = 0;
                            break;
                        case "/":
                            // Obtener la ultima entrada de las pilas
                            // Verificar si la pilas no están vacías
                            if (!lexema.isEmpty() && !valorlexema.isEmpty())
                            {
                                string2 = lexema.pop();
                                numtoken2 = valorlexema.pop();
                            }
                            
                            // Verificar si la pilas no están vacías
                            if (!lexema.isEmpty() && !valorlexema.isEmpty())
                            {
                                string1 = lexema.pop();
                                numtoken1 = valorlexema.pop();
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string2.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string2);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    numtoken2 = (String) value;
                                }
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string1.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string1);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    numtoken1 = (String) value; 
                                }
                            }
                            
                            // Convertir a double
                            numeroDouble1 = Double.parseDouble(numtoken1);
                            numeroDouble2 = Double.parseDouble(numtoken2);
                            
                            double division = numeroDouble1 / numeroDouble2;
                            lexema.push(""+division);
                            valorlexema.push(""+division);
                            
                            string1 = null;
                            numtoken1 = null;
                            apu1 = -1;
                            string2 = null;
                            numtoken2 = null;
                            apu2 = -1;
                            numeroDouble1 = 0;
                            numeroDouble2 = 0;
                            multiplicacion = 0;
                            break;
                        case "+":
                            // Obtener la ultima entrada de las pilas
                            // Verificar si la pilas no están vacías
                            if (!lexema.isEmpty() && !valorlexema.isEmpty())
                            {
                                string2 = lexema.pop();
                                numtoken2 = valorlexema.pop();
                            }
                            
                            // Verificar si la pilas no están vacías
                            if (!lexema.isEmpty() && !valorlexema.isEmpty())
                            {
                                string1 = lexema.pop();
                                numtoken1 = valorlexema.pop();
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string2.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string2);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    numtoken2 = (String) value;
                                }
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string1.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string1);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    numtoken1 = (String) value; 
                                }
                            }
                            
                            // Convertir a double
                            numeroDouble1 = Double.parseDouble(numtoken1);
                            numeroDouble2 = Double.parseDouble(numtoken2);
                            
                            double suma = numeroDouble1 + numeroDouble2;
                            lexema.push(""+suma);
                            valorlexema.push(""+suma);
                            
                            string1 = null;
                            numtoken1 = null;
                            apu1 = -1;
                            string2 = null;
                            numtoken2 = null;
                            apu2 = -1;
                            numeroDouble1 = 0;
                            numeroDouble2 = 0;
                            suma = 0;
                            break;
                        case "-":
                            // Obtener la ultima entrada de las pilas
                            // Verificar si la pilas no están vacías
                            if (!lexema.isEmpty() && !valorlexema.isEmpty())
                            {
                                string2 = lexema.pop();
                                numtoken2 = valorlexema.pop();
                            }
                            
                            // Verificar si la pilas no están vacías
                            if (!lexema.isEmpty() && !valorlexema.isEmpty())
                            {
                                string1 = lexema.pop();
                                numtoken1 = valorlexema.pop();
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string2.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string2);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    numtoken2 = (String) value;
                                }
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string1.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string1);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    numtoken1 = (String) value; 
                                }
                            }
                            
                            // Convertir a double
                            numeroDouble1 = Double.parseDouble(numtoken1);
                            numeroDouble2 = Double.parseDouble(numtoken2);
                            
                            double menos = numeroDouble1 / numeroDouble2;
                            lexema.push(""+menos);
                            valorlexema.push(""+menos);
                            
                            string1 = null;
                            numtoken1 = null;
                            apu1 = -1;
                            string2 = null;
                            numtoken2 = null;
                            apu2 = -1;
                            numeroDouble1 = 0;
                            numeroDouble2 = 0;
                            menos = 0;
                            break;
                        case "=":
                            // Obtener la ultima entrada de las pilas
                            // Verificar si la pilas no están vacías
                            if (!lexema.isEmpty() && !valorlexema.isEmpty())
                            {
                                string2 = lexema.pop();
                                numtoken2 = valorlexema.pop();
                            }
                            
                            // Verificar si la pilas no están vacías
                            if (!lexema.isEmpty() && !valorlexema.isEmpty())
                            {
                                string1 = lexema.pop();
                                numtoken1 = valorlexema.pop();
                            }
                            
                            // Verificar si la clave no es un valor booleano
                            if (!"true".equalsIgnoreCase(string2) && !"false".equalsIgnoreCase(string2))
                            {
                                // Verificar si la clave contiene solo números
                                if (!string2.matches("\\d+")) // Verifica si la clave no contiene solo números
                                {
                                    // Variable para almacenar el valor asociado con la clave buscada
                                    Object value = variables.get(string2);
                                    value = value + "";
                                    if (value != null && !value.equals("null"))
                                    {
                                        numtoken2 = (String) value;
                                    }
                                }
                            }
                            
                            // Verificar si la clave no es un valor booleano
                            if (!"true".equalsIgnoreCase(string1) && !"false".equalsIgnoreCase(string1)) {
                                // Verificar si la clave contiene solo números
                                if (!string1.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                    // Variable para almacenar el valor asociado con la clave buscada
                                    Object value = variables.get(string1);
                                    value = value + "";
                                    if (value != null && !value.equals("null"))
                                    {
                                        numtoken1 = (String) value;
                                    }
                                }
                            }
                            
                            variables.put(string1, numtoken2);                           
                            
                            string1 = null;
                            numtoken1 = null;
                            apu1 = -1;
                            string2 = null;
                            numtoken2 = null;
                            apu2 = -1;
                            numeroDouble1 = 0;
                            numeroDouble2 = 0;
                            menos = 0;
                            break;
                        case "<":
                            // Obtener la ultima entrada de las pilas
                            // Verificar si la pilas no están vacías
                            if (!lexema.isEmpty() && !valorlexema.isEmpty())
                            {
                                string2 = lexema.pop();
                                numtoken2 = valorlexema.pop();
                            }
                            
                            // Verificar si la pilas no están vacías
                            if (!lexema.isEmpty() && !valorlexema.isEmpty())
                            {
                                string1 = lexema.pop();
                                numtoken1 = valorlexema.pop();
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string2.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string2);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    numtoken2 = (String) value;
                                }
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string1.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string1);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    numtoken1 = (String) value; 
                                }
                            }
                            
                            // Convertir a double
                            numeroDouble1 = Double.parseDouble(numtoken1);
                            numeroDouble2 = Double.parseDouble(numtoken2);
                            
                            if(numeroDouble1 < numeroDouble2) {
                                boolean menor_que = true;
                                lexema.push(""+menor_que);
                                valorlexema.push(""+menor_que);
                            }
                            else {
                                boolean menor_que = false;
                                lexema.push(""+menor_que);
                                valorlexema.push(""+menor_que);
                            }
                            
                            string1 = null;
                            numtoken1 = null;
                            apu1 = -1;
                            string2 = null;
                            numtoken2 = null;
                            apu2 = -1;
                            numeroDouble1 = 0;
                            numeroDouble2 = 0;
                            menos = 0;
                            break;
                        case "<=":
                            // Obtener la ultima entrada de las pilas
                            // Verificar si la pilas no están vacías
                            if (!lexema.isEmpty() && !valorlexema.isEmpty())
                            {
                                string2 = lexema.pop();
                                numtoken2 = valorlexema.pop();
                            }
                            
                            // Verificar si la pilas no están vacías
                            if (!lexema.isEmpty() && !valorlexema.isEmpty())
                            {
                                string1 = lexema.pop();
                                numtoken1 = valorlexema.pop();
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string2.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string2);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    numtoken2 = (String) value;
                                }
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string1.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string1);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    numtoken1 = (String) value; 
                                }
                            }
                            
                            // Convertir a double
                            numeroDouble1 = Double.parseDouble(numtoken1);
                            numeroDouble2 = Double.parseDouble(numtoken2);
                            
                            if(numeroDouble1 <= numeroDouble2) {
                                boolean menor_que = true;
                                lexema.push(""+menor_que);
                                valorlexema.push(""+menor_que);
                            }
                            else {
                                boolean menor_que = false;
                                lexema.push(""+menor_que);
                                valorlexema.push(""+menor_que);
                            }
                            
                            string1 = null;
                            numtoken1 = null;
                            apu1 = -1;
                            string2 = null;
                            numtoken2 = null;
                            apu2 = -1;
                            numeroDouble1 = 0;
                            numeroDouble2 = 0;
                            menos = 0;
                            break;
                        case "||":
                            // Obtener la ultima entrada de las pilas
                            // Verificar si la pilas no están vacías
                            if (!lexema.isEmpty() && !valorlexema.isEmpty())
                            {
                                string2 = lexema.pop();
                                numtoken2 = valorlexema.pop();
                            }
                            
                            // Verificar si la pilas no están vacías
                            if (!lexema.isEmpty() && !valorlexema.isEmpty())
                            {
                                string1 = lexema.pop();
                                numtoken1 = valorlexema.pop();
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string2.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string2);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    numtoken2 = (String) value;
                                }
                            }
                            
                            // Verificar si la clave contiene solo números
                            if (!string1.matches("\\d+")) { // Verifica si la clave no contiene solo números
                                // Variable para almacenar el valor asociado con la clave buscada
                                Object value = variables.get(string1);
                                value = value + "";
                                if (value != null && !value.equals("null"))
                                {
                                    numtoken1 = (String) value; 
                                }
                            }
                            
                            
                            if ("true".equals(string1) || "true".equals(string2))
                            {
                                boolean or = true;
                                lexema.push(""+or);
                                valorlexema.push(""+or);
                            }
                            else {
                                
                                boolean or = false;
                                lexema.push(""+or);
                                valorlexema.push(""+or);
                            }
                            
                            string1 = null;
                            numtoken1 = null;
                            apu1 = -1;
                            string2 = null;
                            numtoken2 = null;
                            apu2 = -1;
                            numeroDouble1 = 0;
                            numeroDouble2 = 0;
                            menos = 0;
                            break;
                        default:
                            lexema.push(lexematoken);
                            valorlexema.push(lexematoken);
                            apu++;
                            break;
                    }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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
}