<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DondeLoDejo?com - <g:layoutTitle default="Grails" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<g:javascript library="jquery" plugin="jquery" />
<r:require modules="bootstrap" />
<r:layoutResources />

<%--Theme SIMPLEX, sobreescribe al bootstrap.css básico del plugin--%>
<link rel="stylesheet" type="text/css"
	href="/dondelodejo.com/css/bootstrap-simplex.css">

<%--Modificaciones al theme SIMPLEX, en otro archivo para no romper nada--%>
<link rel="stylesheet" type="text/css"
	href="/dondelodejo.com/css/bootstrap-simplex_MODS.css">

<g:layoutHead />
</head>

<body>
	<%--	<div id="dondeLoDejoLogo" role="banner" align="center">--%>
	<%--		<img class="original"--%>
	<%--			src="${resource(dir: 'images', file: 'dondelodejo_logo.png')}"--%>
	<%--			alt="Grails" /><br />--%>

	<%--TOP: Barra de navegación--%>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<%--			<div class="navbar-header">--%>
			<%--				<a class="navbar-brand" href="../">DondeLoDejo.com</a>--%>
			<%--			</div>--%>

			<g:link controller="login" action="home">
				<div class="navbar-header">
					<span class="navbar-brand">DondeLoDejo.com</span>
				</div>
			</g:link>

			<div id="navbar-main" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<!--  BOTONERA -->
					<li><g:link controller="estacionamiento" action="listado">
							<span>Listado</span>
						</g:link></li>
					<li class='last'><g:link controller="estacionamiento"
							action="buscarPorDistancia">
							<span>Buscar por Distancia</span>
						</g:link></li>
				</ul>

				<%--DERECHA: LOGIN--%>
				<ul class="nav navbar-nav navbar-right">
					<g:if test="${session.usuario}">
						<!-- USUARIO logueado -->
						<li><g:link controller="login" action="home">
								${session.usuario.toString()}
							</g:link></li>
						<li><g:link controller="login" action="logout">Salir</g:link></li>
					</g:if>
					<g:else>
						<!-- USUARIO sin loguear -->
						<li><g:link controller="login" action="index">Ingresar</g:link></li>
						<li><g:link controller="usuario" action="crear">Crear cuenta</g:link></li>
					</g:else>
				</ul>

			</div>
		</div>
	</div>

	<%--CONTENEDOR PRINCIPAL--%>
	<div class="main-container container">


		<%--MENSAJES--%>
		<%--
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		--%>

		<g:if test="${flash.message}">
			<div class="message" role="status">
				<%-- Los mensajes se muestran de colers según sean S, E, I, W--%>
				<g:if test="${flash.message_type == 'S'}">
					<div class="alert alert-dismissible alert-success">
				</g:if>
				<g:elseif test="${flash.message_type == 'E'}">
					<div class="alert alert-dismissible alert-danger">
				</g:elseif>
				<g:elseif test="${flash.message_type == 'W'}">
					<div class="alert alert-dismissible alert-warning">
				</g:elseif>
				<g:else>
					<div class="alert alert-dismissible alert-info">
				</g:else>
				<%--				<div class="alert alert-dismissible alert-info">--%>
				<button type="button" class="close" data-dismiss="alert">×</button>
				<p>
					${flash.message}
				</p>
			</div>
	</div>
	</g:if>

	<g:layoutBody />

	<div class="footer" role="contentinfo"></div>
	<div id="spinner" class="spinner" style="display: none;">
		<g:message code="spinner.alt" default="Loading&hellip;" />
	</div>
	<g:javascript library="application" />
	<r:layoutResources />
	</div>
</body>
</html>
