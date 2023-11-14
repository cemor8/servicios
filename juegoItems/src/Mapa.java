import java.util.ArrayList;
import java.util.Scanner;

public class Mapa {
    private final ArrayList<Zona> zonas = new ArrayList<>();

    /**
     * Método que empieza el juego, carga las zonas para que empiecen a crear items y personajes.
     * */
    public void empezar(){
        int numeroZonas = this.pedirInteger("Introduce el numero de zonas para el mapa");

        Integer itemsXzona=this.pedirInteger("Introduce numero de items por zona a crear");
        Integer personajesXzona=this.pedirInteger("Introduce numero de personajes por zona a crear");
        Monitor.setNumeroDeZonas(numeroZonas);
        for(int i = 0; i< numeroZonas; i++){
            Monitor monitor=new Monitor(this);
            Zona zona = new Zona(new ArrayList<>(),itemsXzona,monitor,personajesXzona,i+1);
            this.zonas.add(zona);
            zona.start();
        }
    }

    public ArrayList<Zona> getZonas() {
        return zonas;
    }
    private Integer pedirInteger(String texto){
        Integer integerDevolver= null;
        while (integerDevolver==null){
            System.out.println(texto);
            Scanner integerDevolverIN=new Scanner(System.in);
            try {
                integerDevolver=integerDevolverIN.nextInt();
            }catch (Exception err){
                System.out.println(err.getMessage());
            }
        }
        return integerDevolver;
    }
}
