package Main.Servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Entidades.*;
import Main.Negocio.*;

/**
 * Servlet implementation class AgregarComentario
 */
@WebServlet("/AgregarComentario")
public class AgregarComentario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarComentario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Comentario comentario = new Comentario();
		comentario.setUsuario((Usuario)request.getSession().getAttribute("user"));
		comentario.setLibro(new Libro(request.getParameter("isbn")));
		comentario.setComentario(request.getParameter("comentario"));
		comentario.setFechaHora(new Date());
		
		ComentarioLogic cl = new ComentarioLogic();
		
		if(cl.Validar(comentario)) {
			if(cl.Insert(comentario)) {
				request.setAttribute("isbn", comentario.getLibro().getISBN());
				
				request.getRequestDispatcher("LibroComentario").forward(request, response);
			}else {
				request.setAttribute("isbn", comentario.getLibro().getISBN());
				request.setAttribute("falloComentario", true);
				
				request.getRequestDispatcher("LibroComentario").forward(request, response);
			}
		}else {
			request.setAttribute("isbn", comentario.getLibro().getISBN());
			request.setAttribute("faltoComentario", true);
			
			request.getRequestDispatcher("LibroComentario").forward(request, response);
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
