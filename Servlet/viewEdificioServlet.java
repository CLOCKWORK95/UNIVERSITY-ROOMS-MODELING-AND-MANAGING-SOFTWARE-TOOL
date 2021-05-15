package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.OutputBean_B;
import Control.BuildingController;

public class viewEdificioServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		ArrayList<OutputBean_B> buildings = new ArrayList<OutputBean_B>();
		BuildingController bc = BuildingController.getInstance();
		
		try {
			buildings = bc.printBuildings_B();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		switch(request.getParameter("text")) {
		
			case "creazioneEdificio":request.setAttribute("message", buildings);
								 getServletConfig().getServletContext().getRequestDispatcher("/creaEdificio.jsp").forward(request,response);
		
			case "creazioneStanza":request.setAttribute("message", buildings);
		                        getServletConfig().getServletContext().getRequestDispatcher("/selezionaEdificio.jsp").forward(request,response);   
		
		
        	case "creazioneStanzaDaModello":request.setAttribute("message", buildings);
          						getServletConfig().getServletContext().getRequestDispatcher("/selezionaEdificioCreaDaModello.jsp").forward(request,response);   
		
			case "eliminaEdificio" :request.setAttribute("message", buildings);
								getServletConfig().getServletContext().getRequestDispatcher("/eliminaEdificio.jsp").forward(request,response);

			case "modificaEdificio" :request.setAttribute("message", buildings);
								getServletConfig().getServletContext().getRequestDispatcher("/modificaEdificio.jsp").forward(request,response);
		}
		
		
		
	}

}