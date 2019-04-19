package Main.Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Servlet implementation class ModificarVenta
 */
@WebServlet("/ModificarVenta")
public class ModificarVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarVenta() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Autentificacion aut = new Autentificacion();
			if(!aut.AutentificacionCliente((Usuario)request.getSession().getAttribute("user"))) {
	    		response.sendRedirect(request.getContextPath() + "/login.jsp");
			}
			else {
				if(request.getSession().getAttribute("carritoCompra") != null)
				{
					SimpleDateFormat format = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
					
					Venta venta = (Venta)request.getSession().getAttribute("carritoCompra");
					String isbn = request.getParameter("isbn");
					String fecha = request.getParameter("fecha");
					Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
					
					for(DetalleVenta detVen : venta.getDetallesVentas()) {
						if(fecha.equals(detVen.getFechaVenta().toString()) && isbn.equals(detVen.getLibro().getISBN())) {
							
							venta.setImporte(venta.getImporte() - detVen.getSubTotal());
							
							detVen.setCantidad(cantidad);
							detVen.setSubTotal(detVen.getCantidad()*detVen.getLibro().getPrecio());
							
							venta.setImporte(venta.getImporte() + detVen.getSubTotal());
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
		catch(Exception ex) {
			request.getRequestDispatcher("Index").forward(request, response);
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
