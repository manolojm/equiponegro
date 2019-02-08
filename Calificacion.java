
// Clase Calificaci�n. Autor: David Toral

public class Calificacion {

	// Atributos

	private String asignatura;
	private String nota;

	// M�todo constructor que recibe el String asignatura por par�metros

	public Calificacion(String asignatura) {

		this.asignatura = asignatura;
	}

	// M�todo setAsignatura (se le pasa la asignatura por par�metros)

	public void setAsignatura(String asignatura) {

		this.asignatura = asignatura;
	}

	// M�todo getAsignatura

	public String getAsignatura() {

		return asignatura;
	}

	// M�todo setNota (se le pasa la nota por par�metros)

	public void setNota(String nota) {

		this.nota = nota;
	}

	// M�todo getNota

	public String getNota() {

		return nota;
	}

	// Sobrecarga del m�todo equals de la clase Object
	
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
