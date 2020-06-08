package Mundo;

public class Album {
    private final Cancion[] miCancion;
    private final String nombre;
    private final String genero;
    private final String fechaLanzamiento;
    private final String foto;
    private short contadorCancion;

    public Album(String nombre, String genero, String fechaLanzamiento, String foto) {
        this.nombre = nombre;
        this.genero = genero;
        this.fechaLanzamiento = fechaLanzamiento;
        this.foto = foto;
        this.contadorCancion = 0;
        miCancion = new Cancion[255];
    }

    public Cancion getMiCancion(short i) {
        return miCancion[i];
    }

    public void crearCancion(String nombre, String duracion) {
        miCancion[contadorCancion] = new Cancion(nombre, duracion);
        contadorCancion++;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public String getFoto() {
        return foto;
    }

    public short getContadorCancion() {
        return contadorCancion;
    }
}
