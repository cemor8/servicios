// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Ruleta ruleta = new Ruleta();
        Mesa mesa = new Mesa(ruleta);
        Jugador jugador1 = new Jugador("calos",2,mesa,false);
        Jugador jugador2 = new Jugador("pepe",2,mesa,false);
        Jugador jugador3 = new Jugador("albeer",2,mesa,false);
        Jugador jugador4 = new Jugador("uda",2,mesa,false);
        Jugador jugador5 = new Jugador("juan",2,mesa,false);
        Banca banca = new Banca(2,mesa,ruleta);
        banca.start();
        jugador1.start();
        jugador2.start();
        jugador3.start();
        jugador4.start();
    }
}