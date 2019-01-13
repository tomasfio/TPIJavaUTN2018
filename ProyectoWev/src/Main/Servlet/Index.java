package Main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Entidades.*;
import Main.Negocio.*;
import java.util.ArrayList;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibroLogic ll = new LibroLogic();
		if(request.getAttribute("librosBuscardos") != null)
		{
			request.setAttribute("listaLibros", (ArrayList<Libro>)request.getAttribute("librosBuscardos"));
		}
		else if(request.getParameter("idCat") !=null)
		{
			request.setAttribute("listaLibros", ll.GetByCategoria(new Libro(0,Integer.parseInt(request.getParameter("idCat")))));
		}
		else
		{
			request.setAttribute("listaLibros", ll.GetAll());
		}
		CategoriaLogic cl = new CategoriaLogic();
		request.setAttribute("listaCategoria", cl.GetAll());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
