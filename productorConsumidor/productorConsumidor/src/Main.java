// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Cola cola=new Cola();
       Productor productor=new Productor(cola);
       Consumidor consumidor=new Consumidor(cola);
       productor.start();
       consumidor.start();
    }
}