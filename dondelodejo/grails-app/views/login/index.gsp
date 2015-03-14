<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="primerLayout" />
</head>
<body>

	<%--//TODO Borrar lo comentado--%>
	<%--	
	<div id="page-body" role="main">
		<center>
			<g:form class="loginForm" action="login">
				<div class="background" />
				<div>
					<label for="email">E-mail:</label>
					<g:textField name="email" value="${usuario.email}" />
				</div>
				<div>
					<label for="contrasenia">Contraseña:</label>
					<g:passwordField name="contrasenia" value="${usuario.contrasenia}" />
				</div>
				<div id="submit">
					<g:submitButton name="login" value="Login" />
					</right>
			</g:form>
		</center>
	</div>
--%>

	<div class="well">
		<g:form class="loginForm form-horizontal" action="login">
			<fieldset>
				<legend>Ingresar</legend>
				<%--Email--%>
				<div class="form-group">
					<div class="col-lg-12">
						<g:textField name="email" class="form-control"
							placeholder="E-mail" value="${usuario.email}" />
					</div>
				</div>

				<%--Contraseña--%>
				<div class="form-group">
					<%--					<label for="contrasenia" class="col-lg-2 control-label">Contraseña</label>--%>
					<div class="col-lg-12">
						<g:passwordField name="contrasenia" class="form-control"
							placeholder="Contraseña" value="${usuario.contrasenia}" />
					</div>
				</div>

				<%--BOTÓN Submit--%>
				<div class="form-group">
					<div id="submit">
						<div class="col-lg-10 col-lg-offset-0">
							<g:submitButton name="login" class="btn btn-primary"
								value="Login" />
						</div>
					</div>
				</div>
			</fieldset>
		</g:form>
	</div>

</body>

</html>