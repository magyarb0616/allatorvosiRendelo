package Service;

import Modell.Animal;
import Repository.AnimalRepo;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author bence
 */
public class AnimalService {
    
    public static String AnimalAdd(Animal a, Integer ownerid, Integer speciesid){
        if(a.getName().length()>0 && a.getName().length()<=100){
            if(ownerid>0){
                if(speciesid>0){
                    if(AnimalRepo.AnimalAdd(a, ownerid, speciesid)){
                        return "Az állat regisztrálása sikeresen megtörtént!";
                    }else{return "A validáció sikeres, de az állatot nem sikerült regisztrálni!";}
                }else{return "A species ID nem megfelő!";}
            }else{return "Az owner ID nem megfelelő!";}
        }else{return "Nem megfelelő név!";}
    }
    
    
}
