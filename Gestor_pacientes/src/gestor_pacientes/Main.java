package gestor_pacientes;

import java.util.Scanner;
import Estructuras.Paciente;
import Estructuras.BinarySearchTree;
import Estructuras.AVLTree;

public class Main {
    static BinarySearchTree historial = new BinarySearchTree();
    static AVLTree cola = new AVLTree();
    static int expedienteId = 0;
    
    public static void main(String[] args) {
        menu();
    }
 
    // metetodos de interfaz
    private static void menu() { // menu principal
        Scanner scanner = new Scanner (System.in);
        int opcion = 0;
        do {         
        System.out.println("Bienvenido a la Clinica");
        System.out.println("1. Gestionar registro histórico de expedientes.");
        System.out.println("2. Gestionar cola de atención activa.");
        System.out.println("3. Salir del sistema.");
        System.out.println("");
        System.out.print("Seleccione una opcion: ");
        
        try {
            opcion = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.print("Elija un valor válido.");
            opcion = -1; 
        }
        
          switch (opcion) {
              case 1:
                  System.out.println("Usted ha seleccionado: Gestionar registro histórico de expedientes.");
                  menuExpedientes();
                  break;
              case 2:
                  System.out.println("2. Gestionar cola de atención activa.");
                  menuCola();
                  break;              
              case 3:
                  System.out.println("Ha salido del sistema.");
                  break;                  
              default:
                  System.out.println("(Entrada numerica del 1 al 3)\n");
                  break;
                  }
        } while (opcion != 3);
    }
    
    private static void menuExpedientes() {
    Scanner scanner = new Scanner (System.in);
        int opcion = 0;
        do {         
        System.out.println("1. Registrar un nuevo expediente.");
        System.out.println("2. Buscar un registro.");
        System.out.println("3. Listar todos los registros.");
        System.out.println("4. Regresar al menu principal.");
        System.out.print("Seleccione una opcion: ");
        try {
            opcion = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Elija un valor válido.\n");
            opcion = -1;
        }
        
        switch (opcion) {
            case 1:
                registrarPaciente();
                break;
            case 2:
                buscarRegistro();
                break;              
            case 3:
                historial.inorder();
                break;                  
            case 4:
                menu();
                break;  
            default:
                System.out.print(" (Entrada numerica del 1 al 4)\n");
                break;
                }
        } while (opcion != 4);
    }
    
