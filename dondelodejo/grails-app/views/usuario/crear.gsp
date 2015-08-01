<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="primerLayout" />
<title>Crear usuario</title>
</head>
<body>

	<div id="page-body" role="main">
		<div class="well">
			<legend>Crear usuario</legend>
			<g:form class="formulario form-horizontal" action="guardar">
				<fieldset>

					<%--Nombre--%>
					<div class="form-group">
						<label for="nombre" class="col-lg-2 control-label">Nombre</label>
						<div class="col-lg-10">
							<g:textField name="nombre" class="form-control"
								placeholder="Nombre" value="${usuario.nombre}" />
						</div>
					</div>

					<%--Apellido--%>
					<div class="form-group">
						<label for="apellido" class="col-lg-2 control-label">Apellido</label>
						<div class="col-lg-10">
							<g:textField name="apellido" class="form-control"
								placeholder="Apellido" value="${usuario.apellido}" />
						</div>
					</div>

					<%--E-mail--%>
					<div class="form-group">
						<label for="email" class="col-lg-2 control-label">E-mail</label>
						<div class="col-lg-10">
							<g:textField name="email" class="form-control"
								placeholder="E-mail" value="${usuario.email}" />
						</div>
					</div>

					<%--Contraseña--%>
					<div class="form-group">
						<label for="contrasenia" class="col-lg-2 control-label">Contraseña</label>
						<div class="col-lg-10">
							<g:textField name="contrasenia" class="form-control"
								placeholder="Contraseña" value="${usuario.contrasenia}" />
						</div>
					</div>

					<%--Tipo de usuario--%>
					<g:textField style="display:none" name="tipoUsuario"
						value="${usuario.getCliente()}" />

					<%--BOTÓN Submit--%>
					<div class="form-group">
						<div id="submit">
							<div class="col-lg-10 col-lg-offset-2">
								<g:submitButton name="crear" class="btn btn-primary"
									value="Crear" />
							</div>
						</div>
					</div>

			</g:form>
			</fieldset>
		</div>
	</div>

</body>
</html>