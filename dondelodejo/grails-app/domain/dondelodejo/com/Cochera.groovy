package dondelodejo.com

import java.util.Formatter.DateTime


class Cochera {

	static final String TIPO_VEHICULO_AUTO="AUTO"
	static final String ESTADO_LIBRE="L"
	static final String ESTADO_OCUPADA="O"
	static final Integer FRACCION_HORARIA=10
	static belongsTo = [estacionamiento:Estacionamiento];
	/** Aca tendremos el numero con que esta numerada en el establecimiento. 
	 * Ya que no tienen porque estar dadas de alta en orden.
	 */
	int numero;

//* Para Sacar	
//	Date fechaAlta;
//	/** tamanio dira para que tipo de vehiculo nos sirve esta cochera */
//	String tipoVehiculo;
//	/** indica el ancho en metros de la cochera */
//	float ancho;
//	
//	float alturaMaxima;
	float precioCocheraHora;
	float precioCocheraFraccion
//	int nivel;
	String estado
	Date horaOcupacion
	Date horaLiberacion

// Para Sacar		
//	int compareTo(Cochera obj) {
//		numero.compareTo(obj.numero)
//	  }
	
    static constraints = {
		numero			(nullable:false)
//		alturaMaxima	(nullable:true,disable:true,max: 5F,min:0F)
//		tipoVehiculo	(nullable:false,inList: ["BICI", "MOTO", TIPO_VEHICULO_AUTO , "4X4", "BUS"],defaultValue:TIPO_VEHICULO_AUTO)
//		fechaAlta		(nullable:false,disable:true,defaultValue:new Date())
		estado			(nullable:true,inList: [ESTADO_OCUPADA,ESTADO_LIBRE],defaultValue:ESTADO_LIBRE)
//		nivel 			nullable:true
		precioCocheraHora		nullable:false
		precioCocheraFraccion	nullable:false
//		ancho			nullable:true
		horaOcupacion	nullable:true
		horaLiberacion	nullable:true
    }
	
	boolean estaOcupada(){
		this.estado.equals(ESTADO_OCUPADA)
	}
	/** 
	 * Busca cocheras libres, por un estacionamiento dado
	 * @param idEstacionamiento
	 * @return Cantidad de cocheras Libres
	 */
	static Integer cocherasLibres(Long idEstacionamiento){
		LoggerService.Log( "COCHERASLIBRES " + idEstacionamiento)
		Integer cantidad=0
		
		Object[] objArray = Estacionamiento.get(idEstacionamiento).cocheras.toArray()

		int i=0;Cochera cochera;
		for(i; i < objArray.length ; i++){
			cochera = (Cochera)objArray[i]
			if (!cochera.estaOcupada()) cantidad++
		}
		return cantidad
	}
	
	def ocupar(){
	//si esta ocupada deberia tirar excepcion
		this.estado = ESTADO_OCUPADA
		this.horaOcupacion=new Date()
		this.horaLiberacion=null
	}
	Date liberar(){
		this.estado = ESTADO_LIBRE
		this.horaLiberacion=new Date()
	}
	String cobrar(){
		Long ocupacion = (this.horaLiberacion.getTime() - this.horaOcupacion.getTime())
		Long ocupacionMinutos = ocupacion / 60000 //hago pelota los segundo y milisegundos 
		long diffHoras = ocupacionMinutos / 60
		LoggerService.Log( "Horas "+diffHoras)
		long diffMinutos = ocupacionMinutos - (diffHoras*60);
		LoggerService.Log( "Minutos "+diffMinutos)
		float aCobrarHora = precioCocheraHora * (diffHoras)
		long fraccionesHorarias =(diffMinutos / FRACCION_HORARIA)
		float aCobrarFraccion = precioCocheraFraccion * (fraccionesHorarias+1)
		float resultado = aCobrarHora+aCobrarFraccion
		resultado.toString()
	}
	static String estadoLibre(){ESTADO_LIBRE}
//* Para Sacar
// 	static String estadoOcupada(){ESTADO_OCUPADA}
	
	String toString(){
		return "Cochera No: "+String.valueOf(numero?numero:id);
	}
}
