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
        
        opcion =  Integer.parseInt(scanner.nextLine()); // <- terminar de validarlo 
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
                  System.out.println("opcion invalida.");
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
        
        opcion =  Integer.parseInt(scanner.nextLine()); 
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
                    System.out.println("opcion invalida.");
                  break;
                  }
        } while (opcion != 4);
    }
    
   private static void registrarPaciente() {
        Scanner scanner = new Scanner (System.in);
        expedienteId++;
        System.out.println("[Formulario] registrar nuevo paciente.");
        //aqui vamos a registrar todos los datos del paciente, nombre, sexo, edad, fecha de nacimiento, motivo, diagnostico, prioridad (el id se autoasigna)
        System.out.println("Ingrese el nombre completo del paciente a registrar.");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el sexo del paciente a registrar. (Ingrese los caracteres H o M)");
        String sexo = scanner.nextLine();

        System.out.println("Ingrese la edad del paciente a registrar.");
        int edad = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese la fecha de nacimiento del paciente a registrar.");
        String fechaNacimiento = scanner.nextLine();
        // aqui hay que validarlo bien por dia, mes y año

        System.out.println("Ingrese el motivo por el cual acude el paciente a registrar.");
        String motivo = scanner.nextLine();

        System.out.println("Ingrese el diagnostico del paciente a registrar. (puede editarse despues)");
        String diagnostico = scanner.nextLine();   

        System.out.println("Ingrese la prioridad del paciente a registrar. (Baja, media o alta)");
        String prioridad = scanner.nextLine();  

        // con estos datos recopilados, montamos la clase
        Paciente paciente = new Paciente (expedienteId, nombre, sexo, edad, fechaNacimiento, motivo, diagnostico, prioridad);
        System.out.println(paciente.toString()); 
        // y en base a ese paciente registrado, lo guardamos en el arbol
        historial.insert(paciente);
        }

        private static void buscarRegistro() {
        Scanner scanner = new Scanner (System.in);   
        System.out.print("Ingrese el id del registro del paciente: ");
        int idAsignado = Integer.parseInt(scanner.nextLine());

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

            opcion = Integer.parseInt(scanner.nextLine());

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
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (opcion != 4);
    }
    
    private static void agregarPacienteCola() {
    Scanner scanner = new Scanner (System.in);   
        System.out.print("Ingrese el id del paciente que quiere agregar a la cola: ");
        int idAsignado = Integer.parseInt(scanner.nextLine());

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
        System.out.print("Ingrese el id del registro del paciente: ");
        int idAsignado = Integer.parseInt(scanner.nextLine());

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
        System.out.print("Ingrese el id del registro del paciente: ");
        int idAsignado = Integer.parseInt(scanner.nextLine());

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




