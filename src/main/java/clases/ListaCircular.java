package clases;

import java.io.*;
import java.util.Random;


public class ListaCircular {
    public Nodos ultimo;

    public ListaCircular() {
        ultimo = null;
    }

    //Metodo para insertar nodo
    public void insertar(String info) {
         Nodos nuevo = new Nodos(info);
    if (ultimo == null) {
        ultimo = nuevo;
    } else {
        nuevo.siguiente = ultimo.siguiente;
        nuevo.anterior = ultimo;
        ultimo.siguiente.anterior = nuevo;
        ultimo.siguiente = nuevo;
        ultimo = nuevo;
    }
}
    
    //Metodo para mostrar o listinha o fenomena 
    public void mostrar() {
        if (ultimo != null) {
            Nodos primero = ultimo.siguiente;
            Nodos aux = primero;
            do {
                System.out.println(aux.elemento);
                aux = aux.siguiente;
            } while (aux != primero);
        }
    }
    
    //Metodo para contar los nodos de la lista
    public int tamañoLista() {
        if (ultimo == null) return 0;

        int i = 1;
        Nodos actual = ultimo.siguiente;
        while (actual != ultimo) {
            i++;
            actual = actual.siguiente;
        }
        return i;
    }
    
    //Metodo para escoger nodo al azar
    public String elegirNodoAleatorio() {
        if (ultimo == null) return null; // Lista vacía

        Random aleatorio = new Random();
        // Método para contar la cantidad de nodos en la lista
        int cantidad = tamañoLista();
        //Escoge una posicion aleatorio
        int posicionAleatoria = aleatorio.nextInt(cantidad); 

        Nodos actual = ultimo.siguiente;
        for (int i = 0; i < posicionAleatoria; i++) {
            actual = actual.siguiente;
        }
        //Retorna la info del nodo seleccionado
        return actual.elemento; 
    }
    
    //Metodo para eliminar un nodo aleatorio
    public String borrarNodoAleatorio() {
        if (ultimo == null) {
            return null;
        }
        int tamaño = tamañoLista();
        if (tamaño == 1) {
            String dato = ultimo.elemento;
            ultimo = null;
            return dato;
        }

        int nodoAleatorio = new Random().nextInt(tamaño);
        Nodos actual = ultimo.siguiente;
        Nodos anterior = ultimo;
        for (int i = 0; i < nodoAleatorio; i++) {
            anterior = actual;
            actual = actual.siguiente;
        }

        if (actual == ultimo) {
            ultimo = anterior;
        }
        anterior.siguiente = actual.siguiente;
        return actual.elemento;    
    }
    
    //Metodo para guardar nodo eliminado
    public void guardarNodoBorrado() {
        if (ultimo == null) {
            return;
        }
        String elementoEliminado = borrarNodoAleatorio();
        try (FileWriter escritor = new FileWriter("C:\\Users\\Pedro\\Desktop\\perdedores.out.txt", true)) {
            escritor.write(elementoEliminado + System.lineSeparator());
        } catch (Exception e) {
            System.out.println("No se encuentra ninguna archivo.");
        }   
    }
    
    //Metodo para guardar ganador
    public void guardarNodoGanador() {
        if (ultimo == null) {
            return;
        }
        String elementoGanador = borrarNodoAleatorio();
        try (FileWriter escritor = new FileWriter("C:\\Users\\Pedro\\Desktop\\ganador.out.txt", true)) {
            escritor.write(elementoGanador + System.lineSeparator());
        } catch (Exception e) {
            System.out.println("No se encuentra ninguna archivo.");
        }   
    }

    //Metodos en sentido horario y contrario
    public void borrarNodoElegido(String elemento) {
        if (ultimo == null) {
            return;
        }
        Nodos actual = ultimo.siguiente;
        Nodos anterior = ultimo;


        if (actual.elemento.equals(elemento) && actual.siguiente == actual) {
            ultimo = null;
            return;
        }

        do {
            if (actual.elemento.equals(elemento)) {
                if (actual == ultimo) {
                    ultimo = anterior;
                }
                anterior.siguiente = actual.siguiente;
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        } while (actual != ultimo.siguiente);
    }
    //Sentido horario
    public void sentidoHorario() {
        if (ultimo == null) {
            return; // La lista está vacía
        }
        int tamaño = tamañoLista();
        Random random = new Random();
        int veces = random.nextInt(tamaño) + 1; // Determina la cantidad aleatoria de veces para recorrer la lista

        Nodos actual = ultimo.siguiente;
        for (int i = 0; i < veces * tamaño; i++) {
            actual = actual.siguiente; // Recorre la lista
        }


        int posicionAleatoria = random.nextInt(tamaño);
        for (int i = 0; i < posicionAleatoria; i++) {
            actual = actual.siguiente;
        }        

        String elementoSeleccionado = actual.elemento;
        borrarNodoElegido(elementoSeleccionado);


        try (FileWriter escritor = new FileWriter("C:\\Users\\Pedro\\Desktop\\perdedores.out.txt", true)) {
            escritor.write(elementoSeleccionado + System.lineSeparator());
        } catch (IOException e) {
            
        }
    }

    //Sentido antihorario
    public void sentidoAntihorario() {
        if (ultimo == null) {
            return;
        }
        int tamaño = tamañoLista();
        Random random = new Random();
        int veces = random.nextInt(tamaño) + 1;


        Nodos actual = ultimo;
        for (int i = 0; i < veces * tamaño; i++) {
            actual = nodoAnterior(actual);
        }


        int posicionAleatoria = random.nextInt(tamaño);
        for (int i = 0; i < posicionAleatoria; i++) {
            actual = nodoAnterior(actual);
        }


        String elementoSeleccionado = actual.elemento;
        borrarNodoElegido(elementoSeleccionado);


        try (FileWriter escritor = new FileWriter("C:\\Users\\Pedro\\Desktop\\perdedores.out.txt", true)) {
            escritor.write(elementoSeleccionado + System.lineSeparator());
        } catch (IOException e) {
            
        }
        
    }
    

    private Nodos nodoAnterior(Nodos nodo) {
        Nodos actual = ultimo;
        while (actual.siguiente != nodo) {
            actual = actual.siguiente;
        }
        return actual;
    }
}

