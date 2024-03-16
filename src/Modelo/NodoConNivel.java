/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author WilsonMundo
 * @author Jefferson Lopez
 */
public class NodoConNivel {

    ArbolBinario nodo;
    int nivel;
    int posicionX;

    public NodoConNivel(ArbolBinario nodo, int nivel, int posicion) {
        this.nodo = nodo;
        this.nivel = nivel;
        this.posicionX = posicion;
    }

    public static void pintarEnModoTexto(ArbolBinario raiz) {
        if (raiz == null) {
            return;
        }

        Queue<NodoConNivel> cola = new LinkedList<>();
        cola.add(new NodoConNivel(raiz, 0, 0)); /* Posición inicial del raíz en 0 para simplificar*/

        while (!cola.isEmpty()) {
            int size = cola.size(); /* Nodos en el nivel actual*/
            for (int i = 0; i < size; i++) {
                NodoConNivel actual = cola.poll();
               
                imprimirEspacios(actual.posicionX);
                System.out.print(actual.nodo.getValor());

                int desplazamiento = Math.max(1, 3 - actual.nivel);
                if (actual.nodo.getHijoIzquierdo() != null) {
                    cola.add(new NodoConNivel(actual.nodo.getHijoIzquierdo(), actual.nivel + 1, actual.posicionX - desplazamiento));
                }
                if (actual.nodo.getHijoDerecho() != null) {
                    cola.add(new NodoConNivel(actual.nodo.getHijoDerecho(), actual.nivel + 1, actual.posicionX + desplazamiento));
                }
            }
            System.out.println(); /* Nueva línea al final de cada nivel */
        }
    }

    private static void imprimirEspacios(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            System.out.print(" "); 
        }
    }
}
