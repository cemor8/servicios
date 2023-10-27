public class Cola {
    private boolean listo=false;
    private Cliente cliente;

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


    }
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
        }
        this.listo=false;
        this.cliente=null;
        notifyAll();


    }

}
