package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Bean.OutputBean;
import Bean.OutputBean_F;
import Bean.RoomBean;
import Bean.SingletonBean;
import Control.BuildingController;
import Control.ModelController;
import Control.RoomController;

public class SelezionaEdificioServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		ArrayList<OutputBean> rooms = new ArrayList<OutputBean>();
		ArrayList<OutputBean_F> models = new ArrayList<OutputBean_F>();
		RoomController rc = RoomController.getInstance();
		ModelController mc = ModelController.getInstance();
		BuildingController bc = BuildingController.getInstance();
		RoomBean rb = new RoomBean();
		String nomeedificio = "";
		String macroarea = "";
		int codiceedificio = Integer.parseInt(request.getParameter("codiceedificio"));
		
		
		try {
			
			nomeedificio = bc.getBuildingName(codiceedificio);
			macroarea = bc.getBuildingMacroarea(codiceedificio);
			rb.setNomeEdificio(nomeedificio);
			rb.setMacroarea(macroarea);
			SingletonBean.getSingletonInstance().setRoomBean(rb);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		switch(request.getParameter("text")) {
		
		case "creazioneStanza":
			try {
				rooms = rc.printRooms();
			} catch(Exception e){
				e.printStackTrace();
			}
			
			request.setAttribute("message",rooms);
			getServletConfig().getServletContext().getRequestDispatcher("/creaStanza.jsp").forward(request,response);
			
			
		case "creazioneStanzaDaModello":
			try {
				models = mc.printModels();
				rooms = rc.printRooms();
			} catch(Exception e){
				e.printStackTrace();
			}
			
			request.setAttribute("models",models);
			request.setAttribute("rooms",rooms);
			getServletConfig().getServletContext().getRequestDispatcher("/selezionaModello.jsp").forward(request,response);
		}

		
		
		
		
		
	}

}