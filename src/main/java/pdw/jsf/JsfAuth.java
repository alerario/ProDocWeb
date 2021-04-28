/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.jsf;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import pdw.data.model.Organization;
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
    private Organization selectedOrganization; //utilize esta classe para manter a organizacao e o processo selecionado
    private pdw.data.model.Process selectedProcess;

    @Inject
    private JsfApp jsfApp;

    public String authenticate() {
        jsfApp.verify(); //verifica se ha um admim
        user = new pdw.data.crud.CrudUser().getAuth(login, password);
        auth = user != null;
        if (auth) {
            if (user.getEnable()) {
                return "mainmenu";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario precisa ser ativado.", "Error"));
                return null;
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inválidos.", "Error"));
        return null;
    }

    public String logout() {
        auth = false;
        user = null;
        return "index";
    }

    public User getUser() {
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

    public Organization getSelectedOrganization() {
        // return para o metodo funcional
        return selectedOrganization;

        //return para o metodo em desenvolvimento
       // return new pdw.data.crud.CrudOrganization().find(1);
    }

    public void setSelectedOrganization(Organization selectedOrganization) {
        this.selectedOrganization = selectedOrganization;
    }

    public pdw.data.model.Process getSelectedProcess() {
        //return para o software em desenvolvimento
      //  return new pdw.data.crud.CrudProcess().find(3);

//return para o sw em producao
       return selectedProcess;
    }

    public void setSelectedProcess(pdw.data.model.Process selectedProcess) {
        this.selectedProcess = selectedProcess;
    }

}
