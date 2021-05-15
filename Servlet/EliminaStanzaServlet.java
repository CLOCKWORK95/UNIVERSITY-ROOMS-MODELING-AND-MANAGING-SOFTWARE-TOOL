package Servlet;

import Bean.OutputBean;
import Control.RoomController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class EliminaStanzaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<OutputBean> rooms = new ArrayList<OutputBean>();
        RoomController rc = RoomController.getInstance();
        int codicestanza = Integer.parseInt(request.getParameter("codicestanza"));


        try {
            rc.deleteRoom(codicestanza);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            rooms = rc.printRooms();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("rooms", rooms);
        getServletConfig().getServletContext().getRequestDispatcher("/eliminaStanza.jsp").forward(request,response);
    }

}