package Main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Entidades.*;
import Main.Negocio.VentaLogic;
import Main.Util.Autentificacion;

/**
 * Servlet implementation class RegistrarVenta
 */
@WebServlet("/RegistrarVenta")
public class RegistrarVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarVenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Autentificacion aut = new Autentificacion();
		if(!aut.AutentificacionCliente((Usuario)request.getSession().getAttribute("user"))) {
    		response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
		else {
			if(request.getParameter("btnRegistrar") != null) {
				if(request.getSession().getAttribute("carritoCompra") != null) {
					Venta venta = (Venta)request.getSession().getAttribute("carritoCompra");
					
					venta.setUsuario((Usuario)request.getSession().getAttribute("user"));
					
					if(Boolean.parseBoolean(request.getParameter("envio"))) {
						Entrega envio = new Entrega();
						envio.setEstado("Recibido");
						envio.setDireccion(request.getParameter("direccion"));
						venta.setEntrega(envio);
						
						
					}else
					{
						venta.setEntrega(null);
					}
					VentaLogic vl = new VentaLogic();
					if(!vl.RegistrarVenta(venta)) {
						request.getRequestDispatcher("CarritoCompra").forward(request, response);
					}
					else {
						request.getSession().setAttribute("carritoCompra",null);
						request.getRequestDispatcher("Index").forward(request, response);				
					}
				}
				else {
					request.getRequestDispatcher("CarritoCompra").forward(request, response);	
					
				}
			}
			else if(request.getParameter("btnDelete") != null) {
				request.getSession().setAttribute("carritoCompra", null);
				request.getRequestDispatcher("CarritoCompra").forward(request, response);	
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
