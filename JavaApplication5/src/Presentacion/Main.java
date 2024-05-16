/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

/**
 *
 * @author Abraham
 */

import Logica.Hilo;

public class Main {
    public static void main(String[] args) {
        Thread[] threads = new Thread[4];
        
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Hilo());
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
            }
        }
        char[] vector = Hilo.getVector();
        for (char c : vector) {
            System.out.print(c);
        }
        System.out.println();
    }

}



