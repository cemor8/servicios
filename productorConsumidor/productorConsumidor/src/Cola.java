public class Cola extends Thread {
    private String texto="";
    private boolean ready=false;

    public Cola() {

    }
    public synchronized void tic(String textoIntroducir) throws InterruptedException {
        while (!ready){
            wait();
        }
        this.texto=textoIntroducir;
        System.out.println(this.texto);
        this.ready=false;
        notifyAll();
    }
    public synchronized void tac(String textoIntroducir) throws InterruptedException {
        while (ready){
            wait();
        }
        this.texto=textoIntroducir;
        System.out.println(this.texto);
        this.ready=true;
        notifyAll();
    }
}
