import java.util.ArrayList;

public class Libro {
    private int ISBN;
    private String titulo;
    private String autor;
    private int cantidadEnBiblioteca;
    private int cantidadDisponiblePrestamo;
    private String Imagen;

    public Libro(int ISBN, String titulo, String autor, int cantidadEnBiblioteca, int cantidadDisponiblePrestamo, String imagen) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.cantidadEnBiblioteca = cantidadEnBiblioteca;
        this.cantidadDisponiblePrestamo = cantidadDisponiblePrestamo;
        Imagen = imagen;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getCantidadEnBiblioteca() {
        return cantidadEnBiblioteca;
    }

    public void setCantidadEnBiblioteca(int cantidadEnBiblioteca) {
        this.cantidadEnBiblioteca = cantidadEnBiblioteca;
    }

    public int getCantidadDisponiblePrestamo() {
        return cantidadDisponiblePrestamo;
    }

    public void setCantidadDisponiblePrestamo(int cantidadDisponiblePrestamo) {
        this.cantidadDisponiblePrestamo = cantidadDisponiblePrestamo;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }


    public boolean ISBNUnico(int ISBN, ArrayList<Libro> listaLibros){
        boolean unico = true;
        for (int i=0; i< listaLibros.size(); i++){
            if(listaLibros.get(i).getISBN() == ISBN){
                unico = false;
            }
        }
        return unico;
    }

    public boolean validarCantidad(int cantidadEnBiblioteca, int cantidadDisponiblePrestamo){
        if (cantidadEnBiblioteca > 0 && cantidadDisponiblePrestamo > 0 && cantidadDisponiblePrestamo <= cantidadEnBiblioteca){
            return true;
        }
        else{
            return false;
        }
    }

    //Metodo para crear libro

    public void crearlibro(int ISBN,String titulo, String autor, int cantidadEnBiblioteca, int cantidadDisponiblePrestamo, String imagen, ArrayList<Libro> listalibros) {
        boolean unico = ISBNUnico(ISBN, listalibros);
        if (unico && validarCantidad(cantidadEnBiblioteca, cantidadDisponiblePrestamo)){
            Libro libro = new Libro(ISBN, titulo, autor , cantidadEnBiblioteca, cantidadDisponiblePrestamo, imagen);
            listalibros.add(libro);
            System.out.println("El libro con ISBN: " + ISBN + "fue agregado exitosamente");
        } else {
            if (!unico) {
                System.out.println("Error: El libro con ISBN " + ISBN + "ya existe en lista");
            } else {
            System.out.println("Error: La cantidad disponible para préstamo no puede ser mayor a las existencias");}
        }
    }

    public static void eliminarLibroPorISBN(int ISBN, ArrayList<Libro> listaLibros){
        for (int i=0; i< listaLibros.size(); i++){
            if(listaLibros.get(i).getISBN() == ISBN){
                listaLibros.remove(i);
                System.out.println("El libro" + ISBN + "fue eliminado exitosamente.");
                return;
            }
        }
        System.out.println("El libro" + ISBN + "no fue encontrado.");
    }
}
