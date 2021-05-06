/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;;
import pdw.data.model.User;
import pdw.data.crud.CrudUser;


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

    @Inject
    private JsfApp jsfApp;

    private String nome;
    private String email;
    private String password;
    private String chave;

    public String createAdmin() {
        User user = new User();
        user.setEmail(email);
        user.setName(nome);
        user.setEnable(true);
        user.setPassword(password);
        CrudUser cruduser = new CrudUser();
        Collection c = cruduser.getAll();
        if (c == null || c.isEmpty()) {
            //nao ha nenhum usuario cadastrado entao cadastramos o admin
            cruduser.persist(user);
            jsfApp.verify(); //verifica a existencia de um admin
        }

        return "index";

    }
    
   
     
    public String passValidator(){
        CrudUser cr = new CrudUser();

        if(!"".equals(cr.geraChave(getEmail()))){
            setChave(cr.geraChave(getEmail()));
            System.out.println(getChave());
            // https://www.devmedia.com.br/enviando-email-com-javamail-utilizando-gmail/18034
            return "mudarSenha";
        }
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EMAIL INEXISTENTE", ""));
       return "";
    }
 

    
    public boolean changePassword(){
         CrudUser cr = new CrudUser();
         String query;
         int id;
         String newPass;
         id = cr.getIdByEmail(getEmail());
         newPass = getPassword();
         
         if(id == -1){ // não encontrou usuário
             return false;
         }
         if("".equals(newPass)){
             return false;
         }
         String md5pass = new pdw.util.Util().getMd5(newPass).toUpperCase();

         query = "UPDATE swuser SET password = "+md5pass+" WHERE id = "+id+"";

        return true;
     }

    public List<User> getAll() {
        return new CrudUser().getAll();
    }
   

    public void turnActive(User user) {
        if (jsfApp.getAdmin_id() != user.getId()) {
            user.setEnable(!user.getEnable());

            if (new CrudUser().merge(user) == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado modificado.", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao modificar estado.", "Error"));
        }
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

    public String getChave(){
        return chave;
    }

    public void setChave(String chave){
        this.chave = chave;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    




}
