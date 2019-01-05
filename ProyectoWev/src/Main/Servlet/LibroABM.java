package Main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Main.Entidades.*;
import Main.Negocio.*;
import Main.Util.Autentificacion;

/**
 * Servlet implementation class LibroABM
 */
@WebServlet("/LibroABM")
public class LibroABM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibroABM() {
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
			if(request.getParameter("btnUpdate") != null || request.getParameter("btnDelete") != null) {
				Libro libro = new Libro();
				libro.setISBN(Integer.parseInt(request.getParameter("isbn")));
				
				LibroLogic ll = new LibroLogic();
				libro = ll.GetOne(libro);
				if(libro != null) {
					request.setAttribute("libro", libro);
					if(request.getParameter("btnUpdate") != null) {
						request.setAttribute("accion", "update");;
					}
					else {
						request.setAttribute("accion", "delete");
					}
					request.getRequestDispatcher("admin-baja-modif-libro.jsp").forward(request,response);
				}
				else {
					request.setAttribute("existeLibro", false);
					request.getRequestDispatcher("ListaLibros").forward(request, response);
				}
			}
			else {
				request.getRequestDispatcher("FormAltaLibro").forward(request, response);
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
