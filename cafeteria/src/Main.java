import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Cola cola=new Cola(null);
        Barista barista=new Barista(cola);

        for(int i = 0;i<4;i++){
            Cliente cliente= new Cliente("cliete "+i,i+5,cola);
            cola.setCliente(cliente);
            barista.start();
            cliente.start();

        }
    }
}