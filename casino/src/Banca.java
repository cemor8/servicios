public class Banca extends Thread{
    private Integer dinero;
    private Mesa mesa;
    private Ruleta ruleta;

    public Banca(Integer dinero, Mesa mesa, Ruleta ruleta) {
        this.dinero = dinero;
        this.mesa = mesa;
        this.ruleta = ruleta;
    }
    @Override
    public void run(){
        System.out.println("holita");
        while (true) {
            try {
                Thread.sleep(1);
            }catch (InterruptedException err){
                System.out.println(err.getMessage());
            }
            try {
                this.mesa.girarRuleta(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }



    }

    public Integer getDinero() {
        return dinero;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Ruleta getRuleta() {
        return ruleta;
    }
}
