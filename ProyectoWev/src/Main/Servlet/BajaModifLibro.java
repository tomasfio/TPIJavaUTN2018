package Main.Servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Entidades.Usuario;
import Main.Negocio.LibroLogic;
import Main.Util.Autentificacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class BajaModifLibro
 */
@WebServlet("/BajaModifLibro")
public class BajaModifLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BajaModifLibro() {
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
			if(request.getParameter("btnUpdate") != null) {
				Main.Entidades.Libro lib = new Main.Entidades.Libro();
				lib.setISBN(Integer.parseInt(request.getParameter("isbn_modificar")));
				lib.setTitulo(request.getParameter("titulo_modificar"));
				lib.setDescripcion(request.getParameter("descripcion_modificar"));
				lib.setAutor(request.getParameter("autor_modificar"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				try {
					lib.setFecha(dateFormat.parse(request.getParameter("fecha_modificar")));
				} catch (ParseException e) {
					request.setAttribute("errorDatos", "La fecha ingresada tiene format invalido");
					request.setAttribute("", request.getParameter("isbn_modificar"));
					request.setAttribute("btnUpdate", true);
					request.getRequestDispatcher("LibroABM").forward(request, response);
				}
				lib.setEdicion(request.getParameter("edicion_modificar"));
				lib.setPrecio(Double.parseDouble(request.getParameter("precio_modificar")));
				
				LibroLogic ll = new LibroLogic();
				if(ll.Update(lib)) {
					request.setAttribute("modifLibro", true);
					request.getRequestDispatcher("ListaLibros").forward(request, response);
				}
				else {
					request.setAttribute("modifLibro", false);
				}
			}
			else if(request.getParameter("btnDelete") != null) {
				Main.Entidades.Libro lib = new Main.Entidades.Libro();
				lib.setISBN(Integer.parseInt(request.getParameter("isbn_baja")));
				
				LibroLogic ll = new LibroLogic();
				if(ll.Delete(lib)) {
					request.setAttribute("bajaLibro", true);
				}
				else {
					request.setAttribute("bajaLibro", false);
				}
				
				request.getRequestDispatcher("ListaLibros").forward(request, response);
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
