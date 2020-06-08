package Interface;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.io.File;

public class Centro extends JPanel {

    private final JLabel nombreArtista;
    private final JLabel img;

    public Centro(String foto, String nombre) {
        setLayout(new BorderLayout());

        if (new File(foto).exists()) {
            img = new JLabel(new ImageIcon(foto));
        } else {
            img = new JLabel(new ImageIcon("./img/404.jpg"));
        }

        nombreArtista = new JLabel("Nombre: " + nombre + ".");
        add(nombreArtista, BorderLayout.SOUTH);
        add(img, BorderLayout.CENTER);
    }

    public void setImage(String foto, String nombre) {
        if (new File(foto).exists()) {
            img.setIcon(new ImageIcon(foto));
        } else {
            img.setIcon(new ImageIcon("./img/404.jpg"));
        }

        nombreArtista.setText("Nombre: " + nombre + ".");
    }
}
