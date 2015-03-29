	<table class="table table-striped table-hover table-clickable">
		<thead>
			<tr>
				<th>Fecha</th>
				<th>Duración</th>
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
					${r.estado}
				</td>
				<%--				<td><g:link controller="calificacion" action="borrar"--%>
				<%--						id="${r.id}">Eliminar</g:link><br /></td>--%>
				<td><g:link controller="reserva" action="crearCalificacion"
						id="${r.id}">Calificar</g:link></td>
			</tr>
		</g:each>
	</table>