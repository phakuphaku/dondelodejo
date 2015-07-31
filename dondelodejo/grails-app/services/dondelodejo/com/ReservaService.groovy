package dondelodejo.com

import java.util.Map;

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
	def Reserva[] listadoReservas (Long idEstacionamiento,Long idUsuario) {
		return Reserva.listadoPorEstacionamientoYUsuario(idEstacionamiento,idUsuario)
	}
	def Reserva[] listadoReservasParaClientes (Long idEstacionamiento,Long idUsuario,boolean bool) {
		return Reserva.listadoPorEstacionamientoYUsuarioYEstadosVisiblesParaClientes(idEstacionamiento,idUsuario,bool)
	}
	def Reserva[] listadoReservasParaAdministrador (Long idEstacionamiento,boolean truefalse) {
		return Reserva.getReservasPorEstacionamientoYEstadosVisiblesParaAdministradores(idEstacionamiento,truefalse)
	}
	def crearReserva(Map mapa) {
		LoggerService.Log("ingreso a CREARRESERVA con "+mapa.toString())
		
		Reserva reserva = new Reserva(mapa["reserva"])
		def estacionamientoId = mapa["estacionamientoId"]
		def usuario = mapa["usuario"]
		
		try{
			//creo la nueva reserva
			Estacionamiento.get(estacionamientoId).addToReservas(reserva)
			Usuario.get(((Usuario)usuario).id).addToReservas(reserva)
			//De acuerdo a lo aprendido, estacionamiento y usuario ya estan guardados.
			reserva.save()
		}catch (Exception e){
			LoggerService.Log("Error en la creacion de reserva")
			throw new RuntimeException("DATOS INSUFICIENTES PARA LA OPERACION")
		}
		LoggerService.Log("RESERVA ACEPTADA")
		return reserva.id
	}
}
