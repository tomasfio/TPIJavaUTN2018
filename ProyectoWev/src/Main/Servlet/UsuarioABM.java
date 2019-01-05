package Main.Servlet;

import Main.Entidades.Usuario;
import Main.Entidades.Categoria;
import Main.Negocio.*;
import Main.Util.Autentificacion;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UsuarioABM
 */
@WebServlet({ "/UsuarioABM", "/Usuarioabm", "/UsuarioAbm" })
public class UsuarioABM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioABM() {
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
			 if(request.getParameter("btnUpdate") != null || request.getParameter("btnDelete") != null) 
			 {
				 Usuario usu = new Usuario();
				 usu.setIdUsuario(Integer.parseInt(request.getParameter("id_user")));
				 
				 UsuarioLogic ul = new UsuarioLogic();
				 Usuario usuario = ul.GetOne(usu);
				 if(usuario != null)
				 {
					 request.setAttribute("usuario", usuario);
					 if(request.getParameter("btnUpdate") != null)
					 {
						 request.setAttribute("accion", "update");
					 }
					 else
					 {
						 request.setAttribute("accion", "delete");
					 }
					 request.getRequestDispatcher("admin-baja-modif-user.jsp").forward(request, response);
				 }
				 else
				 {
					 request.setAttribute("existeUsuario", false);
					 request.getRequestDispatcher("ListaUsuario").forward(request, response);
				 }
			 }
			 else
			 {
				 request.getRequestDispatcher("admin-alta-user.jsp").forward(request, response);
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
