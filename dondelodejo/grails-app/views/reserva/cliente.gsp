<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="primerLayout" />
	<title>Primer Vista</title>
</head>

<body>
	<div id="page-body" role="main">
		<%--<span>Ud es un Cliente, Sea Bienvenido a DondeLoDejo</span>--%>
		<legend>Hola, <strong>${session.usuario.nombre.toString()}</strong></legend>
		<blockquote>
			<p>${session.usuario.nombre.toString()} ${session.usuario.apellido.toString()}</p>
			<p>Cliente</p>
			<p>${session.usuario.email.toString()}</p>
		</blockquote>
		<%--<g:link controller="estacionamiento" action="listado">volver al listado</g:link>--%>
		<legend>Últimos estacionamientos vistos</legend>
		<g:render template="listadoHistorialCliente" />
		<legend>
		Reservas (<g:link controller="reserva" action="cambiarEstadoListadoCompleto">Ver todo</g:link>)		
		</legend>
		<g:render template="listadoReservasParaCliente" />
	</div>
</body>
</html>