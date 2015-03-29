package dondelodejo.com
import java.awt.geom.Point2D
import maps.java.Geocoding


/**
 * PATRON IMPLEMENTADO DE http://mrpaulwoods.wordpress.com/2011/01/23/a-pattern-to-simplify-grails-controllers/
 */
class EstacionamientoController {

	def clienteService
	def estacionamientoService
	def beforeInterceptor = [action:this.&checkUser,except:['index', 'mostrar', 'listado', 'buscarPorDistancia']]
	def checkUser() {
		if(!session.getAttribute("usuario")) {
			// i.e. user not logged in
			redirect(controller:'login',action:'verificarUsuario')
			return false
		}
	}

	//index
	def index() {
		redirect action:"listado"
	}

	//listado
	def listado() {
		[estacionamientoInstanciaListado: estacionamientoService.listado(params), estacionamientoInstanciaTotal: estacionamientoService.cantidad()]
	}

	//crear
	def crear () {
		def e=new Estacionamiento()
		return [estacionamiento:e]
	}

	//TODO pasar a servicio
	def guardar () {
		LoggerService.Log("GUARDAR "+params)
		Estacionamiento estacionamiento = estacionamientoService.alta(params);

		if(estacionamiento != null) {
			flash.message = "El estacionamiento fue creado exitosamente."
			flash.message_type = "S"
			redirect action:"mostrar", id:estacionamiento.id, model: [estacionamiento:estacionamiento]
		} else {
			flash.message = "El estacionamiento no fue creado."
			flash.message_type = "E"
			render view:"crear", model:[estacionamiento:estacionamiento]
		}
	}

	//TODO: El mostrar no debe devolver el objeto. Pasar a Servicio.
	def mostrar () {
		conEstacionamiento { estacionamiento ->
			LoggerService.Log("MOSTRAR "+estacionamiento.getId())
			Map map = estacionamientoService.cocherasLibres(params)
			[estacionamiento:map["estacionamiento"],cocherasLibres:map["cantidadCocherasLibres"]]
		}
	}

	//TODO: El editar no debe devolver el objeto. Pasar a Servicio.
	def editar () {
		conEstacionamiento { estacionamiento ->
			LoggerService.Log( "EDITAR "+params)
			LoggerService.Log( estacionamiento.properties)
			[estacionamiento:estacionamiento,id: params.get("id")]
		}
	}

	def actualizar () {
		conEstacionamiento { estacionamiento ->
			LoggerService.Log( "ACTUALIZAR "+params)
			Estacionamiento e = estacionamientoService.editar(params);

			if(e != null) {
				flash.message = "El estacionamiento fue modificado exitosamente."
				flash.message_type = "S"
				redirect action:"mostrar", id:e.id
			} else {
				flash.message = "El estacionamiento no fue modificado, revise los datos."
				flash.message_type = "E"
				render view:"editar", model:[estacionamiento:e,id: params.get("id")]
			}
		}
	}

	def borrar () {
		conEstacionamiento { estacionamiento ->
			//baja fisica inhabilitada estacionamiento.delete()

			if (estacionamientoService.borrar(Long.valueOf(params.get("id")))) {
				//borrado exitoso
				flash.message = "El estacionamiento fue borrado exitosamente."
				flash.message_type = "S"
			} else {
				//error en el borrado
				flash.message = "El estacionamiento no fue borrado."
				flash.message_type = "E"
			}
			redirect action:"listado"

		}
	}

	private def conEstacionamiento(id="id", Closure c) {
		def estacionamiento = estacionamientoService.buscar([id:params[id]])
		if(estacionamiento) {
			c.call estacionamiento
		} else {
			flash.message = "El estacionamiento no fue encontrado."
			flash.message_type = "E"
			redirect action:"listado"
		}
	}

	def buscarPorDistancia () {
		/* si es la crecion de la pagina, devuelvo el map fijo */
		if (!params.containsKey("buscar")) return	[direccion:"",localidad:"Capital Federal",pais:"Argentina",metros:500]
		else{//es una busqueda
			Integer metros = Integer.valueOf(params["metros"])

			List<Estacionamiento> algo = clienteService.buscarPorDistancia(params.get("direccion"),params.get("localidad"),params.get("pais"),metros)

			render view:"listadoPorDistancia",
			model:[estacionamientoInstanciaListado: algo
				,estacionamientoInstanciaTotal: algo.size()
				,metros:metros
				,direccion:params.get("direccion")]
		}
	}

	//	def operador () {
	//		conEstacionamiento { estacionamiento ->
	//			println "OPERADOR "+estacionamiento.getId()
	//			[estacionamiento:estacionamiento]
	//		}
	//	}

	def administrador () {
		conEstacionamiento { estacionamiento ->
			LoggerService.Log( "ADMINISTRADOR "+estacionamiento.getId())
			if (session.getAttribute("usuario").estacionamiento.id==estacionamiento.getId()) 
				return [estacionamiento:estacionamiento]
			else{
				//nueva parte para no permitir el ingreso a otros estacionamientos
				session.removeAttribute("usuario")
				redirect(controller:'login',action:'verificarUsuario')
				return false
			}
		}
	}

	def	cliente () {
		//TODO sin codificar. falta tener las altas de estacionamiento hechas
		LoggerService.Log( "PERFIL CLIENTE")
		[listadoReservas:clienteService.listadoReservas(null, Long.valueOf(params.get("id")))]
	}

	def cambiarEstadoCochera () {
		LoggerService.Log( params)
		def algo
		if (params["ocupar"])  algo = estacionamientoService.ocuparCochera(Long.valueOf(params["cocheraId"]))
		if (params["liberar"]) {
			algo = estacionamientoService.liberarCochera(Long.valueOf(params["cocheraId"]))
			flash.message = estacionamientoService.cobrarCochera(Long.valueOf(params["cocheraId"]))
		}
		LoggerService.Log( "ALGO " + algo)
		redirect action:"administrador", id:params["estacionamientoId"]
	}

}
