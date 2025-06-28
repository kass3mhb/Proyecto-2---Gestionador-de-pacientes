package Estructuras;

public class BinarySearchTree {
    Node root; // La raíz del BST

    public BinarySearchTree() {
        root = null;
    }

    // Método principal para insertar un nuevo nodo.
    public void insert(int key) {
        root = recursiveInsert(root, key);
    }

    // Método recursivo para insertar un nuevo nodo en el BST.
    // Los valores menores van a la izquierda y los mayores a la derecha.
    private Node recursiveInsert(Node current, int key) {
        // Si el árbol está vacío, el nuevo nodo se convierte en la raíz
        if (current == null) {
            return new Node(key);
        }

        // Si la clave a insertar es menor que la clave del nodo actual,
        // se inserta en el subárbol izquierdo.
        if (key < current.key) {
            current.left = recursiveInsert(current.left, key);
        }
        // Si la clave a insertar es mayor que la clave del nodo actual,
        // se inserta en el subárbol derecho.
        else if (key > current.key) {
            current.right = recursiveInsert(current.right, key);
        }
        
        // Si la clave ya existe, no hacemos nada (No se permite duplicidad).

        return current;
    }

    // Método principal para eliminar un nodo.
    public void delete(int key) {
        root = recursiveDelete(root, key);
    }

    // Método recursivo para eliminar un nodo del BST.
    private Node recursiveDelete(Node current, int key) {
        // Caso base: Si el árbol está vacío o el nodo no se encuentra
        if (current == null) {
            return current;
        }

        // Recorrer el árbol para encontrar el nodo a eliminar
        if (key < current.key) {
            current.left = recursiveDelete(current.left, key);
        } else if (key > current.key) {
            current.right = recursiveDelete(current.right, key);
        } 
        

        else {
            // El nodo a eliminar es 'current'

            // Caso 1: Nodo con cero o un hijo
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            // Caso 2: Nodo con dos hijos
            // Obtener el sucesor inorden (el nodo más pequeño en el subárbol derecho)
            current.key = minValue(current.right);

            // Eliminar el sucesor inorden
            current.right = recursiveDelete(current.right, current.key);
        }
        return current;
    }

    // Encuentra el valor más pequeño en un subárbol
    private int minValue(Node node) {
        int minv = node.key;
        while (node.left != null) {
            minv = node.left.key;
            node = node.left;
        }
        return minv;
    }

    // Método principal para buscar un nodo.
    public boolean search(int key) {
        return recursiveSearch(root, key);
    }

    // Método recursivo para buscar un nodo en el BST.
    // Aprovecha la propiedad de orden del BST.
    private boolean recursiveSearch(Node current, int key) {
        // Caso base: Si el árbol está vacío o la clave se encuentra en la raíz
        if (current == null || current.key == key) {
            return current != null; // True si se encontró, false si current es null
        }

        // Si la clave es menor que la clave de la raíz, buscar en el subárbol izquierdo
        if (key < current.key) {
            return recursiveSearch(current.left, key);
        }
        // Si la clave es mayor que la clave de la raíz, buscar en el subárbol derecho
        else {
            return recursiveSearch(current.right, key);
        }
    }

    // Recorrido Inorden (Izquierda -> Raíz -> Derecha)
    // Para un BST, un recorrido inorden produce los elementos en orden ascendente.
    public void inorder() {
        System.out.print("Inorden BST: ");
        recursiveInorder(root);
        System.out.println();
    }

    private void recursiveInorder(Node current) {
        if (current != null) {
            recursiveInorder(current.left);
            System.out.print(current.key + " ");
            recursiveInorder(current.right);
        }
    }
    
    // Recorrido Inorden Inverso (Derecha -> Raíz -> Izquierda)
    // Para un BST, un recorrido inorden inverso produce los elementos en orden descendente.
    public void reverseInorder() {
        System.out.print("Inorden Inverso BST: ");
        reverseRecursiveInorder(root);
        System.out.println();
    }

    private void reverseRecursiveInorder(Node current) {
        if (current != null) {
            reverseRecursiveInorder(current.right);
            System.out.print(current.key + " ");
            reverseRecursiveInorder(current.left);
        }
    }
}
