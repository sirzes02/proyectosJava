package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Mundo.Tablero;;

class PruebaTablero {

	private Tablero miTablero;

	@BeforeEach
	public void configuracion() {
		miTablero = new Tablero("./data/testLectura.txt");
	}

	@Test
	void testLectura() {
		assertEquals("Desarrollo de interfaz", miTablero.getMiTarea(0).getNombre(), "Error en la lectura de datos");
	}

	@Test
	void testLecturaCreacion() {
		File miArchivo = new File("./data/lectura.txt");

		if (!miArchivo.exists()) {
			fail("No encontrado el archivo");
		}
	}

	@Test
	void testEscritura() {
		miTablero.reporte();

		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/escritura.txt"));
			assertEquals("La cantidad de tareas pentientes es: 2.", br.readLine());
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testEscrituraCreacion() throws IOException {
		File miArchivo = new File("./data/Escritura.txt");

		if (!miArchivo.exists()) {
			miArchivo.createNewFile();
			fail("No encontrado el archivo, pero creado.");
		}

	}

	@Test
	void testModificar() {
		miTablero.modificarTarea("Desarrollo de interfaz", "Desarrollo de paquete interfaz", "Por hacer", "Normal");

		assertEquals("Desarrollo de paquete interfaz", miTablero.getMiTarea(0).getNombre(),
				"Error al modificar los valores.");
	}

	@Test
	void testEliminado() {
		miTablero.deleteMiTarea("Desarrollo de interfaz");

		for (int i = 0; i < miTablero.getCantTareas(); i++) {
			if (miTablero.getMiTarea(i) != null) {
				if (miTablero.getMiTarea(i).getNombre().equals("Desarrollo de interfaz")) {
					fail("Error al eliminar,");
				}
			}
		}
	}

}
