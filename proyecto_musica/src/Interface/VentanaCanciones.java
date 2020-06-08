package Interface;

import Mundo.Principal;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCanciones extends JDialog implements ActionListener {
    private final short indexArtista;
    private final short indexAlbum;
    private short indexCancion;
    private Principal miPrincipal;
    private final CentroCancion miCentroCancion;

    public VentanaCanciones(short indexArtista, short indexAlbum) {
        this.indexArtista = indexArtista;
        this.indexAlbum = indexAlbum;
        indexCancion = 0;

        miPrincipal = new Principal();
        setTitle("Album: " + miPrincipal.getMiArtista(indexArtista).getMiAlbum(indexAlbum).getNombre() + " - Artista: "
                + miPrincipal.getMiArtista(indexArtista).getNombre());
        setLayout(new BorderLayout());
        setSize(600, 200);
        setResizable(false);
        setVisible(true);

        add(new Titulo("Canciones"), BorderLayout.NORTH);

        JButton atras = new JButton("<-");
        atras.addActionListener(this);
        add(atras, BorderLayout.WEST);

        JButton adelante = new JButton("->");
        adelante.addActionListener(this);
        add(adelante, BorderLayout.EAST);

        miCentroCancion = new CentroCancion(
                miPrincipal.getMiArtista(indexArtista).getMiAlbum(indexAlbum).getMiCancion((short) 0).getNombre(),
                miPrincipal.getMiArtista(indexArtista).getMiAlbum(indexAlbum).getMiCancion((short) 0).getDuracion());
        add(miCentroCancion, BorderLayout.CENTER);

        JButton crear = new JButton("Nuevo");
        crear.addActionListener(this);
        add(crear, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (((JButton) actionEvent.getSource()).getActionCommand().equals("->") && indexCancion < miPrincipal
                .getMiArtista(indexArtista).getMiAlbum(indexAlbum).getContadorCancion() - 1) {
            indexCancion++;
            miCentroCancion.set(
                    miPrincipal.getMiArtista(indexArtista).getMiAlbum(indexAlbum).getMiCancion(indexCancion)
                            .getNombre(),
                    miPrincipal.getMiArtista(indexArtista).getMiAlbum(indexAlbum).getMiCancion(indexCancion)
                            .getDuracion());
        } else if (((JButton) actionEvent.getSource()).getActionCommand().equals("<-") && indexAlbum > 0) {
            indexCancion--;
            miCentroCancion.set(
                    miPrincipal.getMiArtista(indexArtista).getMiAlbum(indexAlbum).getMiCancion(indexCancion)
                            .getNombre(),
                    miPrincipal.getMiArtista(indexArtista).getMiAlbum(indexAlbum).getMiCancion(indexCancion)
                            .getDuracion());
        } else if (((JButton) actionEvent.getSource()).getActionCommand().equals("Nuevo")){
            new crearCancion(indexArtista, indexAlbum);
        }
        miPrincipal = new Principal();
    }
}
