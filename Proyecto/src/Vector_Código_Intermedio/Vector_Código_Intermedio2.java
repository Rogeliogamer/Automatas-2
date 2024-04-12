package Vector_Código_Intermedio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Rogelio Perez Guevara
 * @author Equipo 2
 * @version 12/04/2024
 */
public class Vector_Código_Intermedio2 {
    public static Stack<String> pilaDeOperadores = new Stack<>();
    public static Stack<Integer> pilaDePrioridad = new Stack<>();
    public static Stack<String> pilaDeOperadoresTokens = new Stack<>();
    
    public static Stack<String> pilaDeEstatutos = new Stack<>();
    public static Stack<Integer> pilaDeDirecciones = new Stack<>();
    
    public static ArrayList<String> cintaDeVCI = new ArrayList<>();
    public static ArrayList<Integer> cintaDeVCIApuntador = new ArrayList<>();
    
    public static int apuntador = 0;
    
    public static void main (String [] args) throws IOException
    {
        // Leer el archivo de texto línea por línea
        //try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Tokens2.txt")))
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Ejemplo6.txt")))
        {
            String linea;
            String lineaSiguiente = null;
            
            String guardado = null;
            String temporal = null;
            String token = null;
            String hasta = null;
            String inicio = null;
            String mientras = null;
            int apuntador2;
            String resultado = null;
            
            while ((linea = br.readLine()) != null)
            {
                // Dividir la línea en partes
                String[] partes = linea.split(",");
                String palabra2 = partes[0];
                String palabra = partes[1];
                
                // Reglas de análisis léxico
                switch (palabra)
                {
                    case "-6":
                        pilaDeEstatutos.push(linea);
                        break;
                    case "-16":
                        guardado = linea;
                        while (!pilaDeOperadores.isEmpty())
                        {
                            pilaDeOperadores.pop();
                            pilaDePrioridad.pop();
                            cintaDeVCI.add(pilaDeOperadoresTokens.pop());
                            cintaDeVCIApuntador.add(apuntador++);
                        }
                        
                        pilaDeDirecciones.push(apuntador + 1);
                        
                        cintaDeVCI.add("└");
                        cintaDeVCIApuntador.add(apuntador++);
                        
                        cintaDeVCI.add(guardado);
                        cintaDeVCIApuntador.add(apuntador++);
                        guardado = null;
                        break;
                    case "-7":
                        guardado = linea;
                        pilaDeEstatutos.push(linea);
                        
                        if (!pilaDeDirecciones.isEmpty())
                        {   
                            apuntador2 = pilaDeDirecciones.pop();
                            for (int i = 0; i < cintaDeVCI.size(); i++)
                            {
                                if (i == apuntador2)
                                {
                                    cintaDeVCI.set(i-1, ((apuntador + 1) + 1) + "");
                                }
                            }
                        }
                        
                        cintaDeVCI.add("└");
                        cintaDeVCIApuntador.add(apuntador++);
                        
                        pilaDeDirecciones.push(apuntador + 1);
                        
                        cintaDeVCI.add(guardado);
                        cintaDeVCIApuntador.add(apuntador++);
                        guardado = null;
                        break;
                    case "-3":
                        if (!pilaDeEstatutos.isEmpty())
                        {
                            pilaDeEstatutos.pop();
                        }
                        
                        String archivo = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Ejemplo6.txt";
                        String palabraBuscada = linea;
                        try (BufferedReader br2 = new BufferedReader(new FileReader(archivo))) 
                        {
                            String linea2;
                            boolean palabraEncontrada = false;
                            
                            // Leemos el archivo línea por línea
                            while ((linea2 = br2.readLine()) != null)
                            {
                                // Buscamos la palabra en la línea
                                if (linea2.contains(palabraBuscada))
                                {
                                    palabraEncontrada = true;
                                    // Leemos la línea siguiente si existe
                                    lineaSiguiente = br2.readLine();
                                    break; // Salimos del bucle una vez que encontramos la palabra
                                }
                            }
                        }
                        
                        resultado = obtenerPrimerElementoSplit(lineaSiguiente);
                        // Procesar la línea siguiente si es necesario
                        if ("sino".equals(resultado))
                        {
                            resultado = null;
                            break;
                        }
                        else if ("hasta".equals(resultado))
                        {
                            break;
                        }
                        else if (mientras != null && "mientras".equals(mientras))
                        {
                            if (!pilaDeDirecciones.isEmpty())
                            {
                                apuntador2 = pilaDeDirecciones.pop();
                                for (int i = 0; i < cintaDeVCI.size(); i++)
                                {
                                    if (i == apuntador2)
                                    {
                                        cintaDeVCI.set(i-1, ((apuntador + 1) + 1) + "");
                                    }
                                }
                            }
                            
                            if (!pilaDeDirecciones.isEmpty())
                            {
                                apuntador2 = pilaDeDirecciones.pop();
                                cintaDeVCI.add((apuntador2 - 1) + "");
                                cintaDeVCIApuntador.add(apuntador++);
                            }
                            
                            cintaDeVCI.add("fin-mientras");
                            cintaDeVCIApuntador.add(apuntador++);
                            inicio = null;
                            break;
                        }
                        else if(inicio != null && "inicio".equals(inicio))
                        {
                            if (!pilaDeDirecciones.isEmpty())
                            {
                                int apu = pilaDeDirecciones.pop();
                            
                                for (int i = 0; i < cintaDeVCI.size(); i++)
                                {
                                    if (i == apu)
                                    {
                                        if ("└".equals(cintaDeVCI.get(i-2)))
                                        {
                                            cintaDeVCI.set(i-2, apuntador + "");
                                        }
                                        else if ("└".equals(cintaDeVCI.get(i-1)))
                                        {
                                            cintaDeVCI.set(i-1, apuntador + "");
                                        }
                                    }
                                }
                            }
                            inicio = null;
                            break;
                        }
                        break;
                    case "-21":
                        verificarExistencia("*", linea);
                        break;
                    case "-22":
                        verificarExistencia("/", linea);
                        break;
                    case "-23":
                        verificarExistencia("%", linea);
                        break;
                    case "-24":
                        verificarExistencia("+", linea);
                        break;
                    case "-25":
                        verificarExistencia("-", linea);
                        break;
                    case "-31":
                        verificarExistencia("<", linea);
                        break;
                    case "-33":
                        verificarExistencia(">", linea);
                        break;
                    case "-32":
                        verificarExistencia("<=", linea);
                        break;
                    case "-34":
                        verificarExistencia(">=", linea);
                        break;
                    case "-35":
                        verificarExistencia("==", linea);
                        break;
                    case "-36":
                        verificarExistencia("!=", linea);
                        break;
                    case "-43":
                        verificarExistencia("not", linea);
                        break;
                    case "-41":
                        verificarExistencia("and", linea);
                        break;
                    case "-42":
                        verificarExistencia("or", linea);
                        break;
                    case "-26":
                        verificarExistencia("=", linea);
                        break;
                    case "-9":
                        pilaDeEstatutos.push(linea);
                        pilaDeDirecciones.push(apuntador + 1);
                        break;
                    case "-10":
                        hasta = palabra2;
                        temporal = linea;
                        inicio = null;
                        break;
                    case "-73":
                        pilaDeOperadores.push(palabra2);
                        pilaDePrioridad.push(0);
                        pilaDeOperadoresTokens.push(linea);
                        break;
                    case "-74":
                        while(!pilaDeOperadores.isEmpty() && pilaDeOperadores.peek().equals( "("))
                        {
                            String t = pilaDeOperadores.pop();
                            pilaDePrioridad.pop();
                            token = pilaDeOperadoresTokens.pop();
                            if (!"(".equals(t))
                            {
                                cintaDeVCI.add(token);
                                cintaDeVCIApuntador.add(apuntador++);
                            }
                            token = null;
                        }
                        
                        if (!pilaDeOperadores.isEmpty())
                        {
                            pilaDeOperadores.pop();
                            pilaDePrioridad.pop();
                            token = pilaDeOperadoresTokens.pop();
                            cintaDeVCI.add(token);
                            cintaDeVCIApuntador.add(apuntador++);
                        }
                        if (!pilaDeOperadores.isEmpty())
                        {
                            //Elimina el ( de la pila
                            pilaDeOperadores.pop();
                            pilaDePrioridad.pop();
                            pilaDeOperadoresTokens.pop();
                        }
                        
                        if("hasta".equals(hasta))
                        {
                            if (!pilaDeDirecciones.isEmpty())
                            {
                                guardado = (pilaDeDirecciones.pop() - 1) + "";
                                cintaDeVCI.add(guardado);
                                cintaDeVCIApuntador.add(apuntador++);
                            }
                            hasta = null;
                            guardado = null;
                            
                            if(temporal != null)
                            {
                                cintaDeVCI.add(temporal);
                                cintaDeVCIApuntador.add(apuntador++);
                                temporal = null;
                            }
                        }
                        break;
                    case "-2":
                        inicio = "inicio";
                        break;
                    case "-8":
                        pilaDeEstatutos.push(linea);
                        pilaDeDirecciones.push(apuntador + 1);
                        mientras = "mientras";
                        break;
                    case "-17":
                        while (!pilaDeOperadores.isEmpty())
                        {
                            pilaDeOperadores.pop();
                            pilaDePrioridad.pop();
                            guardado = pilaDeOperadoresTokens.pop();
                            cintaDeVCI.add(guardado);
                            cintaDeVCIApuntador.add(apuntador++);
                            guardado = null;
                        }
                        
                        cintaDeVCI.add("└");
                        cintaDeVCIApuntador.add(apuntador++);
                        
                        pilaDeDirecciones.push(apuntador);
                        
                        cintaDeVCI.add(linea);
                        cintaDeVCIApuntador.add(apuntador++);
                        break;
                    case "-75":
                        while (!pilaDeOperadores.isEmpty())
                        {
                            pilaDeOperadores.pop();
                            pilaDePrioridad.pop();
                            token = pilaDeOperadoresTokens.pop();
                            cintaDeVCI.add(token);
                            cintaDeVCIApuntador.add(apuntador++);
                            token = null;
                        }
                        break;
                    default:
                        cintaDeVCI.add(linea);
                        cintaDeVCIApuntador.add(apuntador++);
                        break;
                }
            }
        }
        
        // Ruta del archivo donde se guardará el texto
        String cintaVCI = "C:\\\\Users\\\\rogel\\\\OneDrive\\\\Escritorio\\\\Semestre 8\\\\Lenguajes Y Autómatas II\\\\Proyecto\\\\src\\\\Recursos\\\\Cinta de VCI.txt";
        // Guardamos los ArrayLists en el archivo de texto
        guardarArrayListsEnArchivo(cintaDeVCI, cintaDeVCIApuntador, cintaVCI);
        // Leemos y mostramos el contenido del archivo de texto
        leerArchivoYMostrarContenido(cintaVCI);
    }
    
