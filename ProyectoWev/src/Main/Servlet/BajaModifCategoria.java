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
 * Servlet implementation class BajaModifCategoria
 */
@WebServlet("/BajaModifCategoria")
public class BajaModifCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BajaModifCategoria() {
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
			if(request.getParameter("btnUpdate") != null)
			{
				Main.Entidades.Categoria cat = new Main.Entidades.Categoria();
				cat.setIdCategoria(Integer.parseInt(request.getParameter("id_modificar")));
				cat.setNombre(request.getParameter("nombre_modificar"));
				cat.setDescripcion(request.getParameter("descripcion_modificar"));
				
				CategoriaLogic cl = new CategoriaLogic();
				if(cl.Update(cat)) {
					request.setAttribute("modifCategoria", true);
					request.getRequestDispatcher("ListaCategorias").forward(request, response);
				}
				else
				{
					request.setAttribute("modifCategoria", false);
				}
			}
			else if(request.getParameter("btnDelete") !=null)
			{
				Main.Entidades.Categoria cat = new Main.Entidades.Categoria();
				cat.setIdCategoria(Integer.parseInt(request.getParameter("id_baja")));
				
				CategoriaLogic cl = new CategoriaLogic();
				if(cl.Detele(cat))
				{
					request.setAttribute("bajaCategoria", true);
					request.getRequestDispatcher("ListaCategorias").forward(request, response);
				}
				else
				{
					request.setAttribute("bajaCategoria", false);
					request.getRequestDispatcher("ListaCategorias").forward(request, response);
				}
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
