public class Cliente extends Thread{
    private String nombre;
    private int tiempoEspera;
    private boolean haRecibidoCafe=false;
    private Cola cola;
    private boolean listo=false;
    private long horaLlegada=0;

    public Cliente(String nombre, int tiempoEspera,Cola cola) {
        this.nombre = nombre;
        this.tiempoEspera = tiempoEspera;
        this.cola=cola;
    }


    @Override
    public  void run(){
        try {
            Thread.sleep((int) (Math.random() * (3000 - 1000 + 1) + 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    public void setListo(boolean listo) {
        this.listo = listo;
    }

    public void setHoraLlegada(long horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public boolean isListo() {
        return listo;
    }

    public long getHoraLlegada() {
        return horaLlegada;
    }
}
