package Main.Datos;

import java.util.Collection;
import java.util.Vector;
import java.sql.*;
import Main.Entidades.*;

public class VentaData {

	public boolean Insert(Venta ven)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "INSERT INTO Ventas(idUsuario,importe,idEntrega) VALUES(?,?,?)";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1,ven.getUsuario().getIdUsuario());
			pstm.setDouble(2, ven.getImporte());
			pstm.setInt(3, ven.getEntrega().getIdEntrega());
			
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
	
	public boolean Delete(Venta ven)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "DELETE FROM ventas WHERE idVenta = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, ven.getIdVenta());
			
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
				throw new RuntimeException();
			}
		}
	}
	
	public boolean Update(Venta ven)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "UPDATE ventas SET idUsuario = ? ,importe = ?, idEntrega = ? WHERE idVenta = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, ven.getUsuario().getIdUsuario());
			pstm.setDouble(2, ven.getImporte());
			pstm.setInt(3, ven.getEntrega().getIdEntrega());
			pstm.setInt(4, ven.getIdVenta());
			
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
	
	public Venta GetOne(int id)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		UsuarioData usuData = new UsuarioData();
		EntregaData entData = new EntregaData();
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "SELECT * FROM Ventas WHERE idVenta = ? ";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			Venta ven = null;
			
			while(rs.next())
			{
				ven = new Venta();
				ven.setIdVenta(rs.getInt("idVenta"));
				ven.setUsuario(usuData.GetOne(rs.getInt("idUsuario")));
				ven.setEntrega(entData.GetOne(rs.getInt("idEntrega")));
			}
			return ven;
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
	
	public Collection<Venta> GetAll()
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		UsuarioData usuData = new UsuarioData();
		EntregaData entData = new EntregaData();
		try
		{
			con = Base.getConnection();
			String sql = "SELECT * FROM Ventas";
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Vector<Venta> ventas = new Vector<Venta>();
			Venta ven = null;
			
			while(rs.next())
			{
				ven = new Venta();
				ven.setIdVenta(rs.getInt("idVenta"));
				ven.setUsuario(usuData.GetOne(rs.getInt("idUsuario")));
				ven.setImporte(rs.getDouble("importe"));
				ven.setEntrega(entData.GetOne(rs.getInt("idEntrega")));
				ventas.add(ven);
			}
			return ventas;
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
