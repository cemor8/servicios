public class Item {
    private String nombre;
    private double ataque;

    public Item(String nombre, double ataque) {
        this.nombre = nombre;
        this.ataque = ataque;
    }

    public String getNombre() {
        return nombre;
    }

    public double getAtaque() {
        return ataque;
    }
}
