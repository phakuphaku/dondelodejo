
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
				<td>
					${r.horaDeInicio}
				</td>
				<td>
					${r.cantidadHorasReservadas}
				</td>
				<td>
					${r.estacionamiento.ubicacion.direccionStr}
				</td>
				<g:if test="${!r.esUtilizada()}">
					<td colspan="2">
						${r.estado}
					</td>
				</g:if>
				<g:else>
					<td>
						${r.estado}
					</td>
					<td><a class="crearCalificacion${r.id}" href="#">Calificar Ahora</a></td>
					<script>
					$(document).ready(function(){
					    $("a.crearCalificacion${r.id}").click(function(){
					        $("tr.crearCalificacion${r.id}").fadeToggle(1000);
					    });
					});
					</script>
				</g:else>

				
			</tr>
			<g:if test="${r.esUtilizada()}">
				<tr class="crearCalificacion${r.id}" style="display:none">
					<td colspan="5">
						<table class="table table-striped table-hover table-clickable">
							<tr>
								<th>Calificar esta reserva:</th>
							</tr>
							<g:form action="calificarReserva">
							<tr>
								<td colspan="4">
								<g:select name="valor" from="${1..5}" value="${valor}" noSelection="['':'-Su Calificación-']"/>	Detalle: <g:textField name="detalle" />
								</td>
							</tr>
							<tr>
								<td colspan="4">
										<g:textField style="display:none" name="reservaId" value="${r.id}" />
										<g:submitButton name="Calificar" class="btn btn-primary btn-xs" value="Calificar" />
								</td>
							</tr>
							</g:form>
							<!-- Hasta aqui el formulario -->
						</table>
					</td>
				</tr>
			</g:if>
			<!-- Hasta aqui la seccion de utilizada -->
			
			<!-- TODO Terminar de emprolijar -->
			<g:if test="${r.esCompleta()}">
			<tr>
				<td colspan="5">
				<table class="table table-striped table-hover table-clickable">
					<tr>
						<th>Calificaciones de esta reserva:</th>
					</tr>
					<tr>
						<td colspan="4">----Su Calificación: (${r.calificacionDelClienteAlEstacionamiento.valor}/5) ${r.calificacionDelClienteAlEstacionamiento.detalle}</td>
					</tr>
					<tr>
						<td colspan="4">----Su Respuesta: (${r.calificacionDelEstacionamientoAlCliente.valor}/5) ${r.calificacionDelEstacionamientoAlCliente.detalle}</td>
					</tr>
				</table>
				</td>
			</tr>	
			</g:if>
		</g:each>
	</table>