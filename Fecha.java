
// Autor: Antonio Ramón Mirallas Jiménez

import java.lang.*;

public class Fecha {

	// Atributos

	private int dia;
	private int mes;
	private int year;

	// Constructores

	public Fecha() {

		this.dia = 1;
		this.mes = 1;
		this.year = 2016;
	}

	public Fecha(int dia, int mes, int year) throws Exception {

		if (year < 2016) // Si año es menor que 2016 fecha incorrecta

			throw new Exception("Error: Año incorrecto.");

		switch (mes) {
		// Meses con 31 días
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:

			if (dia < 1 || dia > 31)

				throw new Exception("Error: Día del mes incorrecto.");

			break;

		// Meses con 30 días
		case 4:
		case 6:
		case 9:
		case 11:

			if (dia < 1 || dia > 30)

				throw new Exception("Error: Día del mes incorrecto.");

			break;

		case 2: // Febrero, que depende de bisiesto para 28 o 29 días

			if (esBisiesto(year) && (dia < 1 || dia > 29))

				throw new Exception("Error (año bisiesto): Día del mes incorrecto.");

			if (!esBisiesto(year) && (dia < 1 || dia > 28))

				throw new Exception("Error: Día del mes incorrecto.");

			break;

		default: // Si mes no está entre 1 y 12 error

			if (1 > mes || mes > 12)
				throw new Exception("Error: Mes incorrecto.");

			break;

		}

		this.dia = dia;
		this.mes = mes;
		this.year = year;

	}

	// Getters

	public int getDia() {

		return this.dia;
	}

	public int getMes() {

		return this.mes;
	}

	public int getYear() {

		return this.year;
	}

	// Imprimir fecha
	public void imprimeFecha() {

		System.out.println(this.dia + "/" + this.mes + "/" + this.year);
	}

	private boolean esBisiesto(int year) { // Comprobar si es año bisiesto. Método privado

		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return true;
		else
			return false;
	}

	// Sobrecarga del método equals de la clase Object

	public boolean equals(Object object) {

		boolean igual = false;

		if (object instanceof Fecha) {

			Fecha fecha = (Fecha) object;

			if (fecha.getDia() == this.getDia() && fecha.getMes() == this.getMes() && fecha.getYear() == this.getYear())
				igual = true;
		}

		return igual;
	}
}
