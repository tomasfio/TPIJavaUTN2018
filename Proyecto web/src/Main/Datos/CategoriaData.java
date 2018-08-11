package Main.Datos;

import Main.Entidades.*;
import java.util.Collection;
import java.sql.*;
import java.util.Vector;

public class CategoriaData {
	
	public Collection<Categoria> GetAll()
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
			
			Vector<Categoria> cats = new Vector<Categoria>();
			Categoria cat = null;
			
			while(rs.next())
			{
				cat = new Categoria();
				cat.setIdCategoria(rs.getInt("idCategoria"));
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
	
	public Categoria GetOne(int id)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "SELECT * FROM Categoria WHERE idCategoria = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			Categoria cat = null;
			
			while(rs.next())
			{
				cat = new Categoria();
				cat.setIdCategoria(rs.getInt("idCategoria"));
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
	
	public void Insert(Categoria cat)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try 
		{
			con = Base.getConnection();
			String sql = "";
			sql = "INSERT INTO Categoria(idCategoria,descripcion) VALUES(?.?)";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, cat.getIdCategiria());
			pstm.setString(2, cat.getDescipcion());
			pstm.executeQuery();
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
