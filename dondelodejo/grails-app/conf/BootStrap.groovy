import java.util.Date;

import dondelodejo.com.Cochera
import dondelodejo.com.Estacionamiento
import dondelodejo.com.Ubicacion
import dondelodejo.com.Usuario
import dondelodejo.com.Reserva
import dondelodejo.com.Calificacion

class BootStrap {
	def usuarioService

	def init = { servletContext ->
		Integer numCocheras = 25

		Ubicacion ubicacion1 = new Ubicacion(
				direccionStr:"Rivadavia 502",
				direccionX:-34.6080344,
				direccionY:-58.3733686,
				barrio:"Balvanera",
				localidad:"Capital Federal",
				provincia:"Capital Federal",
				pais:"Argentina");
		Estacionamiento estacionamiento1 = new Estacionamiento(
				nombre:'Primer Garage',
				descripcion:"Una linda esquina",
				fechaAlta:new Date(),
				numCocheras:numCocheras,
				puntaje:0,
				ubicacion:ubicacion1
				).save()

		Cochera cochera1
		for ( i in 1..numCocheras) {
			cochera1 = new Cochera(
					numero:i,
					estado:Cochera.estadoLibre(),
					precioCocheraHora:20,
					precioCocheraFraccion:4
					)
			estacionamiento1.addToCocheras(cochera1);
		}


		Ubicacion ubicacion2 = new Ubicacion(
				direccionStr:"Boedo 679",
				direccionX:-34.6211227,
				direccionY:-58.2054511,
				barrio:"Almagro",
				localidad:"Capital Federal",
				provincia:"Capital Federal",
				pais:"Argentina");

		Estacionamiento estacionamiento2 = new Estacionamiento(
				nombre:'Segundo Garage',
				descripcion:"Donde estacionas de todo",
				fechaAlta:new Date(),
				numCocheras:numCocheras,
				puntaje:0,
				ubicacion:ubicacion2).save()

		Cochera cochera2
		for ( i in 1..numCocheras) {
			cochera2 = new Cochera(
					numero:i,
					estado:Cochera.estadoLibre(),
					precioCocheraHora:28,
					precioCocheraFraccion:7
					)
			estacionamiento2.addToCocheras(cochera2);
		}

		Ubicacion ubicacion3 = new Ubicacion(
				direccionStr:"Libertad 1234",
				direccionX:-34.5938873,
				direccionY:-58.3844316,
				barrio:"Microcentro",
				localidad:"Capital Federal",
				provincia:"Capital Federal",
				pais:"Argentina");

		Estacionamiento estacionamiento3 = new Estacionamiento(
				nombre:'Tercer Garage',
				descripcion:"En pleno centro",
				fechaAlta:new Date(),
				numCocheras:numCocheras,
				puntaje:0,
				ubicacion:ubicacion3
				).save()

		Cochera cochera3
		for ( i in 1..numCocheras) {
			cochera3 = new Cochera(
					numero:i,
					estado:Cochera.estadoLibre(),
					precioCocheraHora:36,
					precioCocheraFraccion:9
					)
			estacionamiento3.addToCocheras(cochera3);
		}

		numCocheras = 1

		Ubicacion ubicacion7 = new Ubicacion(direccionStr:"Adolfo Alsina 2551",direccionX:-34.612244,direccionY:-58.402435,barrio:"Balvanera",	localidad:"Capital Federal",provincia:"Capital Federal",pais:"Argentina");
		Estacionamiento estacionamiento7 = new Estacionamiento(nombre:'A y M',descripcion:"Una linda esquina",fechaAlta:new Date(),numCocheras:numCocheras,puntaje:0,
		ubicacion:ubicacion7).save()
		estacionamiento7.addToCocheras(new Cochera(numero:1,estado:Cochera.estadoLibre(),precioCocheraHora:36,precioCocheraFraccion:9))
		Ubicacion ubicacion8 = new Ubicacion(direccionStr:"Sarmiento 2265",direccionX:-34.60604,direccionY:-58.39886,barrio:"Balvanera",	localidad:"Capital Federal",provincia:"Capital Federal",pais:"Argentina");
		Estacionamiento estacionamiento71 = new Estacionamiento(nombre:'PRESLY',descripcion:"Una linda esquina",fechaAlta:new Date(),numCocheras:numCocheras,puntaje:0,
		ubicacion:ubicacion8).save()
		estacionamiento71.addToCocheras(new Cochera(numero:1,estado:Cochera.estadoLibre(),precioCocheraHora:36,precioCocheraFraccion:9))
		Ubicacion ubicacion9 = new Ubicacion(direccionStr:"Larrea 257",direccionX:-34.606785,direccionY:-58.402,barrio:"Balvanera",	localidad:"Capital Federal",provincia:"Capital Federal",pais:"Argentina");
		Estacionamiento estacionamiento8 = new Estacionamiento(nombre:'Edifa',descripcion:"Una linda esquina",fechaAlta:new Date(),numCocheras:numCocheras,puntaje:0,
		ubicacion:ubicacion9).save()
		estacionamiento8.addToCocheras(new Cochera(numero:1,estado:Cochera.estadoLibre(),precioCocheraHora:36,precioCocheraFraccion:9))
		Ubicacion ubicacion40 = new Ubicacion(direccionStr:"Rinc&oacuten 659",direccionX:-34.6167,direccionY:-58.396122,barrio:"Balvanera",	localidad:"Capital Federal",provincia:"Capital Federal",pais:"Argentina");
		Estacionamiento estacionamiento9 = new Estacionamiento(nombre:'Gran Garage Rinc&oacuten',descripcion:"Una linda esquina",fechaAlta:new Date(),numCocheras:numCocheras,puntaje:0,
		ubicacion:ubicacion40).save()
		estacionamiento9.addToCocheras(new Cochera(numero:1,estado:Cochera.estadoLibre(),precioCocheraHora:36,precioCocheraFraccion:9))
		Ubicacion ubicacion41 = new Ubicacion(direccionStr:"Bartolom&eacute Mitre 2350",direccionX:-34.608532,direccionY:-58.399876,barrio:"Balvanera",	localidad:"Capital Federal",provincia:"Capital Federal",pais:"Argentina");
		Estacionamiento estacionamiento10 = new Estacionamiento(nombre:'Sergio C Valente',descripcion:"Una linda esquina",fechaAlta:new Date(),numCocheras:numCocheras,puntaje:0,
		ubicacion:ubicacion41).save()
		estacionamiento10.addToCocheras(new Cochera(numero:1,estado:Cochera.estadoLibre(),precioCocheraHora:36,precioCocheraFraccion:9))
		Ubicacion ubicacion42 = new Ubicacion(direccionStr:"Viamonte 2244",direccionX:-34.601105,direccionY:-58.399113,barrio:"Balvanera",	localidad:"Capital Federal",provincia:"Capital Federal",pais:"Argentina");
		Estacionamiento estacionamiento11 = new Estacionamiento(nombre:'Garage Viamonte',descripcion:"Una linda esquina",fechaAlta:new Date(),numCocheras:numCocheras,puntaje:0,
		ubicacion:ubicacion42).save()
		estacionamiento11.addToCocheras(new Cochera(numero:1,estado:Cochera.estadoLibre(),precioCocheraHora:36,precioCocheraFraccion:9))
		Ubicacion ubicacion43 = new Ubicacion(direccionStr:"Bartolome Mitre 2374",direccionX:-34.60855,direccionY:-58.400192,barrio:"Balvanera",	localidad:"Capital Federal",provincia:"Capital Federal",pais:"Argentina");
		Estacionamiento estacionamiento12 = new Estacionamiento(nombre:'San Expedito',descripcion:"Una linda esquina",fechaAlta:new Date(),numCocheras:numCocheras,puntaje:0,
		ubicacion:ubicacion43).save()
		estacionamiento12.addToCocheras(new Cochera(numero:1,estado:Cochera.estadoLibre(),precioCocheraHora:36,precioCocheraFraccion:9))
		Ubicacion ubicacion44 = new Ubicacion(direccionStr:"Av Rivadavia 2151",direccionX:-34.609528,direccionY:-58.396954,barrio:"Balvanera",	localidad:"Capital Federal",provincia:"Capital Federal",pais:"Argentina");
		Estacionamiento estacionamiento13 = new Estacionamiento(nombre:'Yesiton Sa',descripcion:"Una linda esquina",fechaAlta:new Date(),numCocheras:numCocheras,puntaje:0,
		ubicacion:ubicacion44).save()
		estacionamiento13.addToCocheras(new Cochera(numero:1,estado:Cochera.estadoLibre(),precioCocheraHora:36,precioCocheraFraccion:9))
		Ubicacion ubicacion45 = new Ubicacion(direccionStr:"Tte. Gral Juan Domingo Per&oacuten 2029",direccionX:-34.607063,direccionY:-58.39545,barrio:"Balvanera",	localidad:"Capital Federal",provincia:"Capital Federal",pais:"Argentina");
		Estacionamiento estacionamiento14 = new Estacionamiento(nombre:'Garage Sucre Sa',descripcion:"Una linda esquina",fechaAlta:new Date(),numCocheras:numCocheras,puntaje:0,
		ubicacion:ubicacion45).save()
		estacionamiento14.addToCocheras(new Cochera(numero:1,estado:Cochera.estadoLibre(),precioCocheraHora:36,precioCocheraFraccion:9))
		Ubicacion ubicacion46 = new Ubicacion(direccionStr:"Boulogne Sur Mer 342",direccionX:-34.60613,direccionY:-58.40721,barrio:"Balvanera",	localidad:"Capital Federal",provincia:"Capital Federal",pais:"Argentina");
		Estacionamiento estacionamiento15 = new Estacionamiento(nombre:'Garage Once S',descripcion:"Una linda esquina",fechaAlta:new Date(),numCocheras:numCocheras,puntaje:0,
		ubicacion:ubicacion46).save()
		estacionamiento15.addToCocheras(new Cochera(numero:1,estado:Cochera.estadoLibre(),precioCocheraHora:36,precioCocheraFraccion:9))

		Usuario usuarioSoporte= new Usuario (
				nombre:"Super",
				apellido:"Soporte",
				email:"soporte@soporte.com",
				contrasenia:usuarioService.encriptar("soporte"),
				tipoUsuario: Usuario.getSoporte()
				).save();
		Usuario usuarioCliente = new Usuario (
				nombre:"Cliente",
				apellido:"Cualquiera",
				email:"cliente@cliente.com",
				contrasenia:usuarioService.encriptar("cliente"),
				tipoUsuario: Usuario.getCliente()
				).save();

		// Admin. del primer estacionamiento
		Usuario usuarioAdmin1 = new Usuario (
				nombre:"Admin de",
				apellido:" Rivadavia 502",
				email:"admin1@admin1.com",
				contrasenia:usuarioService.encriptar("admin1"),
				tipoUsuario: Usuario.getAdministrador(),
				estacionamiento: estacionamiento1,
				mostrarReservasYaCompletadas: true
				).save();

		Reserva reserva1 = new Reserva (
				estacionamiento:1,
				horaDeInicio: new Date(),
				cantidadHorasReservadas:2,
				usuario:2
				).save();
		Reserva reserva2 = new Reserva (
				estacionamiento:1,
				horaDeInicio: new Date(),
				cantidadHorasReservadas:2,
				usuario:2
				).save();
		Reserva reserva3 = new Reserva (
				estacionamiento:1,
				horaDeInicio: new Date(),
				cantidadHorasReservadas:2,
				usuario:2
				).save();
		Reserva reserva4 = new Reserva (
				estacionamiento:2,
				horaDeInicio: new Date(),
				cantidadHorasReservadas:2,
				usuario:2
				).save();

		/*Calificacion calificcion1 = new Calificacion(valor:4,detalle:"Todo en orden!");
		reserva1.calificacionDelClienteAlEstacionamiento=calificcion1
		reserva1.calificarCliente()
		reserva1.save()
		Calificacion calificcion2 = new Calificacion(valor:1,detalle:"Me echaron porque ven&iacutea una caravana de Audis!");
		reserva2.calificacionDelClienteAlEstacionamiento=calificcion2
		reserva2.calificarCliente()
		reserva2.save()
		Calificacion calificcion3 = new Calificacion(valor:1,detalle:"El Tipo es un pel###### que me colg&oacute 5 veces y quiere que lo felicite");
		reserva2.calificacionDelEstacionamientoAlCliente=calificcion3
		reserva2.completar()
		reserva2.save()*/
	}

	def destroy = {
	}
}