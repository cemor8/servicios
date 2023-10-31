public class Cliente extends Thread{
    private String nombre;
    private int tiempoEspera;
    private boolean haRecibidoCafe=false;
    private Cola cola;

    public Cliente(String nombre, int tiempoEspera,Cola cola) {
        this.nombre = nombre;
        this.tiempoEspera = tiempoEspera;
        this.cola=cola;
    }


    @Override
    public  void run(){
        try {
            this.cola.meterCliente(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public boolean isHaRecibidoCafe() {
        return haRecibidoCafe;
    }

    public void setHaRecibidoCafe() {
        this.haRecibidoCafe = true;
    }
}
