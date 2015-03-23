package dondelodejo.com

class Usuario {

	static final String USUARIO_ADMINISTRADOR='ADM'
	static final String USUARIO_OPERADOR='OPE'
	static final String USUARIO_CLIENTE='CLI'
	public static getAdministrador(){
		USUARIO_ADMINISTRADOR
	}
	public static getOperador(){
		USUARIO_OPERADOR
	}
	public static getCliente(){
		USUARIO_CLIENTE
	}

	String nombre
	String apellido
	String email
	String contrasenia
	String tipoUsuario

//	Estacionamiento estacionacmiento
	static hasMany = [reservas:Reserva]

	static constraints = {
		nombre		nullable:false,blank:false
		apellido	nullable:false,blank:false
		email		nullable:false,email:true
		contrasenia	nullable:false,blank:false
		tipoUsuario nullable:true,inList: [
			getAdministrador(),
			getOperador(),
			getCliente()
		],defaultValue:this.getCliente()
//		estacionamiento nullable:true
	}

	def esAdministrador(){
		this.tipoUsuario == USUARIO_ADMINISTRADOR
	}
	def esOperador(){
		this.tipoUsuario == USUARIO_OPERADOR
	}
	def esCliente(){
		this.tipoUsuario == USUARIO_CLIENTE
	}
	String toString(){
		//		"Usuario: "+this.email+" Tipo: "+this.tipoUsuario
		this.nombre+" "+this.apellido+" ("+this.tipoUsuario+")"
	}
}