   private static void registrarPaciente() {
        Scanner scanner = new Scanner (System.in);
        expedienteId++;
        System.out.println("[Formulario] registrar nuevo paciente.");
        //aqui vamos a registrar todos los datos del paciente, nombre, sexo, edad, fecha de nacimiento, motivo, diagnostico, prioridad (el id se autoasigna)
        String nombre;
        do {
            System.out.print("Ingrese el nombre completo del paciente: ");
            nombre = scanner.nextLine().trim();
        } while (nombre.isEmpty());

        String sexo;
        do {
            System.out.print("Ingrese el sexo del paciente (H/M): ");
            sexo = scanner.nextLine().trim().toUpperCase();
        } while (!sexo.equals("H") && !sexo.equals("M"));

        int edad = -1;
        while (edad < 0) {
            try {
                System.out.print("Ingrese la edad del paciente: ");
                edad = Integer.parseInt(scanner.nextLine());
                if (edad < 0) System.out.println("Edad inválida.");
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }

        String fechaNacimiento;
        do {
            System.out.print("Ingrese la fecha de nacimiento: ");
            fechaNacimiento = scanner.nextLine().trim();
        } while (fechaNacimiento.isEmpty());

        String motivo;
        do {
            System.out.print("Ingrese el motivo de la consulta: ");
            motivo = scanner.nextLine().trim();
        } while (motivo.isEmpty());

        String diagnostico;
        do {
            System.out.print("Ingrese el diagnóstico: ");
            diagnostico = scanner.nextLine().trim();
        } while (diagnostico.isEmpty());

        String prioridad;
        do {
            System.out.print("Ingrese la prioridad (baja, media o alta): ");
            prioridad = scanner.nextLine().trim().toLowerCase();
        } while (!prioridad.equals("baja") && !prioridad.equals("media") && !prioridad.equals("alta"));

        // con estos datos recopilados, montamos la clase
        Paciente paciente = new Paciente (expedienteId, nombre, sexo, edad, fechaNacimiento, motivo, diagnostico, prioridad);
        System.out.println(paciente.toString()); 
        // y en base a ese paciente registrado, lo guardamos en el arbol
        historial.insert(paciente);
        System.out.println("Paciente registrado correctamente:");
        }

        private static void buscarRegistro() {
        Scanner scanner = new Scanner (System.in);   
        int idAsignado = -1;
        while (idAsignado < 0) {
            try {
                System.out.print("Ingrese el id del registro del paciente: ");
                idAsignado = Integer.parseInt(scanner.nextLine());
                if (idAsignado < 0) {
                    System.out.println("ID inválido. Debe ser un número positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }

        Paciente pacienteTemporal = new Paciente(idAsignado, "", "", 0, "", "", "", "");
        Paciente Encontrado = historial.search(pacienteTemporal);

        // no le podemos meter un id, porque espera una clase paciente.

        if (Encontrado != null){        
            historial.search(Encontrado);
            System.out.println("Paciente encontrado.");
            System.out.println(Encontrado);
        }
        else {
            System.out.println("paciente no encontrado.");
        }
    }
        
    private static void menuCola() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("Usted ha seleccionado: Gestionar Cola de Atención Activa.");
            System.out.println("1. Agregar paciente a la cola.");
            System.out.println("2. Buscar un paciente por su id en la cola.");
            System.out.println("3. Ver cola actual.");
            System.out.println("4. Eliminar paciente de la cola.");
            System.out.println("5. Regresar al menú principal.");
            System.out.print("Seleccione una opción: ");

            try {
            opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Elija un valor válido.\n");
                opcion = -1; // evita que entre en cualquier case válido
            }

            switch (opcion) {
                case 1:
                    agregarPacienteCola();
                    break;
                case 2:
                    buscarPacienteCola();
                    break;
                case 3:
                    cola.inorder();
                    break;
                case 4:
                    eliminarPacienteCola(); 
                    break;
                case 5:
                    menu(); 
                    break;                    
                    
                default:
                    System.out.println(" (Entrada numerica del 1 al 5)\n");
                    break;
            }
        } while (opcion != 4);
    }
    
    private static void agregarPacienteCola() {
    Scanner scanner = new Scanner (System.in);   
    int idAsignado = -1;

    while (idAsignado < 0) {
        try {
            System.out.print("Ingrese el id del paciente que quiere agregar a la cola: ");
            idAsignado = Integer.parseInt(scanner.nextLine());
            if (idAsignado < 0) {
                System.out.println("ID inválido. Debe ser un número positivo.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número entero válido.");
        }
    }

    Paciente pacienteTemporal = new Paciente(idAsignado, "", "", 0, "", "", "", "");
    Paciente Encontrado = historial.search(pacienteTemporal);
        if (Encontrado != null){        
            cola.insert(Encontrado);
            System.out.println("Paciente agregado a la cola.");
            System.out.println(Encontrado);
        }
        else {
            System.out.println("Paciente no encontrado en el historial.");
        }
    }
    
    private static void buscarPacienteCola() {
        Scanner scanner = new Scanner (System.in);   
        int idAsignado = -1;

        while (idAsignado < 0) {
            try {
                System.out.print("Ingrese el id del registro del paciente: ");
                idAsignado = Integer.parseInt(scanner.nextLine());
                if (idAsignado < 0) {
                    System.out.println("ID inválido. Debe ser un número positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }

    Paciente pacienteTemporal = new Paciente(idAsignado, "", "", 0, "", "", "", "");
    Paciente Encontrado = cola.search(pacienteTemporal);
        if (Encontrado != null){        
            cola.search(Encontrado);
            System.out.println("Paciente en la cola encontrado.");
            System.out.println(Encontrado);
        }
        else {
            System.out.println("paciente inexistente en la cola.");
        }
    }
    
    private static void eliminarPacienteCola() {
        Scanner scanner = new Scanner (System.in);   
        int idAsignado = -1;

        while (idAsignado < 0) {
            try {
                System.out.print("Ingrese el id del registro del paciente: ");
                idAsignado = Integer.parseInt(scanner.nextLine());
                if (idAsignado < 0) {
                    System.out.println("ID inválido. Debe ser un número positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }
    Paciente pacienteTemporal = new Paciente(idAsignado, "", "", 0, "", "", "", "");
    Paciente Encontrado = cola.search(pacienteTemporal);
        if (Encontrado != null){        
            cola.delete(Encontrado);
            System.out.println("Paciente en la cola eliminado.");
        }
        else {
            System.out.println("paciente inexistente en la cola.");
        }
    }
}




