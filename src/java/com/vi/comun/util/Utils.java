/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vi.comun.util;

import java.util.ResourceBundle;

/**
 * @author Jerson Viveros
 */
public class Utils {
    
    public static String getPropiedad(String propiedad){
        ResourceBundle appProperties = ResourceBundle.getBundle("com.vi.comun.util.application");
        return appProperties.getString(propiedad);
    }
    
    public static int getNumAleatorio(){
        return (int)(10000000*Math.random());
    }
    
    public static String rellenarDerecha(String dato, String relleno, int tamano){
        if(dato.length() > tamano){
            return dato.substring(0, tamano);
        }
        
        while(dato.length() < tamano){
            dato+=relleno;
        }
        
        return dato;
    }


}
