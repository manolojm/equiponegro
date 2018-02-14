
import java.util.*;
import java.lang.*;

public class MenuAlumnos{
	
	/*** Menú ***/
	public static void mostrarMenu() {
		
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
	
	
	/*** Metodo 3: Listar Alumnos - Manolo ***/
	public static void listarAlumnos(ArrayList<Alumno> listaAlumnos) {
		
		// Ciclo para sacar los alumnos
		for (int i = 0; i < listaAlumnos.size(); i++) {
			
			Alumno alumnoElegido = listaAlumnos.get(i);
			
			// Mostrar Alumno
			System.out.println("Numero : " + i + " / DNI: " + alumnoElegido.getDni() + " / Nombre: " 
			+ alumnoElegido.getNombre() + " / Apellidos: " + alumnoElegido.getApellidos() 
			+ " \n/ Telefono: " + alumnoElegido.getTelefono() + " / Email: " + alumnoElegido.getEmail());		
		}
	}
	
	/*** Metodo 4: Modificar Alumnos - Manolo ***/
	public static void modificarAlumnos(ArrayList<Alumno> listaAlumnos) {
		
		Scanner entrada = new Scanner(System.in);	
		int numerote = 1;

		do {

			int alumnoModifico, numerito;

			System.out.println("Alumno a modificar: ");
			alumnoModifico = entrada.nextInt();

			// Buscamos al alumno elegido
			Alumno alumnoModificar = listaAlumnos.get(alumnoModifico);

			do {
				
				System.out.println(
						"Que desea modificar? \n1 - DNI \n2 - Nombre" + "\n3 - Apellidos \n4 - Telefono \n5 - Email \n6 - Nada");
				numerito = entrada.nextInt();

				switch (numerito) {

				case 1: // DNI

					String nuevoDni;

					System.out.println("Introduzca nuevo DNI: ");
					nuevoDni = entrada.next();

					alumnoModificar.setDni(nuevoDni); // Modificamos DNI

					break;

				case 2: // Nombre

					String nuevoNombre;

					System.out.println("Introduzca nuevo Nombre: ");
					nuevoNombre = entrada.next();

					alumnoModificar.setNombre(nuevoNombre); // Modificamos Nombre

					break;

				case 3: // Apellidos

					String nuevoApellidos;

					System.out.println("Introduzca nuevos Apellidos: ");
					nuevoApellidos = entrada.next();

					alumnoModificar.setApellidos(nuevoApellidos); // Modificamos Apellidos

					break;

				case 4: // Telefono

					String nuevoTelefono;

					System.out.println("Introduzca nuevo Telefono: ");
					nuevoTelefono = entrada.next();

					alumnoModificar.setTelefono(nuevoTelefono); // Modificamos Telefono

					break;

				case 5: // Email

					String nuevoEmail;

					System.out.println("Introduzca nuevo Email: ");
					nuevoEmail = entrada.next();

					alumnoModificar.setEmail(nuevoEmail); // Modificamos Email

					break;

				case 6: // Salir

					System.out.println("Salir");

					break;

				default: // Por defecto

					System.out.println("Opcion erronea");
				}

			} while (numerito != 6);
			
			do {
				System.out.println("Desea modificar otro alumno? (1 - Si / 2 - No)");
				numerote = entrada.nextInt();
			} while (numerote > 2 || numerote < 1);
			
		} while (numerote == 1);
	}

	//Metodo para dar alta - Autor: Alejandro Fandila Cano.
	public static void darAlta(ArrayList<Alumno> lista) {
		
		Scanner entrada = new Scanner(System.in);
		
		Alumno nuevo = new Alumno(null);
		
		System.out.println("Introduzca el nombre del alumno:");
		nuevo.setNombre(entrada.nextLine());
		
		System.out.println("Introduzca los apellidos del alumno:");
		nuevo.setApellidos(entrada.nextLine());
		
		System.out.println("Introduzca el dni del alumno:");
		nuevo.setDni(entrada.nextLine());
		
		System.out.println("Introduzca el numero de telefono del alumno:");
		nuevo.setTelefono(entrada.nextLine());
		
		System.out.println("Introduzca el email del alumno:");
		nuevo.setEmail(entrada.nextLine());
		
		lista.add(nuevo);
		
	}
	
	//Metodo dar de baja - Autor: Alejandro Fandila Cano.
	public static void darBaja(ArrayList<Alumno> lista) {
		
		boolean numero;
		int seleccionar = -1;
		
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("¿Introduzca a quien desea dar de baja?:");
		MenuAlumnos.listarAlumnos(lista);
		
		//En caso de que no se introduzca correctamente, el metodo no hara nada.
		do {
			try {
				numero = true;
				seleccionar = entrada.nextInt();
				lista.remove(seleccionar);
			}catch(InputMismatchException ex) {
					numero = false;
					
					System.out.println("No se ha introducido ningun numero.");
			}catch(IndexOutOfBoundsException ex2) {
				System.out.println("El alumno "+seleccionar+" no existe.");
			}
			
		}while(numero = false);	
		
		System.out.println("\n");
		
	}
	
	/*** Main ***/
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		
		// Variables
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		boolean salir = false;
		int opcion;
		
		// Pruebas
		Alumno alumno1 = new Alumno("12345X", "Antonio", "Lopez");
		listaAlumnos.add(alumno1);
		
		do {
			
			mostrarMenu();
			
			opcion = entrada.nextInt();
		
			switch(opcion) {
			
			case 1:
				
				MenuAlumnos.darAlta(listaAlumnos);
				System.out.println("Se ha dado de alta ha el alumno.");
				
				System.out.println("\n");
				break;
				
			case 2:
				
				MenuAlumnos.darBaja(listaAlumnos);
				
				break;
				
			case 3: // Manolo
				
				// Llamamos a la funcion 
				listarAlumnos(listaAlumnos);
				
				// Salto de linea
				System.out.println();
				
				break;
				
			case 4: // Manolo
				
				// Llamamos a la funcion 
				modificarAlumnos(listaAlumnos);
						
				// Salto de linea
				System.out.println();
						
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