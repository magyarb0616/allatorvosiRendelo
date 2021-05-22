package Repository;

import Modell.Animal;
import Modell.dbCon;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    public static Boolean AnimalEdit(Animal a, Integer ownerid, Integer speciesid){
        try{
            EntityManager em = dbCon.getdbCon();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("animalEdit");
            
            spq.registerStoredProcedureParameter("id_IN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("name_IN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("birthdate_IN", Date.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("sex_IN", Boolean.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("last_vaccinated_IN", Date.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("species_IN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("owner_IN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("id_IN",a.getAnimalId());
            spq.setParameter("name_IN", a.getName());
            spq.setParameter("birthdate_IN", a.getBirthdate());
            spq.setParameter("sex_IN", a.getSex());
            spq.setParameter("last_vaccinated_IN", a.getLastVaccinated());
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
    
    public static Boolean AnimalSetActive(Animal a){
        try{
            EntityManager em = dbCon.getdbCon();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("animalSetActive");
            
            spq.registerStoredProcedureParameter("id_IN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("isActive_IN", Boolean.class, ParameterMode.IN);
            
            spq.setParameter("id_IN", a.getAnimalId());
            spq.setParameter("isActive_IN", a.getIsActive());
            
            spq.execute();
            em.close();
            return true;
            
        }catch(Exception ex){
            System.out.println("Repo - Error->"+ex.getMessage());
            return false;
        }
        
    }
    
    public static List<Animal> AnimalList() throws ParseException{
        try{
            EntityManager em = dbCon.getdbCon();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("animalList");

            List<Object[]> AnimalObjectList = spq.getResultList();
            List<Animal> AnimalList = new ArrayList<>();

            for (Object[] AnimalObject : AnimalObjectList){
                Integer id = Integer.parseInt(AnimalObject[0].toString());
                String name = AnimalObject[1].toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date birthdate = sdf.parse(AnimalObject[2].toString()); 
                Boolean sex = null;
                if(AnimalObject[3].toString().equals("true")){
                        sex = true;
                    }else{
                        sex = false;
                    }
                Date lastVac = sdf.parse(AnimalObject[4].toString());
                Boolean isActive = null;
                if(AnimalObject[5].toString().equals("true")){
                        isActive = true;
                    }else{
                        isActive = false;
                    }
    //            Integer species = Integer.parseInt(AnimalObject[6].toString());
    //            Integer owner = Integer.parseInt(AnimalObject[7].toString());
                Animal entity = new Animal(id,name,birthdate,sex,lastVac,isActive);
                AnimalList.add(entity);           
            }
            em.close();
            return AnimalList;
            
        }catch (Exception ex){
        System.out.println("Repo - Error-> "+ex);
        return null;
        }
        

    }
    
    public static Boolean AnimalVaccinate(Integer id){
        try{
            EntityManager em = dbCon.getdbCon();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("animalVaccinate");
            
            spq.registerStoredProcedureParameter("id_IN", Integer.class, ParameterMode.IN);
            spq.setParameter("id_IN", id);
            
            spq.execute();
            return true;
        }catch(Exception ex){
            System.out.println("Repo - Error->"+ex.getMessage());
            return false;
        }
        
    }
    
    
    
}
