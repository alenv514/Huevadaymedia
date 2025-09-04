
package javaapplication71;

import java.util.Scanner;

public class GestorPersona {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calificacion calificacion = new Calificacion();
        int opcion;

        do {
            System.out.println("\n--- Gestor de Personas ---");
            System.out.println("1. Estudiantes");
            System.out.println("2. Registro Calificaciones");
            System.out.println("3. Promedio de un Estudiante");
            System.out.println("4. Determina el promedio de Notas de Curso");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    submenuEstudiantes(sc, calificacion);
                    break;
                case 2:
                    registroCalificaciones(sc, calificacion);
                    break;
                case 3:
                    promedioEstudiante(sc, calificacion);
                    break;
                case 4:
                    promedioCurso(calificacion);
                    break;
                case 0:
                    System.out.println("¡Gracias por usar el Gestor de Personas!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private static void submenuEstudiantes(Scanner sc, Calificacion calificacion) {
        int subopcion;
        do {
            System.out.println("\n--- Submenú Estudiantes ---");
            calificacion.mostrarEstudiantes();
            System.out.println("1. Ingresar estudiante");
            System.out.println("2. Modificar estudiante");
            System.out.println("3. Eliminar estudiante");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            subopcion = sc.nextInt();
            sc.nextLine();

            switch (subopcion) {
                case 1:
                    if (calificacion.getCantidadEstudiantes() >= 20) {
                        System.out.println("Ya se ingresó el máximo de estudiantes.");
                        break;
                    }
                    System.out.print("Ingrese cédula: ");
                    String cedula = sc.nextLine();
                    if (calificacion.buscarEstudiantePorCedula(cedula) != null) {
                        System.out.println("Ya existe un estudiante con esa cédula.");
                        break;
                    }
                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Ingrese apellido: ");
                    String apellido = sc.nextLine();
                    System.out.print("Ingrese fecha de nacimiento: ");
                    String fechaNacimiento = sc.nextLine();
                    Estudiante nuevo = new Estudiante(cedula, nombre);
                    calificacion.agregarEstudiante(nuevo);
                    System.out.println("Estudiante ingresado correctamente.");
                    break;
                case 2:
                    if (calificacion.getCantidadEstudiantes() == 0) {
                        System.out.println("No hay estudiantes para modificar.");
                        break;
                    }
                    System.out.print("Ingrese el número del estudiante a modificar: ");
                    int modIndex = sc.nextInt() - 1;
                    sc.nextLine();
                    if (modIndex < 0 || modIndex >= calificacion.getCantidadEstudiantes()) {
                        System.out.println("Número inválido.");
                        break;
                    }
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Nuevo apellido: ");
                    String nuevoApellido = sc.nextLine();
                    System.out.print("Nueva fecha de nacimiento: ");
                    String nuevaFecha = sc.nextLine();
                    calificacion.editarEstudiante(modIndex, nuevoNombre, nuevoApellido, nuevaFecha);
                    System.out.println("Estudiante modificado correctamente.");
                    break;
                case 3:
                    if (calificacion.getCantidadEstudiantes() == 0) {
                        System.out.println("No hay estudiantes para eliminar.");
                        break;
                    }
                    System.out.print("Ingrese el número del estudiante a eliminar: ");
                    int elimIndex = sc.nextInt() - 1;
                    sc.nextLine();
                    if (elimIndex < 0 || elimIndex >= calificacion.getCantidadEstudiantes()) {
                        System.out.println("Número inválido.");
                        break;
                    }
                    calificacion.eliminarEstudiante(elimIndex);
                    System.out.println("Estudiante eliminado correctamente.");
                    break;
                case 4:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
            System.out.print("¿Desea realizar otra acción en el submenú estudiantes? (s/n): ");
        } while (sc.nextLine().equalsIgnoreCase("s"));
    }

    private static void registroCalificaciones(Scanner sc, Calificacion calificacion) {
        System.out.print("Ingrese la cédula del estudiante: ");
        String cedula = sc.nextLine();
        Estudiante estudiante = calificacion.buscarEstudiantePorCedula(cedula);
        while (estudiante == null) {
            System.out.println("No existe estudiante con esa cédula.");
            System.out.print("Ingrese la cédula nuevamente: ");
            cedula = sc.nextLine();
            estudiante = calificacion.buscarEstudiantePorCedula(cedula);
        }
        estudiante.mostrarDatos();
        System.out.print("¿Es el estudiante correcto? (s/n): ");
        if (!sc.nextLine().equalsIgnoreCase("s")) return;

        int accion;
        do {
            System.out.println("\n--- Gestión de Calificaciones ---");
            System.out.println("1. Ingresar calificación");
            System.out.println("2. Eliminar calificación");
            System.out.println("3. Editar calificación");
            System.out.println("4. Volver");
            System.out.print("Seleccione una opción: ");
            accion = sc.nextInt();
            sc.nextLine();

            switch (accion) {
                case 1:
                    if (estudiante.getCantidadNotas() >= 7) {
                        System.out.println("Ya se ingresó el máximo de notas.");
                        break;
                    }
                    System.out.print("Ingrese la nota: ");
                    double nota = sc.nextDouble();
                    sc.nextLine();
                    if (estudiante.agregarNota(nota)) {
                        System.out.println("Nota ingresada correctamente.");
                    } else {
                        System.out.println("No se pudo ingresar la nota.");
                    }
                    break;
                case 2:
                    if (estudiante.getCantidadNotas() == 0) {
                        System.out.println("No hay notas para eliminar.");
                        break;
                    }
                    System.out.print("Ingrese el número de la nota a eliminar (1-" + estudiante.getCantidadNotas() + "): ");
                    int elimNota = sc.nextInt() - 1;
                    sc.nextLine();
                    if (estudiante.eliminarNota(elimNota)) {
                        System.out.println("Nota eliminada correctamente.");
                    } else {
                        System.out.println("No se pudo eliminar la nota.");
                    }
                    break;
                case 3:
                    if (estudiante.getCantidadNotas() == 0) {
                        System.out.println("No hay notas para editar.");
                        break;
                    }
                    System.out.print("Ingrese el número de la nota a editar (1-" + estudiante.getCantidadNotas() + "): ");
                    int editNota = sc.nextInt() - 1;
                    sc.nextLine();
                    System.out.print("Ingrese la nueva nota: ");
                    double nuevaNota = sc.nextDouble();
                    sc.nextLine();
                    if (estudiante.editarNota(editNota, nuevaNota)) {
                        System.out.println("Nota editada correctamente.");
                    } else {
                        System.out.println("No se pudo editar la nota.");
                    }
                    break;
                case 4:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
            System.out.print("¿Desea realizar otra acción con las calificaciones? (s/n): ");
        } while (sc.nextLine().equalsIgnoreCase("s"));
    }

    private static void promedioEstudiante(Scanner sc, Calificacion calificacion) {
        System.out.print("Ingrese la cédula del estudiante: ");
        String cedula = sc.nextLine();
        Estudiante estudiante = calificacion.buscarEstudiantePorCedula(cedula);
        if (estudiante == null) {
            System.out.println("No existe estudiante con esa cédula.");
            return;
        }
        double promedio = estudiante.calcularPromedio();
        if (promedio == 0) {
            System.out.println("El estudiante no tiene notas registradas.");
        } else {
            System.out.println("El promedio del estudiante es: " + promedio);
        }
    }

    private static void promedioCurso(Calificacion calificacion) {
        double promedio = calificacion.promedioCurso();
        if (promedio == 0) {
            System.out.println("No hay notas registradas en el curso.");
        } else {
            System.out.println("El promedio del curso es: " + promedio);
        }
    }
}