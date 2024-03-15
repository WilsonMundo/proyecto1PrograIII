/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WilsonMundo
 */
public class Pila {

    private NodoPila top;

    private class NodoPila {

        ArbolBinario arbol;
        NodoPila siguiente;

        public NodoPila(ArbolBinario arbol) {
            this.arbol = arbol;
            this.siguiente = null;
        }
    }

    public Pila() {
        this.top = null;
    }

    public void push(ArbolBinario arbol) {
        NodoPila nuevoNodo = new NodoPila(arbol);
        nuevoNodo.siguiente = top;
        top = nuevoNodo;
    }

    public ArbolBinario pop() {
        if (top == null) {
            throw new RuntimeException("Pila Vacia");
        }
        ArbolBinario arbol = top.arbol;
        top = top.siguiente;
        return arbol;
    }

    public boolean estaVacia() {
        return top == null;
    }

    public List<String> LimpiarPila() {
        List<String> valoresPostorden = new ArrayList<>();
        while (!estaVacia()) {
            ArbolBinario arbolExtraido = pop();
            // arbolExtraido.recorrerPostorden();
            //  System.out.println();
            
            arbolExtraido.recorrerPostorden(valoresPostorden);

        }
        return valoresPostorden;
    }

}
