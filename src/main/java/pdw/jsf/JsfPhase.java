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
import pdw.data.crud.CrudPhase;
import pdw.data.model.Phase;

/**
 *
 * @author jm-marcel
 */
@Named(value = "jsfPhase")
@RequestScoped
public class JsfPhase {

    /**
     * Creates a new instance of JsfPhase
     */
    public JsfPhase() {
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
        Phase phase = new Phase();
        phase.setName(nome);
        phase.setDescription(descricao);
        phase.setAdmin(jsfAuth.getUser());
        if (new pdw.data.crud.CrudPhase().persist(phase) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao inserir dados", "Error"));
        }
        return null;
    }

    public String remove(Phase phase) {
        if (new pdw.data.crud.CrudPhase().remove(phase) == null) {
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
        Phase phase = new CrudPhase().find(id);
        phase.setName(nome);
        phase.setDescription(descricao);
        phase.setAdmin(jsfAuth.getUser());
        if (new pdw.data.crud.CrudPhase().merge(phase) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao inserir dados", "Error"));
        }
        return null;
    }
    
    public String edit(Phase phase){
        this.nome=phase.getName();
        this.descricao=phase.getDescription();
        this.id = phase.getId();
        return "crPhase.xhtml";
    }
   
    public Collection<Phase> getAll() {
        return new pdw.data.crud.CrudPhase().getAll();
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
