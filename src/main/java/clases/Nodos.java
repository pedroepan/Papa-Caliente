package clases;


public class Nodos {
    String elemento;
    Nodos siguiente;
    Nodos anterior;  

    public Nodos(String info) {
        this.elemento = info;
        this.siguiente = this;
        this.anterior = this;
    }
}
