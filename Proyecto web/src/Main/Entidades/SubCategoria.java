package Main.Entidades;

public class SubCategoria {
	private int idSubCategoria;
	private String descripcion;
	private Categoria Categoria;
	
	public int getIdSubCategoria()
	{
		return idSubCategoria;
	}
	public void setIdSubCategoria(int idSubCategoria)
	{
		this.idSubCategoria = idSubCategoria;
	}
	public String getDescripcion()
	{
		return descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public Categoria getCategoria()
	{
		return Categoria;
	}
	public void setCategoria(Categoria Categoria)
	{
		this.Categoria = Categoria;
	}
}
