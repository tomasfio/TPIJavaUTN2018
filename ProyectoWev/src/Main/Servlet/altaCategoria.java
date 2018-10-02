package Main.Servlet;

import Main.Datos.CategoriaData;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Entidades.Categoria;

/**
 * Servlet implementation class altaCategoria
 */
@WebServlet({ "/altaCategoria", "/altacategoria", "/AltaCategoria", "/Altacategoria" })
public class altaCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public altaCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Categoria cat = new Categoria();
		cat.setNombre(request.getParameter("nombre"));
		cat.setDescripcion(request.getParameter("descripcion"));
		
		CategoriaData catData = new CategoriaData();
		if(catData.Insert(cat))
		{
			//Mensaje que se registro correctamente
		}
		else
		{
			
		}
	}

}
