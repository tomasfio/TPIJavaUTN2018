package Main.Entidades;

public class Categoria {
	private int idCategoria;
	private String nombre;
	private String descripcion;
	
	public int getIdCategoria()
	{
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public String getDescipcion()
	{
		return descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
}
