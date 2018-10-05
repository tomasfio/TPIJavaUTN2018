package Main.Negocio;

import java.util.*;
import Main.Entidades.*;
import Main.Datos.*;

public class UsuarioLogic {
	
	UsuarioData usuData;
	
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
}
