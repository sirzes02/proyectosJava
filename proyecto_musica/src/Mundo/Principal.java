package Mundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;

public class Principal {
    private final Artista[] miArtista;
    private short contadorArtista;

    public Principal() {
        miArtista = new Artista[255];
        contadorArtista = 0;

        leerArchivo();
    }

    public void leerArchivo() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./data/entrada.txt"));

            short cantArtistas = Short.parseShort(br.readLine());

            for (int i = 0; i < cantArtistas; i++) {
                String nombre = br.readLine();
                String fechaNacimiento = br.readLine();
                String fechaDebut = br.readLine();
                boolean activo = Byte.parseByte(br.readLine()) == 0;
                String foto = br.readLine();

                crearArtista(nombre, fechaNacimiento, fechaDebut, activo, foto);

                short cantAlbum = Short.parseShort(br.readLine());

                for (int j = 0; j < cantAlbum; j++) {
                    nombre = br.readLine();
                    String genero = br.readLine();
                    String fechaLanzamiento = br.readLine();
                    foto = br.readLine();

                    crearAlbum((short) i, nombre, genero, fechaLanzamiento, foto);

                    short cantCancion = Short.parseShort(br.readLine());

                    for (int k = 0; k < cantCancion; k++) {
                        nombre = br.readLine();
                        String duracion = br.readLine();

                        crearCancion((short) i, (short) j, nombre, duracion);
                    }
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirArchivo() {
        try {
            File archivo = new File("./data/entrada.txt");

            if (archivo.createNewFile()) {
                System.out.println("El archivo no existia, creado.");
            }

            PrintWriter pw = new PrintWriter(archivo);

            pw.println(contadorArtista);
            for (int i = 0; i < contadorArtista; i++) {
                pw.println(miArtista[i].getNombre());
                pw.println(miArtista[i].getFechaNacimiento());
                pw.println(miArtista[i].getFechaDebout());
                pw.println(miArtista[i].isActivo() ? 0 : 1);
                pw.println(miArtista[i].getFoto());

                pw.println(miArtista[i].getContadorAlbum());
                for (int j = 0; j < miArtista[i].getContadorAlbum(); j++) {
                    pw.println(miArtista[i].getMiAlbum((short) j).getNombre());
                    pw.println(miArtista[i].getMiAlbum((short) j).getGenero());
                    pw.println(miArtista[i].getMiAlbum((short) j).getFechaLanzamiento());
                    pw.println(miArtista[i].getMiAlbum((short) j).getFoto());

                    pw.println(miArtista[i].getMiAlbum((short) j).getContadorCancion());

                    for (int k = 0; k < miArtista[i].getMiAlbum((short) j).getContadorCancion(); k++) {
                        pw.println(miArtista[i].getMiAlbum((short) j).getMiCancion((short) k).getNombre());
                        pw.println(miArtista[i].getMiAlbum((short) j).getMiCancion((short) k).getDuracion());
                    }
                }
            }

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Artista getMiArtista(short i) {
        return miArtista[i];
    }

    public void crearArtista(String nombre, String fechaNacimiento, String fechaDebut, boolean archivo, String foto) {
        miArtista[contadorArtista] = new Artista(nombre, fechaNacimiento, fechaDebut, archivo, foto);
        contadorArtista++;
    }

    public void crearAlbum(short i, String nombre, String genero, String fechaLanzamiento, String foto) {
        miArtista[i].crearAlbum(nombre, genero, fechaLanzamiento, foto);
    }

    public void crearCancion(short i, short j, String nombre, String duracion) {
        miArtista[i].crearCancion(j, nombre, duracion);
    }

    public short getContadorArtista() {
        return contadorArtista;
    }
}
