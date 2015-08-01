package dondelodejo.com

import org.springframework.dao.DataIntegrityViolationException

class UsuarioController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

//	def list(Integer max) {
//		params.max = Math.min(max ?: 10, 100)
//		[usuarioInstanceList: Usuario.list(params), usuarioInstanceTotal: Usuario.count()]
//	}

	def crear() {
		//		[usuarioInstance: new Usuario(params)]
		def u=new Usuario()
		return [usuario:u]
	}

	def guardar() {
		def usuarioInstance = new Usuario(params)
		if (usuarioInstance.guardar()) {
			flash.message=message(code: 'usuario.crear.mensaje.ok')
			redirect (controller:"login", action:"index")
			return
		}
		flash.message = message(code: 'usuario.crear.mensaje.error')
		
		redirect(action: "crear")
	}


// TERMINA LA CLASE
		
//	def show(Long id) {
//		def usuarioInstance = Usuario.get(id)
//				if (!usuarioInstance) {
//					flash.message = message(code: 'default.not.found.message', args: [
//					                                                                  message(code: 'usuario.label', default: 'Usuario'),
//					                                                                  id
//					                                                                  ])
//					                                                                  redirect(action: "list")
//					                                                                  return
//				}
//		
//		[usuarioInstance: usuarioInstance]
//	}
//	def edit(Long id) {
//		def usuarioInstance = Usuario.get(id)
//		if (!usuarioInstance) {
//			flash.message = message(code: 'default.not.found.message', args: [
//				message(code: 'usuario.label', default: 'Usuario'),
//				id
//			])
//			redirect(action: "list")
//			return
//		}
//
//		[usuarioInstance: usuarioInstance]
//	}
//
//	def update(Long id, Long version) {
//		def usuarioInstance = Usuario.get(id)
//		if (!usuarioInstance) {
//			flash.message = message(code: 'default.not.found.message', args: [
//				message(code: 'usuario.label', default: 'Usuario'),
//				id
//			])
//			redirect(action: "list")
//			return
//		}
//
//		if (version != null) {
//			if (usuarioInstance.version > version) {
//				usuarioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
//						[
//							message(code: 'usuario.label', default: 'Usuario')] as Object[],
//						"Another user has updated this Usuario while you were editing")
//				render(view: "edit", model: [usuarioInstance: usuarioInstance])
//				return
//			}
//		}
//
//		usuarioInstance.properties = params
//
//		if (!usuarioInstance.save(flush: true)) {
//			render(view: "edit", model: [usuarioInstance: usuarioInstance])
//			return
//		}
//
//		flash.message = message(code: 'default.updated.message', args: [
//			message(code: 'usuario.label', default: 'Usuario'),
//			usuarioInstance.id
//		])
//		redirect(action: "show", id: usuarioInstance.id)
//	}
//
//	def delete(Long id) {
//		def usuarioInstance = Usuario.get(id)
//		if (!usuarioInstance) {
//			flash.message = message(code: 'default.not.found.message', args: [
//				message(code: 'usuario.label', default: 'Usuario'),
//				id
//			])
//			redirect(action: "list")
//			return
//		}
//
//		try {
//			usuarioInstance.delete(flush: true)
//			flash.message = message(code: 'default.deleted.message', args: [
//				message(code: 'usuario.label', default: 'Usuario'),
//				id
//			])
//			redirect(action: "list")
//		}
//
//		catch (DataIntegrityViolationException e) {
//			flash.message = message(code: 'default.not.deleted.message', args: [
//				message(code: 'usuario.label', default: 'Usuario'),
//				id
//			])
//			redirect(action: "show", id: id)
//		}
//	}
}
