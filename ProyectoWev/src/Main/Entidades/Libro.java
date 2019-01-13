package Main.Entidades;

import java.util.Date;

public class Libro {
	public Libro() {}
	public Libro(int isbn) {
		this.ISBN = isbn;
	}
	public Libro(int isbn,int idCat) {
		this.ISBN = isbn;
		this.Categoria = new Categoria(idCat);
	}
	
	private int ISBN;
	private String titulo;
	private String descripcion;
	private String autor;
	private Date fecha;
	private String edicion;
	private double precio;
	private Categoria Categoria;
	private String imagen;
	
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
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
}
