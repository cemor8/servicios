public class Apuesta {
    private String propiedadApostada;
    private String valor;
    private int precio;
    private Participante participante;

    public Apuesta(String propiedadApostada, String valor, int precio, Participante jugador) {
        this.propiedadApostada = propiedadApostada;
        this.valor = valor;
        this.precio = precio;
        this.participante = jugador;
    }

    public int getPrecio() {
        return precio;
    }

    public Participante getParticipante() {
        return participante;
    }



    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setJugador(Participante jugador) {
        this.participante = jugador;
    }

    public String getPropiedadApostada() {
        return propiedadApostada;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Apuesta{" +
                "propiedadApostada='" + propiedadApostada + '\'' +
                ", valor='" + valor + '\'' +
                ", precio=" + precio +
                '}';
    }
}
