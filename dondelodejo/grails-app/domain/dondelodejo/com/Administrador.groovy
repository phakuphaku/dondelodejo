package dondelodejo.com

class Administrador extends Usuario{

    static constraints = {
    }
	
	def calificar (Reserva reserva,int valor,String detalle){
		reserva.calificaEstacionamientoAlCliente(valor, detalle)
	}
}
