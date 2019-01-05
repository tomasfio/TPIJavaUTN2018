package Main.Servlet;

import Main.Negocio.CategoriaLogic;
import Main.Util.Autentificacion;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Entidades.Categoria;
import Main.Entidades.Usuario;

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
		Autentificacion aut = new Autentificacion();
		if(!aut.AutentificacionAdministrador((Usuario)request.getSession().getAttribute("user"))) {
    		response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
		else {
			Categoria cat = new Categoria();
			cat.setNombre(request.getParameter("nombre"));
			cat.setDescripcion(request.getParameter("descripcion"));
			
			CategoriaLogic cl = new CategoriaLogic();
			
			cat = cl.Insert(cat);
		}
	}

}
