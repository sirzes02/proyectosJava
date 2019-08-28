package Mundo;

/**
 * @author Catalina y Giovanny
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Juego {
	// atributos de la clase juego
	private Partida lapartida[];
	private final String RUTAS[] = { "./data/entrada.txt", "./data/entrada2.txt", "./data/entrada3.txt" };
	private final String RUTAREPORTE = "./data/salida.txt";
	private final int CANTPARTIDAS = 5;
	private int contpartidas, totalPartidas;

	// constructor del juego
	public Juego() {
		// relacion de la partida con el juego
		lapartida = new Partida[CANTPARTIDAS];
		contpartidas = 0;
	}

	// se elimina una partida y se almacena una nueva
	public void eliminacion(int filas, int columnas, int totalPartidas) {
		// se crea un for hasta CANTPARTIDAS
		for (int i = 0; i < CANTPARTIDAS - 1; i++) {
			// se reemplaza la partida en la posicion i por i+1
			lapartida[i] = lapartida[i + 1];
		}
		// se reemplaza la ultima posicion de la partida por una nueva partida
		lapartida[CANTPARTIDAS - 1] = new Partida(filas, columnas, totalPartidas);
	}

	// se crea el metodo cargar partida
	public void cargarJuego(int indice) {
		try {
			// se lee el arreglo de rutas dependiendo el indice que mande
			BufferedReader br = new BufferedReader(new FileReader(RUTAS[indice]));
			String partes[] = br.readLine().split(",");
			// ya teniendo la posicon de las comas se guarda filas y columnas
			int filas = Integer.parseInt(partes[0]);
			int columnas = Integer.parseInt(partes[1]);
			// se maneja un contador de partidas en el que si llega a ser igual
			// al CANTPARTIDAS el arreglo se lleno
			if (contpartidas == CANTPARTIDAS) {
				// por lo tanto se llama el metodo de eliminacion y se llena una
				// partida, asi se asegura obtener las ultimas 5 partidas
				eliminacion(filas, columnas, totalPartidas);
			} else {
				// sino es porque el contador no esta lleno y se pueden seguir
				// creando partidas en el vector
				lapartida[contpartidas] = new Partida(filas, columnas, totalPartidas);
				contpartidas++;
			}
			// se va contando el total de partidas que lleva
			totalPartidas++;

			// se lee la cantidad de fichas que haya
			int cantFichas = Integer.parseInt(br.readLine());
			// se lee las direcciones y nombres, y se envian directamente
			for (int i = 0; i < cantFichas; i++) {
				lapartida[contpartidas - 1].crearFichas(br.readLine(), br.readLine().charAt(0));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("ARCHIVO NO SE ESTA ENCONTRANDO EN LA RUTA PREDETERMINADA\n");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("SE GENERO UNA EXEPCION");
		}
	}

	// Se crea el metodo imprimir
	public String imprimirTodo() {
		// se declara una variable donde guarda todo
		String guardarTodo = "";
		try {
			// se lee ruta reporte y se concatena todo en guardartodo para
			// retornarlo
			BufferedReader br = new BufferedReader(new FileReader(RUTAREPORTE));
			// se crea un for para leer cada linea y concadenar todo en la
			// variable guardar todo
			for (int i = 0; i < contpartidas; i++) {
				guardarTodo = guardarTodo.concat(br.readLine() + "\n");
			}
			// se halla el puntaje mayor
			int puntajeM = 0;
			int poss = 0;

			for (int i = 0; i < contpartidas; i++) {
				if (puntajeM < lapartida[i].getPuntaje()) {
					puntajeM = lapartida[i].getPuntaje();
					poss = i;
				}
			}
			guardarTodo = guardarTodo.concat("El mayor puntaje fue el de la partida "
					+ (lapartida[poss].getNumPartida() + 1) + " con un puntaje de : " + lapartida[poss].getPuntaje());
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("ARCHIVO NO SE ESTA ENCONTRANDO EN LA RUTA PREDETERMINADA\n");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("SE GENERO UNA EXEPCION");
		}
		return guardarTodo;
	}

	// metodo empezar juego carga un tablero
	public void empezarJuego(int i) {
		cargarJuego(i);
	}

	// metodo reiniciar partidas, llama el metodo reiniciar fichas de la partida
	public void reiniciarPartidas(int i) {
		lapartida[i].reiniciarFichas();
	}

	// se crea el metodo reporte
	public void generarReporte() {
		// se estructura un try-catch
		try {
			// se rea un File con la ruta donde deberia estar el archivo de
			// salida
			File elFile = new File(RUTAREPORTE);
			/*
			 * se verifica si existe el archivo y sino existe se crea, y la funcion se llama
			 * asi misma
			 */
			if (!elFile.exists()) {
				elFile.createNewFile();
				generarReporte();
			} else {
				// y si existe se genera el reporte , se crea un PrintWriter con
				// el archivo
				PrintWriter pw = new PrintWriter(elFile);
				// se crea un for para recorrer las partidas
				for (int i = 0; i < contpartidas; i++) {
					pw.println("Para la partida numero " + (lapartida[i].getNumPartida() + 1) + " el puntaje es : "
							+ lapartida[i].getPuntaje());
				}
				// se halla el mayor puntaje
				int puntajeM = 0;
				int poss = 0;
				for (int i = 0; i < contpartidas; i++) {
					if (puntajeM < lapartida[i].getPuntaje()) {
						puntajeM = lapartida[i].getPuntaje();
						poss = i;
					}
				}
				pw.println("El mayor puntaje fue el de la partida " + (lapartida[poss].getNumPartida() + 1)
						+ " con un puntaje de : " + lapartida[poss].getPuntaje());

				pw.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("ARCHIVO NO SE ESTA ENCONTRANDO EN LA RUTA PREDETERMINADA\n");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("SE GENERO UNA EXEPCION");
		}
	}

	// metodo validar, le llegan dos posiciones de una matriz y compara si son
	// iguales o no
	public boolean validar(int i, int i1, int j1, int i2, int j2) {
		boolean soniguales = false;

		if (lapartida[i].getLasFichas(i1, j1).getNombre() == lapartida[i].getLasFichas(i2, j2).getNombre()) {
			lapartida[i].subepuntaje();
			// lo que hace esta funcion es cuando las posiciones esten iguales sube el
			// puntaje de la partida
			return soniguales = true;

		} else {
			// hace lo contrario
			if (lapartida[i].getPuntaje() > 0) {
				// se le restan 10 si se equivoca
				lapartida[i].intento();
			} else {
				lapartida[i].bajapuntaje();
			}
			return soniguales;

		}
	}

	// son los getters and setters de la clase juego.
	public Partida getLapartida(int i) {
		return lapartida[i];
	}

	public void setLapartida(int i, int filas, int columnas, int numPartida) {
		lapartida[i].setColumnas(columnas);
		lapartida[i].setFilas(filas);
		lapartida[i].setNumPartida(numPartida);
	}

	public int getContpartidas() {
		return contpartidas;
	}

	public void setContpartidas(int contpartidas) {
		this.contpartidas = contpartidas;
	}

	public int getCANTPARTIDAS() {
		return CANTPARTIDAS;
	}

	public int getTotalPartidas() {
		return totalPartidas;
	}

	public void setTotalPartidas(int totalPartidas) {
		this.totalPartidas = totalPartidas;
	}

}
