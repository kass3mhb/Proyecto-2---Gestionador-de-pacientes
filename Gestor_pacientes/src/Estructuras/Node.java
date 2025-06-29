package Estructuras;

public class Node {
    Paciente paciente;
    Node left, right;

    public Node(Paciente paciente) {
        this.paciente = paciente;
        left = right = null;
    }
}
