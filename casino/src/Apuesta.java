public class Apuesta {
    private String propiedadApostada;
    private String valor;
    private int precio;
    private Jugador jugador;

    public Apuesta(String propiedadApostada, String valor, int precio, Jugador jugador) {
        this.propiedadApostada = propiedadApostada;
        this.valor = valor;
        this.precio = precio;
        this.jugador = jugador;
    }

    public int getPrecio() {
        return precio;
    }

    public Jugador getJugador() {
        return jugador;
    }



    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public String getPropiedadApostada() {
        return propiedadApostada;
    }

    public String getValor() {
        return valor;
    }
}
