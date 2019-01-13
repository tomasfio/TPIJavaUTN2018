package Main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Entidades.*;
import Main.Negocio.*;

/**
 * Servlet implementation class BuscarLibro
 */
@WebServlet("/BuscarLibro")
public class BuscarLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarLibro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibroLogic ll = new LibroLogic();
		Libro lib = new Libro();
		
		lib.setTitulo(request.getParameter("busca"));
		lib.setDescripcion(request.getParameter("busca"));
		lib.setAutor(request.getParameter("busca"));
		lib.setEdicion(request.getParameter("busca"));
		
		request.setAttribute("librosBuscardos", ll.GetLibro(lib));
		request.getRequestDispatcher("Index").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
