package com.vi.comun.util;

import com.vi.comun.locator.ParameterLocator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * @author jerviver21
 */
public class FechaUtils {
    public static final long MILLISECONDS_ANO = (long)(365.25*24*60*60*1000);
    public static final long MILLISECONDS_DIA = 24*60*60*1000;

    
    
    static SimpleDateFormat formato1 = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    static SimpleDateFormat formato3 = new SimpleDateFormat("HH:mm");
    
    public static Date getFechaHoy(){
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.HOUR_OF_DAY, 0);
        calendario.set(Calendar.MINUTE, 0);
        calendario.set(Calendar.SECOND, 0);
        calendario.set(Calendar.MILLISECOND, 0);
        return calendario.getTime();
    }
    
    public static int getAnoActual(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    
    public static Date getFechaMasPeriodo(Date fecha, int nro, int tipo){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(tipo, nro);
        return calendario.getTime();
    }
    
    
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
    
    public static int getEdad(Date fechaNacimiento){
        int edad = 0;
        if(fechaNacimiento == null){
            return edad;
        }
        Date fechaActual = new Date();
        edad = (int)((fechaActual.getTime()-fechaNacimiento.getTime())/MILLISECONDS_ANO);
        return edad;
    }

    public static int getNroDiasPara(Date fechaFin) {
        double millis = fechaFin.getTime() - new Date().getTime();
        if(millis < 0){
            return 0;
        }
        int nroDias = (int) (millis/MILLISECONDS_DIA);
        return nroDias;
    }
    
    public static boolean isFestivo(Date fecha){
        Set<Date> festivos = ParameterLocator.getInstance().getFestivos();
        return festivos.contains(fecha);
    }
    
}
