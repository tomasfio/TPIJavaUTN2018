package Main.Servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Main.Entidades.*;
import Main.Negocio.*;
import Main.Util.Autentificacion;

/**
 * Servlet implementation class altaUsuario
 */
@WebServlet({ "/altaUsuario", "/altausuario", "/AltaUsuario", "/Altausuario" })
public class altaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public altaUsuario() {
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
		Autentificacion aut = new Autentificacion();
		if(!aut.AutentificacionAdministrador((Usuario)request.getSession().getAttribute("user"))) {
    		response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
		else {
			Usuario usu = new Usuario();
			usu.setNombre(request.getParameter("nombre"));
			usu.setApellido(request.getParameter("apellido"));
			usu.setEmail(request.getParameter("email"));
			usu.setTipoUsuario(Integer.parseInt(request.getParameter("tipo_usu")));
			Date fechaActual = new Date();
			usu.setFechaDeAlta(fechaActual);
			usu.setUsuario(request.getParameter("usuario"));
			usu.setContraseña(request.getParameter("contrasenia"));
			
			UsuarioLogic ul = new UsuarioLogic();
			if(ul.GetByUserName(usu))
			{
				if(ul.Validar(usu)) {
					if(ul.Insert(usu)){
						request.setAttribute("exito", "El nombre de usuario elegido ya fue usado");
						request.getRequestDispatcher("ListaUsuario").forward(request, response);
					}
					else {
						request.setAttribute("error", "Hubo un problema y no se pudo registrar en la base de datos.");
						request.getRequestDispatcher("admin-alta-user.jsp").forward(request, response);
					}
				}
				else {
				}
				request.setAttribute("error", "Tiene que completar todos los campos para el registro\nLa contraseña debe tener 8 caracteres como minimo.");
				request.getRequestDispatcher("admin-alta-user.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("error", "El nombre de usuario elegido ya fue usado");
				request.getRequestDispatcher("admin-alta-user.jsp").forward(request, response);
			}
			
		}
	}

}
