package Mundo;

import java.io.*;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Tablero {

	// Atributos
	private final short FILAS = 5;
	private final short COLUMNAS = 5;
	private final String RUTALECTURA = "./data/lectura.txt";
	private final String RUTAESCRITURA = "./data/escritura.txt";
	private final String DELIMITANTE = "|";
	private short cantTareas;
	private Tarea miTarea[][];

	// Constructor de la clase
	public Tablero(String ruta) {
		cantTareas = 0;
		miTarea = new Tarea[FILAS][COLUMNAS];
		lecturaDatos(ruta);
	}

	public short getCantTareas() {
		return cantTareas;
	}

	public Tarea getMiTarea(int num) {
		return miTarea[num / getCOLUMNAS()][num % getCOLUMNAS()];
	}

	// Crear la tarea
	public void setMiTarea(String nombre, String estado, String prioridad) {
		boolean bandera = true;
		boolean noExiste = true;

		// Se verifica qu ele nombre de la tarea no exista
		for (int i = 0; i < getCantTareas(); i++) {
			if (getMiTarea(i).getNombre().equalsIgnoreCase(nombre)) {
				noExiste = false;
			}
		}

		if (noExiste) {
			if (cantTareas < getFILAS() * getCOLUMNAS()) {
				for (int i = 0; i < getCantTareas(); i++) {
					if (getMiTarea(i) == null) {
						miTarea[i / getCOLUMNAS()][i % getCOLUMNAS()] = new Tarea(nombre, estado, prioridad);
						bandera = false;
						break;
					}
				}
				if (bandera) {
					miTarea[cantTareas / getCOLUMNAS()][cantTareas % getCOLUMNAS()] = new Tarea(nombre, estado,
							prioridad);
					cantTareas++;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, matriz llena.", "Error", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Error, Este nombre ya existe.", "Error", JOptionPane.WARNING_MESSAGE);
		}

		escrituraDatos();

	}

	// Se elemina la tarea por su nombe
	public void deleteMiTarea(String nombre) {
		for (int i = 0; i < getCantTareas(); i++) {
			if (getMiTarea(i).getNombre().equalsIgnoreCase(nombre)) {
				miTarea[i / getCOLUMNAS()][i % getCOLUMNAS()] = null;
				break;
			}
		}
		escrituraDatos();
	}

	// Modificar la tarea
	public void modificarTarea(String nombreViejo, String nombreNuevo, String estado, String prioridad) {
		for (int i = 0; i < getCantTareas(); i++) {
			if (getMiTarea(i).getNombre().equalsIgnoreCase(nombreViejo)) {
				getMiTarea(i).setNombre(nombreNuevo);
				getMiTarea(i).setEstado(estado);
				getMiTarea(i).setPrioridad(prioridad);
				break;
			}
		}
		escrituraDatos();
	}

	public short getFILAS() {
		return FILAS;
	}

	public short getCOLUMNAS() {
		return COLUMNAS;
	}

	// Lectura de persistencia
	public void lecturaDatos(String ruta) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(ruta));
			StringTokenizer st;

			byte cantTareas = Byte.parseByte(br.readLine());

			for (int i = 0; i < cantTareas; i++) {
				st = new StringTokenizer(br.readLine(), DELIMITANTE);
				setMiTarea(st.nextToken(), st.nextToken(), st.nextToken());
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Se escribe lo mismo que se lee, el mismo formato
	public void escrituraDatos() {
		try {
			File miArchivo = new File(RUTALECTURA);

			do {
				if (!miArchivo.exists()) {
					miArchivo.createNewFile();
					escrituraDatos();
				}
			} while (!miArchivo.exists());

			PrintWriter pw = new PrintWriter(miArchivo);

			short cont = 0;
			for (int i = 0; i < getCantTareas(); i++) {
				if (getMiTarea(i) == null) {
					cont++;
				}
			}

			pw.println(getCantTareas() - cont);

			for (int i = 0; i < getCantTareas(); i++) {
				if (getMiTarea(i) != null) {
					pw.println(getMiTarea(i).getNombre() + DELIMITANTE + getMiTarea(i).getEstado() + DELIMITANTE
							+ getMiTarea(i).getPrioridad());
				}
			}

			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		reporte();
	}

	public void reporte() {
		try {
			File miArchivo = new File(RUTAESCRITURA);

			do {
				if (!miArchivo.exists()) {
					miArchivo.createNewFile();
					escrituraDatos();
				}
			} while (!miArchivo.exists());

			PrintWriter pw = new PrintWriter(miArchivo);

			short contPorHacer = 0, contEnCurso = 0, contFinalizada = 0;

			for (int i = 0; i < getCantTareas(); i++) {
				if (getMiTarea(i) != null) {
					if (getMiTarea(i).getEstado().equals("Por hacer")) {
						contPorHacer++;
					} else if (getMiTarea(i).getEstado().equals("En curso")) {
						contEnCurso++;
					} else {
						contFinalizada++;
					}
				}
			}

			pw.println("La cantidad de tareas pentientes es: " + contPorHacer + ".");
			pw.println("La cantidad de tareas en curso es: " + contEnCurso + ".");
			pw.print("La cantidad de tareas finalizadas es: " + contFinalizada + ".");

			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[] nombresTareas(String num) {
		int cant = 0;
		String[] nombres;

		switch (Integer.parseInt(num)) {
		case 1:
			for (int i = 0; i < getCantTareas(); i++) {
				if (getMiTarea(i).getEstado().equalsIgnoreCase("Por hacer")
						&& getMiTarea(i).getPrioridad().equalsIgnoreCase("urgente")) {
					cant++;
				}
			}

			if (cant > 0) {
				nombres = new String[cant];

				for (int i = 0, cont = 0; i < getCantTareas(); i++) {
					if (getMiTarea(i).getEstado().equalsIgnoreCase("Por hacer")
							&& getMiTarea(i).getPrioridad().equalsIgnoreCase("urgente")) {
						nombres[cont] = getMiTarea(i).getNombre();
						cont++;
					}
				}
				return nombres;
			}
			break;
		case 2:
			for (int i = 0; i < getCantTareas(); i++) {
				if (getMiTarea(i).getEstado().equalsIgnoreCase("En curso")
						&& getMiTarea(i).getPrioridad().equalsIgnoreCase("urgente")) {
					cant++;
				}
			}

			if (cant > 0) {
				nombres = new String[cant];

				for (int i = 0, cont = 0; i < getCantTareas(); i++) {
					if (getMiTarea(i).getEstado().equalsIgnoreCase("En curso")
							&& getMiTarea(i).getPrioridad().equalsIgnoreCase("urgente")) {
						nombres[cont] = getMiTarea(i).getNombre();
						cont++;
					}
				}
				return nombres;
			}
			break;
		case 3:
			for (int i = 0; i < getCantTareas(); i++) {
				if (getMiTarea(i).getEstado().equalsIgnoreCase("Finalizada")
						&& getMiTarea(i).getPrioridad().equalsIgnoreCase("urgente")) {
					cant++;
				}
			}

			if (cant > 0) {
				nombres = new String[cant];

				for (int i = 0, cont = 0; i < getCantTareas(); i++) {
					if (getMiTarea(i).getEstado().equalsIgnoreCase("Finalizada")
							&& getMiTarea(i).getPrioridad().equalsIgnoreCase("urgente")) {
						nombres[cont] = getMiTarea(i).getNombre();
						cont++;
					}
				}
				return nombres;
			}
			break;
		case 4:
			for (int i = 0; i < getCantTareas(); i++) {
				if (getMiTarea(i).getEstado().equalsIgnoreCase("Por hacer")
						&& getMiTarea(i).getPrioridad().equalsIgnoreCase("Importante")) {
					cant++;
				}
			}

			if (cant > 0) {
				nombres = new String[cant];

				for (int i = 0, cont = 0; i < getCantTareas(); i++) {
					if (getMiTarea(i).getEstado().equalsIgnoreCase("Por hacer")
							&& getMiTarea(i).getPrioridad().equalsIgnoreCase("Importante")) {
						nombres[cont] = getMiTarea(i).getNombre();
						cont++;
					}
				}
				return nombres;
			}
			break;
		case 5:
			for (int i = 0; i < getCantTareas(); i++) {
				if (getMiTarea(i).getEstado().equalsIgnoreCase("En curso")
						&& getMiTarea(i).getPrioridad().equalsIgnoreCase("Importante")) {
					cant++;
				}
			}

			if (cant > 0) {
				nombres = new String[cant];

				for (int i = 0, cont = 0; i < getCantTareas(); i++) {
					if (getMiTarea(i).getEstado().equalsIgnoreCase("En curso")
							&& getMiTarea(i).getPrioridad().equalsIgnoreCase("Importante")) {
						nombres[cont] = getMiTarea(i).getNombre();
						cont++;
					}
				}
				return nombres;
			}
			break;
		case 6:
			for (int i = 0; i < getCantTareas(); i++) {
				if (getMiTarea(i).getEstado().equalsIgnoreCase("Finalizada")
						&& getMiTarea(i).getPrioridad().equalsIgnoreCase("Importante")) {
					cant++;
				}
			}

			if (cant > 0) {
				nombres = new String[cant];

				for (int i = 0, cont = 0; i < getCantTareas(); i++) {
					if (getMiTarea(i).getEstado().equalsIgnoreCase("Finalizada")
							&& getMiTarea(i).getPrioridad().equalsIgnoreCase("Importante")) {
						nombres[cont] = getMiTarea(i).getNombre();
						cont++;
					}
				}
				return nombres;
			}
			break;
		case 7:
			for (int i = 0; i < getCantTareas(); i++) {
				if (getMiTarea(i).getEstado().equalsIgnoreCase("Por hacer")
						&& getMiTarea(i).getPrioridad().equalsIgnoreCase("Nomal")) {
					cant++;
				}
			}

			if (cant > 0) {
				nombres = new String[cant];

				for (int i = 0, cont = 0; i < getCantTareas(); i++) {
					if (getMiTarea(i).getEstado().equalsIgnoreCase("Por hacer")
							&& getMiTarea(i).getPrioridad().equalsIgnoreCase("Normal")) {
						nombres[cont] = getMiTarea(i).getNombre();
						cont++;
					}
				}
				return nombres;
			}
			break;
		case 8:
			for (int i = 0; i < getCantTareas(); i++) {
				if (getMiTarea(i).getEstado().equalsIgnoreCase("En curso")
						&& getMiTarea(i).getPrioridad().equalsIgnoreCase("Nomal")) {
					cant++;
				}
			}

			if (cant > 0) {
				nombres = new String[cant];

				for (int i = 0, cont = 0; i < getCantTareas(); i++) {
					if (getMiTarea(i).getEstado().equalsIgnoreCase("En curso")
							&& getMiTarea(i).getPrioridad().equalsIgnoreCase("Normal")) {
						nombres[cont] = getMiTarea(i).getNombre();
						cont++;
					}
				}
				return nombres;
			}
			break;
		case 9:
			for (int i = 0; i < getCantTareas(); i++) {
				if (getMiTarea(i).getEstado().equalsIgnoreCase("Finalizada")
						&& getMiTarea(i).getPrioridad().equalsIgnoreCase("Normal")) {
					cant++;
				}
			}

			if (cant > 0) {
				nombres = new String[cant];

				for (int i = 0, cont = 0; i < getCantTareas(); i++) {
					if (getMiTarea(i).getEstado().equalsIgnoreCase("Finalizada")
							&& getMiTarea(i).getPrioridad().equalsIgnoreCase("Normal")) {
						nombres[cont] = getMiTarea(i).getNombre();
						cont++;
					}
				}
				return nombres;
			}
			break;
		}
		return null;
	}
}