package Main.Servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Main.Entidades.*;
import Main.Datos.*;

/**
 * Servlet implementation class altaUsuario
 */
@WebServlet({ "/altaUsuario", "/altausuario", "/AltaUsuario", "/Altausuario" })
public class altaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public altaUsuario() {
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
		Usuario usu = new Usuario();
		usu.setNombre(request.getParameter("nombre"));
		usu.setApellido(request.getParameter("apellido"));
		usu.setEmail(request.getParameter("email"));
		usu.setTipoUsuario(Integer.parseInt(request.getParameter("tipo_usu")));
		Date fechaActual = new Date();
		usu.setFechaDeAlta(fechaActual);
		usu.setUsuario(request.getParameter("usuario"));
		usu.setContraseña(request.getParameter("contrasenia"));
		
		UsuarioData usuData = new UsuarioData();
		if(usuData.GetByUserName(usu.getUsuario()))
		{
			usuData.Insert(usu);
			//Mensaje que se registro el usuario correctamente'
		}
		else
		{
			//Mensaje de nombre de usuario ya registrado
		}
	}

}
