/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author WilsonMundo
 * @author Jefferson Lopez
 */
public class Variable {
    private Double valor;
    private char caracter;

  

    public Variable() {
    }

    public Variable(char caracter) {
        this.caracter = caracter;
    }
      public Variable(Double valor, char caracter) {
        this.valor = valor;
        this.caracter = caracter;
    }
    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }
    
}
