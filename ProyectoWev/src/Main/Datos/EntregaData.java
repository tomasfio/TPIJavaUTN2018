package Main.Datos;

import Main.Entidades.*;
import java.util.Collection;
import java.util.Vector;
import java.sql.*;
import java.text.SimpleDateFormat;

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
				ent.setDireccion(rs.getString("direccion"));
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
	
	public Entrega GetByVenta(Venta ven)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "SELECT * FROM Entregas WHERE idVenta = ? AND IFNULL(fechaEntrega,0) = 0";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, ven.getIdVenta());
			rs = pstm.executeQuery();
			
			Entrega ent = null;
			
			while(rs.next())
			{
				ent = new Entrega();
				ent.setIdEntrega(rs.getInt("idEntrega"));
				ent.setEstado(rs.getString("estado"));
				ent.setFechaEntrega(rs.getDate("fechaEntrega"));
				ent.setDireccion(rs.getString("direccion"));
				ent.setVenta(ven);
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
				ent.setDireccion(rs.getString("direccion"));
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
	
	public boolean Insert(Entrega ent) {
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "INSERT INTO entregas(estado,fechaEntrega,direccion,idVenta) VALUES(?,NULL,?,?)";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, ent.getEstado());
			pstm.setString(2, ent.getDireccion());
			pstm.setInt(3, ent.getVenta().getIdVenta());
			
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
	
	public boolean Update(Entrega ent) {
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "UPDATE entregas SET estado = ?, fechaEntrega = ? WHERE idEntrega = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, ent.getEstado());
			pstm.setString(2, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ent.getFechaEntrega()));
			pstm.setInt(3, ent.getIdEntrega());
			
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
}
