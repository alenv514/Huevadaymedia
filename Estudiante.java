/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication71;

public class Estudiante {
    private String cedula;
    private String nombre;
    private double[] notas = new double[7];
    private int cantidadNotas = 0;

    public Estudiante(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public double[] getNotas() { return notas; }
    public int getCantidadNotas() { return cantidadNotas; }

    public boolean agregarNota(double nota) {
        if (cantidadNotas < 7) {
            notas[cantidadNotas++] = nota;
            return true;
        }
        return false;
    }

    public boolean eliminarNota(int index) {
        if (index >= 0 && index < cantidadNotas) {
            notas[index] = notas[cantidadNotas - 1];
            notas[cantidadNotas - 1] = 0;
            cantidadNotas--;
            return true;
        }
        return false;
    }

    public boolean editarNota(int index, double nuevaNota) {
        if (index >= 0 && index < cantidadNotas) {
            notas[index] = nuevaNota;
            return true;
        }
        return false;
    }

    public double calcularPromedio() {
        if (cantidadNotas == 0) return 0;
        double suma = 0;
        for (int i = 0; i < cantidadNotas; i++) {
            suma += notas[i];
        }
        return suma / cantidadNotas;
    }

    public static int buscarPorCedula(Estudiante[] estudiantes, int cantidad, String cedula) {
        for (int i = 0; i < cantidad; i++) {
            if (estudiantes[i].getCedula().equals(cedula)) {
                return i;
            }
        }
        return -1;
    }

    public void mostrarDatos() {
        System.out.println("Cédula: " + cedula);
        System.out.println("Nombre: " + nombre);
        System.out.print("Notas: ");
        for (int i = 0; i < cantidadNotas; i++) {
            System.out.print(notas[i] + " ");
        }
        System.out.println();
    }
}