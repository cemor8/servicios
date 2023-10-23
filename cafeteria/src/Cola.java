public class Cola {
    private boolean listo=false;
    private Cliente cliente;

    public Cola(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public synchronized void setCafe() throws InterruptedException {
        while (this.listo){
            wait();
        }
        Thread.sleep(5000);
        this.listo=true;
        notifyAll();

    }
    public synchronized Cliente getCafe() throws InterruptedException {
        int tiempoEsperado=0;

        while (!this.listo){
            System.out.println(tiempoEsperado);
            tiempoEsperado++;
            if (tiempoEsperado==this.cliente.getTiempoEspera()){
                this.cliente=null;
                this.listo=false;
                notifyAll();
                return null;
            }
        }
        this.listo=false;
        notifyAll();
        Cliente clienteDevolver=this.cliente;
        this.cliente=null;
        return clienteDevolver;

    }

}
