package Optimizacion;

/**
 *
 * @author Rogelio Perez Guevara
 * @version 28/04/2024
 */
public class Optimizacion_Sin_Mirilla
{
    public static void main(String[] args)
    {
        long startTime = System.nanoTime(); // Capturamos el tiempo de inicio
        
        Optimizacion_Sin_Mirilla persona1 = new Optimizacion_Sin_Mirilla("Rogelio");
        Optimizacion_Sin_Mirilla persona2 = new Optimizacion_Sin_Mirilla("Perez");
        Optimizacion_Sin_Mirilla persona3 = new Optimizacion_Sin_Mirilla("Guevara");
        
        // Llamamos al método saludar() varias veces
        for (int i = 0; i < 9; i++)
        {
            persona1.saludar();
            persona2.saludar();
            persona3.saludar();
        }
        
        long endTime = System.nanoTime(); // Capturamos el tiempo de finalización
        long duration = (endTime - startTime) / 1000000; // Calculamos la duración en milisegundos
        System.out.println("Tiempo de ejecución sin optimización: " + duration + " milisegundos");
    }
    
    private String nombre;
    
    public Optimizacion_Sin_Mirilla(String nombre)
    {
        this.nombre = nombre;
    }
    
    public void saludar()
    {
        System.out.println("¡Hola, soy " + nombre + "!");
    }
}