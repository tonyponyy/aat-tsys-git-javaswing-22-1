package controllers;

import views.Vista_cliente;
import views.Vista_cliente_nuevo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import models.Cliente;

public class Cliente_controlador {
	String id_temp = "0";
	Cliente clientes = new Cliente();
	Vista_cliente_nuevo vista_nuevo = new Vista_cliente_nuevo();

	public Cliente_controlador() {
		inicio();

	}

	public void aniadir_fila_cliente() {
		List<Object[]> filas = (List<Object[]>) clientes.mostrar_todos();
		for (Object[] fila : filas) {
			Vista_cliente.modelo_tabla.addRow(fila);
		}
	}

	public void inicio() {
		try {
			Vista_cliente.nuevo_cliente.addActionListener(nuevo_clientes);
			Vista_cliente.eliminar_cliente.addActionListener(borrar_clientes);
			Vista_cliente.editar_cliente.addActionListener(editar_clientes);
			Vista_cliente frame = new Vista_cliente();
			aniadir_fila_cliente();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void nuevo() {
		resetear_boton_guardar();
		resetear_nombres();
		Vista_cliente_nuevo.guardar.addActionListener(guardar_clientes);
		vista_nuevo.setVisible(true);
	}

	public void resetear_boton_guardar() {
		Vista_cliente_nuevo.guardar.removeActionListener(guardar_clientes);
		Vista_cliente_nuevo.guardar.removeActionListener(update);
	}

	public void editar() {
		try {
			resetear_boton_guardar();
			Vista_cliente_nuevo.guardar.addActionListener(update);
			int pos = Vista_cliente.tabla_cliente.getSelectedRow();
			int id = (int) Vista_cliente.tabla_cliente.getValueAt(pos, 0);
			id_temp = id + "";
			vista_nuevo.nombre.setText(Vista_cliente.tabla_cliente.getValueAt(pos, 1) + "");
			vista_nuevo.apellidos.setText(Vista_cliente.tabla_cliente.getValueAt(pos, 2) + "");
			vista_nuevo.dni.setText(Vista_cliente.tabla_cliente.getValueAt(pos, 4) + "");
			vista_nuevo.direccion.setText(Vista_cliente.tabla_cliente.getValueAt(pos, 3) + "");
			vista_nuevo.fecha.setText(Vista_cliente.tabla_cliente.getValueAt(pos, 5) + "");
			vista_nuevo.setVisible(true);
			vista_nuevo.fecha.getText();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Seleccione una opción");
		}
	}

	public void resetear_nombres() {
		vista_nuevo.nombre.setText("nombre");
		vista_nuevo.apellidos.setText("apellidos");
		vista_nuevo.dni.setText("dni");
		vista_nuevo.direccion.setText("direccion");
		vista_nuevo.fecha.setText("fecha");
	}

	public void editar_cliente() {
		String nombre = vista_nuevo.nombre.getText();
		String apellidos = vista_nuevo.apellidos.getText();
		String dni = vista_nuevo.dni.getText();
		String direccion = vista_nuevo.direccion.getText();
		String fecha = vista_nuevo.fecha.getText();
		if (Cliente.editar(id_temp, nombre, apellidos, dni, direccion, fecha)) {
			JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
			actualizar_index();
			vista_nuevo.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, "Revise los valores y intentelo de nuevo");
		}
	}

	public void guardar_cliente() {
		String nombre = vista_nuevo.nombre.getText();
		String apellidos = vista_nuevo.apellidos.getText();
		String dni = vista_nuevo.dni.getText();
		String direccion = vista_nuevo.direccion.getText();
		String fecha = vista_nuevo.fecha.getText();
		if (clientes.guardar(nombre, apellidos, dni, direccion, fecha)) {
			JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
			actualizar_index();
			vista_nuevo.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, "Revise los valores y intentelo de nuevo");
		}
	}

	public void actualizar_index() {
		Vista_cliente.modelo_tabla.setRowCount(0);
		aniadir_fila_cliente();
	}

	public void borrar_cliente() {
		int pos = Vista_cliente.tabla_cliente.getSelectedRow();
		int id = (int) Vista_cliente.tabla_cliente.getValueAt(pos, 0);
		if (Cliente.borrar(id + "")) {
			actualizar_index();
			JOptionPane.showMessageDialog(null, "Registro con id " + id + " borrado");
		} else {
			JOptionPane.showMessageDialog(null, "Error al borrar el registro");
		}
	}

	ActionListener borrar_clientes = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			borrar_cliente();
		}
	};

	ActionListener nuevo_clientes = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			nuevo();
		}
	};

	ActionListener guardar_clientes = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			guardar_cliente();
		}
	};

	ActionListener editar_clientes = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			editar();
		}
	};

	ActionListener update = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			editar_cliente();
		}
	};

}
