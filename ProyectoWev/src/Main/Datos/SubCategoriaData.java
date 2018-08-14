package Main.Datos;

import java.util.Collection;
import java.util.Vector;
import java.sql.*;
import Main.Entidades.*;

public class SubCategoriaData {
	
	public Collection<SubCategoria> GetAll()
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql += "SELECT * FROM subCategoria";
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Vector<SubCategoria> subCategorias = new Vector<SubCategoria>();
			CategoriaData CatData = new CategoriaData();
			SubCategoria subCat = null;
			
			while(rs.next())
			{
				subCat = new SubCategoria();
				subCat.setIdSubCategoria(rs.getInt("idSubCategoria"));
				subCat.setDescripcion(rs.getString("descripcion"));
				subCat.setCategoria(CatData.GetOne(rs.getInt("idCategoria")));
				subCategorias.add(subCat);
			}
			return subCategorias;
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
	
	public SubCategoria GetOne(int id)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql += "SELECT * FROM SubCategoria WHERE idSubCategoria = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			SubCategoria subCat = null;
			CategoriaData catData = new CategoriaData();
			
			while(rs.next())
			{
				subCat = new SubCategoria();
				subCat.setIdSubCategoria(rs.getInt("idSubCategoria"));
				subCat.setDescripcion(rs.getString("descripcion"));
				subCat.setCategoria(catData.GetOne(rs.getInt("idCategoria")));
			}
			return subCat;
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
