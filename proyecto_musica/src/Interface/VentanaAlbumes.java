package Interface;

import Mundo.Principal;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAlbumes extends JDialog implements ActionListener {
    private final short indexArtista;
    private short indexAlbum;
    private Principal miPrincipal;
    private final Centro miCentro;
    private final surAlbum miSurAlbum;

    public VentanaAlbumes(short indexArtista) {
        this.indexArtista = indexArtista;
        indexAlbum = 0;
        miPrincipal = new Principal();
        setTitle("Artista: " + miPrincipal.getMiArtista(indexArtista).getNombre());
        setLayout(new BorderLayout());
        setSize(600, 450);
        setResizable(false);
        setVisible(true);

        add(new Titulo("Albumes"), BorderLayout.NORTH);

        JButton atras = new JButton("<-");
        atras.addActionListener(this);
        add(atras, BorderLayout.WEST);

        JButton adelante = new JButton("->");
        adelante.addActionListener(this);
        add(adelante, BorderLayout.EAST);

        miCentro = new Centro(
                miPrincipal.getMiArtista(indexArtista).getMiAlbum((short) 0).getFoto(),
                miPrincipal.getMiArtista(indexArtista).getMiAlbum((short) 0).getNombre());
        add(miCentro, BorderLayout.CENTER);

        miSurAlbum = new surAlbum(indexArtista, indexAlbum);
        add(miSurAlbum, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (((JButton) actionEvent.getSource()).getActionCommand().equals("->") &&
                indexAlbum < miPrincipal.getMiArtista(indexArtista).getContadorAlbum() - 1) {
            indexAlbum++;
            miCentro.setImage(
                    miPrincipal.getMiArtista(indexArtista).getMiAlbum(indexAlbum).getFoto(),
                    miPrincipal.getMiArtista(indexArtista).getMiAlbum(indexAlbum).getNombre());
            miSurAlbum.setArtMas();
        } else if (((JButton) actionEvent.getSource()).getActionCommand().equals("<-") && indexAlbum > 0) {
            indexAlbum--;
            miCentro.setImage(
                    miPrincipal.getMiArtista(indexArtista).getMiAlbum(indexAlbum).getFoto(),
                    miPrincipal.getMiArtista(indexArtista).getMiAlbum(indexAlbum).getNombre());
            miSurAlbum.setArtMenos();
        }
        miPrincipal = new Principal();
    }
}
