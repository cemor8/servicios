import java.awt.desktop.AppReopenedEvent;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class Jugador extends Participante {
    private Integer dineroAcumulado;
    private Mesa mesa;
    private boolean haApostado = false;
    private boolean haLLegado = true;
    private Scanner scanner = null;
    private ArrayList<Apuesta> apuestas = new ArrayList<>();
    private ArrayList<Integer> cantidades = new ArrayList<>(List.of(100,200,300,400,500));


    public Jugador(String nombre, Integer dinero, Mesa mesa) {
        super(nombre,dinero);
        this.mesa = mesa;
        this.dineroAcumulado = dinero;
    }

    public void setDineroAcumulado(Integer dinero) {
        this.dineroAcumulado = dinero;
    }


    /**
     * Menu del jugador, muestra las diferentes apuestas posibles a realizar
     * */
    public void menu() throws InterruptedException {

        while (this.dineroAcumulado <= this.dineroInicial * 4 && this.mesa.isSeJuega()) {

            System.out.println("1. Apostar Numero");
            System.out.println("2. Apostar Color");
            System.out.println("3. Apostar Posicion");
            System.out.println("4. Apostar Bloque");
            System.out.println("5. Mostrar Apuestas");
            System.out.println("6. Pasar");
            System.out.println("7. Confirmar Apuestas");
            System.out.println("8. Irse de la mesa ");
            System.out.println("9. Ver dinero");

            this.scanner = new Scanner(System.in);
            Integer opcion = null;
            try {
                opcion = this.scanner.nextInt();
            } catch (Exception err) {
                System.out.println("Error en el scanner");
                continue;
            }
            switch (opcion) {
                case 1:
                    Apuesta apuesta = this.apostarNumero();
                    if(apuesta == null){
                        break;
                    }
                    this.apuestas.add(apuesta);
                    break;
                case 2:
                    this.apostarColor();
                    break;
                case 3:
                    this.apostarPosicion();
                    break;
                case 4:
                    this.apostarBloque();
                    break;
                case 5:
                    this.mostrarApuestas();
                    break;
                case 6:
                    if (this.haApostado) {
                        System.out.println("no puedes apostar aun, espera");
                        continue;
                    }
                    this.mesa.apostar(this,null);
                    this.haApostado = true;
                    this.apuestas = new ArrayList<>();
                    break;
                case 7:
                    if(this.apuestas.isEmpty()){
                        System.out.println("no hay apuestas en tu lista");
                        continue;
                    }
                    if (this.haApostado) {
                        System.out.println("no puedes apostar aun, espera");
                        continue;
                    }
                    this.mesa.apostar(this, this.apuestas);
                    this.haApostado = true;
                    this.apuestas = new ArrayList<>();
                    break;
                case 8:
                    this.mesa.eliminarOriginal(this);
                    System.out.println(this.nombre+" dinero: "+this.dineroAcumulado);
                    System.out.println(this.nombre+" acabo de apostar");
                    this.mesa.eliminarOriginal(this);
                    return;
                case 9:
                    System.out.println(this.dineroAcumulado);
                    break;
                default:
                    continue;
            }

        }
    }
    /**
     * Método que pide los datos para relizar una apuesta a un numero
     * */
    public Apuesta apostarNumero(){
        while (true){
            System.out.println("Introduce un numero entre 1 y 36");
            Integer opcion = null;
            try {
                opcion = this.scanner.nextInt();
                if(opcion<1 ||opcion>36){
                    throw new Exception("error");
                }
            }catch (Exception err){
                System.out.println("Opcion invalida");
                continue;
            }
            Integer cantidad = pedirCantidad();
            if(this.comprobarExistente(String.valueOf(opcion))){
                System.out.println("Apuesta duplicada");
                return null;
            }
            if(cantidad==null){
                return null;
            }
            this.dineroAcumulado-=cantidad;
            return new Apuesta("numero",String.valueOf(opcion),cantidad,this);

        }
    }
    /**
     * Método que pide los datos para relizar una apuesta a una posicion
     * */
    public void apostarPosicion(){
        while (true){
            System.out.println("1. Par 2.Impar ");
            Integer opcion = null;
            try {
                opcion = this.scanner.nextInt();
            }catch (Exception err){
                System.out.println("Opcion invalida");
                continue;
            }
            String posicion;
            switch (opcion){
                case 1:
                    posicion = "par";
                    break;
                case 2:
                    posicion = "impar";
                    break;
                default:
                    continue;
            }
            Integer cantidad = pedirCantidad();
            if(this.comprobarExistente(posicion)){
                System.out.println("Apuesta duplicada");
                return;
            }
            if(cantidad==null){
                return;
            }
            this.dineroAcumulado-=cantidad;
            this.apuestas.add(new Apuesta("posicion", posicion,cantidad,this));
            return;

        }
    }
    /**
     * Método que pide los datos para relizar una apuesta a un color
     * */
    public void apostarColor(){
        while (true){
            System.out.println("1. Rojo 2.Negro ");
            Integer opcion = null;
            try {
                opcion = this.scanner.nextInt();
            }catch (Exception err){
                System.out.println("Opcion invalida");
                continue;
            }
            String posicion;
            switch (opcion){
                case 1:
                    posicion = "rojo";
                    break;
                case 2:
                    posicion = "negro";
                    break;
                default:
                    continue;
            }
            Integer cantidad = pedirCantidad();
            if(this.comprobarExistente(posicion)){
                System.out.println("Apuesta duplicada");
                return;
            }
            if(cantidad==null){
                return;
            }
            this.dineroAcumulado-=cantidad;
            this.apuestas.add(new Apuesta("color", posicion,cantidad,this));
            return;

        }
    }
    /**
     * Método que pide los datos para relizar una apuesta a un bloque
     * */
    public void apostarBloque(){
        while (true){
            System.out.println("1. 1-18 2. 19-36 ");
            Integer opcion = null;
            try {
                opcion = this.scanner.nextInt();
            }catch (Exception err){
                System.out.println("Opcion invalida");
                continue;
            }
            switch (opcion){
                case 1, 2:
                    break;
                default:
                    continue;
            }
            int cantidad = pedirCantidad();
            if(this.comprobarExistente(String.valueOf(opcion))){
                System.out.println("Apuesta duplicada");
                return;
            }
            this.dineroAcumulado-=cantidad;
            this.apuestas.add(new Apuesta("bloque", String.valueOf(opcion),cantidad,this));
            return;

        }
    }

    /**
     * Método que pide los datos para apostar una cantidad
     * */
    public Integer pedirCantidad(){
        if(this.dineroAcumulado<100){
            return null;
        }
        while (true){
            System.out.println("Introduce cantidad");
            System.out.println("Cantidades disponibles: 100-200-300-400-500");
            Integer cantidad = null;
            try {
                cantidad = this.scanner.nextInt();
                if(!this.cantidades.contains(cantidad) || this.dineroAcumulado<cantidad){
                    System.out.println("Canitdad inválida");
                    return null;
                }
            }catch (Exception err){
                System.out.println("Opcion invalida");
                continue;
            }
            return cantidad;
        }
    }
    /**
     * Método que muestra las apuestas actuales
     * */
    public void mostrarApuestas(){
        if(apuestas.isEmpty()){
            System.out.println("no has hecho apuestas");
            return;
        }
        for (Apuesta apuesta : apuestas){
            System.out.println(apuesta);
        }
    }
    public boolean comprobarExistente(String valor){
        Optional<Apuesta> apuestaOptional = apuestas.stream().filter(apuesta -> apuesta.getValor().equalsIgnoreCase(valor)).findAny();
        return apuestaOptional.isPresent();
    }

    public boolean isHaApostado() {
        return haApostado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setHaApostado(boolean haApostado) {
        this.haApostado = haApostado;
    }

    public Integer getDineroAcumulado() {
        return dineroAcumulado;
    }
    public void sumar(int dinero){
        this.dineroAcumulado+=dinero;
    }

}
