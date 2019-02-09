package Main.Datos;

import Main.Entidades.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public class ComentarioData {
	
	public boolean Insert(Comentario com)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		
		try
		{
			con = Base.getConnection();
			String sql = "INSERT INTO comentarios(fechaHora,usuarioID,ISBN,comentario) VALUES(?,?,?,?)";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(com.getFechaHora()));
			pstm.setInt(2, com.getUsuario().getIdUsuario());
			pstm.setInt(3, com.getLibro().getISBN());
			pstm.setString(4, com.getComentario());
			
			int resultado = pstm.executeUpdate();
			
			if(resultado == 1)
				return true;
			else
				return false;
		}
		catch(SQLException ex) {
			return false;
		}
		catch(Exception ex)
		{
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

	public ArrayList<Comentario> GetByLibro(Comentario com)
	{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		UsuarioData usuData = new UsuarioData();
		LibroData libData = new LibroData();
		
		try
		{
			con = Base.getConnection();
			String sql = "";
			sql = "SELECT * FROM comentarios WHERE ISBN = ? ORDER BY fechaHora LIMIT 20";
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, com.getLibro().getISBN());
			rs = pstm.executeQuery();

			ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
			
			while(rs.next())
			{
				com = new Comentario();
				com.setFechaHora(rs.getDate("fechaHora"));
				com.setUsuario(usuData.GetOne(new Usuario(rs.getInt("usuarioID"))));
				com.setLibro(libData.GetOne(new Libro(rs.getInt("ISBN"))));
				com.setComentario(rs.getString("comentario"));
				
				comentarios.add(com);
			}
			return comentarios;
		}
		catch(SQLException ex) {
			return null;
		}
		catch(Exception ex)
		{
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
