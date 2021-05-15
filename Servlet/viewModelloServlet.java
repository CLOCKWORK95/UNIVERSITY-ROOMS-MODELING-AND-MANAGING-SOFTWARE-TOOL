package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Bean.OutputBean_F;
import Control.ModelController;

public class viewModelloServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		ArrayList<OutputBean_F> models = new ArrayList<OutputBean_F>();
		ModelController mc = ModelController.getInstance();
		
		try {
			models = mc.printModels();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		switch(request.getParameter("text")) {
		
		case "modificaModello":request.setAttribute("models", models);
								 getServletConfig().getServletContext().getRequestDispatcher("/selezioneModello.jsp").forward(request,response);
		
		case "creazioneModello":request.setAttribute("models", models);
		                        getServletConfig().getServletContext().getRequestDispatcher("/creareModello.jsp").forward(request,response);   
		
		case "eliminaModello":request.setAttribute("models", models);
							 getServletConfig().getServletContext().getRequestDispatcher("/eliminaModello.jsp").forward(request,response);   
		
		
		
	}

}
}