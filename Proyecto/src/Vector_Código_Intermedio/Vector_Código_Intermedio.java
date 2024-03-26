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
 * @version 25/03/2024
 */
public class Vector_Código_Intermedio {
    public static Stack<String> pilaDeOperadores = new Stack<>();
    public static Stack<Integer> pilaDePrioridad = new Stack<>();
    
    public static Stack<String> pilaDeEstatutos = new Stack<>();
    public static Stack<Integer> pilaDeDirecciones = new Stack<>();
    
    public static ArrayList<String> cintaDeVCI = new ArrayList<>();
    public static ArrayList<Integer> cintaDeVCIApuntador = new ArrayList<>();
    
    public static int apuntador = -1;
    
    public static void main (String [] args)
    {
        // Leer el archivo de texto línea por línea
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Tokens2.txt")))
        {
            String linea;
            String lineaSiguiente;
            
            String guardado = null;
            
            while ((linea = br.readLine()) != null)
            {
                // Almacenar la posición actual del lector
                br.mark(4096); // Establecer un límite para la cantidad de caracteres que se pueden retroceder

                // Dividir la línea en partes
                String[] partes = linea.split(",");
                String palabra = partes[0];
                
                
                // Reglas de análisis léxico
                switch (palabra)
                {
                    case "si":
                        pilaDeEstatutos.push(linea);
                        break;
                    case "entonces":
                        guardado = linea;
                        while (!pilaDeOperadores.isEmpty())
                        {
                            cintaDeVCI.add(pilaDeOperadores.pop());
                            cintaDeVCIApuntador.add(apuntador++);
                            pilaDePrioridad.pop();
                        }
                        pilaDeDirecciones.push(apuntador + 1);
                        cintaDeVCI.add("└");
                        cintaDeVCIApuntador.add(apuntador++);
                        cintaDeVCI.add(guardado);
                        cintaDeVCIApuntador.add(apuntador++);
                        guardado = null;
                        break;
                    case "sino":
                        guardado = linea;
                        pilaDeEstatutos.push(linea);
                        pilaDeDirecciones.pop();
                        cintaDeVCI.add((apuntador + 3) + "");
                        cintaDeVCIApuntador.add(apuntador++);
                        cintaDeVCI.add("└");
                        cintaDeVCIApuntador.add(apuntador++);
                        pilaDeDirecciones.push(apuntador + 1);
                        cintaDeVCI.add(guardado);
                        cintaDeVCIApuntador.add(apuntador++);
                        guardado = null;
                        break;
                    case "fin":
                        pilaDeEstatutos.pop();
                        // Leer la línea siguiente
                        lineaSiguiente = br.readLine();
                        // Procesar la línea siguiente si es necesario
                        if (lineaSiguiente.equals("sino"))
                        {
                            casesino(linea);
                        }
                        else
                        {
                            int apu = pilaDeDirecciones.pop();
                            cintaDeVCI.set(apu, (apuntador + 1) + "");
                        }
                        break;
                    case "operadores":
                }
            }
        }
    }
    
    public static void casesino(String linea)
    {
        String guardado = linea;
        pilaDeEstatutos.push(linea);
        pilaDeDirecciones.pop();
        cintaDeVCI.add((apuntador + 3) + "");
        cintaDeVCIApuntador.add(apuntador++);
        cintaDeVCI.add("└");
        cintaDeVCIApuntador.add(apuntador++);
        pilaDeDirecciones.push(apuntador + 1);
        cintaDeVCI.add(guardado);
        cintaDeVCIApuntador.add(apuntador++);
        guardado = null;
    }
}
