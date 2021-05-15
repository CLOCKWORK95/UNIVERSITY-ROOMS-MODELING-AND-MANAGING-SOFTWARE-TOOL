package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.UserBean;
import Control.UserController;

public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
//		importa i parametri dei campi input userId e password da login.jsp
		String userId = request.getParameter("userId"); 
		String password = request.getParameter("password");
				
		UserBean userbean = new UserBean();
		UserController lc = UserController.getInstance();
		userbean.setUsername(userId);
		userbean.setPassword(password);
		try {
			lc.login(userbean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userbean.getResult() == true){
			
			request.setAttribute("message", userbean.getUsername());
			getServletConfig().getServletContext().getRequestDispatcher("/secretaryMainPage.jsp").forward(request,response);
		}
		else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");  
		    out.println("<title>Login Failed</title>");  
			out.println("</head>");
			out.println("<body>");
			out.println("<center>");
			out.println("<h1>Login Failed</h1>");
			out.println("To try again<a href=login.jsp>Click here</a>");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
