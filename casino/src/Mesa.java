import java.util.ArrayList;

public class Mesa {
    private ArrayList<Apuesta> apuestasRealizadas = new ArrayList<>();
    Ruleta ruleta;
    private boolean comprobando = false;
    private boolean sePuedeApostar = true;
    private boolean nuevaApuesta = false;
    private ArrayList<Jugador> jugadoresEnMesa = new ArrayList<>();

    public Mesa(Ruleta ruleta) {
        this.ruleta=ruleta;
    }

    public void girarRuleta(Banca banca) throws InterruptedException {
        this.sePuedeApostar=false;
        System.out.println("no se puede apostar mas");
        Thread.sleep(5000);
        System.out.println("Comprobando apuestas");
        int i = 0;
        while (i < this.jugadoresEnMesa.size()) {
            this.jugadoresEnMesa.get(i).setHaApostado(false);
            this.jugadoresEnMesa.remove(i);

        }
        System.out.println("acabado comprobar");
        this.sePuedeApostar=true;
        this.despertar();

    }
    public synchronized void hacerApuesta(Jugador jugador,Apuesta apuesta) throws InterruptedException {
        while (!this.sePuedeApostar){
            System.out.println(jugador.getNombre()+" no llego a tiempo y espera");
            wait();
        }

        if(apuesta!=null){
            System.out.println(jugador.getNombre()+" apuesta");
            this.apuestasRealizadas.add(apuesta);
        }else{
            System.out.println("El jugador "+jugador.getNombre()+" pasa");
        }
        jugador.setHaApostado(true);
        jugadoresEnMesa.add(jugador);

        while (jugador.isHaApostado()){
            System.out.println(jugador.getNombre()+" se pone a esperar");
            wait();
        }

    }
    public synchronized void despertar(){
        notifyAll();
    }
}
