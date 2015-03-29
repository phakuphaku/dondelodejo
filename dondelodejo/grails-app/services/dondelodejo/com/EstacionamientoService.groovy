package dondelodejo.com

class EstacionamientoService {
	/** Busca UN Estacionamiento */
	def buscar(Map map) {
		LoggerService.Log( "BUSCAR "+map)
		if (map.containsKey('id')){
			Estacionamiento.get(Long.valueOf(map.get("id")))
		}
		/*TODO: Esto va con query
		 * else Estacionamiento.get
		 */
	}
	def buscarReserva(Map map) {
		LoggerService.Log( "BUSCAR "+map)
		if (map.containsKey('id')){
			Reserva.get(Long.valueOf(map.get("id")))
		}
		/*TODO: Esto va con query
		 * else Estacionamiento.get
		 */
	}
	/** Busca UN Estacionamiento por id
	 * @return un map con el estacionamiento, y el numero de cocheras disponibles.*/
	def cocherasLibres(Map map) {
		LoggerService.Log( "BUSCAR "+map)
		Estacionamiento e = Estacionamiento.get(Long.valueOf(map.get("id")))
		//Integer cocherasLibres = Cochera.cocherasLibres(Long.valueOf(map.get("id")))
		Integer cocherasLibres = e.cantidadCocherasLibres
		[estacionamiento:e,cantidadCocherasLibres:cocherasLibres]
	}
	/** Busca TODOS los Estacionamientos en un rango dado */
	def buscarCercanos(Map e) {
		Estacionamiento.buscarCercanos(params.get("direccionStr")+","+
				params.get("barrio")+","+
				params.get("localidad")+","+
				params.get("provincia")+","+
				params.get("pais"),
				params.get("metros"))
	}


	def alta(Map mapa) {
		mapa.put("fechaAlta", new Date());
		Estacionamiento e = new Estacionamiento(mapa);
		e.establecerUbicacion(mapa);
		//agrego cocheras
		Integer numCocheras = Integer.valueOf(mapa.get("numCocheras"))
		//              .collect{new Cochera(it)}.each{this.addtoCochera(it)}
		(1..numCocheras).each{numCochera ->
			Cochera cochera
			cochera = new Cochera(numero:numCochera,
			//estado:Cochera.estadoLibre(),
			precioCocheraHora:mapa.get("precioCocheraHora"),
			precioCocheraFraccion:mapa.get("precioCocheraFraccion")
			)
			e.addToCocheras(cochera);
			//e.save() El save de abajo guarda todo
		}
		if (e.save(/*flush: true*/)) return e;
		else {
			return null;
			//conviene tirar excepcion
		}
	}
	def editar(Map mapa) {

		Estacionamiento e = this.buscar([id:mapa.get("id")]);
		/*TODO: para hacer esto, 
		 debemos verificar que mapa contiene aquello a modificar SOLAMENTE*/
		e.properties = mapa
		e.setFechaModificacion(new Date())

		if (e.validate() && e.save(/*flush: true*/)) { //TODO validate y save no, save directamente.
			return e;
		}
		else {
			return null;
		}
	}

	def listado(Map map) {
		map.putAt("sort","puntaje")
		map.putAt("order","DESC")
		Estacionamiento.list(map)
	}
	/** Verifica si es un estacionamiento v�lido */
	def validar(Estacionamiento e){
		e.validate()
	}
	Integer cantidad(){
		Estacionamiento.count()
	}
	boolean borrar(Long id){
		Estacionamiento estacionamiento = this.buscar([id:id])
		estacionamiento.fechaBaja = new Date()
		return estacionamiento.save();
	}
	boolean borrarReserva(Long id){
		Reserva reserva = this.buscarReserva([id:id])
		reserva.delete()
	}
	def ocuparCochera(Long idCochera){
		Cochera.get(idCochera).ocupar()
	}
	/** devuelve tiempo de ocupacion */
	def liberarCochera(Long idCochera){
		Cochera.get(idCochera).liberar()
	}
	//TODO: REvisar este calculo para que de algo logico.
	String cobrarCochera(Long idCochera){
		//		"Debe cobrarse PESOS "+Cochera.get(idCochera).cobrar()+" por la cochera utilizada."
		"<b>Total cochera "+Cochera.get(idCochera).getNumero()+":</b> "+Cochera.get(idCochera).cobrar()+"ARS."
	}
}
