package com.vi.comun.util;

import com.vi.comun.locator.ParameterLocator;
import java.io.File;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author Jerson Viveros
 */
public class Log {

    private final static Logger log = Logger.getLogger("com.vbrothers");
    private static FileHandler fh;
    private static Set<String> logs;


    public static Logger getLogger(){
        Calendar calendario = Calendar.getInstance();
        String ano = String.format("%04d",calendario.get(Calendar.YEAR));
        String mes = String.format("%02d",calendario.get(Calendar.MONTH));
        if(logs == null){
            logs = new HashSet<String>();
        }
        if(!logs.contains(ano+""+mes)){
            try {
                if(fh != null){
                    log.removeHandler(fh);
                }
                SimpleFormatter formater = new SimpleFormatter();
                String rutaLog = ParameterLocator.getInstance().getParameter("rutaLog");
                File dirLog = new File(rutaLog);
                if(!dirLog.exists()){
                    dirLog.mkdirs();
                }
                rutaLog += File.separator+"VI_"+ano+mes+".log";
                fh = new FileHandler(rutaLog, true);
                fh.setFormatter(formater);
                log.addHandler(fh);
                log.setLevel(Level.ALL);
                logs.add(ano+""+mes);
            } catch (Exception e) {
                System.out.println("Error al tratar de crear el log!!!! "+e);
            }
        }
        return log;
    }


}
