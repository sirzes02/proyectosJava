package Mundo;

public class Cancion {
    private final String nombre;
    private final String duracion;

    public Cancion(String nombre, String duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDuracion() {
        return duracion;
    }
}
