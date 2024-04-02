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
    public static Stack<String> pilaDeOperadoresTokens = new Stack<>();
    
    public static Stack<String> pilaDeEstatutos = new Stack<>();
    public static Stack<Integer> pilaDeDirecciones = new Stack<>();
    
    public static ArrayList<String> cintaDeVCI = new ArrayList<>();
    public static ArrayList<Integer> cintaDeVCIApuntador = new ArrayList<>();
    
    public static int apuntador = -1;
    
    public static void main (String [] args) throws IOException
    {
        // Leer el archivo de texto línea por línea
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Tokens2.txt")))
        {
            String linea;
            String lineaSiguiente;
            
            String guardado = null;
            String temporal = null;
            String until = null;
            String begin = null;
            
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
                            break;
                        }
                        else if (lineaSiguiente.equals("until"))
                        {
                            break;
                        }
                        else if(begin != null)
                        {
                            int apu = pilaDeDirecciones.pop();
                            cintaDeVCI.set(apu, (apuntador + 1) + "");
                            begin = null;
                            break;
                        }
                        break;
                    case "*":
                        verificarExistencia("*",palabra);
                        break;
                    case "/":
                        verificarExistencia("/",palabra);
                        break;
                    case "+":
                        verificarExistencia("+",palabra);
                        break;
                    case "-":
                        verificarExistencia("-",palabra);
                        break;
                    case "<":
                        verificarExistencia("<",palabra);
                        break;
                    case ">":
                        verificarExistencia(">",palabra);
                        break;
                    case "<=":
                        verificarExistencia("<=",palabra);
                        break;
                    case ">=":
                        verificarExistencia(">=",palabra);
                        break;
                    case "==":
                        verificarExistencia("==",palabra);
                        break;
                    case "not":
                        verificarExistencia("not",palabra);
                        break;
                    case "and":
                        verificarExistencia("and",palabra);
                        break;
                    case "or":
                        verificarExistencia("or",palabra);
                        break;
                    case "=":
                        verificarExistencia("=",palabra);
                        break;
                    case "repetir":
                        pilaDeEstatutos.push(linea);
                        pilaDeDirecciones.push(apuntador + 1);
                        break;
                    case "until":
                        temporal = linea;
                        until = "until";
                        break;
                    case "(":
                        pilaDeOperadores.push(palabra);
                        pilaDePrioridad.push(0);
                        pilaDeOperadoresTokens.push(linea);
                        break;
                    case ")":
                        while(pilaDeOperadores.pop().equals( "("))
                        {
                            pilaDePrioridad.pop();
                            String token = pilaDeOperadoresTokens.pop();
                            cintaDeVCI.add(token);
                            cintaDeVCIApuntador.add(apuntador++);
                        }
                        
                        pilaDePrioridad.pop();
                        String token = pilaDeOperadoresTokens.pop();
                        cintaDeVCI.add(token);
                        cintaDeVCIApuntador.add(apuntador++);
                        
                        if("until".equals(until))
                        {
                            guardado = pilaDeDirecciones.pop() + "";
                            cintaDeVCI.add(guardado);
                            cintaDeVCIApuntador.add(apuntador++);
                            until = null;
                            
                            if(temporal != null)
                            {
                                cintaDeVCI.add(temporal);
                                cintaDeVCIApuntador.add(apuntador++);
                                temporal = null;
                            }
                        }
                        break;
                    case "begin":
                        begin = "begin";
                        break;
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
    
    public static void verificarExistencia(String temporal, String token)
    {
        String [] prioridad_60 = new String [] {"*", "/"};
        String [] prioridad_50 = new String [] {"+", "-"};
        String [] prioridad_40 = new String [] {"<", ">", "<=", ">=", "=="};
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
            cintaDeVCI.add(temporal);
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
            if (pilaDePrioridad.lastElement() >= prioridad)
            {
                pilaDePrioridad.pop();
                String respaldo = pilaDeOperadores.pop();
                String respaldoToken = pilaDeOperadoresTokens.pop();
                pilaDeOperadores.push(temporal);
                pilaDePrioridad.push(prioridad);
                pilaDeOperadoresTokens.push(token);
                cintaDeVCI.add(respaldoToken);
                cintaDeVCIApuntador.add(apuntador++);
            }
            else
            {
                pilaDeOperadores.push(temporal);
                pilaDePrioridad.push(prioridad);
                pilaDeOperadoresTokens.push(token);
            }
        }
    }
}
