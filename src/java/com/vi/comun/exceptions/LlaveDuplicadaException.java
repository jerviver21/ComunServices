package com.vi.comun.exceptions;

import javax.ejb.ApplicationException;

/**
 * @author Jerson Viveros
 */
@ApplicationException (rollback=true)
public class LlaveDuplicadaException extends Exception{
    public LlaveDuplicadaException(String mensaje){
        super(mensaje);
    }
}
