package Main.Datos;

import Main.Entidades.*;
import java.util.ArrayList;
import java.sql.*;

public class CategoriaData {
	
	public ArrayList<Categoria> GetAll()
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql += "SELECT * FROM Categorias";
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			ArrayList<Categoria> cats = new ArrayList<Categoria>();
			Categoria cat = null;
			
			while(rs.next())
			{
				cat = new Categoria();
				cat.setIdCategoria(rs.getInt("idCategoria"));
				cat.setNombre(rs.getString("nombre"));
				cat.setDescripcion(rs.getString("descripcion"));
				cats.add(cat);
			}
			return cats;
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
	
	public Categoria GetOne(Categoria categoria)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "SELECT * FROM Categorias WHERE idCategoria = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, categoria.getIdCategoria());
			rs = pstm.executeQuery();
			
			Categoria cat = null;
			
			while(rs.next())
			{
				cat = new Categoria();
				cat.setIdCategoria(rs.getInt("idCategoria"));
				cat.setNombre(rs.getString("nombre"));
				cat.setDescripcion(rs.getString("descripcion"));
			}
			return cat;
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
	
	public Categoria Insert(Categoria cat)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try 
		{
			con = Base.getConnection();
			String sql = "";
			sql = "INSERT INTO Categorias(nombre,descripcion) VALUES(?,?)";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, cat.getNombre());
			pstm.setString(2, cat.getDescipcion());
			int resultado = pstm.executeUpdate();
			if(resultado == 1)
				return cat;
			else
				return null;
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

	public boolean Delete(Categoria cat)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "DELETE FROM Categorias WHERE idCategoria = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, cat.getIdCategoria());
			
			int rtdo = pstm.executeUpdate();
			
			if(rtdo == 0)
			{
				return false;
			}
			else
			{
				return true;
			}
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

	public boolean Update(Categoria cat) 
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "UPDATE Categorias SET nombre = ? , descripcion = ? WHERE idCategoria = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, cat.getNombre());
			pstm.setString(2, cat.getDescipcion());
			pstm.setInt(3, cat.getIdCategoria());
			
			int res = pstm.executeUpdate();
			
			if(res == 0)
			{
				return false;
			}
			else
			{
				return true;
			}
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
}
