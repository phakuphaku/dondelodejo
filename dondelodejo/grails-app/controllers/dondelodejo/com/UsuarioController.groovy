package dondelodejo.com

import org.springframework.dao.DataIntegrityViolationException

class UsuarioController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def crear() {
		//		[usuarioInstance: new Usuario(params)]
		def u=new Usuario()
		return [usuario:u]
	}

	def guardar() {
		def usuarioInstance = new Usuario(params)
		if (usuarioInstance.guardar()) {
			flash.message=message(code: 'usuario.crear.mensaje.ok')
			flash.message_type = "S"
			redirect (controller:"login", action:"index")
			return
		}
		flash.message = message(code: 'usuario.crear.mensaje.error')
		flash.message_type = "E"
		redirect(action: "crear")
	}

}
