package dondelodejo.com

import java.awt.geom.Point2D

import maps.java.Geocoding

class Estacionamiento {

	//Descripcion
	String nombre;
	String descripcion

	//ubicacion
	Ubicacion ubicacion = new Ubicacion();
	static embedded = ['ubicacion']
	//datos aplicacion
	String aceptaReservas // S OR N
	String foto // será la url de una foto online.


	//Mantenimiento
	Date fechaAlta;
	Date fechaModificacion=null
	Date fechaBaja=null
	Integer numCocheras=0

	Float puntaje=0

	static hasMany = [cocheras:Cochera,reservas:Reserva/*,usuarios:Usuario*/] //, calificaciones:Calificacion]
	static mapping = { cocheras sort:'numero' }
	static constraints = {

		nombre   		nullable:false
		descripcion		nullable:true
		ubicacion		nullable:true

		//datos aplicacion
		aceptaReservas 	nullable:true
		foto		 	nullable:true


		//Mantenimiento
		fechaAlta			nullable:false
		fechaModificacion 	nullable:true
		fechaBaja			nullable:true

		puntaje				nullable:true
	}

	String toString(){
		nombre.size()<=30 ? nombre : nombre[0..19]
	}
	def getGpsToMetros(){
		Ubicacion.getGpsToMetros()
	}
	def prueba(){
		def query = Estacionamiento.where { id == 1 }
		query.list()
	}
	public Map buscar(String direccion,String localidad,String pais){
		this.ubicacion.buscar(direccion,localidad,pais)
	}
	def establecerUbicacion(Map map){
		Ubicacion ubicacion = new Ubicacion(map)
		ubicacion.buscar()
		this.ubicacion = ubicacion
		return true
	}
	def getCantidadCocherasLibres(){
		//cocheras es un set
		this.cocheras.findAll{ cochera ->
			cochera.estado==Cochera.ESTADO_LIBRE
		}.size()


		Cochera.cocherasLibres(this.id)
	}

	/*
	 * Devuelve las calificaciones del estacionamiento
	 */
	//	def getCalificaciones(){
	//		this.cocheras.findAll{ cochera ->
	//			cochera.estado==Cochera.ESTADO_LIBRE
	//		}.size()
	//	}
	/*
	 * Devuelve las calificaciones del estacionamiento.
	 */
	def getCalificaciones(){
		//		this.calificaciones.findAll(estacionamiento_id == '1');
	}
}

class Ubicacion {

	static final float gpsToMetros = 0.000009;

	String direccionStr
	float  direccionX
	float  direccionY
	String barrio
	String localidad
	String provincia
	String pais

	static constraints = {
		//ubicacion
		direccionStr	nullable:false
		direccionX		nullable:true
		direccionY		nullable:true
		//poner como default nos ahorrará tiempo de desarrollo
		barrio			nullable:true
		localidad		nullable:true,defaultValue:"Capital Federal"
		provincia		nullable:true
		pais 			nullable:true,defaultValue:"Argentina"
	}

	//	public Ubicacion (Map params) {
	//		if (params){
	//			this.direccionStr=params.get("direccionStr")
	//			this.barrio		=params.get("barrio")
	//			this.localidad	=params.get("localidad")
	//			this.provincia	=params.get("provincia")
	//			this.pais		=params.get("pais")
	//			def coordenadasByGoogle = this.buscar(params.get("direccionStr"),
	//				params.get("localidad"),
	//				params.get("pais"))
	//			this.direccionX = coordenadasByGoogle.get("x")
	//			this.direccionY = coordenadasByGoogle.get("y")
	//		}
	//	}
	def buscar(){
		def coordenadasByGoogle = Ubicacion.buscar(this.direccionStr,this.localidad,this.pais)
		this.direccionX = coordenadasByGoogle.get('x')
		this.direccionY = coordenadasByGoogle.get('y')
	}
	public static Map buscar(String direccion,String localidad,String pais){

		Geocoding ObjGeocod=new Geocoding();
		double CoordX,CoordY
		try {

			Point2D.Double resultadoCD =ObjGeocod.getCoordinates(direccion+","+localidad+","+pais);
			CoordX = resultadoCD.x
			CoordY = resultadoCD.y
		} catch (Exception e) {
			println("Error Codificación geográfica")
		}
		[x:CoordX,y:CoordY]
	}
	def boolean estaDentroDeRangoEnMetros (float x,float y,int metros) {
		float tolerancia=metros * this.gpsToMetros
		float desdeX = x - tolerancia
		float hastaX = x + tolerancia
		float desdeY = y - tolerancia
		float hastaY = y + tolerancia
		(	(desdeX<this.direccionX)&&
				(this.direccionX<hastaX)&&
				(desdeY<this.direccionY)&&
				(this.direccionY<hastaY)
				)
	}
	def static convertirMetrosAGPS(Integer metros){
		return metros * Ubicacion.getGpsToMetros()
	}
}