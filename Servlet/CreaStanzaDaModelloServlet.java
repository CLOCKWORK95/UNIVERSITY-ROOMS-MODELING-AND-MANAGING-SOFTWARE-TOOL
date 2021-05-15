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
import Bean.RoomBean;
import Bean.SingletonBean;
import Control.ModelController;
import Control.RoomController;

public class CreaStanzaDaModelloServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		ArrayList<OutputBean> rooms = new ArrayList<OutputBean>();
		ArrayList<OutputBean_F> models = new ArrayList<OutputBean_F>();
		
		ModelController mc = ModelController.getInstance();
		RoomController rc = RoomController.getInstance();
		RoomBean roombean = SingletonBean.getSingletonInstance().getRoomBean();
		ModelBean modelbean = SingletonBean.getSingletonInstance().getModelBean();

		roombean.setName(request.getParameter("nome"));

		
		try {
			rc.createRoomFromModel(modelbean,roombean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			rooms = rc.printRooms();
			models = mc.printModels();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("rooms", rooms);
		request.setAttribute("models", models);
		getServletConfig().getServletContext().getRequestDispatcher("/selezionaModello.jsp").forward(request,response);
		
		
		
		
		
	}

}