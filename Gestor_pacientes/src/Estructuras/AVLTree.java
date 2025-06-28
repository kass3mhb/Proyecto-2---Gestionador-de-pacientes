package Estructuras;

public class AVLTree {
    static class NodeAVLTree {
        int key;
        int height; // Altura del nodo (distancia desde este nodo a la hoja más lejana)
        NodeAVLTree left, right;

        public NodeAVLTree(int item) {
            key = item;
            height = 1; // Un nuevo nodo es una hoja, por lo que su altura inicial es 1
            left = right = null;
        }
    }

    NodeAVLTree root; // La raíz del árbol AVL


    public AVLTree() {
        root = null;
    }

    // Métodos auxiliares para AVL

    // Obtiene la altura de un nodo
    private int height(NodeAVLTree N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }

    // Obtiene el máximo de dos enteros
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Realiza una rotación a la derecha
    //      y                               x
    //     / \                             / \
    //    x   T3   ---> Rotación Derecha  T1  y
    //   / \                                 / \
    //  T1  T2                              T2  T3
    private NodeAVLTree rightRotate(NodeAVLTree y) {
        NodeAVLTree x = y.left;
        NodeAVLTree T2 = x.right;

        // Realiza la rotación
        x.right = y;
        y.left = T2;

        // Actualiza las alturas
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Retorna la nueva raíz
        return x;
    }

    // Realiza una rotación a la izquierda
    //    x                                y
    //   / \                              / \
    //  T1  y   ---> Rotación Izquierda  x  T3
    //     / \                          / \
    //    T2  T3                       T1  T2
    private NodeAVLTree leftRotate(NodeAVLTree x) {
        NodeAVLTree y = x.right;
        NodeAVLTree T2 = y.left;

        // Realiza la rotación
        y.left = x;
        x.right = T2;

        // Actualiza las alturas
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Retorna la nueva raíz
        return y;
    }

    // Obtiene el factor de equilibrio de un nodo (altura subárbol izquierdo - altura subárbol derecho)
    private int getBalance(NodeAVLTree N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }

    // Método principal para insertar un nuevo nodo.
    public void insert(int key) {
        root = recursiveInsert(root, key);
    }

    // Método recursivo para insertar un nodo y rebalancear el árbol AVL.
    private NodeAVLTree recursiveInsert(NodeAVLTree current, int key) {
        // 1. Realiza la inserción normal de BST
        if (current == null) {
            return new NodeAVLTree(key);
        }

        if (key < current.key) {
            current.left = recursiveInsert(current.left, key);
        } else if (key > current.key) {
            current.right = recursiveInsert(current.right, key);
        } else { // Claves duplicadas no permitidas en este ejemplo
            return current;
        }

        // 2. Actualiza la altura del nodo actual
        current.height = 1 + max(height(current.left), height(current.right));

        // 3. Obtiene el factor de equilibrio de este nodo para verificar si se desbalanceó
        int balance = getBalance(current);

        // 4. Si el nodo se desbalanceó, hay 4 casos:

        // Caso Izquierda-Izquierda (LL)
        if (balance > 1 && key < current.left.key) {
            return rightRotate(current);
        }

        // Caso Derecha-Derecha (RR)
        if (balance < -1 && key > current.right.key) {
            return leftRotate(current);
        }

        // Caso Izquierda-Derecha (LR)
        if (balance > 1 && key > current.left.key) {
            current.left = leftRotate(current.left);
            return rightRotate(current);
        }

        // Caso Derecha-Izquierda (RL)
        if (balance < -1 && key < current.right.key) {
            current.right = rightRotate(current.right);
            return leftRotate(current);
        }

        // Retorna el nodo (sin cambios si está balanceado)
        return current;
    }

    // Recorrido Inorden (Izquierda -> Raíz -> Derecha)
    public void inorder() {
        System.out.print("Inorden AVL: ");
        recursiveInorder(root);
        System.out.println();
    }

    private void recursiveInorder(NodeAVLTree current) {
        if (current != null) {
            recursiveInorder(current.left);
            System.out.print(current.key + " ");
            recursiveInorder(current.right);
        }
    }
}
