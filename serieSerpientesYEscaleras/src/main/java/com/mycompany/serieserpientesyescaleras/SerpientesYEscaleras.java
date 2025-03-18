/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.serieserpientesyescaleras;

import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author J. Levid Abimael
 */

// Clase principal que maneja el juego
public class SerpientesYEscaleras {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tablero tablero = new Tablero();
        Dado dado = new Dado();

        System.out.print("Ingresa el nombre del Jugador 1: ");
        Jugador jugador1 = new Jugador(scanner.nextLine());

        System.out.print("Ingresa el nombre del Jugador 2: ");
        Jugador jugador2 = new Jugador(scanner.nextLine());

        boolean juegoTerminado = false;
        Jugador turnoActual = jugador1;

        while (!juegoTerminado) {
            System.out.println("\nEs turno de " + turnoActual.getNombre());
            System.out.print("Presiona ENTER para lanzar el dado...");
            scanner.nextLine();

            int resultadoDado = dado.lanzar();
            System.out.println(turnoActual.getNombre() + " saco un " + resultadoDado);

            turnoActual.mover(resultadoDado, tablero);
            
            if (turnoActual.haGanado(tablero)) {
                System.out.println("\n¡" + turnoActual.getNombre() + " ha ganado el juego!");
                juegoTerminado = true;
                break;
            }

            turnoActual = (turnoActual == jugador1) ? jugador2 : jugador1;
        }
        scanner.close();
    }
}
// Clase que representa el tablero del juego
class Tablero {
    private final int[] origenCasillasEspeciales = {7, 11, 31, 30, 40, 43, 50, 59};
    private final int[] destinoCasillasEspeciales = {38, 37, 46, 2, 21, 60, 5, 42};
    private static final int TAMANO_TABLERO = 64;

    public int calcularNuevaPosicion(int posicion) {
        for (int i = 0; i < origenCasillasEspeciales.length; i++) {
            if (posicion == origenCasillasEspeciales[i]) {
                return destinoCasillasEspeciales[i];
            }
        }
        return posicion;
    }

    public boolean esPosicionFinal(int posicion) {
        return posicion == TAMANO_TABLERO;
    }

    public int getTamanoTablero() {
        return TAMANO_TABLERO;
    }
}
// Clase que representa al jugador
class Jugador {
    private String nombre;
    private int posicion;
    private int contadorSeis;
    private boolean bloqueado;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.posicion = 1;
        this.contadorSeis = 0;
        this.bloqueado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public void mover(int avance, Tablero tablero) {
        if (bloqueado) {
            if (avance == 6) {
                bloqueado = false;
                System.out.println(nombre + " ha sido desbloqueado!");
            } else {
                System.out.println(nombre + " sigue bloqueado hasta sacar un 6.");
                return;
            }
        }

        if (avance == 6) {
            contadorSeis++;
            if (contadorSeis == 3) {
                System.out.println(nombre + " saco tres veces 6 y vuelve a la posicion 1!");
                posicion = 1;
                bloqueado = true;
                contadorSeis = 0;
                return;
            }
        } else {
            contadorSeis = 0;
        }

        int nuevaPosicion = posicion + avance;
        if (nuevaPosicion > tablero.getTamanoTablero()) {
            nuevaPosicion = tablero.getTamanoTablero() - (nuevaPosicion - tablero.getTamanoTablero());
        }

        posicion = tablero.calcularNuevaPosicion(nuevaPosicion);
        System.out.println(nombre + " se mueve a la posicion " + posicion);
    }

    public boolean haGanado(Tablero tablero) {
        return tablero.esPosicionFinal(posicion);
    }
}

// Clase que representa el dado
class Dado {
    private Random random;

    public Dado() {
        random = new Random();
    }

    public int lanzar() {
        return random.nextInt(6) + 1;
    }
}