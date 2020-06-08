package Interface;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sur extends JPanel implements ActionListener {

    private short art;

    public Sur(short art) {
        setLayout(new BorderLayout());
        JButton ingresar = new JButton("Agregar");
        ingresar.addActionListener(this);
        add(ingresar, BorderLayout.EAST);

        JButton verEditar = new JButton("Ver / Editar");
        verEditar.addActionListener(this);
        add(verEditar, BorderLayout.WEST);

        this.art = art;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()).getActionCommand().equals("Agregar")) {
            new crearArtista();
        } else {
            new VentanaAlbumes(art);
        }
    }

    public void setArtMas() {
        this.art++;
    }

    public void setArtMenos() {
        this.art--;
    }
}
