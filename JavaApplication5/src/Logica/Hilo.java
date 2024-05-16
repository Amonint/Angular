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

public class Hilo implements Runnable {
    private static final int tamanoVector = 80;
    private static final char[] letras = {'a', 'b', 'c', 'd'};
    private static final int maxLetras = 20;
    private static final char[] vector = new char[tamanoVector];
    private static final int[] counts = new int[letras.length];
    private static int contLetras = 0;

    private final Random random = new Random();

    @Override
    public void run() {
        while (true) {
            synchronized (Hilo.class) {
                if (contLetras >= tamanoVector) {
                    break;
                }

                int indexL = random.nextInt(letras.length);
                if (counts[indexL] < maxLetras) {
                    vector[contLetras++] = letras[indexL];
                    counts[indexL]++;
                }
            }
        }
    }

    public static char[] getVector() {
        return vector;
    }
    
}
