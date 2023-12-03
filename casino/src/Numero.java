public class Numero {
    private int numero;
    private String color;
    private String bloque;

    public Numero(int numero, String color, String bloque) {
        this.numero = numero;
        this.color = color;
        this.bloque = bloque;
    }

    public int getNumero() {
        return numero;
    }

    public String getColor() {
        return color;
    }

    public String getBloque() {
        return bloque;
    }

    @Override
    public String toString() {
        return "Numero{" +
                "numero=" + numero +
                ", color='" + color + '\'' +
                ", bloque=" + bloque +
                '}';
    }
}
