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
			sql += "INSERT INTO usuarios(usuario,contraseña,nombre,apellido,email,tipoUsuario,fechaDeAlta) VALUES(?,?,?,?,?,?,?,?)";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(0, usu.getUsuario());
			pstm.setString(1, usu.getContraseña());
			pstm.setString(2, usu.getNombre());
			pstm.setString(3, usu.getApellido());
			pstm.setString(4, usu.getEmail());
			pstm.setInt(5, usu.getTipoUsuario());
			pstm.setDate(6, (java.sql.Date) usu.getFechaDeAlta());
			
			int resultado = pstm.executeUpdate();
			
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
