package Main.Datos;

import java.util.Collection;
import java.util.Vector;

import org.apache.log4j.Level;

import java.util.ArrayList;
import Main.Entidades.*;
import Main.Util.LogException;
import Main.Util.Rows;

import java.sql.*;

public class UsuarioData {
	
	public ArrayList<Usuario> GetByTipoUsuario(Usuario usuario){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql += "SELECT * FROM usuarios WHERE tipoUsuario = ? ";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, usuario.getTipoUsuario());
			
			rs = pstm.executeQuery();
			
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			Usuario usu = null;
			
			while(rs.next()) {
				usu = new Usuario();
				usu.setIdUsuario(rs.getInt("idUsuario"));
				usu.setUsuario(rs.getString("usuario"));
				usu.setNombre(rs.getString("nombre"));
				usu.setApellido(rs.getString("apellido"));
				usu.setEmail(rs.getString("email"));
				usu.setTipoUsuario(rs.getInt("tipoUsuario"));
				usuarios.add(usu);
			}
			return usuarios;
		}
		catch(Exception ex) 
		{
			new LogException(ex,"Fallo el metodo GetByTipoUsuario de Usuario",Level.ERROR);
			return null;
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
				return null;
			}
		}
	}
	
	public boolean Insert(Usuario usu)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try 
		{
			con = Base.getConnection();
			String sql = "";
			sql += "INSERT INTO usuarios(usuario,contraseña,nombre,apellido,email,tipoUsuario,fechaDeAlta) VALUES(?,?,?,?,?,?,NOW())";
			
			pstm = con.prepareStatement(sql);
			//pstm.RETURN_GENERATED_KEYS();
			pstm.setString(1, usu.getUsuario());
			pstm.setString(2, usu.getContraseña());
			pstm.setString(3, usu.getNombre());
			pstm.setString(4, usu.getApellido());
			pstm.setString(5, usu.getEmail());
			pstm.setInt(6, usu.getTipoUsuario());
			//pstm.setTimestamp(7, new java.sql.Timestamp(usu.getFechaDeAlta()));
			
			int resultado = pstm.executeUpdate();
			
			//Recupera la key autogenerada
			//ResultSet rs = pstm.getGeneratedKeys();
			
			if (resultado == 1)
				return true;
			else
				return false;
		}
		catch(Exception ex)
		{
			new LogException(ex,"Fallo el metodo Insert de Usuario",Level.ERROR);
			return false;
		}
		finally
		{
			try 
			{
				if(pstm != null) pstm.close();
			}
			catch(Exception ex)
			{
				return false;
			}
		}
	}
	
	public boolean Delete(Usuario usu)
	{
		Connection con = null;
		PreparedStatement pstm = null;	
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "DELETE FROM Usuarios WHERE idUsuario = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, usu.getIdUsuario());
			
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
			new LogException(ex,"Fallo el metodo Delete de Usuario",Level.ERROR);
			return false;
		}
		finally
		{
			try
			{
				if(pstm != null) pstm.close();
			}
			catch(Exception ex)
			{
				return false;
			}
		}
	}
	
	public boolean Update(Usuario usu)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "UPDATE Usuarios SET nombre = ?, apellido = ?,email = ? WHERE idUsuario = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, usu.getNombre());
			pstm.setString(2, usu.getApellido());
			pstm.setString(3, usu.getEmail());
			pstm.setInt(4, usu.getIdUsuario());
			
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
			new LogException(ex,"Fallo el metodo Update de Usuario",Level.ERROR);
			return false;
		}
		finally
		{
			try
			{
				if(pstm != null) pstm.close();
			}
			catch(Exception ex)
			{
				return false;
			}
		}
	}
	
	public Usuario GetOne(Usuario usuario)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "SELECT * FROM Usuarios WHERE idUsuario = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, usuario.getIdUsuario());
			rs = pstm.executeQuery();
			
			Usuario usu = null;
			
			while(rs.next())
			{
				usu = new Usuario();
				usu.setIdUsuario(rs.getInt("idUsuario"));
				usu.setUsuario(rs.getString("usuario"));
				usu.setNombre(rs.getString("nombre"));
				usu.setApellido(rs.getString("apellido"));
				usu.setEmail(rs.getString("email"));
				usu.setTipoUsuario(rs.getInt("tipoUsuario"));
				usu.setFechaDeAlta(rs.getDate("fechaDeAlta"));
			}
			return usu;
		}
		catch(Exception ex)
		{
			new LogException(ex,"Fallo el metodo GetOne de Usuario",Level.ERROR);
			return null;
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
				return null;
			}
		}
	}
	
	public boolean GetByUserName(Usuario usu)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "SELECT * FROM Usuarios WHERE usuario = ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, usu.getUsuario());
			rs = pstm.executeQuery();
			
			rs.last();
			if(rs.getRow() > 0)
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
			new LogException(ex,"Fallo el metodo GetByUserName de Usuario",Level.ERROR);
			return false;
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
				return false;
			}
		}
		
	}
	
	public Usuario GetByUsuCon(Usuario usuario)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql += "SELECT * FROM Usuarios WHERE usuario = ? AND contraseña = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, usuario.getUsuario());
			pstm.setString(2, usuario.getContraseña());
			rs = pstm.executeQuery();
			
			Usuario usu = null;
			
			while(rs.next())
			{
				usu = new Usuario();
				usu.setIdUsuario(rs.getInt("idUsuario"));
				usu.setUsuario(rs.getString("usuario"));
				usu.setNombre(rs.getString("nombre"));
				usu.setApellido(rs.getString("apellido"));
				usu.setEmail(rs.getString("email"));
				usu.setTipoUsuario(rs.getInt("tipoUsuario"));
			}
			return usu;
		}
		catch(Exception ex)
		{
			new LogException(ex,"Fallo el metodo GetByUserCon de Usuario",Level.ERROR);
			return null;
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
				return null;
			}
		}
	}
	
	public int GetVentaByUsu(Usuario usu)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "SELECT * FROM Ventas WHERE idUsuario = ? ";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1,usu.getIdUsuario());
			rs = pstm.executeQuery();
			Rows rows = new Rows();
			
			return rows.GetRowCount(rs);
		}
		catch(Exception ex)
		{
			new LogException(ex,"Fallo el metodo GetVentaByUsu de Usuario",Level.ERROR);
			return 0;
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
				return 0;
			}
		}
	}

	public ArrayList<Usuario> GetAll()
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "SELECT * FROM Usuarios ";
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Usuario usu = null;
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			
			while(rs.next())
			{
				usu = new Usuario();
				usu.setIdUsuario(rs.getInt("idUsuario"));
				usu.setUsuario(rs.getString("usuario"));
				usu.setNombre(rs.getString("nombre"));
				usu.setApellido(rs.getString("apellido"));
				usu.setEmail(rs.getString("email"));
				usu.setTipoUsuario(rs.getInt("tipoUsuario"));
				usu.setFechaDeAlta(rs.getDate("fechaDeAlta"));
				usuarios.add(usu);
			}
			return usuarios;
			
		}
		catch(Exception ex)
		{
			new LogException(ex,"Fallo el metodo GetVentaByUsu de Usuario",Level.ERROR);
			return null;
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
				return null;
			}
		}
	}
}
