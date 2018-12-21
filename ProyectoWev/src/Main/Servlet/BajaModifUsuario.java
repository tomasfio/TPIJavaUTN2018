package Main.Servlet;

import Main.Entidades.Usuario;
import Main.Negocio.UsuarioLogic;
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
		if(request.getParameter("btnDelete") != null) {
			Usuario usu = new Usuario();
			
			usu.setIdUsuario(Integer.parseInt(request.getParameter("id_baja")));
			
			UsuarioLogic ul = new UsuarioLogic();
			if(ul.Delete(usu))
			{
				request.setAttribute("bajaUsuario", true);
				request.getRequestDispatcher("ListaUsuario").forward(request, response);
			}
			else
			{
				request.setAttribute("bajaUsuario", false);
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
			if(ul.Update(usu)) {
				request.setAttribute("modificarUsuario", true);
				request.getRequestDispatcher("ListaUsuario").forward(request, response);
			}
			else {
				request.setAttribute("modificarUsuario", false);
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
