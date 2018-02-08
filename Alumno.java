// Clase Alumno. Autor: Manuel Antonio Jiménez

import java.util.ArrayList;

public class Alumno {
	
	// Atributos
	private String dni;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String email;
	//private ArrayList<DiaClase> faltas;
	private ArrayList<Calificacion> notas;
	
	// Constructor 
	public Alumno(String dni) {
		this.dni = dni;
	}
	
	// Constructor
	public Alumno(String dni, String nombre, String apellidos) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		//this.faltas = new ArrayList<DiaClase>();
		this.notas = new ArrayList<Calificacion>();
	}

	// Metodos get
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}
	
	// Sobrecarga del método equals de la clase Object
	public boolean equals(Object object){
		boolean igual = false;

		if(object instanceof Alumno){
			Alumno alumno = (Alumno) object;
			
		if(alumno.getDni().equalsIgnoreCase(this.getDni()))
			igual = true;
		}
		
		return igual; 
	}
}
