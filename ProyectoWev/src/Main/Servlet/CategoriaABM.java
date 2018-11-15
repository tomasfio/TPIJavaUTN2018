package Main.Servlet;

import Main.Entidades.*;
import Main.Negocio.CategoriaLogic;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CategoriaABM
 */
@WebServlet({ "/CategoriaABM", "/Categoriaabm" })
public class CategoriaABM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaABM() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnUpdate") != null || request.getParameter("btnDelete") != null)
		{
			Main.Entidades.Categoria cat = new Main.Entidades.Categoria();
			cat.setIdCategoria(Integer.parseInt(request.getParameter("id_categoria")));
			
			CategoriaLogic cl = new CategoriaLogic();
			Main.Entidades.Categoria categoria = cl.GetOne(cat);
			if(categoria != null)
			{
				request.setAttribute("categoria", categoria);
				if(request.getParameter("btnUpdate") != null)
				{
					request.setAttribute("accion", "update");
				}
				else
				{
					request.setAttribute("accion", "delete");
				}
				request.getRequestDispatcher("admin-baja-modif-categoria.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("existeCategoria", false);
				request.getRequestDispatcher("ListaCategorias").forward(request, response);
			}
		}
		else
		{
			request.getRequestDispatcher("admin-alta-categoria.jsp").forward(request, response);
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
