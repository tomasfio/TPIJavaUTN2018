package Main.Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Entidades.DetalleVenta;
import Main.Entidades.Usuario;
import Main.Entidades.Venta;
import Main.Negocio.CategoriaLogic;
import Main.Util.Autentificacion;

/**
 * Servlet implementation class EliminarVenta
 */
@WebServlet("/EliminarVenta")
public class EliminarVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarVenta() {
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
			if(request.getSession().getAttribute("carritoCompra") != null)
			{
				
				Venta venta = (Venta)request.getSession().getAttribute("carritoCompra");
				String isbn = request.getParameter("isbn");
				String fecha = request.getParameter("fecha");
				
				for(DetalleVenta detVen : venta.getDetallesVentas()) {
					if(fecha.equals("'" + detVen.getFechaVenta().toString() + "'") && isbn.equals("'" + detVen.getLibro().getISBN() + "'")) {
						venta.setImporte(venta.getImporte() - detVen.getSubTotal());
						venta.getDetallesVentas().remove(detVen);
						break;
					}
				}
				
				request.setAttribute("listaDetalleVenta", venta.getDetallesVentas());
				request.setAttribute("importeTotal", venta.getImporte());
			}

			CategoriaLogic cl = new CategoriaLogic();
			request.setAttribute("listaCategoria", cl.GetAll());
			
			request.getRequestDispatcher("carrito-compra.jsp").forward(request, response);
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
