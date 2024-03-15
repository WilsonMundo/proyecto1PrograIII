/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author WilsonMundo
 */
public class PilaOperadores {
       private Node top;
    
    private class Node {
        String value;
        Node right;

        public Node(String value) {
            this.value = value;
            this.right = null;
        }
    }
     public PilaOperadores() {
        this.top = null;
    }
    public void push(String value) {
        Node newNode = new Node(value);
        newNode.right = top;
        top = newNode;
    }
      public String pop() {
        if (top == null) {
            throw new RuntimeException("Pila Vacia");
        }
        String value = top.value;
        top = top.right;
        return value;
    }
    public String peek() {
        if (top == null) {
            throw new RuntimeException("Stack is empty");
        }
        return top.value;
    }  
    public void LimpiarPila() {
        while (top != null) {
            System.out.println("Valor saliendo de la pila " + pop());
        }
    }
    public boolean ValidarParentesis()
    {
        if(top == null)
            return false;
        String valor = peek();        
        return valor.contains("(") ||(valor.contains("{")) ||valor.contains("[");                
    }
     public boolean ValidarParentesisAbierto(String valor)
    {               
        return valor.contains("(") ||(valor.contains("{")) ||valor.contains("[");                
    }
       public boolean ValidarParentesisCerrado(String  valor)
    {               
        return  (valor.contains(")")) ||(valor.contains("}")) ||(valor.contains("]"));                
    }
    public boolean estaVacia()
    {
        return top ==null;
    }
}
