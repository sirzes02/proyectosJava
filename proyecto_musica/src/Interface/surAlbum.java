package Interface;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class surAlbum extends JPanel implements ActionListener {
    private short indexArtista;
    private short indexAlbum;

    public surAlbum(short indexArtista, short indexalbum) {
        setLayout(new BorderLayout());
        JButton ingresar = new JButton("Agregar");
        ingresar.addActionListener(this);
        add(ingresar, BorderLayout.EAST);

        JButton verEditar = new JButton("Ver / Editar");
        verEditar.addActionListener(this);
        add(verEditar, BorderLayout.WEST);

        this.indexAlbum = indexalbum;
        this.indexArtista = indexArtista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()).getActionCommand().equals("Agregar")) {
            new crearAlbum(indexArtista);
        } else {
            new VentanaCanciones(indexArtista, indexAlbum);
        }
    }

    public void setArtMas() {
        this.indexAlbum++;
    }

    public void setArtMenos() {
        this.indexAlbum--;
    }
}
