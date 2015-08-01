package dondelodejo.com

import java.security.MessageDigest

/**
 * UsuarioService hace referencia a la clase de Dominio Usuario. 
 * Deberia ser usada internamente por la aplicacion. Por ejemplo por el servicio de login, 
 * y en aquellos casos que se necesesite saber el tipo de usuario logueado
 * @author Jano
 *
 */
class UsuarioService {
	
	static transactional = true
	
	def buscarUsuario (Map datosDeUsuario) {
		Usuario usuario = new Usuario()
		usuario = Usuario.findWhere(datosDeUsuario)
	}
	
	/**
	 * Debe ser un usuario válido, verifica que no manipulen la session o cookie
	 */
	private boolean validarUsuario(Usuario usuario) {
		if (Usuario.findWhere(usuario.properties)){return true}
		return false
	}
	
	def esTipoUsuario (Usuario usuario,String tipo) {
		if (!validarUsuario(usuario)) {
			throw new RuntimeException("El Usuario no es válido")
		}
		def tipoDeUsuario = false
		//TODO FACUNDO arreglar metodo y sacar switch
		switch ( tipo ) {
			case Usuario.getUSUARIO_ADMINISTRADOR():
				tipoDeUsuario = usuario.esSoporte()
				break

			case Usuario.getUSUARIO_CLIENTE():
				tipoDeUsuario = usuario.esCliente()
				break

			case Usuario.getUSUARIO_OPERADOR():
				tipoDeUsuario = usuario.esAdministrador()
				break

			default:
				throw new RuntimeException("Tipo de usuario desconocido")
		}
		return tipoDeUsuario
	}
	// Para Guardar
//	def guardarUsuario() {
//	}
	
	def encriptar(String pass){
		Usuario.encriptar(pass)
	}
	// TODO BORRAR PRUEBA TEMPORAL*/
	def cambiarEstadoListadoCompleto(Long usuarioId){
		Usuario usuario=Usuario.get(usuarioId)
		if (usuario.debenMostrarseEstadosCompletados()) usuario.setMostrarEstadosCompletados(false)
		else usuario.setMostrarEstadosCompletados(true)
		return true
	}
	def cambiarEstadoListadoCompleto(Usuario usuario){
		if (usuario.debenMostrarseEstadosCompletados()) usuario.setMostrarEstadosCompletados(false)
		else usuario.setMostrarEstadosCompletados(true)
		return usuario
	}
	public void setUltimosVisitados(Estacionamiento e,Usuario usuarioSession){
		Usuario usuario=Usuario.get(usuarioSession.id)
		usuario.guardarUltimosVisitados(e)
		return
	}
	def getUltimosVisitados(Usuario usuarioSession){
		Usuario usuario=Usuario.get(usuarioSession.id)
		usuario.obtenerUltimosVisitados()
	}
}
