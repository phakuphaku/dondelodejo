package dondelodejo.com

class LoginController {

	def index() {
		[usuario:new Usuario()]
	}

	def usuarioService
	def login() {
		def password = usuarioService.encriptar(params.get("contrasenia"))
		Usuario usuario = usuarioService.buscarUsuario(email:params.get("email"),contrasenia:password)
		if (!usuario){
			flash.message=message(code: 'login.usuario.datosInvalidos')
			flash.message_type = "E"
			redirect (controller:"login", action:"index")
		} else {
			//aca debo decidir cual es el home, y luego enviarlo ahi.

			this.setHomeAtSession(usuario)

			session.usuario = usuario
			redirect (controller:session.homeController, action:session.home,id:session.homeId)
		}
	}
	def home() {
		redirect (controller:session.homeController, action:session.home,id:session.homeId)
	}

	/**
	 * Este metodo sera quien decida el home del Usuario. Si es Cliente, su cuenta.
	 * @param usuario
	 * @return la pagina a donde redirige
	 */
	void setHomeAtSession (Usuario usuario) {
		String home = "index" //for unknows
		String homeController="estacionamiento"
		Long homeId=null
		if (usuario.esSoporte()) {
			//			home="administrador";
			homeController="soporte"
			home="index";
			//			homeId=usuario.estacionamiento.id
		}
		if (usuario.esAdministrador()) {
			//			home="operador";
			home="administrador";
			homeId=usuario.estacionamiento.id
			// Validar que el estacionamiento pertenezca al usuario.
		}
		if (usuario.esCliente()) {
			homeController="reserva"
			home="cliente"
			homeId=usuario.id
		}

		session.homeController=homeController
		session.home=home
		session.homeId=homeId

	}

	def logout() {
		session.usuario=null;
		redirect (controller:"estacionamiento", action:"index")
	}

	def verificarUsuario() {
		if(!session.usuario) {
			redirect (controller:"login", action:"index")
		} //else {
		//do nothing
		//}
	}
// Para Sacar
//	def verificarTipoUsuario(String s) {
//		if(!session.usuario || session.usuario.tipoUsuario != s) {
//			redirect (controller:"login", action:"index")
//		}
//	}
}
