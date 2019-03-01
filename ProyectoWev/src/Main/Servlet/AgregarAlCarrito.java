package Main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Entidades.*;
import Main.Util.Autentificacion;
import java.util.ArrayList;
import java.util.Date;

import Main.Negocio.*;

/**
 * Servlet implementation class AgregarAlCarrito
 */
@WebServlet("/AgregarAlCarrito")
public class AgregarAlCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarAlCarrito() {
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
		else
		{
			Venta carritoCompra = null;
			if(request.getSession().getAttribute("carritoCompra") != null)
			{
				carritoCompra = (Venta)request.getSession().getAttribute("carritoCompra");
			}
			else {
				carritoCompra = new Venta();
			}
			
			LibroLogic ll = new LibroLogic();
			
			DetalleVenta detVenta = new DetalleVenta();
			detVenta.setLibro(ll.GetOne(new Libro(request.getParameter("isbn"))));
			detVenta.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
			detVenta.setSubTotal(detVenta.getCantidad()*detVenta.getLibro().getPrecio());
			detVenta.setFechaVenta(new Date());
			
			carritoCompra.getDetallesVentas().add(detVenta);
			
			carritoCompra.setImporte(carritoCompra.getImporte() + (detVenta.getLibro().getPrecio()*detVenta.getCantidad()));
			
			request.getSession().setAttribute("carritoCompra", carritoCompra);
			
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
