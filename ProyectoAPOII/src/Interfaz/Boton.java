package Interfaz;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Boton extends JButton {

	public Boton(String ruta) {
		if (ruta.charAt(0) == '.') {
			ImageIcon imagen = new ImageIcon(ruta);
			this.setIcon(imagen);
		} else {
			this.setText(ruta);
			this.setFont(new Font("Eras Bold ITC", 0, 30));
		}
		// this.setOpaque(true);
	}

}
