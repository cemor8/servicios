import java.util.ArrayList;
class Cola {
    private ArrayList<Cliente> clientesEnCola = new ArrayList<>();


    public synchronized void agregarCliente(Cliente cliente) {
        this.clientesEnCola.add(cliente);
        notifyAll();
    }

    public synchronized Cliente obtenerCliente() throws InterruptedException {
        while (this.clientesEnCola.isEmpty()) {
            wait();
        }
        Cliente cliente=this.clientesEnCola.get(0);
        this.clientesEnCola.remove(this.clientesEnCola.get(0));
        return cliente;
    }

}
