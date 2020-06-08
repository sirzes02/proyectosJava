package Interface;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class CentroCancion extends JPanel {

    private final JLabel nombre;
    private final JLabel duracion;

    public CentroCancion(String nombre, String duracion) {
        setLayout(new GridLayout(2, 2));

        add(new JLabel("Nombre:"));
        this.nombre = new JLabel("Nombre: " + nombre + ".");
        add(this.nombre);
        add(new JLabel("Duracion:"));
        this.duracion = new JLabel("duracion: " + duracion + ".");
        add(this.duracion);
    }

    public void set(String nombre, String duracion){
        this.nombre.setText(nombre);
        this.duracion.setText(duracion);
    }
}
