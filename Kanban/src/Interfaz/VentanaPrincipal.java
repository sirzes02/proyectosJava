package Interfaz;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private PanelCentral miPanelCentral;

	public VentanaPrincipal() {
		miPanelCentral = new PanelCentral();

		setLayout(new BorderLayout());
		setSize(600, 400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Kanban");
		setVisible(true);
		add(miPanelCentral, BorderLayout.CENTER);
		JLabel footer = new JLabel("Ricardo Loaiza  ");
		footer.setHorizontalAlignment(SwingConstants.RIGHT);
		add(footer, BorderLayout.SOUTH);

		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				try {
					BufferedReader br = new BufferedReader(new FileReader("./data/escritura.txt"));
					String mostrar = "", text;

					while ((text = br.readLine()) != null) {
						mostrar = mostrar.concat(text + "\n");
					}

					JOptionPane.showMessageDialog(null, mostrar, "Reporte", JOptionPane.DEFAULT_OPTION);

					br.close();
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		VentanaPrincipal miVentanaPrincipal = new VentanaPrincipal();
	}

}