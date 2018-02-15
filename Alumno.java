// Clase Alumno. Autor: Manuel Antonio Jiménez

import java.util.ArrayList;
import java.util.List;

public class Alumno {
	
	// Atributos
	private String dni;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String email;
	private ArrayList<DiaClase> faltas;
	private ArrayList<Calificacion> notas;
	
	// Constructor 
	public Alumno(String dni) {
		this.dni = dni;
		this.faltas = new ArrayList<DiaClase>();
		this.notas = new ArrayList<Calificacion>();
	}
	
	// Constructor
	public Alumno(String dni, String nombre, String apellidos) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.faltas = new ArrayList<DiaClase>();
		this.notas = new ArrayList<Calificacion>();
	}

	// Metodos get
	public String getDni() {
		return dni;
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
	
	public ArrayList<Calificacion> getNotas(){
		
		return notas;
	}
	
	// Metodos set
	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setNotas(ArrayList<Calificacion> notas) {
		
		this.notas = notas;
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
	
	//Metodo comprobar si existe el alumno - Autor: Alejandro Fandila Cano
		public boolean existe(ArrayList<Alumno> alumnos) {
			boolean existe = false;
			
			for(int i = 0;i < alumnos.size() && existe == false;i++) {
				if(this.equals(alumnos.get(i))) {
					existe = true;
				}
			}
			
			return existe;
		}
}
