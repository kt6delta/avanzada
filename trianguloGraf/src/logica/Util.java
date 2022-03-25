package logica;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Oswaldo
 */
public class Util {
    private static boolean debug = false;

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        Util.debug = debug;
    }
    
    // Método del desarrollador para depurar
    public static void ver(String texto){
        if(Util.isDebug()){
            System.out.println(texto);
        }
    }
    
}
