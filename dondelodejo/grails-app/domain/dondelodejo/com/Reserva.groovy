package dondelodejo.com
import dondelodejo.com.Estacionamiento
import dondelodejo.com.Usuario

class Reserva {

	//Constantes
	private static final String ESTADO_PENDIENTE = "PENDIENTE";
	private static final String ESTADO_ACEPTADA = "ACEPTADA";
	private static final String ESTADO_CANCELADA= "CANCELADA";
	private static final String ESTADO_UTILIZADA = "UTILIZADA";
	private static final String ESTADO_CALIFICADA_POR_CLIENTE = "CALIFxCLI";
	private static final String ESTADO_COMPLETA = "COMPLETA";
	
	static belongsTo = [estacionamiento:Estacionamiento,usuario:Usuario];
	/** Hora en que el cliente piensa ocupar la cochera*/
	Date horaDeInicio
	int  cantidadHorasReservadas
	String estado=ESTADO_PENDIENTE
	Calificacion calificacionDelClienteAlEstacionamiento
	Calificacion calificacionDelEstacionamientoAlCliente
	static embedded = ['calificacionDelClienteAlEstacionamiento','calificacionDelEstacionamientoAlCliente']

	static constraints = {
		estado (nullable:true)
		calificacionDelClienteAlEstacionamiento nullable:true
		calificacionDelEstacionamientoAlCliente nullable:true
	}

	def getID() {
		return this.id;
	}
	
	/** @Motivo Cliente en su muro, tiene una lista de Estacionamientos que visitó. (los toma de las reservas que fueron creadas) */
	def static Estacionamiento[] listadoDeEstacionamientosPorCliente(Long idCliente){
		//TODO Generar vista/sevicio para que llame a este m�todo
		
		Estacionamiento[] estacionamientos 
		
		def criteria = Usuario.createCriteria()
		def usuario = criteria.list {
				if (idCliente != null)	usuario{ eq('id', idCliente)}
		}
		
		estacionamientos add (usuario.reservas.each { res -> res.getEstacionamiento() })
		
		return estacionamientos

	}
	
	def static Usuario[] listadoDeClientesPorEstacionamiento(Long idEstacionamiento){
		
		Usuario[] usuarios
		
		def criteria = Estacionamiento.createCriteria()
		def estacionamiento = criteria.list {
				if (idEstacionamiento != null)	estacionamiento{ eq('id', idEstacionamiento)}
		}
		
		usuarios add (estacionamiento.reservas.each { res -> res.getUsuario() })
		
		return usuarios
		
	}
	/**3.En la pantalla de operador debe existir un listado o link con las reservas aceptadas para poder señalarlas como utilizadas*/
	def static getReservasAceptadasPorEstacionamiento(Long idEstacionamiento){
		
		return this.getReservasPorEstacionamientoYClienteYEstado(idEstacionamiento,null,null)
		
 	}
	
	def static getReservasPorEstacionamientoYEstadosVisiblesParaAdministradores(Long idEstacionamiento,boolean debenMostrarseEstadosCompletados){
		def criteria = Reserva.createCriteria()
		def reservas = criteria.list {
			if (idEstacionamiento != null)	estacionamiento{	eq('id', idEstacionamiento)}
			or {
				eq('estado', ESTADO_PENDIENTE)
				eq('estado', ESTADO_ACEPTADA)
				eq('estado', ESTADO_CALIFICADA_POR_CLIENTE)
				if (debenMostrarseEstadosCompletados){
					  eq('estado', ESTADO_COMPLETA)
					  eq('estado', ESTADO_UTILIZADA)
					  eq('estado', ESTADO_CANCELADA)
				}
			}
		}
		return reservas	
	}
	
	def static getReservasPorEstacionamientoYEstadoPendienteOAceptada(Long idEstacionamiento){
		Reserva[] reservas = getReservasPorEstacionamientoYClienteYEstado(idEstacionamiento,null,ESTADO_PENDIENTE)	
		         reservas += getReservasPorEstacionamientoYClienteYEstado(idEstacionamiento,null,ESTADO_ACEPTADA)
		return reservas	
	}
	
	/**Generalizcion del anterior y algunos siguientes tambien*/
	def static getReservasPorEstacionamientoYClienteYEstado(Long idEstacionamiento,Long idCliente,String estado){	
		
		def criteria = Reserva.createCriteria()
		def reservas = criteria.list {
			and {
				if (idEstacionamiento != null)	estacionamiento{	eq('id', idEstacionamiento)}
				if (idCliente         != null)	usuario{	        eq('id', idCliente)}
				if (estado            != null)  eq('estado', estado)
				}
		}
		return reservas
		
	}

	/** 4.Cliente califica la reserva. estado CALIFxCLI. Con esta calificacion se actualiza el puntaje del estacionamiento. */
	def static getReservasUtilizadasPorCliente(Long idCliente){
	
		return this.getReservasPorEstacionamientoYClienteYEstado(null,idCliente,"CALIFxCLI")
	}
	
