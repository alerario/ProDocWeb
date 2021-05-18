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
import pdw.data.crud.CrudImagem;
import pdw.data.model.Images;
import pdw.data.model.User;

/**
 *
 * @author tiago
 */

@Named(value = "jsfImages")
@RequestScoped
public class JsfImages {

    /**
     * Creates a new instance of JsfImages
     */
    public JsfImages() {
    }

    private int id;
    private String nome;
    private String descricao;
    
    private List<FilterMeta> filterBy;


    @Inject
    private JsfAuth jsfAuth;

    @Inject JsfApp jsfApp;
    
    public String persist() {
        if(this.id!=0){
            //se ha id entao e um merge
            return merge();
        }
        Images img = new Images();
        img.setName(nome);
        img.setDescription(descricao);
        img.setAdmin(jsfAuth.getUser());
        if (new pdw.data.crud.CrudImagem().persist(img) == null) {
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

    public String remove(Images img) {
        if (new pdw.data.crud.CrudImagem().remove(img) == null) {
            //redireciona para a mesma pagina, reseta parametros e mantem a mensagem
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
        Images img = new CrudImagem().find(id);
        img.setName(nome);
        img.setDescription(descricao);
        img.setAdmin(jsfAuth.getUser());
        if (new pdw.data.crud.CrudImagem().merge  (img) == null) {
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
    
    public String edit(Images img){
        this.nome=img.getName();
        this.descricao=img.getDescription();
        this.id = img.getId();
        return "crImages.xhtml";
    }
   
    
    public Collection<Images> getAll() {
        return new pdw.data.crud.CrudImagem().getAll();
    }
    
    public Collection<User> getUsers() {
        Images img = new CrudImagem().find(id);
        return img.getUserCollection();
        //return jsfAuth.getSelectedOrganization().getUserCollection();
    }
    
    public String removeUser(User user) {
        
        System.out.println("User: " + user);
        
        Images img = new CrudImagem().find(id);
        img.removeUser(user);
        return merge();
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

