<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="primerLayout" />
<title>Reservar</title>
</head>

<body>

	<div id="page-body" role="main" align="center">
		<center>
			<g:form class="formulario" action="alta">
				<div class="background" />
				<g:textField style="display:none" name="estacionamientoId"
					value="${estacionamientoId}" />
				<div>
					<label for="horaDeInicio">Fecha de Inicio:</label>
					<g:datePicker name="horaDeInicio"
						value="${reservaFiltro?.horaDeInicio}" precision="minute"
						years="[2015,2016]" />

					<!--<g:textField name="horaDeInicio" value="${reservaFiltro?.horaDeInicio}"/>-->
				</div>
				<div>
					<label for="cantidadHorasReservadas">Horas que quiere reservar:</label>
					<g:textField name="cantidadHorasReservadas"
						value="${reservaFiltro?.cantidadHorasReservadas}" />
				</div>
				<div id="submit">
					<g:submitButton name="crear" value="Crear la Reserva" />
				</div>
				</div>
			</g:form>
		</center>
	</div>

	<legend>Historial de reservas  (<g:link controller="reserva" action="cambiarEstadoListadoCompletoCrear" id="${estacionamientoId}">Ver/Ocultar Listado Completo</g:link>)</legend>

	<g:render template="listadoReservasParaCliente"/>

</body>
</html>