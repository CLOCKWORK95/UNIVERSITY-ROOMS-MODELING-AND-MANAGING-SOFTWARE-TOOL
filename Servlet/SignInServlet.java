package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.UserBean;
import Control.UserController;

public class SignInServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		

		String userId = request.getParameter("userId"); 
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirmPassword");
				
		UserBean userbean = new UserBean();
		UserController lc = UserController.getInstance();
		userbean.setUsername(userId);
		userbean.setPassword(password);
		
		if (password.equals(confirm) && password !="") {
			try {
				lc.signIn(userbean);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");  
		    out.println("<title>Sign</title>");  
			out.println("</head>");
			out.println("<body>");
			out.println("<center>");
			out.println(userbean.getText());
			out.println("To enter<a href=login.jsp>Click here</a>");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
		}
		
		else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");  
		    out.println("<title>Sign</title>");  
			out.println("</head>");
			out.println("<body>");
			out.println("<center>");
			out.println("the password fields were not equals!");
			out.println("To try again<a href=signIn.jsp>Click here</a>");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			
		}
		
	}

}