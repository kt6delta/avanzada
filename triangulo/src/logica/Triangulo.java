

package logica;

import java.util.Random;

public class Triangulo {
    private int xA;
    private int yA;
    
    private int xB;
    private int yB;
    
    private int xC;
    private int yC;

    public Triangulo(SistemaTriangular s) {
        //crea un triángulo con puntos aleatorios, dentro de un sistema
        
        // obtenemos las dimensiones del sistema
        int w = s.getAncho();
        int h = s.getAlto();
        
        //para cada punto, creamos una ubicación aleatoria
        Random generador = new Random();
        // Para las x
        xA = generador.nextInt(w);
        xB = generador.nextInt(w);
        xC = generador.nextInt(w);
        // Para las y
        yA = generador.nextInt(h);
        yB = generador.nextInt(h);
        yC = generador.nextInt(h);
    }

    public int getxA() {
        return xA;
    }

    public void setxA(int xA) {
        this.xA = xA;
    }

    public int getyA() {
        return yA;
    }

    public void setyA(int yA) {
        this.yA = yA;
    }

    public int getxB() {
        return xB;
    }

    public void setxB(int xB) {
        this.xB = xB;
    }

    public int getyB() {
        return yB;
    }

    public void setyB(int yB) {
        this.yB = yB;
    }

    public int getxC() {
        return xC;
    }

    public void setxC(int xC) {
        this.xC = xC;
    }

    public int getyC() {
        return yC;
    }

    public void setyC(int yC) {
        this.yC = yC;
    }     
    
    // comportamientos de mi sistema
    
    public float distanciaAB(){
        return (float)distancia(xA, yA, xB, yB);
    }
    
    public float distanciaAC(){
        return (float)distancia(xA, yA, xC, yC);
    }
    
    public float distanciaBC(){
        return (float)distancia(xB, yB, xC, yC);
    }
    
    public float area(){
        // Aquí hay que usar el método de Herón (area para todo tipo de triángulo)
        // recordar que pitágoras se usa para triángulos rectángulos
        float p = perimetro();
        float a = distanciaAB();
        float b = distanciaBC();
        float c = distanciaAC();
        return (float) Math.sqrt(p*(p-a)*(p-b)*p-c);
    }
    
    public float perimetro(){
        return distanciaAB() + distanciaBC() + distanciaAC();
    }
    
    protected float distancia(int xi, int yi, int xf, int yf){  
        double x = (double)(xf - xi);
        double y = (double)(yf - yi);
        return (float) Math.sqrt( x*x + y*y);
    }
    
    public void mostrar(){
        // para depurar
        System.out.println();
        System.out.println("A: (" + xA + ", " + yA + ")");
        System.out.println("B: (" + xB + ", " + yB + ")");
        System.out.println("C: (" + xC + ", " + yC + ")");
        System.out.println("Distancia A-B: " + distanciaAB());
        System.out.println("Distancia A-C: " + distanciaAC());
        System.out.println("Distancia B-C: " + distanciaBC());
        System.out.println("Perímetro: " + perimetro());
        System.out.println("Area: " + area());
    }
}
