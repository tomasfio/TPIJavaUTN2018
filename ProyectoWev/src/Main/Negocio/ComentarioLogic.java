package Main.Negocio;

import Main.Entidades.*;
import Main.Datos.*;
import java.util.*;

public class ComentarioLogic {
	ComentarioData comData;
	
	public ComentarioLogic() {
		comData = new ComentarioData();
	}
	
	public boolean Validar(Comentario com) {
		if(com.getComentario() == null || com.getComentario().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean Insert(Comentario com) {
		if(comData.Insert(com)) {
			return true;
		}else {
			return false;
		}
	}
}
