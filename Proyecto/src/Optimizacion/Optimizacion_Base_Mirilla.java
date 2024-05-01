package Optimizacion;

/**
 *
 * @author Rogelio Perez Guevara
 * @version 28/04/2024 
 */
public class Optimizacion_Base_Mirilla
{
    public static void main(String[] args)
    {
        Optimizacion_Base_Mirilla persona1 = new Optimizacion_Base_Mirilla("Rogelio");
        Optimizacion_Base_Mirilla persona2 = new Optimizacion_Base_Mirilla("Karina");
        Optimizacion_Base_Mirilla persona3 = new Optimizacion_Base_Mirilla("Idwin");
        
        persona1.saludar();
        
        persona2.saludar();
        
        persona3.saludar();
    }

    private String nombre;
    
    public Optimizacion_Base_Mirilla(String nombre)
    {
        this.nombre = nombre;
    }
    
    public void saludar()
    {
        System.out.println("¡Hola, soy " + nombre + "!");
    }
}
