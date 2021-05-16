/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.data.crud;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import pdw.data.model.Phase;

/**
 *
 * @author jm-marcel
 */
public class CrudPhase extends AbstractCrud<pdw.data.model.Phase> {

    private EntityManager em;

    public CrudPhase() {
        super(pdw.data.model.Phase.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;
    }

    @Override
    public Exception persist(Phase entity) {
        return super.persist(entity);
    }
  
}
