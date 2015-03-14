<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="primerLayout" />
<title>Primer Vista</title>
<g:if test="${session.usuario}">
	<g:if test="${!session.usuario.esAdministrador()}">
		<style type='text/css'>
.admin {
	display: none;
}
</style>
	</g:if>
</g:if>
<g:else>
	<style type='text/css'>
.admin {
	display: none;
}
</style>
</g:else>
</head>

<body>
	<%--
	<div id="page-body" role="main">
		<table border=1>
			<tr>
				<td width="80%" colspan="2">Estacionamiento:</td>
				<td class="admin" width="20%"><a href="crear/">Agregar
						Estacionamiento</a></td>
			</tr>
			<g:each in="${estacionamientoInstanciaListado}" var="e">
				<tr>
					<td>Nombre: ${e.nombre}<br /> Direccion: ${e.ubicacion?.direccionStr}<br />
						Alta: ${e.fechaAlta}<br /> Edicion: ${e.fechaModificacion}<br />
						Borrado: ${e.fechaBaja}<br /> CoordX: ${e.ubicacion?.direccionX}<br />
						CoordY: ${e.ubicacion?.direccionY}<br />
					</td>
					<td><a href="mostrar/${e.id}">Ver Detalle</a></td>
					<td class="admin"><g:link controller="estacionamiento"
							action="editar" id="${e.id}">Editar</g:link><br /> <g:link
							controller="estacionamiento" action="borrar" id="${e.id}">Eliminar</g:link><br />
						<g:link controller="estacionamiento" action="operador"
							id="${e.id}">Operador</g:link><br /></td>
				</tr>
			</g:each>
			<tr>
				<td colspan="3">Y fueron ${estacionamientoInstanciaTotal}
					Elemento/s mostrado/s
				</td>
			</tr>
		</table>
	</div>
--%>

	<div id="page-body" role="main">

		<legend>Estacionamientos</legend>

		<blockquote class="admin">
			<p>
				<g:link controller="estacionamiento" action="crear">Agregar estacionamiento</g:link>
			</p>
		</blockquote>

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
	</div>

</body>
</html>