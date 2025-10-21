package org.example;
public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        int[] valores = {30, 20, 40, 10, 25, 5, 35};

        System.out.println("--- PRUEBA DE ÁRBOL AVL CON VALORES ESPECÍFICOS ---");

        for (int valor : valores) {
            System.out.printf("\n>>> Insertando valor: %d\n", valor);
            tree.insert(valor);
        }

        System.out.println("\n--- RESULTADO FINAL DEL ÁRBOL ---");

        // Imprime el recorrido InOrden y valida el Factor de Balance de cada nodo.
        tree.inOrderWithBalance();

        tree.displayTreeVisual();
    }
}