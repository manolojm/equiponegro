//Autor:Juan Paez
public class Horario {
		
		//Declaramos los atributos
		private char [] sesiones = new char [6];
		
		//Metodos
		//Constructor por defecto
		public Horario (){
			for (int i=0;i<6;i++){
				sesiones[i]=' ';
			}
		}
		
		//Devolvemos el array de caracteres de sesiones con el metodo get
		public char[] getSesiones(){
			
			return sesiones;
			
		}
		
		//Recorremos el array y ponemos 'F' a todo el dia
		public void faltaDiaEntero(){
			for(int i=0;i<6;i++){
				sesiones[i]='F';
			}
		}
		
		//Poner 'F' a una sesion, entre el valor 1 y 6
		public void faltaHora(int sesion){
			try{
				sesiones[sesion-1]='F';
			}catch (Exception ex){
				System.out.println("Valor entre 1 y 6. :" + ex.getMessage());
			}
		}
		
		//Mostramos el array de sesiones con el formato F/F//F//F.	
		public void imprimeHorario(){
			
			for (int i=0;i<6;i++){
				if(i==5){
					System.out.print(sesiones[i+1]);
				}else{ 
				System.out.print(sesiones[i+1] + "/");
				}
			}
			
		}
		
}
