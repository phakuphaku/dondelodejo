
<table class="table table-striped table-hover table-clickable">
	<thead>
		<tr>
			<th>Horario Reservado</th>
			<th>Duración</th>
			<th>Dirección</th>
			<th colspan="2">Estado</th>
		</tr>
	</thead>

	<g:each in="${listadoReservas}" var="r">
		<tr>
			<td>${r.horaDeInicio}</td>								<%--Horario Reservado--%>
			<td>${r.cantidadHorasReservadas}</td>					<%--Duración--%>
			<td>${r.estacionamiento.ubicacion.direccionStr}</td>	<%--Dirección--%>
			
			<%--Estado--%>											<%--Estados: CANCELADA / PENDIENTE--%>
			<g:if test="${!r.esUtilizada()}">
				<%--<td>${r.estado}</td>--%>
				<td colspan="2">
					<g:if test="${r.estado == "CANCELADA"}">
						<span class="label label-primary">
					</g:if>
					<g:elseif test="${r.estado == "PENDIENTE"}">
						<span class="label label-warning">
					</g:elseif>
					<g:elseif test="${r.estado == "ACEPTADA"}">
						<span class="label label-danger">
					</g:elseif>
					<g:elseif test="${r.estado == "CALIFxCLI"}">
						<span class="label label-default">
					</g:elseif>
					<g:elseif test="${r.estado == "COMPLETA"}">
						<span class="label label-success">
					</g:elseif>
					${r.estado}</span>
				</td>
			</g:if>

			<g:else>
				<td>												<%--Estado: UTILIZADA--%>
					<g:if test="${r.estado == "UTILIZADA"}">
						<span class="label label-info">
					</g:if>
					${r.estado}</span>
				</td>

				<%--Botón CALIFICAR--%>
				<g:if test="${!session.usuario.esSoporte()}">
					<td>
						<a class="crearCalificacion${r.id} btn btn-default btn-xs"" href="#">Calificar Ahora</a>
					</td>
					<script>
						$(document).ready(function(){
						    $('a.crearCalificacion${r.id}').click(function(){	
						        $('tr.crearCalificacion${r.id}').fadeToggle(400);
							});
						});
					</script>
				</g:if>
			</g:else>
		</tr>
		<g:if test="${r.esUtilizada()}">
			<tr class="crearCalificacion${r.id}" style="display: none">
				<td colspan="5">
					<table class="table table-striped table-hover table-clickable"><tr><td>
					<g:form action="calificarReserva" class="form-horizontal">
						<fieldset>
							<g:textField style="display:none" name="reservaId" value="${r.id}"/>
							
							<%--Calificación-------------------%>
							<div class="form-group">
							    <label for="calif" class="col-lg-1 control-label">Calificación</label>
							    <div class="col-lg-2">
									<%--<g:select id="calif" name="valor" from="${1..5}" value="${valor}" noSelection="['':'Calificación']" class="form-control"/>--%>
									<g:select id="calif" name="valor" from="${1..5}" value="3" class="form-control"/>
							    </div>
						    </div>
							
							<%--Detalle------------------------%>
							<div class="form-group">
							    <label for="detalle" class="col-lg-1 control-label">Detalle</label>
							    <div class="col-lg-10">
									<g:textField name="detalle" size="120" class="form-control"/>
						        </div>
						    </div>

							<%--Botón CALIFICAR----------------%>
							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-1">
									<g:submitButton	name="Calificar" class="btn btn-primary btn" value="Calificar" />
								</div>
							</div>
							
						</fieldset>
					</g:form> <!-- Hasta aqui el formulario -->
					</td></tr></table>
				</td>
			</tr>
		</g:if> <!-- Hasta aqui la seccion de utilizada -->

		<g:if test="${r.esCompleta()}">
			<tr>
				<td colspan="5" class="success">
					<%--<table class="table table-striped table-hover table-clickable">--%>
					<table class="table">
						<%--<tr><th colspan="3">Calificaciones</th></tr>--%>
						<tr>
							<td width="1%" class="autowidth">Calificaste</td>
							<td width="1%" class="stars">
								<g:if		test="${r.calificacionDelClienteAlEstacionamiento.valor == 1}">★☆☆☆☆</g:if> 
								<g:elseif	test="${r.calificacionDelClienteAlEstacionamiento.valor == 2}">★★☆☆☆</g:elseif>
								<g:elseif	test="${r.calificacionDelClienteAlEstacionamiento.valor == 3}">★★★☆☆</g:elseif>
								<g:elseif	test="${r.calificacionDelClienteAlEstacionamiento.valor == 4}">★★★★☆</g:elseif>
								<g:elseif	test="${r.calificacionDelClienteAlEstacionamiento.valor == 5}">★★★★★</g:elseif>
							</td>
							<td><i><q>${r.calificacionDelClienteAlEstacionamiento.detalle}</i></q></td>
						</tr>
						<tr>
							<td width="1%" class="autowidth">&#8618 Respondieron</td>
							<td width="1%" class="stars">
								<g:if		test="${r.calificacionDelEstacionamientoAlCliente.valor == 1}">★☆☆☆☆</g:if>
								<g:elseif	test="${r.calificacionDelEstacionamientoAlCliente.valor == 2}">★★☆☆☆</g:elseif>
								<g:elseif	test="${r.calificacionDelEstacionamientoAlCliente.valor == 3}">★★★☆☆</g:elseif>
								<g:elseif	test="${r.calificacionDelEstacionamientoAlCliente.valor == 4}">★★★★☆</g:elseif>
								<g:elseif	test="${r.calificacionDelEstacionamientoAlCliente.valor == 5}">★★★★★</g:elseif>
							</td>
							<td><i><q>${r.calificacionDelEstacionamientoAlCliente.detalle}</q></i></td>
						</tr>
					</table>
				</td>
			</tr>
		</g:if>
	</g:each>
</table>