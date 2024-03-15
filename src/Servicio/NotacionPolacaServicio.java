/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import Modelo.ArbolBinario;
import Modelo.Pila;
import Modelo.PilaOperadores;
import Modelo.Variable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WilsonMundo
 */
public class NotacionPolacaServicio {

    private PilaOperadores pilaOperadores = new PilaOperadores();
    private Pila arbol = new Pila();
    List<String> valoresPostorden = new ArrayList<>();
    List<String> valoresPreOrden = new ArrayList<>();

    public List<String> getValoresPreOrden() {
        return valoresPreOrden;
    }

    public void setValoresPreOrden(List<String> valoresPreOrden) {
        this.valoresPreOrden = valoresPreOrden;
    }

    public List<String> getValoresInOrden() {
        return valoresInOrden;
    }

    public void setValoresInOrden(List<String> valoresInOrden) {
        this.valoresInOrden = valoresInOrden;
    }
    List<String> valoresInOrden = new ArrayList<>();
    public String generaProcesoArbolexpression(String expression, ArrayList<Variable> variables) {
        if ((expression.length() <= 0) || (expression.equals(""))) {
            throw new RuntimeException("la cadena que ingreso esta vacia");
        }
        Pila pila = new Pila();
        Pila pila2 = new Pila();

        RegistroVariablesServicio variableServicio = new RegistroVariablesServicio();
        Variable variable;
        String[] cadena = expression.split(" ");
        for (int i = 0; i <= cadena.length - 1; i++) {
            String operador = cadena[i];

            if (Helpers.validaciones.esNumero(operador)) {
                pila.push(new ArbolBinario(operador));
            } else if (Helpers.validaciones.esLetra(operador)) {
                variable = variableServicio.retornaVariable(variables, operador.charAt(0));
                pila.push(new ArbolBinario(operador));
            } else if (Helpers.validaciones.esOperador(operador)) {
                ArbolBinario derecho = pila.pop();
                ArbolBinario izquierdo = pila.pop();
                ArbolBinario arbol = new ArbolBinario(operador);
                arbol.setHijoIzquierdo(izquierdo);
                arbol.setHijoDerecho(derecho);
                pila.push(arbol);
            }

        }
        pila.LimpiarPila();

        return "";
    }

    public double notacionPolacaGeneral(String expression, ArrayList<Variable> variables) {
        if (Helpers.validaciones.ValidaExpressionVacia(expression)) {
            RegistroVariablesServicio variableServicio = new RegistroVariablesServicio();
            Variable variable;
            String[] cadena = expression.split(" ");
            for (int i = 0; i <= cadena.length - 1; i++) {
                String operador = cadena[i];

                if (Helpers.validaciones.esNumero(operador)) {
                    arbol.push(new ArbolBinario(operador));
                } else if (Helpers.validaciones.esLetra(operador)) {
                    variable = variableServicio.retornaVariable(variables, operador.charAt(0));
                    String valorVariable = String.valueOf(variable.getValor());
                    arbol.push(new ArbolBinario(valorVariable));
                } else if (Helpers.validaciones.esOperador(operador) || (pilaOperadores.ValidarParentesisAbierto(operador)) || (pilaOperadores.ValidarParentesisCerrado(operador))) {
                    if ((pilaOperadores.estaVacia()) || pilaOperadores.ValidarParentesis()) {
                        pilaOperadores.push(operador);
                    } else if ((pilaOperadores.ValidarParentesisAbierto(operador)) || (pilaOperadores.ValidarParentesisCerrado(operador))) {
                        if (pilaOperadores.ValidarParentesisAbierto(operador)) {
                            pilaOperadores.push(operador);
                        } else if (pilaOperadores.ValidarParentesisCerrado(operador)) {
                            while (!pilaOperadores.estaVacia() && !pilaOperadores.ValidarParentesis()) {
                                String operadorSacado = pilaOperadores.pop();
                                ArbolBinario derecho = arbol.pop();
                                ArbolBinario izquierdo = arbol.pop();
                                ArbolBinario nuevoArbol = new ArbolBinario(operadorSacado);
                                nuevoArbol.setHijoIzquierdo(izquierdo);
                                nuevoArbol.setHijoDerecho(derecho);
                                arbol.push(nuevoArbol);
                            }
                            if (!pilaOperadores.estaVacia()) {
                                pilaOperadores.pop();
                            }
                        }
                    } else if (Helpers.validaciones.retornarValorOperador(operador) > Helpers.validaciones.retornarValorOperador(pilaOperadores.peek())) {
                        pilaOperadores.push(operador);
                    } else {
                        while (!pilaOperadores.estaVacia() && Helpers.validaciones.retornarValorOperador(operador) <= Helpers.validaciones.retornarValorOperador(pilaOperadores.peek())) {
                            String operadorSacado = pilaOperadores.pop();
                            ArbolBinario derecho = arbol.pop();
                            ArbolBinario izquierdo = arbol.pop();
                            ArbolBinario nuevoArbol = new ArbolBinario(operadorSacado);
                            nuevoArbol.setHijoIzquierdo(izquierdo);
                            nuevoArbol.setHijoDerecho(derecho);
                            arbol.push(nuevoArbol);
                        }
                        pilaOperadores.push(operador);
                    }
                }
            }
            while (!pilaOperadores.estaVacia()) {
                String operadorSacado = pilaOperadores.pop();
                ArbolBinario derecho = arbol.pop();
                ArbolBinario izquierdo = arbol.pop();
                ArbolBinario nuevoArbol = new ArbolBinario(operadorSacado);
                nuevoArbol.setHijoIzquierdo(izquierdo);
                nuevoArbol.setHijoDerecho(derecho);
                arbol.push(nuevoArbol);
            }

            //arbol.LimpiarPila();
            if (!arbol.estaVacia()) {
                ArbolBinario arboles = arbol.pop();
                try {
                    double resultado = arboles.evaluar();
               
                                      
                  
                     arboles.recorrerPreorden(valoresPreOrden);
                        arboles.recorrerInorden(valoresInOrden);
                     arboles.recorrerPostorden(valoresPostorden);
                    return resultado;
                } catch (Exception e) {
                    throw new RuntimeException("Hubo un error al evaluar la expresión: " + e.getMessage());
                }
            } else {
               throw new RuntimeException("La pila está vacía, no hay expresión para evaluar.");
            }

        }else
        {
            throw new RuntimeException("Expresión no correcta");
        }
    }
        
    public List<String> getValoresPostorden() {
        return valoresPostorden;
    }

    public void setValoresPostorden(List<String> valoresPostorden) {
        this.valoresPostorden = valoresPostorden;
    }

    public ArrayList<Character> RetornaCadenaCaracteres(String expression) {
        ArrayList<Character> caracteres = new ArrayList<>();
        if ((expression.length() <= 0) || (expression.equals(""))) {
            throw new RuntimeException("la cadena que ingreso esta vacia");
        }
        String[] cadena = expression.split(" ");
        for (int i = 0; i <= cadena.length - 1; i++) {
            String operador = cadena[i];
            if (!(Helpers.validaciones.esOperador(operador))) {
                if (!(Helpers.validaciones.esNumero(operador))) {
                    if (Helpers.validaciones.esLetra(operador)) {
                        caracteres.add(operador.charAt(0));
                    }
                }
            }
        }
        return caracteres;
    }

}
