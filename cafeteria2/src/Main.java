import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Cola cola=new Cola();
        ArrayList<Barista>baristas=new ArrayList<>(List.of(
                new Barista(cola,"jose"),
                new Barista(cola,"alberto")
        ));
        ArrayList<Cliente> clientes=new ArrayList<>(List.of(
                new Cliente("Carlos",7000,cola),
                new Cliente("Oscar",1000,cola),
                new Cliente("Pepe",4500,cola),
                new Cliente("Juan",500,cola),
                new Cliente("Alvaro",6000,cola),
                new Cliente("Alvaro2",6000,cola)
        ));
        Barista.modificarVariableEstatica(clientes.size());
        for (Barista barista: baristas){
            barista.start();
        }
        for (Cliente cliente : clientes){
             cliente.start();
        }
    }
}