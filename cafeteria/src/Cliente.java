public class Cliente extends Thread {
    private String nombre;
    private int tiempoEspera;
    private Cola cola;

    public Cliente(String nombre, int tiempoEspera, Cola cola) {
        this.nombre = nombre;
        this.tiempoEspera = tiempoEspera;
        this.cola = cola;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public synchronized void run() {
        try {
             this.cola.getCafe(this);
        } catch (InterruptedException e) {

        }


    }
}
