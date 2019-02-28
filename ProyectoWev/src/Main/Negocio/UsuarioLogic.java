package Main.Negocio;

import java.util.*;
import Main.Entidades.*;
import Main.Util.ValidacionEmail;
import Main.Datos.*;

public class UsuarioLogic {
	
	UsuarioData usuData;
	
	public boolean ValidarDelete(Usuario usu) {
		if(usuData.GetVentaByUsu(usu) != 0)
			return false;
		else
			return true;
	}
	
	public boolean Validar(Usuario usu) {
		if(usu.getNombre().isEmpty() || usu.getApellido().isEmpty() || usu.getUsuario().isEmpty() ||
				usu.getEmail().isEmpty() || usu.getContraseña().isEmpty() || usu.getContraseña().length() < 8 ) {
			return false;
		}
		
		if(!ValidacionEmail.ValidarMail(usu.getEmail())) {
			return false;
		}
				
		return true;
	}
	
	public boolean ValidarUpdate(Usuario usu) {
		if(usu.getNombre().isEmpty() || usu.getApellido().isEmpty() || usu.getUsuario().isEmpty() ||
				usu.getEmail().isEmpty()) {
			return false;
		}
		
		if(!ValidacionEmail.ValidarMail(usu.getEmail())) {
			return false;
		}
				
		return true;
	}
	
	public UsuarioLogic() {
		usuData = new UsuarioData();
	}
	
	public Collection<Usuario> GetAll(){
		return usuData.GetAll();
	}
	
	public boolean Insert(Usuario usu) {
		return usuData.Insert(usu);
	}

	public boolean Delete(Usuario usu) {
		return usuData.Delete(usu);
	}
	
	public boolean Update(Usuario usu) {
		return usuData.Update(usu);
	}
	
	public Usuario GetOne(Usuario usu) {
		return usuData.GetOne(usu);
	}
	
	public boolean GetByUserName(Usuario usu) {
		return usuData.GetByUserName(usu);
	}
	
	public Usuario GetByUsuCon(Usuario usu) {
		return usuData.GetByUsuCon(usu);
	}
	
	public ArrayList<Usuario> GetByTipoUsuario(Usuario usu)
	{
		if(usu == null)
		{
			usu = new Usuario();
			usu.setTipoUsuario(0);
		}
		return usuData.GetByTipoUsuario(usu);
	}
}
