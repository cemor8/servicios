import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Monitor {
    private int numeroZonas;
    private int zonasTerminadas=0;
    private Mapa mapa;

    public Monitor(int numeroZonas,Mapa mapa) {
        this.numeroZonas = numeroZonas;
        this.mapa=mapa;
    }
    /**
     * Método que se encarga de devolver un item de la lista de items
     * que tiene la zona de un personaje para que se le aparezca a este, luego
     * mueve los items restantes de su zona aleatoriamente.
     * */
    public synchronized Item devolverItem(Personaje personaje){
        while (!personaje.getZonaAsignada().isGenerada()){
            try {
                wait();
            }catch (InterruptedException err){
                System.out.println(err.getMessage());
            }
        }
        if (personaje.getZonaAsignada().getListaItems().isEmpty()){
            return null;
        }
        try {
            Thread.sleep((int) (Math.floor((Math.random())*1000)+100));
        }catch (InterruptedException err){
            System.out.println(err.getMessage());
        }
        Item item=personaje.getZonaAsignada().getListaItems().get(0);
        personaje.getZonaAsignada().getListaItems().remove(item);
        Collections.shuffle(personaje.getZonaAsignada().getListaItems());
        return item;

    }
    /**
     * Método que se encarga de esperar a que una zona acabe, esto sucederá cuando se quede sin items,
     * cuando todas las zonas hayan acabado, se calculara
     * */
    public synchronized void esperarZonas(Zona zona){
        System.out.println("esperando");
        notifyAll();
        while (!zona.getListaItems().isEmpty()) {
            try {
                wait();
            }catch (InterruptedException err){
                System.out.println(err.getMessage());
            }

        }
        System.out.println("acabe");
        this.zonasTerminadas++;
        if(zonasTerminadas==numeroZonas){
            this.calcularLadder();
        }

    }
    /**
     * Método que se encarga de calcular la clasificación de un mapa.
     * */
    private void calcularLadder(){
        Clasificacion clasificacion=new Clasificacion();
        clasificacion.calcularResultado(this.mapa);
    }
    public synchronized void despertar(){
        notifyAll();
    }
}
