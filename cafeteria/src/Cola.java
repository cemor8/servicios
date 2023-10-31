import java.util.ArrayList;
class Cola {
    private ArrayList<Cliente> clientesEnCola = new ArrayList<>();

    /**
     * Método que se encarga de agregar un cliente a la lista de clientes de la cola y notificar
     * al barista de que ha llegado un cliente.
     * @param cliente cliente que ha llegado a la cafeteria
     * */
    public synchronized void agregarCliente(Cliente cliente) throws InterruptedException {
        System.out.println(cliente.getNombre() + " ha llegado a la cafetería.\n");
        this.clientesEnCola.add(cliente);
        notifyAll();



    }
    /**
     * Método que se encarga de devolver un cliente y sacarlo de la lista para que el barista
     * lo pueda atender, mientras no haya ningun cliente, el barista esperará.
     * */
    public synchronized Cliente obtenerCliente() throws InterruptedException {
        while (this.clientesEnCola.isEmpty()) {
            wait();
        }
       // System.out.println("emepze");
        Cliente cliente=this.clientesEnCola.get(0);
        this.clientesEnCola.remove(this.clientesEnCola.get(0));
        return cliente;
    }


}
