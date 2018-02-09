import java.util.*;
import java.lang.*;

public class MenuAlumnos{
	
	static void mostrarMenu() {
		
		System.out.println("Introduzca la opción del menú: ");
		System.out.println("1 para dar de alta alumnos");
		System.out.println("2 para dar de baja alumnos");
		System.out.println("3 para listar a los alumnos");
		System.out.println("4 para modificar alumnos");
		System.out.println("5 para matricular alumnos");
		System.out.println("6 para dar de baja una asignatura");
		System.out.println("7 para introducir una calificación trimestral");
		System.out.println("8 para listar las calificaciones de un alumno");
		System.out.println("9 para poner una falta de un día completo");
		System.out.println("10 para poner una falta en una sesión");
		System.out.println("11 para pasar lista");
		System.out.println("12 para listar faltas");
		System.out.println("13 para salir");
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		
		boolean salir = false;
		int opcion;
		
		do {
			
			mostrarMenu();
			
			opcion = entrada.nextInt();
		
			switch(opcion) {
			
			case 1:
				
				
				
				break;
				
			case 2:
				
				
				
				break;
				
			case 3:
				
				
				
				break;
				
			case 4:
				
				
						
				break;
						
			case 5:
				
				
				
				break;
				
			case 6:
				
				
				
				break;
				
			case 7:
				
				
				
				break;
				
			case 8:
				
				
				
				break;
				
			case 9:
				
				
				
				break;
				
			case 10:
				
				
				
				break;
				
			case 11:
				
				
				
				break;
				
			case 12:
				
				
				
				break;
				
			case 13:
				
				System.out.println("Has salido");
				
				salir = true;
				
				break;
			
			default:
				
				if(opcion < 1 || opcion > 13)
				
					throw new Exception("Error. Opción incorrecta");
				
				break;
			}
		
		}while(!salir);

	}
	
}
