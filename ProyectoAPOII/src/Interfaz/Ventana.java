package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import Mundo.Juego;

@SuppressWarnings("serial")
public class Ventana extends JFrame {
	private Juego elJuego;
	private JPanel panelPrincipal, panelMenuAbajo, panelMenuCentro, panelDerecha, panelCentro, panelArriba, panelAbajo;
	private Etiqueta etq4;
	// matriz de botones
	private Boton fichitas[][], mostrar[][], volver;
	private int aux = 0, cont = 0, enviari = 0, enviarj = 0, posj = 0, posi = 0, contador;
	private boolean voltear = false;

	private final String RUTA = "./imagenes/signo.gif";

	public Ventana() {
		this.setTitle("Concentrese");// titulo de la ventana
		this.setResizable(true);// para que no se pueda cambiar de tamanio
		this.setSize(600, 600); // tamanio de la pantalla
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);// se cierre la ventana
		// se carga el icono de ventana y se configura el valor
		this.setIconImage(new ImageIcon("./imagenes/icono.png").getImage());
		colocarComponentes();// Colocamos todos los componentes de la ventana

		// Actiolistener para las acciones de las ventanas
		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// vuelve el frame invisible, pero no se cierra
				setVisible(false);
				JOptionPane.showMessageDialog(null, "Gracias por jugar.", "Fin", JOptionPane.DEFAULT_OPTION);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void cargarMundo() {
		// instanciamos un objeto de la clase juego y llamamos a la funcion cargar
		elJuego = new Juego();
		// se inicializa el contador para mostrar el aviso de finalizacion
		contador = 0;
		elJuego.empezarJuego(aux);
		elJuego.reiniciarPartidas(elJuego.getContpartidas() - 1);

	}

	private void otrojuego() {
		// inicia una nueva partida
		contador = 0;
		matrizBotones();
		elJuego.empezarJuego(aux);
		elJuego.reiniciarPartidas(elJuego.getContpartidas() - 1);
		etq4.setText("" + elJuego.getLapartida(elJuego.getContpartidas() - 1).getPUNTINICIAL());
		cont = 0;
		enviari = 0;
		enviarj = 0;
	}

	@SuppressWarnings("unchecked")
	private void colocarComponentes() {
		// Panel principal
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.WHITE);
		// panel de abajo
		panelMenuAbajo = new JPanel();
		panelMenuAbajo.setBackground(Color.WHITE);
		// panel del centro
		panelMenuCentro = new JPanel();
		panelMenuCentro.setBackground(Color.WHITE);

		// Etiqueta del menu para la imagen tablero
		Etiqueta tableroMenu = new Etiqueta("./imagenes/tablero.png");
		panelMenuCentro.add(tableroMenu);

		// Etiqueta del titulo bienvenido
		// String[] niveles = { "Facil", "Medio", "Dificil" };
		Etiqueta tituloMenu = new Etiqueta("./imagenes/bienvenidos.gif");
		tituloMenu.setAlignmentX(CENTER_ALIGNMENT);
		panelPrincipal.add(tituloMenu, BorderLayout.NORTH);

		@SuppressWarnings("rawtypes")
		JComboBox listaDesplegable = new JComboBox();
		listaDesplegable.setFont(new Font("Nyala", Font.ITALIC, 30));
		listaDesplegable.addItem("Facil");
		listaDesplegable.addItem("Medio");
		listaDesplegable.addItem("Dificil");
		listaDesplegable.setSelectedItem("Medio");
		panelMenuAbajo.add(listaDesplegable, BorderLayout.SOUTH);

