package dondelodejo.com
import dondelodejo.com.Estacionamiento
import dondelodejo.com.Usuario

class Reserva {

	//Constantes
	private final String ESTADO_PENDIENTE = "PENDIENTE";
	private final String ESTADO_ACEPTADA = "ACEPTADA";
	private final String ESTADO_CANCELADA= "CANCELADA";
	private final String ESTADO_UTILIZADA = "UTILIZADA";
	private final String ESTADO_CALIFICADA_POR_CLIENTE = "CALIFxCLI";
	private final String ESTADO_COMPLETA = "COMPLETA";
	
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
		//TODO Metodo sin codear
	}
	def static Usuario[] listadoDeClientesPorEstacionamiento(Long idEstacionamiento){
	//TODO Metodo sin codear
	}
	/**3.En la pantalla de operador debe existir un listado o link con las reservas aceptadas para poder señalarlas como utilizadas*/
	def static getReservasAceptadasPorEstacionamiento(Long idEstacionamiento){
	//TODO Metodo sin codear USAR getReservasPorEstacionamientoYClienteYEstado
	}
	/**Generalizcion del anterior y algunos siguientes tambien*/
	def static getReservasPorEstacionamientoYClienteYEstado(Long idEstacionamiento,Long idCliente,String estado){
	//TODO Metodo sin codear
	}
	/** 4.Cliente califica la reserva. estado CALIFxCLI. Con esta calificacion se actualiza el puntaje del estacionamiento. */
	def static getReservasUtilizadasPorCliente(Long idCliente){
	//TODO Metodo sin codear USAR getReservasPorEstacionamientoYClienteYEstado
	}
	/**5.Admin "califica" al cliente. estado COMPLETA. Seria mas bien una replica en caso de que el cliente califica negativo.*/
	def static getReservasCalificadasPorElClientePorEstacionamiento(Long idEstacionamiento){
	//TODO Metodo sin codear USAR getReservasPorEstacionamientoYClienteYEstado
	}
	/**El detalle de estacionamiento tiene un resumen de las ultimas calificaciones recibidas por el estacionamiento.*/
	def static listadoDeCalificacionesRecibidasPorEstacionamiento(Long idEstacionamiento){
	//TODO Metodo sin codear
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
	
	//TODO Este metodo se puede eliminar haciendo dinamica la query anterior
//	/** En su muro debe ver las reservas que tiene pendientes para poder aceptarlas o cancelarlas. */
//	def static listadoPorEstacionamiento(Long idEstacionamiento){
//		def criteria = Reserva.createCriteria()
//		def results = criteria.list {
//			estacionamiento{	eq('id', idEstacionamiento)}
//			order("horaDeInicio", "ASC")
//		}
//		return results
//	}
	
	def calificaClienteAlEstacionamiento (int valor, char detalle){

		this.calificacionDelClienteAlEstacionamiento = new Calificacion(["valor":valor, "detalle":detalle])
		recalcularPuntajeEstacionamiento(valor)
	}
	def calificaEstacionamientoAlCliente(int valor, char detalle){
		this.calificacionDelEstacionamientoAlCliente = new Calificacion(["valor":valor, "detalle":detalle])
	}

	private recalcularPuntajeEstacionamiento(int valor) {
		int cantidad = Reserva.findByEstacionamientoAndEstado(this.estacionamiento.id,"Calificada").size()+1
		int puntaje = this.estacionamiento.puntaje
		def nuevoPuntaje = (puntaje + valor)/cantidad
		this.estacionamiento.puntaje = nuevoPuntaje
	}
	
	def aceptar()		{this.estado=ESTADO_ACEPTADA;}
	def cancelar() 		{this.estado=ESTADO_CANCELADA;}
	def utilizar() 		{this.estado=ESTADO_UTILIZADA;}
	def calificarCliente(){this.estado=ESTADO_CALIFICADA_POR_CLIENTE;}
	def completar() 	{this.estado=ESTADO_COMPLETA;}
	def esPendiente() 	{return this.estado==this.ESTADO_PENDIENTE;}
	def esAceptada() 	{return this.estado==this.ESTADO_ACEPTADA;}
	def esCancelada() 	{return this.estado==this.ESTADO_CANCELADA;}
	def esUtilizada() 	{return this.estado==this.ESTADO_UTILIZADA;}
	def esCalifXCliente(){return this.estado==this.ESTADO_CALIFICADA_POR_CLIENTE;}
	def esCompleta() 	{return this.estado==this.ESTADO_COMPLETA;}
}

class Calificacion {
	
		Integer valor
		String detalle
	
		static constraints = {
			valor 	 	    nullable : false, inList:[1, 2, 3, 4, 5]
			detalle 	    nullable : true
		}
	
	}