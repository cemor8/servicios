public class Barista extends Thread{
    private Cola cola;

    public Barista(Cola cola) {
        this.cola = cola;
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
                System.out.println("Barista está preparando café para " + cliente.getNombre()+"\n");
                prepararCafe(cliente);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    /**
     * Método que se encarga de la simulación de la preparación de un cafe mediante un sleep.
     * Cuando el café se acaba de preparar, este es entregado al cliente
     * */
    public void prepararCafe(Cliente cliente) throws InterruptedException {
        Thread.sleep((int) Math.floor(Math.random()*(cliente.getTiempoEspera()-2000))+1000);
        cliente.recibirCafe();
        System.out.println("cafe acabado para "+cliente.getNombre()+"\n");
    }


}
