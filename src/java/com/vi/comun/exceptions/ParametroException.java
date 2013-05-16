/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vi.comun.exceptions;

import javax.ejb.ApplicationException;

/**
 * @author Jerson Viveros
 */
@ApplicationException (rollback=true)
public class ParametroException extends Exception {
    public ParametroException(String mensaje){
        super(mensaje);
    }
}
