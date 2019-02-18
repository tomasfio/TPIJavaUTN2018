package Main.Entidades;

import java.util.*;

public class Venta {
	private int idVenta;
	private Usuario Usuario;
	private double importe;
	private Entrega Entrega;
	private Date fecha;
	private ArrayList<DetalleVenta> DetallesVentas;
	
	public Venta() {
		this.DetallesVentas = new ArrayList<DetalleVenta>();
		this.Entrega = new Entrega();
		this.Usuario = new Usuario();
	}
	public Venta(int id) {
		this.DetallesVentas = new ArrayList<DetalleVenta>();
		this.Entrega = new Entrega();
		this.Usuario = new Usuario();
		this.idVenta = id;
	}
	
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public Usuario getUsuario() {
		return Usuario;
	}
	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}
	public Entrega getEntrega() {
		return Entrega;
	}
	public void setEntrega(Entrega entrega) {
		Entrega = entrega;
	}
	public ArrayList<DetalleVenta> getDetallesVentas()
	{
		return DetallesVentas;
	}
	public void setDetallesVentas(ArrayList<DetalleVenta> detallesVentas)
	{
		DetallesVentas = detallesVentas;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
