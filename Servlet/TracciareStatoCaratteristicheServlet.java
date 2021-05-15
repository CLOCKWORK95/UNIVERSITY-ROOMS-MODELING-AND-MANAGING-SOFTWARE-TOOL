package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.FeatureBean;
import Bean.OutputBean;
import Bean.OutputBean_FOR;
import Bean.RoomBean;
import Bean.SingletonBean;
import Control.FeatureController;
import Control.RoomController;

public class TracciareStatoCaratteristicheServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		

		ArrayList<OutputBean_FOR> FoR = new ArrayList<OutputBean_FOR>();
		ArrayList<OutputBean> rooms = new ArrayList<OutputBean>();
		
		
		int codicefeature = Integer.parseInt(request.getParameter("codicefeature"));
				
		RoomController rc = RoomController.getInstance();
		FeatureController fc = FeatureController.getInstance();
		FeatureBean fb = new FeatureBean();
		RoomBean rb = SingletonBean.getSingletonInstance().getRoomBean();
		
		fb.setCodiceFeature(codicefeature);
		fb.setNumberOfInstances(Integer.parseInt(request.getParameter("num")));

		
		
		try {
			if (request.getParameter("operazione").equals("incrementa")) {
				fc.addFeatureDamagesInARoom(fb,rb);
			}
			
			else {
				fc.repairFeatureDamagesInARoom(fb,rb);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			FoR = rc.printFeaturesOfRoom(rb);
			rooms = rc.printRooms();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("FOR",FoR);
		request.setAttribute("message",rooms);
		getServletConfig().getServletContext().getRequestDispatcher("/selezionaStanzaTracciareStatoCaratteristiche.jsp").forward(request,response);
		
		
		
		
		
	}

}