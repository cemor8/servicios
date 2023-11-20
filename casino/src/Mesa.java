import java.util.ArrayList;

public class Mesa {
    private ArrayList<Apuesta> apuestasRealizadas;
    private ArrayList<Jugador> jugadoresEnMesa;
    Ruleta ruleta = new Ruleta();

    public synchronized void girarRuleta(Banca banca){


    }
    public void hacerApuesta(Apuesta apuesta){
        this.apuestasRealizadas.add(apuesta);
    }
}
