package Main.Entidades;

import java.util.Date;

public class Entrega {
	private int idEntrega;
	private String estado;
	private Date fechaEntrega;
	private String direccion;
	private Venta Venta;
	
	public Venta getVenta() {
		return Venta;
	}
	public void setIdVenta(Venta Venta) {
		this.Venta = Venta;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getIdEntrega() {
		return idEntrega;
	}
	public void setIdEntrega(int idEntrega) {
		this.idEntrega = idEntrega;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
}
