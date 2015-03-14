package dondelodejo.com

class Tarjeta {
	 
	Long 	numero
	Date 	vencimiento 
	String 	nombreTitular
	Long 	clave
	String 	domicilioFacturacion
	
    static constraints = {
		numero					nullable:false,blank:false
		vencimiento				nullable:false,blank:false
		nombreTitular			nullable:false,blank:false
		clave					nullable:false,blank:false
		domicilioFacturacion	nullable:false,blank:false
    }
	
	/**
	 * Esta funcion en realidad será un servicio al que se le pase una tarjeta.
	 * @return
	 */
	def validar(){}
}
