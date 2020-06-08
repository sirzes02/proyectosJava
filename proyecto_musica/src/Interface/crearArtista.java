package Interface;

import Mundo.Principal;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class crearArtista extends JDialog implements ActionListener {
    private final JTextField nombre;
    private final JTextField fechaN;
    private final JTextField fechaD;
    private final JTextField foto;
    private final JCheckBox act;

    public crearArtista() {
        setTitle("Crear nuevo artista");
        setResizable(false);
        setSize(300, 300);
        setVisible(true);
        setLayout(new GridLayout(6, 2));

        this.add(new JLabel("Nombre"));
        nombre = new JTextField("");
        add(nombre);

        this.add(new JLabel("Fecha Nacimiento"));
        fechaN = new JTextField("");
        add(fechaN);

        this.add(new JLabel("Fecha Debout"));
        fechaD = new JTextField("");
        add(fechaD);

        this.add(new JLabel());
        act = new JCheckBox("Activo");
        add(act);

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
            if (!nombre.getText().equals("") && !fechaD.getText().equals("") && !fechaN.getText().equals("")) {
                Principal miPrincipal = new Principal();
                miPrincipal.crearArtista(nombre.getText(), fechaN.getText(), fechaD.getText(), act.isSelected(),
                        foto.getText());
                miPrincipal.escribirArchivo();
                JOptionPane.showMessageDialog(null, "Artista creado Correctamente", "Correcto",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error, debe llenar lo necesario", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            nombre.setText("");
            fechaN.setText("");
            fechaD.setText("");
            act.setSelected(false);
            foto.setText("");
        }
    }
}
