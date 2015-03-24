		<table class="table table-striped table-hover table-clickable">

			<%--HEADER-------------------------------------%>

			<%-- Los elementos de clase "admin" sólo los verán los administradores.--%>
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Dirección</th>
					<th>Coordenadas</th>
					<th>$ Hora</th>
					<th>Calificación</th>
					<th colspan="3" class="admin">Opciones</th>
				</tr>
			</thead>

			<g:each in="${estacionamientoInstanciaListado}" var="e">
				<%--Color de la fila según el puntaje del estacionamiento--%>
				<g:if test="${e.puntaje >= 4}">
					<tr class="success"
						onclick='document.location = "<g:createLink action='mostrar' id='${e.id}' />" '>
				</g:if>
				<g:elseif test="${e.puntaje >= 3}">
					<tr class="warning"
						onclick='document.location = "<g:createLink action='mostrar' id='${e.id}' />" '>
				</g:elseif>
				<g:elseif test="${e.puntaje >= 1}">
					<tr class="danger"
						onclick='document.location = "<g:createLink action='mostrar' id='${e.id}' />" '>
				</g:elseif>
				<g:elseif test="${e.puntaje == 0}">
					<%--Todavía no fue calificado--%>
					<tr
						onclick='document.location = "<g:createLink action='mostrar' id='${e.id}' />" '>
				</g:elseif>

				<td>
					${e.nombre}
				</td>
				<td>
					${e.ubicacion?.direccionStr}
				</td>
				<td>
					${e.ubicacion?.direccionX} ; ${e.ubicacion?.direccionY}
				</td>

				<td>
					${e.cocheras[0].precioCocheraHora}
				</td>

				<td><g:if test="${e.puntaje == 0}">
					Sin calificaciones
					</g:if> <g:else>
						${e.puntaje}
					</g:else></td>

				<td class="admin"><g:link controller="estacionamiento"
						action="editar" id="${e.id}">Editar</g:link></td>
				<td class="admin"><g:link controller="estacionamiento"
						action="borrar" id="${e.id}">Eliminar</g:link></td>
				<td class="admin"><g:link controller="estacionamiento"
						action="operador" id="${e.id}">Operador</g:link></td>
				</tr>
			</g:each>
		</table>

		<%--Cantidad de estacionamientos mostrados--%>
		<div class="alert alert-dismissible alert-success">
			<button type="button" class="close" data-dismiss="alert">×</button>
			<g:if test="${estacionamientoInstanciaTotal == 1}">
				<strong> ${estacionamientoInstanciaTotal}</strong> elemento mostrado.
			</g:if>
			<g:else>
				<strong> ${estacionamientoInstanciaTotal}</strong> elementos mostrados.
			</g:else>
		</div>