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
	private Integer calificarReserva(Long usuarioId,Long reservaId,int valor,String detalle){
		Usuario.getById(usuarioId).calificar(Reserva.get(reservaId),valor,detalle)
		return 0
	}
}
