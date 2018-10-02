package Main.Entidades;

import java.util.Date;

public class Comentario {
	private Date fechaHora;
	private Usuario Usuario;
	private Libro Libro;
	private String comentario;
	
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	public Usuario getUsuario() {
		return Usuario;
	}
	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}
	public Libro getLibro() {
		return Libro;
	}
	public void setLibro(Libro libro) {
		Libro = libro;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
