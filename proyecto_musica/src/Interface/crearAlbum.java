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

public class crearAlbum extends JDialog implements ActionListener {
    private final JTextField nombre;
    private final JTextField genero;
    private final JTextField fecha;
    private final JTextField foto;
    private final short indexArtista;

    public crearAlbum(short indexArtista) {
        this.indexArtista = indexArtista;

        setTitle("Crear nuevo album");
        setResizable(false);
        setSize(300, 250);
        setVisible(true);
        setLayout(new GridLayout(5, 2));

        this.add(new JLabel("Nombre"));
        nombre = new JTextField("");
        add(nombre);

        this.add(new JLabel("Genero"));
        genero = new JTextField("");
        add(genero);

        this.add(new JLabel("Fecha Lanzamiento"));
        fecha = new JTextField("");
        add(fecha);

        this.add(new JLabel("Foto"));
        foto = new JTextField("");
        add(foto);

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
            if (!nombre.getText().equals("") && !genero.getText().equals("") && !fecha.getText().equals("")) {
                Principal miPrincipal = new Principal();
                miPrincipal.getMiArtista(indexArtista).crearAlbum(nombre.getText(), genero.getText(), fecha.getText(),
                        foto.getText());
                miPrincipal.escribirArchivo();
                JOptionPane.showMessageDialog(null, "Album creado Correctamente", "Correcto",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error, debe llenar lo necesario", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            nombre.setText("");
            genero.setText("");
            fecha.setText("");
            foto.setText("");
        }
    }
}
