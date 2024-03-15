/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import Modelo.Variable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author WilsonMundo
 */
public class RegistroVariablesServicio {

    private Variable RegistraVariables(double numero, char caracter) {

        if (!(Character.isLetter(caracter))) {
            throw new RuntimeException("el valor ingresado como variable no es un caracter valido\n "
                    + "solo se aceptan letras del alfabeto");
        }
        Variable variable = new Variable(numero, caracter);
        return variable;
    }

    public Variable insertaVariable(double numero, String caracter) {
        if (!(caracter.length() == 1)) {
            throw new RuntimeException("la expresion indicada como variable no aplica unicamente"
                    + "\n se acepta un caracter");
        } else {
            return RegistraVariables(numero, caracter.charAt(0));
        }
    }

    public String ValidarExpression(String expression) {
        String expressionRetorno = "";
        for (int i = 0; i < expression.length(); i++) {
            boolean esDigito;
            expressionRetorno = expressionRetorno + String.valueOf(expression.charAt(i));
            esDigito = Character.isDigit(expression.charAt(i));
            boolean esOperador = Helpers.validaciones.esOperador(String.valueOf(expression.charAt(i)));
            if (i + 1 < expression.length()) {
                boolean esDigitoSiguiente = Character.isDigit(expression.charAt(i + 1));
                boolean espacioActual = expression.charAt(i) == ' ';
                boolean espacioActualSiguiente = expression.charAt(i + 1) == ' ';

                boolean esOperadorSiguiente = Helpers.validaciones.esOperador(String.valueOf(expression.charAt(i + 1)));

                if (esOperador && esOperadorSiguiente) {
                    throw new RuntimeException("La expression es invalida no puede contener dos operadores seguidos");
                }

                if (!espacioActual && !espacioActualSiguiente && !(esDigito && esDigitoSiguiente)) {
                    expressionRetorno = expressionRetorno + " ";
                }
            }
            if (!esDigito && !esOperador) {
                if (!(Helpers.validaciones.esLetra(String.valueOf(expression.charAt(i))))) {
                    if (!(Helpers.validaciones.sonParenteSis(String.valueOf(expression.charAt(i))))) {
                        throw new RuntimeException("Este caracter no esta admitido " + expression.charAt(i));
                    }
                }
            }
        }

        return expressionRetorno;
    }

    public Variable retornaVariable(ArrayList<Variable> variables, char caracter) {
        Variable item = new Variable();
        if ((variables != null) && (variables.size() > 0)) {
            for (Variable variable : variables) {
                if (variable.getCaracter() == caracter) {
                    item = variable;
                    break;
                }
            }
            if ((item != null) && (item.getValor() != null)) {
                return item;
            } else {
                throw new RuntimeException("no se localizo informacion de la variable");
            }
        } else {
            throw new RuntimeException("las variables son nulas");
        }
    }

    public ArrayList<Variable> RetonarVariableSinValor(String expression) {

        ArrayList<Variable> variable = new ArrayList<>();
        boolean yaExisteValorVariable = false;
        String[] cadena = expression.split(" ");
        for (int i = 0; i < cadena.length; i++) {
            String operador = cadena[i];
            if (Helpers.validaciones.esLetra(operador)) {
                for (Variable item : variable) {
                    if (item.getCaracter() == operador.charAt(0)) {
                        yaExisteValorVariable = true;
                        break;
                    }
                }
                if (!(yaExisteValorVariable)) {
                    variable.add(new Variable(operador.charAt(0)));
                }
            }
        }
        return variable;
    }

    public boolean ExisenVariablesConValorNull(ArrayList<Variable> variable) {
        for (Variable item : variable) {
            if (item.getValor() == null) {
                return true;
            }
        }
        return false;
    }
}
