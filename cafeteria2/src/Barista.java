public class Barista extends Thread {
    private static int cantidad= 0;
    private Cola cola;
    private String nombre;

    public Barista(Cola cola, String nombre) {
        this.cola = cola;
        this.nombre = nombre;
    }
    /**
     * Método que se encarga de que los baristas produzcan el café
     * para los clientes que lleguen a la tienda.
     * */
    @Override
    public void run(){
        while (cantidad>1){
            try {
               Cliente cliente=this.cola.obtenerCliente();
               this.preprararCafe(cliente);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            modificarVariableEstatica(cantidad-=1);
        }

    }
    /**
     * Método que simula la preparacion del cafe, comprueba que el cliente tenga tiempo para recibirlo,
     * si no lo tiene le dice que se vaya y si lo tiene, lo prepara y luego se lo manda.
     * */
    public void preprararCafe(Cliente cliente) throws InterruptedException {
        System.out.println("Se empieza a preparar cafe para "+cliente.getNombre()+" por el barista "+this.nombre);
        int timepo_realizacion=4000;
        if((System.currentTimeMillis()- cliente.getHoraLlegada())+timepo_realizacion>cliente.getTiempoEspera()){
            cliente.setListo(true);
            this.cola.avisarCliente();
        }else {
            Thread.sleep(timepo_realizacion);
            cliente.setListo(true);
            cliente.setHaRecibidoCafe();
            this.cola.avisarCliente();
        }

    }

    public static void modificarVariableEstatica(int nuevoValor) {
        cantidad = nuevoValor;
    }
}
