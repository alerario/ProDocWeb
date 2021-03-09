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
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;
import pdw.data.model.Organization;

/**
 *
 * @author alexandrelerario
 */
@Named(value = "jsfOrganization")
@RequestScoped
public class JsfOrganization {

    /**
     * Creates a new instance of JsfOrganization
     */
    public JsfOrganization() {
    }

    private int id;
    private String nome;
    private String descricao;

    @Inject
    private JsfAuth jsfAuth;

    public String persist() {
        Organization orgt = new Organization();
        orgt.setName(nome);
        orgt.setDescription(descricao);
        orgt.setAdmin(jsfAuth.getUser());
        if (new pdw.data.crud.CrudOrganization().persist(orgt) == null) {
            //redireciona para a mesma pagina, reseta parametros e mantem a mensagem
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao inserir dados", "Error"));
        }
        return null;
    }

    public Collection<Organization> getAll() {

        return new pdw.data.crud.CrudOrganization().getAll();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
