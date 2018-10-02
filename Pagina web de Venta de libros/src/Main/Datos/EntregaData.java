package Main.Datos;

import Main.Entidades.*;
import java.util.Collection;
import java.util.Vector;
import java.sql.*;

public class EntregaData {
	
	public Collection<Entrega> GetAll()
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql += "SELECT * FROM Entregas";
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Vector<Entrega> entregas = new Vector<Entrega>();
			Entrega ent = null;
			
			while(rs.next())
			{
				ent = new Entrega();
				ent.setIdEntrega(rs.getInt("idEntrega"));
				ent.setEstado(rs.getString("entrega"));
				ent.setFechaEntrega(rs.getDate("fechaEntrega"));
				entregas.add(ent);
			}
			return entregas;
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

	public Entrega GetOne(int id)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "SELECT * FROM Entregas WHERE idEntrega = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			Entrega ent = null;
			
			while(rs.next())
			{
				ent = new Entrega();
				ent.setIdEntrega(rs.getInt("idEntrega"));
				ent.setEstado(rs.getString("estado"));
				ent.setFechaEntrega(rs.getDate("fechaEntrega"));
			}
			return ent;
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
				if(rs != null) rs.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
	}
	
}
