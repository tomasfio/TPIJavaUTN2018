package Main.Servlet;

import Main.Entidades.Usuario;
import Main.Negocio.CategoriaLogic;
import Main.Util.Autentificacion;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListaCategorias
 */
@WebServlet("/ListaCategorias")
public class ListaCategorias extends HttpServlet {
	private static final long serialVersionUID = 1;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaCategorias() {
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
			CategoriaLogic ctrl = new CategoriaLogic();
			request.setAttribute("ListaCategoria", ctrl.GetAll());
			request.getRequestDispatcher("admin-cp-categoria.jsp").forward(request,response);
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
