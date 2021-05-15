package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.FeatureBean;
import Bean.OutputBean_F;
import Bean.OutputBean_FOR;
import Bean.RoomBean;
import Bean.SingletonBean;
import Control.FeatureController;
import Control.RoomController;

public class ModificaStanzaServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
//		Enumeration<String> parameterNames = request.getParameterNames();
//
//		while(parameterNames.hasMoreElements()) {
//			
//		   String paramName = parameterNames.nextElement();
//		   System.out.println(paramName);
//		   String[] paramValues = request.getParameterValues(paramName);
//		   for (int i = 0; i < paramValues.length; i++) {
//		      String paramValue = paramValues[i];
//		      System.out.println("  " + paramValue);
//		   }
//		}
		
		ArrayList<OutputBean_FOR> FoR = new ArrayList<OutputBean_FOR>();
		ArrayList<OutputBean_F> features = new ArrayList<OutputBean_F>();
		
		
		int codicefeature = Integer.parseInt(request.getParameter("codicefeature"));
		int numberOfInstances= Integer.parseInt(request.getParameter("numberofinstances_"+ codicefeature));
				
		
		RoomController rc =  RoomController.getInstance();
		FeatureController fc = FeatureController.getInstance();
		RoomBean roombean = SingletonBean.getSingletonInstance().getRoomBean();
		FeatureBean featurebean = new FeatureBean();

		featurebean.setCodiceFeature(codicefeature);
		featurebean.setNumberOfInstances(numberOfInstances);

		
		
		try {
			if (request.getParameter("text").equals("aggiungiCaratteristica")) {
				rc.insertFeatureIntoRoom(roombean,featurebean);
				FoR = rc.printFeaturesOfRoom(roombean);
				features = fc.printFeatures();
			}
			
			else {
				rc.removeFeatureFromRoom(roombean, featurebean);
				FoR = rc.printFeaturesOfRoom(roombean);
				features = fc.printFeatures();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("FOR",FoR);
		request.setAttribute("Feature",features);
		getServletConfig().getServletContext().getRequestDispatcher("/modificaStanza.jsp").forward(request,response);
		
		
		
		
		
	}

}