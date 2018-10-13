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
	
	public ArrayList<Categoria> GetAll()
	{
		return catData.GetAll();
	}
	
	public Categoria Insert(Categoria cat)
	{
		return catData.Insert(cat);
	}

}
