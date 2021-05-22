package Repository;

import Modell.Animal;
import Modell.dbCon;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
/**
 * @author bence
 */
public class AnimalRepo {
    
    public static Boolean AnimalAdd(Animal a, Integer ownerid, Integer speciesid){
        try{
            EntityManager em = dbCon.getdbCon();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("animalAdd");
            
            spq.registerStoredProcedureParameter("name_IN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("birthdate_IN", Date.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("sex_IN", Boolean.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("species_IN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("owner_IN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("name_IN", a.getName());
            spq.setParameter("birthdate_IN", a.getBirthdate());
            spq.setParameter("sex_IN", a.getSex());
            spq.setParameter("species_IN", speciesid);
            spq.setParameter("owner_IN", ownerid);
            
            spq.execute();
            em.close();
            return true;        
            
        }catch(Exception ex){
            System.out.println("Repo - Error->"+ex.getMessage());
            return false;
        }
    }
    
    
    
    
}
