package dondelodejo.com

import dondelodejo.com.Reserva
import dondelodejo.com.Tarjeta

class Pago {
	static belongsTo = [reserva:Reserva]
//	Reserva reserva
	Float importe
	Tarjeta tarjeta
	String estado
	
    static constraints = {
		estado (inList:["PendienteDeConfirmacion","Aceptado","Rechazado"],defaultValue:"PendienteDeConfirmacion")
//		reserva nullable:true
    }
}
