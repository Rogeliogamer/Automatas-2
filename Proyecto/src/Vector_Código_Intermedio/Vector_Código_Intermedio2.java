package Vector_Código_Intermedio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
        long tiempo_inicial = System.currentTimeMillis();
        // Leer el archivo de texto línea por línea
        //try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Tokens2.txt")))
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Tokens2.txt")))
        {
            String linea;
            String lineaSiguiente = null;
            
            String guardado = null;
            String temporal = null;
            String token = null;
            String hasta = null;
            String sino = null;
            ArrayList<String> inicio = new ArrayList<>();
            ArrayList<String> mientras = new ArrayList<>();
            int apuntador2;
            String resultado = null;
            boolean bandera = false;
            
            while ((linea = br.readLine()) != null)
            {
                // Dividir la línea en partes
                String[] partes = linea.split(",");
                String palabra2 = partes[0];
                String palabra = partes[1];
                
                // Se ejecutará solo la primera vez que se encuentre la palabra "inicio"
                if(!bandera && "-2".equals(palabra))
                {
                    bandera = true;
                }
                
                if(bandera == true)
                {
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

                            pilaDeDirecciones.push(apuntador);

                            cintaDeVCI.add("└");
                            cintaDeVCIApuntador.add(apuntador);
                            apuntador++;

                            cintaDeVCI.add(guardado);
                            cintaDeVCIApuntador.add(apuntador++);
                            guardado = null;
                            break;
                        case "-7":
                            guardado = linea;
                            sino = "sino";
                            
                            pilaDeEstatutos.push(linea);

                            if (!pilaDeDirecciones.isEmpty())
                            {   
                                apuntador2 = pilaDeDirecciones.pop();
                                for (int i = 0; i < cintaDeVCI.size(); i++)
                                {
                                    if (i == apuntador2)
                                    {
                                        cintaDeVCI.set(i, (apuntador + 2) + "");
                                        break;
                                    }
                                }
                            }

                            cintaDeVCI.add("└");
                            pilaDeDirecciones.push(apuntador);
                            cintaDeVCIApuntador.add(apuntador++);

                            cintaDeVCI.add(guardado);
                            cintaDeVCIApuntador.add(apuntador++);
                            guardado = null;
                            break;
                        case "-3":
                            if (!pilaDeEstatutos.isEmpty())
                            {
                                pilaDeEstatutos.pop();
                            }

                            String archivo = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Tokens2.txt";
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
                            else if (!mientras.isEmpty() && "mientras".equals(mientras.get(mientras.size() - 1)) && sino == null)
                            {
                                if (!pilaDeDirecciones.isEmpty())
                                {
                                    apuntador2 = pilaDeDirecciones.pop();
                                    for (int i = 0; i < cintaDeVCI.size(); i++)
                                    {
                                        if (i == apuntador2)
                                        {
                                            cintaDeVCI.set(i, (apuntador + 2) + "");
                                            break;
                                        }
                                    }
                                }

                                if (!pilaDeDirecciones.isEmpty())
                                {
                                    apuntador2 = pilaDeDirecciones.pop();
                                    cintaDeVCI.add((apuntador2) + "");
                                    cintaDeVCIApuntador.add(apuntador++);
                                }

                                cintaDeVCI.add("fin-mientras");
                                cintaDeVCIApuntador.add(apuntador++);
                                mientras.remove("inicio");
                                mientras.remove("mientras");
                                break;
                            }
                            else if(!inicio.isEmpty() && "inicio".equals(inicio.get(inicio.size() - 1)))
                            {
                                sino = null;
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
                                            else if ("└".equals(cintaDeVCI.get(i)))
                                            {
                                                cintaDeVCI.set(i, apuntador + "");
                                            }
                                        }
                                    }
                                }
                                mientras.remove("inicio");
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
                            pilaDeDirecciones.push(apuntador);
                            break;
                        case "-10":
                            hasta = palabra2;
                            temporal = linea;
                            mientras.remove("inicio");
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
                                    guardado = (pilaDeDirecciones.pop()) + "";
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
                            inicio.add("inicio");
                            break;
                        case "-8":
                            pilaDeEstatutos.push(linea);
                            pilaDeDirecciones.push(apuntador);
                            mientras.add("mientras");
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
                            pilaDeDirecciones.push(apuntador);
                            cintaDeVCIApuntador.add(apuntador++);

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
        }
        
        // Ruta del archivo donde se guardará el texto
        String cintaVCI = "C:\\\\Users\\\\rogel\\\\OneDrive\\\\Escritorio\\\\Semestre 8\\\\Lenguajes Y Autómatas II\\\\Proyecto\\\\src\\\\Recursos\\\\Cinta de VCI.txt";
        // Guardamos los ArrayLists en el archivo de texto
        guardarArrayListsEnArchivo(cintaDeVCI, cintaDeVCIApuntador, cintaVCI);
        // Leemos y mostramos el contenido del archivo de texto
        ArrayList<String> primeraLinea = new ArrayList<>();
        ArrayList<String> segundaLinea = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(cintaVCI)))
        {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ↕ ");
                primeraLinea.add(partes[0]);
                segundaLinea.add(partes[1]);
            }
        }
        catch (IOException e)
        {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }
            
        // Menú de opciones
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        while (opcion < 1 || opcion > 4)
        {
            System.out.println("Menú de Opciones:");
            System.out.println("1. Cinta VCI con elementos y apuntadores (Horizontal)");
            System.out.println("2. Cinta VCI solo con elementos (Horizontal)");
            System.out.println("3. Cinta VCI con elementos y apuntadores (Vertical)");
            System.out.println("4. Cinta VCI solo con elementos (Vertical)");
            System.out.print("Ingrese el número de la opción que desea (1-4): ");
            if (scanner.hasNextInt())
            {
                opcion = scanner.nextInt();
                if (opcion < 1 || opcion > 4)
                {
                    System.out.println("Opción no válida. Por favor, ingrese un número entre 1 y 4.");
                }
            }
            else
            {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar el buffer del scanner
            }
        }
        
        // Imprimir según la opción seleccionada
        // Realizar la acción correspondiente a la opción seleccionada
        switch (opcion)
        {
            case 1:
                leerArchivoYMostrarContenido(cintaVCI);
                break;
            case 2:
                leerArchivoYMostrarContenido2(cintaVCI);
                break;
            case 3:
                leerArchivoYMostrarContenido3(cintaVCI);
                break;
            case 4:
                leerArchivoYMostrarContenido4(cintaVCI);
                break;
            default:
                System.out.println("Opción no válida.");
        }
        
        // Dar formato al archivo
        try
        {
            // Procesar el archivo
            formatoArchivo(cintaVCI);
            
            System.out.println("Datos procesados y guardados en el archivo " + cintaVCI);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        long tiempo_final = System.currentTimeMillis() - tiempo_inicial;
        double tiempo_en_segundos = tiempo_final / 1000.0; // Convertir a segundos
        System.out.println("El tiempo total fue de " + tiempo_en_segundos + " segundos");
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
            int maxLength = 0;
            for (String elemento : arrayList1)
            {
                maxLength = Math.max(maxLength, elemento.length());
            }
            
            for (String elemento : arrayList1)
            {
                writer.write(String.format("%-" + maxLength + "s", elemento));
                writer.write(" ↕ ");
            }
            writer.newLine(); // Agregamos un salto de línea después del primer ArrayList
            
            // Escribimos el segundo ArrayList
            for (int i = 0; i < arrayList2.size(); i++)
            {
                writer.write(String.format("%-" + maxLength + "s", arrayList2.get(i)));
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
    
    public static void leerArchivoYMostrarContenido2(String rutaArchivo)
    {
        try
        {
            Path path = Paths.get(rutaArchivo);
            List<String> contenido = Files.readAllLines(path);
            if (!contenido.isEmpty())
            {
                System.out.println("Contenido del archivo:");
                System.out.println(contenido.get(0)); // Imprime solo el primer renglón
            }
            else
            {
                System.out.println("El archivo está vacío.");
            }
        }
        catch (IOException e)
        {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
    public static void leerArchivoYMostrarContenido3(String rutaArchivo)
    {
        try
        {
            Path path = Paths.get(rutaArchivo);
            List<String> lineas = Files.readAllLines(path);
            System.out.println("Contenido del archivo:");
            
            // Imprimir el primer renglón
            imprimirRenglonConNumeros(lineas.get(0));
        }
        catch (IOException e)
        {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
    public static void leerArchivoYMostrarContenido4(String rutaArchivo)
    {
        try
        {
            Path path = Paths.get(rutaArchivo);
            List<String> lineas = Files.readAllLines(path);
            String primerRenglon = lineas.get(0);
            String[] elementos = primerRenglon.split(" ↕ ");
            System.out.println("Contenido del archivo:");
            
            for (String elemento : elementos)
            {
                System.out.println(elemento);
            }
        }
        catch (IOException e)
        {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
    public static void imprimirRenglonConNumeros(String linea) {
        String[] elementos = linea.split(" ↕ ");
        for (int i = 0; i < elementos.length; i++) {
            System.out.println(String.format("%-3d %-20s", i, elementos[i]));
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
    
    public static void formatoArchivo(String cintaVCI) throws IOException
    {
        // Abrir el archivo para lectura
        BufferedReader reader = new BufferedReader(new FileReader(cintaVCI));
        
        // Leer solo la primera línea
        String line = reader.readLine();
        
        // Crear un StringBuilder para almacenar el contenido
        StringBuilder outputContent = new StringBuilder();
        
        // Verificar si la primera línea es null
        if (line != null)
        {
            // Dividir la línea en elementos
            String[] elements = line.split(" ↕ ");

            // Concatenar los elementos con saltos de línea
            for (String element : elements)
            {
                outputContent.append(element).append("\n");
            }
        }
        
        // Cerrar el archivo de entrada
        reader.close();
        
        // Abrir el archivo para escritura
        FileWriter writer = new FileWriter(cintaVCI);
        
        // Escribir el contenido procesado en el archivo
        writer.write(outputContent.toString());
        
        // Cerrar el archivo de salida
        writer.close();
    }
}