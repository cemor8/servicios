public class Barista extends Thread{
    private Cola cola;
    private String nombre;

    public Barista(Cola cola,String nombre) {
        this.cola = cola;
        this.nombre=nombre;
    }
    /**
     * El barista esta a la espera de que llegue un cliente, cuando llega uno, este lo obtiene mediante
     * el metodo de la cola obtenerCliente. Empieza a preparar un café al cliente mediante el método prepararCafe
     * y luego le indica al cliente que su café esta listo.
     * */
    @Override
    public void run(){

        while (true) {
            try {
                Cliente cliente = this.cola.obtenerCliente();
                System.out.println("Barista "+this.nombre+" está preparando café para " + cliente.getNombre()+"\n");
                prepararCafe(cliente);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void prepararCafe(Cliente cliente) throws InterruptedException {

        System.out.println("cafe acabado para "+cliente.getNombre());
        Thread.sleep(5000);

            cliente.setHaRecibidoCafe();


    }



}
