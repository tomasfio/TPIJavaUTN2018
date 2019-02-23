package Main.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Entidades.Usuario;
import Main.Entidades.Venta;
import Main.Negocio.VentaLogic;
import Main.Util.Autentificacion;

/**
 * Servlet implementation class RealizarEntrega
 */
@WebServlet("/RealizarEntrega")
public class RealizarEntrega extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RealizarEntrega() {
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
			VentaLogic vl = new VentaLogic();
			ArrayList<Venta> entregas = vl.GetVentaWithEntregas();
			ArrayList<Venta> entregasRealizar = new ArrayList<Venta>();
			
			for(Venta ven : entregas) {
				String control = "envio" + String.valueOf(ven.getEntrega().getIdEntrega());
				if(Boolean.parseBoolean(request.getParameter(control.toString()))){
					ven.getEntrega().setEstado("Entregado");
					ven.getEntrega().setFechaEntrega(new Date());
					entregasRealizar.add(ven);
				}
			}
			
			if(entregasRealizar.size() != 0) {
				if(vl.RegistrarEntregas(entregasRealizar)) {
					request.setAttribute("registrado", true);
				}
				else {
					request.setAttribute("registrado", false);
				}
			}
			else {
				request.setAttribute("sinEnvios", true);
			}
			request.getRequestDispatcher("ListaEntregas").forward(request, response);
			
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
