public class Numero {
    private int numero;
    private String color;
    private Integer bloque;

    public Numero(int numero, String color, Integer bloque) {
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

    public int getBloque() {
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
