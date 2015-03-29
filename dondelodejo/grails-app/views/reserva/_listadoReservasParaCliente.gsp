	<table class="table table-striped table-hover table-clickable">
		<thead>
			<tr>
				<th>Fecha</th>
				<th>Duración</th>
				<th>Dirección</th>
				<th colspan="2">Estado</th>
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
					${r.estacionamiento.ubicacion.direccionStr}
				</td>
				<td>
					${r.estado}
				</td>
				<g:if test="${r.esUtilizada()}">
					<td><g:link controller="reserva" action="crearCalificacion"	id="${r.id}">Calificar</g:link></td>
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
						<td colspan="4">----Su Calificación: (${r.calificacionDelClienteAlEstacionamiento.valor}/5) ${r.calificacionDelClienteAlEstacionamiento.detalle}</td>
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