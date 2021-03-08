/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author alexandrelerario
 */
public class Util {

    public  String getMd5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            return myHash;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

    }
    
    
    /*
     algumas chaves
    
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
