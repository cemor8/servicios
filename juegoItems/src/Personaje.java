public class Personaje extends Thread{
    private Pechera itemPechera=null;
    private Casco itemCasco=null;
    private Espada itemEspada=null;
    private Zona zonaAsignada;
    private String nombre;

    public Personaje(Zona zonaAsignada, String nombre) {
        this.zonaAsignada = zonaAsignada;
        this.nombre = nombre;
    }
    @Override
    public void run(){

    }

}
