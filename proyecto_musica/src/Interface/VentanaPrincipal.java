package Interface;

import Mundo.Principal;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class VentanaPrincipal extends JFrame implements ActionListener, WindowListener {

    private Principal miPrincipal;
    private final Centro miCentro;
    private short contArtista;
    private Sur miSur;

    public VentanaPrincipal() {
        addWindowListener(this);
        setTitle("Proyecto");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setResizable(false);

        add(new Titulo("Artistas"), BorderLayout.NORTH);

        JButton atras = new JButton("<-");
        atras.addActionListener(this);
        add(atras, BorderLayout.WEST);

        JButton adelante = new JButton("->");
        adelante.addActionListener(this);
        add(adelante, BorderLayout.EAST);
        miSur = new Sur(contArtista);
        add(miSur, BorderLayout.SOUTH);

        contArtista = 0;
        miPrincipal = new Principal();
        miCentro = new Centro(miPrincipal.getMiArtista((short) 0).getFoto(), miPrincipal.getMiArtista((short) 0).getNombre());
        add(miCentro, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()).getActionCommand().equals("->") &&
                contArtista < miPrincipal.getContadorArtista() - 1) {
            contArtista++;
            miSur.setArtMas();
            miCentro.setImage(
                    miPrincipal.getMiArtista(contArtista).getFoto(),
                    miPrincipal.getMiArtista(contArtista).getNombre());
        } else if (((JButton) e.getSource()).getActionCommand().equals("<-") && contArtista > 0) {
            contArtista--;
            miSur.setArtMenos();
            miCentro.setImage(
                    miPrincipal.getMiArtista(contArtista).getFoto(),
                    miPrincipal.getMiArtista(contArtista).getNombre());
        }
        miPrincipal = new Principal();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        JOptionPane.showMessageDialog(null, "Muchas gracias por usar.");
        miPrincipal.escribirArchivo();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
