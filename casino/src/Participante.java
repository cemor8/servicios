import java.util.ArrayList;
import java.util.Scanner;

public class Participante {
    protected String nombre;
    protected Integer dineroInicial;
    protected Integer dineroAcumulado;
    private boolean haApostado = false;

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


    public void setDineroAcumulado(Integer dineroAcumulado) {
        this.dineroAcumulado = dineroAcumulado;
    }
}
