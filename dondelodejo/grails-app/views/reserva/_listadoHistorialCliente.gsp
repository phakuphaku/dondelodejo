
<table class="table table-striped table-hover table-clickable">

	<%--HEADER-------------------------------------%>

	<%-- Los elementos de clase "admin" sólo los verán los administradores.--%>
	<thead>
		<tr>
			<th>Nombre</th>
			<th>Dirección</th>
			<th>Barrio</th>
			<th>Localidad</th>
			<th>$ Hora</th>
			<th>Calificación</th>
			<g:if test="${session.usuario}">
				<g:if test="${session.usuario.esSoporte()}">
					<th colspan="3" class="admin">Opciones</th>
				</g:if>
			</g:if>
		</tr>
	</thead>

	<g:each in="${listadoHistorial}" var="e">
		<%--Color de la fila según el puntaje del estacionamiento--%>
		<g:if test="${e.puntaje >= 4}">
			<tr class="success"
				onclick='document.location = "<g:createLink controller='estacionamiento' action='mostrar' id='${e.id}' />" '>
		</g:if>
		<g:elseif test="${e.puntaje >= 3}">
			<tr class="warning"
				onclick='document.location = "<g:createLink controller='estacionamiento' action='mostrar' id='${e.id}' />" '>
		</g:elseif>
		<g:elseif test="${e.puntaje >= 1}">
			<tr class="danger"
				onclick='document.location = "<g:createLink controller='estacionamiento' action='mostrar' id='${e.id}' />" '>
		</g:elseif>
		<g:elseif test="${e.puntaje == 0}">
			<%--Todavía no fue calificado--%>
			<tr
				onclick='document.location = "<g:createLink controller='estacionamiento' action='mostrar' id='${e.id}' />" '>
		</g:elseif>

		<td>
			${e.nombre}
		</td>
		<td>
			${e.ubicacion?.direccionStr}
		</td>
		<td>
			${e.ubicacion?.barrio}
		</td>
		<td>
			${e.ubicacion?.localidad}
		</td>
		<td>
			${e.cocheras[0].precioCocheraHora}
		</td>

		<td><g:if test="${e.puntaje == 0}">
					Sin calificaciones
					</g:if> <g:else>
				${e.puntaje}
			</g:else></td>

		<g:if test="${session.usuario}">
			<g:if test="${session.usuario.esSoporte()}">
				<td class="admin"><g:link controller="estacionamiento"
						action="editar" id="${e.id}">Editar</g:link></td>
				<td class="admin"><g:link controller="estacionamiento"
						action="borrar" id="${e.id}">Eliminar</g:link></td>
				<td class="admin"><g:link controller="estacionamiento"
						action="operador" id="${e.id}">Operador</g:link></td>
			</g:if>
		</g:if>
		</tr>
	</g:each>
</table>