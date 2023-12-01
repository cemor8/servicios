import java.awt.desktop.AppReopenedEvent;
import java.util.*;

public class Jugador extends Thread {
    private String nombre;
    private Integer dineroInicial;
    private Integer dineroAcumulado;
    private Mesa mesa;
    private boolean ia;
    private boolean haApostado = false;
    private Scanner scanner;
    private boolean haLLegado = true;
    private ArrayList<Apuesta> apuestas = new ArrayList<>();

    public Jugador(String nombre, Integer dinero, Mesa mesa, boolean isIa) {
        this.nombre = nombre;
        this.dineroInicial = dinero;
        this.mesa = mesa;
        this.ia = isIa;
        this.dineroAcumulado = dinero;
        if(!ia){
            this.scanner = new Scanner(System.in);
        }
    }

    public void setDineroAcumulado(Integer dinero) {
        this.dineroAcumulado = dinero;
    }

    @Override
    public void run() {

            while (this.dineroAcumulado>50 && this.dineroAcumulado< this.dineroInicial*4) {
                this.haLLegado = true;
                this.apuestas = new ArrayList<>();
                if(!ia){
                    this.scanner = new Scanner(System.in);
                    try {
                        this.menu();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    double aleatorio = Math.random();
                    if (aleatorio < 0.7) {
                        ArrayList<Apuesta> apuestas = new ArrayList<>();
                        aleatorio = (int) (Math.random() * 37);
                        if (aleatorio!= 0 ){
                            this.hacerApuesta("numero",String.valueOf(aleatorio),apuestas);
                        }
                        this.hacerApuesta("posicion","par",apuestas);
                        this.hacerApuesta("posicion","impar",apuestas);
                        this.hacerApuesta("color","rojo",apuestas);
                        this.hacerApuesta("color","negro",apuestas);
                        this.hacerApuesta("bloque","1",apuestas);
                        this.hacerApuesta("bloque","2",apuestas);
                        try {
                            this.mesa.hacerApuesta(this,apuestas);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            this.mesa.hacerApuesta(this, null);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

    }
    private void hacerApuesta(String propieadApostar , String valorApostar, ArrayList<Apuesta> apuestas){
        double aleatorio = Math.random();
        if(aleatorio<0.5){
            int precio = (int) Math.floor(Math.random() * this.dineroAcumulado) + 10;
            System.out.println("\n"+this.getNombre()+" apuesta al "+valorApostar+", "+precio+", euros");
            apuestas.add(new Apuesta(propieadApostar,valorApostar,precio,this));
        }
    }

    public void menu() throws InterruptedException {
        while (this.scanner != null){
            System.out.println("1. Apostar Numero");
            System.out.println("2. Apostar Color");
            System.out.println("3. Apostar Posicion");
            System.out.println("4. Apostar Bloque");
            System.out.println("5. Pasar");
            System.out.println("6. Confirmar Apuestas");
            Integer opcion = null;
            try {
                this.scanner.close();
                opcion = this.scanner.nextInt();

            }catch (Exception err){
                System.out.println("Error en el scanner");
                this.mesa.hacerApuesta(this,null);
                continue;
            }
            switch (opcion){
                case 1:
                    Apuesta apuesta = this.apostarNumero();
                    this.apuestas.add(apuesta);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    this.mesa.hacerApuesta(this,this.apuestas);
                    return;
            }

        }

    }
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
            System.out.println("Introduce cantidad");
            Integer cantidad = null;
            try {
                cantidad = this.scanner.nextInt();
                if(cantidad< 0 || cantidad>this.dineroAcumulado){
                    throw new Exception("error");
                }
            }catch (Exception err){
                System.out.println("Opcion invalida");
                continue;
            }
            this.dineroAcumulado-=cantidad;
            return new Apuesta("numero",String.valueOf(opcion),cantidad,this);


        }
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
    public void sumarDinero(Integer dinero){
        this.dineroAcumulado+=dinero;
    }
    public void restarDinero(Integer dinero){
        this.dineroAcumulado-=dinero;
    }

    public boolean isIa() {
        return ia;
    }
    public void cerrarScanner(){
        this.scanner.close();
    }

    public Integer getDineroInicial() {
        return dineroInicial;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public boolean isHaLLegado() {
        return haLLegado;
    }

    public ArrayList<Apuesta> getApuestas() {
        return apuestas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDineroInicial(Integer dineroInicial) {
        this.dineroInicial = dineroInicial;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void setIa(boolean ia) {
        this.ia = ia;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setHaLLegado(boolean haLLegado) {
        this.haLLegado = haLLegado;
    }

    public void setApuestas(ArrayList<Apuesta> apuestas) {
        this.apuestas = apuestas;
    }
}
