package Interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PanelCentral extends JPanel implements ActionListener{

	private DialogoSeleccion miDialogoSeleccion;
	
	public PanelCentral() {

		setLayout(new GridLayout(4, 4));

		JLabel logo = new JLabel(), titulos[] = new JLabel[3], filas[] = new JLabel[3];
		JButton relleno[][] = new JButton[3][3];
		String nombre[] = { "Urgente", "Importante", "Normal" };

		ImageIcon imagen = new ImageIcon("./img/uscLogo.png");
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		
		logo.setHorizontalAlignment(SwingConstants.CENTER);

		logo.setIcon(icono);
		
		logo.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		add(logo);
		titulos[0] = new JLabel("Por Hacer");
		titulos[0].setHorizontalAlignment(SwingConstants.CENTER);
		titulos[0].setBorder(BorderFactory.createLineBorder(Color.black, 1));
		add(titulos[0]);
		titulos[1] = new JLabel("En curso");
		titulos[1].setHorizontalAlignment(SwingConstants.CENTER);
		titulos[1].setBorder(BorderFactory.createLineBorder(Color.black, 1));
		add(titulos[1]);
		titulos[2] = new JLabel("Finalizada");
		titulos[2].setHorizontalAlignment(SwingConstants.CENTER);
		titulos[2].setBorder(BorderFactory.createLineBorder(Color.black, 1));
		add(titulos[2]);

		short cant = 0;
		for (int i = 0, cont = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (j == 0) {
					filas[j] = new JLabel(nombre[cont]);
					cont++;
					filas[j].setHorizontalAlignment(SwingConstants.CENTER);
					filas[j].setBorder(BorderFactory.createLineBorder(Color.black, 1));
					add(filas[j]);
				} else {
					cant++;
					relleno[i][j - 1] = new JButton(String.valueOf(cant));
					relleno[i][j - 1].addActionListener(this);
					add(relleno[i][j - 1]);
				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		miDialogoSeleccion = new DialogoSeleccion(e.getActionCommand());
		miDialogoSeleccion.setVisible(true);
	}

}
