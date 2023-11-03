package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class Vista_cliente_nuevo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField nombre= new JTextField();
	public JTextField apellidos= new JTextField();
	public JTextField direccion= new JTextField();
	public JTextField dni= new JTextField();
	public JTextField fecha= new JTextField();
	public static JButton guardar = new JButton("guardar");


	


	public Vista_cliente_nuevo() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 395, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		guardar.setVerticalAlignment(SwingConstants.TOP);
		guardar.setBounds(188, 73, 168, 23);
		contentPane.add(guardar);
		
		nombre.setText("nombre");
		nombre.setBounds(10, 12, 168, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		apellidos.setText("apellidos");
		apellidos.setColumns(10);
		apellidos.setBounds(188, 12, 168, 20);
		contentPane.add(apellidos);
		
		direccion.setText("direccion");
		direccion.setColumns(10);
		direccion.setBounds(10, 43, 168, 20);
		contentPane.add(direccion);
		
		dni.setText("dni");
		dni.setColumns(10);
		dni.setBounds(188, 43, 168, 20);
		contentPane.add(dni);
		
		fecha.setText("fecha");
		fecha.setColumns(10);
		fecha.setBounds(10, 74, 168, 20);
		contentPane.add(fecha);
	}

}
