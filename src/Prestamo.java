import java.time.LocalDate;
import java.util.ArrayList;

public class Prestamo {
    private Usuario usuario;
    private Libro libro;
    private int ISBN;
    private String RUN;
    private LocalDate fechaPrestamo;
    private int diasPrestamo;
    private LocalDate fechaDevolucion;


    public Prestamo(Usuario usuario, Libro libro, int ISBN, String RUN, LocalDate fechaPrestamo, int diasPrestamo, LocalDate fechaDevolucion) {
        this.usuario = usuario;
        this.libro = libro;
        this.ISBN = ISBN;
        this.RUN = RUN;
        this.fechaPrestamo = fechaPrestamo;
        this.diasPrestamo = diasPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getRUN() {
        return RUN;
    }

    public void setRUN(String RUN) {
        this.RUN = RUN;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public int getDiasPrestamo() {
        return diasPrestamo;
    }

    public void setDiasPrestamo(int diasPrestamo) {
        this.diasPrestamo = diasPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    // verificar si el libro existe por ISBN
    public static Prestamo ingresarPrestamo(int ISBN, String RUN, ArrayList<Libro> listaLibros, ArrayList<Usuario> usuarios, int diasPrestamo){

        Libro libro = null;
        Usuario usuario = null;

        // busca libro
        for(int i=0; i < listaLibros.size(); i++){
            if (listaLibros.get(i).getISBN() == ISBN){
                libro = listaLibros.get(i);
            }
        }
        // busca usuario
        for(int i=0; i < usuarios.size(); i++){
            if(usuarios.get(i).getRUN() == RUN){
                usuario = usuarios.get(i);
            }
        }
        //Verifica si existe el libro buscado
        if(libro == null){
            throw new IllegalArgumentException("No existe el libro con ISBN: " +ISBN);
        }
        //Verifica si existe disponibilidad de libros para hacer prestamo
        if(libro.getCantidadDisponiblePrestamo()<1){
            throw new IllegalArgumentException("No existen ejemplares disponibles del libro con ISBN: '"+ISBN+"' para hacer un prestamo");
        }
        //Verifica si existe el usuario buscado
        if(usuario == null){
            throw new IllegalArgumentException("No existen usuarios con RUN: '"+RUN+"'");
        }
        //Verifica si el usuario está habilitado para realizar prestamos
        if(usuario.isPrestamo() == true){
            throw new IllegalArgumentException("Usuario con RUN: '"+RUN+"' no está habilitado para realizar prestamos.");
        }
        if(usuario.isEsDocente() == true && diasPrestamo > 20){
            throw new IllegalArgumentException("Los días máximos de prestamo para Docentes son 20 días.");
        }
        if(usuario.isEsDocente() == false && diasPrestamo > 10){
            throw new IllegalArgumentException("Los días máximos de prestamo para Estudiantes son 10 días.");
        }
        //Si el libro existe, está disponible para prestamo, el usuario existe y está habilitado para prestamos, retorna true
        Prestamo prestamo = new Prestamo(usuario, libro, ISBN, RUN, LocalDate.now(), diasPrestamo, LocalDate.now().plusDays(diasPrestamo));

        return prestamo;
    }
    public static void ingresarDevolucion(int ISBN, String RUN, ArrayList<Prestamo> prestamos) {
        Prestamo prestamo = null;
        // busca prestamo
        for(int i=0; i < prestamos.size(); i++){
            if(prestamos.get(i).getRUN() == RUN){
                prestamo = prestamos.get(i);
            }
        }
        if (prestamo == null){
            throw new IllegalArgumentException("El prestamo asociado al RUN: '"+RUN+"' no existe");
        }
        Devolucion devolucion = new Devolucion(ISBN, RUN, prestamo.getFechaDevolucion());
    }
}
