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
				<td>
					${r.estado}
				</td>
				<g:if test="${r.esPendiente()}">
					<td><g:link controller="reserva" action="aceptarCalificacion"	id="${r.id}">Aceptar</g:link></td>
					<td><g:link controller="reserva" action="cancelarCalificacion"	id="${r.id}">Cancelar</g:link></td>
				</g:if>
				<g:if test="${r.esAceptada()}">
					<td><g:link controller="reserva" action="utilizarCalificacion"	id="${r.id}">Marcar como utilizada</g:link></td>
				</g:if>
			</tr>
				
			<!-- TODO Terminar de emprolijar -->
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