
package Controller;

import Modell.Animal;
import Service.AnimalService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 * @author bence
 */
public class AnimalController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //add
            if(request.getParameter("task").equals("add")){
                JSONObject returnValue = new JSONObject();
                try{
                    if(!request.getParameter("name").isEmpty()
                       && !request.getParameter("birthdate").isEmpty()
                       && !request.getParameter("sex").isEmpty()
                       && !request.getParameter("species").isEmpty()
                       && !request.getParameter("owner").isEmpty()
                       && (request.getParameter("sex").equals("true") || request.getParameter("sex").equals("false"))){
                        
                        String name = request.getParameter("name");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = sdf.parse(request.getParameter("birthdate"));
                        Boolean sex = null;
                        if(request.getParameter("sex").equals("true")) {
                                  sex = true;                 
                              } else {sex = false;}
                        Integer species = Integer.parseInt(request.getParameter("species"));
                        Integer owner = Integer.parseInt(request.getParameter("owner"));
                        
                        Animal a = new Animal(0,name,date,sex);
                        String result = AnimalService.AnimalAdd(a, owner, species);
                        returnValue.put("result", result);      
                }else{
                      returnValue.put("result", "A mezők nincsenek megfelelően kitöltve!");   
                    }
                    out.print(returnValue.toString());  
                    
                }catch(Exception ex){
                    System.out.println("Controller - Error->"+ex.getMessage());
                }    
            }
            
            //update
             if(request.getParameter("task").equals("update")){
                JSONObject returnValue = new JSONObject();
                try{
                    if(!request.getParameter("id").isEmpty()
                       && !request.getParameter("name").isEmpty()
                       && !request.getParameter("birthdate").isEmpty()
                       && !request.getParameter("sex").isEmpty()
                       && !request.getParameter("lastVac").isEmpty()
                       && !request.getParameter("species").isEmpty()
                       && !request.getParameter("owner").isEmpty()
                       && (request.getParameter("sex").equals("true") || request.getParameter("sex").equals("false"))){
                        
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        String name = request.getParameter("name");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = sdf.parse(request.getParameter("birthdate"));
                        Boolean sex = null;
                        if(request.getParameter("sex").equals("true")) {
                                  sex = true;                 
                              } else {sex = false;}
                        Date lastVac = sdf.parse(request.getParameter("lastVac"));
                        Integer species = Integer.parseInt(request.getParameter("species"));
                        Integer owner = Integer.parseInt(request.getParameter("owner"));
                        
                        Animal a = new Animal(id,name,date,sex, lastVac);
                        String result = AnimalService.AnimalEdit(a, owner, species);
                        returnValue.put("result", result);      
                }else{
                      returnValue.put("result", "A mezők nincsenek megfelelően kitöltve!");   
                    }
                    out.print(returnValue.toString());  
                    
                }catch(Exception ex){
                    System.out.println("Controller - Error->"+ex.getMessage());
                }    
            }
            
             //set Active
             if(request.getParameter("task").equals("setActive")){
                JSONObject returnValue = new JSONObject();
                try{
                    if(!request.getParameter("id").isEmpty()
                       && !request.getParameter("isActive").isEmpty()
                       && (request.getParameter("isActive").equals("true") || request.getParameter("isActive").equals("false"))){
                        
                       Integer id = Integer.parseInt(request.getParameter("id"));
                    Boolean isActive = null;
                    if(request.getParameter("isActive").equals("true")) {
                            isActive = true;                 
                        } else {isActive = false;}
                    
                    Animal a = new Animal(id,isActive);
                    String result = AnimalService.AnimalSetActive(a);
                    returnValue.put("result", result);   
                    }else{
                        returnValue.put("result", "A mezők nincsenek megfelelően kitöltve!");
                    }
                    out.print(returnValue.toString());     
                }catch(Exception ex){
                    System.out.println("Controller - Error->"+ex.getMessage());
                }    
             }
             
             //List
             if(request.getParameter("task").equals("list")){
                JSONObject returnValue = new JSONObject();
                try{
                    if(AnimalService.AnimalList().isEmpty()){
                       returnValue.put("result", "Nincs megjeleníthető elem!"); 
                    }else{returnValue.put("result", AnimalService.AnimalList());}                    
                }catch(Exception ex){
                    System.out.println("Controller - Error -> "+ex.getMessage());
                }
                out.print(returnValue.toString());  
            }
             
            //vaccinate
            if(request.getParameter("task").equals("vaccinate")){
                JSONObject returnValue = new JSONObject();
                try{
                    if(!request.getParameter("id").isEmpty()){
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        String result = AnimalService.AnimalVacciante(id);
                        returnValue.put("result", result);
                    }
                }catch(Exception ex){
                    returnValue.put("result", "A mezők nincsenek megfelelően kitöltve!");
                    System.out.println("Controller - Error -> "+ex.getMessage());
                }
                out.print(returnValue.toString());
            }
            
        }catch(Exception ex){
            System.out.println("Controller - Error -> "+ex.getMessage());
        }
    }
    
//     if(request.getParameter("task").equals("list")){
//                JSONObject returnValue = new JSONObject();
//                try{
//                    returnValue.put("result", AnimalService.AnimalList());
//                }catch(Exception ex){
//                    System.out.println("Controller - Error -> "+ex.getMessage());
//                }
//                out.print(returnValue.toString());  
//            }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
