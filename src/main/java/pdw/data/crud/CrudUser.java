/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.data.crud;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import static org.eclipse.persistence.expressions.ExpressionOperator.toUpperCase;
import pdw.data.model.User;

/**
 *
 * @author alexandrelerario
 */
public class CrudUser extends AbstractCrud<pdw.data.model.User> {

    private EntityManager em;

    public CrudUser() {
        super(pdw.data.model.User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;
    }
    

    
      public int getIdByEmail(String user_email) { // 21.04.2021
        try {
            User user = (User) getEntityManager().createNamedQuery("User.findByEmail").setParameter("email", user_email).getSingleResult();
                return user.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        
     }
      
    
      
     public String geraChave(String email){
       
         String chave;
         String codigo;
        
         if(getIdByEmail(email) == -1){
             return "";
         }
         User user = (User) getEntityManager().createNamedQuery("User.findByEmail").setParameter("email", email).getSingleResult();
        
         codigo =  new pdw.util.Util().getMd5(user.getPassword()+user.getId()).toUpperCase();
         chave = codigo;
         return chave;
     }

    // os metodos abaixo sao opcionais
    public User getAuth(String user_email, String password) {
        try {
            User user = (User) getEntityManager().createNamedQuery("User.findByEmail").setParameter("email", user_email).getSingleResult();
            String md5pass = new pdw.util.Util().getMd5(password).toUpperCase();
            if (user.getPassword().toUpperCase().equals(md5pass)) {
                return user;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    



    //sobrescrever persist para gravar a md5 da senha
    @Override
     public Exception persist(User entity) {
         entity.setPassword(new pdw.util.Util().getMd5(entity.getPassword()));
         entity.setCreated(new Date());
         return super.persist(entity);
     }
   
     //retornar o usuario administrador
     public User getAdmin(){
        List<User> users = new CrudUser().getAll();
        User admin = users.stream().min(Comparator.comparing(User::getId)).orElse(null);
        return admin;
    }
    
     /*
     algumas chaves para testar
    
    a
    0cc175b9c0f1b6a831c399e269772661
    
     abc123
e99a18c428cb38d5f260853678922e03

r2d2
3e0fd1ad8efb39d90b8cd3b04a6c94f1

Starwar5
7b03c175eb89d1a2640e0ebef6ea68a9
     
     */
}
