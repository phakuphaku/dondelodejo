package dondelodejo.com


class Calificacion {

	/** Donde fue hecha la reserva*/
//	Estacionamiento estacionamiento

	//	static belongsTo = [reserva:Reserva];
	static belongsTo = [//estacionamiento:Estacionamiento, 
		reserva:Reserva];

	int valor
	String detalle
	/** A una reserva siempre estara vinculado un cliente */
	Usuario cliente

	static constraints = {

		//estacionamiento nullable : false
		valor 	 	    nullable : false, inList:[1, 2, 3, 4, 5]
		detalle 	    nullable : true

	}
	/**
	 * Calificar?
	 * @param administrador
	 * @param cliente
	 */
	void calificar (Usuario administrador, Usuario cliente) {
	}
}
