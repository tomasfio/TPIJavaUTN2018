package Main.Entidades;

import java.util.ArrayList;
import java.util.Date;

public class Libro {
	public Libro() {
		this.Categoria = new Categoria();
		this.Comentario = new ArrayList<>();
	}
	public Libro(String isbn) {
		this.ISBN = isbn;
		this.Categoria = new Categoria();
		this.Comentario = new ArrayList<>();
	}
	public Libro(String isbn,int idCat) {
		this.ISBN = isbn;
		this.Categoria = new Categoria(idCat);
		this.Comentario = new ArrayList<>();
	}
	
	private String ISBN;
	private String titulo;
	private String descripcion;
	private String autor;
	private Date fecha;
	private String edicion;
	private double precio;
	private Categoria Categoria;
	private String imagen;
	private ArrayList<Comentario> Comentario;
	
	public String getISBN() {
		return this.ISBN;
	}
	public void setISBN(String iSBN) {
		this.ISBN = iSBN;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEdicion() {
		return edicion;
	}
	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Categoria getCategoria() {
		return Categoria;
	}
	public void setCategoria(Categoria Categoria) {
		this.Categoria = Categoria;
	}
	public String getImagen() {
		return imagen;
	}	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public ArrayList<Comentario> getComentario() {
		return Comentario;
	}
	public void setComentario(ArrayList<Comentario> comentario) {
		Comentario = comentario;
	}
}
