package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * @Author: Rene Cruz 
 * @Date: Wednesday, May 25th, 2022
 * */

public class Conexion {
	//ATRIBUTES
	private static String url = "jdbc:derby://localhost:1527/db_20p";
	
	//METHODS
	//? Generar la conexion a la Base de Datos
	public Connection getConnection () {
		try {
			return DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
