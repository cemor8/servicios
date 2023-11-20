public class Apuesta {
    private Integer numero;
    private String posicion;
    private String color;
    private Integer bloque;
    private int precio;

    public Apuesta(int numero, String posicion, String color, int bloque, int precio) {
        this.numero = numero;
        this.posicion = posicion;
        this.color = color;
        this.bloque = bloque;
        this.precio = precio;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getPosicion() {
        return posicion;
    }

    public String getColor() {
        return color;
    }

    public Integer getBloque() {
        return bloque;
    }

    public int getPrecio() {
        return precio;
    }
}