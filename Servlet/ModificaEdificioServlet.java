package Servlet;

import Bean.BuildingBean;
import Bean.OutputBean_B;
import Control.BuildingController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ModificaEdificioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<OutputBean_B> buildings = new ArrayList<OutputBean_B>();
        BuildingController bc = BuildingController.getInstance();
        BuildingBean bb = new BuildingBean();

        String newNomeEdificio = request.getParameter("newnomeedificio");
        String newMacroarea = request.getParameter("newmacroarea");

        bb.setNewNomeEdificio(newNomeEdificio);
        bb.setNewMacroarea(newMacroarea);


        int codiceedificio = Integer.parseInt(request.getParameter("codiceedificio"));


        try {

            bb.setNomeEdificio(bc.getBuildingName(codiceedificio));
            bb.setMacroarea(bc.getBuildingMacroarea(codiceedificio));

            if (newNomeEdificio != "") {
                bb.setNewNomeEdificio(newNomeEdificio);
                bc.buildingModifyName(bb);
            } else {
                bb.setNewNomeEdificio(bb.getNomeEdificio());
                bc.buildingModifyName(bb);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            if (newMacroarea != "") {
                bb.setNomeEdificio(newNomeEdificio);
                bb.setNewMacroarea(newMacroarea);
                bc.buildingModifyMacroarea(bb);
            } else {
                bb.setNewMacroarea(bb.getMacroarea());
                bc.buildingModifyMacroarea(bb);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            buildings = bc.printBuildings_B();
        } catch (Exception e) {
            e.printStackTrace();
        }


        request.setAttribute("message", buildings);
        getServletConfig().getServletContext().getRequestDispatcher("/modificaEdificio.jsp").forward(request,response);
    }

}