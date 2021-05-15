package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Bean.OutputBean_F;
import Bean.SingletonBean;
import Bean.ModelBean;
import Control.ModelController;

public class CreazioneModelloServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		

		ArrayList<OutputBean_F> models = new ArrayList<OutputBean_F>();
		
		String nome = request.getParameter("nome"); 
		String roomgroup = request.getParameter("RoomGroup");
				
		ModelBean mb = new ModelBean();
		ModelController mc = ModelController.getInstance();
		mb.setNomeModello(nome);
		mb.setRoomGroup(roomgroup);
		SingletonBean.getSingletonInstance().setModelBean(mb);
		
		try {
			mc.createModel(mb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			models = mc.printModels();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("models", models);
		getServletConfig().getServletContext().getRequestDispatcher("/creareModello.jsp").forward(request,response);
		
		
		
		
		
	}

}