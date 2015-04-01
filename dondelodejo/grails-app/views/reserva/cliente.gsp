<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="primerLayout"/>
		<title>Primer Vista</title>
		
	</head>
	<body>
		<div id="page-body" role="main">
			<span>Ud es un Cliente, Sea Bienvenido a DondeLoDejo</span>
		<g:link controller="estacionamiento" action="listado">volver al listado</g:link>	
		</div>
		<g:render template="listadoReservasParaCliente"/>
	</body>
	
</html>