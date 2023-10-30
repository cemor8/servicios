public class Cliente extends Thread {
    private String nombre;
    private int tiempoEspera;
    private Cola cola;
    private boolean haRecibidoCafe = false;
    private int tiempoLlegada;

    public Cliente(String nombre, int tiempoEspera, Cola cola, int tiempoLlegada) {
        this.nombre = nombre;
        this.tiempoEspera = tiempoEspera;
        this.cola = cola;
        this.tiempoLlegada=tiempoLlegada;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public synchronized void run() {
        try {
            Thread.sleep(tiempoLlegada);
            double timepoInicio= System.currentTimeMillis();
            System.out.println(nombre + " ha llegado a la cafetería.\n");
            this.cola.agregarCliente(this);
            wait(tiempoEspera);
            if (!haRecibidoCafe) {
                System.out.println(nombre + " se ha ido de la cafetería.\n");
            }else {
                System.out.println(nombre + " ha recibido su café en "+((System.currentTimeMillis()-timepoInicio)/1000)+" segundos\n");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public synchronized void recibirCafe() {
        haRecibidoCafe = true;
        notifyAll();
    }
}
