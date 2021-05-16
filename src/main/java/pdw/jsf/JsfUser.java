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
// email
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    // variaveis usadas para trocar senha
    private String chave;
    private String chaveCampo;
    private String segurancaEmail;



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
    
   public void sendEmail(String chave){
Properties props = new Properties();
    /** Parâmetros de conexão com servidor Gmail */
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class",
    "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");

    Session session = Session.getDefaultInstance(props,
      new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication()
           {
                 return new PasswordAuthentication("trabalhosoftwareblabla@gmail.com",
                 "oficina31");
           }
      });

    /** Ativa Debug para sessão */
    session.setDebug(true);

    try {

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("trabalhosoftwareblabla@gmail.com"));
      //Remetente

      Address[] toUser = InternetAddress //Destinatário(s)
                 .parse(getEmail());

      message.setRecipients(Message.RecipientType.TO, toUser);
      message.setSubject("Trocar senha ProdocWeb");//Assunto
      message.setText("Aqui esta sua chave, cole no navegador: "+chave);//texto
      /**Método para enviar a mensagem criada*/
      Transport.send(message);

     } catch (MessagingException e) {
        throw new RuntimeException(e);
    }


   }
     
    public String passValidator(){
        CrudUser cr = new CrudUser();

        if(!"".equals(cr.geraChave(getEmail()))){
            setChave(cr.geraChave(getEmail()));
            sendEmail(getChave());
            return "mudarSenha";
        }
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EMAIL INEXISTENTE", ""));
       return "";
    }
 

    
    public boolean changePassword(){
         String query;
         String newPass = getPassword();
         CrudUser cr = new CrudUser();

         if(!"".equals(cr.geraChave(getSegurancaEmail()))){
             setChave(cr.geraChave(getSegurancaEmail()));
         }
         else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EMAIL INEXISTENTE", ""));
             return false;
         }

        // verifica se a chave digitada é igual a do email
         if (!getChaveCampo().equals(getChave())){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CHAVE INVALIDA, VERIFIQUE SEU EMAIL.", ""));
             System.out.println("Email digitado:"+getSegurancaEmail());
             System.out.println("Chave enviada no email:"+getChave());
             System.out.println("Chave Campo:"+getChaveCampo());
             return true;
         }

        // proteção
          if("".equals(newPass)){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SENHA NAO PODE SER NULA.", ""));
             return true;
          }

         // método para trocar a senha
         //String md5pass = new pdw.util.Util().getMd5("123456").toUpperCase();
         cr.changePass(newPass,getSegurancaEmail());
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SENHA TROCADA COM SUCESSO.", ""));
         //query = "UPDATE swuser SET password = "+md5pass+" WHERE id = "+id+"";

        return true;
     }

    // public boolean changePassword(){
    //      CrudUser cr = new CrudUser();
    //      String query;
    //      int id;
    //      String newPass;
    //      id = cr.getIdByEmail(getEmail());
    //      newPass = getPassword();
         
    //      if(id == -1){ // não encontrou usuário
    //          return false;
    //      }
    //      if("".equals(newPass)){
    //          return false;
    //      }
    //      String md5pass = new pdw.util.Util().getMd5(newPass).toUpperCase();

    //      query = "UPDATE swuser SET password = "+md5pass+" WHERE id = "+id+"";

    //     return true;
    //  }

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

   public String getSegurancaEmail() {
        return segurancaEmail;
    }

    public void setSegurancaEmail(String segurancaEmail) {
        this.segurancaEmail = segurancaEmail;
    }


    public String getChave(){
        return chave;
    }

    public void setChave(String chave){
        this.chave = chave;
    }

    public String getChaveCampo(){
        return chaveCampo;
    }

    public void setChaveCampo(String chaveCampo){
        this.chaveCampo = chaveCampo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    




}
