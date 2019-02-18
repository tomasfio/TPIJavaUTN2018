package Main.Datos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
import java.sql.*;
import java.text.SimpleDateFormat;

import Main.Entidades.*;

public class VentaData {

	public Venta Insert(Venta ven)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "INSERT INTO Ventas(idUsuario,importe,fechaVenta) VALUES(?,?,?)";
			
			pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1,ven.getUsuario().getIdUsuario());
			pstm.setDouble(2, ven.getImporte());
			pstm.setString(3, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ven.getFecha()));
			
			int resultado = pstm.executeUpdate();
			
			if(resultado == 1){
				Venta venta = new Venta();
				
				ResultSet rs = pstm.getGeneratedKeys();
				if(rs.next())
				{
					venta.setIdVenta(rs.getInt(1));
				}
				
				return venta;
			}
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
				
				Usuario usu = new Usuario();
				usu.setIdUsuario(rs.getInt("idUsuario"));
				ven.setUsuario(usuData.GetOne(usu));
				ven.setFecha(rs.getDate("fechaVenta"));
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
	
	public ArrayList<Venta> GetAll()
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		UsuarioData usuData = new UsuarioData();
		EntregaData entData = new EntregaData();
		DetalleVentaData detData = new DetalleVentaData();
		
		try
		{
			con = Base.getConnection();
			String sql = "SELECT * FROM Ventas ORDER BY idventa DESC LIMIT 30";
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			ArrayList<Venta> ventas = new ArrayList<Venta>();
			Venta ven = null;
			
			while(rs.next())
			{
				ven = new Venta();
				ven.setIdVenta(rs.getInt("idVenta"));
				
				Usuario usu = new Usuario();
				usu.setIdUsuario(rs.getInt("idUsuario"));
				
				ven.setUsuario(usuData.GetOne(usu));
				ven.setImporte(rs.getDouble("importe"));
				ven.setDetallesVentas(detData.GetByVenta(new DetalleVenta(ven.getIdVenta())));
				ven.setFecha(rs.getDate("fechaVenta"));
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
