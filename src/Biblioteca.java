import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Biblioteca {



        public static void main(String[] args) {
        //ingreso de datos:
        Scanner sc = new Scanner(System.in);
        String eleccion = "";
        while (!eleccion.equals("9")) {
            System.out.println(
                    "1. Registrar Usuario\n2. Registrar libro\n3. Registrar prestamo\n4. Registrar devolución\n5. Ver libros\n6. Ver prestamos\n7. Salir\nElige:");
            eleccion = sc.nextLine();
            if (eleccion.equals("1")) {
                System.out.println(
                        "1. Registrar Estudiante\n2. Registrar Docente\n");
                eleccion = sc.nextLine();
                if (eleccion.equals("1")){
                    System.out.println("Ingrese Run: ");
                    String run = sc.nextLine();
                    System.out.println("Ingrese Nombre Completo: ");
                    String nombre = sc.nextLine();
                    //System.out.println("Ingrese genero (M / F): ");
                    String genero;
                    do {
                        System.out.println("Ingrese genero (M / F): ");
                        genero = sc.nextLine();
                        if (!Usuario.validarGenero(genero)) {
                            System.out.println("Entrada no válida. Por favor, ingrese M o F.");
                        }

                    } while (!Usuario.validarGenero(genero));

                    System.out.println("Ingrese Carrera: ");
                    String carrera = sc.nextLine();


                    Usuario.crearEstudiante(nombre,run,genero,carrera);

                }
                if (eleccion.equals("2")){
                    //Usuario.crearDocente();
                    System.out.println("es docente");
                }

            }

            /*
            if (eleccion.equals("2")) {
            }
            if (eleccion.equals("3")) {
            }
            if (eleccion.equals("4")) {
            }
            if (eleccion.equals("5")) {
            }
            if (eleccion.equals("6")) {
            }
            if (eleccion.equals("7")) {
            }*/
            if (eleccion.equals("7")) {
                System.exit(0);
            }

        }


        // GENERAMOS DATOS DE USUARIOS BASE
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>(
                Arrays.asList(
                       /* new Usuario("Juan Perez", "12345678-9", 'M', "Ingenieria Informática", true ),
                        new Usuario("María Gonzalez", "98765432-1", 'F', "Medicina", false),
                        new Usuario("Carlos Rodriguez", "34567890-1",'M', "Derecho", true ),
                        new Usuario("Ana López", "56789012-3", 'F', "Psicología", false ),
                        new Usuario("Pedro Martinez", "89012345-6", 'M', "Arquitectura", true )*/
                )
        );
        ArrayList<Libro> listaLibros = new ArrayList<Libro>(
                Arrays.asList(
                        new Libro(1234, "La Casa de los Espíritus", "Isabel Allende", 20, 15, "a"),
                        new Libro(2345,"Los Detectives Salvajes","Roberto Bolaño",18,12,"b"),
                        new Libro(3456,"Altazor","Vicente Huidobro",12,8,"c"),
                        new Libro(4567,"Bonsái","Alejandro Zambra",15,10,"d"),
                        new Libro(5678, "La Dimensión Desconocida","Nona Fernández",14,9,"e")
                )
        );



        // UN LISTA DE DEVOLUCIONES
        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();

        // GENERAMOS UN PRÉSTAMO
        Prestamo prestamo = Prestamo.ingresarPrestamo(1234, "56789012-3", listaLibros, usuarios, 5);

        // AGREGAMOS EL PRÉTAMO AL ARREGLO DE PRÉSTAMOS
        prestamos.add(prestamo);

        // IMPRIMIMOS EL ESTADO ACTUAL DEL PRÉSTAMO
        System.out.println(prestamo.toString());

        // GENERAMOS UNA DEVOLUCION
        //ISBN, String RUN, LocalDateTime fechaDevolucion
        Prestamo.ingresarDevolucion(1234, "56789012-3", prestamos);
        System.out.println("-----------------------------------------------------------");
        // IMPRIMIMOS EL ESTADO ACTUAL DEL PRÉSTAMO
        System.out.println(prestamo.toString());

        int ISBNParaEliminar = 3456;
        Libro.eliminarLibroPorISBN(ISBNParaEliminar, listaLibros);
        System.out.println(listaLibros);
    }
}
