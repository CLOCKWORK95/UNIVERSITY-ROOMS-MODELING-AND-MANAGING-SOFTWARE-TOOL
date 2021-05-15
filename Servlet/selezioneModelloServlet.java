package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.ModelBean;
import Bean.OutputBean_F;
import Bean.OutputBean_FOM;
import Bean.SingletonBean;
import Control.FeatureController;
import Control.ModelController;


public class selezioneModelloServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		ArrayList<OutputBean_FOM> FoM = new ArrayList<OutputBean_FOM>();
		ArrayList<OutputBean_F> features = new ArrayList<OutputBean_F>();
		
		ModelController mc = ModelController.getInstance();
		FeatureController fc = FeatureController.getInstance();
		ModelBean mb = new ModelBean();
		int codicemodello = Integer.parseInt(request.getParameter("codicemodello"));
		
		
		try {
			mb.setNomeModello(mc.getModel(codicemodello).getName());
			mb.setRoomGroup(mc.getModel(codicemodello).getRoomGroup());
			mb.setCodiceModello(codicemodello);
			SingletonBean.getSingletonInstance().setModelBean(mb);
			
			FoM = mc.printFeaturesOfModel(mb);
			features = fc.printFeatures();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		request.setAttribute("FOM",FoM);
		request.setAttribute("Feature",features);
		getServletConfig().getServletContext().getRequestDispatcher("/modificaModello.jsp").forward(request,response);
		
		
		
	}

}