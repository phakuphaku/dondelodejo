package dondelodejo.com

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import groovy.lang.Closure;

class TarjetaController {

	def clienteService
	//el controlador es completamente privado.
	def beforeInterceptor = [action:this.&checkUser]
	def checkUser() {
		if(!session.getAttribute("usuario")) {
		// i.e. user not logged in
		redirect(controller:'login',action:'verificarUsuario')
		return false
		}
	}
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Tarjeta.list(params), model:[tarjetaInstanceCount: Tarjeta.count()]
    }

    def mostrar(Tarjeta tarjetaInstance) {
        respond tarjetaInstance
    }

    def crear() {
        new Tarjeta(params)
    }

    def guardar(Tarjeta tarjetaInstance) {
        if (tarjetaInstance == null) {
            return
        }

        if (tarjetaInstance.hasErrors()) {
            respond tarjetaInstance.errors, view:'create'
            return
        }

        tarjetaInstance.save flush:true
		Usuario.get(session.usuario.id).addToTarjetas(tarjetaInstance)
		flash.message = message(code: 'default.created.message', args: [message(code: 'tarjeta.label', default: 'Tarjeta'), tarjetaInstance.id])
		redirect controller:"estacionamiento",action: "cliente", method: "GET"
    }

    def editar(Tarjeta tarjetaInstance) {
        respond tarjetaInstance
    }

    def actualizar(Tarjeta tarjetaInstance) {

        tarjetaInstance.save flush:true
		flash.message = message(code: 'default.updated.message', args: [message(code: 'Tarjeta.label', default: 'Tarjeta'), tarjetaInstance.id])
		redirect controller:"estacionamiento" ,action:"cliente"
    }

    def borrar() {
        conTarjeta { tarjeta ->
			if (clienteService.borrarTarjeta(Long.valueOf(params.get("id")),session.usuario.id)) {
				//borrado exitoso
				println "borrado exitoso"
				flash.message = "La tarjeta fue borrada exitosamente."
			} else {
				//error en el borrado
				flash.message = "La tarjeta no fue borrada."
			}
		redirect controller:"estacionamiento",action: "cliente", method: "GET"
        }
    }
	
	private def conTarjeta(id="id", Closure c) {
		def tarjeta = clienteService.buscarTarjeta([id:params[id]])
		if(tarjeta) {
			c.call tarjeta
		} else {
			flash.message = "El estacionamiento no fue encontrado."
			redirect action:"listado"
		}
	}
}
