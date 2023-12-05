import java.util.ArrayList;

public class Mesa {
    private ArrayList<Apuesta> apuestasRealizadas = new ArrayList<>();
    private ArrayList<Participante> jugadoresOriginales = new ArrayList<>();
    Ruleta ruleta;
    private boolean sePuedeApostar = true;
    private ArrayList<Participante> jugadoresEnMesa = new ArrayList<>();
    private Banca banca;
    private boolean seJuega = true;

    public Mesa(Ruleta ruleta) {
        this.ruleta=ruleta;
    }
    /**
     * Método que usa la banca para tirar de la ruleta y comprobar las apuestas
     * */
    public synchronized void girarRuleta() throws InterruptedException {
        this.sePuedeApostar=false;
        System.out.println("\n ------Tirando ruleta------ \n");
        Thread.sleep(4000);
        Numero numero = banca.getRuleta().tirarRule();
        System.out.println("\n ------Numero ganador------");
        System.out.println(numero + "\n");
        this.banca.comprobarApuestas(this.apuestasRealizadas,numero);
        this.apuestasRealizadas = new ArrayList<>();

        int i = 0;
        while (i < this.jugadoresEnMesa.size()) {

            this.jugadoresEnMesa.get(i).setHaApostado(false);
            this.jugadoresEnMesa.remove(i);
        }
        this.sePuedeApostar=true;
        notifyAll();
    }
    /*
     * Método que llama la ia para realizar una apuesta
     *
     * */
    public synchronized void hacerApuesta(Participante participante,ArrayList<Apuesta> apuestas) throws InterruptedException {
        participante.setHaApostado(true);
        jugadoresEnMesa.add(participante);

        if(apuestas!=null){
            this.apuestasRealizadas.addAll(apuestas);
        }else{
            System.out.println("El jugador "+participante.getNombre()+" pasa");
        }

        while (participante.isHaApostado()){
            wait();
        }

    }
    /*
    * Método que llama el jugador por terminal para introducir una apuesta
    *
    * */
    public void apostar(Participante participante, ArrayList<Apuesta> apuestas){
        if (!this.sePuedeApostar){
            System.out.println(participante.getNombre()+" no le dio tiempo");
            return;
        }
        participante.setHaApostado(true);
        jugadoresEnMesa.add(participante);
        if(apuestas!=null){
            this.apuestasRealizadas.addAll(apuestas);
        }else{
            System.out.println("El jugador "+participante.getNombre()+" pasa");
        }
    }

    public ArrayList<Participante> getJugadoresOriginales() {
        return jugadoresOriginales;
    }

    public boolean isSeJuega() {
        return seJuega;
    }

    public void setSeJuega(boolean seJuega) {
        this.seJuega = seJuega;
    }

    public void setJugadoresOriginales(ArrayList<Participante> jugadoresOriginales) {
        this.jugadoresOriginales = jugadoresOriginales;
    }
    public void eliminarOriginal(Participante participante){
        this.jugadoresOriginales.remove(participante);
    }

    public void setBanca(Banca banca) {
        this.banca = banca;
    }

    public boolean isSePuedeApostar() {
        return sePuedeApostar;
    }
}
