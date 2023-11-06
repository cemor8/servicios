import java.util.ArrayList;

public class Zona extends Thread{
    private ArrayList<Item> listaItems = new ArrayList<Item>();
    private ArrayList<Personaje> listaPersonajes;
    private boolean terminada=false;
    private int itemsAGenerar;

    public Zona(ArrayList<Personaje> listaPersonajes, int itemsAGenerar) {
        this.listaPersonajes = listaPersonajes;
        this.itemsAGenerar = itemsAGenerar;
    }
    @Override
    public void run(){

    }
}
