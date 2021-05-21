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
import pdw.data.crud.CrudGuidance;
import pdw.data.model.Guidance;

/**
 *
 * @author anama
 */
@Named(value = "jsfGuidance")
@RequestScoped
public class JsfGuidance {

    /**
     * Creates a new instance of JsfGuidance
     */
    public JsfGuidance() {
    }

    private int id;
    private String nome;
    private String descricao;
    private byte[] guidefile;
    
    private List<FilterMeta> filterBy;

    @Inject
    private JsfAuth jsfAuth;

    @Inject JsfApp jsfApp;

    public String persist() {
        if(this.id != 0){
            return merge();
        }
        Guidance guidance = new Guidance();
        guidance.setName(nome);
        guidance.setDescription(descricao);
        // guidance.setAdmin(jsfAuth.getUser());
        if (new pdw.data.crud.CrudGuidance().persist(guidance) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao inserir dados", "Error"));
        }
        return null;
    }

    public String remove(Guidance guidance) {
        if (new pdw.data.crud.CrudGuidance().remove(guidance) == null) {
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
        Guidance guidance = new CrudGuidance().find(id);
        guidance.setName(nome);
        guidance.setDescription(descricao);
        guidance.setGuidefile(guidefile);
        // guidance.setAdmin(jsfAuth.getUser());
        if (new pdw.data.crud.CrudGuidance().merge(guidance) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao inserir dados", "Error"));
        }
        return null;
    }
    
    public String edit(Guidance guidance){
        this.nome=guidance.getName();
        this.descricao=guidance.getDescription();
        this.id = guidance.getId();
        return "crGuidance.xhtml";
    }
   
    public Collection<Guidance> getAll() {
        return new pdw.data.crud.CrudGuidance().getAll();
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

    public byte[] getGuidefile() {
        return guidefile;
    }

    public void setGuidefile(byte[] guidefile) {
        this.guidefile = guidefile;
    }
    

}
