
package presentacion;

import logica.Util;
import java.awt.Component;
import java.awt.Container;
import logica.SistemaTriangular;
import logica.Triangulo;

public class Modelo {
    
    private SistemaTriangular miSistema;
    private Vista ventana;
    

    public Modelo() {        
        getVentana().reset(true);
    }
    
    // Métododos generados para ocultación de información
    
    public Vista getVentana(){        
        if(ventana == null){
            ventana = new Vista(this);
        }
        return ventana;                
    }

    public SistemaTriangular getMiSistema() {
        if(miSistema == null){
            miSistema = new SistemaTriangular();
        }
        return miSistema;                
    }
    
    
    // Funcionalidades halladas en los requirimientos (casos de uso)
    
    public void iniciar(){
        getVentana().setSize(800, 800);
        getVentana().setVisible(true);
        
        //crearTriangulo();
    }   

    public void salir() {
        System.exit(0);
    }

    public void crearTriangulo(){
        
        // Si se necesita pasar datos a la lógica, antes tenemos que tomoar esos datos de la GUI
        // luego se envían esos datos a la lógica para luego invocar el comportamiento del negocio que me interesa
        getVentana().reset(false);
        
        getMiSistema().crearTriangulo();
        Triangulo t = getMiSistema().getTrianguloActual();        
        
        getVentana().getCmbListaTriangulos().addItem("Triángulo "+getMiSistema().getCantidadTriangulos());
        getVentana().getCmbListaTriangulos().setSelectedIndex(getMiSistema().getNumeroTrianguloActual());
        
        
        //probando
        if(Util.isDebug()){
            int nT = getMiSistema().getCantidadTriangulos();
            Util.ver("Triángulos en el sistema: " + nT);
            
            for(int i=0; i<nT; i++){
                Util.ver("Triángulo " + i);                
                t = (Triangulo) getMiSistema().getListaTriangulos().get(i);
                t.mostrar();
            }
        }
                
        // si ese comportamiento devuelve información, es responsabilidad de este método del modelo
        // dar formato a esos datos para mostrarlos de manera adecuada en la GUI
    }
    
    public void seleccionarTriangulo(){        
        int i = getVentana().getCmbListaTriangulos().getSelectedIndex();
        
        Util.ver("i: "+i);       
        getMiSistema().setNumeroTrianguloActual(i);
        actualizarGUI();        
    }    
    
        
    public void eliminarTriangulo() {
        if(miSistema.getCantidadTriangulos() < 1){
            getVentana().reset(true);
        }else{
            miSistema.eliminarTriangulo();
            int i = getVentana().getCmbListaTriangulos().getSelectedIndex();
            getVentana().getCmbListaTriangulos().removeItemAt(i);   
            getVentana().reset(true);
        }
    }
    
    public void cambiarValorCoordenada(String coord, int v) {        
        // La variable coord, viene especificada así:   "x,y"
        // x es un número que corresponde al punto; 0, 1 o 2 para A, B, C
        // y es el componente de coordenada: x o y
        
        String c[] = coord.split(",");
        int p = Integer.parseInt(c[0]);
        String dim = c[1];
        
        Triangulo t = getMiSistema().getTrianguloActual();
        if(t==null){
            Util.ver("Triangulo t es null en cambiarValorCoordenada()");
            return;
        }
            
        switch(p){
            case 0: //punto A
                if(dim.equals("x")){
                    t.setxA(v);
                }else{
                    t.setyA(v);
                }                
                break;
            case 1: //punto B
                if(dim.equals("x")){
                    t.setxB(v);
                }else{
                    t.setyB(v);
                }                
                break;
            case 2: //punto C
                if(dim.equals("x")){
                    t.setxC(v);
                }else{
                    t.setyC(v);
                }                
                break;
            default:
                //si llega acá, hay algo mal
                Util.ver("Punto de triangulo no válido");
                break;
        }
        actualizarGUI();
    }
    

    private void actualizarGUI() {
        int cT = getMiSistema().getCantidadTriangulos();
        
        if(cT < 1){
            return;
        }
        Triangulo t = getMiSistema().getTrianguloActual();
        
        if(t == null){
            Util.ver("Triángulo null en actualizarGUI");
            return;
        }
        
        getVentana().getWPuntoCoord()[0].getSliX().setValue(t.getxA());
        getVentana().getWPuntoCoord()[0].getSliY().setValue(t.getyA());
        getVentana().getWPuntoCoord()[1].getSliX().setValue(t.getxB());
        getVentana().getWPuntoCoord()[1].getSliY().setValue(t.getyB());
        getVentana().getWPuntoCoord()[2].getSliX().setValue(t.getxC());
        getVentana().getWPuntoCoord()[2].getSliY().setValue(t.getyC());
        
        getVentana().getTxtDistanciaAB().setText(""+t.distanciaAB() + " u");
        getVentana().getTxtDistanciaAC().setText(""+t.distanciaAC() + " u");
        getVentana().getTxtDistanciaBC().setText(""+t.distanciaBC() + " u");
        
        getVentana().getTxtPerimetro().setText(""+t.perimetro() + " u");
        getVentana().getTxtArea().setText(""+t.area() + " u2");
    }
    
       
}
