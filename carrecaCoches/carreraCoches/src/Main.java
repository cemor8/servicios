import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int distanciaCarrera=500;
        ArrayList<Thread> cochesParticipante = new ArrayList<>(List.of(new Coche(220,"Coche2",0.0,distanciaCarrera,3),
                new Coche(220,"Coche3",0.0,distanciaCarrera,3),
                new Coche(220,"Coche1",0.0,distanciaCarrera,3)));
        for(int i=0;i<cochesParticipante.size();i++){
            cochesParticipante.get(i).start();
        }
    }
}