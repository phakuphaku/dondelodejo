package dondelodejo.com
import dondelodejo.com.Ubicacion
import dondelodejo.com.Estacionamiento

import java.lang.RuntimeException

import org.apache.jasper.compiler.Node.ParamsAction;
class ClienteService {

	def buscarPorDistancia(String direccion,String localidad,String pais,Integer metros) {
		if (direccion==null || direccion==""){
			throw new RuntimeException("Para la busqueda de las coordenadas de google, hace falta una direccion.")
		}
		if (metros < 1) {
			throw new RuntimeException("Una busqueda debe ser hecha en distancias mas extensas.")
		}
		//convierte metros a cuadrado con centro en la direccion dada.
		float tolerancia = metros * Ubicacion.getGpsToMetros()

		if (localidad==null) localidad = ''
		if (pais==null)      pais = ''

		def coordenadasByGoogle= Ubicacion.buscar(direccion,localidad,pais)

		this.buscarPorDistancia(coordenadasByGoogle, tolerancia)
	}
	def buscarPorDistancia(Map coord,Float tolerancia) {

		float desdeX = coord.get("x") - tolerancia
		float hastaX = coord.get("x") + tolerancia
		float desdeY = coord.get("y") - tolerancia
		float hastaY = coord.get("y") + tolerancia

		/*TODO pasar esto a un Map de Maps para no devolver la entidad 
		 * y en los nuevos maps, agregar la distancia que tiene a las coordenadas por pitagoras.
		 */
		Estacionamiento.findAll(
				"from Estacionamiento as e "+
				"where (e.ubicacion.direccionX between ? AND ? ) "+
				"AND (e.ubicacion.direccionY between ? AND ?) ",
				[
					desdeX,
					hastaX,
					desdeY,
					hastaY
				])
	}

	def crearReserva(Map mapa) {
		println "CREARRESERVA "+mapa
		def r=mapa["reserva"]
		Reserva reserva = new Reserva(r)
		Estacionamiento.get(mapa["estacionamientoId"]).addToReservas(reserva)
		Usuario.get(((Usuario)mapa["usuario"]).id).addToReservas(reserva)
		Pago pago=new Pago (importe:0,tarjeta:Tarjeta.get(1),estado:"PendienteDeConfirmacion")

		reserva.save()//id pago null

		reserva.setPago(pago)
		reserva.save()


		//			reserva.addToPago(pago).save()//TODO: tarjeta default
		//			if (!reserva.validate()) return null;
		println "RESERVA ACEPTADA"
		Usuario usuario=mapa.get("usuario")

		if (mapa["estacionamientoId"] && (usuario)){
			Estacionamiento.get(mapa["estacionamientoId"]).addToReservas(reserva).save(flush:true)
			println "RESERVA ADD EXITOSO"
			return reserva.id
		}
		else {
			println "DATOS INSUFICIENTES PARA LA OPERACION"
			return null;
		}
	}
	def Reserva[] listadoReservas (Long idEstacionamiento,Long idUsuario) {
		Reserva.listadoByEstacionamientoYUsuario(idEstacionamiento, idUsuario)
	}

}
