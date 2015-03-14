package dondelodejo.com.backend

import dondelodejo.com.Estacionamiento;

class BackendEstacionamientoController {
	def beforeInterceptor = [action:this.&checkUser,except:['index'/*,'list'*/,'show']]
    def scaffold = Estacionamiento
	def checkUser() {
		if(!session.usuario) {
		// i.e. user not logged in
		redirect(controller:'login',action:'verificarUsuario')
		return false
		}
	}
}
