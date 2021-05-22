package Controller;

import Modell.Species;
import Service.SpeciesService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
/**
 * @author bence
 */
public class SpeciesController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("applictaion/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
       
        try (PrintWriter out = response.getWriter()) {
            //Add
            if(request.getParameter("task").equals("add")){
                JSONObject returnValue = new JSONObject();
                
                if(!request.getParameter("name").isEmpty()){
                    String name = request.getParameter("name");
                    
                    Species s = new Species(0,name,true);
                    String result = SpeciesService.addNewSpecies(s);
                    returnValue.put("result", result);
                } else{
                    returnValue.put("result", "A mezők nincsenek megfelelően kitöltve!");
                }
                out.print(returnValue.toString());
            }
            
            //Update
            if(request.getParameter("task").equals("update")){
                JSONObject returnValue = new JSONObject();
                try{
                    if(!request.getParameter("id").isEmpty() && !request.getParameter("name").isEmpty()){
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        String name = request.getParameter("name");
                        
                        Species s = new Species(id,name,true);
                        String result = SpeciesService.updateSpecies(s);
                        returnValue.put("result", result);
                    }else{
                        returnValue.put("result", "A mezők nincsenek megfelelően kitöltve!");
                    }
                    out.print(returnValue.toString());
                }catch(Exception ex){
                    System.out.println("Controller - Error -> "+ex.getMessage());
                }
                
            }
            
            //set Active
            if(request.getParameter("task").equals("setActive")){
                JSONObject returnValue = new JSONObject();
                try{
                    if(!request.getParameter("id").isEmpty() && !request.getParameter("isActive").isEmpty() 
                            && ( request.getParameter("isActive").equals("false") || request.getParameter("isActive").equals("true")))
                    {                        
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Boolean isActive = null;
                        
                        if(request.getParameter("isActive").equals("true")) {
                            isActive = true;                 
                        } else {isActive = false;}                        
                        Species s = new Species(id,"idc",isActive);
                        String result = SpeciesService.SpeciesSetActive(s);
                        returnValue.put("result", result);
                    }else{
                        returnValue.put("result", "A mezők nincsenek megfelelően kitöltve!");
                    }
                    out.print(returnValue.toString());
                }catch(Exception ex){
                    System.out.println("Controller - Error -> "+ex.getMessage());
                }
                
            }
                       
            //List
            if(request.getParameter("task").equals("list")){
                JSONObject returnValue = new JSONObject();
                try{
                    returnValue.put("result", SpeciesService.listSpecies());
                }catch(Exception ex){
                    System.out.println("Controller - Error -> "+ex.getMessage());
                }
                out.print(returnValue.toString());  
            }
            
            //SpeciesCount
            if(request.getParameter("task").equals("speciesCount")){
                JSONObject returnValue = new JSONObject();
                try{
                    JSONObject speciesCount = SpeciesService.SpeciesCount();
                    returnValue.put("result", speciesCount);
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
