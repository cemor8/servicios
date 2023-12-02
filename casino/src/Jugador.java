import java.awt.desktop.AppReopenedEvent;
import java.util.*;

public class Jugador extends Participante {
    private String nombre;
    private Integer dineroInicial;
    private Integer dineroAcumulado;
    private Mesa mesa;
    private boolean haApostado = false;
    private boolean haLLegado = true;
    private Scanner scanner = null;
    private ArrayList<Apuesta> apuestas = new ArrayList<>();


    public Jugador(String nombre, Integer dinero, Mesa mesa) {
        super(nombre,dinero);
        this.nombre = nombre;
        this.dineroInicial =dinero;
        this.mesa = mesa;
        this.dineroAcumulado = dinero;
        this.mesa.setJugadorNoIa(this);
    }

    public void setDineroAcumulado(Integer dinero) {
        this.dineroAcumulado = dinero;
    }



    public void menu() throws InterruptedException {

        while (this.dineroAcumulado <= 50 && this.dineroAcumulado >= this.dineroInicial * 2 && this.mesa.isSeJuega()) {
            System.out.println("\n" + this.nombre + " dinero: " + this.dineroAcumulado + "\n");
            System.out.println("1. Apostar Numero");
            System.out.println("2. Apostar Color");
            System.out.println("3. Apostar Posicion");
            System.out.println("4. Apostar Bloque");
            System.out.println("5. Mostrar Apuestas");
            System.out.println("6. Pasar");
            System.out.println("7. Confirmar Apuestas");
            this.scanner = new Scanner(System.in);
            Integer opcion = null;
            try {
                opcion = this.scanner.nextInt();

                //que esperen a que el usuario haya enviado las apuestas o que se acabe el tiempo, hacer hilo contador

            } catch (Exception err) {
                System.out.println("Error en el scanner");
                continue;
            }
            switch (opcion) {
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
                    break;
                case 7:
                    if (this.haApostado) {
                        System.out.println("no puedes apostar aun, espera");
                        continue;
                    }
                    this.mesa.hacerApuesta(this, this.apuestas);
                    this.haApostado = true;
                    break;
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
    public void mostrarApuestas(){
        for (Apuesta apuesta : apuestas){
            System.out.println(apuesta);
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


    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setHaLLegado(boolean haLLegado) {
        this.haLLegado = haLLegado;
    }

    public void setApuestas(ArrayList<Apuesta> apuestas) {
        this.apuestas = apuestas;
    }
    public void cerrarScaner() {
        if (this.scanner != null) {
            this.scanner.close();
            this.scanner = null;
        }
    }
}
