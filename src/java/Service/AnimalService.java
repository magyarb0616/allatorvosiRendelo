package Service;

import Modell.Animal;
import Repository.AnimalRepo;
import java.text.ParseException;
import java.util.List;
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
    
    public static String AnimalEdit(Animal a, Integer ownerid, Integer speciesid){
        if(a.getName().length()>0 && a.getName().length()<=100){
            if(ownerid>0){
                if(speciesid>0){
                    if(a.getAnimalId()>0){
                        if(AnimalRepo.AnimalEdit(a, ownerid, speciesid)){
                            return "Az állat adatainak módosítása sikeresen megtörtént!";
                        }else{return "A validáció sikeres, de az állat adatait nem sikerült módosítani!";}
                    }else{return "Az animal ID nem megfelő!";}
                }else{return "A species ID nem megfelő!";}
            }else{return "Az owner ID nem megfelelő!";}
        }else{return "Nem megfelelő név!";}
    }
    
    public static String AnimalSetActive(Animal a){
        if(a.getAnimalId()>0){
            if(AnimalRepo.AnimalSetActive(a)){
                return "Az állat módosítása sikeresen megtörtént!";
            }else{return "A validáció sikeres, de az állatot nem sikerült módosítani!";}
        }else {return "Az animal ID nem megfelő!";}
    }
    
    public static List<Animal> AnimalList() throws ParseException{
        return AnimalRepo.AnimalList();
    }
    
}
