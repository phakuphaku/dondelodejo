<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="primerLayout" />
<title>Reservar</title>
</head>

<body>
	<%--
	<div id="page-body" role="main">
		<center>
			<g:form class="formulario" action="alta">
				<div class="background" />
				<g:textField style="display:none" name="estacionamientoId"
					value="${estacionamientoId}" />
				<div>
					<label for="horaDeInicio">Fecha de Inicio:</label>
					<g:datePicker name="horaDeInicio"
						value="${reservaFiltro?.horaDeInicio}" precision="minute"
						years="[2014,2015]" />

					<!--<g:textField name="horaDeInicio" value="${reservaFiltro?.horaDeInicio}"/>-->
				</div>
				<div>
					<label for="horasReservadas">Horas que quiere reservar:</label>
					<g:textField name="horasReservadas"
						value="${reservaFiltro?.horasReservadas}" />
				</div>
				<div id="submit">
					<g:submitButton name="crear" value="Crear la Reserva" />
				</div>
			</g:form>
		</center>
	</div>

	<table border=1>
		<tr>
			<td width="80%" colspan="2">Reservas:</td>
		</tr>
		<g:each in="${listadoReservas}" var="r">
			<tr>
				<td>Fecha: ${r.horaDeInicio}<br /> horasReservadas:${r.horasReservadas}<br />
					estado:${r.estado}<br />
				</td>

				<td><g:link controller="reserva" action="borrar" id="${r.id}">Eliminar</g:link><br />
				</td>

			</tr>
		</g:each>
	</table>
	--%>

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
						years="[2014,2015]" />

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

	<legend>Historial de reservas  (<g:link controller="usuario" action="cambiarEstadoListadoCompleto">Ver/Ocultar Listado Completo</g:link>)</legend>

	<g:render template="listadoReservasParaCliente"/>

</body>
</html>