	/**5.Admin "califica" al cliente. estado COMPLETA. Seria mas bien una replica en caso de que el cliente califica negativo.*/
	def static getReservasCalificadasPorElClientePorEstacionamiento(Long idEstacionamiento){
		
		return this.getReservasPorEstacionamientoYClienteYEstado(idEstacionamiento,null,"COMPLETA")
		
	}
	/**El detalle de estacionamiento tiene un resumen de las ultimas calificaciones recibidas por el estacionamiento.*/
	def static listadoDeCalificacionesRecibidasPorEstacionamiento(Long idEstacionamiento){
		
		def criteria = Estacionamiento.createCriteria()
		def estacionamiento = criteria.list {
				if (idEstacionamiento != null)	estacionamiento{ eq('id', idEstacionamiento)}
		}
		
		return estacionamiento.getReservas().each { r -> r.getcalificacionDelClienteAlEstacionamiento} 
		
	}
	
	/** @params si son null se anula ese filtro */
	def static listadoPorEstacionamientoYUsuario(Long idEstacionamiento,Long idCliente){
		def criteria = Reserva.createCriteria()
		def results = criteria.list {
			and {
				if (idEstacionamiento != null)	estacionamiento{	eq('id', idEstacionamiento)}
				if (idCliente         != null)	usuario{	        eq('id', idCliente)}
				}
			order("horaDeInicio", "ASC")
		}
		return results
	}
	/** @params si son null se anula ese filtro */
	def static listadoPorEstacionamientoYUsuarioYEstadosVisiblesParaClientes(Long idEstacionamiento,Long idCliente,boolean debenMostrarseEstadosOcultos){
		def criteria = Reserva.createCriteria()
		def results = criteria.list {
			and {
				if (idEstacionamiento != null)	estacionamiento{	eq('id', idEstacionamiento)}
				if (idCliente         != null)	usuario{	        eq('id', idCliente)}
				if (!debenMostrarseEstadosOcultos){				eq('estado', ESTADO_UTILIZADA)}
				}
			order("horaDeInicio", "ASC")
		}
		return results
	}
	
	def calificaClienteAlEstacionamiento (int valor, String detalle){
		this.calificacionDelClienteAlEstacionamiento = new Calificacion(["valor":valor, "detalle":detalle])
		this.calificarCliente()
		this.save(flush:true)
		recalcularPuntajeEstacionamiento(valor)
		this
	}
	def calificaEstacionamientoAlCliente(int valor, String detalle){
		this.calificacionDelEstacionamientoAlCliente = new Calificacion(["valor":valor, "detalle":detalle])
		this.completar()
		this.save(flush:true)
		this
	}

	private recalcularPuntajeEstacionamiento(int valor) {
		//*Lockeo pesimista
		int cantidad = Reserva.findAllByEstacionamientoAndEstado(this.estacionamiento,ESTADO_CALIFICADA_POR_CLIENTE, [lock: true]).size()
		cantidad += Reserva.findAllByEstacionamientoAndEstado(this.estacionamiento,ESTADO_COMPLETA, [lock: true]).size()
		int puntaje = this.estacionamiento.puntaje
		Float nuevoPuntaje = this.redondear((puntaje + valor)/cantidad)
		this.estacionamiento.puntaje = nuevoPuntaje
		this.estacionamiento.save(flush:true)
	}
	
	def aceptar()		{this.estado=ESTADO_ACEPTADA;return this}
	def cancelar() 		{this.estado=ESTADO_CANCELADA;return this}
	def utilizar() 		{this.estado=ESTADO_UTILIZADA;return this}
	def calificarCliente(){this.estado=ESTADO_CALIFICADA_POR_CLIENTE;return this}
	def completar() 	{this.estado=ESTADO_COMPLETA;return this}
	def esPendiente() 	{return this.estado==this.ESTADO_PENDIENTE;}
	def esAceptada() 	{return this.estado==this.ESTADO_ACEPTADA;}
	def esCancelada() 	{return this.estado==this.ESTADO_CANCELADA;}
	def esUtilizada() 	{return this.estado==this.ESTADO_UTILIZADA;}
	def esCalifXCliente(){return this.estado==this.ESTADO_CALIFICADA_POR_CLIENTE;}
	def esCompleta() 	{return this.estado==this.ESTADO_COMPLETA;}

	public Float redondear( Float numero) {
		int decimales=2;
	    return (Math.round((numero)*Math.pow(10,decimales))/Math.pow(10,decimales));
	}
}

class Calificacion {
		Integer valor
		String detalle
		static constraints = {
			valor 	 	    nullable : false, inList:[1, 2, 3, 4, 5]
			detalle 	    nullable : true
		}
	}