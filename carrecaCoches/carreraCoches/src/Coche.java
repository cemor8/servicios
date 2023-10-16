import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Coche extends Thread{
    private int veloMaxima;
    private String nombre;
    private Double distanciaRecorrida;
    private Integer distanciaCarrera;
    private int participantes;

    public Coche(int veloMaxima, String nombre, Double distanciaRecorrida,Integer distanciaCarrera, int participantes) {
        this.veloMaxima = veloMaxima;
        this.nombre = nombre;
        this.distanciaRecorrida = distanciaRecorrida;
        this.distanciaCarrera=distanciaCarrera;
        this.participantes=participantes;

    }

    public void aumentarDistanciaRecorrida(){
        Double numAleatorio= Math.floor(Math.random()*(this.veloMaxima+1));
        this.distanciaRecorrida+=numAleatorio;
    }
    @Override
    public void run(){
        long horaInicio= System.currentTimeMillis();
        while (this.distanciaRecorrida<this.distanciaCarrera){
            this.aumentarDistanciaRecorrida();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(this.nombre+" Distancia recorrida: "+this.distanciaRecorrida+" Distancia Carrera: "+this.distanciaCarrera);
        }
        long tiempoFinal = System.currentTimeMillis();
        long tiempoRestado=tiempoFinal-horaInicio;
        long segundos = tiempoRestado / 1000;
        long minutos = segundos / 60;
        System.out.println("Cohe: "+this.nombre+" ha acabado: "+(((this.participantes+2)-Thread.activeCount())+1)+" en minutos: "+minutos+" segundos: "+segundos);



    }
}
