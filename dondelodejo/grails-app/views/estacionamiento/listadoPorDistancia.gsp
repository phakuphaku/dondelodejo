<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="primerLayout" />
<title>Estacionamientos cercanos</title>
</head>

<body>

	<div id="page-body" role="main">

		<legend>
			Estacionamientos cercanos a <b> ${direccion}
			</b> <i>(radio ${metros} metros)
			</i>
		</legend>

		<g:render template="listadoTabla"/>
	</div>
</body>
</html>