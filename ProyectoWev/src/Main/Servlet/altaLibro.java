package Main.Servlet;

import java.io.IOException;
import java.text.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Entidades.*;
import Main.Negocio.*;
import Main.Util.Autentificacion;

/**
 * Servlet implementation class altaLibro
 */
@WebServlet({"/AltaLibro","/Altalibro" })
public class altaLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Autentificacion aut = new Autentificacion();
		if(!aut.AutentificacionAdministrador((Usuario)request.getSession().getAttribute("user"))) {
    		response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
		else {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
			try {
				Libro libro = new Libro();
				CategoriaLogic cl = new CategoriaLogic();
				
				libro.setISBN(Integer.parseInt(request.getParameter("ISBN")));
				libro.setTitulo(request.getParameter("titulo"));
				libro.setDescripcion(request.getParameter("descripcion"));
				libro.setAutor(request.getParameter("autor"));
				libro.setFecha(format.parse(request.getParameter("fecha")));
				libro.setEdicion(request.getParameter("edicion"));
				libro.setPrecio(Double.parseDouble(request.getParameter("precio")));
				libro.setCategoria(cl.GetOne(new Main.Entidades.Categoria(Integer.parseInt((request.getParameter("categoria"))))));
				libro.setImagen(request.getParameter("imagen"));
				
				LibroLogic ll = new LibroLogic();
				if(ll.Insert(libro))
				{
					request.setAttribute("registroLibro", true);
				}
				else
				{
					request.setAttribute("registroLibro", false);
				}
				
				request.getRequestDispatcher("ListaLibros").forward(request, response);
			}
			/*catch (ParseException e){
				e.printStackTrace();
				throw new RuntimeException(e);
			}*/
			catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
