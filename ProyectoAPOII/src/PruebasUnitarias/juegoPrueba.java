package PruebasUnitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.Juego;

@SuppressWarnings("unused")
class juegoPrueba {

	private Juego elJuego;

	// se carga antes de todos los test
	@BeforeEach
	void cargado() {
		/*
		 * creamos un metodo donde llamamos a la funcion cargar juego para iniciar a
		 * probar las funciones con las pruebas unitarias
		 */
		elJuego = new Juego();
		elJuego.cargarJuego(0);
	}

	@Test
	void testImprimirtodo() {
		/*
		 * este test lo que hace es ver que lo que devuelve el metodo imprimir todo no
		 * sea null
		 */
		assertNotNull("imprimio todo", elJuego.imprimirTodo());
	}

	// se declara el try-catch directamente
	@Test
	void testUbicacionDatos() throws IOException {
		/*
		 * este test lo que hara sera revisar si el archivo de entrada existe en la
		 * carpeta data de lo contrario se creara el archivo y con asserTrue confirmamos
		 * de que si existe
		 */
		File miFile = new File("./data/entrada.txt");

		if (!miFile.exists()) {
			miFile.createNewFile();
			fail("El directorio NO fue encontrado.");
		} else {
			assertTrue("El directorio NO fue encontrado.", true);
		}

	}

	@Test
	void testConstructores() {
		/*
		 * En este test probaremos todos los constructores verificando que estan
		 * funcionando correctamente al cargar los datos
		 */
		assertEquals("./imagenes/a.png", elJuego.getLapartida(0).getLasFichas(0, 0).getDirImagen());
	}

	@Test
	void testValidar() {
		/*
		 * En este test comenzamos a comparar la funcion validar , la que se encargara
		 * de retornar un true en el caso de que ambas fichas sean iguales
		 */
		assertTrue(elJuego.validar(0, 0, 0, 0, 1));
	}

	@Test
	void testReiniciar() {
		/*
		 * En este test comenzamos a comparar la funcion validar , la que se encargara
		 * de retornar un true en el caso de que ambas fichas sean iguales
		 */
		elJuego.getLapartida(0).reiniciarFichas();
		assertFalse(elJuego.validar(0, 0, 0, 0, 1));
	}

	@Test
	void testUbicacionReporte() throws IOException {
		/*
		 * En este test se revisara la ubicacion del archivo txt donde saldra el reporte
		 * y en el caso de que no este , se creara.
		 */

		File miFile = new File("./data/salida.txt");

		if (!miFile.exists()) {
			miFile.createNewFile();
			fail("El directorio NO fue encontrado.");
		} else {
			assertTrue("El directorio NO fue encontrado.", true);
		}
	}

	@Test
	void testNingunVacio() {
		// Verificamos que en la matriz de fichas despues de cargar las mismas
		// no se encuentre
		// ningun vacio
		for (int i = 0; i < elJuego.getLapartida(0).getFilas(); i++) {
			for (int j = 0; j < elJuego.getLapartida(0).getColumnas(); j++) {
				if (elJuego.getLapartida(0).getLasFichas(i, j).getDirImagen() == null) {
					fail("Posicion inexistente.");
				}
			}
		}
	}

	@Test
	void testFilasYColumnas() {
		// Verificamos que despues de cargar la matriz las filas y columnas sean
		// diferentes de 0 y no nulas
		assertNotEquals(0, elJuego.getLapartida(0).getFilas());
		assertNotEquals(0, elJuego.getLapartida(0).getColumnas());
		assertNotNull(elJuego.getLapartida(0).getFilas());
		assertNotNull(elJuego.getLapartida(0).getColumnas());
	}

	@Test
	void testContPartidad() {
		// se usa para verificar la cantidad de partidas inicial
		assertEquals(1, elJuego.getContpartidas(), "Error al contar la partida.");
	}

}
