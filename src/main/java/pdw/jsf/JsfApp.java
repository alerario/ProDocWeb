/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.jsf;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import pdw.data.crud.CrudUser;
import pdw.data.model.User;

/**
 *
 * @author alexandrelerario
 */
@Named(value = "jsfApp")
@ApplicationScoped
public class JsfApp {

    /**
     * Creates a new instance of JsfApp
     */
    public JsfApp() {
     //   this.verify();
    }

    //verity if has admin
    @PostConstruct
    public void verify() {
        List<User> c = new CrudUser().getAll();
        if (c == null || c.isEmpty()) {
            this.hasadmin = false;
            admin_id=0;
           // return false;
        } else {
            this.hasadmin = true;
            admin_id = c.get(0).getId();
            //return true;
        }

    }

    private boolean hasadmin; //ha adm?
    private int admin_id;

    public boolean isHasadmin() {
        return hasadmin;
    }

    public int getAdmin_id() {
        return admin_id;
    }
}
