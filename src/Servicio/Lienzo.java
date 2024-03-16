/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import Modelo.ArbolBinario;
import java.awt.Graphics;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author WilsonMundo
 * @author Jefferson Lopez
 */
public class Lienzo extends JPanel {

    private ArbolBinario objArbol;
    public static final int DIAMETRO = 30;
    public static final int RADIO = DIAMETRO / 2;
    public static final int ANCHO = 50;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (objArbol != null) {
            pintar(g, getWidth() / 2, 20, objArbol, getWidth() / 4);
        }
    }

    public void setObjArbol(ArbolBinario objArbol) {
        this.objArbol = objArbol;
        repaint();
    }

    public static void mostrarArbol(JDesktopPane desktopPane, ArbolBinario arbol) {
          JInternalFrame  frame = new JInternalFrame ("Visualización del Árbol Binario", true, true, true, true);
        
     //   JFrame frame = new JFrame("Visualización del Árbol Binario");
        Lienzo arbolGUI = new Lienzo();
        frame.add(arbolGUI);
        frame.setContentPane(arbolGUI);
        frame.setSize(1200, 625);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        desktopPane.add(frame);
        frame.setVisible(true);
        arbolGUI.setObjArbol(arbol);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    private void pintar(Graphics g, int x, int y, ArbolBinario n, int desplazamiento) {
        if (n == null) {
            return;
        }

        /* Dibuja el nodo actual*/
        g.drawOval(x - RADIO, y, DIAMETRO, DIAMETRO);
        g.drawString(n.getValor().toString(), x - RADIO + 12, y + 18);

        /* Si existe un hijo izquierdo, dibuja la línea hacia él y luego el hijo izquierdo*/
        if (n.getHijoIzquierdo() != null) {
            g.drawLine(x - RADIO, y + RADIO, x - desplazamiento - RADIO, y + ANCHO - RADIO);
            pintar(g, x - desplazamiento, y + ANCHO, n.getHijoIzquierdo(), desplazamiento / 2);
        }

        /* Si existe un hijo derecho, dibuja la línea hacia él y luego el hijo derecho*/
 /**/
        if (n.getHijoDerecho() != null) {
            g.drawLine(x + RADIO, y + RADIO, x + desplazamiento + RADIO, y + ANCHO - RADIO);
            pintar(g, x + desplazamiento, y + ANCHO, n.getHijoDerecho(), desplazamiento / 2);
        }
    }
}
