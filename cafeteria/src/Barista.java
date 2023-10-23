public class Barista extends Thread{
    private Cola cola;

    public Barista(Cola cola) {
        this.cola = cola;
    }
    @Override
    public void run(){
        try {
            this.cola.setCafe();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
