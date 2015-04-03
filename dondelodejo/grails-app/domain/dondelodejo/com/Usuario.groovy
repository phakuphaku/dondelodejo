package dondelodejo.com

class Usuario {

	static final String USUARIO_SOPORTE='SOP'
	static final String USUARIO_ADMINISTRADOR='ADM'
	static final String USUARIO_CLIENTE='CLI'

	public static getSoporte(){
		USUARIO_SOPORTE
	}
	public static getAdministrador(){
		USUARIO_ADMINISTRADOR
	}
	public static getCliente(){
		USUARIO_CLIENTE
	}

	//////////////////////////////////////////////////////////////////////

	String 			nombre
	String 			apellido
	String 			email
	String 			contrasenia
	String			tipoUsuario
	Estacionamiento estacionamiento
	boolean 		mostrarReservasYaCompletadas=false

	static hasMany = [reservas:Reserva]

	//////////////////////////////////////////////////////////////////////

	static constraints = {
		nombre		nullable:false,blank:false
		apellido	nullable:false,blank:false
		email		nullable:false,email:true
		contrasenia	nullable:false,blank:false
		tipoUsuario nullable:true,inList: [getSoporte(), getAdministrador(), getCliente()],defaultValue:this.getCliente()
		estacionamiento nullable:true
	}

	def esSoporte(){
		this.tipoUsuario == USUARIO_SOPORTE
	}
	def esAdministrador(){
		this.tipoUsuario == USUARIO_ADMINISTRADOR
	}
	def esCliente(){
		this.tipoUsuario == USUARIO_CLIENTE
	}
	String toString(){
		//		"Usuario: "+this.email+" Tipo: "+this.tipoUsuario
		this.nombre+" "+this.apellido+" ("+this.tipoUsuario+")"
	}
	public boolean debenMostrarseEstadosCompletados() {
		return mostrarReservasYaCompletadas;
	}
	public void setMostrarEstadosCompletados(boolean truefalse) {
		this.mostrarReservasYaCompletadas = truefalse;
	}
	def static Usuario getById(Long id){
		Usuario u=Usuario.get(id)
		if (u.tipoUsuario==getCliente()){
			return new Cliente(u.properties)
		}
		if (u.tipoUsuario==getAdministrador()){
			return new Administrador(u.properties)
		}
		if (u.tipoUsuario==getSoporte()){
			return new Usuario() //TODO para este camino esta situacion no esta definida.
		}
	}
}