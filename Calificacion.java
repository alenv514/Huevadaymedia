
package javaapplication71;

public class Calificacion {
    private Estudiante[] estudiantes = new Estudiante[20];
    private int cantidadEstudiantes = 0;

    public boolean agregarEstudiante(Estudiante estudiante) {
        if (cantidadEstudiantes < 20) {
            estudiantes[cantidadEstudiantes++] = estudiante;
            return true;
        }
        return false;
    }

    public boolean eliminarEstudiante(int index) {
        if (cantidadEstudiantes == 0 || index < 0 || index >= cantidadEstudiantes) {
            return false;
        }
        estudiantes[index] = estudiantes[cantidadEstudiantes - 1];
        estudiantes[cantidadEstudiantes - 1] = null;
        cantidadEstudiantes--;
        return true;
    }

    public boolean editarEstudiante(int index, String nombre, String apellido, String fechaNacimiento) {
        if (index >= 0 && index < cantidadEstudiantes) {
            estudiantes[index] = new Estudiante(
                estudiantes[index].getCedula(),
                nombre,
                apellido,
                fechaNacimiento
            );
            return true;
        }
        return false;
    }

    public Estudiante buscarEstudiantePorCedula(String cedula) {
        int index = Estudiante.buscarPorCedula(estudiantes, cantidadEstudiantes, cedula);
        if (index != -1) {
            return estudiantes[index];
        }
        return null;
    }

    public void mostrarEstudiantes() {
        if (cantidadEstudiantes == 0) {
            System.out.println("\n==============================");
            System.out.println(" No hay estudiantes registrados ");
            System.out.println("==============================\n");
            return;
        }
        System.out.println("\n======= LISTA DE ESTUDIANTES =======");
        System.out.printf("%-5s %-15s %-15s\n", "#", "Nombre", "Cédula");
        System.out.println("-------------------------------------");
        for (int i = 0; i < cantidadEstudiantes; i++) {
            System.out.printf("%-5d %-15s %-15s\n", (i + 1), estudiantes[i].getNombre(), estudiantes[i].getCedula());
        }
        System.out.println("=====================================\n");
    }

    public double promedioCurso() {
        if (cantidadEstudiantes == 0) return 0;
        double suma = 0;
        int totalNotas = 0;
        for (int i = 0; i < cantidadEstudiantes; i++) {
            suma += estudiantes[i].calcularPromedio();
            if (estudiantes[i].getCantidadNotas() > 0) totalNotas++;
        }
        if (totalNotas == 0) return 0;
        return suma / totalNotas;
    }

    public int getCantidadEstudiantes() { return cantidadEstudiantes; }
    public Estudiante getEstudiante(int index) { return estudiantes[index]; }
}