package poo;

public class Coche {
	private int ruedas; //solo es modificable desde la propia clase
	private int largo;
	private int ancho;
	private int motor;
	private int peso_plataforma;
	
	private String color;
	private int peso_total;
	private boolean asientos_cuero,climatizador;
	
	//método
	public Coche() {
		ruedas=4;
		largo=2000;
		ancho=300;
		motor=1600;
		peso_plataforma=500;
	}
	
	//método getter
	public String dime_datos_generales() {
		return "La plataforma del vehículo tiene "+ruedas+ " ruedas"
	+". Mide "+largo/1000 + " metros con un ancho de "+ancho+ "cm.";
	}
	
	//método setter: modificar el valor de los objetos
	public void establece_color(String color_coche) {
		color = color_coche; //paso de parámetros
		//color="azul";
	}
	
	//método getter
		public String dime_color() {
			return "El color del coche es: "+ color;
		}

	public void configura_asientos(String asientos_cuero){
			if(asientos_cuero=="si") {
				this.asientos_cuero=true;
			}else {
				this.asientos_cuero=false;
			}
	}
	
	public String dime_asientos() {
		if(asientos_cuero=true) {
			return "El coche tiene asientos de cuero";
		}else {
			return "El coche tiene asientos de serie";
		}
	}

			 

}
