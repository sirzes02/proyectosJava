package Interface;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class Titulo extends JPanel {

    public Titulo(String titulo) {
        add(new JLabel(titulo));
    }
}
