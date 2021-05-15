package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.OutputBean;
import Bean.OutputBean_F;
import Bean.OutputBean_FOR;
import Bean.RoomBean;
import Bean.SingletonBean;
import Control.FeatureController;
import Control.RoomController;

public class SelezionaStanzaServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		ArrayList<OutputBean_FOR> FoR = new ArrayList<OutputBean_FOR>();
		ArrayList<OutputBean_F> features = new ArrayList<OutputBean_F>();
		ArrayList<OutputBean> rooms = new ArrayList<OutputBean>();
		RoomController rc = RoomController.getInstance();
		FeatureController fc = FeatureController.getInstance();
		RoomBean rb = new RoomBean();
		
		String nome = "";
		String roomgroup = "";
		String nomeedificio = "";
		String macroarea = "";
		
		int codicestanza = Integer.parseInt(request.getParameter("codicestanza"));
		
		
		
		
		try {
			nome = rc.getRoom(codicestanza).getName();
			roomgroup = rc.getRoom(codicestanza).getRoomGroup();
			nomeedificio = rc.getRoom(codicestanza).getBuilding().getName();
			macroarea = rc.getRoom(codicestanza).getBuilding().getMacroArea();
			rb.setName(nome);
			rb.setRoomGroup(roomgroup);
			rb.setNomeEdificio(nomeedificio);
			rb.setMacroarea(macroarea);
			rb.setRoomCode(codicestanza);
			SingletonBean.getSingletonInstance().setRoomBean(rb);
			
			FoR = rc.printFeaturesOfRoom(rb);
			features = fc.printFeatures();
			rooms = rc.printRooms();
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		switch(request.getParameter("text")) {
		
		case "modificaStanza":request.setAttribute("FOR",FoR);
							  request.setAttribute("Feature",features);
							  getServletConfig().getServletContext().getRequestDispatcher("/modificaStanza.jsp").forward(request,response);
		
		case "tracciareStatoCaratteristiche":request.setAttribute("FOR",FoR);
											 request.setAttribute("message", rooms);
											 getServletConfig().getServletContext().getRequestDispatcher("/selezionaStanzaTracciareStatoCaratteristiche.jsp").forward(request,response);
		
		}
		
		
		
	}

}