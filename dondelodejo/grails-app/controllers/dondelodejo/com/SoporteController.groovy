package dondelodejo.com

class SoporteController {
	def clienteService
	def estacionamientoService
	def reservaService
	def usuarioService
	
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
		redirect action:"soporte"
	}
	def soporte(){}

}
