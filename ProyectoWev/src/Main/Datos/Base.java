package Main.Datos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Base {
	private static Connection con=null;
	
	public static Connection getConnection()
	{
		try {
			if(con == null)
			{
				// con esto determinamos cuando finalize el progreso
				Runtime.getRuntime().addShutdownHook(new MiShDwnHook());
				
				//ResourceBundle rb=ResourceBundle.getBundle("jdbc");
				String driver = "com.mysql.cj.jdbc.Driver"; //rb.getString("driver");
				String url = "jdbc:mysql://localhost:3306/tpjava?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; //rb.getString("url");
				String pwd = "admin"; //rb.getString("pwd");
				String usr = "root"; //rb.getString("usr");
				
				Class.forName(driver);
				con = DriverManager.getConnection(url,usr,pwd);
			}
			return con;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException("error al crear la conexion",ex);
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
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
	}
}
