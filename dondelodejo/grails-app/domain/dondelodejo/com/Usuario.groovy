package dondelodejo.com

import java.security.MessageDigest

class Usuario {

	String 			nombre
	String 			apellido
	String 			email
	String 			contrasenia
	String			tipoUsuario
	String			ultimosVisitados
	Estacionamiento estacionamiento
	boolean 		mostrarReservasYaCompletadas=false
	int				maxVisitados=3
	
	static final String USUARIO_SOPORTE='SOP'
	static final String USUARIO_ADMINISTRADOR='ADM'
	static final String USUARIO_CLIENTE='CLI'

	static hasMany = [reservas:Reserva]

	static constraints = {
		nombre		nullable:false,blank:false
		apellido	nullable:false,blank:false
		email		nullable:false,email:true
		contrasenia	nullable:false,blank:false
		tipoUsuario nullable:true,inList: [getSoporte(), getAdministrador(), getCliente()],defaultValue:this.getCliente()
		ultimosVisitados nullable:true,defaultValue:""
		estacionamiento nullable:true
	}

	def Usuario(){
		super()
	}
	def guardar(){
		this.contrasenia = Usuario.encriptar(this.contrasenia)
		this.save(flush:true)
	}
	
	public static getSoporte(){
		USUARIO_SOPORTE
	}
	public static getAdministrador(){
		USUARIO_ADMINISTRADOR
	}
	public static getCliente(){
		USUARIO_CLIENTE
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
	
	
	
	/**
	 * Este método ayuda a un debug mas entendible.
	 */
	String toString(){
		this.nombre+" "+this.apellido+" ("+this.tipoUsuario+")"
	}
	
	
	
	def obtenerUltimosVisitados (){
		ArrayList ultimosEstacionamientosVisitados = new ArrayList<Estacionamiento>()
		String [] aux;
		if (ultimosVisitados!=null && ultimosVisitados!=""){
			aux = this.ultimosVisitados.split(",")
			aux.each{idE -> 
				if (idE != null )
				ultimosEstacionamientosVisitados.add(Estacionamiento.get(Long.valueOf(idE)))	
			}}
		return ultimosEstacionamientosVisitados
	}
	def guardarUltimosVisitados (Estacionamiento E){
		boolean limiteMaximoDeVisitados = false
		if (this.ultimosVisitados != null) {
			limiteMaximoDeVisitados = (this.ultimosVisitados.split(",").size() >= this.maxVisitados)
			int ultimaPosicionDelArray = this.maxVisitados -1
			if (limiteMaximoDeVisitados) 
				this.ultimosVisitados=this.removerElUltimo(ultimosVisitados)
			this.ultimosVisitados=E.id+","+ultimosVisitados
		}else{
			this.ultimosVisitados=E.id
		}
		this.save(flush:true)
		return
	}
	
	def removerElUltimo(String s){
		String[] StringSpliteado = s.split(",")
		int posicionDelUltimo = StringSpliteado.size() -1
		String aDevolver = ""
		(0..posicionDelUltimo-1).each  { pos -> 
			aDevolver+=StringSpliteado[pos]+","
		}
		aDevolver=aDevolver.substring(0, aDevolver.size()-1)
		return aDevolver
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
	def static encriptar(String pass){
		String passwordToHash = pass;
		String generatedPassword = null;

		// Create MessageDigest instance for MD5
		MessageDigest md = MessageDigest.getInstance("MD5");
		//Add password bytes to digest
		md.update(passwordToHash.getBytes());
		//Get the hash's bytes
		byte[] bytes = md.digest();
		//This bytes[] has bytes in decimal format;
		//Convert it to hexadecimal format
		StringBuilder sb = new StringBuilder();
		for(int i=0; i< bytes.length ;i++)
		{
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		//Get complete hashed password in hex format
		generatedPassword = sb.toString();
		return generatedPassword
	}
}