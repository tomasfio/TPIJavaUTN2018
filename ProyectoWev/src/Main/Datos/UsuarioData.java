package Main.Datos;

import java.util.Collection;
import java.util.Vector;
import Main.Entidades.*;
import java.sql.*;

public class UsuarioData {
	
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
	
	public boolean Update(Usuario usu)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "UPDATE Usuarios SET nombre = ?, apelldio = ?,email = ? WHERE idUsuario = ?";
			
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
	
	public Usuario GetOne(int id)
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
			pstm.setInt(1, id);
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
	
	public boolean GetByUserName(String usr)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "SELECT * FROM Usuarios WHERE nombre = ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, usr);
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
	
	public Collection<Usuario> GetAll()
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql += "SELECT * FROM Usuarios";
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Vector<Usuario> Usuarios = new Vector<Usuario>();
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
				Usuarios.add(usu);
			}
			return Usuarios;
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
	
	public Usuario GetByUsuCon(String nom,String pass)
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
			pstm.setString(1, nom);
			pstm.setString(2, pass);
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
