package com.vi.comun.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jerviver21
 */
public class FechaUtils {
    
    static SimpleDateFormat formato1 = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    static SimpleDateFormat formato3 = new SimpleDateFormat("HH:mm");
    
    
    public static String getHora(Date date){
        if(date == null){
            return null;
        }
        return formato3.format(date);
    }
    
    public static String getFecha(Date date){
        if(date == null){
            return null;
        }
        return formato1.format(date);
    }
    
    public static Date getDate(Date fecha, String hora)throws ParseException{
        if(fecha == null || hora == null){
            return null;
        }
        return formato2.parse(formato1.format(fecha)+" "+hora);
    }

    public static Date getTime(String hora) throws ParseException{
        return formato3.parse(hora);
    }
    
}
