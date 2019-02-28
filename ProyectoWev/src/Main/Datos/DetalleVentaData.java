package Main.Datos;

import Main.Entidades.*;
import Main.Util.Rows;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Collection;

public class DetalleVentaData {
	
	public boolean Insert(DetalleVenta det)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "INSERT INTO detallesVentas(fechaVenta,idVenta,ISBN,cantidad,subtotal) VALUES(?,?,?,?,?)";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(det.getFechaVenta()));
			pstm.setInt(2, det.getVenta().getIdVenta());
			pstm.setInt(3, det.getLibro().getISBN());
			pstm.setInt(4, det.getCantidad());
			pstm.setDouble(5, det.getSubTotal());
			
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

	public ArrayList<DetalleVenta> GetByVenta(DetalleVenta deta)
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
			sql = "SELECT * FROM DetallesVentas WHERE idVenta = ? ";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1,deta.getVenta().getIdVenta());
			rs = pstm.executeQuery();
			
			DetalleVenta det = null;
			ArrayList<DetalleVenta> detalles = new ArrayList<DetalleVenta>();
			
			while(rs.next())
			{
				det = new DetalleVenta();
				det.setFechaVenta(rs.getDate("fechaVenta"));
				det.setVenta(venData.GetOne(rs.getInt("idVenta")));
				det.setLibro(libData.GetOne(new Libro(rs.getInt("ISBN"))));
				det.setCantidad(rs.getInt("cantidad"));
				det.setSubTotal(rs.getDouble("subtotal"));
				
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
	
	public ArrayList<DetalleVenta> GetByLibro(DetalleVenta deta)
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
			sql = "SELECT * FROM DetallesVentas WHERE isbn = ? ";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1,deta.getLibro().getISBN());
			rs = pstm.executeQuery();
			
			DetalleVenta det = null;
			ArrayList<DetalleVenta> detalles = new ArrayList<DetalleVenta>();
			
			while(rs.next())
			{
				det = new DetalleVenta();
				det.setFechaVenta(rs.getDate("fechaVenta"));
				det.setVenta(venData.GetOne(rs.getInt("idVenta")));
				det.setLibro(libData.GetOne(new Libro(rs.getInt("ISBN"))));
				det.setCantidad(rs.getInt("cantidad"));
				det.setSubTotal(rs.getDouble("subtotal"));
				
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
	
	public Integer[][] GetCantidadVendida()
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "SELECT isbn,SUM(cantidad)'ventas' FROM detallesventas GROUP BY isbn ORDER BY SUM(cantidad)DESC";
			
			pstm = con.prepareStatement(sql);

			Rows rows = new Rows();
			Integer[][] cantidad = new Integer[2][rows.GetRowCount(rs = pstm.executeQuery())];
			rs = pstm.executeQuery();
			int i = 0;
			
			while(rs.next())
			{
				cantidad[0][i] = rs.getInt("isbn");
				cantidad[1][i] = rs.getInt("ventas");
				i++;
			}
			return cantidad;
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
				det.setLibro(libData.GetOne(new Libro(rs.getInt("ISBN"))));
				det.setCantidad(rs.getInt("cantidad"));
				det.setSubTotal(rs.getDouble("subtotal"));
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
