package dondelodejo.com

class Cliente extends Usuario{

    static constraints = {
    }
	
	def calificar (Reserva reserva,int valor,String detalle){
		reserva.calificaClienteAlEstacionamiento(valor, detalle)
	}
}
