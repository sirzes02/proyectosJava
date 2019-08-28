package Interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Mundo.Tablero;

@SuppressWarnings("serial")
public class DialogoAgregar extends JDialog {

	private Tablero miTablero = new Tablero("./data/lectura.txt");

	public DialogoAgregar(String num) {
		setTitle("Agregar");
		setSize(400, 120);
		setLayout(new GridLayout(2, 2));
		JLabel nombre = new JLabel("Nombre:");
		add(nombre);
		JTextField agregarNombre = new JTextField();
		add(agregarNombre);

		JButton aceptar = new JButton("Aceptar");
		add(aceptar);
		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!agregarNombre.getText().equals("")) {
					switch (Integer.parseInt(num)) {
					case 1:
						miTablero.setMiTarea(agregarNombre.getText(), "Por Hacer", "Urgente");
						break;
					case 2:
						miTablero.setMiTarea(agregarNombre.getText(), "En curso", "Urgente");
						break;
					case 3:
						miTablero.setMiTarea(agregarNombre.getText(), "Finalizada", "Urgente");
						break;
					case 4:
						miTablero.setMiTarea(agregarNombre.getText(), "Por hacer", "Importante");
						break;
					case 5:
						miTablero.setMiTarea(agregarNombre.getText(), "En curso", "Importante");
						break;
					case 6:
						miTablero.setMiTarea(agregarNombre.getText(), "Finalizada", "Importante");
						break;
					case 7:
						miTablero.setMiTarea(agregarNombre.getText(), "Por hacer", "Normal");
						break;
					case 8:
						miTablero.setMiTarea(agregarNombre.getText(), "En curso", "Normal");
						break;
					case 9:
						miTablero.setMiTarea(agregarNombre.getText(), "Finalizada", "Normal");
						break;
					}
					JOptionPane.showMessageDialog(null, "Proceso realizado.", "Proceso", JOptionPane.DEFAULT_OPTION);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Debe ingresar un nombre.", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
}