public class Barista extends Thread {
    Cola cola;
    String nombre;

    public Barista(Cola cola, String nombre) {
        this.cola = cola;
        this.nombre = nombre;
    }
    @Override
    public void run(){
        while (true){
            try {
               this.cola.obtenerCliente();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
