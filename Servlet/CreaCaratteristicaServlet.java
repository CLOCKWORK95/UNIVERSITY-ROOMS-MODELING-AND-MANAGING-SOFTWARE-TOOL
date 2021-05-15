package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Bean.FeatureBean;
import Bean.OutputBean_F;
import Control.FeatureController;

public class CreaCaratteristicaServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		

		ArrayList<OutputBean_F> features = new ArrayList<OutputBean_F>();
		String nome = request.getParameter("nomefeature"); 
		String descrizione = request.getParameter("descrizione");
				
		FeatureBean fb = new FeatureBean();
		FeatureController fc = FeatureController.getInstance();
		fb.setName(nome);
		fb.setDescription(descrizione);
		
		try {
			fc.createNewFeature(fb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			features = fc.printFeatures();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("message", features);
		getServletConfig().getServletContext().getRequestDispatcher("/creaCaratteristica.jsp").forward(request,response);
		
		
		
		
		
	}

}