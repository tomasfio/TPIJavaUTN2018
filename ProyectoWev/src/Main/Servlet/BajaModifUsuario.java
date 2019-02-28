package Main.Servlet;

import Main.Entidades.Usuario;
import Main.Negocio.UsuarioLogic;
import Main.Util.Autentificacion;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class BajaModifUsuario
 */
@WebServlet({ "/BajaModifUsuario", "/bajamodifusuario", "/BajaModifusuario" })
public class BajaModifUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BajaModifUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Autentificacion aut = new Autentificacion();
		if(!aut.AutentificacionAdministrador((Usuario)request.getSession().getAttribute("user"))) {
    		response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
		else {
			if(request.getParameter("btnDelete") != null) {
				Usuario usu = new Usuario();
				
				usu.setIdUsuario(Integer.parseInt(request.getParameter("id_baja")));
				
				UsuarioLogic ul = new UsuarioLogic();
				if(ul.ValidarDelete(usu)) {
					if(ul.Delete(usu))
					{
						request.setAttribute("bajaUsuario", true);
						request.getRequestDispatcher("ListaUsuario").forward(request, response);
					}
					else
					{
						Usuario usuario = ul.GetOne(usu);
						request.setAttribute("usuario", usuario);
						request.setAttribute("accion", "delete");
						request.setAttribute("error", "Hubo un error y no se pudo dar de baja el usuario");
						request.getRequestDispatcher("admin-baja-modif-user.jsp").forward(request, response);
					}
				}
				else {
					request.setAttribute("error", "No se puede borrar el usuario porque tiene registros asociados");
					request.getRequestDispatcher("ListaUsuario").forward(request, response);
				}
			}
			else if(request.getParameter("btnUpdate") != null) {
				Usuario usu = new Usuario();
				usu.setIdUsuario(Integer.parseInt(request.getParameter("id_modificar")));
				usu.setNombre(request.getParameter("nombre_modificar"));
				usu.setApellido(request.getParameter("apellido_modificar"));
				usu.setEmail(request.getParameter("email_modificar"));
				usu.setUsuario(request.getParameter("usuario_modificar"));
				
				UsuarioLogic ul = new UsuarioLogic();
				if(ul.ValidarUpdate(usu)) {
					if(ul.Update(usu)) {
						request.setAttribute("modificarUsuario", true);
						request.getRequestDispatcher("ListaUsuario").forward(request, response);
					}
					else {
						Usuario usuario = ul.GetOne(usu);
						request.setAttribute("usuario", usuario);
						request.setAttribute("accion", "update");
						request.setAttribute("error", "Hubo un error y no se pudo dar de modificar el usuario el usuario");
						request.getRequestDispatcher("admin-baja-modif-user.jsp").forward(request, response);
					}
				}
				else {
					Usuario usuario = ul.GetOne(usu);
					request.setAttribute("usuario", usuario);
					request.setAttribute("accion", "update");
					request.setAttribute("error", "Hay campos con datos incompletos y/o incorrectos");
					request.getRequestDispatcher("admin-baja-modif-user.jsp").forward(request, response);
				}
			}
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
