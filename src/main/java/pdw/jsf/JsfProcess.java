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
import pdw.data.crud.CrudProcess;
import pdw.data.model.Process;

/**
 *
 * @author InfinitePondera
 */
@Named(value = "jsfProcess")
@RequestScoped
public class JsfProcess {

    /**
     * Creates a new instance of JsfProcess
     */
    public JsfProcess() {
    }

    private int id;
    private String nome;
    private String descricao;

    private List<FilterMeta> filterBy;

    @Inject
    private JsfAuth jsfAuth;

    @Inject
    JsfApp jsfApp;

    public String persist() {
        if (this.id != 0) {
            return merge();
        }
        Process process = new Process();
        process.setName(nome);
        process.setDescription(descricao);
        // process.setAdmin(jsfAuth.getUser());
        if (new pdw.data.crud.CrudProcess().persist(process) == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao inserir dados", "Error"));
        }
        return null;
    }

    public String remove(Process process) {
        if (new pdw.data.crud.CrudProcess().remove(process) == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir dados", "Error"));
        }
        return null;
    }

    public String merge() {
        Process process = new CrudProcess().find(id);
        process.setName(nome);
        process.setDescription(descricao);
        // process.setAdmin(jsfAuth.getUser());
        if (new pdw.data.crud.CrudProcess().merge(process) == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao inserir dados", "Error"));
        }
        return null;
    }

    public String edit(Process process) {
        this.nome = process.getName();
        this.descricao = process.getDescription();
        this.id = process.getId();
        return "crProcess.xhtml";
    }

    public Collection<Process> getAll() {
        return new pdw.data.crud.CrudProcess().getAll();
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
