package dondelodejo.com
import dondelodejo.com.Estacionamiento
import dondelodejo.com.Usuario

class Reserva {

	static belongsTo = [estacionamiento:Estacionamiento,usuario:Usuario];
	Date horaDeInicio
	int  horasReservadas
	/**
	 * @Cargada		estado inicial
	 * @Cancelada" 	Aunque haya seguido los pasos el administrador la rechazo.
	 * @Aceptada" 	El administrador dio su consentimiento.
	 */
	String estado="Cargada"

	Calificacion calificacion
	static embedded = ['calificacion']

	static constraints = {
		estado (nullable:true,inList:[
			"Cargada",
			"Aceptada",
			"Cancelada"
		])
		calificacion nullable:true
	}

	def getID() {
		return this.id;
	}                
	public static Reserva[] listadoPorEstacionamientoYUsuario(Long idEstacionamiento,Long idUsuario){
		def criteria = Reserva.createCriteria()
		def results = criteria.list {
			and {
				estacionamiento{	eq('id', idEstacionamiento)}
				usuario{	        eq('id', idUsuario)}
				}
			order("horaDeInicio", "ASC")
		}
		return results
	}

}
class Calificacion {
	
		Integer valor
		String detalle
	
		static constraints = {
			valor 	 	    nullable : false, inList:[1, 2, 3, 4, 5]
			detalle 	    nullable : true
		}
	
		void calificar (Usuario administrador, Usuario cliente) {
		}
	}