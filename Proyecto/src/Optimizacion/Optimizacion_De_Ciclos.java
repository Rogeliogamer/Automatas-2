package Optimizacion;

public class Optimizacion_De_Ciclos {
    public static void main(String[] args) {
        long tiempoInicial=System.nanoTime();
        ReduccionFrecuencia();
        long tiempoFinal=System.nanoTime();
        System.out.println("el tiempo del codigo sin optimizar fue de: " + ((tiempoFinal-tiempoInicial)/1e6));
        tiempoInicial = System.nanoTime();
        OptimizadoReduccionFrecuencia();
        tiempoFinal = System.nanoTime();
        System.out.println("el tiempo del codigo optimizado fue de: " + ((tiempoFinal-tiempoInicial)/1e6));
    }
    public static void ReduccionFrecuencia() {
        int suma = 0;
        for (int i = 1; i <= 10; i++) {
            suma += i * i;
        }

        System.out.println("La suma de los cuadrados de los números del 1 al 10 es: " + suma);
    }

    public static void OptimizadoReduccionFrecuencia(){
        int suma = 0;
        int cuadrado = 1;
        suma=0;
        for (int i = 1; i <= 10; i++) {
            cuadrado = i * i;
            suma += cuadrado;
        }
        System.out.println("La suma de los cuadrados de los números del 1 al 10 es: " + suma);
    }
}
