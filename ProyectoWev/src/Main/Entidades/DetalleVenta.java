package Main.Entidades;

import java.util.Date;

public class DetalleVenta {
	private Date fechaVenta;
	private Libro Libro;
	private Venta Venta;
	public Venta getVenta() {
		return Venta;
	}
	public void setVenta(Venta venta) {
		Venta = venta;
	}
	private int cantidad;
	
	public Date getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public Libro getLibro()
	{
		return Libro;
	}
	public void setLibro(Libro Libro) 
	{
		this.Libro = Libro;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
