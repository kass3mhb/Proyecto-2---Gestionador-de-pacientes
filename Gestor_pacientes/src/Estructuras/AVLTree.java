package Estructuras;

public class AVLTree {
    
    static class NodeAVLTree {
        Paciente paciente;
        int height; // Altura del nodo (distancia desde este nodo a la hoja más lejana)
        NodeAVLTree left, right;

        public NodeAVLTree(Paciente paciente) {
            this.paciente = paciente;
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
    public void insert(Paciente paciente) {
        root = recursiveInsert(root, paciente);
    }

    // Método recursivo para insertar un nodo y rebalancear el árbol AVL.
    private NodeAVLTree recursiveInsert(NodeAVLTree current, Paciente paciente) {
        // 1. Realiza la inserción normal de BST
        if (current == null) {
            return new NodeAVLTree(paciente);
        }

        if (paciente.getId() < current.paciente.getId()) {
            current.left = recursiveInsert(current.left, paciente);
        } else if (paciente.getId()> current.paciente.getId()) {
            current.right = recursiveInsert(current.right, paciente);
        } else { // Claves duplicadas no permitidas en este ejemplo
            return current;
        }

        // 2. Actualiza la altura del nodo actual
        current.height = 1 + max(height(current.left), height(current.right));

        // 3. Obtiene el factor de equilibrio de este nodo para verificar si se desbalanceó
        int balance = getBalance(current);

        // 4. Si el nodo se desbalanceó, hay 4 casos:

        // Caso Izquierda-Izquierda (LL)
        if (balance > 1 && paciente.getId() < current.left.paciente.getId()) {
            return rightRotate(current);
        }

        // Caso Derecha-Derecha (RR)
        if (balance < -1 && paciente.getId() > current.right.paciente.getId()) {
            return leftRotate(current);
        }

        // Caso Izquierda-Derecha (LR)
        if (balance > 1 && paciente.getId() > current.left.paciente.getId()) {
            current.left = leftRotate(current.left);
            return rightRotate(current);
        }

        // Caso Derecha-Izquierda (RL)
        if (balance < -1 && paciente.getId() < current.right.paciente.getId()) {
            current.right = rightRotate(current.right);
            return leftRotate(current);
        }

        // Retorna el nodo (sin cambios si está balanceado)
        return current;
    }
    
    // Recorrido Inorden (Izquierda -> Raíz -> Derecha)
    public void inorder() {
        System.out.println("Mostrando cola actual: ");
        recursiveInorder(root);
        System.out.println();
    }

    private void recursiveInorder(NodeAVLTree current) {
        if (current != null) {
            recursiveInorder(current.left);
            System.out.println(current.paciente + " ");
            recursiveInorder(current.right);
        }
    }
    
    // Método principal para buscar un nodo.
    public Paciente search(Paciente paciente) {
        return recursiveSearch(root, paciente.getId());
    }
    
    // Método recursivo para buscar un nodo en el arbol AVL.
    // Aprovecha la propiedad de orden del arbol AVL.
    private Paciente recursiveSearch(NodeAVLTree current, int id) {
        // Caso base: Si el árbol está vacío o la clave se encuentra en la raíz
        if (current == null) {
            return null; // no se encontro
        } 
        
        if (current.paciente.getId() == id) { // se encontro
            return current.paciente;
        }
       
        // Si la clave es menor que la clave de la raíz, buscar en el subárbol izquierdo
        if (id < current.paciente.getId()) {
            return recursiveSearch(current.left, id);
        }
        // Si la clave es mayor que la clave de la raíz, buscar en el subárbol derecho
        else {
            return recursiveSearch(current.right, id);
        }
    }
    
    public void delete(Paciente paciente) {
        root = recursiveDelete(root, paciente.getId());
    }

    private NodeAVLTree recursiveDelete(NodeAVLTree current, int id) {
        if (current == null) {
            return null;
        }

        // Buscar el nodo a eliminar
        if (id < current.paciente.getId()) {
            current.left = recursiveDelete(current.left, id);
        } else if (id > current.paciente.getId()) {
            current.right = recursiveDelete(current.right, id);
        } else {
            // Nodo encontrado

            // Caso 1: Un solo hijo o ninguno
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            // Caso 2: Dos hijos
            NodeAVLTree sucesor = minValue(current.right);
            current.paciente = sucesor.paciente; // Reemplazar con el paciente sucesor
            current.right = recursiveDelete(current.right, sucesor.paciente.getId());
        }

        // Actualizar altura
        current.height = 1 + max(height(current.left), height(current.right));

        // Rebalancear
        int balance = getBalance(current);

        // Casos de desbalanceo
        if (balance > 1 && getBalance(current.left) >= 0)
            return rightRotate(current);
        if (balance > 1 && getBalance(current.left) < 0) {
            current.left = leftRotate(current.left);
            return rightRotate(current);
        }
        if (balance < -1 && getBalance(current.right) <= 0)
            return leftRotate(current);
        if (balance < -1 && getBalance(current.right) > 0) {
            current.right = rightRotate(current.right);
            return leftRotate(current);
        }

        return current;
    }

    // Encuentra el nodo con el menor ID (usado como sucesor inorden)
    private NodeAVLTree minValue(NodeAVLTree node) {
        NodeAVLTree current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
}

