package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.OutputBean;
import Control.RoomController;

public class viewStanzaServlet extends HttpServlet{
	

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		ArrayList<OutputBean> rooms = new ArrayList<OutputBean>();
		RoomController rc = RoomController.getInstance();
		
		try {
			rooms = rc.printRooms();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		switch(request.getParameter("text")) {
		
		case "modificaStanza":request.setAttribute("message", rooms);
								 getServletConfig().getServletContext().getRequestDispatcher("/selezionaStanza.jsp").forward(request,response);
		
		case "creazioneStanza":request.setAttribute("message", rooms);
		                        getServletConfig().getServletContext().getRequestDispatcher("/creaStanza.jsp").forward(request,response);   
		
		case "eliminaStanza":request.setAttribute("rooms", rooms);
							 getServletConfig().getServletContext().getRequestDispatcher("/eliminaStanza.jsp").forward(request,response);   
		
		case "tracciareStatoCaratteristiche":request.setAttribute("message", rooms);
		 									 getServletConfig().getServletContext().getRequestDispatcher("/selezionaStanzaTracciareStatoCaratteristiche.jsp").forward(request,response);
		}

	}

}