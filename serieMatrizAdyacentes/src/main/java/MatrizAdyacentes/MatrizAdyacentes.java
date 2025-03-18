/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package MatrizAdyacentes;

/**
 *
 * @author Levid Abimael
 */

import java.util.ArrayList;
import java.util.List;

public class MatrizAdyacentes {
    public static List<int[]> encontrarAdyacentes(int[][] matriz, int fila, int columna) {
        List<int[]> adyacentes = new ArrayList<>();
        int filas = matriz.length;
        int columnas = matriz[0].length;

        // Arriba
        if (fila > 0) {
            adyacentes.add(new int[]{fila - 1, columna});
        }
        // Abajo
        if (fila < filas - 1) {
            adyacentes.add(new int[]{fila + 1, columna});
        }
        // Izquierda
        if (columna > 0) {
            adyacentes.add(new int[]{fila, columna - 1});
        }
        // Derecha
        if (columna < columnas - 1) {
            adyacentes.add(new int[]{fila, columna + 1});
        }

        return adyacentes;
    }

    public static void main(String[] args) {
        int[][] matriz = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        int fila = 1, columna = 1;
        List<int[]> adyacentes = encontrarAdyacentes(matriz, fila, columna);
        
        System.out.println("Posiciones adyacentes a (" + fila + ", " + columna + "):");
        for (int[] pos : adyacentes) {
            System.out.println("(" + pos[0] + ", " + pos[1] + ")");
        }
    }
}

