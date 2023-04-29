package poo;

import java.util.*;

public class Uso_Empleado {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Empleado empleado1= new Empleado("Paco Gomez",85000,1990,12,17);
		Empleado empleado2= new Empleado("Ana Lopez",95000,1995,6,2);
		Empleado empleado3= new Empleado("María",105000,2002,3,15);
		
		empleado1.subeSueldo(5);
		empleado2.subeSueldo(5);
		empleado3.subeSueldo(5);
		
		System.out.println("Nombre: " + empleado1.dameNombre()+" Sueldo: "+empleado1.dameSueldo()
		+ " Fecha de Alta: "+empleado1.dameFechaContrato());
		System.out.println("Nombre: " + empleado2.dameNombre()+" Sueldo: "+empleado2.dameSueldo()
		+ " Fecha de Alta: "+empleado2.dameFechaContrato());
		System.out.println("Nombre: " + empleado3.dameNombre()+" Sueldo: "+empleado3.dameSueldo()
		+ " Fecha de Alta: "+empleado3.dameFechaContrato());
		*/
		
		//Por ARRAYS
		
		Jefatura jefe_RRHH=new Jefatura("Juan",55000,2006,9,25);
		jefe_RRHH.estableceIncentivo(2570);
		
		Empleado[] misEmpleados=new Empleado[6];
		misEmpleados[0]=new Empleado("Paco Gomez",30000,1990,12,17);
		misEmpleados[1]=new Empleado("Ana Lopez",31000,1995,6,2);
		misEmpleados[2]=new Empleado("María",32000,2002,3,15);
		misEmpleados[3]=new Empleado("Javier");
		misEmpleados[4]=jefe_RRHH; //Polimorfismo, principio de sustitución
		misEmpleados[5]=new Jefatura("María",95000,1999,5,26);
		
		Jefatura jefa_Finanzas=(Jefatura) misEmpleados[5]; //Casting
		jefa_Finanzas.estableceIncentivo(5000);
		
		System.out.println(jefa_Finanzas.tomar_decisiones("Dar más días de vacaciones a empleados."));
		
		System.out.println("El jefe "+ jefa_Finanzas.dameNombre()+ " tiene un bonus de: "+jefa_Finanzas.establece_bonus(500));
		
		System.out.println(misEmpleados[3].dameNombre()+" tiene un bonus de: "+misEmpleados[3].establece_bonus(200));
		
		/*
		 * 
		 * USO DE INSTANCE OF
		Empleado director_comercial=new Jefatura("Sandra",85000,2012,5,5);
		
		Comparable ejemplo = new Empleado("Elizabeth",95000,2011,5,7);
		
		if(director_comercial instanceof Empleado) {
			System.out.println("Es de tipo Jefatura");;
		}
		
		if(ejemplo instanceof Comparable) {
			System.out.println("Implementa la interfaz comparable");;
		}
		*/
		
		/*for(int i=0;i<3;i++) {
			misEmpleados[i].subeSueldo(5);
		}*/
		for(Empleado e: misEmpleados) {
			e.subeSueldo(5);
		}
		
		/*for(int i=0;i<3;i++) {
			System.out.println("Nombre: " + misEmpleados[i].dameNombre()+" Sueldo: "+misEmpleados[i].dameSueldo()
			+ " Fecha de Alta: "+misEmpleados[i].dameFechaContrato());
		}*/
		
		//ordenar objetos
		Arrays.sort(misEmpleados);
		
		for(Empleado e: misEmpleados) {
			System.out.println("Nombre: " + e.dameNombre()+" Sueldo: "+e.dameSueldo() //enlazado dinámico
					+ " Fecha de Alta: "+e.dameFechaContrato());
		}
		
	}

}

class Empleado implements Comparable, Trabajadores{//implementar la intefaz comparable y trabajadores
	//método constructor con parámetros
	public Empleado(String nom, double sue, int agno,int mes, int dia) {
		nombre=nom;
		sueldo=sue;
		GregorianCalendar calendario = new GregorianCalendar(agno,mes-1,dia);
		altaContrato=calendario.getTime();
	}
	
	//desarrollar el metod de Trabajadores
	public double establece_bonus(double gratificacion) {
		return Trabajadores.bonus_base+gratificacion;
	}
	
	//sobrecarga de constructores
	public Empleado(String nom) {
		//nombre=nom;
		this(nom,3000,2000,01,01);
	}
	
	public String dameNombre() {//getter
		return nombre;
		
	}
	public double dameSueldo() {//getter
		return sueldo;
	}
	public Date dameFechaContrato() {//getter
		return altaContrato;
	}
	public void subeSueldo(double porcentaje) {//setter
		double aumento=sueldo*porcentaje/100;
		sueldo+=aumento;
	}

	//***********************
	//Para ordenar por sueldo
	public int compareTo(Object miObjeto) {
		Empleado otroEmpleado=(Empleado) miObjeto; //casting
		if(this.sueldo<otroEmpleado.sueldo) {
			return -1;
		}
		if(this.sueldo>otroEmpleado.sueldo) {
			return 1;
		}
		return 0;
	}
	//****************
	
	private String nombre;
	private double sueldo;
	private Date altaContrato;
}

class Jefatura extends Empleado implements Jefes {
		
	public Jefatura(String nom, double sue,int agno, int mes, int dia) {
		super(nom, sue, agno, mes, dia);
	}
	//desarrollar el metodo de Jefes
	public String tomar_decisiones(String decision) {
		return "Un miembro de la dirección ha tomado la decision de: "+decision;
	}
	
	//desarrollar el metodo de Trabajadores
	public double establece_bonus(double gratificacion){
		double prima=2000;
		return Trabajadores.bonus_base+gratificacion+prima;
	}
	
	public void estableceIncentivo(double b){
		incentivo=b;
	}
	public double dameSueldo() {
		double sueldoJefe=super.dameSueldo();
		return sueldoJefe + incentivo;
	}
	private double incentivo;
	
}

final class Director extends Jefatura{
	public Director(String nom, double sue,int agno, int mes, int dia){
		super(nom, sue, agno, mes, dia);
	}
}

