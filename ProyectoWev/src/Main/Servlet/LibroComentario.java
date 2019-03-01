package Main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Negocio.*;
import Main.Entidades.*;

/**
 * Servlet implementation class LibroComentario
 */
@WebServlet("/LibroComentario")
public class LibroComentario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibroComentario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibroLogic ll = new LibroLogic();
		request.setAttribute("libro", ll.GetOne(new Libro(request.getParameter("isbn"))));
		
		CategoriaLogic cl = new CategoriaLogic();
		request.setAttribute("listaCategoria", cl.GetAll());

		request.getRequestDispatcher("libro.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
