package Main.Servlet;

import Main.Entidades.Usuario;
import Main.Negocio.UsuarioLogic;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Entidades.Usuario;
import Main.Negocio.UsuarioLogic;

/**
 * Servlet implementation class BajaModifUsuario
 */
@WebServlet({ "/BajaModifUsuario", "/bajamodifusuario", "/BajaModifusuario" })
public class BajaModifUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BajaModifUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usu = new Usuario();
		
		usu.setIdUsuario(Integer.parseInt(request.getParameter("id_baja")));
		
		UsuarioLogic ul = new UsuarioLogic();
		if(ul.Delete(usu))
		{
			request.setAttribute("bajaUsuario", true);
		}
		else
		{
			request.setAttribute("bajaUsuario", false);
		}
		request.getRequestDispatcher("UsuarioABM").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
