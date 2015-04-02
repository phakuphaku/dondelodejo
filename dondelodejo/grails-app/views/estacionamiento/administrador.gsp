<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="primerLayout" />
<title>Primer Vista</title>
</head>
<body>
	<div id="page-body" role="main">

<%-- Se obtiene el estacionamiento del administrador --%>

		<%--HEADER--%>
		<legend>
			Estacionamiento <b>
				${estacionamiento.nombre}
			</b>
		</legend>
		<%--Detalle del estacionamiento--%>
		<blockquote>
			<p>
				${estacionamiento.ubicacion?.direccionStr}
			</p>
			<p>
				${estacionamiento.numCocheras}
				cocheras.
			</p>
		</blockquote>
		<legend>
			Reservas: (<g:link controller="estacionamiento" action="cambiarEstadoListadoCompleto">Ver/Ocultar Listado Completo</g:link>)
		</legend>
		<table class="table table-striped table-hover table-clickable">
			<thead>
				<tr>
					<th>Horario Reservado</th>
					<th>Duración</th>
					<th colspan="3">Estado</th>
				</tr>
			</thead>
			<g:each in="${listadoReservas}" var="r">
				
				<tr>
					<td>
						${r.horaDeInicio}
					</td>
					<td>
						${r.cantidadHorasReservadas}
					</td>
					<g:if test="${r.esCompleta() || r.esUtilizada()}">
						<td colspan="3">
							${r.estado}
						</td>
					</g:if>
					<g:else>
						<td>
							${r.estado}
						</td>
					</g:else>
					<g:if test="${r.esPendiente()}">
						<td><g:link action="aceptarCalificacion"	id="${r.id}">Aceptar</g:link></td>
						<td><g:link action="cancelarCalificacion"	id="${r.id}">Cancelar</g:link></td>
					</g:if>
					<g:if test="${r.esAceptada()}">
						<td colspan="2"><g:link action="utilizarCalificacion"	id="${r.id}">Marcar como utilizada</g:link></td>
					</g:if>
					<g:if test="${r.esCalifXCliente()}">
						<td colspan="2"><a class="crearCalificacion${r.id}" href="#">Calificar Ahora</a></td>
						<script>
						$(document).ready(function(){
						    $('a.crearCalificacion${r.id}').click(function(){
						        $('tr.crearCalificacion${r.id}').fadeToggle(1000);
						    });
						});
						</script>
					</g:if>
				</tr>
				<g:if test="${r.esCalifXCliente()}">
				<tr class="crearCalificacion${r.id}" style="display:none">
					<td colspan="5">
						<table class="table table-striped table-hover table-clickable">
							<tr>
								<th>Calificar esta reserva:</th>
							</tr>
							<g:form action="calificarReserva">
							<tr>
								<td colspan="4">
								<g:select name="valor" from="${1..5}" value="${valor}" noSelection="['':'-Su Calificación-']"/>	Detalle: <g:textField name="detalle" />
								</td>
							</tr>
							<tr>
								<td colspan="4">
										<g:textField style="display:none" name="reservaId" value="${r.id}" />
										<g:submitButton name="Calificar" class="btn btn-primary btn-xs" value="Calificar" />
								</td>
							</tr>
							</g:form>
							<!-- Hasta aqui el formulario -->
						</table>
					</td>
				</tr>
				</g:if>
					
				<g:if test="${r.esCompleta()}">
				<tr>
					<td colspan="5">
					<table class="table table-striped table-hover table-clickable">
						<tr>
							<th>Calificaciones de esta reserva:</th>
						</tr>
						<tr>
							<td colspan="4">----Lo calificaron: (${r.calificacionDelClienteAlEstacionamiento.valor}/5) ${r.calificacionDelClienteAlEstacionamiento.detalle}</td>
						</tr>
						<tr>
							<td colspan="4">----Su Respuesta: (${r.calificacionDelEstacionamientoAlCliente.valor}/5) ${r.calificacionDelEstacionamientoAlCliente.detalle}</td>
						</tr>
					</table>
					</td>
				</tr>	
				</g:if>
			</g:each>
		</table>
		<legend>
			Su Disponibilidad:
		</legend>
		<table class="table table-striped table-hover ">
			<thead>
				<tr>
					<th>#</th>
					<th>$ 60'</th>
					<th>$ 15'</th>
					<th>Ingreso</th>
					<th>Egreso (esperado)</th>
					<th>Ocupar / Liberar</th>
				</tr>
			</thead>

			<g:each in="${estacionamiento.cocheras}" var="c">
				<g:if test='${c.estaOcupada()}'>
					<tr class="danger">
						<td class="tdOperador tdOcupada">
				</g:if>
				<g:else>
					<tr class="">
						<td class="tdOperador tdLibre">
				</g:else>
				${c.numero}
				</td>
				<td>$ ${c.precioCocheraHora}
				</td>
				<td>$ ${c.precioCocheraFraccion}
				</td>

				<%--Si la cochera está ocupada, se muestra la hora de ingreso y egreso.--%>
				<g:if test='${c.estaOcupada()}'>
					<td>
						${c.horaOcupacion}
					</td>
					<td>
						${c.horaLiberacion}
					</td>
				</g:if>
				<g:else>
					<td></td>
					<td></td>
				</g:else>

				<td><g:form action="cambiarEstadoCochera">
						<g:textField style="display:none" name="estacionamientoId"
							value="${estacionamiento.id}" />
						<g:textField style="display:none" name="cocheraId" value="${c.id}" />
						<g:if test='${c.estaOcupada()}'>
							<g:submitButton name="liberar" class="btn btn-primary btn-xs"
								value="LIBERAR" />
						</g:if>
						<g:else>
							<g:submitButton name="ocupar" class="btn btn-default btn-xs"
								value="OCUPAR" />
						</g:else>
					</g:form></td>
			</g:each>
			</tr>
		</table>
		<g:link controller="estacionamiento" action="listado">Volver al listado</g:link>
	</div>
</body>

</html>