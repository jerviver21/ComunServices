
package com.vi.comun.services;

import com.vi.comun.dominio.Parametro;
import com.vi.comun.exceptions.LlaveDuplicadaException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Jerson Viveros
 */
@Stateless
public class ParamsServices implements ParamsServicesLocal {
    
    @PersistenceContext(unitName = "PUComun")
    private EntityManager em;

    @Override
    public void create(Parametro entity) throws LlaveDuplicadaException{
        try {
            em.persist(entity);
        } catch (ConstraintViolationException e) {
            throw new LlaveDuplicadaException("El menú ya existe");
        }
    }

    @Override
    public void edit(Parametro entity) throws LlaveDuplicadaException{
        try {
            em.merge(entity);
        } catch (ConstraintViolationException e) {
            throw new LlaveDuplicadaException("El menú ya existe");
        }
    }
    
    @Override
    public Parametro find(Object id) {
        return em.find(Parametro.class, id);
    }

    @Override
    public void remove(Parametro entity){
        em.remove(em.merge(entity));
    }

    @Override
    public List<Parametro> findAll(){
        List<Parametro> parametros = em.createNamedQuery("Parametro.findAll").getResultList();
        return parametros;
    }
 
}
