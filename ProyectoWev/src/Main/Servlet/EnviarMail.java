package Main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Util.*;
/**
 * Servlet implementation class EnviarMail
 */
@WebServlet("/EnviarMail")
public class EnviarMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnviarMail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mailContacto = request.getParameter("email");
		String asunto = request.getParameter("asunto");
		String texto = request.getParameter("texto");
		
		if(mailContacto.isEmpty() || asunto.isEmpty() || texto.isEmpty()) {
			request.setAttribute("faltaDatos", true);
			request.getRequestDispatcher("Contacto").forward(request, response);
		}
		else {
			String body = "Se a contactado desde el mail " + mailContacto + " para la siguiente consulta" + 
			"\n" + texto;
			
			Email email;
			try {
				email = Email.getInstance();
				email.send("pruebajava2018@gmail.com", asunto, body);
			} catch (Exception e) {
				request.setAttribute("falloEnvio", true);
				request.getRequestDispatcher("Contacto").forward(request, response);
			}
			request.setAttribute("envioExitoso", true);
			request.getRequestDispatcher("Contacto").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
