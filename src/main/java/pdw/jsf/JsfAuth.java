/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.jsf;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import pdw.data.model.User;

/**
 *
 * @author alexandrelerario
 */
@Named(value = "jsfAuth")
@SessionScoped
public class JsfAuth implements Serializable {

    /**
     * Creates a new instance of JsfPassaporte
     */
    //classe utilizada para efetuar a autenticacao e autorizacao
    public JsfAuth() {
    }

    private User user;
    private boolean auth = false;
    private String login;
    private String password;

    public String authenticate() {
        user = new pdw.data.crud.CrudUser().getAuth(login, password);
        auth = user != null;
        if (auth) {
            return "mainmenu";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inválidos.", "Error"));
        return null;
    }

    public String logout(){
        auth=false;
        user=null;
        return "index";
    }
    
    public User getUser(){
        return user;
    }
    
    public boolean getAuth() {
        return auth;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
