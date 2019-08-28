package Mundo;

public class Partida {
	// atributos clase partida
	private final int PUNTINICIAL = 0;
	private int intento, filas, columnas, puntaje, numPartida, cantFichas;
	private Ficha lasFichas[][], elementosM[];

	// constructor clase partida
	public Partida(int filas, int columnas, int numPartida) {
		this.filas = filas;
		this.columnas = columnas;
		intento = 0;
		puntaje = PUNTINICIAL;
		this.numPartida = numPartida;
		cantFichas = 0;
		lasFichas = new Ficha[this.filas][this.columnas];
		// se crea un vector con la mitad de fichas que tenga la matriz
		elementosM = new Ficha[(this.filas * this.columnas) / 2];
	}

	// metodo reiniciar fichas
	public void reiniciarFichas() {
		/*
		 * Se crea un vector de elementos segun la MITAD de la cantidad de fichas que se
		 * requieran
		 * 
		 */
		int cont = 0;
		boolean no_repetido;

		// se crean cuatro for para comparar una posicion con todas
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				for (int j2 = 0; j2 < filas; j2++) {
					for (int k = 0; k < columnas; k++) {
						if (lasFichas[i][j] == lasFichas[j2][k]) {
							if (cont > elementosM.length) {
								break;
							} else {
								// se establece un boolean que nos va a manejar
								// las veces que se repite la ficha
								no_repetido = true;
								// se recorre el arreglo comparando sus
								// atributos con el de la matriz para saber si
								// se almacena o no, o sea si se repite
								for (int k2 = 0; k2 < cont; k2++) {
									if (lasFichas[j2][k].getNombre() == elementosM[k2].getNombre()) {
										no_repetido = false;
									}
								}
								// como no se repite se almacena y aunmenta el contador
								if (no_repetido == true) {
									elementosM[cont] = lasFichas[j2][k];
									cont++;
								}
							}
						}
					}
				}
			}
		}
		// se crea una variable de tipo entero que se le asignaran datos
		// aleatorios
		int aleatorio;
		// se crea un booleano inicializado en verdadero
		boolean bandera = true;
		/*
		 * Se crea un vector aux para almacenar la cantidad de veces que un numero
		 * aleatorio ha aparecido
		 */
		int[] aux = new int[elementosM.length];
		// se crean dos for para crear las posiciones de los elementos en la
		// matriz
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				/*
				 * se crea un do-while para repetir hasta que un numero este en menos de dos
				 * posiciones
				 */
				do {
					/*
					 * Usamos Math.random para obtener un numero entre 1 hasta la cantidad, se usa
					 * el +1 par decirle que sea desde 1
					 * 
					 */
					aleatorio = (int) ((Math.random() * elementosM.length) + 1);
					// se crea un swith para ver que numero se obtuvo en
					// aleatorio
					for (int j2 = 0; j2 < aux.length; j2++) {
						if (j2 + 1 == aleatorio) {
							aux[j2]++;
						}
					}
					// se verifica que el dato haya aprecido en mas de dos
					// ocasiones
					if (aux[aleatorio - 1] > 2) {
						// si aparece en mas de dos ocasiones se obtiene falso y
						// se repite el calculo
						bandera = false;
					} else {
						// si no se obtiene verdadero y se guarda la posicion
						bandera = true;
					}
				} while (!bandera);
				// se utiliza el numero para referenciar la posicion de la letra
				lasFichas[i][j] = elementosM[aleatorio - 1];
				// System.out.println(lasFichas[i][j].getDirImagen()+"
				// "+lasFichas[i][j].getNombre());
			}
		}
	}

	// metodo crear fichas
	public void crearFichas(String dirImagen, char nombre) {
		lasFichas[cantFichas / columnas][cantFichas % columnas] = new Ficha(dirImagen, nombre);
		cantFichas++;
	}

	public void subepuntaje() {
		puntaje += 20;
	}

	public void bajapuntaje() {
		puntaje -= 0;
	}

	public void intento() {
		puntaje -= 10;
	}

	// los getters and setters de la clase partida
	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	public int getNumPartida() {
		return numPartida;
	}

	public void setNumPartida(int numPartida) {
		this.numPartida = numPartida;
	}

	public int getIntento() {
		return intento;
	}

	public void setIntento(int intento) {
		this.intento = intento;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public Ficha getLasFichas(int i, int j) {
		return lasFichas[i][j];
	}

	public void setLasFichas(String dirImagen, char nombre, int i, int j) {
		lasFichas[i][j].setDirImagen(dirImagen);
		lasFichas[i][j].setNombre(nombre);
	}

	public int getPUNTINICIAL() {
		return PUNTINICIAL;
	}

}