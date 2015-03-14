<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="primerLayout" />
<title>Buscar</title>
</head>
<body>
	<%--
	<div id="page-body" role="main">
		<center>
			<g:form class="formulario" action="buscarPorDistancia">
				<div class="background" />
				<div>
					<label for="direccionDestino">Direccion de Destino:</label>
					<g:textField name="direccion" value="${direccion}"
						placeholder="Escriba Aqui la direccion" required="" />
				</div>
				<div>
					<label for="localidad">Localidad:</label>
					<g:textField name="localidad" value="${localidad}" />
				</div>
				<div>
					<label for="pais">Pais:</label>
					<g:textField name="pais" value="${pais}" />
				</div>
				<div>
					<label for="metros">Cercanía:</label>
					<g:field type="number" name="metros" value="${metros}" />
				</div>
				<div id="submit">
					<right>
					<g:submitButton name="buscar" value="Buscar para esta distancia" /></right>
				</div>
			</g:form>
		</center>
	</div>
	--%>

	<div id="page-body" role="main">

		<div class="well">

			<g:form class="formulario form-horizontal"
				action="buscarPorDistancia">
				<fieldset>

					<legend>Buscar estacionamiento cercano</legend>

					<%--Dirección--%>
					<div class="form-group">
						<div class="col-lg-12">
							<%--<g:textField name="email" class="form-control"--%>
							<%--placeholder="E-mail" value="${usuario.email}" />--%>
							<g:textField name="direccion" class="form-control"
								value="${direccion}" placeholder="Dirección" required="" />
						</div>
					</div>

					<%--Localidad--%>
					<div class="form-group">
						<div class="col-lg-12">
							<%--						<label for="localidad">Localidad:</label>--%>
							<g:textField name="localidad" placeholder="Localidad"
								class="form-control" value="${localidad}" />
						</div>
					</div>

					<%--País--%>
					<div class="form-group">
						<div class="col-lg-12">
							<%--						<label for="pais">Pais:</label>--%>
							<g:textField name="pais" placeholder="País" class="form-control"
								value="${pais}" />
						</div>
					</div>

					<%--Cercanía--%>
					<div class="form-group">
						<div class="col-lg-12">
							<%--						<label for="metros">Cercanía:</label>--%>
							<g:field type="number" placeholder="Cercanía"
								class="form-control" name="metros" value="${metros}" />
						</div>
					</div>

					<%--BOTÓN Submit--%>
					<div class="form-group">
						<div id="submit">
							<div class="col-lg-10 col-lg-offset-0">
								<g:submitButton name="buscar" class="btn btn-primary"
									value="Buscar" />

							</div>						</div>
					</div>
				</fieldset>
			</g:form>
		</div>
	</div>
</body>
</html>