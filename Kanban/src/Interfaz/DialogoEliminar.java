package Interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Mundo.Tablero;

@SuppressWarnings("serial")
public class DialogoEliminar extends JDialog {

	private Tablero miTablero = new Tablero("./data/lectura.txt");

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DialogoEliminar(String num) {
		setTitle("Eliminar");
		setSize(400, 120);
		setResizable(false);
		setLayout(new GridLayout(2, 2));
		JLabel nombre = new JLabel("Nombre:");
		add(nombre);

		JComboBox miBox;

		if (miTablero.nombresTareas(num) != null) {
			miBox = new JComboBox(miTablero.nombresTareas(num));
		} else {
			miBox = new JComboBox();
			miBox.addItem("Sin opciones.");
		}

		add(miBox);

		JButton eliminar = new JButton("Eliminar");
		eliminar.setEnabled(false);
		add(eliminar);
		eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				miTablero.deleteMiTarea((String) miBox.getSelectedItem());
				JOptionPane.showMessageDialog(null, "Proceso realizado.", "Proceso", JOptionPane.DEFAULT_OPTION);
				dispose();
			}
		});

		miBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eliminar.setEnabled(true);
			}
		});
	}

}
