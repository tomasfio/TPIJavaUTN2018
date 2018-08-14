package Main.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Main.Entidades.*;
import Main.Datos.*;

@WebServlet({"/loginUsu","/loginusu"})
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public loginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		
			response(response,"Login fallido");
		
	}
	
	private void response(HttpServletResponse resp, String msg)
			throws IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<t1>" + msg + "</t1>");
		out.println("</body>");
		out.println("</html>");
	}


}
