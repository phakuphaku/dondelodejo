<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="primerLayout" />
<title>Primer Vista</title>

</head>
<body>
	<%--
	<div id="page-body" role="main">
		<center>
			<g:form class="formulario" action="guardar">
				<div class="background" />
				<div>
					<label for="nombre">Nombre:</label>
					<g:textField name="nombre" value="${estacionamiento.nombre}" />
				</div>
				<div>
					<label for="direccionStr">Direccion:</label>
					<g:textField name="direccionStr"
						value="${estacionamiento.ubicacion.direccionStr}" />
				</div>
				<div>
					<label for="localidad">Localidad:</label>
					<g:textField name="localidad"
						value="${estacionamiento.ubicacion.localidad}" />
				</div>
				<div>
					<label for="pais">Pais:</label>
					<g:textField name="pais" value="${estacionamiento.ubicacion.pais}" />
				</div>
				<div>
					<label for="numCocheras">Cantidad de Cocheras:</label>
					<g:textField name="numCocheras"
						value="${estacionamiento.numCocheras?:1}" />
				</div>
				<div>
					<label for="precioCocheraHora">Precio por Hora:</label>
					<g:textField name="precioCocheraHora"
						value="${precioCocheraHora?precioCocheraHora:null}" />
				</div>
				<div>
					<label for="precioCocheraFraccion">Precio por Fracción:</label>
					<g:textField name="precioCocheraFraccion"
						value="${precioCocheraFraccion?precioCocheraFraccion:null}" />
				</div>
				<div id="submit">
					<right> <g:submitButton name="crear"
						value="Crear el Estacionamiento" /></right>
				</div>
			</g:form>
		</center>
	</div>
--%>

	<div id="page-body" role="main">
		<div class="well">
			<legend>Crear estacionamiento</legend>

			<g:form class="formulario form-horizontal" action="guardar">
				<fieldset>
					<%--
					<div>
						<label for="nombre">Nombre:</label>
						<g:textField name="nombre" value="${estacionamiento.nombre}" />
					</div>
--%>
					<%--Nombre--%>
					<div class="form-group">
						<label for="nombre" class="col-lg-2 control-label">Nombre</label>
						<div class="col-lg-10">
							<g:textField name="nombre" class="form-control"
								placeholder="Nombre" value="${estacionamiento.nombre}" />
						</div>
					</div>


					<%--
					<div>
						<label for="direccionStr">Direccion:</label>
						<g:textField name="direccionStr"
							value="${estacionamiento.ubicacion.direccionStr}" />
					</div>
--%>
					<%--Dirección--%>
					<div class="form-group">
						<label for="direccionStr" class="col-lg-2 control-label">Dirección</label>
						<div class="col-lg-10">
							<g:textField name="direccionStr" class="form-control"
								placeholder="Dirección"
								value="${estacionamiento.ubicacion.direccionStr}" />
						</div>
					</div>


					<%--
					<div>
						<label for="localidad">Localidad:</label>
						<g:textField name="localidad"
							value="${estacionamiento.ubicacion.localidad}" />
					</div>
--%>
					<%--Localidad--%>
					<div class="form-group">
						<label for="localidad" class="col-lg-2 control-label">Localidad</label>
						<div class="col-lg-10">
							<g:textField name="localidad" class="form-control"
								placeholder="Localidad"
								value="${estacionamiento.ubicacion.localidad}" />
						</div>
					</div>

					<%--
					<div>
						<label for="pais">Pais:</label>
						<g:textField name="pais" value="${estacionamiento.ubicacion.pais}" />
					</div>
--%>
					<%--País--%>
					<div class="form-group">
						<label for="pais" class="col-lg-2 control-label">País</label>
						<div class="col-lg-10">
							<g:textField name="pais" class="form-control" placeholder="País"
								value="${estacionamiento.ubicacion.pais}" />
						</div>
					</div>

					<%--
					<div>
						<label for="numCocheras">Cantidad de Cocheras:</label>
						<g:textField name="numCocheras"
							value="${estacionamiento.numCocheras?:1}" />
					</div>
--%>
					<%--Cantidad de cocheras--%>
					<div class="form-group">
						<label for="numCocheras" class="col-lg-2 control-label">Cantidad
							de cocheras</label>
						<div class="col-lg-10">
							<g:textField name="numCocheras" class="form-control"
								placeholder="numCocheras"
								value="${estacionamiento.numCocheras?:1}" />
						</div>
					</div>
					<%--
					<div>
						<label for="precioCocheraHora">Precio por Hora:</label>
						<g:textField name="precioCocheraHora"
							value="${precioCocheraHora?precioCocheraHora:null}" />
					</div>
					--%>
					<%--Precio por hora--%>
					<div class="form-group">
						<label for="precioCocheraHora" class="col-lg-2 control-label">Precio
							por hora</label>
						<div class="col-lg-10">
							<g:textField name="precioCocheraHora" class="form-control"
								placeholder="Precio por hora"
								value="${precioCocheraHora?precioCocheraHora:null}" />
						</div>
					</div>


					<%--
					<div>
						<label for="precioCocheraFraccion">Precio por Fracción:</label>
						<g:textField name="Precio por hora"
							value="${precioCocheraFraccion?precioCocheraFraccion:null}" />
					</div>
					--%>
					<%--Precio por hora--%>
					<div class="form-group">
						<label for="precioCocheraFraccion" class="col-lg-2 control-label">Precio
							por fracción 15'</label>
						<div class="col-lg-10">
							<g:textField name="precioCocheraFraccion" class="form-control"
								placeholder="Precio por fracción 15'"
								value="${precioCocheraHora?precioCocheraFraccion:null}" />
						</div>
					</div>

					<%--
					<div id="submit">
						<right> <g:submitButton name="crear"
							value="Crear el Estacionamiento" /></right>
					</div>
--%>
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