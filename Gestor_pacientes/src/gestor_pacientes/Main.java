package gestor_pacientes;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        menu();
    }
 
    // metetodos de interfaz
    private static void menu() { // menu principal
        Scanner scanner = new Scanner (System.in);
        int opcion = 0;
        do {         
        System.out.println("Bienvenido a la Clinica");
        System.out.println("1. Gestionar Registro Histórico de Expedientes.");
        System.out.println("2. Gestionar Cola de Atención Activa.");
        System.out.println("3. Salir del sistema.");
        System.out.println("");
        System.out.print("Seleccione una opcion: ");
        
        opcion =  Integer.parseInt(scanner.nextLine()); // <- terminar de validarlo 
          switch (opcion) {
              case 1:
                  System.out.println("Gestionar Registro Histórico de Expedientes.");
                  
                  break;
              case 2:
                  System.out.println("Gestionar Cola de Atención Activa.");
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
}




