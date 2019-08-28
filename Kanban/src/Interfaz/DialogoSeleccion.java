package Interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

@SuppressWarnings("serial")
public class DialogoSeleccion extends JDialog {

	private DialogoAgregar miDialogoAgregar;
	private DialogoEliminar miDialogoEliminar;
	private DialogoModificar miDialogoModificar;

	public DialogoSeleccion(String num) {
		setTitle("Selecciones");
		setLayout(new GridLayout(3, 1));
		setResizable(false);
		setSize(100, 300);

		JButton miButton[] = new JButton[3];

		miButton[0] = new JButton("Agregar");
		add(miButton[0]);
		miButton[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				miDialogoAgregar = new DialogoAgregar(num);
				miDialogoAgregar.setVisible(true);
				dispose();
			}
		});
		miButton[1] = new JButton("Eliminar");
		add(miButton[1]);
		miButton[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				miDialogoEliminar = new DialogoEliminar(num);
				miDialogoEliminar.setVisible(true);
				dispose();
			}
		});
		miButton[2] = new JButton("Modificar");
		add(miButton[2]);
		miButton[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				miDialogoModificar = new DialogoModificar(num);
				miDialogoModificar.setVisible(true);
				dispose();
			}
		});
	}

}
