package Main.Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;

import Main.Util.LogException;

public class Base {
	private static Connection con=null;
	
	public static Connection getConnection()
	{
		try {
			FileInputStream inputStream = new FileInputStream("C:\\Users\\Tomas\\git\\TPIJavaUTN2018\\ProyectoWev\\Resource\\jdbc.properties");
			if(con == null)
			{
				// con esto determinamos cuando finalize el progreso
				Runtime.getRuntime().addShutdownHook(new MiShDwnHook());
				
				Properties props = new Properties();
				props.load(inputStream);
				
				String driver = props.getProperty("driver");
				String url = props.getProperty("url");
				String pwd = props.getProperty("psw");
				String usr = props.getProperty("usr");
				
				Class.forName(driver);
				con = DriverManager.getConnection(url,usr,pwd);
			}
			return con;
		}
		catch(IOException exIO) {
			new LogException(exIO,"Error al buscar archivo proporties para conexion a base de datos",Level.FATAL);
			return null;
		} 
		catch (ClassNotFoundException | SQLException exSql) {
			new LogException(exSql,"Error al crear la conexion, no se pudo conectar a mysql",Level.FATAL);
			return null;
        }
		catch(Exception ex)
		{
			new LogException(ex,"Error al crear la conexion",Level.FATAL);
			return null;
		}
	}
	
	static class MiShDwnHook extends Thread
	{
		//justo antes de finalizar el programa la JVM invocara
		//a este metodo donde podemos cerrar la conexion
		public void run()
		{
			try
			{
				Connection con = Base.getConnection();
				con.close();
			}
			catch(Exception ex)
			{
				new LogException(ex,"Fallo cortar la conexion a la base de datos",Level.FATAL); 
			}
		}
	}
}
