import java.util.ArrayList;

<<<<<<< HEAD
class Cola {
    private ArrayList<Cliente> clientesEnCola = new ArrayList<>();
=======
    public synchronized void setCafe() throws InterruptedException {

            while (this.listo){
                wait();
            }
            System.out.println("el barista empieza a preparar");
            System.out.println("Preparando café...");
            Thread.sleep(5000);
            this.listo=true;
            notifyAll();
            System.out.println("se acabo el cafe");

>>>>>>> b6d93b077db51c53fa5d38277e12b2774bf961b0

    public synchronized void agregarCliente(Cliente cliente) {
        this.clientesEnCola.add(cliente);
        notifyAll();
    }
<<<<<<< HEAD

    public synchronized Cliente obtenerCliente() throws InterruptedException {
        while (this.clientesEnCola.isEmpty()) {
            wait();
=======
    public synchronized void getCafe(Cliente cliente) throws InterruptedException {
        this.cliente=cliente;
        System.out.println("entra el cliente "+this.cliente.getNombre());
        double segundos_iniciar= System.currentTimeMillis();


        System.out.println("se llama al barista");
        notifyAll();
        System.out.println("el cliente empieza a esperar");
        while (!this.listo){
            wait();
        }

        System.out.println("se paso el cliente"+this.cliente.getNombre());
        //wait(this.cliente.getTiempoEspera());

        if(!this.listo){
            System.out.println("el cliente "+cliente.getNombre()+" se va sin su cafe");
        }else {
            System.out.println("El cliente "+this.cliente.getNombre()+" ha conseguido su cafe en "+((System.currentTimeMillis()-segundos_iniciar)/1000)+" segundos");
>>>>>>> b6d93b077db51c53fa5d38277e12b2774bf961b0
        }
        Cliente cliente=this.clientesEnCola.get(0);
        this.clientesEnCola.remove(this.clientesEnCola.get(0));
        return cliente;
    }

}
