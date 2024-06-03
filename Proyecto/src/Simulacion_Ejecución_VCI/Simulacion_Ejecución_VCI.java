package Simulacion_Ejecución_VCI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.io.*;

/**
 * Practica No. 4 Simulación de la pila de ejecución del VCI
 * @author Equipo 2
 * @version 14/05/2024
 */
public class Simulacion_Ejecución_VCI
{
    public static void main(String[] args) throws IOException
    {
        String archivoVariables = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Simbolos.txt"; // Nombre del archivo TXT de variables
        String archivoOperaciones = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Cinta de VCI.txt"; // Nombre del archivo TXT de operaciones

        Stack<Token> pilaControl = new Stack<>();
        ArrayList<Tokensimbolo> tablaSimbolos = new ArrayList<>();
        tablaSimbolos = lecturaDeSimbolos(archivoVariables);

        try
        {
            String linea;
            BufferedReader lector = new BufferedReader(new FileReader(archivoOperaciones));
            ArrayList<String> lectura = new ArrayList<>();
            Scanner sc = new Scanner(System.in);
            Token tem;
            Token tem2;
            Token temporal;
            Object valor = null;
            Object valor2 = null;
            boolean vv;
            int pc_aux;
            int pc = 0;
            
            while ((linea = lector.readLine()) != null)
            {
                lectura.add(linea);
            }
            
            while ((pc < lectura.size()))
            {
                String[] partes = lectura.get(pc).split(",");
                pc++;
                
                if (partes[1].equals("null") || partes[1].equals("0") || partes[1].equals("-4") || partes[1].equals("-5") || partes[1].equals("-7")
                        || partes[1].equals("-10") || partes[1].equals("-16") || partes[1].equals("-17") || partes[1].equals("-21")
                        || partes[1].equals("-22") || partes[1].equals("-24") || partes[1].equals("-25") || partes[1].equals("-26")
                        || partes[1].equals("-31") || partes[1].equals("-32") || partes[1].equals("-33") || partes[1].equals("-34")
                        || partes[1].equals("-35") || partes[1].equals("-36") || partes[1].equals("-41") || partes[1].equals("-42") || partes[1].equals("-43"))
                {
                    switch (partes[1])
                    {
                        // este case se puede omitir
                        case "null":
                            pilaControl.add(new Token(partes[0], 0, 0, 0));
                            break;
                        case "-4":
                            partes = lectura.get(pc).split(",");
                            System.out.println("ingrese algo");
                            
                            for (int i = 0; i < tablaSimbolos.size(); i++)
                            {
                                if (tablaSimbolos.get(i).getNombre().equals(partes[0]))
                                {
                                    tablaSimbolos.get(i).setValor(sc.nextLine());
                                }
                            }
                            
                            pc++;
                            break;
                        case "-5":
                            boolean entro = false;
                            partes = lectura.get(pc).split(",");
                            
                            for (int i = 0; i < tablaSimbolos.size(); i++)
                            {
                                if (tablaSimbolos.get(i).getNombre().equals(partes[0]))
                                {
                                    System.out.println(tablaSimbolos.get(i).getValor());
                                    entro = true;
                                }
                            }
                            
                            if (entro == false)
                            {
                                partes = lectura.get(pc).split(",");
                                System.out.println(partes[2]);
                            }
                            
                            pc++;
                            break;
                        case "-7":
                        case "0":
                            pc = Integer.parseInt(pilaControl.pop().getNombre());
                            break;
                        case "-10":
                        case "-16":
                        case "-17":
                            pc_aux = Integer.parseInt(pilaControl.pop().getNombre());
                            vv = Boolean.parseBoolean(pilaControl.pop().getNombre());
                            
                            if (vv == true)
                            {
                                pc = pc;
                            }
                            else
                            {
                                pc = pc_aux;
                            }
                            break;
                        // caso *
                        case "-21":
                            tem = pilaControl.pop();
                            tem2 = pilaControl.pop();
                            
                            switch (tem.getToken())
                            {
                                case -51:
                                case -61:
                                    if (tem.getToken() == -51 && tem2.getToken() == -51)
                                    {

                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) * Integer.parseInt("" + valor)), -61, 0, 0));
                                    }
                                    else if (tem.getToken() == -61 && tem2.getToken() == -61)
                                    {
                                        pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) * Integer.parseInt(tem.getNombre())), -61, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -51)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) * Integer.parseInt("" + valor)), -61, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) * Integer.parseInt(tem.getNombre())), -61, 0, 0));
                                        }
                                    }
                                    break;
                                case -52:
                                case -62:
                                    if (tem.getToken() == -52 && tem2.getToken() == -52)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) * Double.parseDouble("" + valor)), -62, 0, 0));
                                    }
                                    else if (tem.getToken() == -62 && tem2.getToken() == -62)
                                    {
                                        pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) * Double.parseDouble(tem.getNombre())), -62, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -52)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) * Double.parseDouble("" + valor)), -62, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) * Double.parseDouble(tem.getNombre())), -62, 0, 0));
                                        }
                                    }
                                    break;
                            }
                            break;
                        // caso /
                        case "-22":
                            tem = pilaControl.pop();
                            tem2 = pilaControl.pop();
                            
                            switch (tem.getToken())
                            {
                                case -51:
                                case -61:
                                    if (tem.getToken() == -51 && tem2.getToken() == -51)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) / Integer.parseInt("" + valor)), -61, 0, 0));
                                    }
                                    else if (tem.getToken() == -61 && tem2.getToken() == -61)
                                    {
                                        pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) / Integer.parseInt(tem.getNombre())), -61, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -51)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) / Integer.parseInt("" + valor)), -61, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) / Integer.parseInt(tem.getNombre())), -61, 0, 0));
                                        }
                                    }
                                    break;
                                case -52:
                                case -62:
                                    if (tem.getToken() == -52 && tem2.getToken() == -52)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) / Double.parseDouble("" + valor)), -62, 0, 0));
                                    }
                                    else if (tem.getToken() == -62 && tem2.getToken() == -62)
                                    {
                                        pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) / Double.parseDouble(tem.getNombre())), -62, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -52)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) / Double.parseDouble("" + valor)), -62, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) / Double.parseDouble(tem.getNombre())), -62, 0, 0));
                                        }
                                    }
                                    break;
                            }
                            break;
                        // caso +
                        case "-24":
                            tem = pilaControl.pop();
                            tem2 = pilaControl.pop();
                            
                            switch (tem.getToken())
                            {
                                case -51:
                                case -61:
                                    if (tem.getToken() == -51 && tem2.getToken() == -51)
                                    {

                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) + Integer.parseInt("" + valor)), -61, 0, 0));
                                    }
                                    else if (tem.getToken() == -61 && tem2.getToken() == -61)
                                    {
                                        pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) + Integer.parseInt(tem.getNombre())), -61, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -51)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) + Integer.parseInt("" + valor)), -61, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) + Integer.parseInt(tem.getNombre())), -61, 0, 0));
                                        }
                                    }
                                    break;
                                case -52:
                                case -62:
                                    if (tem.getToken() == -52 && tem2.getToken() == -52)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) + Double.parseDouble("" + valor)), -62, 0, 0));
                                    }
                                    else if (tem.getToken() == -62 && tem2.getToken() == -62)
                                    {
                                        pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) + Double.parseDouble(tem.getNombre())), -62, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -52)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) + Double.parseDouble("" + valor)), -62, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) + Double.parseDouble(tem.getNombre())), -62, 0, 0));
                                        }
                                    }
                                    break;
                                case -53:
                                case -63:
                                    if (tem.getToken() == -53 && tem2.getToken() == -53)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token(("" + valor2) + ("" + valor), -63, 0, 0));
                                    }
                                    else if (tem.getToken() == -63 && tem2.getToken() == -63)
                                    {
                                        pilaControl.add(new Token(((tem2.getNombre()) + (tem.getNombre())), -62, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -53)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token(((tem2.getNombre()) + ("" + valor)), -63, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token((("" + valor2) + (tem.getNombre())), -62, 0, 0));
                                        }
                                    }
                                    break;
                            }
                            break;
                        // caso -
                        case "-25":
                            tem = pilaControl.pop();
                            tem2 = pilaControl.pop();
                            
                            switch (tem.getToken())
                            {
                                case -51:
                                case -61:
                                    if (tem.getToken() == -51 && tem2.getToken() == -51)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) - Integer.parseInt("" + valor)), -61, 0, 0));
                                    }
                                    else if (tem.getToken() == -61 && tem2.getToken() == -61)
                                    {
                                        pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) - Integer.parseInt(tem.getNombre())), -61, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -51)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) - Integer.parseInt("" + valor)), -61, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) - Integer.parseInt(tem.getNombre())), -61, 0, 0));
                                        }
                                    }
                                    break;
                                case -52:
                                case -62:
                                    if (tem.getToken() == -52 && tem2.getToken() == -52)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) - Double.parseDouble("" + valor)), -62, 0, 0));
                                    }
                                    else if (tem.getToken() == -62 && tem2.getToken() == -62)
                                    {
                                        pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) - Double.parseDouble(tem.getNombre())), -62, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -52)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) - Double.parseDouble("" + valor)), -62, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) - Double.parseDouble(tem.getNombre())), -62, 0, 0));
                                        }
                                    }
                                    break;
                            }
                            break;
                        // caso =
                        case "-26":
                            tem = pilaControl.pop();
                            Token guardar = pilaControl.pop();
                            
                            for (int i = 0; i < tablaSimbolos.size(); i++)
                            {
                                if (guardar.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                {
                                    tablaSimbolos.get(i).setValor((tem.getNombre()));
                                }
                            }
                            //System.out.println(tablaSimbolos.get(0).toString());
                            break;
                        // caso <
                        case "-31":
                            tem = pilaControl.pop();
                            tem2 = pilaControl.pop();
                            
                            switch (tem.getToken())
                            {
                                case -51:
                                case -61:
                                    if (tem.getToken() == -51 && tem2.getToken() == -51)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) < Integer.parseInt("" + valor)), -64, 0, 0));
                                    }
                                    else if (tem.getToken() == -61 && tem2.getToken() == -61)
                                    {
                                        pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) < Integer.parseInt(tem.getNombre())), -64, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -51)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) < Integer.parseInt("" + valor)), -64, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) < Integer.parseInt(tem.getNombre())), -64, 0, 0));
                                        }
                                    }
                                    break;
                                case -52:
                                case -62:
                                    if (tem.getToken() == -52 && tem2.getToken() == -52)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) < Double.parseDouble("" + valor)), -64, 0, 0));
                                    }
                                    else if (tem.getToken() == -62 && tem2.getToken() == -62)
                                    {
                                        pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) < Double.parseDouble(tem.getNombre())), -64, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -52)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) < Double.parseDouble("" + valor)), -64, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) < Double.parseDouble(tem.getNombre())), -64, 0, 0));
                                        }
                                    }
                                    break;
                            }
                            break;
                        // caso <=
                        case "-32":
                            tem = pilaControl.pop();
                            tem2 = pilaControl.pop();
                            
                            switch (tem.getToken())
                            {
                                case -51:
                                case -61:
                                    if (tem.getToken() == -51 && tem2.getToken() == -51)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) <= Integer.parseInt("" + valor)), -64, 0, 0));
                                    }
                                    else if (tem.getToken() == -61 && tem2.getToken() == -61)
                                    {
                                        pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) <= Integer.parseInt(tem.getNombre())), -64, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -51)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) <= Integer.parseInt("" + valor)), -64, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) <= Integer.parseInt(tem.getNombre())), -64, 0, 0));
                                        }
                                    }
                                    break;
                                case -52:
                                case -62:
                                    if (tem.getToken() == -52 && tem2.getToken() == -52)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) <= Double.parseDouble("" + valor)), -64, 0, 0));
                                    }
                                    else if (tem.getToken() == -62 && tem2.getToken() == -62)
                                    {
                                        pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) <= Double.parseDouble(tem.getNombre())), -64, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -52)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) <= Double.parseDouble("" + valor)), -64, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) <= Double.parseDouble(tem.getNombre())), -64, 0, 0));
                                        }
                                    }
                                    break;
                            }
                            break;
                        // caso >
                        case "-33":
                            tem = pilaControl.pop();
                            tem2 = pilaControl.pop();
                            
                            switch (tem.getToken())
                            {
                                case -51:
                                case -61:
                                    if (tem.getToken() == -51 && tem2.getToken() == -51)
                                    {

                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) > Integer.parseInt("" + valor)), -64, 0, 0));
                                    }
                                    else if (tem.getToken() == -61 && tem2.getToken() == -61)
                                    {
                                        pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) > Integer.parseInt(tem.getNombre())), -64, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -51)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) > Integer.parseInt("" + valor)), -64, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) > Integer.parseInt(tem.getNombre())), -64, 0, 0));
                                        }
                                    }
                                    break;
                                case -52:
                                case -62:
                                    if (tem.getToken() == -52 && tem2.getToken() == -52)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) > Double.parseDouble("" + valor)), -64, 0, 0));
                                    }
                                    else if (tem.getToken() == -62 && tem2.getToken() == -62)
                                    {
                                        pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) > Double.parseDouble(tem.getNombre())), -64, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -52)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) > Double.parseDouble("" + valor)), -64, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) > Double.parseDouble(tem.getNombre())), -64, 0, 0));
                                        }
                                    }
                                    break;
                            }
                            break;
                        // caso >=
                        case "-34":
                            tem = pilaControl.pop();
                            tem2 = pilaControl.pop();
                            
                            switch (tem.getToken())
                            {
                                case -51:
                                case -61:
                                    if (tem.getToken() == -51 && tem2.getToken() == -51)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) >= Integer.parseInt("" + valor)), -64, 0, 0));
                                    }
                                    else if (tem.getToken() == -61 && tem2.getToken() == -61)
                                    {
                                        pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) >= Integer.parseInt(tem.getNombre())), -64, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -51)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) >= Integer.parseInt("" + valor)), -64, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) >= Integer.parseInt(tem.getNombre())), -64, 0, 0));
                                        }
                                    }
                                    break;
                                case -52:
                                case -62:
                                    if (tem.getToken() == -52 && tem2.getToken() == -52)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) >= Double.parseDouble("" + valor)), -64, 0, 0));
                                    }
                                    else if (tem.getToken() == -62 && tem2.getToken() == -62)
                                    {
                                        pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) >= Double.parseDouble(tem.getNombre())), -64, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -52)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) >= Double.parseDouble("" + valor)), -64, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) >= Double.parseDouble(tem.getNombre())), -64, 0, 0));
                                        }
                                    }
                                    break;
                            }
                            break;
                        // caso ==
                        case "-35":
                            tem = pilaControl.pop();
                            tem2 = pilaControl.pop();
                            
                            switch (tem.getToken())
                            {
                                case -51:
                                case -61:
                                    if (tem.getToken() == -51 && tem2.getToken() == -51)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) == Integer.parseInt("" + valor)), -64, 0, 0));
                                    }
                                    else if (tem.getToken() == -61 && tem2.getToken() == -61)
                                    {
                                        pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) == Integer.parseInt(tem.getNombre())), -64, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -51)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) == Integer.parseInt("" + valor)), -64, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) == Integer.parseInt(tem.getNombre())), -64, 0, 0));
                                        }
                                    }
                                    break;
                                case -52:
                                case -62:
                                    if (tem.getToken() == -52 && tem2.getToken() == -52)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) == Double.parseDouble("" + valor)), -64, 0, 0));
                                    }
                                    else if (tem.getToken() == -62 && tem2.getToken() == -62)
                                    {
                                        pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) == Double.parseDouble(tem.getNombre())), -64, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -52)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) == Double.parseDouble("" + valor)), -64, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) == Double.parseDouble(tem.getNombre())), -64, 0, 0));
                                        }
                                    }
                                    break;
                                case -54:
                                case -64:
                                    if (tem.getToken() == -54 && tem2.getToken() == -54)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (valor2.equals(valor)), -64, 0, 0));
                                    } else if (tem.getToken() == -64 && tem2.getToken() == -64)
                                    {
                                        pilaControl.add(new Token("" + ((tem2.getNombre().equals(tem.getNombre()))), -64, 0, 0));

                                    }
                                    else
                                    {
                                        if (tem.getToken() == -54)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + ((tem2.getNombre().equals(valor))), -64, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (valor2.equals(tem.getNombre())), -64, 0, 0));
                                        }
                                    }
                                    break;
                            }
                            break;
                        // caso !=
                        case "-36":
                            tem = pilaControl.pop();
                            tem2 = pilaControl.pop();
                            
                            switch (tem.getToken())
                            {
                                case -51:
                                case -61:
                                    if (tem.getToken() == -51 && tem2.getToken() == -51)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) != Integer.parseInt("" + valor)), -64, 0, 0));
                                    }
                                    else if (tem.getToken() == -61 && tem2.getToken() == -61)
                                    {
                                        pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) != Integer.parseInt(tem.getNombre())), -64, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -51)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt(tem2.getNombre()) != Integer.parseInt("" + valor)), -64, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Integer.parseInt("" + valor2) != Integer.parseInt(tem.getNombre())), -64, 0, 0));
                                        }
                                    }
                                    break;
                                case -52:
                                case -62:
                                    if (tem.getToken() == -52 && tem2.getToken() == -52)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) != Double.parseDouble("" + valor)), -64, 0, 0));
                                    }
                                    else if (tem.getToken() == -62 && tem2.getToken() == -62)
                                    {
                                        pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) != Double.parseDouble(tem.getNombre())), -64, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -52)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble(tem2.getNombre()) != Double.parseDouble("" + valor)), -64, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (Double.parseDouble("" + valor2) != Double.parseDouble(tem.getNombre())), -64, 0, 0));
                                        }
                                    }
                                    break;
                                case -54:
                                case -64:
                                    if (tem.getToken() == -54 && tem2.getToken() == -54)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        pilaControl.add(new Token("" + (!valor2.equals(valor)), -64, 0, 0));
                                    }
                                    else if (tem.getToken() == -64 && tem2.getToken() == -64)
                                    {
                                        pilaControl.add(new Token("" + (!(tem2.getNombre().equals(tem.getNombre()))), -64, 0, 0));
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -54)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (!(tem2.getNombre().equals(valor))), -64, 0, 0));
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            pilaControl.add(new Token("" + (!valor2.equals(tem.getNombre())), -64, 0, 0));
                                        }
                                    }
                                    break;
                            }
                            break;
                        // caso &&
                        case "-41":
                            tem = pilaControl.pop();
                            tem2 = pilaControl.pop();
                            
                            switch (tem.getToken())
                            {
                                case -54:
                                case -64:
                                    if (tem.getToken() == -54 && tem2.getToken() == -54)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        if (valor2.equals("true") && valor.equals("true"))
                                        {
                                            pilaControl.add(new Token("true", -64, 0, 0));
                                        }
                                        else
                                        {
                                            pilaControl.add(new Token("false", -64, 0, 0));
                                        }
                                    }
                                    else if (tem.getToken() == -64 && tem2.getToken() == -64)
                                    {
                                        if (tem2.getNombre().equals("true") && tem.getNombre().equals("true"))
                                        {
                                            pilaControl.add(new Token("true", -64, 0, 0));
                                        }
                                        else
                                        {
                                            pilaControl.add(new Token("false", -64, 0, 0));
                                        }
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -54)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            if (tem2.getNombre().equals("true") && valor.equals("true"))
                                            {
                                                pilaControl.add(new Token("true", -64, 0, 0));
                                            }
                                            else
                                            {
                                                pilaControl.add(new Token("false", -64, 0, 0));
                                            }
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            if (valor2.equals("true") && tem.getNombre().equals("true"))
                                            {
                                                pilaControl.add(new Token("true", -64, 0, 0));
                                            }
                                            else
                                            {
                                                pilaControl.add(new Token("false", -64, 0, 0));
                                            }
                                        }
                                    }
                                    break;
                            }
                            break;
                        // caso ||
                        case "-42":
                            tem = pilaControl.pop();
                            tem2 = pilaControl.pop();
                            
                            switch (tem.getToken())
                            {
                                case -54:
                                case -64:
                                    if (tem.getToken() == -54 && tem2.getToken() == -54)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        if (valor2.equals("true") || valor.equals("true"))
                                        {
                                            pilaControl.add(new Token("true", -64, 0, 0));
                                        }
                                        else
                                        {
                                            pilaControl.add(new Token("false", -64, 0, 0));
                                        }
                                    }
                                    else if (tem.getToken() == -64 && tem2.getToken() == -64)
                                    {

                                        if (tem2.getNombre().equals("true") || tem.getNombre().equals("true"))
                                        {
                                            pilaControl.add(new Token("true", -64, 0, 0));
                                        }
                                        else
                                        {
                                            pilaControl.add(new Token("false", -64, 0, 0));
                                        }
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -54)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            if (tem2.getNombre().equals("true") || valor.equals("true"))
                                            {
                                                pilaControl.add(new Token("true", -64, 0, 0));
                                            }
                                            else
                                            {
                                                pilaControl.add(new Token("false", -64, 0, 0));
                                            }
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            if (valor2.equals("true") || tem.getNombre().equals("true"))
                                            {
                                                pilaControl.add(new Token("true", -64, 0, 0));
                                            }
                                            else
                                            {
                                                pilaControl.add(new Token("false", -64, 0, 0));
                                            }
                                        }
                                    }
                                    break;
                            }
                            break;
                        // caso !
                        case "-43":
                            tem = pilaControl.pop();
                            tem2 = pilaControl.pop();
                            
                            switch (tem.getToken())
                            {
                                case -54:
                                case -64:
                                    if (tem.getToken() == -54 && tem2.getToken() == -54)
                                    {
                                        for (int i = 0; i < tablaSimbolos.size(); i++)
                                        {
                                            if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor = tablaSimbolos.get(i).getValor();
                                            }
                                            
                                            if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                            {
                                                valor2 = tablaSimbolos.get(i).getValor();
                                            }
                                        }
                                        
                                        if (valor2.equals("true") && valor.equals("true"))
                                        {
                                            pilaControl.add(new Token("false", -64, 0, 0));

                                        }
                                        else
                                        {
                                            pilaControl.add(new Token("true", -64, 0, 0));
                                        }
                                    }
                                    else if (tem.getToken() == -64 && tem2.getToken() == -64)
                                    {
                                        if (tem2.getNombre().equals("true") && tem.getNombre().equals("true"))
                                        {
                                            pilaControl.add(new Token("false", -64, 0, 0));
                                        }
                                        else
                                        {
                                            pilaControl.add(new Token("true", -64, 0, 0));
                                        }
                                    }
                                    else
                                    {
                                        if (tem.getToken() == -54)
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            if (tem2.getNombre().equals("true") && valor.equals("true"))
                                            {
                                                pilaControl.add(new Token("false", -64, 0, 0));
                                            }
                                            else
                                            {
                                                pilaControl.add(new Token("true", -64, 0, 0));
                                            }
                                        }
                                        else
                                        {
                                            for (int i = 0; i < tablaSimbolos.size(); i++)
                                            {
                                                if (tem2.getNombre().equals(tablaSimbolos.get(i).getNombre()))
                                                {
                                                    valor2 = tablaSimbolos.get(i).getValor();
                                                }
                                            }
                                            
                                            if (valor2.equals("true") && tem.getNombre().equals("true"))
                                            {
                                                pilaControl.add(new Token("false", -64, 0, 0));
                                            }
                                            else
                                            {
                                                pilaControl.add(new Token("true", -64, 0, 0));
                                            }
                                        }
                                    }
                                    break;
                            }
                            break;
                    }
                }
                else
                {
                    pilaControl.add(new Token(partes[0], Integer.parseInt(partes[1]), Integer.parseInt(partes[2]), Integer.parseInt(partes[3])));
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("error");
        }
        catch (ArithmeticException e)
        {
            System.out.println("no se puede hacer la divicion entre 0");
        }

        System.out.println("\nimpresion de la tabla de simbolos");
        
        for (int i = 0; i < tablaSimbolos.size(); i++)
        {
            System.out.println(tablaSimbolos.get(i).toString());
        }

        sobreescribirArchivoSimbolos(tablaSimbolos);
    }

    public static void sobreescribirArchivoSimbolos(ArrayList<Tokensimbolo> simbolos)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Simbolos.txt")))
        {
            for (Tokensimbolo simbolo : simbolos)
            {
                bw.write(simbolo.getNombre() + "," + simbolo.getToken() + "," + simbolo.getValor() + ","
                        + "Main");
                bw.newLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<Tokensimbolo> lecturaDeSimbolos(String tbSimbolos)
    {
        ArrayList<Tokensimbolo> arreglo = new ArrayList<>();
        try
        {
            BufferedReader lector = new BufferedReader(new FileReader(tbSimbolos));
            String linea;
            
            while ((linea = lector.readLine()) != null)
            {
                String[] partes = linea.split(",");
                arreglo.add(new Tokensimbolo(partes[0], Integer.parseInt(partes[1]), partes[2]));
            }
            
            lector.close();
        }
        catch (IOException e)
        {
            System.out.println("error de lectura");
        }

        return arreglo;
    }
}