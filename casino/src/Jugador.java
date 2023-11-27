public class Jugador extends Thread{
    private String nombre;
    private Integer dinero;
    private Mesa mesa;
    private boolean ia;
    private boolean haApostado = false;

    public Jugador(String nombre, Integer dinero, Mesa mesa, boolean isIa) {
        this.nombre = nombre;
        this.dinero = dinero;
        this.mesa = mesa;
        this.ia=isIa;
    }

    public void setDinero(Integer dinero) {
        this.dinero = dinero;
    }
    @Override
    public void run(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (true){
            double aleatorio = Math.random();
            if(aleatorio>0.7){
                try {
                    this.mesa.hacerApuesta(this,new Apuesta(2,null,null,1,2,this));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else {
                try {
                    this.mesa.hacerApuesta(this,null);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
    public void menu(){}

    public boolean isHaApostado() {
        return haApostado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setHaApostado(boolean haApostado) {
        this.haApostado = haApostado;
    }
}
