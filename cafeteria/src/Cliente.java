public class Cliente extends Thread {
    private String nombre;
    private int tiempoEspera;
    private Cola cola;
    private boolean haRecibidoCafe = false;
    public Cliente(String nombre, int tiempoEspera, Cola cola) {
        this.nombre = nombre;
        this.tiempoEspera = tiempoEspera;
        this.cola = cola;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Método que se encarga de simular la llegada de un cliente a la cafetería. Este llegará en un momento aleatorio, por lo
     * que se pausará la ejecucion del hilo hasta que este llegue, se registra la hora de llegada y se indica por pantalla, luego
     * se asigna a la lista de la cola y se pone a esperar el café. En el momento en el que el café este listo, este será notificado e
     * imprimirá por pantalla cuanto tiempo le ha llevado conseguir el café. Si espera y no consigue su café a tiempo, este se irá de la
     * cafetería.
     * */
    @Override
    public void run() {
        try {
            long timepoInicio= System.currentTimeMillis();

            synchronized (this){
                this.cola.agregarCliente(this);
                wait(this.tiempoEspera);
                if (!this.haRecibidoCafe) {
                    System.out.println(this.nombre + " se ha ido de la cafetería.\n");
                }else{System.out.println(this.nombre + " ha recibido su café en " + ((System.currentTimeMillis() - timepoInicio) / 1000) + " segundos\n");

                }
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void setHaRecibidoCafe() {
        System.out.println("cafe entregado");
        this.haRecibidoCafe = true;
        notifyAll();

    }

}
