import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Ruleta ruleta = new Ruleta();
        Mesa mesa = new Mesa(ruleta);
        Ia jugador1 = new Ia("calos",100,mesa);
        Ia jugador2 = new Ia("pepe",100,mesa);
        Jugador jugadorNo = new Jugador("CArlos morais",10000,mesa);
        Banca banca = new Banca(999999999,mesa,ruleta);
        ArrayList<Participante> jugadoresOrigi = new ArrayList<>(List.of(jugador1,jugador2,jugadorNo));
        mesa.setJugadoresOriginales(jugadoresOrigi);
        mesa.setBanca(banca);
        banca.start();
        new Thread(jugador1).start();
        new Thread(jugador2).start();
        try {
            jugadorNo.menu();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}