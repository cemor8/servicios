public class Main {
    public static void main(String[] args) throws InterruptedException {
        Cola cola=new Cola();
        Cliente cliente=new Cliente("Carlos",7000,cola);
        Barista barista=new Barista(cola,"jose");
        Barista barista2=new Barista(cola,"alberto");
        Cliente cliente2=new Cliente("Oscar",0,cola);
        Cliente cliente3=new Cliente("Pepe",9000,cola);
        Cliente cliente4=new Cliente("Juan",1,cola);
        Cliente cliente5=new Cliente("Alvaro",9000,cola);
        barista.start();
        barista2.start();
        cliente.start();
        cliente2.start();
        Thread.sleep((int) Math.floor(Math.random()*2000)+1);
        cliente3.start();
        Thread.sleep((int) Math.floor(Math.random()*2000)+1);
        cliente4.start();
        Thread.sleep((int) Math.floor(Math.random()*2000)+1);
        cliente5.start();

    }
}