package org.example;

public class AVLNode {
    int value;
    int height; // Altura del nodo en el subárbol
    AVLNode left;
    AVLNode right;

    public AVLNode(int value) {
        this.value = value;
        this.height = 1; // Un nodo recién insertado (hoja) tiene altura 1
        this.left = null;
        this.right = null;
    }
}