		// se anade el action listenes
		listaDesplegable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String itemSelecionado = (String) listaDesplegable.getSelectedItem();
				/*
				 * Para extraer el contenido del item seleccionado llamamos al método
				 * getSelectemItem() el cual retorna un objeto y lo volvemos String
				 */
				if ("Dificil".equals(itemSelecionado)) {
					// se selecciona el item
					panelPrincipal.setVisible(false);
					panelMenuAbajo.setVisible(false);
					panelMenuCentro.setVisible(false);
					aux = 2;
					// se llaman los metodos
					cargarMundo();
					colocarPaneles();
					colocarEtiquetas();
					colocarBotones();

				} else if ("Medio".equals(itemSelecionado)) {
					panelPrincipal.setVisible(false);
					panelMenuAbajo.setVisible(false);
					panelMenuCentro.setVisible(false);
					aux = 1;
					cargarMundo();
					colocarPaneles();
					colocarEtiquetas();
					colocarBotones();

				} else if ("Facil".equals(itemSelecionado)) {
					panelPrincipal.setVisible(false);
					panelMenuAbajo.setVisible(false);
					panelMenuCentro.setVisible(false);
					aux = 0;
					cargarMundo();
					colocarPaneles();
					colocarEtiquetas();
					colocarBotones();

				}

			}

		});
		// se anadeem los paneles
		this.add(panelMenuAbajo, BorderLayout.SOUTH);
		this.add(panelPrincipal, BorderLayout.NORTH);
		this.add(panelMenuCentro, BorderLayout.CENTER);

	}

	private void colocarEtiquetas() {
		/*
		 * Creamos una etiqueta de la clase Etiqueta y anadimos las caracteristicas que
		 * queremos a gusto , asi con todas!!
		 */
		Etiqueta etq1 = new Etiqueta("Encuentra los pares de los profesores", 28);
		etq1.setBackground(panelArriba.getBackground());
		etq1.setFont(new Font("Nyala", Font.BOLD, 28));
		etq1.setForeground(Color.BLACK);
		panelArriba.add(etq1);// se añade al panel de arriba

		Etiqueta etq2 = new Etiqueta("./imagenes/usc.jpg");
		// se desactivo el diseno predeterminado
		etq2.setHorizontalAlignment(SwingConstants.CENTER);
		panelDerecha.add(etq2);// se anade al panel de la derecha

		Etiqueta etq3 = new Etiqueta("Puntaje :", 40);
		etq3.setHorizontalAlignment(SwingConstants.CENTER);
		etq3.setFont(new Font("Nyala", Font.BOLD, 40));
		etq3.setForeground(Color.BLACK);
		etq3.setBackground(panelDerecha.getBackground());
		panelDerecha.add(etq3);// se anade al panel de la derecha
		/*
		 * Esta es la etiqueta que nos mostrara el puntaje que se lleva en cada partida
		 * por lo tanto con el objeto de clase juego , se pide la informacion del
		 * puntaje
		 */
		etq4 = new Etiqueta("" + elJuego.getLapartida(elJuego.getContpartidas() - 1).getPuntaje(), 80);
		etq4.setHorizontalAlignment(SwingConstants.CENTER);
		etq4.setForeground(Color.BLACK);
		etq4.setBackground(panelDerecha.getBackground());
		panelDerecha.add(etq4);// anade la etiqueta

	}

	// Metodo para que los botones creados tenga la imagen "?"
	private void reiniciar(int i, int j) {
		fichitas[i][j].setIcon(new ImageIcon(RUTA));

	}

	private void matrizBotones() {
		// Aqui es donde cobra vida los botones de "?" se crea una matriz
		// de botones y se le anade en cada posicion un boton
		fichitas = new Boton[elJuego.getLapartida(elJuego.getContpartidas() - 1).getFilas()][elJuego
				.getLapartida(elJuego.getContpartidas() - 1).getColumnas()];

		for (int i = 0; i < elJuego.getLapartida(elJuego.getContpartidas() - 1).getFilas(); i++) {
			for (int j = 0; j < elJuego.getLapartida(elJuego.getContpartidas() - 1).getColumnas(); j++) {
				fichitas[i][j] = mostrar[i][j];
				reiniciar(i, j);
			}
		}

	}

	private void colocarBotones() {
		/*
		 * Aqui es donde creamos los botones y todos los metemos en un panel con sus
		 * respectivas caracteristicas
		 */
		mostrar = new Boton[elJuego.getLapartida(elJuego.getContpartidas() - 1).getFilas()][elJuego
				.getLapartida(elJuego.getContpartidas() - 1).getColumnas()];

		// se crea los fors para recorrer la matriz
		for (int i = 0; i < elJuego.getLapartida(elJuego.getContpartidas() - 1).getFilas(); i++) {
			for (int j = 0; j < elJuego.getLapartida(elJuego.getContpartidas() - 1).getColumnas(); j++) {

				mostrar[i][j] = new Boton(RUTA);
				mostrar[i][j].setBackground(panelArriba.getBackground());
				mostrar[i][j].setBorder(BorderFactory.createLineBorder(panelArriba.getBackground()));
				// se anade la matriz mostrar en su respectivo panel
				panelCentro.add(mostrar[i][j]);

			}

		}

		// se crea el boton de reinicio de juego o sea una nueva partida
		Boton reiniciar = new Boton("Reiniciar");
		reiniciar.setBackground(Color.decode("#D3EBF5"));
		reiniciar.setForeground(Color.BLACK);
		reiniciar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panelDerecha.add(reiniciar);
		botonreinicio(reiniciar);// acccion del oyente
		/*
		 * boton para generar reporte estos dos botones van en otros paneles
		 * independientes que al de las imagenes
		 */

		Boton generarReporte = new Boton("Reporte");
		generarReporte.setBackground(Color.decode("#E4D3F5"));
		generarReporte.setForeground(Color.BLACK);
		generarReporte.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panelDerecha.add(generarReporte);
		botonGenerarReporte(generarReporte);
		/*
		 * llamamos a la funcion vida y le mandamos por parametro todos los botones
		 */

		volver = new Boton("./imagenes/volver.png.png");
		volver.setBackground(Color.decode("#E4D3F5"));
		volver.setBorder(BorderFactory.createLineBorder(Color.decode("#E4D3F5")));
		panelAbajo.add(volver);
		panelAbajo.add(new JLabel());
		panelAbajo.add(new JLabel());

		// se anade action listener
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// el dispose es para cerrar el frame
				dispose();
				//y este es para rellama y que se rehaga todo
				main(null);
			}
		});
		matrizBotones();
		eventoOyentedeRaton();

	}

	private void eventoOyentedeRaton() {
		// cobra vida el juego se instancia un oyente de mouse
		MouseListener oyentedeMouse = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// se hacen fors para recorrer todos lo botones
				for (int i = 0; i < elJuego.getLapartida(elJuego.getContpartidas() - 1).getFilas(); i++) {
					for (int j = 0; j < elJuego.getLapartida(elJuego.getContpartidas() - 1).getColumnas(); j++) {
						/*
						 * se hace una comparacion.Si donde se presiono es igual a cualquiera de las
						 * fichas
						 */
						if (e.getComponent() == fichitas[i][j]) {
							// se crea un contador para saber cuantas veces se entra en esta esta condicion
							cont++;
							/*
							 * si se cumple la condicion hacemos que se cambie la imagen por la que
							 * alcameneamos desde el mundo
							 */
							fichitas[i][j].setIcon(new ImageIcon(elJuego.getLapartida(elJuego.getContpartidas() - 1)
									.getLasFichas(i, j).getDirImagen()));
							/*
							 * Gracias al contador decimos que cada vez que contador sea par o se haya echo
							 * click en dos imagenes haremos una comparacion
							 */
							if (cont == 3) {
								/*
								 * contador se inicia desde 1 para que al dar click a la 3 imagen se vuelvan a
								 * reiniciar
								 */
								cont = 1;
								if (voltear) {
									reiniciar(posi, posj);
									reiniciar(enviari, enviarj);
								}
							}
							if (cont % 2 == 0) {
								/*
								 * llamamos al metodo validar de clase juego , donde le enviamos la primera
								 * imagen donde se dio click y la segunda
								 */
								if (enviari != i || enviarj != j) {
									if (!elJuego.validar(elJuego.getContpartidas() - 1, enviari, enviarj, i, j)) {
										/*
										 * si nos retorna un false es por que no son iguales , por lo tanto llamamos al
										 * metodo reiniciar que lo que hara es volver a editar iconImage
										 */
										voltear = true;
										posi = i;
										posj = j;
										/*
										 * como la persona se equivoco , llamamos al metodo del mundo para que nos diga
										 * el nuevo puntaje y modificamos la etiqueta
										 */
										etq4.setText(
												"" + elJuego.getLapartida(elJuego.getContpartidas() - 1).getPuntaje());
									} else {
										/*
										 * si no es false , es porque ambas imagenes son iguales por lo tanto lo que
										 * haremos sera que las posiciones donde se hizo la comparacion de la imagenes
										 * las dejaremos como null por que null? para que no se puedan seguir comparando
										 * 
										 */
										voltear = false;
										fichitas[i][j] = null;
										fichitas[enviari][enviarj] = null;
										/*
										 * como la persona acerto , llamamos al metodo del mundo para que nos diga el
										 * nuevo puntaje y modificamos la etiqueta
										 */
										etq4.setText(
												" " + elJuego.getLapartida(elJuego.getContpartidas() - 1).getPuntaje());
										// se aumenta el contador por cada acierto
										contador++;
									}
								} else {
									reiniciar(i, j);
								}
							} else {
								/*
								 * si no se cumplio la condicion de que el contador sea par es por que es impar
								 * por lo tanto solo le ha dado click a una imagen guardamos la posicion de la
								 * imagen que nos ayudara para comparar
								 */
								enviari = i;
								enviarj = j;
							}
						}
					}
				}
				/*
				 * se crea un if donde la condicion contador igual a la cantidad de imagenes, se
				 * utiliza un calculo matematico para obtener la cantidad de imagenes puestas y
				 * a su vez obtener la cantidad de veces que se tiene que cumplir
				 */
				if (contador == (elJuego.getLapartida(elJuego.getContpartidas() - 1).getFilas()
						* elJuego.getLapartida(elJuego.getContpartidas() - 1).getColumnas()) / 2) {
					// se muestra un mensaje cuando finaliza una partida
					JOptionPane.showMessageDialog(null, "Partida terminada.", "Aviso", JOptionPane.DEFAULT_OPTION);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		};
		for (int i = 0; i < elJuego.getLapartida(elJuego.getContpartidas() - 1).getFilas(); i++) {
			for (int j = 0; j < elJuego.getLapartida(elJuego.getContpartidas() - 1).getColumnas(); j++) {
				// aqui se recorre la matriz de botones y le anadimos a todos el
				// oyente de mouse
				fichitas[i][j].addMouseListener(oyentedeMouse);
			}
		}
	}

	private void botonreinicio(JButton reinicio) {

		reinicio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				otrojuego();
			}
		});// Metodo que anade un oyente de accion

	}

	// aqui se hace funcionar el boton
	private void botonGenerarReporte(JButton reporte2) {

		reporte2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// se llama el metodo generar reporte
				elJuego.generarReporte();
				JOptionPane.showMessageDialog(null, elJuego.imprimirTodo());

			}

		});
	}

	private void colocarPaneles() {
		// paneles principales, se establecen los paneles en algunos se les desactiva el
		// layout para poder modificar su tamano y posicion

		panelCentro = new JPanel();// el panel del centro
		panelCentro.setBackground(Color.decode("#E4D3F5"));
		panelCentro.setLayout(new GridLayout(elJuego.getLapartida(elJuego.getContpartidas() - 1).getFilas(),
				elJuego.getLapartida(elJuego.getContpartidas() - 1).getColumnas()));

		panelArriba = new JPanel();
		panelArriba.setBackground(Color.decode("#E4D3F5"));

		panelAbajo = new JPanel();
		panelAbajo.setBackground(Color.decode("#E4D3F5"));
		panelAbajo.setLayout(new GridLayout(1, 3));

		panelDerecha = new JPanel();
		panelDerecha.setBackground(Color.decode("#E4D3F5"));
		panelDerecha.setLayout(new GridLayout(5, 1, 0, 10));
		panelDerecha.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));

		// flow layout seguidos barra de herramienta
		// grid layout filas y columnas

		this.add(panelAbajo, BorderLayout.SOUTH);
		this.add(panelCentro, BorderLayout.CENTER);
		this.add(panelArriba, BorderLayout.NORTH);
		this.add(panelDerecha, BorderLayout.EAST);

		// panel2.setBounds(0,0);

	}

	public static void main(String[] args) {
		// ventana
		Ventana ventana = new Ventana();
		ventana.setVisible(true);
		ventana.setLocationRelativeTo(null);

	}

}
