import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int distanciaCarrera=600;
        ArrayList<Coche> cochesParticipante = new ArrayList<>(List.of(new Coche(220,"Coche2",0.0,distanciaCarrera),
                new Coche(220,"Coche3",0.0,distanciaCarrera),
                new Coche(220,"Coche1",0.0,distanciaCarrera)));
        for (Coche coche : cochesParticipante) {
            coche.start();
        }

    }
}