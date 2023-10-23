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
    public void run() {
        Cliente clienteConseguido;
        double segundos_iniciar= System.currentTimeMillis();
        try {
             clienteConseguido=this.cola.getCafe();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(clienteConseguido==null){
            System.out.println("El cliente "+this.nombre+" no ha conseguido su cafe y se va");
            return;
        }
        System.out.println("El cliente "+this.nombre+" ha conseguido su cafe en "+((System.currentTimeMillis()-segundos_iniciar)/1000)+" segundos");


    }
}