    public static void verificarExistencia(String temporal, String token)
    {
        String [] prioridad_60 = new String [] {"*", "/", "%"};
        String [] prioridad_50 = new String [] {"+", "-"};
        String [] prioridad_40 = new String [] {"<", ">", "<=", ">=", "==", "!="};
        String [] prioridad_30 = new String [] {"not"};
        String [] prioridad_20 = new String [] {"and"};
        String [] prioridad_10 = new String [] {"or"};
        String [] prioridad_0 = new String [] {"="};
        
        boolean encontrado = false;
        
        for (String elemento : prioridad_0)
        {
            if (temporal.equals(elemento))
            {
                operacionDePilas(temporal, 0, token);
                encontrado = true;
                break;
            }
        }
        
        for (String elemento : prioridad_10)
        {
            if (temporal.equals(elemento))
            {
                operacionDePilas(temporal, 10, token);
                encontrado = true;
                break;
            }
        }
        
        for (String elemento : prioridad_20)
        {
            if (temporal.equals(elemento))
            {
                operacionDePilas(temporal, 20, token);
                encontrado = true;
                break;
            }
        }
        
        for (String elemento : prioridad_30)
        {
            if (temporal.equals(elemento))
            {
                operacionDePilas(temporal, 30, token);
                encontrado = true;
                break;
            }
        }
        
        for (String elemento : prioridad_40)
        {
            if (temporal.equals(elemento))
            {
                operacionDePilas(temporal, 40, token);
                encontrado = true;
                break;
            }
        }
        
        for (String elemento : prioridad_50)
        {
            if (temporal.equals(elemento))
            {
                operacionDePilas(temporal, 50, token);
                encontrado = true;
                break;
            }
        }
        
        for (String elemento : prioridad_60)
        {
            if (temporal.equals(elemento))
            {
                operacionDePilas(temporal, 60, token);
                encontrado = true;
                break;
            }
        }
        
        if (encontrado == false)
        {
            cintaDeVCI.add(token);
            cintaDeVCIApuntador.add(apuntador++);
        }
    }
    
