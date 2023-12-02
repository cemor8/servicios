import java.util.ArrayList;
import java.util.Scanner;

public class Participante {
    protected String nombre;
    protected Integer dineroInicial;
    protected Integer dineroAcumulado;
    private Mesa mesa;
    private boolean haApostado = false;
    private ArrayList<Apuesta> apuestas = new ArrayList<>();

    public Participante(String nombre, Integer dineroInicial) {
        this.nombre = nombre;
        this.dineroInicial = dineroInicial;
        this.dineroAcumulado = dineroInicial;
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

    public boolean isHaApostado() {
        return haApostado;
    }
    public void sumarDinero(int dinero){
        System.out.println(this.getNombre()+" gana "+dinero);
        this.dineroAcumulado += dinero;
    }
    public void restarDinero(int dinero){
        this.dineroAcumulado -=dinero;
    }

    public void setDineroAcumulado(Integer dineroAcumulado) {
        this.dineroAcumulado = dineroAcumulado;
    }
}
