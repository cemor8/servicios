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

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        try {
            double timepoInicio= System.currentTimeMillis();
            System.out.println(nombre + " ha llegado a la cafetería.");
            this.cola.agregarCliente(this);
            System.out.println(this.nombre+" esperando" );
            wait(tiempoEspera);
            if (!haRecibidoCafe) {
                System.out.println(nombre + " se ha ido de la cafetería.");
            }else {
                System.out.println(nombre + " ha recibido su café en "+((System.currentTimeMillis()-timepoInicio)/1000));
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
