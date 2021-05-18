/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.data.crud;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import pdw.data.model.Template;

/**
 *
 * @author Gabriel-CSS
 */
public class CrudTemplate extends AbstractCrud<pdw.data.model.Template> {

    private EntityManager em;

    public CrudTemplate() {
        super(pdw.data.model.Template.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;
    }

    @Override
    public Exception persist(Template entity) {
        return super.persist(entity);
    }
  
}
