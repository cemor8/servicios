public class Personaje extends Thread{
    private Pechera itemPechera=null;
    private Casco itemCasco=null;
    private Espada itemEspada=null;
    private Zona zonaAsignada;
    private String nombre;
    private Monitor monitor;

    public Personaje(Zona zonaAsignada, String nombre,Monitor monitor) {
        this.zonaAsignada = zonaAsignada;
        this.nombre = nombre;
        this.monitor=monitor;
    }
    /**
     * Metodo en el que el personaje intenta obtener un item, luego lo compara y comprueba si lo puede coger o no.
     * */
    @Override
    public void run(){
        while (!this.zonaAsignada.isTerminada()){
            try {
                Thread.sleep((int) (Math.floor((Math.random())*1000)+100));
                Item item = this.monitor.devolverItem(this);
                if(item==null){
                    break;
                }
                System.out.println("A "+this.nombre+" de la Zona "+this.getZonaAsignada().getNumeroDeZona() +" se le aparece "+item.getNombre());
                switch (item) {
                    case Casco casco when (this.itemCasco == null || this.itemCasco.getAtaque() < item.getAtaque()) ->
                            this.itemCasco = casco;
                    case Espada espada when (this.itemEspada == null || this.itemEspada.getAtaque() < item.getAtaque()) ->
                            this.itemEspada = espada;
                    case Pechera pechera when (this.itemPechera == null || this.itemPechera.getAtaque() < item.getAtaque()) ->
                            this.itemPechera = pechera;
                    default -> System.out.println("El item es peor y "+this.nombre+" lo tira a la basura");
                }
            }catch (InterruptedException err){
                System.out.println(err.getMessage());
            }
        }
    }
    /**
     * Método que devuelve el daño total de un personaje.
     * */
    public double getTotal(){
        double total=0;
        if(this.itemEspada!=null){
            total+=this.itemEspada.getAtaque();
        }else if(this.itemCasco!=null){
            total+=this.itemCasco.getAtaque();
        }else if(this.itemPechera!=null){
            total+=this.itemPechera.getAtaque();
        }
        return total;
    }

    public Zona getZonaAsignada() {
        return zonaAsignada;
    }

    public String getNombre() {
        return nombre;
    }
}
