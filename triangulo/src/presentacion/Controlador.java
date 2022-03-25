
package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controlador implements ActionListener, ItemListener, ChangeListener{
    
    private Vista ventana;
    private Modelo miApp;
    
    public Controlador(Vista v){
        this.ventana = v;
        miApp = ventana.getModelo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menu;
        menu = (JMenuItem) e.getSource();
        
        if(menu == ventana.getMniCrear()){
            miApp.crearTriangulo();
        }if(menu == ventana.getMniEliminar()){
            miApp.eliminarTriangulo();
        }if(menu == ventana.getMniSalir()){
            miApp.salir();
        }else{
            // no hay nada mas implementado
        }
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() instanceof JComboBox){
            JComboBox combo = (JComboBox) e.getSource();        
            miApp.seleccionarTriangulo();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() instanceof JSlider){
            JSlider slider = (JSlider) e.getSource();            
            miApp.cambiarValorCoordenada(slider.getName(), slider.getValue());
        }
        
    }
}
