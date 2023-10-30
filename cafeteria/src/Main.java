import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //lista clientes, baristas.
        Cola cola=new Cola();
        Cliente cliente=new Cliente("prueba",9000,cola,((int) Math.floor(Math.random()*2000)+1));
        Barista barista=new Barista(cola);
        Barista barista2=new Barista(cola);
        Cliente cliente2=new Cliente("prueba5t3",4000,cola,((int) Math.floor(Math.random()*2000)+1));
        Cliente cliente3=new Cliente("prueba2",9000,cola,((int) Math.floor(Math.random()*2000)+1));
        barista.start();
        barista2.start();
        cliente.start();
        cliente2.start();
        cliente3.start();
    }
}