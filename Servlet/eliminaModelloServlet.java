package Servlet;



import Bean.ModelBean;
import Bean.OutputBean_F;
import Control.ModelController;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class eliminaModelloServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	 ArrayList<OutputBean_F> models = new ArrayList<OutputBean_F>();
         ModelController mc = ModelController.getInstance();
         ModelBean mb = new ModelBean();
         
         int codicemodello = Integer.parseInt(request.getParameter("codicemodello"));
         


         try {
        	 mb.setCodiceModello(codicemodello);
             mc.deleteModel(mb);
         } catch (Exception e) {
             e.printStackTrace();
         }


         try {
             models = mc.printModels();
         } catch (Exception e) {
             e.printStackTrace();
         }

         request.setAttribute("models", models);
         getServletConfig().getServletContext().getRequestDispatcher("/eliminaModello.jsp").forward(request,response);
     }

 }