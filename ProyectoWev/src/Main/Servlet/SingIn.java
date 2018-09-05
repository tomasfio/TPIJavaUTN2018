package Main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Main.Entidades.*;
import Main.Datos.*;


/**
 * Servlet implementation class SingIn
 */
@WebServlet({ "/SingIn", "/singin", "/SINGIN" })
public class SingIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioData usuData = new UsuarioData();
		Usuario usu = usuData.GetByUsuCon(request.getParameter("usuario"), request.getParameter("pass"));
		if (usu != null)
		{
			System.out.println("Se conceto al servlet");
		}
		else 
		{
			System.out.println("Usuario y/o contrase�a incorrecta");
		}
	}

}
