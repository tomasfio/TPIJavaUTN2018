package Main.Entidades;

import java.util.Date;

public class DetalleVenta {
	private Date fechaVenta;
	private Venta Venta;
	private Libro Libro;
	private int cantidad;
	
	public Date getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public Venta getVenta() {
		return Venta;
	}
	public void setVenta(Venta Venta) {
		this.Venta = Venta;
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
