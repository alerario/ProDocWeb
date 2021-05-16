package pdw.data.crud;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import pdw.data.model.User;

public class CrudImagem extends AbstractCrud<pdw.data.model.img> {

    private EntityManager em;

    public CrudImagem() {
        super(pdw.data.model.User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;
    }
}

public static void main(String[] args) {

    File f =  new File("test.jpg");
        String encodstring = encodeFileToBase64Binary(f);
        System.out.println(encodstring);
}

private static String encodeFileToBase64Binary(File file){
    String encodedfile = null;
    try {
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        encodedfile = Base64.encodeBase64(bytes).toString();
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    return encodedfile;
}