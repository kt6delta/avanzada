
package logica;

import java.util.ArrayList;


public class SistemaTriangular {
    
    private ArrayList listaTriangulos;
    private int cantidadTriangulos;
    private int numeroTrianguloActual;
    private int ancho, alto;
    
    public SistemaTriangular() {
        ancho = 2000;
        alto = 2000;
        listaTriangulos = new ArrayList();
        cantidadTriangulos = 0;
        numeroTrianguloActual = -1;
    }

    public int getNumeroTrianguloActual() {
        return numeroTrianguloActual;
    }

    public void setNumeroTrianguloActual(int t) {
        this.numeroTrianguloActual = t;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
        
    public void crearTriangulo(){
        Triangulo t = new Triangulo(this);
        listaTriangulos.add(t);
        this.cantidadTriangulos++;
        if(cantidadTriangulos == 0){
            numeroTrianguloActual = -1;
        }else{
            numeroTrianguloActual = cantidadTriangulos - 1;
        }
    }

    public ArrayList getListaTriangulos() {
        return listaTriangulos;
    }

    public int getCantidadTriangulos() {
        return cantidadTriangulos;
    }        
    
    public Triangulo getTrianguloActual(){
        if(numeroTrianguloActual < 0){
            return null;
        }
        return (Triangulo) listaTriangulos.get(numeroTrianguloActual);
    }
    
    public void actualizarTriangulo(Triangulo t){
        if(numeroTrianguloActual < 0){
            return;
        }
        listaTriangulos.set(numeroTrianguloActual,t);
    }

    public void eliminarTriangulo() {
        listaTriangulos.remove(numeroTrianguloActual);
        cantidadTriangulos--;
        if(cantidadTriangulos > 0){
            numeroTrianguloActual = 0;
        }else{
            numeroTrianguloActual = -1;
        }
    }
}
