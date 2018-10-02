package Main.Entidades;

public class Venta {
	private int idVenta;
	private Usuario Usuario;
	private double importe;
	private Entrega Entrega;
	
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
}
