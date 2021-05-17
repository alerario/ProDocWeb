/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.data.crud;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import pdw.data.model.Guidance;

/**
 *
 * @author anama
 */
public class CrudGuidance extends AbstractCrud<pdw.data.model.Guidance> {

    private EntityManager em;

    public CrudGuidance() {
        super(pdw.data.model.Guidance.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;
    } 
}
