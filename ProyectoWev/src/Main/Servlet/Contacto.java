package Main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Entidades.Usuario;
import Main.Negocio.CategoriaLogic;

/**
 * Servlet implementation class Contacto
 */
@WebServlet("/Contacto")
public class Contacto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Contacto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user") != null) {
			request.setAttribute("email", ((Usuario)request.getSession().getAttribute("user")).getEmail());
		}
		if(request.getAttribute("faltaDatos") != null)
			request.setAttribute("faltaDatos", true);
		if(request.getAttribute("falloEnvio") != null)
			request.setAttribute("falloEnvio", true);
		if(request.getAttribute("envioExitoso") != null)
			request.setAttribute("envioExitoso", true);

		CategoriaLogic cl = new CategoriaLogic();
		request.setAttribute("listaCategoria", cl.GetAll());
		request.getRequestDispatcher("contacto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
