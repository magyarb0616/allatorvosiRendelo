package Service;

import Modell.Species;
import Repository.SpeciesRepo;
import java.util.List;

/**
 * @author bence
 */
public class SpeciesService {
    
    
    public static String addNewSpecies(Species s){
        if(s.getName().length()>0 && s.getName().length()<=100){
            if(SpeciesRepo.addNewSpecies(s)){
                return "Az új faj sikeresen rögzítésre került!";
            } else{return "A validáció sikeres, de az adatok nem kerültek rögzítésre!";}
        } else{return "A név nem megfelelő hosszúságú!";}
        
    }
    
    public static String updateSpecies(Species s){
        if(s.getName().length()>0 && s.getName().length()<=100){
            if(s.getSpeciesId()>0){
                if(SpeciesRepo.updateSpecies(s)){
                  return "A módósítás sikeresen megtörtént!";
                } else{return "A validáció sikeres, de az adatok nem kerültek rögzítésre!";}
            }else{return "Az ID nem lehet 0-nál kisebb!";}
        }else{return "A név nem megfelelő hosszúságú!";}
    }
    
    public static String SpeciesSetActive(Species s){
        if(s.getSpeciesId()>0){
            if(SpeciesRepo.SpeciesSetActive(s)){
                  return "A módósítás sikeresen megtörtént!";
                } else{return "A validáció sikeres, de az adatok nem kerültek rögzítésre!";}
        }else{return "Az ID nem lehet 0-nál kisebb!";}
    }
    
    public static List<Species> listSpecies(){
        return SpeciesRepo.listSpecies();
    }
    
    
}