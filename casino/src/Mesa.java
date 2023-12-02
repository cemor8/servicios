import java.util.ArrayList;

public class Mesa {
    private ArrayList<Apuesta> apuestasRealizadas = new ArrayList<>();
    private ArrayList<Jugador> jugadoresOriginales = new ArrayList<>();
    Ruleta ruleta;
    private boolean sePuedeApostar = true;
    private ArrayList<Participante> jugadoresEnMesa = new ArrayList<>();
    private Jugador jugadorNoIa;
    private Banca banca;
    private boolean seJuega = true;

    public Mesa(Ruleta ruleta) {
        this.ruleta=ruleta;
    }

    public synchronized void girarRuleta() throws InterruptedException {
        this.sePuedeApostar=false;
        System.out.println("Tirando ruleta");
        Numero numero = banca.getRuleta().tirarRule();

        this.banca.comprobarApuestas(this.apuestasRealizadas,numero);
        this.apuestasRealizadas = new ArrayList<>();

        int i = 0;
        while (i < this.jugadoresEnMesa.size()) {
            this.jugadoresEnMesa.get(i).setHaApostado(false);
            /*
            System.out.println("\nJugador : "+jugadoresEnMesa.get(i).getNombre());
            System.out.println("\t Dinero : "+jugadoresEnMesa.get(i).getDineroAcumulado());

             */
            this.jugadoresEnMesa.remove(i);

        }
        this.sePuedeApostar=true;
        //this.jugadorNoIa.menu();
        this.jugadorNoIa.setHaApostado(false);
        notifyAll();
    }
    public synchronized void hacerApuesta(Participante participante,ArrayList<Apuesta> apuestas) throws InterruptedException {
        while (!this.sePuedeApostar){
            if(participante instanceof Jugador){
                ((Jugador) participante).getScanner().close();
                System.out.println(participante.getNombre()+" no le dio tiempo");
                return;
            }
            System.out.println(participante.getNombre()+" no llego a tiempo y espera");
            wait();
        }
        participante.setHaApostado(true);
        jugadoresEnMesa.add(participante);
        if(apuestas!=null){
            this.apuestasRealizadas.addAll(apuestas);
        }else{
            System.out.println("El jugador "+participante.getNombre()+" pasa");
        }
        while (participante.isHaApostado() && participante instanceof Ia){
            wait();
        }

    }

    public void setJugadorNoIa(Jugador jugadorNoIa) {
        this.jugadorNoIa = jugadorNoIa;
    }
    // banca tiene que ser variable privada y al meter una apuesta comprobar si la puede hacer, si no , no la acepta
    // al hacer apuesta quitar directamente dinero de jugador, luego no restar, solo sumar si gana
    //preguntar scanner, como hacerlo para que lo pueda cerrar.
    //como hacer para que si no hace nada, cierre el scanner y luego lo vuelva a abrir
    //otra variable para banca comprobar dinero, que se reinicie en cada apuesta y sea el dinero que tiene, luego le vas restando a esa variable.

    public ArrayList<Jugador> getJugadoresOriginales() {
        return jugadoresOriginales;
    }

    public boolean isSeJuega() {
        return seJuega;
    }

    public void setSeJuega(boolean seJuega) {
        this.seJuega = seJuega;
    }
}
