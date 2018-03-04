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
	public static int devolverCalificacion(Alumno alumno, String asig) {

		int posCal = 0;
		boolean encontrado = false;

		// Mientras no excedamos el vector de asignaturas del alumno en posAlumno y no
		// encontremos la asignatura continuamos el bucle
		while (posCal < alumno.getNotas().size() && !encontrado) {

			Calificacion calificacion = alumno.getNotas().get(posCal); // Obtenemos la nota en la posición
																		// posCal del Alumno posAlumno

			if (calificacion.getAsignatura().equals(asig))

				encontrado = true;

			else

				posCal++;
		}

		if (!encontrado) // Si no estó devuelve -1

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

		// Si la asignatura no se había introducido anteriormente lo aniade
		// correctamente

		if (!encontrado) {

			calificaciones.add(nuevaAsignatura);
			nuevaAsignatura.setAsignatura(asignatura);

			System.out.println("El alumno " + alumno.getNombre() + " " + alumno.getApellidos()
					+ " ha sido correctamente matriculado en " + asignatura
					+ ". Esta matriculado en las siguientes asignaturas:");

			for (int i = 0; i < calificaciones.size(); i++) {

				System.out.println("Asignatura " + i + ": " + calificaciones.get(i).getAsignatura());
			}

		} else {

			throw new Exception("El alumno ya estaba matriculado en " + asignatura);
		}
	}

	/*** Método para comprobar si hay alumnos - Manolo ***/
	public static void hayAlumnos(ArrayList<Alumno> listaAlumnos) throws Exception {
		if (listaAlumnos.size() < 1) {
			throw new Exception("Error: no hay alumnos en la lista");
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

			// Tan solo aniadiremos al alumno en caso de que este no exista.

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
	/* Recorre el ArrayList para listar los alumnos */
	public static void listarAlumnos(ArrayList<Alumno> listaAlumnos) throws Exception {

		// Excepcion: no hay alumnos en la lista
		hayAlumnos(listaAlumnos);

		// Ciclo para sacar los alumnos
		for (int i = 0; i < listaAlumnos.size(); i++) {

			Alumno alumnoElegido = listaAlumnos.get(i);

			// Mostrar Alumno
			System.out.println("Numero : " + i + " / DNI: " + alumnoElegido.getDni() + " / Nombre: "
					+ alumnoElegido.getNombre() + " / Apellidos: " + alumnoElegido.getApellidos() + " \n   / Telefono: "
					+ alumnoElegido.getTelefono() + " / Email: " + alumnoElegido.getEmail() + "\n");
		}
	}

	/*** Metodo 4: Modificar Alumnos - Manolo ***/
	/*
	 * Permite modificar cualquier caracterísitca de un alumno a través de un menú
	 */
	public static void modificarAlumnos(ArrayList<Alumno> listaAlumnos) throws Exception {

		// Variables
		Scanner entrada = new Scanner(System.in);
		int numerote = 1, alumnoModifico, numerito;

		// Excepcion: no hay alumnos en la lista
		hayAlumnos(listaAlumnos);

		do {

			// Preguntamos que alumno se desea modificar
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

		// Excepcion: no hay alumnos en la lista
		hayAlumnos(listaAlumnos);

		do { // Bucle para repetir proceso de calificar

			do {

				System.out
						.println("Elegirá al alumno por el DNI o por el número de lista? (1 - DNI, 2 - Num. lista): ");

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

						throw new Exception("El alumno no existe.");

				} while (idAlumno == -1); // Si ha devuelto -1 es que no lo ha encontrado

			}

			else // Elegir por lista

				do {

					System.out.println("Introduzca la posición: ");
					idAlumno = entrada.nextInt();

					if (idAlumno < 0 || idAlumno > listaAlumnos.size() - 1) // Comprobamos posición

						throw new Exception("El alumno no existe.");

					entrada.nextLine(); // Vaciamos el buffer

				} while (idAlumno < 0 || idAlumno > listaAlumnos.size() - 1);

			// Buscamos al alumno que ha introducido el usuario

			Alumno alumno = listaAlumnos.get(idAlumno);

			// Llamamos al método matricularAlumno y le pasamos el alumno que acabamos de
			// pedir

			matricularAlumno(alumno);

			do { // Bucle para decidir si continuar o no

				System.out.println("Desea continuar? (1 - Si, 2 - No): ");
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

		// Excepcion: no hay alumnos en la lista
		hayAlumnos(listaAlumnos);

		do { // Bucle para repetir proceso de calificar

			do {

				System.out
						.println("Elegirá al alumno por el DNI o por el numero de lista? (1 - DNI, 2 - Num. lista): ");

				elegir = entrada.nextInt();

				if (elegir != 1 && elegir != 2)

					throw new Exception("El alumno no existe.");

			} while (elegir != 1 && elegir != 2);

			if (elegir == 1) { // Elegir por DNI

				do { // Bucle para la correcta introduccion del DNI

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

					System.out.println("Introduzca la posicion: ");
					idAlumno = entrada.nextInt();

					if (idAlumno < 0 || idAlumno > listaAlumnos.size() - 1) // Comprobamos posicion

						throw new Exception("El alumno no existe.");

					entrada.nextLine(); // Vaciamos el buffer

				} while (idAlumno < 0 || idAlumno > listaAlumnos.size() - 1);

			// Buscamos al alumno que ha introducido el usuario

			Alumno alumno = listaAlumnos.get(idAlumno);

			// Declaramos un ArrayList de Calificacion y declaramos su constructor

			ArrayList<Calificacion> calificaciones = alumno.getNotas();

			// Calificacion quitarAsignatura = new Calificacion(asignatura);

			// Pedimos la asignatura

			System.out.println("Introduzca el nombre de la asignatura de la que va a ser desmatriculado el alumno: ");
			asignatura = entrada.nextLine();

			// Comprobamos a traves del metodo equals de la clase Calificacion que el alumno
			// esta matriculado en esa asignatura

			for (int i = 0; i < calificaciones.size(); i++) {

				if (calificaciones.get(i).getAsignatura().equals(asignatura)) {

					encontrado = true;
					aux = i; // Almacenamos la posicion en la que se encuentra la asignatura en una variable
								// auxiliar
				}
			}

			// Si el alumno estaba matriculado de esa asignatura, borra la posicion en la
			// que se encontraba (gracias a aux)

			if (encontrado) {

				calificaciones.remove(aux);

				System.out.println("El alumno " + alumno.getNombre() + " " + alumno.getApellidos()
						+ " ha sido correctamente desmatriculado de " + asignatura
						+ ". Esta matriculado en las siguientes asignaturas:");

				for (int i = 0; i < calificaciones.size(); i++) {

					System.out.println("Asignatura " + i + ": " + calificaciones.get(i).getAsignatura());
				}

			} else {

				throw new Exception("El alumno no estaba matriculado en la asignatura " + asignatura);
			}

			do { // Bucle para decidir si continuar o no

				System.out.println("Desea continuar? (1 - Si, 2 - No): ");
				numContinuar = entrada.nextInt();

			} while (numContinuar != 1 && numContinuar != 2);

			entrada.nextLine(); // Vaciamos buffer para la siguiente iteracion

		} while (numContinuar != 2);

	}

	/*** Metodo 7: Calificacion trimestral - Antonio Mirallas ***/
	public static void introducirCalificacion(ArrayList<Alumno> listaAlumnos) {

		// Declaracion de variables
		Scanner entrada = new Scanner(System.in);
		int posicion = 0, elegir = 0, deseaMatricular = 1, posCalificacion = 0, opcionLista = 0;
		String dni = "";
		String asig, calif;
		boolean error = false, matriculado = false, errorLetra = false;

		if (listaAlumnos.size() < 1)

			System.out.println("No hay alumnos matriculados");

		else {

			do { // Bucle para repetir proceso de calificar

				do {

					do { // Bucle para caracteres no validos

						try {

							System.out.println(
									"Elegira al alumno por el DNI o por el numero de lista? (1 - DNI, 2 - Num. lista): ");
							elegir = entrada.nextInt();
							errorLetra = false;

						} catch (InputMismatchException ex) {

							System.out.println("Caracter no valido");
							errorLetra = true;
							entrada.nextLine();
						}

					} while (errorLetra);

					entrada.nextLine(); // Vaciamos el buffer

					if (elegir != 1 && elegir != 2)

						System.out.println("Eleccion incorrecta");

				} while (elegir != 1 && elegir != 2);

				if (elegir == 1) { // Elegir por DNI

					do { // Bucle para la correcta introduccion del DNI

						System.out.println("Introduzca el DNI del alumno: ");
						dni = entrada.nextLine();

						posicion = devolverPosicion(listaAlumnos, dni);

						if (posicion == -1) {

							System.out.println("DNI incorrecto");

							do {

								do { // Bucle para caracteres no validos

									try {

										System.out.println("Ver lista? 1 - Si, 2 - No");
										errorLetra = false;
										opcionLista = entrada.nextInt();

									} catch (InputMismatchException ex) {

										System.out.println("Caracter no valido");
										errorLetra = true;
										entrada.nextLine();
									}

								} while (errorLetra);

								if (opcionLista == 1) {

									try {

										listarAlumnos(listaAlumnos);

									} catch (Exception ex) {

										System.out.println(ex.getMessage());
									}

								}

								entrada.nextLine();

							} while (opcionLista != 1 && opcionLista != 2);

						}

					} while (posicion == -1); // Si ha devuelto -1 es que no lo ha encontrado

				}

				else // Elegir por lista

					do {

						do { // Bucle para caracteres no validos

							try {

								System.out.println("Introduzca la posicion: ");
								posicion = entrada.nextInt();
								errorLetra = false;

							} catch (InputMismatchException ex) {

								System.out.println("Caracter no valido");
								errorLetra = true;
								entrada.nextLine();
							}

						} while (errorLetra);

						if (posicion < 0 || posicion > listaAlumnos.size() - 1) // Comprobamos posicion

							System.out.println("Posicion incorrecta");

						entrada.nextLine();

					} while (posicion < 0 || posicion > listaAlumnos.size() - 1);

				System.out.println("Introduzca la asignatura: ");
				asig = entrada.nextLine();

				matriculado = false;

				if (devolverCalificacion(listaAlumnos.get(posicion), asig) == -1) { // Llamada a devolverCalificacion
																					// para
																					// comprobar la existencia de dicha
																					// calificacion

					System.out.println(
							"El alumno no esta matriculado en esa asignatura, desea matricularlo? (1 - Si, 2 - No): ");

					do { // Bucle para matricular al alumno en la asignatura

						deseaMatricular = entrada.nextInt();

						if (deseaMatricular == 1) {

							try { // Matriculamos

								matricularAlumno(listaAlumnos.get(posicion));
								entrada.nextLine();

							} catch (Exception ex) {

								System.out.println(ex.getMessage());
							}

							matriculado = true;

						}

						else if (deseaMatricular == 2)

							System.out.println("Volviendo...");

						else

							System.out.println("Error. Vuelva a elegir una opcion (1 - Si, 2 - No):");

					} while (deseaMatricular != 1 && deseaMatricular != 2);

				}

				if (deseaMatricular == 1) { // El alumno ya esta matriculado en la asignatura

					do { // Bluce para la correcta introduccion de la nota

						System.out.println("Introduzca la calificacion: ");
						calif = entrada.nextLine();

						try { // En el caso de que se haya introducido mal la nota

							error = comprobarNota(calif);

						} catch (Exception ex) {

							error = true;
							System.out.println(ex.getMessage());
						}

					} while (error);

					if (matriculado) // Si matriculado es true es porque el usuario ha tenido que matricular antes la
										// asignatura y por tanto la nota a cambiar es la que esta en ultimo lugar

						posCalificacion = listaAlumnos.get(posicion).getNotas().size() - 1;

					else // Si no ha tenido que matricular, entonces la asignatura ya existe y es aquella
							// con el valor de asig, por tanto buscamos su posicion con este valor sin temor
							// a que no esta

						posCalificacion = devolverCalificacion(listaAlumnos.get(posicion), asig);

					listaAlumnos.get(posicion).getNotas().get(posCalificacion).setNota(calif);

				}

				do { // Bucle para decidir si continuar o no

					do { // Bucle para caracteres no validos

						try {

							System.out.println("Desea continuar? (1 - Si, 2 - No): ");
							elegir = entrada.nextInt();
							errorLetra = false;

						} catch (InputMismatchException ex) {

							System.out.println("Caracter no valido");
							errorLetra = true;
							entrada.nextLine();
						}

					} while (errorLetra);

				} while (elegir != 1 && elegir != 2);

				entrada.nextLine(); // Vaciamos buffer para la siguiente iteraccion

			} while (elegir != 2);
		}
	}

	/*** Metodo 8: Calificacion trimestral - Antonio Mirallas ***/
	public static void mostrarCalificaciones(ArrayList<Alumno> listaAlumnos) {

		Scanner entrada = new Scanner(System.in);
		String dni;
		int posicion = 0, numContinuar = 0, elegir = 0, opcionLista = 0;
		boolean error = false, errorLetra = false;

		if (listaAlumnos.size() < 1)

			System.out.println("No hay alumnos matriculados");

		else {

			do { // Bucle para repetir proceso de calificar

				do {

					do { // Bucle para caracteres no validos

						try {

							System.out.println(
									"Elegira al alumno por el DNI o por el numero de lista? (1 - DNI, 2 - Num. lista): ");
							elegir = entrada.nextInt();
							errorLetra = false;

						} catch (InputMismatchException ex) {

							System.out.println("Caracter no valido");
							errorLetra = true;
							entrada.nextLine();
						}

					} while (errorLetra);

					entrada.nextLine(); // Vaciamos el buffer

					if (elegir != 1 && elegir != 2)

						System.out.println("Eleccion incorrecta");

				} while (elegir != 1 && elegir != 2);

				if (elegir == 1) { // Elegir por DNI

					do { // Bucle para la correcta introduccion del DNI

						System.out.println("Introduzca el DNI del alumno: ");
						dni = entrada.nextLine();

						posicion = devolverPosicion(listaAlumnos, dni);

						if (posicion == -1) {

							System.out.println("DNI incorrecto");

							do {

								do { // Bucle para caracteres no validos

									try {

										System.out.println("Ver lista? 1 - Si, 2 - No");
										errorLetra = false;
										opcionLista = entrada.nextInt();

									} catch (InputMismatchException ex) {

										System.out.println("Caracter no valido");
										errorLetra = true;
										entrada.nextLine();
									}

								} while (errorLetra);

								if (opcionLista == 1) {

									try {

										listarAlumnos(listaAlumnos);

									} catch (Exception ex) {

										System.out.println(ex.getMessage());
									}

								}

								entrada.nextLine();

							} while (opcionLista != 1 && opcionLista != 2);

						}

					} while (posicion == -1); // Si ha devuelto -1 es que no lo ha encontrado

				}

				else // Elegir por lista

					do {

						do { // Bucle para caracteres no validos

							try {

								System.out.println("Introduzca la posicion: ");
								posicion = entrada.nextInt();
								errorLetra = false;

							} catch (InputMismatchException ex) {

								System.out.println("Caracter no valido");
								errorLetra = true;
								entrada.nextLine();
							}

						} while (errorLetra);

						if (posicion < 0 || posicion > listaAlumnos.size() - 1) // Comprobamos posicion

							System.out.println("Posicion incorrecta");

						entrada.nextLine();

					} while (posicion < 0 || posicion > listaAlumnos.size() - 1);

				System.out
						.println("La calificacion del alumno con DNI " + listaAlumnos.get(posicion).getDni() + " es ");

				for (int j = 0; j < listaAlumnos.get(posicion).getNotas().size(); j++) {

					System.out.println("\nAsignatura: " + listaAlumnos.get(posicion).getNotas().get(j).getAsignatura());
					System.out.println("Nota: " + listaAlumnos.get(posicion).getNotas().get(j).getNota());

				}

				do { // Bucle para decidir si continuar o no

					do { // Bucle para caracteres no validos

						try {

							System.out.println("Desea continuar? (1 - Si, 2 - No): ");
							numContinuar = entrada.nextInt();
							errorLetra = false;

						} catch (InputMismatchException ex) {

							System.out.println("Caracter no valido");
							errorLetra = true;
							entrada.nextLine();
						}

					} while (errorLetra);

				} while (numContinuar != 1 && numContinuar != 2);

				entrada.nextLine(); // Vaciamos buffer para la siguiente iteraccion

			} while (numContinuar != 2);

		}

	}

	/***
	 * Metodo 9: Poner falta dia completo - Alejandro Fandila Cano y Juan Paez
	 ***/
	public static void faltaDiaCompleto(ArrayList<Alumno> alumnos) throws Exception {

		Scanner entrada = new Scanner(System.in);

		// Declaracion de variables
		int numContinuar = 0, seleccionar = -1, dia = 1, mes = 1, ano = 2016;
		String dni = "";
		int elegir = 0;
		ArrayList<DiaClase> faltas = new ArrayList();
		boolean encontrado = false;

		// Excepcion: no hay alumnos en la lista
		hayAlumnos(alumnos);

		do { // bucle para repetir faltaDiaCompleto
			do { // bucle para repetir si continuar o no
				do { // bucle para repetir todo si fecha es incorrecta

					// Pedimos al usuario que introduzca la fecha
					System.out.println("Introduzca la fecha");
					do { // bucle introduccion correcta del dia
						try {

							System.out.println("1. Dia:");
							dia = entrada.nextInt();
							encontrado = true;

						} catch (InputMismatchException ex) {
							System.out.println("Vuelve a introducir un dia");
							encontrado = false;
							entrada.next();
						}
					} while (encontrado == false);

					do { // bucle introduccion correcta del mes
						try {

							System.out.println("2. Mes:");
							mes = entrada.nextInt();
							encontrado = true;

						} catch (InputMismatchException ex) {
							System.out.println("Vuelve a introducir un mes");
							encontrado = false;
							entrada.next();
						}
					} while (encontrado == false);

					do { // bucle introduccion correcta del anio
						try {

							System.out.println("3. Anio:");
							ano = entrada.nextInt();
							encontrado = true;

						} catch (InputMismatchException ex) {
							System.out.println("Vuelve a introducir un anio");
							encontrado = false;
							entrada.next();
						}
					} while (encontrado == false);

					// Creamos / Setteamos fecha, y la aniadimos a falta

					try { // comprobamos que la fecha es correcta

						Fecha fecha = new Fecha(dia, mes, ano);
						DiaClase falta = new DiaClase(fecha);

						encontrado = true;

					} catch (Exception ex) {
						System.out.println(ex.getMessage());
						encontrado = false;

					}

				} while (encontrado == false); // Final bucle de comprobar si la fecha es correcta

				// Seleccionamos al alumno al que vamos a poner falta

				do { // bucle que repite el dni y numero de lista

					do { // bucle para elegir entre el DNI O Num. de lista
						try {
							System.out.println(
									"Elegira al alumno por el DNI o por el numero de lista? (1 - DNI, 2 - Num. lista): ");

							elegir = entrada.nextInt();

							if (elegir != 1 && elegir != 2)

								System.out.println("Eleccion incorrecta");

							encontrado = true;
						} catch (InputMismatchException ex) {
							System.out.println("Introduzca 1 o 2 !!! ");
							encontrado = false;
							entrada.nextLine();
						}
					} while (elegir != 1 && elegir != 2);

					if (elegir == 1) // Elegir por DNI

						do { // Bucle para la correcta introducción del DNI

							entrada.nextLine(); // Vaciamos el buffer

							System.out.println("Que alumno ha faltado el dia completo?:");
							MenuAlumnos.listarAlumnos(alumnos); // mostramos los alumnos

							System.out.println(""); // salto de linea
							System.out.println("Introduzca el DNI del alumno: ");
							dni = entrada.nextLine();

							seleccionar = devolverPosicion(alumnos, dni);

							if (seleccionar == -1)

								System.out.println("DNI incorrecto");

						} while (seleccionar == -1); // Si ha devuelto -1 es que no lo ha encontrado

					else // Elegir por lista
						do { // bucle que repite si el usuario mete alguna letra

							do { // Bucle para la correcta introduccion del Num. de lista
								try {
									System.out.println("Que alumno ha faltado el dia completo?:");
									MenuAlumnos.listarAlumnos(alumnos); // mostramos los alumnos

									System.out.println("");// salto de linea
									System.out.println("Introduzca la posicion: ");
									seleccionar = entrada.nextInt();

									if (seleccionar < 0 || seleccionar > alumnos.size() - 1) // Comprobamos posicion

										System.out.println("Posicion incorrecta");
									encontrado = true;

								} catch (Exception ex) {
									System.out.println("Introduzca el numero del alumno !!!");
									encontrado = false;
									entrada.nextLine();
								}
							} while (seleccionar < 0 || seleccionar > alumnos.size() - 1);

						} while (encontrado == false); // final del bucle que repite el numero del alumno

				} while (encontrado == false); // final del bucle que repite el dni y numero del alumno
				// Cambiamos le aniadimos a las faltas del alumno 6 mas
				try {
					Fecha fecha = new Fecha(dia, mes, ano);
					DiaClase falta = new DiaClase(fecha);
					falta.getHorario().faltaDiaEntero();
					alumnos.get(seleccionar).getFaltas().add(falta);

				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					encontrado = false;

				}
				System.out.println("Falta puesta correctamente ");

				do { // Bucle para decidir si continuar o no
					try {
						System.out.println("Desea continuar? (1 - Si, 2 - No): ");
						numContinuar = entrada.nextInt();
						encontrado = true;
					} catch (InputMismatchException ex) {
						System.out.println("Introduzca 1 o 2 !!!!");
						encontrado = false;
						entrada.nextLine();
					}
				} while (numContinuar != 1 && numContinuar != 2);

				entrada.nextLine(); // Vaciamos buffer para la siguiente iteraccion

			} while (numContinuar != 2);

		} while (encontrado == false); // final de repetir falta dia completo

	}

	/***
	 * Metodo 10: Poner falta en una sesion - Alejandro Fandila Cano y Juan Paez
	 ***/
	public static void faltaHora(ArrayList<Alumno> alumnos) throws Exception {

		Scanner entrada = new Scanner(System.in);

		// Declaracion de variables
		int numContinuar = 0, seleccionar = -1, dia = 1, mes = 1, ano = 2016;
		String dni = "";
		boolean encontrado;
		ArrayList<DiaClase> faltas = new ArrayList();

		// Excepcion: no hay alumnos en la lista
		hayAlumnos(alumnos);

		do {// bucle para repetir faltaHora completo
			do { // bucle para repetir si continuar o no
				do { // bucle para repetir todo si fecha es incorrecta

					// Pedimos al usuario que introduzca la fecha
					System.out.println("Introduzca la fecha");
					do { // bucle Introducion correcta del dia

						try {
							encontrado = true;
							System.out.println("1. Dia:");
							dia = entrada.nextInt();

						} catch (InputMismatchException ex) {
							encontrado = false;
							System.out.println("Vuelve a introducir un dia");
							entrada.next();
						}
					} while (encontrado == false);// final bucle dia

					do { // bucle introducion correcta del mes
						try {
							encontrado = true;
							System.out.println("2. Mes:");
							mes = entrada.nextInt();

						} catch (InputMismatchException ex) {
							System.out.println("Vuelve a introducir un mes");
							encontrado = false;
							entrada.next();
						}
					} while (encontrado == false); // final bucle mes

					do { // bucle introducion correcta del anio
						try {
							encontrado = true;
							System.out.println("3. Anio:");
							ano = entrada.nextInt();

						} catch (InputMismatchException ex) {
							System.out.println("Vuelve a introducir un anio");
							encontrado = false;
							entrada.next();
						}
					} while (encontrado == false);// final bucle anio

					// Creamos / Setteamos fecha, y la aniadimos a falta

					try { // comprobar que la fecha es correcta

						Fecha fecha = new Fecha(dia, mes, ano);
						DiaClase falta = new DiaClase(fecha);

						encontrado = true;

					} catch (Exception ex) {
						System.out.println(ex.getMessage());
						encontrado = false;

					}
				} while (encontrado == false); // Final del bucle de comprobar si la fecha es correcta

				do { // bucle que repite el dni y numero de lista

					do { // bucle para elegir entre el DNI O Num de lista
						try {
							System.out.println(
									"Elegira al alumno por el DNI o por el numero de lista? (1 - DNI, 2 - Num. lista): ");

							seleccionar = entrada.nextInt();

							if (seleccionar != 1 && seleccionar != 2)

								System.out.println("Eleccion incorrecta");

							encontrado = true;

						} catch (InputMismatchException ex) {
							System.out.println("Introduzca 1 o 2 !!!");
							encontrado = false;
							entrada.nextLine();
						}

					} while (seleccionar != 1 && seleccionar != 2);

					if (seleccionar == 1) // Elegir por DNI

						do { // Bucle para la correcta introduccion del DNI

							entrada.nextLine(); // Vaciamos el buffer

							System.out.println("Que alumno ha faltado el dia completo?:");
							MenuAlumnos.listarAlumnos(alumnos); // mostramos los alumnos

							System.out.println("Introduzca el DNI del alumno: ");
							dni = entrada.nextLine();

							seleccionar = devolverPosicion(alumnos, dni);

							if (seleccionar == -1)

								System.out.println("DNI incorrecto");
							System.out.println("");// falto de linea

						} while (seleccionar == -1); // Si ha devuelto -1 es que no lo ha encontrado

					else // Elegir por lista

						do { // bucle que repite si el usuario mete alguna letra

							do { // Bucle para la correcta introduccion del Num. de lista
								try {
									System.out.println("Que alumno ha faltado a una sesion?:");
									MenuAlumnos.listarAlumnos(alumnos); // mostramos los alumnos

									System.out.println("");// salto de linea
									System.out.println("Introduzca la posicion: ");
									seleccionar = entrada.nextInt();

									if (seleccionar < 0 || seleccionar > alumnos.size() - 1) // Comprobamos posicion

										System.out.println("Posicion incorrecta");
									encontrado = true;

								} catch (InputMismatchException ex) {
									System.out.println("Introduzca el numero del alumno !!!");
									encontrado = false;
									entrada.nextLine();
								}
							} while (seleccionar < 0 || seleccionar > alumnos.size() - 1);

						} while (encontrado == false); // final del bucle que repite el numero del alumno

				} while (encontrado == false); // final del bucle que repite el dni y numero del alumno
				// Le aniadimos la faltas
				do {
					try { // comprobamos que la sesion se a introducido correctamente

						Fecha fecha = new Fecha(dia, mes, ano);
						DiaClase falta = new DiaClase(fecha);

						System.out.println("Introduzca la sesion en la que falta el alumno:");
						falta.getHorario().faltaHora(entrada.nextInt());
						alumnos.get(seleccionar).getFaltas().add(falta);

						encontrado = true;

					} catch (Exception ex) {
						System.out.println("Sesion entre 1 y 6: ");
						encontrado = false;
						entrada.nextLine();
					}
				} while (encontrado == false);
				System.out.println("Falta puesta correctamente");

				do { // Bucle para decidir si continuar o no
					try {

						System.out.println("Desea continuar? (1 - Si, 2 - No): ");
						numContinuar = entrada.nextInt();
						encontrado = true;

					} catch (InputMismatchException ex) {

						System.out.println("Introduzca entre 1 o 2 !!!");
						encontrado = false;
						entrada.nextLine();// vaciamos buffer

					}
					while (encontrado == false)
						;

				} while (numContinuar != 1 && numContinuar != 2);

				entrada.nextLine(); // Vaciamos buffer para la siguiente iteraccion

			} while (numContinuar != 2); // final de repetir si continua o no

		} while (encontrado == false); // final de repetir falta dia completo
	}

	/*** Metodo 11: Pasar lista - Manolo ***/
	/*
	 * Preguntamos si ha venido cada uno de los alumnos y al final mostramos cuantos
	 * han faltado
	 */
	public static void pasarLista(ArrayList<Alumno> listaAlumnos) throws Exception {

		Scanner entrada = new Scanner(System.in);

		// Variables
		Alumno alumnoAListar;
		int numeroLista, contadorLista = 0;

		// Excepcion: no hay alumnos en la lista
		hayAlumnos(listaAlumnos);

		// Ciclo para sacar los alumnos
		for (int i = 0; i < listaAlumnos.size(); i++) {

			alumnoAListar = listaAlumnos.get(i);

			// Filtro para asegurarse que el usuario introduce opcion correcta
			do {

				// Preguntamos si se encuentra el alumno
				System.out.println("Se encuentra " + alumnoAListar.getNombre() + " - " + alumnoAListar.getDni()
						+ " ?\n1 - Si\n2 - No\n");
				numeroLista = entrada.nextInt();

				// Si introducimos un numero que no sea ni 1 ni 2
				if ((numeroLista < 1) || (numeroLista > 2)) {
					System.out.println("Error, numero incorrecto");
				}
			} while ((numeroLista < 1) || (numeroLista > 2));

			// Contador para las ausencias
			if (numeroLista == 2) {
				contadorLista++;
			}
		}

		// Informacion
		System.out.println("Han faltado " + contadorLista + " alumno/s");
	}

	// Metodo 12: Listar Faltas - Alejandro Fandila Cano
	public static void listarFaltas(ArrayList<Alumno> listaAlumnos) throws Exception {

		Scanner entrada = new Scanner(System.in);

		// Declaracion de variables
		int seleccionar;
		char[] faltas;
		faltas = new char[6];
		boolean excepcion = false;

		// Excepcion: no hay alumnos en la lista
		hayAlumnos(listaAlumnos);

		// Excepcion
		if (listaAlumnos.size() < 1) {
			throw new Exception("Error: no hay alumnos en la lista");
		} else {
			// Seleccionamos un alumno
			System.out.println("Elija un alumno:");
			MenuAlumnos.listarAlumnos(listaAlumnos);
		}

		// Excepcion InputMismatchException
		do {
			try {

				excepcion = false;

				seleccionar = entrada.nextInt();

				for (int i = 0; i < listaAlumnos.get(seleccionar).getFaltas().size(); i++) {
					faltas = listaAlumnos.get(seleccionar).getFaltas().get(i).getHorario().getSesiones();
					listaAlumnos.get(seleccionar).getFaltas().get(i).getDia().imprimeFecha();
					for (int k = 0; k < 6; k++) {
						if (faltas[k] == 'F') {
							System.out.println("Sesion " + (k + 1));
							System.out.println("F");
						}
					}
				}

			} catch (InputMismatchException ex) {
				excepcion = true;
				System.out.println("Introduzca un numero: ");
				entrada.next();
			} catch (IndexOutOfBoundsException ex2) {
				System.out.println("No ha seleccionado ningun alumno.");
			}

		} while (excepcion == true);
	}

	/*** Main ***/
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);

		// Variables
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		boolean salir = false, opcionCorrecta;
		int opcion = 0;

		// Si se desea probar el programa
		/*
		 * Alumno alumno1 = new Alumno("12345X", "Antonio", "Lopez");
		 * listaAlumnos.add(alumno1); Alumno alumno2 = new Alumno("12345Y", "Pepe",
		 * "Perez"); listaAlumnos.add(alumno2); Alumno alumno3 = new Alumno("12345Z",
		 * "Carlos", "Fernandez"); listaAlumnos.add(alumno3);
		 */

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

				// Llamamos al metodo
				MenuAlumnos.darAlta(listaAlumnos);

				System.out.println("\n");

				break;

			case 2:

				// Llamamos al metodo
				if (listaAlumnos.size() > 0) {
					MenuAlumnos.darBaja(listaAlumnos);
					System.out.println("Se ha dado de baja al alumno.");
				} else {
					System.out.println("No hay alumnos.");
				}

				System.out.println("\n");

				break;

			case 3:

				// Llamamos al metodo
				try {
					listarAlumnos(listaAlumnos);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

				System.out.println();

				break;

			case 4:

				// Llamamos al metodo
				try {
					modificarAlumnos(listaAlumnos);
				} catch (InputMismatchException ex) {
					System.out.println("Caracter no valido");
				} catch (IndexOutOfBoundsException ex) {
					System.out.println("Numero no valido");
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

				System.out.println();

				break;

			case 5:

				// Llamamos al metodo
				try {
					altaAsignatura(listaAlumnos);
				} catch (InputMismatchException ex) {
					System.out.println("Caracter no valido");
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

				System.out.println();

				break;

			case 6:

				// Llamamos al metodo
				try {
					bajaAsignatura(listaAlumnos);
				} catch (InputMismatchException ex) {
					System.out.println("Caracter no valido");
				} catch (IndexOutOfBoundsException ex) {
					System.out.println("Numero no valido");
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

				System.out.println();

				break;

			case 7:

				// Llamamos al metodo
				try {
					introducirCalificacion(listaAlumnos);
				} catch (InputMismatchException ex) {
					System.out.println("Caracter no valido");
				}

				System.out.println();

				break;

			case 8:

				// Llamamos al metodo
				mostrarCalificaciones(listaAlumnos);

				System.out.println();

				break;

			case 9:

				// Llamamos al metodo
				try {
					faltaDiaCompleto(listaAlumnos);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

				System.out.println();

				break;

			case 10:

				// Llamamos al metodo
				try {
					faltaHora(listaAlumnos);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

				System.out.println();

				break;

			case 11:

				// Llamamos al metodo
				try {
					pasarLista(listaAlumnos);
				} catch (InputMismatchException ex) {
					System.out.println("Caracter no valido");
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

				System.out.println();

				break;

			case 12:

				// Llamamos al metodo
				try {
					MenuAlumnos.listarFaltas(listaAlumnos);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

				System.out.println();

				break;

			case 13:

				System.out.println("Has salido");

				salir = true;

				break;

			default:

				System.out.println("Opcion incorrecta");

				break;
			}

		} while (!salir); // fin
	}
}
