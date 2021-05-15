package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Bean.OutputBean_F;
import Control.FeatureController;

public class ViewFeaturesServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		ArrayList<OutputBean_F> features = new ArrayList<OutputBean_F>();
		FeatureController fc = FeatureController.getInstance();
		
		try {
			features = fc.printFeatures();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		switch(request.getParameter("text")) {
		
		case "creazioneCaratteristica":request.setAttribute("message", features);
								 getServletConfig().getServletContext().getRequestDispatcher("/creaCaratteristica.jsp").forward(request,response);
		
		case "eliminazioneCaratteristica":request.setAttribute("message", features);
		                        getServletConfig().getServletContext().getRequestDispatcher("/eliminaCaratteristica.jsp").forward(request,response);   
		
		}
		
		
		
	}

}