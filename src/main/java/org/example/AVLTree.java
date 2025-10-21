package org.example;

public class AVLTree {
    AVLNode root;

    // --- MÉTODOS AUXILIARES ---

    // Obtiene la altura del nodo. Retorna 0 si el nodo es nulo.
    private int getHeight(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    // Actualiza la altura del nodo actual (Max(h_izq, h_der) + 1).
    private void updateHeight(AVLNode node) {
        if (node != null) {
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }
    }

    // Calcula el Factor de Balance (FB = Altura_Izq - Altura_Der).
    public int getBalanceFactor(AVLNode node) {
        return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }


    // --- MÉTODOS DE ROTACIÓN ---

    // Rotación Simple Derecha (LL y parte de LR)
    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Realizar rotación
        x.right = y;
        y.left = T2;

        // Actualizar alturas (de abajo hacia arriba)
        updateHeight(y);
        updateHeight(x);

        // Retorna la nueva raíz
        return x;
    }

    // Rotación Simple Izquierda (RR y parte de RL)
    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Realizar rotación
        y.left = x;
        x.right = T2;

        // Actualizar alturas (de abajo hacia arriba)
        updateHeight(x);
        updateHeight(y);

        // Retorna la nueva raíz
        return y;
    }


    // --- MÉTODO DE INSERCIÓN Y EQUILIBRIO ---

    // Método principal de inserción
    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    // Método recursivo que inserta y realiza las rotaciones
    private AVLNode insertRecursive(AVLNode node, int value) {
        // 1. Inserción estándar de BST
        if (node == null) {
            return new AVLNode(value);
        }

        if (value < node.value) {
            node.left = insertRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = insertRecursive(node.right, value);
        } else {
            return node; // No permite valores duplicados
        }

        // 2. Actualizar la altura del nodo actual
        updateHeight(node);

        // 3. Obtener el Factor de Balance (FB) para verificar el equilibrio
        int balance = getBalanceFactor(node);

        // 4. Realizar Rotaciones si hay desequilibrio (balance > 1 o balance < -1)

        // Caso LL (Left Left - Desequilibrio en subárbol izquierdo, e inserción izquierda)
        if (balance > 1 && value < node.left.value) {
            System.out.println("-> Desequilibrio en " + node.value + ". Aplicando Rotación Simple Derecha (LL)");
            return rotateRight(node);
        }

        // Caso RR (Right Right - Desequilibrio en subárbol derecho, e inserción derecha)
        if (balance < -1 && value > node.right.value) {
            System.out.println("-> Desequilibrio en " + node.value + ". Aplicando Rotación Simple Izquierda (RR)");
            return rotateLeft(node);
        }

        // Caso LR (Left Right - Desequilibrio Izquierda, inserción Derecha)
        if (balance > 1 && value > node.left.value) {
            System.out.println("-> Desequilibrio en " + node.value + ". Aplicando Rotación Doble (LR)");
            node.left = rotateLeft(node.left); // 1er paso: Rotación Izquierda
            return rotateRight(node);          // 2do paso: Rotación Derecha
        }

        // Caso RL (Right Left - Desequilibrio Derecha, inserción Izquierda)
        if (balance < -1 && value < node.right.value) {
            System.out.println("-> Desequilibrio en " + node.value + ". Aplicando Rotación Doble (RL)");
            node.right = rotateRight(node.right); // 1er paso: Rotación Derecha
            return rotateLeft(node);           // 2do paso: Rotación Izquierda
        }

        // Retorna el nodo (si está equilibrado o después de la rotación)
        return node;
    }


    // --- MÉTODOS DE IMPRESIÓN Y VALIDACIÓN ---

    // Imprime el recorrido InOrden (ordenado) y el Factor de Balance de cada nodo.
    public void inOrderWithBalance() {
        System.out.println("\n--- Recorrido InOrden con Factor de Balance ---");
        inOrder(root);
        System.out.println("\n----------------------------------------------");
    }

    private void inOrder(AVLNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.printf("Valor: %d (Altura: %d, FB: %d)\n", node.value, node.height, getBalanceFactor(node));
            inOrder(node.right);
        }
    }

    public void displayTreeVisual() {
        System.out.println("----------------------------------------------");
        printTree(root, "", true);
        System.out.println("----------------------------------------------");
    }

    private void printTree(AVLNode node, String prefix, boolean isTail) {
        if (node.right != null) {

            printTree(node.right, prefix + (isTail ? "│   " : "    "), false);
        }


        System.out.println(prefix + (isTail ? "└── " : "┌── ") + node.value);

        if (node.left != null) {

            printTree(node.left, prefix + (isTail ? "│   " : "    "), true);
        }
    }
}
