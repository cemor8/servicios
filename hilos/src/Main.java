public class Main {
    public static void main(String[] args) throws InterruptedException {


        for (int i = 1; i <= 3; i++) {
            Saludo saludo=new Saludo("hilo "+i,i);
            saludo.start();
        }
        // thread.activecount devuelve los hilos que se estan ejecutando en el momento de ejecutar la funcion.
        System.out.println("Se han creado los 3 hilos "+Thread.activeCount()+" hilos ejecutandose");
    }
}