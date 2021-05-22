package Service;

import Modell.Animal;
import Modell.Owner;
import Repository.OwnerRepo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author bence
 */
public class OwnerService {
    public static String OwnerAdd(Owner o){
        if(o.getName().length()>0 && o.getName().length()<=100){
            if(o.getPhone().length()>=11 && o.getPhone().length()<=12){
                if(o.getCity().length()>0 && o.getCity().length()<=100){
                    if(o.getAddress().length()>0 && o.getAddress().length()<=100){
                        if(OwnerRepo.OwnerAdd(o)){
                            return "Az új tulajdonos felvétele sikeresen megtörtént!";
                        }else{return "A validáció sikeres, de a felvétel nem sikerült!";}
                    }else{return "Nem megfelelő cím!";}
                }else{return "Nem megfelelő város!";}
            }else{return "Nem magyar telefonszám!";}
        }else{return "Nem megfelelő név!";}
}
    
    public static String OwnerUpdate(Owner o){
        if(o.getOwnerId()>0){
            if(o.getName().length()>0 && o.getName().length()<=100){
                if(o.getPhone().length()>=11 && o.getPhone().length()<=12){
                    if(o.getCity().length()>0 && o.getCity().length()<=100){
                        if(o.getAddress().length()>0 && o.getAddress().length()<=100){
                            if(OwnerRepo.OwnerUpdate(o)){
                                return "A tulajdonos módosítása sikeresen megtörtént!";
                            }else{return "A validáció sikeres, de a módosítás nem sikerült!";}
                        }else{return "Nem megfelelő cím!";}
                    }else{return "Nem megfelelő város!";}
                }else{return "Nem magyar telefonszám!";}
            }else{return "Nem megfelelő név!";}
       }else{return "Nem megfelelő ID!";}
}    
    
    
    public static String OwnerSetActive(Owner o){
        if(o.getOwnerId()>0){
            if(OwnerRepo.OwnerSetActive(o)){
                return "A módosítás sikeresen megtörtént!";
            }else {return "A validáció sikeres, de a módosítás nem sikerült!";}
        }else{return "Nem megfelő ID!";}
    }
    
    public static List<Owner> OwnerList(){
        return OwnerRepo.OwnerList();
    }
    
    public static List<Animal> OwnerAnimals(Owner o){
        return OwnerRepo.OwnerAnimals(o);
    }
    
    
    
}
