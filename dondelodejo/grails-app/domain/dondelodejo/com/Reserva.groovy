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
	 * @Cancelada" 	Aunque haya seguido los pasos el administrador la rechazo.
	 * @Aceptada" 	El administrador dio su consentimiento.
	 */
	String estado="Cargada"


	//	La calificación ahora pertenece sólo a la reserva y al estacionamiento
	//	CalificaOper calificacionOper
	//	CalificaCliente calificacionCliente

	//	Calificacion calificacionCliente

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


//	static ArrayList<Reserva> listadoByEstacionamientoYUsuario(Long idEstacionamiento,Long idUsuario){
//		println "LISTADO " + idEstacionamiento + idUsuario
//		Integer cantidad=0
//
//		Object[] objArray = Estacionamiento.get(idEstacionamiento).reservas.toArray()
//
//		int i=0;ArrayList<Reserva> reserva = new ArrayList<Reserva>();
//		for(i; i < objArray.length ; i++){
//			reserva.add(objArray[i])
//		}
//		return reserva
//	}

	def getID() {
		return this.id;
	}


}
