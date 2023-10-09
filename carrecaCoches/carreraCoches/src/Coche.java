import java.time.Duration;
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
    private int contador=0;
    public Coche(int veloMaxima, String nombre, Double distanciaRecorrida,Integer distanciaCarrera) {
        this.veloMaxima = veloMaxima;
        this.nombre = nombre;
        this.distanciaRecorrida = distanciaRecorrida;
        this.distanciaCarrera=distanciaCarrera;
    }

    public int getVeloMaxima() {
        return veloMaxima;
    }

    public Double getDistanciaRecorrida() {
        return distanciaRecorrida;
    }
    public void aumentarDistanciaRecorrida(){
        Double numAleatorio= Math.floor(Math.random()*(this.veloMaxima+1));
        this.distanciaRecorrida+=numAleatorio;
    }
    @Override
    public void run(){
        long horaInicio= System.currentTimeMillis();
        Date horaInicioFinal=new Date(horaInicio);

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
        Date horaFinal = new Date(tiempoFinal);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(horaInicioFinal);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(horaFinal);
        long diferenciaMillis =Math.abs(cal1.getTimeInMillis() - cal2.getTimeInMillis()) ;
        long horas = TimeUnit.MILLISECONDS.toHours(diferenciaMillis);
        long minutos = TimeUnit.MILLISECONDS.toMinutes(diferenciaMillis) % 60;
        long segundos = TimeUnit.MILLISECONDS.toSeconds(diferenciaMillis) % 60;

        ThreadGroup miGrupo=Thread.currentThread().getThreadGroup();
        Thread[] hilosEnElGrupo = new Thread[miGrupo.activeCount()];

        Arrays.stream(hilosEnElGrupo).sorted(Comparator<>);
        System.out.println(miGrupo.enumerate(hilosEnElGrupo));


        System.out.println("Coche"+this.nombre+" llega a meta en Horas:"+horas+" Minutos: "+minutos+" Segundos: "+segundos);


    }
}
