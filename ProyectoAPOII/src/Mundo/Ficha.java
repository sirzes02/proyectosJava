package Mundo;

public class Ficha {
	//atributos de la clase ficha
	private String dirImagen;
	private char nombre;
	
	//constructor de la clase ficha
	public Ficha(String dirImagen, char nombre) {
		this.dirImagen = dirImagen;
		this.nombre = nombre;
	}

	//son los getters and setters de la clase ficha
	public String getDirImagen() {
		return dirImagen;
	}

	public void setDirImagen(String dirImagen) {
		this.dirImagen = dirImagen;
	}

	public char getNombre() {
		return nombre;
	}

	public void setNombre(char nombre) {
		this.nombre = nombre;
	}

}
