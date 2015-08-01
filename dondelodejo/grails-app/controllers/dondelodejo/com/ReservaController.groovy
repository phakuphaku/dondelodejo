package dondelodejo.com
import dondelodejo.com.Reserva

class ReservaController {

	def usuarioService //para validaciones eventuales
	def estacionamientoService //para listar las reservas
	def reservaService
	def beforeInterceptor = [action:this.&checkUser,except:[]]
	def checkUser() {
		if(!session.getAttribute("usuario")) {
			// i.e. user not logged in
			redirect(controller:'login',action:'verificarUsuario')
			return false
		}
	}

	/*TODO: en un futuro este crear puede levantar todas 
	 * las reservas del estacionamiento para ver los horarios libres.
	 */
	def crear () {
		//creo la reserva para el filtro de creacion
		Reserva reserva = new Reserva()
		reserva.cantidadHorasReservadas=1
		Reserva[] listadoReservas=null
		if (params["id"]){
			//hago la busqueda para encontrar las reservas ya hechas.
			listadoReservas = reservaService.listadoReservas(Long.valueOf(params["id"]), ((Usuario)session.getAttribute("usuario")).id)
		}
		def out = [reservaFiltro:reserva,listadoReservas:listadoReservas,estacionamientoId:params["id"]]
		LoggerService.Log( out)
		out
	}

	public static Long getDateFromDatePicket(Map map,String s){
		Date fecha=new Date(Integer.valueOf(map.get(s+"_year"))-1900,
				Integer.valueOf(map.get(s+"_month")),
				Integer.valueOf(map.get(s+"_day")),
				Integer.valueOf(map.get(s+"_hour")),
				Integer.valueOf(map.get(s+"_minute")))
		LoggerService.Log( "SE CONVIRTIO A LA FECHA " +fecha)
		return fecha.getTime();
	}


/**
 * Verificar si se usa y en donde
 * @return
 */
	def alta () {
		LoggerService.Log(params)
		Map reservaIn = [horaDeInicio:new Date(getDateFromDatePicket(params,"horaDeInicio")),
			//			def reservaIn = [horaDeInicio:new Date(params.get("horaDeInicio")),
			cantidadHorasReservadas:params.get("cantidadHorasReservadas")]
		def reservaOut = reservaService.crearReserva(  [reserva:reservaIn,
			estacionamientoId:params["estacionamientoId"],
			usuario:session.getAttribute("usuario")])
		if(reservaOut != null) {
			flash.message = "La Reserva fue creada exitosamente."
			flash.message_type = "S"
			redirect action:"crear", id:params["estacionamientoId"]
		} else {
			flash.message = "El estacionamiento no acepto su reserva."
			flash.message_type = "E"
			render view:"crear", id:params["id"], model:[reserva:estacionamientoService.buscarReserva(reservaOut)]
		}

	}

	def borrar () {
		conReserva { reserva ->
			//baja fisica inhabilitada reserva.delete()

			if (!estacionamientoService.borrarReserva(Long.valueOf(params.get("id")))) {
				//borrado exitoso
				flash.message = "La reserva fue borrada exitosamente."
				flash.message_type = "S"
			} else {
				//error en el borrado
				flash.message = "La reserva no fue borrada."
				flash.message_type = "E"
			}
			redirect action:"crear",id:params["estacionamientoId"]

		}
	}

	private def conReserva(id="id", Closure c) {
		def reserva = estacionamientoService.buscarReserva([id:params[id]])
		if(reserva) {
			c.call reserva
		} else {
			flash.message = "El reserva no fue encontrado."
			flash.message_type = "E"
			redirect action:"listado"
		}
	}
	
	public listado() {
		Reserva[] aux = reservaService.getReservasPorEstacionamientoYClienteYEstado(params)
		[reservasInstanciaListado: aux , reservaInstanciaTotal: (List)aux.size()]
	}
	
//* Para sacar	
//	def	cliente () {
//		//TODO sin codificar. falta tener las altas de estacionamiento hechas
//		LoggerService.Log( "PERFIL CLIENTE")
//		[listadoReservas:reservaService.listadoReservasParaClientes(null, Long.valueOf(params.get("id")),session.usuario?.debenMostrarseEstadosCompletados())]
//	}
	def calificarReserva(){
		LoggerService.Log("FP: Calificar Reserva "+params.get("reservaId"))
		LoggerService.Log(params)
		
		Integer resultado_ok=0
		Integer res = reservaService.calificarReserva(
			Long.valueOf(session.usuario.id),
			Long.valueOf(params.get("reservaId")), 
			Integer.valueOf(params.get("valor")).intValue(), 
			params.get("detalle"))   
        if (res == resultado_ok){
			flash.message = "La calificacion se hizo exitosamente"
			redirect action:session.home, id:session.homeId
        }
	}
	def cambiarEstadoListadoCompleto(){
		session.usuario=usuarioService.cambiarEstadoListadoCompleto(session.usuario)
		redirect action:session.home, id:session.homeId
	}
}
