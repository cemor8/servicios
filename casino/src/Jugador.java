public class Jugador extends Thread{
    private String nombre;
    private Integer dinero;
    private Mesa mesa;
    private boolean ia;

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
        if(ia){

        }else{

        }
    }
    public void menu(){}
}
