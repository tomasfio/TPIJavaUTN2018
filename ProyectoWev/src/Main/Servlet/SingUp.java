package Main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import Main.Negocio.UsuarioLogic;
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
		usu.setEmail(request.getParameter("email"));
		usu.setContraseña(request.getParameter("psw"));
		Date fechaActual = new Date();
		usu.setFechaDeAlta(fechaActual);
		usu.setTipoUsuario(1);
		
		UsuarioLogic ul = new UsuarioLogic();
		if(ul.GetByUserName(usu))
		{
			if(ul.Validar(usu)) {
				if(ul.Insert(usu)){
					request.setAttribute("exito", "El nombre de usuario elegido ya fue usado");
					request.getRequestDispatcher("Index").forward(request, response);
				}
				else {
					request.setAttribute("error", "Hubo un problema y no se pudo registrar en la base de datos.");
					request.getRequestDispatcher("registro-login.jsp").forward(request, response);
				}
			}
			else {
			}
			request.setAttribute("error", "Tiene que completar todos los campos para el registro\nLa contraseña debe tener 8 caracteres como minimo.");
			request.getRequestDispatcher("registro-login.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("error", "El nombre de usuario elegido ya fue usado");
			request.getRequestDispatcher("registro-login.jsp").forward(request, response);
		}
	}

}
