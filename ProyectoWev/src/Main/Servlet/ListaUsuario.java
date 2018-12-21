package Main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Main.Negocio.*;


/**
 * Servlet implementation class Usuario
 */
@WebServlet("/ListaUsuario")
public class ListaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UsuarioLogic ul = new UsuarioLogic();
		
		Main.Entidades.Usuario usu = new Main.Entidades.Usuario();
		
		usu.setTipoUsuario(request.getParameter("tipo_usuario") != null ? Integer.parseInt(request.getParameter("tipo_usuario")) : 0);
		
		request.setAttribute("listaUsuarios", ul.GetByTipoUsuario(usu));
		request.setAttribute("tipoUsuario", usu.getTipoUsuario());
		
		request.getRequestDispatcher("admin-cp-usuario.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
