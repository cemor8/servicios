import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Coche extends Thread{
    private int veloMaxima;
    private String nombre;
    private Double distanciaRecorrida;
    private Integer distanciaCarrera;
    private static int posicionLlegada = 0;

    public Coche(int veloMaxima, String nombre, Double distanciaRecorrida,Integer distanciaCarrera) {
        this.veloMaxima = veloMaxima;
        this.nombre = nombre;
        this.distanciaRecorrida = distanciaRecorrida;
        this.distanciaCarrera=distanciaCarrera;

    }
    /**
     * Método que aumenta la distancia recorrida de un coche en la carrera,
     * se calcula mediante un numero aleatorio entre 0 y la velocidad maxima del coche
     * */
    public void aumentarDistanciaRecorrida(){
        Double numAleatorio= Math.floor(Math.random()*(this.veloMaxima+1));
        this.distanciaRecorrida+=numAleatorio;
    }
    /**
     * Método que ejecuta la carrera, mientras el coche no haya llegado a la meta,
     * se aumenta su distancia recorrida, se esparan 1000ms para mostrar el estado de la carrera.
     * Cuando el coche ha llegado a la meta, se muestra su poscion y el tiempo en segundos que ha
     * tardado en acabar la carrera.
     * */
    @Override
    public void run(){
        double horaInicio= System.currentTimeMillis();
        while (this.distanciaRecorrida<this.distanciaCarrera){
            this.aumentarDistanciaRecorrida();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(this.nombre+" Distancia recorrida: "+this.distanciaRecorrida+" Distancia Carrera: "+this.distanciaCarrera);
        }
        double tiempoFinal = System.currentTimeMillis();
        double tiempoRestado=tiempoFinal-horaInicio;
        double segundos = tiempoRestado / 1000;
        synchronized (Coche.class) {
            posicionLlegada++;
            System.out.println("Cohe: "+this.nombre+" ha acabado: "+posicionLlegada+" segundos: "+ segundos);
        }




    }
}
