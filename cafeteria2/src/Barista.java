public class Barista extends Thread {
    private static int cantidad= 0;
    private Cola cola;
    private String nombre;

    public Barista(Cola cola, String nombre) {
        this.cola = cola;
        this.nombre = nombre;
    }
    @Override
    public void run(){
        while (cantidad>0){
            try {
               Cliente cliente=this.cola.obtenerCliente();
               this.preprararCafe(cliente);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            modificarVariableEstatica(cantidad-=1);
        }
    }
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
