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
        try {
            Thread.sleep(20000);
        }catch (InterruptedException err){
            System.out.println(err.getMessage());
        }
        this.mesa.girarRuleta(this);

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
