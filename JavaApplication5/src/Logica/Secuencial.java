/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author Abraham
 */


import java.util.Random;

public class Secuencial {
    private static final int tamanoVector = 80;
    private static final char[] letras = {'a', 'b', 'c', 'd'};
    private static final int maxLetras = 20;
    private final char[] vector = new char[tamanoVector];
    private final int[] counts = new int[letras.length];
    private final Random random = new Random();
    
    public void llenarVector() {
        int contador = 0;

        while (contador < tamanoVector) {
            int indexL = random.nextInt(letras.length);
            if (counts[indexL] < maxLetras) {
                vector[contador++] = letras[indexL];
                counts[contador]++;
            }
        }
    }

    public char[] getVector() {
        return vector;
    }
    
}
