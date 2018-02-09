//Autor: Alejandro Fandila Cano. 

public class DiaClase {
	
	//Variables
	private Fecha dia;
	private Horario sesiones;
	
	//Constructor con parametros FechaDia
	public DiaClase(Fecha dia) {
		this.dia = dia;
		Horario sesiones = new Horario();
	}
	
	//Metodos 	
	
	//1) getters 
	public Fecha getDia() {
		return dia;
	}
	
	public Horario getHorario() {
		return sesiones;
	}
	
	// 2) Set dia
	public void setDia(int dias, int mes, int año) {
		this.setDia(dias, mes, año);
	}
	
	// Sobrecarga del método equals de la clase Object
	public boolean equals(Object object){
	 boolean igual = false;

		 if(object instanceof DiaClase){
			 DiaClase diaClase = (DiaClase) object;
			 if(diaClase.getDia().equals(this.getDia()))
			 igual = true;
		 }
		 return igual;
	 }


}
