package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.ModelBean;
import Bean.OutputBean;
import Bean.OutputBean_F;
import Bean.OutputBean_FOM;
import Bean.SingletonBean;
import Control.ModelController;
import Control.RoomController;

public class ViewFOMServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		
		ArrayList<OutputBean_FOM> FoM = new ArrayList<OutputBean_FOM>();
		ArrayList<OutputBean_F> models = new ArrayList<OutputBean_F>();
		ArrayList<OutputBean> rooms = new ArrayList<OutputBean>();
		
		
		int codicemodello = Integer.parseInt(request.getParameter("codicemodello"));
		RoomController rc = RoomController.getInstance();
		ModelController mc = ModelController.getInstance();
		ModelBean mb = new ModelBean();
		mb.setCodiceModello(codicemodello);
		
		
		try {
			
			mb.setNomeModello(mc.getModel(codicemodello).getName());
			mb.setRoomGroup(mc.getModel(codicemodello).getRoomGroup());
			SingletonBean.getSingletonInstance().setModelBean(mb);
			
			FoM = mc.printFeaturesOfModel(mb);
			models = mc.printModels();
			rooms = rc.printRooms();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("FOM",FoM);
		request.setAttribute("models",models);
		request.setAttribute("rooms",rooms);
		
		switch(request.getParameter("text")) {
		case ("creazioneStanzaDaModello"):
			getServletConfig().getServletContext().getRequestDispatcher("/selezionaModello.jsp").forward(request,response);
		
		case("modificaModello"):
			getServletConfig().getServletContext().getRequestDispatcher("/selezioneModello.jsp").forward(request,response);
		}
		
		
		
		
		
		
	}

}