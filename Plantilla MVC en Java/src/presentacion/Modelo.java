
package presentacion;

import logica.Sistema;

public class Modelo {
    
    private Sistema miSistema;
    private Vista ventana;
    
    // Métododos generados para ocultación de información
    
    public Vista getVentana(){
        if(ventana == null){
            ventana = new Vista(this);
        }
        return ventana;                
    }

    public Sistema getMiSistema() {
        if(miSistema == null){
            miSistema = new Sistema();
        }
        return miSistema;                
    }
    
    
    // Funcionalidades halladas en los requirimientos (casos de uso)
    
    public void iniciar(){
        
    }
}
