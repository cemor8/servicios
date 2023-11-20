import java.util.ArrayList;

public class Ruleta {
    private ArrayList<Numero> listaNumeros = new ArrayList<>();
    private boolean lista = true;

    public Ruleta() {
        this.listaNumeros.add(new Numero(0,null,null));
        for(int i  = 1 ; i < 37 ;i++){
            String color;
            int bloque = 2;
            if((i%2==0 && i<=10)){
                color="negro";
            }else if((i%2!=0 && i>=11 && i<=17)){
                color="negro";
            }else if((i%2==0 && i>=20 && i<=28)){
                color="negro";
            }else if((i%2!=0 && i>=29 && i<=35)){
                color="negro";
            }else{
                color="rojo";
            }
            if(i<=36/2){
                bloque=1;
            }
            this.listaNumeros.add(new Numero(i,color,bloque));

        }
    }

    public ArrayList<Numero> getListaNumeros() {
        return listaNumeros;
    }

    public boolean isLista() {
        return lista;
    }

    public void setLista(boolean lista) {
        this.lista = lista;
    }
}
