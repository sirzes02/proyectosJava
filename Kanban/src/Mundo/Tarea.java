package Mundo;

public class Tarea {

	private String nombre;
	private String estado;
	private String prioridad;

	public Tarea(String nombre, String estado, String prioridad) {
		this.nombre = nombre;
		this.estado = estado;
		this.prioridad = prioridad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

}
