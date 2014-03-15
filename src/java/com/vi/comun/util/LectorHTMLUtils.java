/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vi.comun.util;

import java.io.RandomAccessFile;

/**
 *
 * @author jerviver21
 */
public class LectorHTMLUtils {
    
    public static String leerFila(RandomAccessFile lector, String linea)throws Exception{
        StringBuilder resultado = new StringBuilder("");
        int limit = 0;
        //System.out.println("Leyendo fila.... "+linea);
        while(linea != null && !linea.matches(".*</[tT][rR]\\s*>.*")){
            limit++;
            if(limit == 1000){
                throw new Exception("Estructura del archivo incorrecta");
            }
            if(linea.matches(".*<[tT][dD].*>.*")){
                resultado.append(leerColumna(lector, linea));
                resultado.append(";;");
            }
            linea = lector.readLine();
        }
        return resultado.toString();
    }
    
    public static String leerColumna(RandomAccessFile lector, String linea)throws Exception{
        StringBuilder resultado = new StringBuilder("");
        int limit = 0;
        //System.out.println("Leyendo columna.... "+linea);
        if(linea.matches(".*<[tT][dD].*?>.*</[tT][dD]>.*")){
            return linea.replaceAll(".*<[tT][dD].*?>(.*)</[tT][dD]>.*", "$1");
        }
        if(linea.matches(".*<[tT][dD].*?>.*\\w.*")){
           resultado.append(linea.replaceAll(".*<[tT][dD].*?>(.*\\w.*)", "$1").trim());
           resultado.append(" "); 
        }
        linea = lector.readLine();
        
        while(linea != null && !linea.matches(".*</[tT][dD]\\s*>.*")){
            limit++;
            if(limit == 10000){
                throw new Exception("Estructura del archivo incorrecta");
            }
            
            if(linea.matches("\\s*<.*>\\s*")){
                linea = lector.readLine();
                continue;
            }
            
            resultado.append(linea.trim());
            resultado.append(" ");
            linea = lector.readLine();
            
            if(linea.matches(".*\\w.*</[tT][dD]\\s*>.*")){
                resultado.append(linea.replaceAll("(.*\\w.*)</[tT][dD]\\s*>.*", "$1").trim());
                resultado.append(" "); 
            }
        }
        return resultado.toString();
    }
    
}
