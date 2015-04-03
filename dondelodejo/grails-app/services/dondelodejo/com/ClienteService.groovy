package dondelodejo.com
import dondelodejo.com.Ubicacion
import dondelodejo.com.Estacionamiento
import dondelodejo.com.Reserva

import java.lang.RuntimeException
import java.util.logging.Logger.LoggerHelper;

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
		float tolerancia = Ubicacion.convertirMetrosAGPS(metros)

		def coordenadasByGoogle= Ubicacion.buscar(direccion,localidad,pais)

		this.buscarPorDistancia(coordenadasByGoogle, tolerancia)
	}
	def buscarPorDistancia(Map coord,Float tolerancia) {

		float desdeX = coord.get("x") - tolerancia
		float hastaX = coord.get("x") + tolerancia
		float desdeY = coord.get("y") - tolerancia
		float hastaY = coord.get("y") + tolerancia

		def c = Estacionamiento.createCriteria()
		def results = c.list {
			
			ubicacion{
				and {
					between("direccionX", desdeX, hastaX)
					between("direccionY", desdeY, hastaY)
				}
			}
			order("puntaje", "desc")
		}
	}

	def crearReserva(Map mapa) {
		LoggerService.Log("ingreso a CREARRESERVA con "+mapa.toString())
		
		Reserva reserva = new Reserva(mapa["reserva"])
		def estacionamientoId = mapa["estacionamientoId"]
		def usuario = mapa["usuario"]
		
		try{		
			//creo la nueva reserva
			Estacionamiento.get(estacionamientoId).addToReservas(reserva)
			Usuario.get(((Usuario)usuario).id).addToReservas(reserva)
			//De acuerdo a lo aprendido, estacionamiento y usuario ya estan guardados.
			reserva.save()
		}catch (Exception e){
			LoggerService.Log("Error en la creacion de reserva")
			throw new RuntimeException("DATOS INSUFICIENTES PARA LA OPERACION")
		}
		LoggerService.Log("RESERVA ACEPTADA")
		return reserva.id
	}
	def Reserva[] listadoReservas (Long idEstacionamiento,Long idUsuario,boolean bool) {
		return Reserva.listadoPorEstacionamientoYUsuario(idEstacionamiento,idUsuario,bool)
	}
	def Reserva[] listadoReservasParaClientes (Long idEstacionamiento,Long idUsuario,boolean bool) {
		return Reserva.listadoPorEstacionamientoYUsuarioYEstadosVisiblesParaClientes(idEstacionamiento,idUsuario,bool)
	}
	def Reserva[] listadoReservasParaAdministrador (Long idEstacionamiento,boolean truefalse) {
		return Reserva.getReservasPorEstacionamientoYEstadosVisiblesParaAdministradores(idEstacionamiento,truefalse)
	}

}
