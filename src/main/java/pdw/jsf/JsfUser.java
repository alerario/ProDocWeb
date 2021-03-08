/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.jsf;

import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import pdw.data.crud.CrudUser;
import pdw.data.model.User;

/**
 *
 * @author alexandrelerario
 */
@Named(value = "jsfUser")
@RequestScoped
public class JsfUser {

    /**
     * Creates a new instance of JsfUser
     */
    public JsfUser() {
    }
    
   
    private String nome;
    private String email;
    private String password;
    
    public String createAdmin(){
        User user = new User();
        user.setEmail(email);
        user.setName(nome);
        user.setEnable(true);
        user.setPassword(password);
        CrudUser cruduser = new CrudUser();
        Collection c = cruduser.getAll();
        if(c==null||c.isEmpty()){
            //nao ha nenhum usuario cadastrado entao cadastramos o admin
           
               cruduser.persist(user);
        }
        
        return "index";
        
    }
    
    //verificamos se existe usuarios, o primeiro e o admin
    public boolean isAdmin(){
        Collection c =new CrudUser().getAll();
        if(c==null||c.isEmpty()){
            return false;
        }
        return true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
