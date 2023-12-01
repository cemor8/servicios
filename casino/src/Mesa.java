import java.util.ArrayList;

public class Mesa {
    private ArrayList<Apuesta> apuestasRealizadas = new ArrayList<>();
    Ruleta ruleta;
    private boolean sePuedeApostar = true;
    private ArrayList<Jugador> jugadoresEnMesa = new ArrayList<>();

    public Mesa(Ruleta ruleta) {
        this.ruleta=ruleta;
    }

    public void girarRuleta(Banca banca) throws InterruptedException {
        this.sePuedeApostar=false;
        System.out.println("Tirando ruleta");
        Numero numero = banca.getRuleta().tirarRule();
        if(numero.getNumero() == 0){
            int cantidad_ganada = 0;
            int i = 0;
            while (i<this.apuestasRealizadas.size()){
                cantidad_ganada+=apuestasRealizadas.get(i).getPrecio();
                apuestasRealizadas.get(i).getJugador().setDineroAcumulado(
                        apuestasRealizadas.get(i).getJugador().getDineroAcumulado() - apuestasRealizadas.get(i).getPrecio()
                );
                apuestasRealizadas.remove(i);
            }
            banca.sumarDinero(cantidad_ganada);
            System.out.println("Salio 0, gana la banca");
        }else {
            for(Apuesta apuesta : this.apuestasRealizadas){
                boolean ganar = false;
                switch (apuesta.getPropiedadApostada()){
                    case "color", "numero":
                        if(numero.getColor().equalsIgnoreCase(apuesta.getValor())){
                            banca.restarDinero(apuesta.getPrecio());
                            if(apuesta.getPropiedadApostada().equalsIgnoreCase("numero")){
                                apuesta.getJugador().sumarDinero(apuesta.getPrecio() * 36);
                            }else {
                                apuesta.getJugador().sumarDinero(apuesta.getPrecio() * 2);
                            }
                            ganar = true;
                        }else {
                            banca.sumarDinero(apuesta.getPrecio());
                            apuesta.getJugador().restarDinero(apuesta.getPrecio());
                        }
                        break;
                    case "posicion":
                        String posicion = null;
                        if(numero.getNumero()%2 == 0){
                            posicion = "par";
                        }else{
                            posicion = "impar";
                        }
                        if(posicion.equalsIgnoreCase(apuesta.getValor())){
                            banca.restarDinero(apuesta.getPrecio());
                            apuesta.getJugador().sumarDinero(apuesta.getPrecio() * 2);
                            ganar = true;
                        }else {
                            banca.sumarDinero(apuesta.getPrecio());
                            apuesta.getJugador().restarDinero(apuesta.getPrecio());
                        }
                        break;
                    case "bloque":
                        String bloque = null;
                        if(numero.getNumero()<18){
                            bloque = "1";
                        }else{
                            bloque = "2";
                        }
                        if(bloque.equalsIgnoreCase(apuesta.getValor())){
                            banca.restarDinero(apuesta.getPrecio());
                            apuesta.getJugador().sumarDinero(apuesta.getPrecio() * 2);
                            ganar = true;
                        }else {
                            banca.sumarDinero(apuesta.getPrecio());
                            apuesta.getJugador().restarDinero(apuesta.getPrecio());
                        }
                        break;
                }
                if(ganar){
                    System.out.println(apuesta.getJugador().getNombre()+" gana: "+apuesta.getPrecio()+" apostando a "+apuesta.getPropiedadApostada());
                }else {
                    System.out.println(apuesta.getJugador().getNombre()+" pierde: "+apuesta.getPrecio()+" apostando a "+apuesta.getPropiedadApostada());
                }
            }

        }
        this.apuestasRealizadas = new ArrayList<>();
        int i = 0;
        while (i < this.jugadoresEnMesa.size()) {
            this.jugadoresEnMesa.get(i).setHaApostado(false);
            System.out.println("\nJugador : "+jugadoresEnMesa.get(i).getNombre());
            System.out.println("\t Dinero : "+jugadoresEnMesa.get(i).getDineroAcumulado());
            this.jugadoresEnMesa.remove(i);

        }
        this.sePuedeApostar=true;
        this.despertar();

    }
    public synchronized void hacerApuesta(Jugador jugador,ArrayList<Apuesta> apuestas) throws InterruptedException {
        while (!this.sePuedeApostar){
            System.out.println(jugador.getNombre()+" no llego a tiempo y espera");
            if (!jugador.isIa()){
                jugador.cerrarScanner();
            }
            jugador.setHaLLegado(false);
            wait();
        }
        if(!jugador.isHaLLegado()){
            return;
        }

        if(apuestas!=null){
            this.apuestasRealizadas.addAll(apuestas);
        }else{
            System.out.println("El jugador "+jugador.getNombre()+" pasa");
        }
        jugador.setHaApostado(true);
        jugadoresEnMesa.add(jugador);

        while (jugador.isHaApostado()){
            wait();
        }

    }
     // banca tiene que ser variable privada y al meter una apuesta comprobar si la puede hacer, si no , no la acepta
    // al hacer apuesta quitar directamente dinero de jugador, luego no restar, solo sumar si gana
    //preguntar scanner, como hacerlo para que lo pueda cerrar.
    //como hacer para que si no hace nada, cierre el scanner y luego lo vuelva a abrir
    //otra variable para banca comprobar dinero, que se reinicie en cada apuesta y sea el dinero que tiene, luego le vas restando a esa variable.
    public synchronized void despertar(){
        notifyAll();
    }

}
