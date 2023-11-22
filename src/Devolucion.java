import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Devolucion {
    private int ISBN;
    private String RUN;
    private LocalDate fechaDevolucion;

    public Devolucion(int ISBN, String RUN, LocalDate fechaDevolucion) {
        this.ISBN = ISBN;
        this.RUN = RUN;
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

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void efectuarDevolucion(ArrayList<Libro> listaLibros) {
        boolean libroEncontrado = false;

        for (Libro libro : listaLibros) {
            if (libro.getISBN() == ISBN) {
                System.out.println("Proceso de devolución completado con éxito.");
                libroEncontrado = true;

                // Aquí puedes agregar la lógica adicional para la devolución

                break; // No es necesario seguir iterando
            }
        }

        if (!libroEncontrado) {
            System.out.println("El libro no se puede devolver porque no existe en la colección.");
        }
    }

     private boolean verificarExistenciasLibros(ArrayList<Libro> listaLibros) {
         for (Libro libro : listaLibros) {
             if (libro.getISBN() == ISBN) {
                 System.out.println("Libro encontrado: " + libro.getTitulo());
                 return true;
             }
         }
         System.out.println("El libro con el ISBN " + ISBN + " no existe en la colección.");
         return false;
     }

    private void habilitarUsuario(String RUN, ArrayList<Prestamo> prestamos, ArrayList<Usuario> usuarios) {
        int indexPrestamo = -1;
        for (int i = 0; i < prestamos.size(); i++) {
            Prestamo prestamo = prestamos.get(i);
            if (prestamo.getISBN() == ISBN && prestamo.getRUN().equals(RUN)) {
                indexPrestamo = i;
                break;
            }
        }

        if (indexPrestamo == -1) {
            System.out.println("No se encontró el préstamo correspondiente. Devolución no realizada.");
            return;
        }

        // Obtener referencia al usuario desde la lista de usuarios
        for (Usuario usuario : usuarios) {
            if (usuario.getRUN().equals(RUN)) {
                usuario.habilitadoParaPrestamo = true;
                System.out.println("Usuario habilitado para préstamo");
                return;
            }
        }

        System.out.println("No se encontró el usuario correspondiente. Devolución no realizada.");
    }

    private void aumentarExistenciasDisponibles(ArrayList<Libro> listaLibros, int ISBN) {
        for (Libro listaLibro : listaLibros) {
            if (listaLibro.getISBN() == ISBN) {
                Libro libro = listaLibro;
                listaLibro.setCantidadDisponiblePrestamo(libro.getCantidadDisponiblePrestamo() + 1);
                listaLibro.setCantidadEnBiblioteca(libro.getCantidadEnBiblioteca() + 1);
            }
        }
    }

    private LocalDate obtenerFechaLimiteDevolucion(String RUN, ArrayList<Prestamo> prestamos) {
        Prestamo prestamo = null;
        // busca prestamo
        for(int i=0; i < prestamos.size(); i++){
            if(prestamos.get(i).getRUN() == RUN){
                prestamo = prestamos.get(i);
            }
        }
        return prestamo.getFechaDevolucion();
    }

    private void revisarRetraso(String RUN, ArrayList<Prestamo> prestamos) {
        LocalDate fechaLimite = obtenerFechaLimiteDevolucion(RUN, prestamos);
        LocalDate fechaActual = LocalDate.now();
        if (fechaLimite.isAfter(fechaActual)) {
            int diasDeRetraso = (int) fechaActual.until(fechaLimite, ChronoUnit.DAYS);
            aplicarMulta(diasDeRetraso);
        }
    }

    private void aplicarMulta(int diasDeRetraso) {
        int multa = diasDeRetraso * 1000; // $1,000 por día de retraso
        System.out.println("Se ha aplicado una multa de $" + multa + " por " + diasDeRetraso + " días de retraso.");
    }

    private int calcularDiasDeRetraso() {
        return (int) LocalDate.now().until(fechaDevolucion, java.time.temporal.ChronoUnit.DAYS);
    }
}
