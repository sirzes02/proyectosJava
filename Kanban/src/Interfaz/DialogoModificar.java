package Interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Mundo.Tablero;

@SuppressWarnings("serial")
public class DialogoModificar extends JDialog {

	private Tablero miTablero = new Tablero("./data/lectura.txt");

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DialogoModificar(String num) {
		setTitle("Modificar");
		setLayout(new GridLayout(5, 2));
		setResizable(false);
		setSize(400, 400);

		JLabel seleccione = new JLabel("Seleccione:");
		add(seleccione);

		JComboBox miBox;

		if (miTablero.nombresTareas(num) != null) {
			miBox = new JComboBox(miTablero.nombresTareas(num));
		} else {
			miBox = new JComboBox();
			miBox.addItem("Sin opciones.");
		}

		add(miBox);

		JLabel nombre = new JLabel("Nombre:");
		add(nombre);
		JTextField nombreNuevo = new JTextField();
		nombreNuevo.setText("Vacio");
		nombreNuevo.setEnabled(false);
		add(nombreNuevo);
		JLabel estado = new JLabel("Estado:");
		add(estado);

		JComboBox miEstado = new JComboBox();
		add(miEstado);
		JLabel prioridad = new JLabel("Prioridad:");
		add(prioridad);
		JComboBox miPrioridad = new JComboBox();
		add(miPrioridad);

		switch (Integer.parseInt(num)) {
		case 1:
			miEstado.addItem("Por hacer");
			miEstado.addItem("En curso");
			miEstado.addItem("Finalizado");

			miPrioridad.addItem("Urgente");
			miPrioridad.addItem("Importante");
			miPrioridad.addItem("Normal");
			break;
		case 2:
			miEstado.addItem("En curso");
			miEstado.addItem("Por hacer");
			miEstado.addItem("Finalizado");

			miPrioridad.addItem("Urgente");
			miPrioridad.addItem("Importante");
			miPrioridad.addItem("Normal");
			break;
		case 3:
			miEstado.addItem("Finalizado");
			miEstado.addItem("En curso");
			miEstado.addItem("Por hacer");

			miPrioridad.addItem("Urgente");
			miPrioridad.addItem("Importante");
			miPrioridad.addItem("Normal");
			break;
		case 4:
			miEstado.addItem("Por hacer");
			miEstado.addItem("Finalizado");
			miEstado.addItem("En curso");

			miPrioridad.addItem("Importante");
			miPrioridad.addItem("Urgente");
			miPrioridad.addItem("Normal");
			break;
		case 5:
			miEstado.addItem("En curso");
			miEstado.addItem("Por hacer");
			miEstado.addItem("Finalizado");

			miPrioridad.addItem("Importante");
			miPrioridad.addItem("Urgente");
			miPrioridad.addItem("Normal");
			break;
		case 6:
			miEstado.addItem("Finalizado");
			miEstado.addItem("En curso");
			miEstado.addItem("Por hacer");

			miPrioridad.addItem("Importante");
			miPrioridad.addItem("Urgente");
			miPrioridad.addItem("Normal");
			break;
		case 7:
			miEstado.addItem("Por hacer");
			miEstado.addItem("Finalizado");
			miEstado.addItem("En curso");

			miPrioridad.addItem("Normal");
			miPrioridad.addItem("Importante");
			miPrioridad.addItem("Urgente");
			break;
		case 8:
			miEstado.addItem("En curso");
			miEstado.addItem("Por hacer");
			miEstado.addItem("Finalizado");

			miPrioridad.addItem("Normal");
			miPrioridad.addItem("Importante");
			miPrioridad.addItem("Urgente");
			break;
		case 9:
			miEstado.addItem("Finalizado");
			miEstado.addItem("En curso");
			miEstado.addItem("Por hacer");

			miPrioridad.addItem("Normal");
			miPrioridad.addItem("Importante");
			miPrioridad.addItem("Urgente");
			break;
		}

		miEstado.setEnabled(false);

		miPrioridad.setEnabled(false);

		JButton boton = new JButton("Modificar");
		boton.setEnabled(false);
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				miTablero.modificarTarea((String) miBox.getSelectedItem(), nombreNuevo.getText(),
						(String) miEstado.getSelectedItem(), (String) miPrioridad.getSelectedItem());
				JOptionPane.showMessageDialog(null, "Proceso realizado.", "Proceso", JOptionPane.DEFAULT_OPTION);
				dispose();
			}
		});

		miBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!miBox.getSelectedItem().equals("Sin opciones.")) {
					nombreNuevo.setEnabled(true);
					nombreNuevo.setText((String) miBox.getSelectedItem());
					miEstado.setEnabled(true);
					miPrioridad.setEnabled(true);
					boton.setEnabled(true);
				}
			}
		});

		add(boton);
	}

}
