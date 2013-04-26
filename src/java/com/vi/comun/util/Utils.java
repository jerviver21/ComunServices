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


}
