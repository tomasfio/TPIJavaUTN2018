package Main.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Entidades.Libro;
import Main.Entidades.Usuario;
import Main.Negocio.LibroLogic;
import Main.Util.Autentificacion;

/**
 * Servlet implementation class ListaMasVendidos
 */
@WebServlet("/ListaMasVendidos")
public class ListaMasVendidos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaMasVendidos() {
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
			LibroLogic ll = new LibroLogic();
			
			Integer[][] cantidad = ll.GetCantidadVendida();
			ArrayList<Libro> librosOrdenados = ll.GetLibrosOrdenadosVentas(cantidad[0]);

			request.setAttribute("listaLibros", librosOrdenados);
			request.setAttribute("ventas", cantidad[1]);
			
			request.getRequestDispatcher("admin-mas-vendidos.jsp").forward(request, response);
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
