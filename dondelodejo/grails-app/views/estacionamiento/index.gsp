<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="primerLayout" />
<title>Primer Vista</title>
</head>

<body>
	<div id="page-body" role="main">
		<div class="jumbotron">
			
			<%--Logo--%>
			<div class="logo-m"></div>

			<%--3 columnas--%>
			<div class="row">
				<div class="col-lg-4">
					<div class="bs-component">
						<h2>¡Estacionar es más fácil!</h2>
						<p class="lead">Encontrá dónde dejar tu vehículo en toda Latinoamérica!<br/>
						<strong>Buscás, reservás y pagás!</strong></p>
					</div>
				</div>
				<div class="col-lg-5">
					<div class="bs-component">
						<h2>¿Tenés un estacionamiento?</h2>
						<p>Incrementá tus visitas llegando a nuestros <strong>más de 3 usuarios </strong> de prueba!</p>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="bs-component">
						<h2>Comenzá ya!</h2>
						<p><g:link class="btn btn-default btn-lg" controller="login" action="index">Ingresar</g:link></p>
						<p><g:link class="btn btn-success btn-lg" controller="usuario" action="crear">Crear cuenta</g:link></p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>