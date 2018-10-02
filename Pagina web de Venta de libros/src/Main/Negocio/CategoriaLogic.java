package Main.Negocio;

import Main.Entidades.*;
import Main.Datos.*;
import java.util.*;

public class CategoriaLogic {
	CategoriaData catData;
	
	public CategoriaLogic()
	{
		catData = new CategoriaData();
	}
	
	public Collection<Categoria> GetAll()
	{
		return catData.GetAll();
	}

}
