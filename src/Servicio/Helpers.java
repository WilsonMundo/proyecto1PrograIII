/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import static java.lang.Double.parseDouble;

/**
 *
 * @author WilsonMundo
 */
public class Helpers {

    public static class validaciones {

        public static boolean esOperador(String operador) {
            return operador.equals("*")
                    || operador.equals("/")
                    || operador.equals("+")
                    || operador.equals("-")
                    || operador.equals("^")
                    || operador.equals("√");
        }

        public static boolean esNumero(String valor) {
            if (valor == null || valor.isEmpty()) {
                return false;
            }
            try {
                Double.parseDouble(valor);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public static double RetornaNumero(String valor) {
            if (valor == null || valor.isEmpty()) {
                throw new RuntimeException("El valor ingresado no puede ser vacio");
            }
            try {
                return Double.parseDouble(valor);

            } catch (Exception e) {
                throw new RuntimeException("existio un error al convertir el valro a numero "
                        + "\n " + e.getMessage());
            }
        }

        public static boolean esLetra(String valor) {
            if (valor == null || valor.isEmpty()) {
                return false;
            }
            if (!(valor.length() == 1)) {
                return false;
            } else {
                return (Character.isLetter(valor.charAt(0)));
            }

        }

        public static boolean ValidaExpressionVacia(String expression) {
            if ((expression.length() <= 0) || (expression.equals(""))) {
                throw new RuntimeException("la cadena que ingreso esta vacia");
            } else {
                return true;
            }
        }

        public static int retornarValorOperador(String operador) {
            switch (operador) {
                case "+":
                    return 1;
                case "-":
                    return 1;
                case "*":
                    return 2;
                case "/":
                    return 2;
                case "√":
                    return 3;
                case "^":
                    return 4;
                default:
                    throw new RuntimeException("Error no existe operador definido");
            }
        }

        public static boolean sonParenteSis(String valor) {
            return (valor.contains(")")) || (valor.contains("}")) || (valor.contains("]")
                    || valor.contains("(") || (valor.contains("{")) || valor.contains("["));
        }

        public static double retornarOeracion(double operando1, double operando2, String operador) {
            switch (operador) {
                case "+":
                    return operando1 + operando2;
                case "-":
                    return operando1 - operando2;
                case "*":
                    return operando1 * operando2;
                case "/": {
                    if (operando2 == 0) {
                        throw new RuntimeException("No es posible dividir en 0");
                    }
                    return operando1 / operando2;
                }
                case "^":
                    return Math.pow(operando1, operando2);
                case "√":
                        return Math.sqrt(operando1);
                default:
                    throw new RuntimeException("Error operador no encontrado");
            }
        }

    }

}
