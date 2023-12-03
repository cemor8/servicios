import java.util.ArrayList;
import java.util.List;

public class Ia extends Participante implements Runnable{
    private int dineroAcumulado;
    private ArrayList<Apuesta> apuestas = new ArrayList<>();
    private Mesa mesa;
    public Ia(String nombre, Integer dineroInicial, Mesa mesa) {
        super(nombre, dineroInicial);
        this.dineroAcumulado = dineroInicial;
        this.mesa = mesa;
    }
    @Override
    public void run() {

        while (this.dineroAcumulado>50 && this.dineroAcumulado < this.dineroInicial*4 && this.mesa.isSeJuega()) {
            if(!this.mesa.isSePuedeApostar()){
                continue;
            }
            int numeroAleatorio = (int) (Math.random() * 16) + 7;
            numeroAleatorio*=1000;
            /*
            try {
                Thread.sleep(numeroAleatorio);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

             */
            System.out.println(this.nombre+" dinero: "+this.dineroAcumulado);
            this.apuestas = new ArrayList<>();
            double aleatorio = Math.random();
            if (aleatorio < 0.7) {
                ArrayList<Apuesta> apuestas = new ArrayList<>();
                aleatorio = (int) (Math.random() * 37);
                if (aleatorio!= 0 ){
                    this.hacerApuesta("numero",String.valueOf(aleatorio),apuestas);
                }
                this.hacerApuesta("posicion","par",apuestas);
                this.hacerApuesta("posicion","impar",apuestas);
                this.hacerApuesta("color","rojo",apuestas);
                this.hacerApuesta("color","negro",apuestas);
                this.hacerApuesta("bloque","1",apuestas);
                this.hacerApuesta("bloque","2",apuestas);
                try {
                    this.mesa.hacerApuesta(this,apuestas);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    this.mesa.hacerApuesta(this, null);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println(this.nombre+" dinero: "+this.dineroAcumulado);
        System.out.println(this.nombre+" acabo de apostar en la rule");
        this.mesa.eliminarOriginal(this);
    }
    private void hacerApuesta(String propieadApostar , String valorApostar, ArrayList<Apuesta> apuestas){
        ArrayList<Integer> posiblesCantidades= new ArrayList<>(List.of(25,50,100,150,200,250,400,500,1000));
        if(this.dineroAcumulado<=0){
            return;
        }
        ArrayList<Integer> posibles = new ArrayList<>();
        for(Integer cantidad: posiblesCantidades){
            if(cantidad<=this.dineroAcumulado){
                posibles.add(cantidad);
            }
        }
        if(posibles.isEmpty()){
            return;
        }
        double aleatorio = Math.random();
        if(aleatorio<0.5){
            int posicion = (int) Math.floor(Math.random() * posibles.size());
            int precio = posibles.get(posicion);
            System.out.println("\n"+this.nombre+" apuesta al "+valorApostar+", "+precio+", euros \n");

            apuestas.add(new Apuesta(propieadApostar,valorApostar,precio,this));
            this.dineroAcumulado-=precio;
        }
    }

}
