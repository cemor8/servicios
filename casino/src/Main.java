// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Ruleta ruleta = new Ruleta();
        Mesa mesa = new Mesa(ruleta);
        Jugador jugador1 = new Jugador("calos",1000000,mesa,true);
        Jugador jugador2 = new Jugador("pepe",1000000,mesa,true);
        Jugador jugador3 = new Jugador("albeer",1000000,mesa,true);
        Jugador jugador4 = new Jugador("uda",1000000,mesa,true);
        Jugador jugador5 = new Jugador("juan",2000000,mesa,false);
        Banca banca = new Banca(2,mesa,ruleta);
        banca.start();
        jugador1.start();
        jugador2.start();
        jugador3.start();
        jugador4.start();

    }
}