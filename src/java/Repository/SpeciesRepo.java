package Repository;

import Modell.Species;
import Modell.dbCon;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author bence
 */

public class SpeciesRepo {
    
    public static boolean addNewSpecies(Species s){
        try{
            EntityManager em = dbCon.getdbCon();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("speciesAdd");
            
            spq.registerStoredProcedureParameter("name_IN", String.class, ParameterMode.IN);
            spq.setParameter("name_IN", s.getName());
            
            spq.execute();
            em.close();
            return true;
            
        }catch(Exception ex){
            System.out.println("Repo - Error-> "+ex.getMessage());
            return false;
        }
    }
    
    public static boolean updateSpecies(Species s){
        try{
            EntityManager em = dbCon.getdbCon();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("speciesEdit");
            
            spq.registerStoredProcedureParameter("id_IN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("name_IN", String.class, ParameterMode.IN);
            
            spq.setParameter("id_IN", s.getSpeciesId());
            spq.setParameter("name_IN", s.getName());
            
            spq.execute();
            em.close();
            return true;
                    
        }catch(Exception ex){
            System.out.println("Repo - Error-> "+ex.getMessage());
            return false;
        }    
    }
    
    public static boolean SpeciesSetActive(Species s){
        try{
            EntityManager em = dbCon.getdbCon();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("speciesSetActive");
            
            spq.registerStoredProcedureParameter("id_IN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("isActive_IN", Boolean.class, ParameterMode.IN);
            
            spq.setParameter("id_IN", s.getSpeciesId());
            spq.setParameter("isActive_IN", s.getIsActive());
            
            spq.execute();
            em.close();
            return true;
                    
        }catch(Exception ex){
            System.out.println("Repo - Error-> "+ex.getMessage());
            return false;
        }    
    }
    
    
    public static List<Species> listSpecies(){
        try{
           EntityManager em = dbCon.getdbCon(); 
           StoredProcedureQuery spq = em.createStoredProcedureQuery("speciesList");
           
           List<Object[]> SpeciesObjectList = spq.getResultList();
           List<Species> SpeciesList = new ArrayList<>();
           
           for (Object[] SpeciesObject : SpeciesObjectList){
               Integer id = Integer.parseInt(SpeciesObject[0].toString());
               String name = SpeciesObject[1].toString();               
               Boolean isActive = null;
               if(SpeciesObject[2].toString().equals("true")) {
                   isActive = true;                 
               } else {
                   isActive = false;
               }
               
               Species entity = new Species(id,name,isActive);
               SpeciesList.add(entity);
           }
           em.close();
           System.out.println("Repo - listSpecies-> done");
           return SpeciesList;
           
        } catch (Exception ex){
            System.out.println("Repo - Error-> "+ex);
            return null;
        }    
    }
    
    public static JSONObject SpeciesCount(){
        try{
           EntityManager em = dbCon.getdbCon(); 
           StoredProcedureQuery spq = em.createStoredProcedureQuery("speciesCount");
           
           List<Object[]> SpeciesCountObjectList = spq.getResultList();
           JSONObject speciesCount= new JSONObject();
           JSONArray nameArray = new JSONArray();
           JSONArray countArray = new JSONArray();
           for (Object[] obj : SpeciesCountObjectList){
               String name = obj[0].toString();
               Integer count = Integer.parseInt(obj[1].toString());
               nameArray.put(name);
               countArray.put(count);
           }
           speciesCount.put("name", nameArray);
           speciesCount.put("count", countArray);
           em.close();
           return speciesCount;      
        }catch (Exception ex){
            System.out.println("Repo - Error-> "+ex);
            return null;
        }
    }
    
    
    
    
}
