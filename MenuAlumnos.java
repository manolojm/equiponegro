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

	/*** Método devolverPosicion - Autor: Antonio Mirallas ***/
	// Este método está hecho para devolver la posición de un alumno recibiendo su
	// DNI. En caso de no encontrar ese DNI devolverá -1
	public static int devolverPosicion(ArrayList<Alumno> lista, String dni) {

		boolean encontrado = false;
		int posicion = 0;

		while (posicion < lista.size() && !encontrado) { // Mientras no encuentre ni recorra todo el array sigue
															// comprobando

			if (lista.get(posicion).getDni().equals(dni)) // Condicional para encontrar

				encontrado = true;

			else

				posicion++;
		}

		if (!encontrado) // Si no está devuelve -1

			posicion = -1;

		return posicion;

	}

	/*** Método devolverCalificación - Antonio Mirallas ***/
	// Método para devolver la posición de una calificación recibiendo la posición
	// del alumno y el nombre de la asignatura. En caso de no encontrar dicha
	// asignatura devuelve -1
	public static int devolverCalificacion(ArrayList<Alumno> lista, int posAlumno, String asig) {

		int posCal = 0;
		boolean encontrado = false;

		// Mientras no excedamos el vector de asignaturas del alumno en posAlumno y no
		// encontremos la asignatura continuamos el bucle
		while (posCal < lista.get(posAlumno).getNotas().size() && !encontrado) {

			Calificacion calificacion = lista.get(posAlumno).getNotas().get(posCal); // Obtenemos la nota en la posición
																						// posCal del Alumno posAlumno

			if (calificacion.getAsignatura().equals(asig))

				encontrado = true;

			else

				posCal++;
		}

		if (!encontrado) // Si no está devuelve -1

			posCal = -1;

		return posCal; // Devolvemos la posición de la asignatura buscada en el alumno de la posición
						// posAlumno

	}

	/*** Método comprobarNota - Antonio Mirallas ***/
	// Método para comprobar si una nota introducida es correcta
	public static boolean comprobarNota(String nota) throws Exception {

		boolean error = false;

		if (!nota.equals("NE") && !nota.equals("ne")) {

			int n = Integer.parseInt(nota); // Convertimos nota en entero para comprobar

			if (n < 0 || n > 10)

				error = true;

		}

		if (error) // Si hay error lanzamos excepción

			throw new Exception("Error, nota incorrecta");

		return error;
	}

	/*** Método matricularAlumno - David Toral ***/

	// Método para matricular a un alumno en una asignatura

	public static void matricularAlumno(Alumno alumno) throws Exception {

		// Variables

		Scanner entrada = new Scanner(System.in);

		ArrayList<Calificacion> calificaciones = alumno.getNotas();

		String asignatura = "";
		boolean encontrado = false;

		Calificacion nuevaAsignatura = new Calificacion(asignatura);

		// Pedimos la asignatura

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

			throw new Exception("El alumno ya estaba matriculado en " + asignatura);
		}
	}

	/*** Metodo 1: Dar de alta - Autor: Alejandro Fandila Cano. ***/
	public static void darAlta(ArrayList<Alumno> lista) {

		Scanner entrada = new Scanner(System.in);

		Alumno nuevo = new Alumno(null);

		// Setteamos todos los campos, no es necesario capturar excepciones.
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

		if (devolverPosicion(lista, nuevo.getDni()) == -1) {

			// Tan solo añadiremos al alumno en caso de que este no exista.

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

		System.out.println("Introduzca a quien desea dar de baja:");

		MenuAlumnos.listarAlumnos(lista);

		// En caso de que no se introduzca correctamente, el metodo no hará nada.
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
	public static void modificarAlumnos(ArrayList<Alumno> listaAlumnos) {

		// Variables
		Scanner entrada = new Scanner(System.in);
		int numerote = 1, alumnoModifico, numerito;

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

	/*** Método 5: Dar de alta de una asignatura - David ***/

	public static void altaAsignatura(ArrayList<Alumno> listaAlumnos) throws Exception {

		Scanner entrada = new Scanner(System.in);

		// Variables

		int idAlumno = 0;
		int numContinuar = 2;
		int elegir = 0;
		String dni = "";

		do { // Bucle para repetir proceso de calificar

			do {

				System.out.println("¿Elejirá al alumno por el DNI o por el número de lista? (1 - DNI, 2 - Nº lista): ");

				elegir = entrada.nextInt();

				if (elegir != 1 && elegir != 2)

					System.out.println("Elección incorrecta");

			} while (elegir != 1 && elegir != 2);

			if (elegir == 1) { // Elegir por DNI

				do { // Bucle para la correcta introducción del DNI

					entrada.nextLine(); // Vaciamos el buffer

					System.out.println("Introduzca el DNI del alumno: ");
					dni = entrada.nextLine();

					idAlumno = devolverPosicion(listaAlumnos, dni);

					if (idAlumno == -1)

						System.out.println("DNI incorrecto");

				} while (idAlumno == -1); // Si ha devuelto -1 es que no lo ha encontrado

			}

			else // Elegir por lista

				do {

					System.out.println("Introduzca la posición: ");
					idAlumno = entrada.nextInt();

					if (idAlumno < 0 || idAlumno > listaAlumnos.size() - 1) // Comprobamos posición

						System.out.println("Posición incorrecta");

					entrada.nextLine(); // Vaciamos el buffer

				} while (idAlumno < 0 || idAlumno > listaAlumnos.size() - 1);

			// Buscamos al alumno que ha introducido el usuario

			Alumno alumno = listaAlumnos.get(idAlumno);

			// Llamamos al método matricularAlumno y le pasamos el alumno que acabamos de
			// pedir

			matricularAlumno(alumno);

			do { // Bucle para decidir si continuar o no

				System.out.println("¿Desea continuar? (1 - Sí, 2 - No): ");
				numContinuar = entrada.nextInt();

			} while (numContinuar != 1 && numContinuar != 2);

			entrada.nextLine(); // Vaciamos buffer para la siguiente iteración

		} while (numContinuar != 2);

	}

	/*** Método 6: Dar de baja de una asignatura - David ***/

	public static void bajaAsignatura(ArrayList<Alumno> listaAlumnos) throws Exception {

		Scanner entrada = new Scanner(System.in);

		// Variables

		int idAlumno = 0;
		int numContinuar = 2;
		int elegir = 0;
		int aux = 0;
		String dni = "";
		String asignatura = "";
		boolean encontrado = false;

		do { // Bucle para repetir proceso de calificar

			do {

				System.out.println("¿Elejirá al alumno por el DNI o por el número de lista? (1 - DNI, 2 - Nº lista): ");

				elegir = entrada.nextInt();

				if (elegir != 1 && elegir != 2)

					System.out.println("Elección incorrecta");

			} while (elegir != 1 && elegir != 2);

			if (elegir == 1) { // Elegir por DNI

				do { // Bucle para la correcta introducción del DNI

					entrada.nextLine(); // Vaciamos el buffer

					System.out.println("Introduzca el DNI del alumno: ");
					dni = entrada.nextLine();

					idAlumno = devolverPosicion(listaAlumnos, dni);

					if (idAlumno == -1)

						System.out.println("DNI incorrecto");

				} while (idAlumno == -1); // Si ha devuelto -1 es que no lo ha encontrado

			}

			else // Elegir por lista

				do {

					System.out.println("Introduzca la posición: ");
					idAlumno = entrada.nextInt();

					if (idAlumno < 0 || idAlumno > listaAlumnos.size() - 1) // Comprobamos posición

						System.out.println("Posición incorrecta");

					entrada.nextLine(); // Vaciamos el buffer

				} while (idAlumno < 0 || idAlumno > listaAlumnos.size() - 1);

			// Buscamos al alumno que ha introducido el usuario

			Alumno alumno = listaAlumnos.get(idAlumno);

			// Declaramos un ArrayList de Calificación y declaramos su constructor

			ArrayList<Calificacion> calificaciones = alumno.getNotas();

			// Calificacion quitarAsignatura = new Calificacion(asignatura);

			// Pedimos la asignatura

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

				throw new Exception("El alumno no estaba matriculado en la asignatura " + asignatura);
			}

			do { // Bucle para decidir si continuar o no

				System.out.println("¿Desea continuar? (1 - Sí, 2 - No): ");
				numContinuar = entrada.nextInt();

			} while (numContinuar != 1 && numContinuar != 2);

			entrada.nextLine(); // Vaciamos buffer para la siguiente iteración

		} while (numContinuar != 2);

	}

	/*** Metodo 7: Calificación trimestral - Antonio Mirallas ***/
	public static void introducirCalificacion(ArrayList<Alumno> listaAlumnos) {

		// Declaración de variables
		Scanner entrada = new Scanner(System.in);
		int posicion = 0, numContinuar = 2, elegir;
		String dni = "";
		String asig, calif;
		boolean error = false;

		do { // Bucle para repetir proceso de calificar

			do {

				System.out.println("¿Elejirá al alumno por el DNI o por el número de lista? (1 - DNI, 2 - Nº lista): ");

				elegir = entrada.nextInt();

				entrada.nextLine(); // Vaciamos el buffer

				if (elegir != 1 && elegir != 2)

					System.out.println("Elección incorrecta");

			} while (elegir != 1 && elegir != 2);

			if (elegir == 1) { // Elegir por DNI

				do { // Bucle para la correcta introducción del DNI

					System.out.println("Introduzca el DNI del alumno: ");
					dni = entrada.nextLine();

					posicion = devolverPosicion(listaAlumnos, dni);

					if (posicion == -1)

						System.out.println("DNI incorrecto");

				} while (posicion == -1); // Si ha devuelto -1 es que no lo ha encontrado

			}

			else // Elegir por lista

				do {

					System.out.println("Introduzca la posición: ");
					posicion = entrada.nextInt();

					if (posicion < 0 || posicion > listaAlumnos.size() - 1) // Comprobamos posición

						System.out.println("Posición incorrecta");

				} while (posicion < 0 || posicion > listaAlumnos.size() - 1);

			entrada.nextLine(); // Vaciamos el buffer

			System.out.println("Introduzca la asignatura: ");
			asig = entrada.nextLine();

			do { // Bluce para la correcta introducción de la nota

				System.out.println("Introduzca la calificación: ");
				calif = entrada.nextLine();

				try { // En el caso de que se haya introducido mal la nota

					error = comprobarNota(calif);

				} catch (Exception ex) {

					error = true;
					System.out.println(ex.getMessage());
				}

			} while (error);

			// Introducimos la nueva calificación
			ArrayList<Calificacion> calificaciones = listaAlumnos.get(posicion).getNotas();

			Calificacion calificacion = new Calificacion("0");

			calificacion.setNota(calif);

			calificacion.setAsignatura(asig);

			calificaciones.add(calificacion);

			listaAlumnos.get(posicion).cambiarNotas(calificaciones);

			do { // Bucle para decidir si continuar o no

				System.out.println("¿Desea continuar? (1 - Sí, 2 - No): ");
				numContinuar = entrada.nextInt();

			} while (numContinuar != 1 && numContinuar != 2);

			entrada.nextLine(); // Vaciamos buffer para la siguiente iteracción

		} while (numContinuar != 2);
	}

	/*** Metodo 8: Calificación trimestral - Antonio Mirallas ***/
	public static void mostrarCalificaciones(ArrayList<Alumno> listaAlumnos) {

		Scanner entrada = new Scanner(System.in);
		String dni;
		int posicion, numContinuar, elegir;
		boolean error = false;

		do { // Bucle para repetir proceso de calificar

			do {

				System.out.println("¿Elejirá al alumno por el DNI o por el número de lista? (1 - DNI, 2 - Nº lista): ");

				elegir = entrada.nextInt();

				if (elegir != 1 && elegir != 2)

					System.out.println("Elección incorrecta");

			} while (elegir != 1 && elegir != 2);

			if (elegir == 1) // Elegir por DNI

				do { // Bucle para la correcta introducción del DNI

					entrada.nextLine(); // Vaciamos el buffer

					System.out.println("Introduzca el DNI del alumno: ");
					dni = entrada.nextLine();

					posicion = devolverPosicion(listaAlumnos, dni);

					if (posicion == -1)

						System.out.println("DNI incorrecto");

				} while (posicion == -1); // Si ha devuelto -1 es que no lo ha encontrado

			else // Elegir por lista

				do {

					System.out.println("Introduzca la posición: ");
					posicion = entrada.nextInt();

					if (posicion < 0 || posicion > listaAlumnos.size() - 1) // Comprobamos posición

						System.out.println("Posición incorrecta");

				} while (posicion < 0 || posicion > listaAlumnos.size() - 1);

			System.out.println("La calificación del alumno con DNI " + listaAlumnos.get(posicion).getDni() + " es ");

			for (int j = 0; j < listaAlumnos.get(posicion).getNotas().size(); j++) {

				System.out.println("\nAsignatura: " + listaAlumnos.get(posicion).getNotas().get(j).getAsignatura());
				System.out.println("Nota: " + listaAlumnos.get(posicion).getNotas().get(j).getNota());

			}

			do { // Bucle para decidir si continuar o no

				System.out.println("¿Desea continuar? (1 - Sí, 2 - No): ");
				numContinuar = entrada.nextInt();

			} while (numContinuar != 1 && numContinuar != 2);

			entrada.nextLine(); // Vaciamos buffer para la siguiente iteracción

		} while (numContinuar != 2);
	}

	// Metodo 9: Poner falta dia completo
	public static void faltaDiaCompleto(ArrayList<Alumno> alumnos) throws Exception {

		Scanner entrada = new Scanner(System.in);

		// Declaracion de variables
		int seleccionar, dia, mes, ano;
		ArrayList<DiaClase> faltas = new ArrayList();

		// Pedimos al usuario que introduzca la fecha
		System.out.println("Introduzca la fecha");
		System.out.println("1. Dia:");
		dia = entrada.nextInt();
		System.out.println("2. Mes:");
		mes = entrada.nextInt();
		System.out.println("3. Año:");
		ano = entrada.nextInt();

		// Creamos / Setteamos fecha, y la añadimos a falta
		Fecha fecha = new Fecha(dia, mes, ano);
		DiaClase falta = new DiaClase(fecha);
		falta.getHorario().faltaDiaEntero();

		// Seleccionamos al alumno al que vamos a poner falta
		System.out.println("¿Que alumno ha faltado el dia completo?:");
		MenuAlumnos.listarAlumnos(alumnos);
		seleccionar = entrada.nextInt();

		// Cambiamos le añadimos a las faltas del alumno 6 mas
		alumnos.get(seleccionar).getFaltas().add(falta);

	}

	// Metodo 10:Poner falta hora
	public static void faltaHora(ArrayList<Alumno> alumnos) throws Exception {

		Scanner entrada = new Scanner(System.in);

		// Declaracion de variables
		int seleccionar, dia, mes, ano;
		;
		ArrayList<DiaClase> faltas = new ArrayList();

		// Pedimos al usuario que introduzca la fecha
		System.out.println("Introduzca la fecha");
		System.out.println("1. Dia:");
		dia = entrada.nextInt();
		System.out.println("2. Mes:");
		mes = entrada.nextInt();
		System.out.println("3. Año:");
		ano = entrada.nextInt();

		// Creamos / Setteamos fecha, y la añadimos a falta
		Fecha fecha = new Fecha(dia, mes, ano);
		DiaClase falta = new DiaClase(fecha);
		System.out.println("Introduzca la sesion en la que falta el alumno:");
		falta.getHorario().faltaHora(entrada.nextInt());

		// Seleccionamos al alumno al que vamos a poner falta
		System.out.println("¿Que alumno ha faltado?:");
		MenuAlumnos.listarAlumnos(alumnos);
		seleccionar = entrada.nextInt();

		// Le añadimos la falta
		alumnos.get(seleccionar).getFaltas().add(falta);

	}

	/*** Metodo 11: Pasar lista - Manolo ***/
	public static void pasarLista(ArrayList<Alumno> listaAlumnos) throws Exception {

		
		Scanner entrada = new Scanner(System.in);
		
		// Variables
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
				System.out.println("Se encuentra " + alumnoAListar.getNombre() + " - " + alumnoAListar.getDni()
						+ " ?\n1 - Si\n2 - No\n");
				numeroLista = entrada.nextInt();
			} while ((numeroLista < 1) || (numeroLista > 2));

			if (numeroLista == 2) {
				contadorLista++;
			}
		}

		// Informacion
		System.out.println("Han faltado " + contadorLista + " alumno/s");
	}

	// Metodo 12: Listar Faltas - Alejandro Fandila Cano
