package dondelodejo.com

class LoginController {

    def index() {[usuario:new Usuario()]}

	def usuarioService
	def login() {
		def password = usuarioService.encriptar(params.get("contrasenia"))
		Usuario usuario = usuarioService.buscarUsuario(email:params.get("email"),contrasenia:password)
		if (!usuario){
			flash.message=message(code: 'login.usuario.datosInvalidos')
			redirect (controller:"login", action:"index")
		} else {//aca debo decidir cual es el home, y luego enviarlo ahi.

			this.setHomeAtSession(usuario)

			session.usuario = usuario
			redirect (controller:"estacionamiento", action:session.home,id:session.homeId)
		}
	}
	def home() {
		redirect (controller:"estacionamiento", action:session.home,id:session.homeId)
	}
	/**
	 * Este metodo sera quien decida el home del Usuario. Si es Cliente, su cuenta.
	 * @param usuario
	 * @return la pagina a donde redirige
	 */
	void setHomeAtSession (Usuario usuario) {
		String home = "index" //for unknows
		Long homeId=null
		if (usuario.esAdministrador()) {
			home="administrador";
//			homeId=usuario.estacionamiento.id
		}
		if (usuario.esOperador()) {
			home="operador";
//			homeId=usuario.estacionamiento.id
		}
		if (usuario.esCliente()) {home="cliente"}

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
	def verificarTipoUsuario(String s) {
		if(!session.usuario || session.usuario.tipoUsuario != s) {
		redirect (controller:"login", action:"index")
		}
	}
}
