package Interface;

import Mundo.Principal;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class crearCancion extends JDialog implements ActionListener {
    private final JTextField nombre;
    private final JTextField duracion;
    private final short indexArtista;
    private final short indexAlbum;

    public crearCancion(short indexArtista, short indexalbum) {
        this.indexAlbum = indexalbum;
        this.indexArtista = indexArtista;

        setTitle("Crear nueva cancion");
        setResizable(false);
        setSize(300, 150);
        setVisible(true);
        setLayout(new GridLayout(3, 2));

        this.add(new JLabel("Nombre"));
        nombre = new JTextField("");
        add(nombre);

        this.add(new JLabel("Duracion"));
        duracion = new JTextField("");
        add(duracion);

        JButton enviar = new JButton("Crear");
        enviar.addActionListener(this);
        add(enviar);
        JButton limpiar = new JButton("Limpiar");
        limpiar.addActionListener(this);
        add(limpiar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()).getActionCommand().equals("Crear")) {
            if (!nombre.getText().equals("") && !duracion.getText().equals("")) {
                Principal miPrincipal = new Principal();
                miPrincipal.getMiArtista(indexArtista).getMiAlbum(indexAlbum).crearCancion(nombre.getText(),
                        duracion.getText());
                miPrincipal.escribirArchivo();
                JOptionPane.showMessageDialog(null, "Cancion creada Correctamente", "Correcto",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error, debe llenar lo necesario", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            nombre.setText("");
            duracion.setText("");
        }
    }
}
