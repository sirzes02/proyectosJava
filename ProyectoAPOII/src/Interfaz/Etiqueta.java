package Interfaz;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Etiqueta extends JLabel {

	public Etiqueta(String titulo, int tamano) {
		this.setFont(new Font("Nyala", 0, tamano));
		this.setText(titulo);
		this.setOpaque(true);
	}

	public Etiqueta(String ruta) {
		ImageIcon imagen = new ImageIcon(ruta);
		this.setIcon(imagen);
	}

}
