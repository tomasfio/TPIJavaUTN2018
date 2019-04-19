package Main.Datos;

import java.util.ArrayList;

import org.apache.log4j.Level;

import java.sql.*;
import java.text.SimpleDateFormat;

import Main.Entidades.*;
import Main.Util.LogException;

public class LibroData {
	
	public boolean Insert(Libro lib)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "INSERT INTO Libros(ISBN,titulo,descripcion,autor,fecha,edicion,precio,idCategoria,imagen) VALUES(?,?,?,?,?,?,?,?,?)";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1,lib.getISBN());
			pstm.setString(2, lib.getTitulo());
			pstm.setString(3, lib.getDescripcion());
			pstm.setString(4, lib.getAutor());
			pstm.setString(5, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(lib.getFecha()));
			pstm.setString(6, lib.getEdicion());
			pstm.setDouble(7, lib.getPrecio());
			pstm.setInt(8, lib.getCategoria().getIdCategoria());
			pstm.setString(9, lib.getImagen() != null ? lib.getImagen() : "NULL");
			
			int resultado = pstm.executeUpdate();
			
			if(resultado == 1)
				return true;
			else
				return false;
		}
		catch(Exception ex)
		{
			new LogException(ex,"Fallo el metodo Insert de Libro",Level.ERROR);
			return false;
		}
		finally
		{
			try
			{
				if(pstm != null) pstm.close();
			}
			catch(Exception ex)
			{
				return false;
			}
		}
	}
	
	public boolean Delete(Libro lib)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "DELETE FROM libros WHERE ISBN = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, lib.getISBN());
			
			int rtdo = pstm.executeUpdate();
			
			if(rtdo == 0)
				return false;
			else
				return true;
		}
		catch(Exception ex)
		{
			new LogException(ex,"Fallo el metodo Delete de Libro",Level.ERROR);
			return false;
		}
		finally
		{
			try
			{
				if(pstm != null) pstm.close();
			}
			catch(Exception ex)
			{
				return false;
			}
		}
	}

	public boolean Update(Libro lib)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "UPDATE libros SET titulo = ?, descripcion = ?,autor = ?,fecha = ?,edicion = ?, precio = ?,imagen = ? WHERE ISBN = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, lib.getTitulo());
			pstm.setString(2, lib.getDescripcion());
			pstm.setString(3, lib.getAutor());
			pstm.setString(4, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(lib.getFecha()));
			pstm.setString(5, lib.getEdicion());
			pstm.setDouble(6, lib.getPrecio());
			pstm.setString(7, lib.getImagen());
			pstm.setString(8, lib.getISBN());
			
			int res = pstm.executeUpdate();
			
			if(res == 0)
				return false;
			else
				return true;
		}
		catch(Exception ex)
		{
			new LogException(ex,"Fallo el metodo Update de Libro",Level.ERROR);
			return false;
		}
		finally
		{
			try
			{
				if(pstm != null) pstm.close();
			}
			catch(Exception ex)
			{
				return false;
			}
		}
	}
	
	public ArrayList<Libro> GetLibro(Libro libro)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		CategoriaData catData = new CategoriaData();
		
		try {
			con = Base.getConnection();
			String sql = "";
			sql = "SELECT * FROM libros " +
				"WHERE titulo LIKE ? OR descripcion LIKE ? " +
				"OR autor LIKE ? OR edicion LIKE ? ORDER BY fecha DESC";
			
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, "%" + libro.getTitulo() + "%");
			pstm.setString(2, "%" + libro.getDescripcion() + "%");
			pstm.setString(3, "%" + libro.getAutor() + "%");
			pstm.setString(4, "%" + libro.getEdicion() + "%");
			
			rs = pstm.executeQuery();
			ArrayList<Libro> Libros = new ArrayList<Libro>();
			Libro lib = null;
			
			while(rs.next())
			{
				lib = new Libro();
				lib.setISBN(rs.getString("ISBN"));
				lib.setTitulo(rs.getString("titulo"));
				lib.setDescripcion(rs.getString("descripcion"));
				lib.setAutor(rs.getString("autor"));
				lib.setFecha(rs.getDate("fecha"));
				lib.setEdicion(rs.getString("edicion"));
				lib.setPrecio(rs.getDouble("precio"));
				lib.setImagen(rs.getString("imagen") != null ? rs.getString("imagen") : "Error_imagen_no_encontrada.jpg");
				
				Categoria cat = new Categoria();
				cat.setIdCategoria(rs.getInt("idCategoria"));
				lib.setCategoria(catData.GetOne(cat));
				
				Libros.add(lib);
			}
			return Libros;
		}
		catch(Exception ex)
		{
			new LogException(ex,"Fallo el metodo GetLibro de Libro",Level.ERROR);
			return null;
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(pstm != null) pstm.close(); 
			}
			catch(Exception ex)
			{
				return null;
			}
		}
	}

	public Libro GetOne(Libro lib)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		CategoriaData catData = new CategoriaData();
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "SELECT * FROM libros WHERE ISBN = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, lib.getISBN());
			rs = pstm.executeQuery();
			
			Libro libro = null;
			
			while(rs.next())
			{
				libro = new Libro();
				libro.setISBN(rs.getString("ISBN"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setDescripcion(rs.getString("descripcion"));
				libro.setAutor(rs.getString("autor"));
				libro.setFecha(rs.getDate("fecha"));
				libro.setEdicion(rs.getString("edicion"));
				libro.setPrecio(rs.getDouble("precio"));
				libro.setImagen(rs.getString("imagen") != null ? rs.getString("imagen") : "Error_imagen_no_encontrada.jpg");
				
				Categoria cat = new Categoria();
				cat.setIdCategoria(rs.getInt("idCategoria"));
				
				libro.setCategoria(catData.GetOne(cat));
			}
			return libro;
		}
		catch(Exception ex)
		{
			new LogException(ex,"Fallo el metodo GetOne de Libro",Level.ERROR);
			return null;
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(pstm != null) pstm.close(); 
			}
			catch(Exception ex)
			{
				return null;
			}
		}
	}
	
	public ArrayList<Libro> GetAll()
	{
		CategoriaData catData = new CategoriaData();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "SELECT * FROM libros ORDER BY fecha DESC";
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			ArrayList<Libro> Libros = new ArrayList<Libro>();
			Libro lib = null;
			
			while(rs.next())
			{
				lib = new Libro();
				lib.setISBN(rs.getString("ISBN"));
				lib.setTitulo(rs.getString("titulo"));
				lib.setDescripcion(rs.getString("descripcion"));
				lib.setAutor(rs.getString("autor"));
				lib.setFecha(rs.getDate("fecha"));
				lib.setEdicion(rs.getString("edicion"));
				lib.setPrecio(rs.getDouble("precio"));
				lib.setImagen(rs.getString("imagen") != null ? rs.getString("imagen") : "Error_imagen_no_encontrada.jpg");
				
				Categoria cat = new Categoria();
				cat.setIdCategoria(rs.getInt("idCategoria"));
				
				lib.setCategoria(catData.GetOne(cat));
				
				Libros.add(lib);
			}
			return Libros;
		}
		catch(Exception ex)
		{
			new LogException(ex,"Fallo el metodo GetAll de Libro",Level.ERROR);
			return null;
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(pstm != null) pstm.close();
			}
			catch(Exception ex)
			{
				return null;
			}
		}
	}

	public ArrayList<Libro> GetByCategoria(Libro libro){
		CategoriaData catData = new CategoriaData();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			con = Base.getConnection();
			String sql = "";
			sql = "SELECT * FROM libros WHERE idCategoria = ? ORDER BY fecha DESC";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, libro.getCategoria().getIdCategoria());
			rs = pstm.executeQuery();
			
			ArrayList<Libro> Libros = new ArrayList<Libro>();
			Libro lib = null;
			
			while(rs.next())
			{
				lib = new Libro();
				lib.setISBN(rs.getString("ISBN"));
				lib.setTitulo(rs.getString("titulo"));
				lib.setDescripcion(rs.getString("descripcion"));
				lib.setAutor(rs.getString("autor"));
				lib.setFecha(rs.getDate("fecha"));
				lib.setEdicion(rs.getString("edicion"));
				lib.setPrecio(rs.getDouble("precio"));
				lib.setImagen(rs.getString("imagen") != null ? rs.getString("imagen") : "Error_imagen_no_encontrada.jpg");
				
				Categoria cat = new Categoria();
				cat.setIdCategoria(rs.getInt("idCategoria"));
				
				lib.setCategoria(catData.GetOne(cat));
				
				Libros.add(lib);
			}
			return Libros;
		}
		catch(Exception ex)
		{
			new LogException(ex,"Fallo el metodo GetByCategoria de Libro",Level.ERROR);
			return null;
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(pstm != null) pstm.close();
			}
			catch(Exception ex)
			{
				return null;
			}
		}
	}
}
