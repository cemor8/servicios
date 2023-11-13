import org.w3c.dom.CDATASection;

import java.util.ArrayList;
import java.util.List;

public class Zona extends Thread{
    private int numeroDeZona;
    private ArrayList<Item> listaItems = new ArrayList<Item>();
    private ArrayList<Personaje> listaPersonajes;
    private int itemsAGenerar;
    private Monitor monitor;
    private int personajesACrear;
    private boolean generada = false;
    private boolean terminada=false;

    public Zona(ArrayList<Personaje> listaPersonajes, int itemsAGenerar,Monitor monitor,int personajesACrear,int numeroDeZona) {
        this.listaPersonajes = listaPersonajes;
        this.itemsAGenerar = itemsAGenerar;
        this.monitor=monitor;
        this.personajesACrear=personajesACrear;
        this.numeroDeZona=numeroDeZona;
    }
    /**
     * Método de la zona que se encarga de crear a los personajes e items de esta, cuando acaba, empieza a
     * esperar a que se acaben los items para acabar la zona.
     * */
    @Override
    public void run(){
        ArrayList<String> nombresPosibles = new ArrayList<>(List.of("Carlos","Pepe","Alberto","Gustavo","Javier","Jaime","Juan","Alex","Roberto","Daniel"));
        ArrayList<String> descripcionesItems = new ArrayList<>(List.of("Desgastado","Poco desgastado","Usado","Casi nuevo","Nuevo","Recien fabricado"));

        System.out.println("Zona "+numeroDeZona+" creando items");

        for(int i = 0 ;i<this.itemsAGenerar;i++){
            double aleatorio = Math.random();
            if(aleatorio<0.3){
                aleatorio =Math.floor(Math.random() * 200)+1;
                Espada espada=new Espada("espada en estado "+descripcionesItems.get((int) (Math.random() * descripcionesItems.size())), (int) aleatorio);
                this.listaItems.add(espada);
            }else if(aleatorio<0.6){
                aleatorio =Math.floor(Math.random() * 50)+1;
                Pechera pechera=new Pechera("pechera en estado "+descripcionesItems.get((int) (Math.random() * descripcionesItems.size())), (int) aleatorio);
                this.listaItems.add(pechera);
            }else{
                aleatorio =Math.floor(Math.random() * 25)+1 ;
                Casco casco=new Casco("casco en estado "+descripcionesItems.get((int) (Math.random() * descripcionesItems.size())), (int) aleatorio);
                this.listaItems.add(casco);
            }

        }

        System.out.println("Zona "+numeroDeZona+" creando personajes");

        for(int i = 0 ;i<this.personajesACrear;i++){
            Personaje personaje=new Personaje(this,nombresPosibles.get((int) (Math.random() * nombresPosibles.size())),this.monitor);
            this.listaPersonajes.add(personaje);
            personaje.start();
        }
        this.generada=true;
        this.monitor.esperarZonas(this);

    }

    public ArrayList<Item> getListaItems() {
        return listaItems;
    }

    public ArrayList<Personaje> getListaPersonajes() {
        return listaPersonajes;
    }

    public boolean isTerminada() {
        return terminada;
    }

    public boolean isGenerada() {
        return generada;
    }

    public int getNumeroDeZona() {
        return numeroDeZona;
    }

    public void setTerminada(boolean terminada) {
        this.terminada = terminada;
    }
}
