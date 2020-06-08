package Mundo;

public class Artista {
    private final Album[] miAlbum;
    private final String nombre;
    private final String fechaNacimiento;
    private final String fechaDebout;
    private final boolean activo;
    private final String foto;
    private short contadorAlbum;

    public Artista(String nombre, String fechaNacimiento, String fechaDebout, boolean activo, String foto) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaDebout = fechaDebout;
        this.activo = activo;
        this.foto = foto;
        miAlbum = new Album[255];
        contadorAlbum = 0;
    }

    public Album getMiAlbum(short i) {
        return miAlbum[i];
    }

    public void crearAlbum(String nombre, String genero, String fechaLanzamiento, String foto) {
        miAlbum[contadorAlbum] = new Album(nombre, genero, fechaLanzamiento, foto);
        contadorAlbum++;
    }

    public void crearCancion(short j, String nombre, String duracion) {
        miAlbum[j].crearCancion(nombre, duracion);
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getFechaDebout() {
        return fechaDebout;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getFoto() {
        return foto;
    }

    public short getContadorAlbum() {
        return contadorAlbum;
    }
}
