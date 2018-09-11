package Main.Datos;

import Main.Entidades.*;
import java.sql.*;
import java.util.Vector;
import java.util.Collection;

public class DetalleVentaData {
	
	public boolean Insert(DetalleVenta det)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "INSERT INTO detallesVentas(fechaVenta,idVenta,ISBN,cantidad) VALUES(?,?,?,?)";
			
			pstm = con.prepareStatement(sql);
			pstm.setDate(0, (java.sql.Date)det.getFechaVenta());
			pstm.setInt(1, det.getVenta().getIdVenta());
			pstm.setInt(2, det.getLibro().getISBN());
			pstm.setInt(3, det.getCantidad());
			
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
		
	public boolean Delete(DetalleVenta det)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "DELETE FROM detalleVentas WHERE idVenta = ? AND ISBN = ? AND fechaVenta = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(0, det.getVenta().getIdVenta());
			pstm.setInt(1, det.getLibro().getISBN());
			pstm.setDate(2, (java.sql.Date)det.getFechaVenta());
			
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

	public boolean Update(DetalleVenta det)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "UPDATE detalleVentas SET cantidad = ? WHERE idVenta = ? AND ISBN = ? AND fechaVenta = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(0, det.getCantidad());
			pstm.setDouble(1, det.getVenta().getIdVenta());
			pstm.setInt(2, det.getLibro().getISBN());
			pstm.setDate(3, (java.sql.Date)det.getFechaVenta());
			
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

	public DetalleVenta GetOne(int idVenta,int isbn, Date fechaVenta)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		LibroData libData = new LibroData();
		VentaData venData = new VentaData();
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "SELECT * FROM DetalleVentas WHERE idVenta = ? AND ISBN = ? AND fechaVenta = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(0, idVenta);
			pstm.setInt(1, isbn);
			pstm.setDate(2, fechaVenta);
			rs = pstm.executeQuery();
			
			DetalleVenta det = null;
			
			while(rs.next())
			{
				det = new DetalleVenta();
				det.setFechaVenta(rs.getDate("fechaVenta"));
				det.setVenta(venData.GetOne(rs.getInt("idVenta")));
				det.setLibro(libData.GetOne(rs.getInt("ISBN")));
				det.setCantidad(rs.getInt("cantidad"));
			}
			return det;
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
	
	public Collection<DetalleVenta> GetAll()
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		LibroData libData = new LibroData();
		VentaData venData = new VentaData();
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "SELECT * FROM DetalleVentas";
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Vector<DetalleVenta> detalles = new Vector<DetalleVenta>();
			DetalleVenta det = null;
			
			while(rs.next())
			{
				det = new DetalleVenta();
				det.setFechaVenta(rs.getDate("fechaVenta"));
				det.setVenta(venData.GetOne(rs.getInt("idVenta")));
				det.setLibro(libData.GetOne(rs.getInt("ISBN")));
				det.setCantidad(rs.getInt("cantidad"));
				detalles.add(det);
			}
			return detalles;
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
