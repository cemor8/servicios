import java.util.ArrayList;

public class Cola {
    ArrayList<Cliente> clientes = new ArrayList<>();

    public synchronized void meterCliente(Cliente cliente) throws InterruptedException {
        System.out.println(cliente.getNombre()+" llego a la cola ");
        long tiempoInicio = System.currentTimeMillis();
        cliente.setHoraLlegada(tiempoInicio);
        this.clientes.add(cliente);
        notifyAll();
        while (!cliente.isListo()){
            wait();
        }
        if (cliente.isHaRecibidoCafe()){
            System.out.println(cliente.getNombre()+" recibio el cafe en "+(System.currentTimeMillis()-tiempoInicio)/1000+" segundos");
        }else {
            System.out.println(cliente.getNombre()+" no recibio el cafe a tiempo y se fue");
        }

    }
    public synchronized Cliente obtenerCliente() throws InterruptedException {
        while (clientes.isEmpty()){
            wait();
        }
        Cliente cliente=this.clientes.get(0);
        this.clientes.remove(this.clientes.get(0));
        return cliente;
    }
    public synchronized void avisarCliente(){
        notifyAll();
    }

}
