package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Conection {
	private static Connection connection;
	
	

	public Conection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:33060?useTimezone=true&serverTimezone=UTC", "root", "123456");
			System.out.println("CONNECTION OK");
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("BAD CONNECTION--> " + ex);
		}
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean ejecuta_sentencia(String sentencia) {
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(sentencia);
			return true;
		} catch (SQLException ex) {
			System.out.println("Error--> " + ex);
			return false;
		}
	}

	public static List<Object[]> ejecuta_query(String query) {
	    List<Object[]> resultadosList = new ArrayList<>();
	    try {
	        Statement st = connection.createStatement();
	        ResultSet resultados = st.executeQuery(query);
	        ResultSetMetaData meta_data = resultados.getMetaData();
	        int num_columnas = meta_data.getColumnCount();

	        while (resultados.next()) {
	            Object[] fila = new Object[num_columnas];
	            for (int i = 1; i <= num_columnas; i++) {
	                fila[i - 1] = resultados.getObject(i);
	            }
	            resultadosList.add(fila);
	        }

	        return resultadosList;
	    } catch (SQLException ex) {
	        System.out.println("Error--> " + ex);
	        return resultadosList; 
	    }
	}
	
	public static void usar_esquema(String nombre_esquema) {
		ejecuta_sentencia("USE "+nombre_esquema);
	}

	public static boolean agregar_registro(String nombre_tabla,String atributos_tabla,String valores_registro) {
		String sentencia = "insert into "+nombre_tabla+"("+atributos_tabla+") values ("+valores_registro+")";
		if (ejecuta_sentencia(sentencia)) {
			return true;
		}else {
			return false;
		}
	}
	
	public static List<Object[]> mostrar_tabla(String nombre_tabla) {
		String sentencia = "select * from "+nombre_tabla;
		return ejecuta_query(sentencia);
	}
	

}
