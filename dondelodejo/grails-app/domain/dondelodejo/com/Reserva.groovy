package dondelodejo.com
import dondelodejo.com.Estacionamiento
import dondelodejo.com.Usuario
//import dondelodejo.com.calificaciones.CalificaOper
//import dondelodejo.com.calificaciones.CalificaCliente

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

	CalificacionNueva calificacion
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
	public Reserva[] listadoPorEstacionamientoYUsuario(Long idEstacionamiento,Long idUsuario){
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
