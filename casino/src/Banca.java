import java.util.ArrayList;

public class Banca extends Thread{
    private Integer dinero;
    private Mesa mesa;
    private Ruleta ruleta;
    private int dineroComprobar;

    public Banca(Integer dinero, Mesa mesa, Ruleta ruleta) {
        this.dinero = dinero;
        this.mesa = mesa;
        this.ruleta = ruleta;
    }
    @Override
    public void run(){
        while (!this.mesa.getJugadoresOriginales().isEmpty() && this.dinero>100) {
            try {
                Thread.sleep(20000);
            }catch (InterruptedException err){
                System.out.println(err.getMessage());
            }
            if(this.mesa.getJugadoresOriginales().isEmpty() || this.dinero<100){
                break;
            }
            try {
                this.mesa.girarRuleta();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.mesa.setSeJuega(false);
    }
    /**
     * Método que se encarga de comprobar las apuestas
     * */
    public void comprobarApuestas(ArrayList<Apuesta> apuestas, Numero numeroGanador){
        this.dineroComprobar = this.dinero;
        this.validarDineroApuesta(apuestas,this.dineroComprobar);

        if(numeroGanador.getNumero() == 0){

            int cantidad_ganada = 0;
            int i = 0;

            while (i<apuestas.size()){

                cantidad_ganada+=apuestas.get(i).getPrecio();
                apuestas.get(i).getParticipante().setDineroAcumulado(
                        apuestas.get(i).getParticipante().getDineroAcumulado() - apuestas.get(i).getPrecio()
                );
                apuestas.remove(i);

            }

            this.sumarDinero(cantidad_ganada);
            System.out.println("Salio 0, gana la banca");

        }else {
            for(Apuesta apuesta : apuestas){
                switch (apuesta.getPropiedadApostada()){
                    case "color", "numero":

                        if(numeroGanador.getColor().equalsIgnoreCase(apuesta.getValor())){

                            this.restarDinero(apuesta.getPrecio());

                            if(apuesta.getPropiedadApostada().equalsIgnoreCase("numero")){

                                if(apuesta.getParticipante() instanceof Jugador){

                                    Jugador jugador = (Jugador) apuesta.getParticipante();
                                    jugador.sumar(apuesta.getPrecio() * 36);
                                    System.out.println(apuesta.getParticipante().getNombre()+" gana "+apuesta.getPrecio() * 36+ " apostando a numero");
                                    continue;

                                }else {
                                    apuesta.getParticipante().sumarDinero(apuesta.getPrecio() * 36);
                                }
                                System.out.println(apuesta.getParticipante().getNombre()+" gana "+apuesta.getPrecio() * 36+ " apostando a numero");
                            }else {

                                if(apuesta.getParticipante() instanceof Jugador){

                                    Jugador jugador = (Jugador) apuesta.getParticipante();
                                    jugador.sumar(apuesta.getPrecio() * 2);
                                    System.out.println(apuesta.getParticipante().getNombre()+" gana "+apuesta.getPrecio() * 2+ " apostando a color");
                                    continue;

                                }else {
                                    apuesta.getParticipante().sumarDinero(apuesta.getPrecio() * 2);
                                }

                                System.out.println(apuesta.getParticipante().getNombre()+" gana "+apuesta.getPrecio() * 2+ " apostando a color");
                            }
                        }else {

                            System.out.println(apuesta.getParticipante().getNombre()+" pierde "+apuesta.getPrecio());
                            this.sumarDinero(apuesta.getPrecio());
                        }
                        break;

                    case "posicion":
                        String posicion = null;

                        if(numeroGanador.getNumero()%2 == 0){
                            posicion = "par";
                        }else{
                            posicion = "impar";
                        }

                        if(posicion.equalsIgnoreCase(apuesta.getValor())){

                            this.restarDinero(apuesta.getPrecio());

                            if(apuesta.getParticipante() instanceof Jugador){
                                Jugador jugador = (Jugador) apuesta.getParticipante();
                                jugador.sumar(apuesta.getPrecio() * 2);
                                System.out.println(apuesta.getParticipante().getNombre()+" gana "+apuesta.getPrecio() * 2+ " apostando a posicion");
                                continue;
                            }else {
                                apuesta.getParticipante().sumarDinero(apuesta.getPrecio() * 2);
                            }

                            System.out.println(apuesta.getParticipante().getNombre()+" gana "+apuesta.getPrecio() * 2+ " apostando a posicion");

                        }else {
                            System.out.println(apuesta.getParticipante().getNombre()+" pierde "+apuesta.getPrecio());
                            this.sumarDinero(apuesta.getPrecio());
                        }
                        break;

                    case "bloque":
                        String bloque = null;

                        if(numeroGanador.getNumero()<18){
                            bloque = "1";
                        }else{
                            bloque = "2";
                        }

                        if(bloque.equalsIgnoreCase(apuesta.getValor())){

                            this.restarDinero(apuesta.getPrecio());

                            if(apuesta.getParticipante() instanceof Jugador){
                                Jugador jugador = (Jugador) apuesta.getParticipante();
                                jugador.sumar(apuesta.getPrecio() * 2);
                                System.out.println(apuesta.getParticipante().getNombre()+" gana "+apuesta.getPrecio() * 2+ " apostando a bloque");
                                continue;
                            }else {
                                apuesta.getParticipante().sumarDinero(apuesta.getPrecio() * 2);
                            }

                            System.out.println(apuesta.getParticipante().getNombre()+" gana "+apuesta.getPrecio() * 2+ " apostando a bloque");
                        }else {
                            this.sumarDinero(apuesta.getPrecio());
                            System.out.println(apuesta.getParticipante().getNombre()+" pierde "+apuesta.getPrecio());
                        }

                        break;
                }
            }

        }
    }
    /*
    * Método que se encarga de validar o aceptar una apuesta
    * */
    public void validarDineroApuesta(ArrayList<Apuesta> apuestas, int dineroComprobar){
        int i = 0;
        while (i<apuestas.size()){
            Apuesta apuesta = apuestas.get(i);
            int dineroApuesta = apuesta.getPrecio();
            switch (apuesta.getPropiedadApostada()){
                case  "numero":

                    if(dineroComprobar< dineroApuesta * 36){
                        System.out.println("Apuesta de "+apuesta.getParticipante().getNombre()+" a "+apuesta.getPropiedadApostada()+" denegada");
                        apuestas.remove(apuesta);
                        continue;
                    }
                    i++;
                    dineroComprobar-=dineroApuesta;
                    break;

                case "posicion", "bloque", "color":

                    if(dineroComprobar<dineroApuesta * 2){
                        System.out.println("Apuesta de "+apuesta.getParticipante().getNombre()+" a "+apuesta.getPropiedadApostada()+" denegada");
                        apuestas.remove(apuesta);
                        continue;
                    }
                    i++;
                    dineroComprobar-=dineroApuesta;
                    break;
            }
        }
    }

    public Integer getDinero() {
        return dinero;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Ruleta getRuleta() {
        return ruleta;
    }
    public void sumarDinero(Integer dinero){
        this.dinero += dinero;
    }
    public void restarDinero(Integer dinero){
        this.dinero-=dinero;
    }

}
