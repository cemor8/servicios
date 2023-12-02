import java.util.ArrayList;

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
    }
    private void hacerApuesta(String propieadApostar , String valorApostar, ArrayList<Apuesta> apuestas){
        if(this.dineroAcumulado<=0){
            return;
        }
        double aleatorio = Math.random();
        if(aleatorio<0.5){
            int precio = (int) Math.floor(Math.random() * this.dineroAcumulado) + 10;

            System.out.println("\n"+this.nombre+" apuesta al "+valorApostar+", "+precio+", euros");

            apuestas.add(new Apuesta(propieadApostar,valorApostar,precio,this));
            this.dineroAcumulado-=precio;
        }
    }
}
