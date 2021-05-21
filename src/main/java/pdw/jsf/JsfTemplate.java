/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.jsf;

import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.Part;
import org.primefaces.model.FilterMeta;
import pdw.data.crud.CrudTemplate;
import pdw.data.model.Template;

/**
 *
 * @author Gabriel-CSS
 */
@Named(value = "jsfTemplate")
@RequestScoped
public class JsfTemplate {

    /**
     * Creates a new instance of JsfTemplate
     */
    public JsfTemplate() {
    }

    private int id;
    private String nome;
    private String descricao;
    private byte[] templatefile;
    private Part arquivo;
    
     private List<FilterMeta> filterBy;


    @Inject
    private JsfAuth jsfAuth;

    @Inject JsfApp jsfApp;
    
    public String persist() throws IOException {
        if(this.id!=0){
            return merge();
        }
        
        templatefile = toByteArrayUsingJava(arquivo.getInputStream());
        
        Template template = new Template();
        template.setName(nome);
        template.setDescription(descricao);
        template.setTemplatefile(templatefile);
        if (new pdw.data.crud.CrudTemplate().persist(template) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao inserir dados", "Error"));
        }
        return null;
    }

    public String remove(Template template) {
        if (new pdw.data.crud.CrudTemplate().remove(template) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir dados", "Error"));
        }
        return null;
    }
    
    public String merge() throws IOException{
        Template template = new CrudTemplate().find(id);
        
        templatefile = toByteArrayUsingJava(arquivo.getInputStream());
        
        template.setName(nome);
        template.setDescription(descricao);
        template.setTemplatefile(templatefile);
        if (new pdw.data.crud.CrudTemplate().merge  (template) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            return view.getViewId() + "?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao inserir dados", "Error"));
        }
        return null;
    }
    
    public String edit(Template template){
        this.nome=template.getName();
        this.descricao=template.getDescription();
        this.templatefile=template.getTemplatefile();
        this.id = template.getId();
        return "crTemplates.xhtml";
    }
    
    public byte[] toByteArrayUsingJava(InputStream is) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int reads = is.read();
        while(reads != -1){
            baos.write(reads);
            reads = is.read();
        }
        return baos.toByteArray();
    }
    
    public Collection<Template> getAll() {
        return new pdw.data.crud.CrudTemplate().getAll();
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
    
    public byte[] getTemplatefile() {
        return templatefile;
    }

    public void setTemplatefile(byte[] templatefile) {
        this.templatefile = templatefile;
    }
    
    public Part getArquivo() {
        return arquivo;
    }

    public void setArquivo(Part part) {
        this.arquivo = part;
    }
}
