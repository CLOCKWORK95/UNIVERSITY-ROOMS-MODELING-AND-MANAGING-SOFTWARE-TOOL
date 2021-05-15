package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.BuildingBean;
import Bean.OutputBean_B;
import Control.BuildingController;

public class CreaEdificioServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		

		ArrayList<OutputBean_B> buildings = new ArrayList<OutputBean_B>();
		String nomeEdificio = request.getParameter("nomeedificio"); 
		String macroarea = request.getParameter("macroarea");
				
		BuildingBean bb = new BuildingBean();
		BuildingController bc = BuildingController.getInstance();
		bb.setNomeEdificio(nomeEdificio);
		bb.setMacroarea(macroarea);
		
		try {
			bc.createBuilding(bb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			buildings = bc.printBuildings_B();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("message", buildings);
		getServletConfig().getServletContext().getRequestDispatcher("/creaEdificio.jsp").forward(request,response);
		
		
		
		
		
	}

}