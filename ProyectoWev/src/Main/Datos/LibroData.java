package Main.Datos;

import java.util.ArrayList;
import java.sql.*;
import java.text.SimpleDateFormat;

import Main.Entidades.*;

public class LibroData {
	
	public boolean Insert(Libro lib)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "INSERT INTO Libros(ISBN,titulo,descripcion,autor,fecha,edicion,precio,idCategoria) VALUES(?,?,?,?,CURDATE(),?,?,?)";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1,lib.getISBN());
			pstm.setString(2, lib.getTitulo());
			pstm.setString(3, lib.getDescripcion());
			pstm.setString(4, lib.getAutor());
			/*pstm.setDate(5, new java.sql.Date(lib.getFecha().getTime()));*/
			pstm.setString(5, lib.getEdicion());
			pstm.setDouble(6, lib.getPrecio());
			pstm.setInt(7, lib.getCategoria().getIdCategoria());
			
			int resultado = pstm.executeUpdate();
			
			if(resultado == 1)
				return true;
			else
				return false;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		finally
		{
			try
			{
				if(pstm != null) pstm.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				throw new RuntimeException(ex);
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
			pstm.setInt(1, lib.getISBN());
			
			int rtdo = pstm.executeUpdate();
			
			if(rtdo == 0)
				return false;
			else
				return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		finally
		{
			try
			{
				if(pstm != null) pstm.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				throw new RuntimeException(ex);
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
			sql = "UPDATE libros SET titulo = ?, descripcion = ?,autor = ?,fecha = ?,edicion = ?, precio = ? WHERE ISBN = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, lib.getTitulo());
			pstm.setString(2, lib.getDescripcion());
			pstm.setString(3, lib.getAutor());
			pstm.setString(4, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(lib.getFecha()));
			pstm.setString(5, lib.getEdicion());
			pstm.setDouble(6, lib.getPrecio());
			pstm.setInt(7, lib.getISBN());
			
			int res = pstm.executeUpdate();
			
			if(res == 0)
				return false;
			else
				return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		finally
		{
			try
			{
				if(pstm != null) pstm.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				throw new RuntimeException(ex);
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
			pstm.setInt(1, lib.getISBN());
			rs = pstm.executeQuery();
			
			Libro libro = null;
			
			while(rs.next())
			{
				libro = new Libro();
				libro.setISBN(rs.getInt("ISBN"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setDescripcion(rs.getString("descripcion"));
				libro.setAutor(rs.getString("autor"));
				libro.setFecha(rs.getDate("fecha"));
				libro.setEdicion(rs.getString("edicion"));
				libro.setPrecio(rs.getDouble("precio"));
				
				Categoria cat = new Categoria();
				cat.setIdCategoria(rs.getInt("idCategoria"));
				
				libro.setCategoria(catData.GetOne(cat));
			}
			return libro;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
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
				ex.printStackTrace();
				throw new RuntimeException(ex);
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
			sql = "SELECT * FROM libros";
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			ArrayList<Libro> Libros = new ArrayList<Libro>();
			Libro lib = null;
			
			while(rs.next())
			{
				lib = new Libro();
				lib.setISBN(rs.getInt("ISBN"));
				lib.setTitulo(rs.getString("titulo"));
				lib.setDescripcion(rs.getString("descripcion"));
				lib.setAutor(rs.getString("autor"));
				lib.setFecha(rs.getDate("fecha"));
				lib.setEdicion(rs.getString("edicion"));
				lib.setPrecio(rs.getDouble("precio"));
				
				Categoria cat = new Categoria();
				cat.setIdCategoria(rs.getInt("idCategoria"));
				
				lib.setCategoria(catData.GetOne(cat));
				
				Libros.add(lib);
			}
			return Libros;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
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
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
	}
}
