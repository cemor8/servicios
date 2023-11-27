import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Jugador extends Thread {
    private String nombre;
    private Integer dineroInicial;
    private Integer dineroAcumulado;
    private Mesa mesa;
    private boolean ia;
    private boolean haApostado = false;

    public Jugador(String nombre, Integer dinero, Mesa mesa, boolean isIa) {
        this.nombre = nombre;
        this.dineroInicial = dinero;
        this.mesa = mesa;
        this.ia = isIa;
        this.dineroAcumulado = dinero;
    }

    public void setDineroAcumulado(Integer dinero) {
        this.dineroAcumulado = dinero;
    }

    @Override
    public void run() {
            while (this.dineroAcumulado>50 && this.dineroAcumulado< this.dineroInicial*4) {
                double aleatorio = Math.random();
                if (aleatorio < 0.7) {
                    ArrayList<Apuesta> apuestas = new ArrayList<>();
                    aleatorio = (int) (Math.random() * 37);
                    if (aleatorio!= 0 ){
                        this.hacerApuesta("numero",String.valueOf(aleatorio),apuestas);
                    }
                    this.hacerApuesta("posicion","par",apuestas);
                    this.hacerApuesta("posicion","impar",apuestas);
                    this.hacerApuesta("color","rojo",apuestas);
                    this.hacerApuesta("color","negro",apuestas);
                    this.hacerApuesta("bloque","1",apuestas);
                    this.hacerApuesta("bloque","2",apuestas);
                    try {
                        this.mesa.hacerApuesta(this,apuestas);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        this.mesa.hacerApuesta(this, null);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }

    }
    private void hacerApuesta(String propieadApostar , String valorApostar, ArrayList<Apuesta> apuestas){
        double aleatorio = Math.random();
        if(aleatorio<0.5){
            int precio = (int) Math.floor(Math.random() * this.dineroAcumulado) + 10;
            System.out.println("\n"+this.getNombre()+" apuesta al "+valorApostar+", "+precio+", euros");
            apuestas.add(new Apuesta(propieadApostar,valorApostar,precio,this));
        }
    }

    public void menu() {
    }

    public boolean isHaApostado() {
        return haApostado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setHaApostado(boolean haApostado) {
        this.haApostado = haApostado;
    }

    public Integer getDineroAcumulado() {
        return dineroAcumulado;
    }
    public void sumarDinero(Integer dinero){
        this.dineroAcumulado+=dinero;
    }
    public void restarDinero(Integer dinero){
        this.dineroAcumulado-=dinero;
    }
}
