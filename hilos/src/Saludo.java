public class Saludo extends Thread {

    public Saludo(String nombre,int priority) {
        this.setName(nombre);
        this.setPriority(priority);
    }

    @Override
    public void run() {
        System.out.println("Dentro del "+this.getName());
        System.out.println("Prioridad: "+this.getPriority());
        System.out.println("ID: "+this.getId());
        System.out.println(Thread.activeCount()+" hilos activos");

    }

}
