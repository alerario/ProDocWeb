/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.data.crud;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.xml.bind.DatatypeConverter;
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

    // os metodos abaixo sao opcionais
    public User getAuth(String user_email, String password) {
        try {
            User user = (User) getEntityManager().createNamedQuery("User.findByEmail").setParameter("email", user_email).getSingleResult();
            String md5pass = getMd5(password);
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

    private String getMd5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

            return myHash;
// m.update(pass.getBytes(), 0, pass.length());
            // return Arrays.toString(m.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
    /*
     algumas chaves
     abc123
e99a18c428cb38d5f260853678922e03

r2d2
3e0fd1ad8efb39d90b8cd3b04a6c94f1

Starwar5
7b03c175eb89d1a2640e0ebef6ea68a9
     
     */

}
