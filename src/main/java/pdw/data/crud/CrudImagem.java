/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.data.crud;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import pdw.data.model.Images;
import pdw.data.model.Organization;

/**
 *
 * @author tiago
 */
public class CrudImagem extends AbstractCrud<pdw.data.model.Images> {

    private EntityManager em;

    public CrudImagem() {
        super(pdw.data.model.Images.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;
    }

    @Override
    public Exception persist(Images entity) {
         return super.persist(entity);
     }
  
}

