package com.vi.comun.locator;

import com.vi.comun.services.CommonServicesLocal;
import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javax.naming.InitialContext;

/**
 * @author Jerson Viveros
 */
public class ParameterLocator {
    public static int PARAMETROS = 0;
    public static int FESTIVOS = 1;

    //Guarda mapas con par llave-valor que se utilizan en la aplicación
    private Map cache;
    private CommonServicesLocal commonFacade;
    protected static ParameterLocator instance;


    protected ParameterLocator() throws Exception{
        try {
            ResourceBundle appProperties = ResourceBundle.getBundle("com.vi.comun.util.application");
            InitialContext contexto = new InitialContext();
            commonFacade = (CommonServicesLocal)contexto.lookup(appProperties.getString("jndi_common"));
            cache = Collections.synchronizedMap(new HashMap());
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace(System.err);
        }
    }
    
    public static ParameterLocator getInstance(){
        if(instance == null){
            try {
                instance = new ParameterLocator();
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace(System.err);
            }
        }
        return instance;
    }


    public void restartCache(){
        cache = new HashMap();
    }

    public String getParameter(String parametro){
        Map<String, String> parametros = (Map)cache.get(PARAMETROS);
        if(parametros == null){
            parametros = commonFacade.getReferenceTableForCombo("SELECT nombre, valor FROM parametro");
            cache.put(PARAMETROS, parametros);
        }
        String valorParametro = parametros.get(parametro);
        if(valorParametro != null && valorParametro.startsWith("OPENSHIFT")){
            String nombreVar = valorParametro.replaceAll("(.*)"+File.separator+".*", "$1");
            String valorVar = System.getenv(nombreVar);
            System.out.println("Variable de entorno: "+nombreVar+" - "+valorVar);
            valorParametro = valorParametro.replace(nombreVar+File.separator, valorVar);
            System.out.println("Parametro: "+valorParametro);
        }
        return valorParametro;
    }
    
    public Set<Date> getFestivos(){
        Set<Date> festivos = (Set<Date>)cache.get(FESTIVOS);
        if(festivos == null){
            festivos = commonFacade.getFestivos();
            cache.put(FESTIVOS, festivos);
        }
        return festivos;
    }

    

}
