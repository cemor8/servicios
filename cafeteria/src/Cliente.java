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

    public int getTiempoEspera() {
        return tiempoEspera;
    }
    /**
     * Método que se encarga de simular la llegada de un cliente a la cafetería. Este llegará en un momento aleatorio, por lo
     * que se pausará la ejecucion del hilo hasta que este llegue, se registra la hora de llegada y se indica por pantalla, luego
     * se asigna a la lista de la cola y se pone a esperar el café. En el momento en el que el café este listo, este será notificado e
     * imprimirá por pantalla cuanto tiempo le ha llevado conseguir el café. Si espera y no consigue su café a tiempo, este se irá de la
     * cafetería.
     * */
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
    /**
     * Método que se encarga de cambiar el valor del atributo haRecibidoCafe del cliente a true, debido
     * a que este ha recibido el café, luego le notifica al cliente que puede dejar de esperar.
     * */
    public synchronized void recibirCafe() {
        haRecibidoCafe = true;
        notifyAll();
    }
}
