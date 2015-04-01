package dondelodejo.com

import grails.transaction.Transactional

@Transactional
class ReservaService {

	def getReservasPorEstacionamientoYClienteYEstado(Map map) {
		
		Long idEstacionamiento = map.get("idEstacionaminto")
		Long idCliente = map.get("idCliente")
		String estado= map.get("estado")
		
		Reserva.getReservasPorEstacionamientoYClienteYEstado(idEstacionamiento, idCliente, estado)
	}
	def cambiarEstado(String accion,Long id){
		Reserva reserva = Reserva.get(id)
		if (accion=="aceptar") reserva.aceptar()
		if (accion=="cancelar") reserva.cancelar()
		if (accion=="utilizar") reserva.utilizar()
		reserva.save()
	}
	def calificarReservaPorCliente(Long reservaId,int valor,String detalle){
		char origenDeLaCalificacion = 'C'
		this.calificarReserva(origenDeLaCalificacion,reservaId, valor, detalle)
	}
	def calificarReservaPorEstacionamiento(Long reservaId,int valor,String detalle){
		char origenDeLaCalificacion = 'E'
		this.calificarReserva(origenDeLaCalificacion,reservaId, valor, detalle)
	}
	/** si devuelve 0 la reserva fue calificada OK*/
	private Integer calificarReserva(char origenDeLaCalificacion,Long reservaId,int valor,String detalle){
		//TODO FACUNDO Delegar este comportamiento en la clase Reserva. 
		final char ES_UN_CLIENTE = 'C'
		final char ES_UN_ESTACIONAMIENTO = 'E'
		if (origenDeLaCalificacion.equals(ES_UN_CLIENTE)) {
			Reserva.get(reservaId).calificaClienteAlEstacionamiento(valor, detalle)
		}
		if (origenDeLaCalificacion.equals(ES_UN_ESTACIONAMIENTO)) {
			Reserva.get(reservaId).calificaEstacionamientoAlCliente(valor, detalle)
		}
		return 0
	}
}
