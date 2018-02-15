import java.util.*;
import java.lang.*;

public class MenuAlumnos {

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

	/*** Metodo 1: Dar de alta - Autor: Alejandro Fandila Cano. ***/
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

		if (nuevo.existe(lista) == false) {
			lista.add(nuevo);
			System.out.println("Se ha dado de alta al alumno.");
		} else {
			System.out.println("El alumno ya se encuentra dado de alta.");
		}

	}

	/*** Método 2: Dar de baja - Autor: Alejandro Fandila Cano. ***/
	public static void darBaja(ArrayList<Alumno> lista) throws Exception {

		boolean numero;
		int seleccionar = -1;

		Scanner entrada = new Scanner(System.in);

		System.out.println("¿Introduzca a quien desea dar de baja?:");
		MenuAlumnos.listarAlumnos(lista);

		// En caso de que no se introduzca correctamente, el metodo no hara nada.
		do {
			try {
				numero = true;
				seleccionar = entrada.nextInt();
				lista.remove(seleccionar);
			} catch (InputMismatchException ex) {
				numero = false;

				System.out.println("No se ha introducido ningun numero.");
			} catch (IndexOutOfBoundsException ex2) {
				System.out.println("El alumno " + seleccionar + " no existe.");
			}

		} while (numero = false);

		System.out.println("\n");

	}

	/*** Metodo 3: Listar Alumnos - Manolo ***/
	public static void listarAlumnos(ArrayList<Alumno> listaAlumnos) throws Exception {
		
		// Excepcion 
		if (listaAlumnos.size() < 1) {
			throw new Exception("Error: no hay alumnos en la lista");	
		}

		// Ciclo para sacar los alumnos
		for (int i = 0; i < listaAlumnos.size(); i++) {

			Alumno alumnoElegido = listaAlumnos.get(i);

			// Mostrar Alumno
			System.out.println("Numero : " + i + " / DNI: " + alumnoElegido.getDni() + " / Nombre: "
					+ alumnoElegido.getNombre() + " / Apellidos: " + alumnoElegido.getApellidos() + " \n/ Telefono: "
					+ alumnoElegido.getTelefono() + " / Email: " + alumnoElegido.getEmail());
		}
	}

	/*** Metodo 4: Modificar Alumnos - Manolo ***/
	public static void modificarAlumnos(ArrayList<Alumno> listaAlumnos){

		// Variables
		Scanner entrada = new Scanner(System.in);
		int numerote = 1, alumnoModifico, numerito;;

		do {

			System.out.println("Alumno a modificar: ");
			alumnoModifico = entrada.nextInt();

			// Buscamos al alumno elegido
			Alumno alumnoModificar = listaAlumnos.get(alumnoModifico);

			do {


				// Menu para elegir que se desea modificar
				System.out.println("Que desea modificar? \n1 - DNI \n2 - Nombre"
						+ "\n3 - Apellidos \n4 - Telefono \n5 - Email \n6 - Nada");
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

			// Si se desea modificar otro alumno
			do {
				System.out.println("Desea modificar otro alumno? (1 - Si / 2 - No)");
				numerote = entrada.nextInt();
			} while (numerote > 2 || numerote < 1); // Fin 

		} while (numerote == 1); // Fin
	}

	/*** Método 5: Matricular alumnos - David ***/

	public static void matricularAlumnos(ArrayList<Alumno> listaAlumnos) {

		Scanner entrada = new Scanner(System.in);

		// Variables

		int idAlumno = 0;
		String asignatura = "";
		boolean encontrado = false;

		// Pedimos el alumno al que vamos a matricular

		System.out.println("Introduzca el número del alumno que quiere matricular: ");
		idAlumno = entrada.nextInt();

		// Buscamos al alumno que ha introducido el usuario

		Alumno alumno = listaAlumnos.get(idAlumno);

		// Declaramos un ArrayList de Calificación y declaramos su constructor

		ArrayList<Calificacion> calificaciones = alumno.getNotas();

		Calificacion nuevaAsignatura = new Calificacion(asignatura);

		// Limpiamos el buffer y pedimos la asignatura

		entrada.nextLine();

		System.out.println("Introduzca el nombre de la asignatura de la que va a ser matriculado el alumno: ");
		asignatura = entrada.nextLine();

		// Comprobamos a través del método equals de la clase Calificación que la
		// asignatura introducida por el usuario no ha sido introducida anteriormente

		for (int i = 0; i < calificaciones.size(); i++) {

			if (calificaciones.get(i).getAsignatura().equals(asignatura)) {

				encontrado = true;
			}
		}

		// Si la asignatura no se había introducido anteriormente lo añade correctamente

		if (!encontrado) {

			calificaciones.add(nuevaAsignatura);
			nuevaAsignatura.setAsignatura(asignatura);

			System.out.println("El alumno " + alumno.getNombre() + " " + alumno.getApellidos()
					+ " ha sido correctamente matriculado en " + asignatura
					+ ". Está matriculado en las siguientes asignaturas:");

			for (int i = 0; i < calificaciones.size(); i++) {

				System.out.println("Asignatura " + i + ": " + calificaciones.get(i).getAsignatura());
			}

		} else {

			System.out.println("El alumno ya estaba matriculado en " + asignatura);
		}

	}

	/*** Método 6: Dar de baja de una asignatura - David ***/

	public static void bajaAsignatura(ArrayList<Alumno> listaAlumnos) {

		Scanner entrada = new Scanner(System.in);

		// Variables

		int idAlumno = 0;
		String asignatura = "";
		boolean encontrado = false;
		int aux = 0;

		// Pedimos el alumno al que vamos a matricular

		System.out.println("Introduzca el número del alumno que quiere desmatricular: ");
		idAlumno = entrada.nextInt();

		// Buscamos al alumno que ha introducido el usuario

		Alumno alumno = listaAlumnos.get(idAlumno);

		// Declaramos un ArrayList de Calificación y declaramos su constructor

		ArrayList<Calificacion> calificaciones = alumno.getNotas();

		Calificacion quitarAsignatura = new Calificacion(asignatura);

		// Limpiamos el buffer y pedimos la asignatura

		entrada.nextLine();

		System.out.println("Introduzca el nombre de la asignatura de la que va a ser desmatriculado el alumno: ");
		asignatura = entrada.nextLine();

		// Comprobamos a través del método equals de la clase Calificación que el alumno
		// está matriculado en esa asignatura

		for (int i = 0; i < calificaciones.size(); i++) {

			if (calificaciones.get(i).getAsignatura().equals(asignatura)) {

				encontrado = true;
				aux = i; // Almacenamos la posición en la que se encuentra la asignatura en una variable
							// auxiliar
			}
		}

		// Si el alumno estaba matriculado de esa asignatura, borra la posición en la
		// que se encontraba (gracias a aux)

		if (encontrado) {

			calificaciones.remove(aux);

			System.out.println("El alumno " + alumno.getNombre() + " " + alumno.getApellidos()
					+ " ha sido correctamente desmatriculado de " + asignatura
					+ ". Está matriculado en las siguientes asignaturas:");

			for (int i = 0; i < calificaciones.size(); i++) {

				System.out.println("Asignatura " + i + ": " + calificaciones.get(i).getAsignatura());
			}

		} else {

			System.out.println("El alumno no estaba matriculado la asignatura " + asignatura);
		}

	}

	/*** Metodo 7: Calificación trimestral - Antonio Mirallas ***/
	public static void introducirCalificacion(ArrayList<Alumno> listaAlumnos) {

		Scanner entrada = new Scanner(System.in);

		int numeroAlumno;
		String asig, calif;

		System.out.println("Introduzca el número de lista del alumno: ");
		numeroAlumno = entrada.nextInt();

		Alumno alumno = listaAlumnos.get(numeroAlumno);

		entrada.nextLine();

		System.out.println("Introduzca la asignatura: ");
		asig = entrada.nextLine();

		System.out.println("Introduzca la calificación: ");
		calif = entrada.nextLine();

		ArrayList<Calificacion> calificaciones = alumno.getNotas();

		Calificacion calificacion = new Calificacion("0");

		calificacion.setNota(calif); // ¿Poner un constructor con los dos atriutos?

		calificacion.setAsignatura(asig);

		calificaciones.add(calificacion);

		alumno.cambiarNotas(calificaciones);
	}

	/*** Metodo 8: Calificación trimestral - Antonio Mirallas ***/
	public static void mostrarCalificaciones(ArrayList<Alumno> listaAlumnos) {

		Scanner entrada = new Scanner(System.in);

		int numeroAlumno;

		System.out.println("Introduzca el número de lista del alumno: ");
		numeroAlumno = entrada.nextInt();

		ArrayList<Calificacion> calificaciones = listaAlumnos.get(numeroAlumno).getNotas();

		System.out.println("La calificación del alumno con DNI " + listaAlumnos.get(numeroAlumno).getDni() + " es ");

		for (int j = 0; j < calificaciones.size(); j++) {

			System.out.println("Asignatura: " + calificaciones.get(j).getAsignatura());
			System.out.println("Nota: " + calificaciones.get(j).getNota());

		}
	}
	
	/*** Metodo 11: Pasar lista - Manolo ***/
	public static void pasarLista(ArrayList<Alumno> listaAlumnos) throws Exception {
		
		// Variables
		Scanner entrada = new Scanner(System.in);
		Alumno alumnoAListar;
		int numeroLista, contadorLista = 0;
		
		// Excepcion 
		if (listaAlumnos.size() < 1) {
			throw new Exception("Error: no hay alumnos en la lista");	
		}
		
		// Ciclo para sacar los alumnos
		for (int i = 0; i < listaAlumnos.size(); i++) {
			
			alumnoAListar = listaAlumnos.get(i);
			
			
			do {
				System.out.println("Se encuentra " + alumnoAListar.getNombre() + " - " 
						+ alumnoAListar.getDni() + " ?\n1 - Si\n2 - No\n");
				numeroLista = entrada.nextInt();
			} while((numeroLista < 1) || (numeroLista > 2));
			
			if (numeroLista == 2) {
				contadorLista ++;
			}
		}
		
		//Informacion
		System.out.println("Han faltado " + contadorLista + " alumno/s");
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

			entrada.nextLine();

			switch (opcion) {

			case 1:

				MenuAlumnos.darAlta(listaAlumnos);

				System.out.println("\n");
				break;

			case 2:

				MenuAlumnos.darBaja(listaAlumnos);
				System.out.println("Se ha dado de baja al alumno.");

				System.out.println("\n");

				break;

			case 3: // Manolo

				// Llamamos al metodo
				try {
					listarAlumnos(listaAlumnos);
				} catch (Exception ex){
					System.out.println(ex.getMessage());
				}
					
				// Salto de linea
				System.out.println();

				break;

			case 4: // Manolo


				// Llamamos al metodo
				modificarAlumnos(listaAlumnos);

				// Salto de linea
				System.out.println();

				break;

			case 5:

				// Llamamos al metodo
				matricularAlumnos(listaAlumnos);

				// Salto de linea
				System.out.println();
				
				break;

			case 6:

				// Llamamos al metodo
				bajaAsignatura(listaAlumnos);

				// Salto de linea
				System.out.println();
				
				break;

			case 7:

				introducirCalificacion(listaAlumnos);

				break;

			case 8:

				mostrarCalificaciones(listaAlumnos);

				break;

			case 9:

				break;

			case 10:

				break;

			case 11: // Manolo
				
				// Llamamos al metodo
				try {
					pasarLista(listaAlumnos);
				} catch (Exception ex){
					System.out.println(ex.getMessage());
				}
				
				// Salto de linea
				System.out.println();

				break;

			case 12:

				break;

			case 13:

				System.out.println("Has salido");

				salir = true;

				break;

			default:

				if (opcion < 1 || opcion > 13)

					throw new Exception("Error. Opción incorrecta");

				break;
			}

		} while (!salir);
	}
}