package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.FeatureBean;
import Bean.ModelBean;
import Bean.OutputBean_F;
import Bean.OutputBean_FOM;
import Bean.SingletonBean;
import Control.FeatureController;
import Control.ModelController;


public class ModificaModelloServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		

		ArrayList<OutputBean_FOM> FoM = new ArrayList<OutputBean_FOM>();
		ArrayList<OutputBean_F> features = new ArrayList<OutputBean_F>();
		
		
		int codicefeature = Integer.parseInt(request.getParameter("codicefeature"));
		int numberOfInstances= Integer.parseInt(request.getParameter("numberofinstances_"+ codicefeature));
				
		
		ModelController mc =  ModelController.getInstance();
		FeatureController fc = FeatureController.getInstance();
		ModelBean modelbean = SingletonBean.getSingletonInstance().getModelBean();
		FeatureBean featurebean = new FeatureBean();

		featurebean.setCodiceFeature(codicefeature);
		featurebean.setNumberOfInstances(numberOfInstances);

		
		
		try {
			if (request.getParameter("text").equals("aggiungiCaratteristica")) {
				mc.insertFeatureIntoModel(modelbean,featurebean);
				FoM = mc.printFeaturesOfModel(modelbean);
				features = fc.printFeatures();
			}
			
			else {
				mc.removeFeatureFromModel(modelbean, featurebean);
				FoM = mc.printFeaturesOfModel(modelbean);
				features = fc.printFeatures();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("FOM",FoM);
		request.setAttribute("Feature",features);
		getServletConfig().getServletContext().getRequestDispatcher("/modificaModello.jsp").forward(request,response);
		
		
		
		
		
	}

}