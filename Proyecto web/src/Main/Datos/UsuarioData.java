package Main.Datos;

import java.util.Collection;
import java.util.Vector;
import Main.Entidades.*;
import java.sql.*;

public class UsuarioData {
	
	public Collection<Usuario> GetAll()
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql += "SELCET * FROM Usuarios";
			
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
			sql += "SELCET * FROM Usuarios WHERE usuario = ? AND contraseña = ?";
			
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
