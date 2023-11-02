import java.util.ArrayList;

public class Cola {
    ArrayList<Cliente> clientes = new ArrayList<>();
    /**
     * Método que se encarga de usar el cliente para indicar a la cola que llego a la cafeteria, este
     * se mete en la cola, le indica a los baristas que llego y se pone a esperar el cafe, una vez acabado
     * de esperar, comprueba si ha recibido el cafe o no.
     * */
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
            System.out.println(cliente.getNombre()+" recibio el cafe en "+(System.currentTimeMillis()-cliente.getHoraLlegada())/1000+" segundos");
        }else {
            System.out.println(cliente.getNombre()+" no recibio el cafe a tiempo y se fue");
        }

    }
    /**
     * Método que se encarga de devolver un cliente para que el barista lo atienda, mientras no haya, espera,
     * luego devuelve el cliente y lo saca de la cola.
     * */
    public synchronized Cliente obtenerCliente() throws InterruptedException {
        while (clientes.isEmpty()){
            wait();
        }
        Cliente cliente=this.clientes.get(0);
        this.clientes.remove(this.clientes.get(0));
        return cliente;
    }
    /**
     * Método que se encarga de avisar a los clientes cuando ya esten listos
     * para irse de la cafetería.
     * */
    public synchronized void avisarCliente(){
        notifyAll();
    }

}
