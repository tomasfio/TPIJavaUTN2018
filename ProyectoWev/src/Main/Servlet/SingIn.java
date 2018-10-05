package Main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Main.Entidades.*;
import Main.Negocio.*;


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
		UsuarioLogic ul = new UsuarioLogic();
		Usuario usu = new Usuario();
		usu.setUsuario(request.getParameter("usuario"));
		usu.setContraseña(request.getParameter("pass"));
		
		usu = ul.GetByUsuCon(usu);
		if (usu != null)
		{
			
		}
		else 
		{
			System.out.println("Usuario y/o contraseña incorrecta");
		}
	}

}
