public class Barista extends Thread{
    private Cola cola;

    public Barista(Cola cola) {
        this.cola = cola;
    }

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
    public void prepararCafe(Cliente cliente) throws InterruptedException {
        Thread.sleep(2000);
        cliente.recibirCafe();
        System.out.println("cafe acabado para cliente "+cliente.getNombre()+"\n");
    }


}
