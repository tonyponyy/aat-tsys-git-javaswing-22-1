package models;

import java.util.List;

import mysql.Conection;

public class Cliente {
	static Conection conexion = new Conection();
	private String ATRIBUTOS = "nombre,apellido,dni,direccion,fecha";
	private static String DATABASE_NAME = "clientes";
	private static String TABLE_NAME = "cliente";
	
	public List<Object[]> mostrar_todos() {
		conexion.usar_esquema(DATABASE_NAME);
		return conexion.mostrar_tabla(TABLE_NAME);
	}
	
	public boolean guardar(String nombre,String apellido,String dni,String direccion,String fecha) {
		conexion.usar_esquema(DATABASE_NAME);
		if (conexion.agregar_registro(TABLE_NAME, ATRIBUTOS, "'"+nombre+"','"+apellido+"','"+dni+"','"+direccion+"','"+fecha+"'")) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean borrar(String id) {
		conexion.usar_esquema(DATABASE_NAME);
		if (conexion.ejecuta_sentencia("delete from "+TABLE_NAME+" where id ="+id)) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean editar(String id,String nombre,String apellido,String dni,String direccion,String fecha) {
		conexion.usar_esquema(DATABASE_NAME);
		if (conexion.ejecuta_sentencia("update "+TABLE_NAME+" set nombre ='"+nombre+"',apellido ='"+apellido+"',dni='"+dni+"',direccion='"+direccion+"',fecha='"+fecha+"' where id="+id)) {
			return true;
		}else {
			return false;
		}
	}
	


}
