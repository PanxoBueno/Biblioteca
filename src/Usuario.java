import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Usuario {

    public String nombreCompleto;
    private String RUN;
    private String genero;
    public String carrera;
    private boolean prestamo;
    private String prestamoISBN;
    //en caso de docente
    private boolean esDocente;
    private String profesion;
    private String grado;


    public Usuario(String nombreCompleto, String RUN, String genero, String carrera, boolean esDocente) {
        this.nombreCompleto = nombreCompleto;
        this.RUN = RUN;
        this.genero = genero;
        this.carrera = carrera;
        this.prestamo = false;
        this.prestamoISBN = "";
        this.esDocente = esDocente;
        this.profesion = "";
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getRUN() {
        return RUN;
    }

    public void setRUN(String RUN) {
        this.RUN = RUN;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public boolean isPrestamo() {
        return prestamo;
    }

    public void setPrestamo(boolean prestamo) {
        this.prestamo = prestamo;
    }

    public String getPrestamoISBN() {
        return prestamoISBN;
    }

    public void setPrestamoISBN(String prestamoISBN) {
        this.prestamoISBN = prestamoISBN;
    }

    public boolean isEsDocente() {
        return esDocente;
    }

    public void setEsDocente(boolean esDocente) {
        this.esDocente = esDocente;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    private static List<Usuario> usuarios = new ArrayList<>();


    //Validador de genero
    public static boolean validarGenero (String genero){
        return genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F");
    }


    public static boolean validarRUN(String RUN) {
        String regex = "^\\d{7,8}-[0-9Kk]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(RUN);

        if (!matcher.matches()) return false;
        String[] stringRut = RUN.split("-");
        return stringRut[1].equalsIgnoreCase(verificador(stringRut[0]));

    }

    // logica validar digito verificador de RUN

    public static String verificador (String RUN) {
        int mult = 0;
        int suma = 1;
        int numero = Integer.parseInt(RUN);

        while (numero != 0) {
            suma = (suma + numero % 10 * (9 - mult++ % 6)) % 11;
            numero = (int) Math.floor(numero / 10);
        }
        return (suma > 0) ? String.valueOf(suma - 1) : "k";

    }

    /*public static Usuario crearUsuario(String nombreCompleto, String RUN, String genero, String carrera, boolean esDocente) {
        for (Usuario u : usuarios) {
            if (u.getRUN().equals(RUN)) {
                System.out.println("Error: El RUN ya está registrado.");
                return null;
            }

        }

        Usuario nuevoUsuario = new Usuario(nombreCompleto, RUN, genero, carrera, esDocente);
        usuarios.add(nuevoUsuario);
        return nuevoUsuario;
    }*/

    public void editarUsuario(String nombreCompleto, String genero, String carrera, boolean esDocente) {
        Usuario usuarioAEditar = null;
        for (Usuario u : usuarios) {
            if (u.getRUN().equals(RUN)) {
                usuarioAEditar = u;
                break;
            }
        }

        if (usuarioAEditar != null) {
            usuarioAEditar.setNombreCompleto(nombreCompleto);
            usuarioAEditar.setGenero(genero);
            usuarioAEditar.setCarrera(carrera);
            usuarioAEditar.setEsDocente(esDocente);
            System.out.println("Usuario editado con éxito.");
        } else {
            System.out.println("Error: El usuario no existe.");
        }
    }

    public void eliminarUsuario() {
        Usuario usuarioAEliminar = null;
        for (Usuario u : usuarios) {
            if (u.getRUN().equals(RUN)) {
                usuarioAEliminar = u;
                break;
            }
        }

        if (usuarioAEliminar != null) {
            usuarios.remove(usuarioAEliminar);
            System.out.println("Usuario eliminado con éxito.");
        } else {
            System.out.println("Error: El usuario no existe.");
        }
    }

    public static Usuario crearEstudiante(String nombreCompleto, String RUN, String genero, String carrera) {
        Usuario nuevoEstudiante = new Usuario(nombreCompleto, RUN, genero, carrera, false);
        usuarios.add(nuevoEstudiante);

        for (Usuario usuario : usuarios) {
            System.out.println("Datos registrados correctamente:\n");
            System.out.println("Nombre Completo: " + usuario.getNombreCompleto());
            System.out.println("Run: " + usuario.getRUN());
            System.out.println("Género: " + usuario.getGenero());
            System.out.println("Carrera: " + usuario.getCarrera());
            System.out.println("___________________________________");
        }
        return nuevoEstudiante;
    }

    public static Usuario crearDocente(String nombreCompleto, String RUN, String genero, String carrera, String profesion, String grado) {
        if (validarRUN(RUN)) {
            System.out.println("Error: El RUN ya está registrado.");
            return null;
        }

        Usuario nuevoDocente = new Usuario(nombreCompleto, RUN, genero, carrera, true);
        nuevoDocente.setProfesion(profesion);
        nuevoDocente.setGrado(grado);

        usuarios.add(nuevoDocente);

        return nuevoDocente;
    }
    public boolean habilitadoParaPrestamo;
    public boolean isHabilitadoParaPrestamo() {
        return habilitadoParaPrestamo;
    }
}
