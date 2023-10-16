public class Productor extends Thread{
    private Cola cola;

    public Productor(Cola cola) {
        this.cola = cola;
    }

    @Override
    public void run(){
        while (true){
            try {
                cola.tac("tac");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
