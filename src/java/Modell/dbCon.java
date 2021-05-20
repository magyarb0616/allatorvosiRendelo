package Modell;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * @author bence
 */
public class dbCon {
    
    public EntityManager getdbCon(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllatorvosRendeloPU");
        EntityManager em = emf.createEntityManager();
        return em;
    }
}
