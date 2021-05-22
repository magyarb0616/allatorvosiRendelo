package Controller;

import Modell.Owner;
import Service.OwnerService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
/**
 * @author bence
 */
public class OwnerController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //Add
            if(request.getParameter("task").equals("add")){
                JSONObject returnValue = new JSONObject();
                try{
                    if(!request.getParameter("name").isEmpty()
                       &&!request.getParameter("birthdate").isEmpty()
                       &&!request.getParameter("phone").isEmpty()
                       &&!request.getParameter("phone").isEmpty()
                       &&!request.getParameter("city").isEmpty()
                       &&!request.getParameter("address").isEmpty()
                       &&!request.getParameter("sex").isEmpty()
                       &&(request.getParameter("sex").equals("true") || request.getParameter("sex").equals("false")))
                  {
                    String name = request.getParameter("name");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = sdf.parse(request.getParameter("birthdate"));
                    String phone = request.getParameter("phone");
                    String city = request.getParameter("city");
                    String address = request.getParameter("address");
                    Boolean sex = null;
                    if(request.getParameter("sex").equals("true")) {
                              sex = true;                 
                          } else {sex = false;}

                    Owner o = new Owner(0,name,date,phone,city,address,sex);  
                    String result = OwnerService.OwnerAdd(o);
                    returnValue.put("result", result);
                  }else{
                      returnValue.put("result", "A mezők nincsenek megfelelően kitöltve!");
                  }
                  out.print(returnValue.toString());  
                }catch(Exception ex){
                    System.out.println("Controller - Error->"+ex.getMessage());
                }    
            }
            
           //Update            
          if(request.getParameter("task").equals("update")){
                JSONObject returnValue = new JSONObject();
                try{
                    if(!request.getParameter("name").isEmpty()
                       &&!request.getParameter("birthdate").isEmpty()
                       &&!request.getParameter("phone").isEmpty()
                       &&!request.getParameter("phone").isEmpty()
                       &&!request.getParameter("city").isEmpty()
                       &&!request.getParameter("address").isEmpty()
                       &&!request.getParameter("sex").isEmpty()
                       &&(request.getParameter("sex").equals("true") || request.getParameter("sex").equals("false"))
                       &&!request.getParameter("id").isEmpty())
                  {
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    String name = request.getParameter("name");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = sdf.parse(request.getParameter("birthdate"));
                    String phone = request.getParameter("phone");
                    String city = request.getParameter("city");
                    String address = request.getParameter("address");
                    Boolean sex = null;
                    if(request.getParameter("sex").equals("true")) {
                              sex = true;                 
                          } else {sex = false;}

                    Owner o = new Owner(id,name,date,phone,city,address,sex);  
                    String result = OwnerService.OwnerUpdate(o);
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
                  if(!request.getParameter("id").isEmpty() && !request.getParameter("isActive").isEmpty()
                     && (request.getParameter("isActive").equals("true") || request.getParameter("isActive").equals("false")))
                  {                      
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    Boolean isActive = null;
                    if(request.getParameter("isActive").equals("true")) {
                            isActive = true;                 
                        } else {isActive = false;}
                    
                    
                    Owner o = new Owner(id, isActive);
                    String result = OwnerService.OwnerSetActive(o);
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
                    returnValue.put("result", OwnerService.OwnerList());
                }catch(Exception ex){
                    System.out.println("Controller - Error -> "+ex.getMessage());
                }
                out.print(returnValue.toString());  
            }
    
          //owner animals
          if(request.getParameter("task").equals("ownerAnimals")){
                JSONObject returnValue = new JSONObject();
                try{
                    if(!request.getParameter("id").isEmpty()){
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Owner o = new Owner(id);
                        returnValue.put("result", OwnerService.OwnerAnimals(o));                       
                    }else{
                        returnValue.put("result", "A mezők nem megfelelően vannak kitöltve!");
                    }
          
                }catch(Exception ex){
                    System.out.println("Controller - Error -> "+ex.getMessage());
                }
                out.print(returnValue.toString());  
            }
          
          
          
          
        }catch(Exception ex){
            System.out.println("Controller - Error -> "+ex.getMessage());
        }
        
        
        
        
    }

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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
