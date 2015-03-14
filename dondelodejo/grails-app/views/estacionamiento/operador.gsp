<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="primerLayout" />
<title>Primer Vista</title>

</head>
<body>
	<%--
		<div id="page-body" role="main">
			Estacionamiento numero: ${estacionamiento.id}
			<table border=1>
					<tr>
			    <g:each in="${estacionamiento.cocheras}" var="c">

				                <g:if test='${c.estaOcupada()}'>
									<td class="tdOperador tdOcupada">
								</g:if>
								<g:else>
									<td class="tdOperador tdLibre" >
								</g:else>
							<g:form action="cambiarEstadoCochera" >
							
								${c.numero}<br/>
								Hora:${c.precioCocheraHora} ARS<br/>
								Fraccion:${c.precioCocheraFraccion} ARS<br/>
				                <g:textField style="display:none" name="estacionamientoId" value="${estacionamiento.id}"/>
				                <g:textField style="display:none" name="cocheraId" value="${c.id}"/>
				                <g:if test='${c.estaOcupada()}'>
									<g:submitButton name="liberar" value="LIBERAR" />
								</g:if>
								<g:else>
									<g:submitButton name="ocupar" value="OCUPAR" />
								</g:else>
			                </g:form>
						</td>
			         <g:if test='${(c.numero % 10)==0}'>
			         </tr><tr>
					</g:if>
				</g:each>
					</tr>
			</table>
		<a href="../listado">volver al listado</a>		
		</div>
	--%>

	<div id="page-body" role="main">

		<%--HEADER--%>
		<legend>
			Estacionamiento <b> ${estacionamiento.nombre}
			</b>
		</legend>
		<%--Detalle del estacionamiento--%>
		<blockquote>
			<p>
				${estacionamiento.ubicacion?.direccionStr}
			</p>
			<p>
				${estacionamiento.numCocheras}
				cocheras.
			</p>
		</blockquote>

		<table class="table table-striped table-hover ">
			<thead>
				<tr>
					<th>#</th>
					<th>$ 60'</th>
					<th>$ 15'</th>
					<th>Ingreso</th>
					<th>Egreso (esperado)</th>
					<th>Ocupar / Liberar</th>
				</tr>
			</thead>

			<g:each in="${estacionamiento.cocheras}" var="c">
				<g:if test='${c.estaOcupada()}'>
					<tr class="danger">
						<td class="tdOperador tdOcupada">
				</g:if>
				<g:else>
					<tr class="">
						<td class="tdOperador tdLibre">
				</g:else>
				${c.numero}
				</td>
				<td>$ ${c.precioCocheraHora}
				</td>
				<td>$ ${c.precioCocheraFraccion}
				</td>

				<%--Si la cochera está ocupada, se muestra la hora de ingreso y egreso.--%>
				<g:if test='${c.estaOcupada()}'>
					<td>
						${c.horaOcupacion}
					</td>
					<td>
						${c.horaLiberacion}
					</td>
				</g:if>
				<g:else>
					<td></td>
					<td></td>
				</g:else>

				<td><g:form action="cambiarEstadoCochera">
						<g:textField style="display:none" name="estacionamientoId"
							value="${estacionamiento.id}" />
						<g:textField style="display:none" name="cocheraId" value="${c.id}" />
						<g:if test='${c.estaOcupada()}'>
							<g:submitButton name="liberar" class="btn btn-primary btn-xs"
								value="LIBERAR" />
						</g:if>
						<g:else>
							<g:submitButton name="ocupar" class="btn btn-default btn-xs"
								value="OCUPAR" />
						</g:else>
					</g:form></td>
			</g:each>
			</tr>
		</table>
		<g:link controller="estacionamiento" action="listado">Volver al listado</g:link>
	</div>
</body>

</html>