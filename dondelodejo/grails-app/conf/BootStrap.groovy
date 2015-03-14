import java.util.Date;

import dondelodejo.com.Cochera
import dondelodejo.com.Estacionamiento
import dondelodejo.com.Tarjeta
import dondelodejo.com.Ubicacion
import dondelodejo.com.Usuario
import dondelodejo.com.Reserva
import dondelodejo.com.Calificacion

class BootStrap {
	def usuarioService

	def init = { servletContext ->

		Ubicacion ubicacion1 = new Ubicacion(
				direccionStr:"Rivadavia 502",
				direccionX:-34.6080344,
				direccionY:-58.3733686,
				barrio:"Balvanera",
				localidad:"Capital Federal",
				provincia:"Capital Federal",
				pais:"Argentina");

		Ubicacion ubicacion2 = new Ubicacion(
				direccionStr:"Boedo 679",
				direccionX:-34.6211227,
				direccionY:-58.2054511,
				barrio:"Almagro",
				localidad:"Capital Federal",
				provincia:"Capital Federal",
				pais:"Argentina");

		Ubicacion ubicacion3 = new Ubicacion(
				direccionStr:"Libertad 1234",
				direccionX:-34.5938873,
				direccionY:-58.3844316,
				barrio:"Microcentro",
				localidad:"Capital Federal",
				provincia:"Capital Federal",
				pais:"Argentina");

		Tarjeta tarjeta1 = new Tarjeta(
				numero:"0000111100002222",
				vencimiento:"2015/01/01",
				nombreTitular: "Pablo Cosso",
				clave:123,
				domicilioFacturacion:"Paseo Colon 850");

		Integer numCocheras = 25

		//
		//	Estacionamiento 1 / 3
		//
		Estacionamiento estacionamiento1 = new Estacionamiento(
				nombre:'Primer Garage',
				descripcion:"Una linda esquina",
				fechaAlta:new Date(),
				numCocheras:numCocheras,
				puntaje:5,
				ubicacion:ubicacion1)

		Cochera cochera1
		for ( i in 1..numCocheras) {
			cochera1 = new Cochera(numero:i,
			estado:Cochera.estadoLibre(),
			precioCocheraHora:20,
			precioCocheraFraccion:4
			)
			estacionamiento1.addToCocheras(cochera1);
			estacionamiento1.save()
		}
		estacionamiento1.save(flush: true)

		//
		// Estacionamiento 2 / 3
		//
		Estacionamiento estacionamiento2 = new Estacionamiento(
				nombre:'Segundo Garage',
				descripcion:"Donde estacionas de todo",
				fechaAlta:new Date(),
				numCocheras:numCocheras,
				puntaje:3,
				ubicacion:ubicacion2)

		Cochera cochera2
		for ( i in 1..numCocheras) {
			cochera2 = new Cochera(numero:i,
			estado:Cochera.estadoLibre(),
			precioCocheraHora:28,
			precioCocheraFraccion:7
			)
			estacionamiento2.addToCocheras(cochera2);
			estacionamiento2.save()
		}
		estacionamiento2.save(flush: true)

		//
		// Estacionamiento 3 / 3
		//
		Estacionamiento estacionamiento3 = new Estacionamiento(
				nombre:'Tercer Garage',
				descripcion:"En pleno centro",
				fechaAlta:new Date(),
				numCocheras:numCocheras,
				puntaje:0,
				ubicacion:ubicacion3)

		Cochera cochera3
		for ( i in 1..numCocheras) {
			cochera3 = new Cochera(numero:i,
			estado:Cochera.estadoLibre(),
			precioCocheraHora:36,
			precioCocheraFraccion:9
			)
			estacionamiento3.addToCocheras(cochera3);
			estacionamiento3.save()
		}
		estacionamiento3.save(flush: true)

		Usuario usuarioAdmin= new Usuario (
				nombre:"Super",
				apellido:"Admin",
				email:"admin@admin.com",
				contrasenia:usuarioService.encriptar("admin"),
				estacionamiento:estacionamiento1,
				tipoUsuario: Usuario.getAdministrador())./*addToTarjetas(tarjeta1).*/save();
		//		Usuario usuario2 = new Usuario (
		//				nombre:"Operador",
		//				apellido:"Del Estacionamiento",
		//				email:"oper@oper.com",
		//				contrasenia:usuarioService.encriptar("oper"),
		//				estacionamiento:estacionamiento1,
		//				tipoUsuario: Usuario.getOperador())./*addToTarjetas(tarjeta1).*/save();
		Usuario usuarioCliente = new Usuario (
				nombre:"Cliente",
				apellido:"Cualquiera",
				email:"cliente@cliente.com",
				contrasenia:usuarioService.encriptar("cliente"),
				tipoUsuario: Usuario.getCliente())./*addToTarjetas(tarjeta1).*/save();

		Reserva reserva1 = new Reserva (
				//				calificacion_cliente_id:2,
				estacionamiento:1,
				estado:'Pendiente',
				horaDeInicio: new Date(),
				horasReservadas:2,
				//				pago_id:1,
				usuario:2,
				).save(flush: true);
		Reserva reserva2 = new Reserva (
				//				calificacion_cliente_id:2,
				estacionamiento:1,
				estado:'Pendiente',
				horaDeInicio: new Date(),
				horasReservadas:2,
				//				pago_id:1,
				usuario:2,
				).save(flush: true);

		Calificacion cal1= new Calificacion(
				valor: 4,
				detalle: "Todo en orden!",
				cliente: 2,
				estacionamiento: 1,
				reserva: 1,
				).save(flush: true);

		Calificacion cal2= new Calificacion(
				valor: 1,
				detalle: "Me echaron porque venía una caravana de Audis!",
				cliente: 2,
				estacionamiento: 1,
				reserva: 1,
				).save(flush: true);
	}

	def destroy = {
	}
}