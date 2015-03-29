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
	
}
