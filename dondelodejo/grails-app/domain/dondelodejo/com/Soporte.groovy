package dondelodejo.com

class Soporte extends Usuario{

    static constraints = {
    }
	
 	def calificar (Reserva reserva,int valor,String detalle){
 		reserva.calificaEstacionamientoAlCliente(valor, detalle)
 	}
}
