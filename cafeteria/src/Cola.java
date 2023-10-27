public class Cola {
    private boolean listo=false;
    private Cliente cliente;

    public synchronized void setCafe() throws InterruptedException {
            while (this.listo ||this.cliente==null){
                wait();
            }
            System.out.println("Preparando café...");
            Thread.sleep(5000);
            this.listo=true;
            notifyAll();

    }
    public synchronized void getCafe(Cliente cliente) throws InterruptedException {
        double segundos_iniciar= System.currentTimeMillis();
        this.cliente=cliente;
        this.listo=false;
        notifyAll();
        wait(this.cliente.getTiempoEspera());
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
