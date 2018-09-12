package Main.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Entidades.*;
import Main.Datos.*;

/**
 * Servlet implementation class altaLibro
 */
@WebServlet({ "/altaLibro", "/AltaLibro", "/altalibro", "/Altalibro" })
public class altaLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public altaLibro() {
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
		Libro libro = new Libro();
		libro.setISBN(Integer.parseInt(request.getParameter("ISBN")));
		libro.setTitulo(request.getParameter("titulo"));
		libro.setDescripcion(request.getParameter("descripcion"));
		libro.setAutor(request.getParameter("autor"));
		//libro.setFecha(request.getParameter("fecha"));
		libro.setEdicion(request.getParameter("edicion"));
		libro.setPrecio(Double.parseDouble(request.getParameter("precio")));
		
		LibroData libData = new LibroData();
		if(libData.Insert(libro))
		{
			//Mensaje de registro exitoso
		}
		else
		{
			//Mensaje de registro no existoso
		}
	}

}
