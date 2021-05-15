package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Bean.OutputBean;
import Bean.RoomBean;
import Bean.SingletonBean;
import Control.RoomController;

public class CreazioneStanzaServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		

		ArrayList<OutputBean> rooms = new ArrayList<OutputBean>();
		String nome = request.getParameter("nome"); 
		String roomgroup = request.getParameter("RoomGroup");
				
		RoomBean rb = SingletonBean.getSingletonInstance().getRoomBean();
		RoomController rc = RoomController.getInstance();
		rb.setName(nome);
		rb.setRoomGroup(roomgroup);
		
		try {
			rc.createRoom(rb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			rooms = rc.printRooms();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("message", rooms);
		getServletConfig().getServletContext().getRequestDispatcher("/creaStanza.jsp").forward(request,response);
		
		
		
		
		
	}

}