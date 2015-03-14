<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="primerLayout" />
<title>Primer Vista</title>

</head>
<body>
	<%--
		<div id="page-body" role="main">

			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

		<g:form action="actualizar" >
			<g:textField style="display:none" name="id" value="${id}"/>
		    <br/>
		    <label for="nombre">nombre</label>
		    <g:textField name="nombre" value="${estacionamiento.nombre}"/>
		    <br/>
		    <label for="direccionStr">direccionStr</label>
		    <g:textField name="direccionStr" value="${estacionamiento.ubicacion?.direccionStr}"/>
		    <br/>
		  	<label for="localidad">Localidad:</label>
		    <g:textField name="localidad" value="${estacionamiento.ubicacion?.localidad}"/>
		    <br/>
		  	<label for="pais">Pais:</label>
		    <g:textField name="pais" value="${estacionamiento.ubicacion?.pais}"/>
		    <br/>
		    <g:submitButton name="actualizar" value="Guardar Cambios" />
		</g:form>
		</div>
	--%>

	<div id="page-body" role="main">

		<div class="well">
			<legend>Editar estacionamiento</legend>

			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>

			<%--			<g:form action="actualizar">--%>
			<g:form class="formulario form-horizontal" action="actualizar">
				<fieldset>

					<g:textField style="display:none" name="id" value="${id}" />

					<%--Nombre--%>
					<div class="form-group">
						<label for="nombre" class="col-lg-2 control-label">Nombre</label>
						<div class="col-lg-10">
							<g:textField name="nombre" class="form-control"
								placeholder="Nombre" value="${estacionamiento.nombre}" />
						</div>
					</div>

					<%--Dirección--%>
					<div class="form-group">
						<label for="direccionStr" class="col-lg-2 control-label">Dirección</label>
						<div class="col-lg-10">
							<g:textField name="direccionStr" class="form-control"
								placeholder="Dirección"
								value="${estacionamiento.ubicacion?.direccionStr}" />

						</div>
					</div>

					<%--Localidad--%>
					<div class="form-group">
						<label for="localidad" class="col-lg-2 control-label">Localidad</label>
						<div class="col-lg-10">
							<g:textField name="localidad" class="form-control"
								placeholder="Localidad"
								value="${estacionamiento.ubicacion?.localidad}" />
						</div>
					</div>

					<%--País--%>
					<div class="form-group">
						<label for="pais" class="col-lg-2 control-label">Pais</label>
						<div class="col-lg-10">
							<g:textField name="pais" class="form-control" placeholder="País"
								value="${estacionamiento.ubicacion?.pais}" />
						</div>
					</div>

					<%--BOTÓN Submit--%>
					<div class="form-group">
						<div id="submit">
							<div class="col-lg-10 col-lg-offset-2">
								<g:submitButton name="actualizar" class="btn btn-primary"
									value="Guardar Cambios" />
							</div>
						</div>
					</div>
				</fieldset>
			</g:form>
		</div>
	</div>

</body>
</html>