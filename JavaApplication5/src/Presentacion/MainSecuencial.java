/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import Logica.Secuencial;

/**
 *
 * @author Abraham
 */
public class MainSecuencial {
   public static void main(String[] args) {
       Secuencial llenador = new Secuencial();
        llenador.llenarVector();
        char[] vector = llenador.getVector();
        for (char c : vector) {
           System.out.print(c );
       }
        System.out.println();
    }
}

