<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="primerLayout" />
<title>Primer Vista</title>
<g:if test="${session.usuario}">
	<g:if test="${!session.usuario.esSoporte()}">
		<style type='text/css'>
		.soporte {
			display: none;
		}
		</style>
	</g:if>
</g:if>
<g:else>
	<style type='text/css'>
	.soporte {
		display: none;
	}
	</style>
</g:else>
</head>

<body>

	<div id="page-body" role="main">

		<legend>Estacionamientos</legend>

		<blockquote class="soporte">
			<p>
				<g:link controller="estacionamiento" action="crear">Agregar estacionamiento</g:link>
			</p>
		</blockquote>

		<g:render template="listadoTablaEstacionamientos"/>
	</div>

</body>
</html>