import java.util.ArrayList;

public class Cola {
    ArrayList<Cliente> clientes = new ArrayList<>();

    public synchronized void meterCliente(Cliente cliente) throws InterruptedException {
        System.out.println(cliente.getNombre()+" llego a la cola ");
        long tiempoInicio = System.currentTimeMillis();
        this.clientes.add(cliente);
        notifyAll();
        int segundosEsperar = cliente.getTiempoEspera()/1000;

        for (int i = 0; i < segundosEsperar; i++) {

            Thread.sleep(1000);

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
        System.out.println("Se empieza a preparar cafe para "+cliente.getNombre());
        Thread.sleep(4000);
        System.out.println("cafe preparado para "+cliente.getNombre());
        cliente.setHaRecibidoCafe();
        notifyAll();
        return cliente;


    }

}
