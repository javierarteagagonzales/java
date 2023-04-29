package poo;

public class Uso_Vehiculo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Este coche tiene "+ Renault.ruedas+ " ruedas");
		//System.out.println("Este coche pesa "+ Renault.peso+" kg");
		/*
		//INSTANCIAR UNA CLASE
		Coche Renault=new Coche();
		//System.out.println(Renault.dime_largo());
		
		//acceder a getter y setter
		Renault.establece_color("marrón"); //Paso de parámetros
		System.out.println(Renault.dime_color());
		
		System.out.println(Renault.dime_datos_generales());
		
		//asientos de cuero
		Renault.configura_asientos("si");
		
		System.out.println(Renault.dime_asientos());
		*/
		//************************
		Coche micoche1=new Coche();
		micoche1.establece_color("Rojo");
		Furgoneta mifurgoneta1=new Furgoneta(7, 580);
		mifurgoneta1.establece_color("azul");
		mifurgoneta1.configura_asientos("si");
		System.out.println(micoche1.dime_datos_generales()+ " "+micoche1.dime_color());
		System.out.println(mifurgoneta1.dime_datos_generales()+" "+mifurgoneta1.dimeDatosFurgoneta());
	}

}
