package com.vi.comun.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.Local;

/**
 * @author Jerson Viveros
 */
@Local
public interface CommonServicesLocal {
    public static final int BANCOS = 0;

    /*
     * Permite consultar tablas multivaluadas de referencia, como por ejemplo tipos de id:
     * 1 - CC
     * 2 - CE
     *
     * @Return: En el mapa de retorno la llave representa la pk en la BD y el valor es el valor buscado.
     */
    public Map getReferenceTableForCombo(String consulta);
    public Map getReferenceTableForSubcombo(String consulta);
    public int executeUpdate(String query);
    public List<String> executeQuery(String query);
    public void updateEstructuraMenus();
    public void insertAudSesion(String usr, String opr);
    public Set<Date> getFestivos();
}
