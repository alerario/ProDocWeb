/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.jsf;

import java.util.Collection;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.FilterMeta;
import pdw.data.crud.CrudArctifact;
import pdw.data.model.Arctifact;

/**
 *
 * @author CesarOliveira
 */
@Named(value = "jsfArctifact")
@RequestScoped
public class JsfArctifact {

    /**
     * Creates a new instance of JsfArctifact
     */
    public JsfArctifact() {
    }

    private int id;
    private String nome;
    private String descricao;
    
    private List<FilterMeta> filterBy;

    @Inject
    private JsfAuth jsfAuth;

    @Inject JsfApp jsfApp;

    public String persist() {
        if(this.id != 0){
            return merge();
        }
        Arctifact arctifact = new Arctifact();
        arctifact.setName(nome);
        arctifact.setDescription(descricao);
        // arctifact.setAdmin(jsfAuth.getUser());
        if (new pdw.data.crud.CrudArctifact().persist(arctifact) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao inserir dados", "Error"));
        }
        return null;
    }

    public String remove(Arctifact arctifact) {
        if (new pdw.data.crud.CrudArctifact().remove(arctifact) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir dados", "Error"));
        }
        return null;
    }
    
    public String merge() {
        Arctifact arctifact = new CrudArctifact().find(id);
        arctifact.setName(nome);
        arctifact.setDescription(descricao);
        // arctifact.setAdmin(jsfAuth.getUser());
        if (new pdw.data.crud.CrudArctifact().merge(arctifact) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao inserir dados", "Error"));
        }
        return null;
    }
    
    public String edit(Arctifact arctifact){
        this.nome=arctifact.getName();
        this.descricao=arctifact.getDescription();
        this.id = arctifact.getId();
        return "crArctifact.xhtml";
    }
   
    public Collection<Arctifact> getAll() {
        return new pdw.data.crud.CrudArctifact().getAll();
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
