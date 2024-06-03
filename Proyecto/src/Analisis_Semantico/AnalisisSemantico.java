package Analisis_Semantico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * Practica 1 Analisis Semantatico
 * @author Rogelio Perez Guevara
 * @author Equipo 2
 * @version 05/03/2024
 * 
 */
public class AnalisisSemantico
{
    public static void main (String [] args) throws IOException
    {
        // declara los archivos de entrada y de salida
        String archivoTokens = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Tokens.txt";
        String archivoSimbolos = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Simbolos.txt";
        String archivoDirecciones = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Direcciones.txt";
        String archivoNuevoTokens = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Tokens2.txt";
        String valor = null;
        
        // crea la lectura y escritura de los archivos
        try (BufferedReader br = new BufferedReader(new FileReader(archivoTokens));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSimbolos));
             BufferedWriter dw = new BufferedWriter(new FileWriter(archivoDirecciones)))
        {
            // linea almacena la linea leida del archivo
            String linea;
            
            // en este ciclo lo que se hace es separar en 4 partes la cadena de entrada
            while ((linea = br.readLine()) != null)
            {
                String[] partes = linea.split(",", 4);
                String primeraParte = partes[0].trim();
                String segundaParte = partes[1].trim();
                String cuartaParte = partes[3].trim();
                
                // aqui de la primera parte de la cadena lo que se hace es asignar el tipo de valor que es
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
                
                if(primeraParte.contains("@"))
                {
                    dw.write(primeraParte + ",");
                    dw.write(segundaParte + ",");
                    dw.write(cuartaParte + ",");
                    dw.write("0");
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
                }
            }
            //Imprime un mensaje para dar por completado el proceso
            System.out.println("Se ha generado el archivo: " + archivoSimbolos);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
            
        //Llama al metodo para eliminar la duplicidad
        try
        {
            eliminarLineasRepetidas(archivoSimbolos);
            System.out.println("Procesamiento completado.");
        }
        catch (IOException e)
        {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
            
        tk2(archivoSimbolos,archivoTokens,archivoNuevoTokens);
        estaBien(archivoTokens);
    }
        
    private static void estaBien(String leer)
    {
        try
        {
            BufferedReader leerToken = new BufferedReader(new FileReader(leer));
            String linea;
            ArrayList<String> enteros = new ArrayList<>();
            ArrayList<String> reales = new ArrayList<>();
            ArrayList<String> cadena = new ArrayList<>();
            ArrayList<String> logicos = new ArrayList<>();
            ArrayList<String> general = new ArrayList<>();

            while ((linea = leerToken.readLine()) != null)
            {
                String[] partes = linea.split(",", 4);
                switch (partes[1].trim())
                {
                    case "-51":
                        enteros.add(partes[0].trim().replace("&",""));
                        break;
                    case "-52":
                        reales.add(partes[0].trim().replace("%",""));
                        break;
                    case "-53":
                        cadena.add(partes[0].trim().replace("$",""));
                        break;
                    case "-54":
                        logicos.add(partes[0].trim().replace("#",""));
                        break;
                    case "-55":
                        general.add(partes[0].trim().replace("@",""));
                }
            }
            
            for (String elemento : enteros)
            {
                if(reales.contains(elemento) || cadena.contains(elemento) || logicos.contains(elemento) || general.contains(elemento))
                {
                    throw new IOException("la variable esta declarada en 2 tipos de datos");
                }
            }
            
            for (String elemento : reales)
            {
                if(enteros.contains(elemento) || cadena.contains(elemento) || logicos.contains(elemento)||general.contains(elemento))
                {
                    throw new IOException("la variable esta declarada en 2 tipos de datos");
                }
            }
            
            for (String elemento : cadena)
            {
                if(reales.contains(elemento) || enteros.contains(elemento) || logicos.contains(elemento)|| general.contains(elemento))
                {
                    throw new IOException("la variable esta declarada en 2 tipos de datos");
                }
            }
            
            for (String elemento : logicos)
            {
                if(reales.contains(elemento) || cadena.contains(elemento) || enteros.contains(elemento)|| general.contains(elemento))
                {
                    throw new IOException("la variable esta declarada en 2 tipos de datos");
                }
            }
            
            for (String elemento : general)
            {
                if(reales.contains(elemento) || cadena.contains(elemento) || enteros.contains(elemento)|| logicos.contains(elemento))
                {
                    throw new IOException("la variable esta declarada en 2 tipos de datos");
                }
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private static void tk2(String leerTS,String leerT,String escribirNT)
    {
        try (BufferedReader leerToken = new BufferedReader(new FileReader(leerT));
             BufferedWriter t2 = new BufferedWriter(new FileWriter(escribirNT)))
        {
            String linea;
            String linea2;
            int cont = 0;
            
            while ((linea = leerToken.readLine()) != null)
            {
                String[] partes = linea.split(",", 4);
                String primeraParte = partes[0].trim();
                String segundaParte = partes[1].trim();
                String terceraParte = partes[2].trim();
                String cuartaParte = partes[3].trim();
                BufferedReader leerSimbolos = new BufferedReader(new FileReader(leerTS));
                
                while ((linea2 = leerSimbolos.readLine()) !=null)
                {
                    String[] partesSimbolos = linea2.split(",", 4);
                    String primeraParteSimbolo = partesSimbolos[0].trim();
                    
                    if(primeraParte.equals(primeraParteSimbolo))
                    {
                        terceraParte = "" + cont;
                    }
                    cont++;
                }
                leerSimbolos.close();
                t2.write(primeraParte + "," +segundaParte + "," + terceraParte + "," + cuartaParte + "\n");
                cont=0;
            }
            leerToken.close();
            t2.close();
        }
        catch (IOException e)
        {
            System.out.println("no se pudo renombrar");
        }
    }
    
    //Metodo para eliminar elementos duplicados en la generacion de la tabla de simbolos
    //El metodo recibe el archivo de salida
    public static void eliminarLineasRepetidas(String inputFile) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        Set<String> firstParts = new HashSet<>();
        StringBuilder result = new StringBuilder();
        
        //Variable para almacenar el contenido linea por linea
        String line;
        
        //Se inicializa un ciclo para dar lectura al archivo de salida linea por linea
        while ((line = reader.readLine()) != null)
        {
            String[] parts = line.split(",", 4); // Divide la línea en cuatro partes utilizando ","
            String firstPart = parts[0]; // La primera parte antes de la ","
            
            if (!firstParts.contains(firstPart))
            {
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