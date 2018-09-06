package Main.Datos;

import java.util.Collection;
import java.util.Vector;
import java.sql.*;
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
			sql = "INSERT INTO Libros(ISBN,titulo,descripcion,autor,fecha,edicion,precio,idCategoria= VALUES(?,?,?,?,?,?,?,?)";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(0,lib.getISBN());
			pstm.setString(1, lib.getTitulo());
			pstm.setString(2, lib.getDescripcion());
			pstm.setString(3, lib.getAutor());
			pstm.setDate(4, (java.sql.Date)lib.getFecha());
			pstm.setString(5, lib.getEdicion());
			pstm.setDouble(6, lib.getPrecio());
			pstm.setInt(7, lib.getCategoria().getIdCategiria());
			
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
			sql = "UPDATE libros SET titulo = ?, descripcion = ?,autor = ?,fecha = ?,edicion = ?, precio = ?,idCategorio = ? WHERE ISBN = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(0, lib.getTitulo());
			pstm.setString(1, lib.getDescripcion());
			pstm.setString(2, lib.getAutor());
			pstm.setDate(3, (java.sql.Date)lib.getFecha());
			pstm.setString(4, lib.getEdicion());
			pstm.setDouble(5, lib.getPrecio());
			pstm.setInt(6, lib.getCategoria().getIdCategiria());
			
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

	public Libro GetOne(int id)
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
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
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
				lib.setCategoria(catData.GetOne(rs.getInt("idCategoria")));
			}
			return lib;
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
	
	public Collection<Libro> GetAll()
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
			
			Vector<Libro> Libros = new Vector<Libro>();
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
				lib.setCategoria(catData.GetOne(rs.getInt("idCategoria")));
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
