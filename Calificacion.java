
// Clase Calificación. Autor: David Toral

public class Calificacion {

	// Atributos

	String asignatura;
	String nota;

	// Método constructor que recibe el String asignatura por parámetros

	public Calificacion(String asignatura) {

		this.asignatura = asignatura;
	}

	// Método setAsignatura (se le pasa la asignatura por parámetros)

	public void setAsignatura(String asignatura) {

		this.asignatura = asignatura;
	}

	// Método getAsignatura

	public String getAsignatura() {

		return asignatura;
	}

	// Método setNota (se le pasa la nota por parámetros)

	public void setNota(String nota) {

		this.nota = nota;
	}

	// Método getNota

	public String getNota() {

		return nota;
	}

	// Sobrecarga del método equals de la clase Object
	
	public boolean equals(Object object) {
		
		boolean igual = false;
		if (object instanceof Calificacion) {
			
			Calificacion calificacion = (Calificacion) object;
			if (calificacion.getAsignatura().equalsIgnoreCase(this.getAsignatura()))
				
				igual = true;
		}
		
		return igual;
	}

}
