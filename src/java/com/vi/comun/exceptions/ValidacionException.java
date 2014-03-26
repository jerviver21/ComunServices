package com.vi.comun.exceptions;

import javax.ejb.ApplicationException;

/**
 * @author Jerson Viveros
 */
@ApplicationException (rollback=true)
public class ValidacionException extends Exception {
    public ValidacionException(String mensaje){
        super(mensaje);
    }
}
