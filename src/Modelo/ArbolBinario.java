/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Servicio.Helpers;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author WilsonMundo
 * @author Jefferson Lopez
 */
public class ArbolBinario {

    private String valor;
    private ArbolBinario hijoIzquierdo;
    private ArbolBinario hijoDerecho;

    public ArbolBinario(String valor) {
        this.valor = valor;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public ArbolBinario getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(ArbolBinario hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public ArbolBinario getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(ArbolBinario hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public void recorrerPostorden(List<String> valores) {
        if (hijoIzquierdo != null) {
            hijoIzquierdo.recorrerPostorden(valores);
        }
        if (hijoDerecho != null) {
            hijoDerecho.recorrerPostorden(valores);
        }

        valores.add(valor);

    }

    public void recorrerPreorden(List<String> valores) {

        valores.add(valor);

        if (hijoIzquierdo != null) {
            hijoIzquierdo.recorrerPreorden(valores);
        }

        if (hijoDerecho != null) {
            hijoDerecho.recorrerPreorden(valores);
        }
    }

    public void recorrerInorden(List<String> valores) {

        if (hijoIzquierdo != null) {
            hijoIzquierdo.recorrerInorden(valores);
        }

        valores.add(valor);

        if (hijoDerecho != null) {
            hijoDerecho.recorrerInorden(valores);
        }
    }

    public double evaluar() {
        if (Helpers.validaciones.esOperador(valor)) {
            double izquierdo = hijoIzquierdo.evaluar();
            double derecho = hijoDerecho.evaluar();
            return Helpers.validaciones.retornarOeracion(izquierdo, derecho, valor);
        } else {

            return Double.parseDouble(valor);
        }

    }

  

    


}
