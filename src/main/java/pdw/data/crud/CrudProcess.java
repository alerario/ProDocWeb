/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.data.crud;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import pdw.data.model.Process;

/**
 *
 * @author InfinitePondera
 */
public class CrudProcess extends AbstractCrud<pdw.data.model.Process> {

    private EntityManager em;

    public CrudProcess() {
        super(pdw.data.model.Process.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;
    }

    @Override
    public Exception persist(Process entity) {
        return super.persist(entity);
    }

}
