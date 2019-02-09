package Main.Negocio;

import Main.Entidades.Comentario;
import Main.Entidades.Libro;
import Main.Datos.ComentarioData;
import Main.Datos.LibroData;
import java.util.ArrayList;

public class LibroLogic {
	LibroData libData;
	
	public LibroLogic(){
		libData = new LibroData();
	}
	
	public ArrayList<Libro> GetAll(){
		return libData.GetAll();
	}
	
	public boolean Insert(Libro lib) {
		return libData.Insert(lib);
	}
	
	public Libro GetOne(Libro lib) {
		Libro libro = new Libro();
		libro = libData.GetOne(lib);
		
		ComentarioData comData = new ComentarioData();
		libro.setComentario(comData.GetByLibro(new Comentario(libro.getISBN())));
		
		return libro;
	}
	
	public ArrayList<Libro> GetByCategoria(Libro lib) {
		return libData.GetByCategoria(lib);
	}
	
	public boolean Update(Libro lib) {
		return libData.Update(lib);
	}
	
	public boolean Delete(Libro lib) {
		return libData.Delete(lib);
	}
	
	public ArrayList<Libro> GetLibro(Libro libro){
		return libData.GetLibro(libro);
	}
}