<<<<<<< HEAD
		public static void listarFaltas(ArrayList<Alumno> alumnos) throws Exception {

			Scanner entrada = new Scanner(System.in);

			// Declaracion de variables
			int seleccionar;
			char[] faltas;
			faltas = new char[6];
			boolean excepcion = false;
			
			// Seleccionamos un alumno
			System.out.println("Elija un alumno:");
			MenuAlumnos.listarAlumnos(alumnos);
			
			//Excepcion InputMismatchException
			do {
				try {
					
					excepcion = false;
					
					seleccionar = entrada.nextInt();
					
					for (int i = 0; i < alumnos.get(seleccionar).getFaltas().size(); i++) {
						faltas = alumnos.get(seleccionar).getFaltas().get(i).getHorario().getSesiones();
						alumnos.get(seleccionar).getFaltas().get(i).getDia().imprimeFecha();
						for (int k = 0; k < 6; k++) {
							if(faltas[k] == 'F') {
								System.out.println("Sesion "+(k + 1));
								System.out.println("F");
							}
						}
					}
					
				}catch(InputMismatchException ex) {
					excepcion = true;
					System.out.println("Introduzca un numero: ");
					entrada.next();
				}catch(IndexOutOfBoundsException ex2) {
					System.out.println("No ha seleccionado ningún alumno.");
				}
				
			}while(excepcion == true);
			
=======
	public static void listarFaltas(ArrayList<Alumno> alumnos) throws Exception {

		Scanner entrada = new Scanner(System.in);

		// Declaracion de variables
		int seleccionar;
		char[] faltas;
		faltas = new char[6];

		// Seleccionamos un alumno
		System.out.println("Elija un alumno:");
		MenuAlumnos.listarAlumnos(alumnos);
		seleccionar = entrada.nextInt();

		for (int i = 0; i < alumnos.get(seleccionar).getFaltas().size(); i++) {
			faltas = alumnos.get(seleccionar).getFaltas().get(i).getHorario().getSesiones();
			alumnos.get(seleccionar).getFaltas().get(i).getDia().imprimeFecha();
			for (int k = 0; k < 6; k++) {
				if (faltas[k] == 'F') {
					System.out.println("Sesion " + (k + 1));
					System.out.println("F");
				}
			}
>>>>>>> 5af2f48225337bb66370d80f4896f66e80293d0c
		}

	}

	/*** Main ***/
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);

		// Variables
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		boolean salir = false, opcionCorrecta;
		int opcion = 0;

		// Pruebas
		Alumno alumno1 = new Alumno("12345X", "Antonio", "Lopez");
		listaAlumnos.add(alumno1);

		do {

			mostrarMenu();

			// Excepcion para obtener una opcion valida - Alejandro Fandila Cano.
			do {
				try {
					opcion = entrada.nextInt();
				} catch (InputMismatchException ex) {
					opcionCorrecta = false;
					System.out.println("No se ha seleccionado correctamente la opcion.");
					System.out.println("Vuelva a introducirla:");
				}
				opcionCorrecta = true;
			} while (opcionCorrecta == false);

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
				} catch (Exception ex) {
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
				try {
					altaAsignatura(listaAlumnos);
				} catch (Exception ex) {

					System.out.println(ex.getMessage());
				}

				// Salto de linea
				System.out.println();

				break;

			case 6:

				try {
					bajaAsignatura(listaAlumnos);
				} catch (Exception ex) {

					System.out.println(ex.getMessage());
				}
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
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

				// Salto de linea
				System.out.println();

				break;

			case 12:
				MenuAlumnos.listarFaltas(listaAlumnos);
				System.out.println("\n");
				
				break;

			case 13:

				System.out.println("Has salido");

				salir = true;

				break;

			default:

				System.out.println("Opción incorrecta");

				break;
			}

		} while (!salir);
	}
}