public class Consumidor extends Thread{

private Cola cola;

    public Consumidor(Cola cola) {
        this.cola = cola;
    }

    @Override
    public void run(){
        while (true){
            try {
                cola.tic("tic");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
