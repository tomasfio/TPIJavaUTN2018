package Main.Servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Entidades.Libro;
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
				try {
					Main.Entidades.Libro lib = new Main.Entidades.Libro();
					lib.setISBN(Integer.parseInt(request.getParameter("isbn_modificar")));
					lib.setTitulo(request.getParameter("titulo_modificar"));
					lib.setDescripcion(request.getParameter("descripcion_modificar"));
					lib.setAutor(request.getParameter("autor_modificar"));
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					lib.setFecha(dateFormat.parse(request.getParameter("fecha_modificar")));
					 
					lib.setEdicion(request.getParameter("edicion_modificar"));
					lib.setPrecio(Double.parseDouble(request.getParameter("precio_modificar")));
					
					
					LibroLogic ll = new LibroLogic();
					if(ll.Validar(lib)) {
						if(ll.Update(lib)) {
							request.setAttribute("modifLibro", true);
							request.getRequestDispatcher("ListaLibros").forward(request, response);
						}
						else {
							Libro libro = ll.GetOne(lib);
							request.setAttribute("libro", libro);
							request.setAttribute("accion", "update");
							request.setAttribute("error", "Hubo un error y no se pudo modificar el libro");
							request.getRequestDispatcher("admin-baja-modif-libro.jsp").forward(request, response);
						}
					}
					else {
						Libro libro = ll.GetOne(lib);
						request.setAttribute("libro", libro);
						request.setAttribute("accion", "update");
						request.setAttribute("error", "Hay campos con datos incompletos y/o incorrectos");
						request.getRequestDispatcher("admin-baja-modif-libro.jsp").forward(request, response);
					}
				}
				catch (ParseException e ) {
					LibroLogic ll = new LibroLogic();
					request.setAttribute("error", "La fecha ingresada y/o el precio ingresado tiene format invalido");
					request.setAttribute("libro", ll.GetOne(new Libro(Integer.parseInt(request.getParameter("isbn_modificar")))));
					request.setAttribute("accion", "update");
					request.getRequestDispatcher("admin-baja-modif-libro.jsp").forward(request, response);
				}catch(NumberFormatException e) {
					LibroLogic ll = new LibroLogic();
					request.setAttribute("error", "La fecha ingresada y/o el precio ingresado tiene format invalido");
					request.setAttribute("libro", ll.GetOne(new Libro(Integer.parseInt(request.getParameter("isbn_modificar")))));
					request.setAttribute("accion", "update");
					request.getRequestDispatcher("admin-baja-modif-libro.jsp").forward(request, response);
				}
				
			}
			else if(request.getParameter("btnDelete") != null) {
				Libro lib = new Libro();
				lib.setISBN(Integer.parseInt(request.getParameter("isbn_baja")));
				
				LibroLogic ll = new LibroLogic();
				if(ll.ValidarDelete(lib)) {
					if(ll.Delete(lib)) {
						request.setAttribute("bajaLibro", true);
						request.getRequestDispatcher("ListaLibros").forward(request, response);
					}
					else {
						Libro libro = ll.GetOne(lib);
						request.setAttribute("libro", libro);
						request.setAttribute("accion", "delete");
						request.setAttribute("error", "Hubo un error y no se pudo dar de baja el libro");
						request.getRequestDispatcher("admin-baja-modif-libro.jsp").forward(request, response);
					}
				}
				else {
					request.setAttribute("error", "No puedes eliminar el libro porque tiene registro asociados a el");
					request.getRequestDispatcher("admin-baja-modif-libro.jsp").forward(request, response);
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
