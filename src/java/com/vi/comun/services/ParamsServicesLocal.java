/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vi.comun.services;

import com.vi.comun.exceptions.LlaveDuplicadaException;
import com.vi.comun.dominio.Parametro;
import java.util.List;
import javax.ejb.Local;

/**
 * @author Jerson Viveros
 */
@Local
public interface ParamsServicesLocal {
    void create(Parametro param) throws LlaveDuplicadaException;

    void edit(Parametro param) throws LlaveDuplicadaException;

    void remove(Parametro param);

    Parametro find(Object id);

    public List<Parametro> findAll();
}