    public static void operacionDePilas(String temporal, int prioridad, String token)
    {
        if (pilaDeOperadores.isEmpty())
        {
            pilaDeOperadores.push(temporal);
            pilaDePrioridad.push(prioridad);
            pilaDeOperadoresTokens.push(token);
        }
        else
        {
            if (pilaDePrioridad.peek() >= prioridad)
            {
                while (pilaDePrioridad.peek() >= prioridad)
                {
                    pilaDeOperadores.pop();
                    pilaDePrioridad.pop();
                    String respaldoToken = pilaDeOperadoresTokens.pop();
                    cintaDeVCI.add(respaldoToken);
                    cintaDeVCIApuntador.add(apuntador++);
                }
                
                pilaDeOperadores.push(temporal);
                pilaDePrioridad.push(prioridad);
                pilaDeOperadoresTokens.push(token);
            }
            else
            {
                pilaDeOperadores.push(temporal);
                pilaDePrioridad.push(prioridad);
                pilaDeOperadoresTokens.push(token);
            }
        }
    }
    
    public static void guardarArrayListsEnArchivo(ArrayList<String> arrayList1, ArrayList<Integer> arrayList2, String rutaArchivo)
    {
        File archivo = new File(rutaArchivo);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo)))
        {
            // Verificamos si el archivo no existe y lo creamos
            if (!archivo.exists())
            {
                archivo.createNewFile();
            }
            
            // Escribimos el primer ArrayList
            for (String elemento : arrayList1)
            {
                writer.write(elemento);
                writer.write(" ↕ ");
            }
            writer.newLine(); // Agregamos un salto de línea después del primer ArrayList
            
            // Escribimos el segundo ArrayList
            for (int i = 0; i < arrayList2.size(); i++)
            {
                writer.write(String.valueOf(arrayList2.get(i))); // Convertimos el entero a cadena de texto antes de escribirlo
                if (i < arrayList2.size() - 1)
                {
                    writer.write(" ↕ ");
                }
            }
            // No es necesario agregar un salto de línea después del segundo ArrayList porque este será el final del archivo
            System.out.println("ArrayLists guardados en el archivo correctamente.");
        }
        catch (IOException e)
        {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    public static void leerArchivoYMostrarContenido(String rutaArchivo)
    {
        try
        {
            java.nio.file.Path path = java.nio.file.Paths.get(rutaArchivo);
            java.util.List<String> contenido = java.nio.file.Files.readAllLines(path);
            System.out.println("Contenido del archivo:");
            for (String linea : contenido)
            {
                System.out.println(linea);
            }
        }
        catch (IOException e)
        {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
    public static String obtenerPrimerElementoSplit(String cadena)
    {
        if (cadena != null)
        {
            String[] partes = cadena.split(",");
            return partes.length > 0 ? partes[0] : "No se encontraron elementos después de dividir la cadena.";
        }
        else
        {
            return "La cadena es nula.";
        }
    }
}
