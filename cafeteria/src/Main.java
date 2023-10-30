
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Cola cola=new Cola();
        Cliente cliente=new Cliente("Carlos",9000,cola,((int) Math.floor(Math.random()*2000)+1));
        Barista barista=new Barista(cola,"jose");
        Barista barista2=new Barista(cola,"alberto");
        Cliente cliente2=new Cliente("Oscar",4000,cola,((int) Math.floor(Math.random()*2000)+1));
        Cliente cliente3=new Cliente("Pepe",9000,cola,((int) Math.floor(Math.random()*2000)+1));
        Cliente cliente4=new Cliente("Juan",2000,cola,((int) Math.floor(Math.random()*2000)+1));
        Cliente cliente5=new Cliente("Alvaro",4000,cola,((int) Math.floor(Math.random()*2000)+1));
        barista.start();
        barista2.start();
        cliente.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();
        cliente5.start();
    }
}