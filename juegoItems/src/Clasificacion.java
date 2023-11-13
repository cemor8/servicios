import javax.swing.text.html.parser.Entity;
import java.util.*;

public class Clasificacion {
    /**
     * Método que calcula la clasificación de un mapa.
     * @param mapa mapa a calcular resultado
     * */
    public void calcularResultado(Mapa mapa){
        ArrayList<Zona> listaZonas = mapa.getZonas();
        HashMap<Zona,ArrayList<Personaje>> listasPorZonas=new HashMap<>();
        for (Zona zona : listaZonas){
            listasPorZonas.put(zona,zona.getListaPersonajes());
        }
        ArrayList<Personaje> todosPersonajes=new ArrayList<>();
        for(Map.Entry<Zona,ArrayList<Personaje>> valores : listasPorZonas.entrySet()){
            for(Personaje personaje : valores.getValue()){
                todosPersonajes.add(personaje);
            }
        }
        Collections.sort(todosPersonajes,Comparator.comparing(personaje -> personaje.getTotal(),Comparator.reverseOrder()));
        System.out.println("\n");
        System.out.println("---------Clasificación-----------");
        for (Personaje personaje : todosPersonajes){
            System.out.println(todosPersonajes.indexOf(personaje)+1+" :" +personaje.getNombre()+" de la zona "+personaje.getZonaAsignada().getNumeroDeZona()+" puntuación: "+personaje.getTotal());
        }
    }
}
