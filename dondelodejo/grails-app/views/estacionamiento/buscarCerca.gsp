<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="primerLayout"/>
		<title>Primer Vista</title>
		
	</head>
	<body>
		<div id="page-body" role="main">

			<g:remoteLink action="delete" id="2" update="message">
			Delete Book
			</g:remoteLink>
			<!-- INICIO DE LLAMADA -->
			<g:render template="resultadoBusqueda"/>
			<!-- FIN DE LLAMADA -->
		</div>
	</body>
</html>