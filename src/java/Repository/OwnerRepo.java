package Repository;

import Modell.Owner;
import Modell.dbCon;
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
public class OwnerRepo {

    public static Boolean OwnerAdd(Owner o){
        try{
            EntityManager em = dbCon.getdbCon();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("ownerAdd");
            
            spq.registerStoredProcedureParameter("name_IN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("birthdate_IN", Date.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("phone_IN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("city_IN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("address_IN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("sex_IN", Boolean.class, ParameterMode.IN);
            
            spq.setParameter("name_IN", o.getName());
            spq.setParameter("birthdate_IN", o.getBirthdate());
            spq.setParameter("phone_IN", o.getPhone());
            spq.setParameter("city_IN", o.getCity());
            spq.setParameter("address_IN", o.getAddress());
            spq.setParameter("sex_IN", o.getSex());
            
            spq.execute();
            em.close();
            return true;
        }catch(Exception ex){
           System.out.println("Repo - Error->"+ex);
           return false; 
        }
    }
    
    public static Boolean OwnerUpdate(Owner o){
        try{
            EntityManager em = dbCon.getdbCon();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("ownerUpdate");
            
            spq.registerStoredProcedureParameter("id_IN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("name_IN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("birthdate_IN", Date.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("phone_IN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("city_IN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("address_IN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("sex_IN", Boolean.class, ParameterMode.IN);
            
            spq.setParameter("id_IN", o.getOwnerId());
            spq.setParameter("name_IN", o.getName());
            spq.setParameter("birthdate_IN", o.getBirthdate());
            spq.setParameter("phone_IN", o.getPhone());
            spq.setParameter("city_IN", o.getCity());
            spq.setParameter("address_IN", o.getAddress());
            spq.setParameter("sex_IN", o.getSex());
            
            spq.execute();
            em.close();
            return true;
  
        }catch(Exception ex){
           System.out.println("Repo - Error->"+ex);
           return false; 
        }
    }
    
    public static boolean OwnerSetActive(Owner o){
        try{
            EntityManager em = dbCon.getdbCon();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("ownerSetActive");
            
            spq.registerStoredProcedureParameter("id_IN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("isActive_IN", Boolean.class, ParameterMode.IN);
            
            spq.setParameter("id_IN", o.getOwnerId());
            spq.setParameter("isActive_IN", o.getIsActive());
            
            spq.execute();
            em.close();
            return true;
            
        }catch(Exception ex){
           System.out.println("Repo - Error->"+ex);
           return false; 
        }
    }
    
    public static List<Owner> OwnerList(){
        try{
            EntityManager em = dbCon.getdbCon();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("ownerList");
            
            List<Object[]> OwnerObjectList = spq.getResultList();
            List<Owner> OwnerList = new ArrayList<>();
            
            for (Object[] OwnerObject : OwnerObjectList){
                Integer id = Integer.parseInt(OwnerObject[0].toString());
                String name = OwnerObject[1].toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date birthdate = sdf.parse(OwnerObject[2].toString());
                String phone = OwnerObject[3].toString();
                String city = OwnerObject[4].toString();
                String address = OwnerObject[5].toString();
                Boolean sex = null;
                Boolean isActive = null;
                
                if(OwnerObject[6].toString().equals("true")){
                    sex = true;
                }else{
                    sex = false;
                }
                
                if(OwnerObject[7].toString().equals("true")){
                    isActive = true;
                }else{
                    isActive = false;
                }
                
                Owner entity = new Owner(id,name,birthdate,phone,city,address,sex,isActive);
                OwnerList.add(entity);
            }
            em.close();
            return OwnerList;
            
        }catch (Exception ex){
            System.out.println("Repo - Error-> "+ex);
            return null;
        }
    }
    
            
    
}
