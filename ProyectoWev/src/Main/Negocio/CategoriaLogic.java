package Main.Negocio;

import Main.Entidades.*;
import Main.Datos.*;
import java.util.*;

public class CategoriaLogic {
	CategoriaData catData;
	
	public boolean Validar(Categoria cat) {
		if(cat.getNombre().isEmpty() || cat.getDescipcion().isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean ValidarDelete(Categoria cat) {
		LibroData libData = new LibroData();
		Libro lib = new Libro();
		lib.setCategoria(cat);
		if(libData.GetByCategoria(lib).size() != 0)
		{
			return false;			
		}

		return true;
	}
	
	public CategoriaLogic()
	{
		catData = new CategoriaData();
	}
	
	public ArrayList<Categoria> GetAll()
	{
		return catData.GetAll();
	}
	
	public boolean Insert(Categoria cat)
	{
		return catData.Insert(cat);
	}
	
	public Categoria GetOne(Categoria categoria) {
		return catData.GetOne(categoria);
	}
	
	public boolean Detele(Categoria cat) {
		return catData.Delete(cat);
	}
	
	public boolean Update(Categoria cat) {
		return catData.Update(cat);
	}

}
