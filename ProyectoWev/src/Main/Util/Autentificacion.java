package Main.Util;

import Main.Entidades.Usuario;

public class Autentificacion {
	
	public boolean AutentificacionCliente(Usuario usu) {
		if(usu != null) {
			if(usu.getTipoUsuario() == 1) {
				return true;
			}
		}
		return false;
	}
	
	public boolean AutentificacionAdministrador(Usuario usu) {
		if(usu != null) {
			if(usu.getTipoUsuario() == 0) {
				return true;
			}
		}
		return false;
	}

}
