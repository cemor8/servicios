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
            /*
            System.out.println("\nJugador : "+jugadoresEnMesa.get(i).getNombre());
            System.out.println("\t Dinero : "+jugadoresEnMesa.get(i).getDineroAcumulado());

             */
            this.jugadoresEnMesa.remove(i);

        }
        this.sePuedeApostar=true;
        notifyAll();
    }
    /*
     * Método que llama la ia para realizar una apuesta
     * */
    public synchronized void hacerApuesta(Participante participante,ArrayList<Apuesta> apuestas) throws InterruptedException {
        /*
        while (!this.sePuedeApostar){
            System.out.println(participante.getNombre()+" no llego a tiempo y espera");
            wait();
        }

         */

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

    // banca tiene que ser variable privada y al meter una apuesta comprobar si la puede hacer, si no , no la acepta
    // al hacer apuesta quitar directamente dinero de jugador, luego no restar, solo sumar si gana
    //preguntar scanner, como hacerlo para que lo pueda cerrar.
    //como hacer para que si no hace nada, cierre el scanner y luego lo vuelva a abrir
    //otra variable para banca comprobar dinero, que se reinicie en cada apuesta y sea el dinero que tiene, luego le vas restando a esa variable.

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
