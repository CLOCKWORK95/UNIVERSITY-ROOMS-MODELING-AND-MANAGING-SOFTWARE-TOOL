package Servlet;

import Bean.FeatureBean;
import Bean.OutputBean_F;
import Control.FeatureController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class EliminaCaratteristicaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<OutputBean_F> features = new ArrayList<OutputBean_F>();
        FeatureController fc = FeatureController.getInstance();
        FeatureBean fb = new FeatureBean();

        int codicefeature = Integer.parseInt(request.getParameter("codicefeature"));


        try {
        	fb.setCodiceFeature(codicefeature);
        	fb.setName(fc.getFeature(codicefeature).getName());
        	fb.setDescription(fc.getFeature(codicefeature).getDescription());
        	
            fc.deleteFeature(fb);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            features = fc.printFeatures();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("message", features);
        getServletConfig().getServletContext().getRequestDispatcher("/eliminaCaratteristica.jsp").forward(request,response);
    }

}