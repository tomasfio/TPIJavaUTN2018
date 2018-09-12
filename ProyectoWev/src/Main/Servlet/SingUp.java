package Main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import Main.Datos.UsuarioData;
import Main.Entidades.Usuario;

/**
 * Servlet implementation class SingUp
 */
@WebServlet({ "/SingUp", "/singUp", "/singup", "/SINGUP" })
public class SingUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usu = new Usuario();
		usu.setUsuario(request.getParameter("usrname"));
		usu.setNombre(request.getParameter("nombre"));
		usu.setApellido(request.getParameter("apellido"));
		usu.setEmail("email");
		usu.setContraseña("psw");
		Date fechaActual = new Date();
		usu.setFechaDeAlta(fechaActual);
		usu.setTipoUsuario(0);
		
		UsuarioData usuData = new UsuarioData();
		
		if(usuData.GetByUserName(usu.getUsuario()))
		{

			if(usuData.Insert(usu))
			{
				//Mostrar mensaje de que usuario valido
			}
			else
			{
				//Mostrar mensaje que usuario invalido
			}
		}
		else
		{
			//Mostrar mensaje de que el usuario seleccionado ya fue usado
		}
	}

}
