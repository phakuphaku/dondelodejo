<!DOCTYPE html>
<html>

<head>
<meta name="layout" content="primerLayout" />
<title>Primer Vista</title>
</head>

<body>
	<div id="page-body" role="main">

		<%-- Se obtiene el estacionamiento del administrador --%>

		<%--HEADER--%>
		<legend>
			Estacionamiento <strong>${estacionamiento.nombre}</strong>
		</legend>

		<%--Detalle del estacionamiento--%>
		<blockquote>
			<p>${estacionamiento.ubicacion?.direccionStr}</p>
			<p>${estacionamiento.numCocheras} cocheras.</p>
		</blockquote>

		<%--Reservas--%>
		<legend>
			Reservas (<g:link controller="estacionamiento" action="cambiarEstadoListadoCompleto">Ver todo</g:link>)
		</legend>

		<table class="table table-striped table-hover table-clickable">
			<thead>
				<tr>
					<th>Horario Reservado</th>
					<th>Duración</th>
					<th colspan="3">Estado</th>
				</tr>
			</thead>

			<g:each in="${listadoReservas}" var="r">
				<tr>
					<td>${r.horaDeInicio}</td>
					<td>${r.cantidadHorasReservadas}</td>
					<g:if test="${r.esCompleta() || r.esUtilizada()}">
						<td colspan="3">
							<%--${r.estado}--%>
							<g:if test="${r.estado == "COMPLETA"}">
								<span class="label label-success">
							</g:if>
							<g:elseif test="${r.estado == "UTILIZADA"}">
								<span class="label label-info">
							</g:elseif>
								${r.estado}
							</span>
						</td>
					</g:if>
					<g:else>
						<%--<td>${r.estado}</td>--%>
						<td>
							<g:if test="${r.estado == "CALIFxCLI"}">
								<span class="label label-default">
							</g:if>
							<g:elseif test="${r.estado == "CANCELADA"}">
								<span class="label label-primary">
							</g:elseif>
							<g:elseif test="${r.estado == "PENDIENTE"}">
								<span class="label label-warning">
							</g:elseif>
							<g:elseif test="${r.estado == "ACEPTADA"}">
								<span class="label label-danger">
							</g:elseif>
								${r.estado}
							</span>
						</td>
					</g:else>
					<g:if test="${r.esPendiente()}">
						<td><g:link action="aceptarCalificacion" id="${r.id}" class="btn btn-success btn-sm">Aceptar</g:link></td>
						<td><g:link action="cancelarCalificacion" id="${r.id}" class="btn btn-primary btn-sm">Cancelar</g:link></td>
					</g:if>
					<g:if test="${r.esAceptada()}">
						<td colspan="2"><g:link action="utilizarCalificacion" id="${r.id}" class="btn btn-success btn-sm">Marcar como utilizada</g:link></td>
					</g:if>
					<g:if test="${r.esCalifXCliente()}">
						<td colspan="2"><a class="crearCalificacion${r.id} btn btn-default btn-xs" href="#">Calificar ahora</a></td>
						<script>
							$(document).ready(function(){
							    $('a.crearCalificacion${r.id}').click(function(){
							        $('tr.crearCalificacion${r.id}').fadeToggle(400);
								});
							});
						</script>
					</g:if>
				</tr>

				<g:if test="${r.esCalifXCliente()}">
					<tr class="crearCalificacion${r.id}" style="display: none">
						<td colspan="5">
							<table class="table table-striped table-hover table-clickable"><tr><td>
							<g:form action="calificarReserva" class="form-horizontal">
								<fieldset>
									<g:textField style="display:none" name="reservaId" value="${r.id}"/>
									
									<%--Calificación--%>
									<div class="form-group">
									    <label for="calif" class="col-lg-1 control-label">Calificación</label>
									    <div class="col-lg-2">
<%--											<g:select id="calif" name="valor" from="${1..5}" value="${valor}" noSelection="['':'Calificación']" class="form-control"/>--%>
											<g:select id="calif" name="valor" from="${1..5}" value="3" class="form-control"/>
									    </div>
								    </div>
									
									<%--Detalle--%>
									<div class="form-group">
									    <label for="detalle" class="col-lg-1 control-label">
									    	Detalle
									    </label>
									    <div class="col-lg-10">
											<g:textField name="detalle" size="120" class="form-control"/>
								        </div>
								    </div>

									<%--Botón CALIFICAR--%>
									<div class="form-group">
										<div class="col-lg-10 col-lg-offset-1">
											<g:submitButton	name="Calificar" class="btn btn-primary btn" value="Calificar" />
										</div>
									</div>
									
								</fieldset>
							</g:form><!-- Hasta aqui el formulario -->
							</td></tr></table>
						</td>
					</tr>
				</g:if>

				<g:if test="${r.esCompleta()}">
					<tr>
						<td colspan="5" class="success">
							<%--<div class="well well-sm">--%> <%--<table class="table table-striped table-hover table-clickable">--%>
							<table class="table">
								<%--<tr><th>Calificaciones</th></tr>--%>
								<tr>
									<%--<td colspan="4">----Lo calificaron: (${r.calificacionDelClienteAlEstacionamiento.valor}/5)--%>
									<%--${r.calificacionDelClienteAlEstacionamiento.detalle}</td>--%>
									<td width="1%" class="autowidth">Te calificaron</td>
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
									<%--<td colspan="4">----Su Respuesta: (${r.calificacionDelEstacionamientoAlCliente.valor}/5)--%>
									<%--${r.calificacionDelEstacionamientoAlCliente.detalle}</td>--%>
									<td width="1%" class="autowidth">&#8618 Tu réplica</td>
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

<%--Panel de control de las cocheras------------------------------------------------------------%>
		<div class="panel panel-primary">
		<div class="panel-heading">
    		<h3 class="panel-title">Administración de cocheras</h3>
  		</div>
  		<div class="panel-body">
			<table class="table table-striped table-hover ">
				<thead><tr>
						<th>#</th>
						<th>$ 60'</th>
						<th>$ 15'</th>
						<th>Ingreso</th>
						<th>Egreso (esperado)</th>
						<th>Ocupar / Liberar</th>
				</tr></thead>
	
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
					<td>$ ${c.precioCocheraHora}</td>
					<td>$ ${c.precioCocheraFraccion}</td>
	
					<%--Si la cochera está ocupada, se muestra la hora de ingreso y egreso.--%>
					<g:if test='${c.estaOcupada()}'>
						<td>${c.horaOcupacion}</td>
						<td>${c.horaLiberacion}</td>
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
			</div>
		</div>
<%--		<g:link controller="estacionamiento" action="listado">Volver al listado</g:link>--%>
	</div>
</body>

</html>