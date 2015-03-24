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
	 * @Cargada		Estado inicial.
	 * @Cancelada 	Aunque haya seguido los pasos el administrador la rechazo.
	 * @Aceptada 	El administrador dio su consentimiento.
	 * @Calificada  El usuario la calificó.
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


void calificar (Usuario user, int valor, char detalle) {

	CalificacionNueva calificacion = new CalificacionNueva(["valor":valor, "detalle":detalle])
	this.calificacion = calificacion
	int cantidad = Reserva.findByEstacionamientoAndEstado(this.estacionamiento.id,"Calificada").size()+1
	int puntaje = this.estacionamiento.puntaje
	def nuevoPuntaje = (puntaje + valor)/cantidad	
	this.estacionamiento.puntaje = nuevoPuntaje
	
	}

}