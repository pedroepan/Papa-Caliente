package clases;

import java.io.*;
import java.util.Scanner;


public class PapaCalienteV3 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int sentidoDeJuego;
        
        System.out.println("Los jugadores son:");
        ListaCircular lista = new ListaCircular();
        try {
            FileReader fr = new FileReader("C:\\Users\\Pedro\\Desktop\\Nombres.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.insertar(linea);
            }
            br.close();
        } catch (Exception e) {
            System.err.println("No se encontro archivo");
        }
        
        lista.mostrar();
        
        System.out.println("\nEl jugador que comenzara es: ");
        
        String elementoAleatorio = lista.elegirNodoAleatorio();
        System.out.println(elementoAleatorio);
        
        
        
        int tamañoDeLista = lista.tamañoLista();
        for (int i = 0; i < tamañoDeLista - 1; i++) {
            System.out.println("\nSe jugara en sentido horario (1) o antihorario (2)?"); 
        sentidoDeJuego = entrada.nextInt();
            if(sentidoDeJuego == 1){
                lista.sentidoHorario();
            }
            else if (sentidoDeJuego == 2){
                lista.sentidoAntihorario();
            }
        System.out.println("");
            lista.mostrar();
            System.out.println("");
        }
        
        System.out.println("El ganador es:");
        lista.mostrar();
        lista.guardarNodoGanador();

    }
}