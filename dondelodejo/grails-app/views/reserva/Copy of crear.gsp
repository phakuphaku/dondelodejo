<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="primerLayout" />
<title>Reservar</title>
</head>

<body>
	<%--
		<div id="page-body" role="main">
			<center>
				<g:form class="formulario" action="alta">
					<div class="background"/>
					<g:textField style="display:none" name="estacionamientoId" value="${estacionamientoId}"/>
					<div>
					    <label for="horaDeInicio">Fecha de Inicio:</label>
					    <g:datePicker name="horaDeInicio" value="${reservaFiltro?.horaDeInicio}" precision="minute" 
					    years="[2014,2015]"/>
					    
					    <!--<g:textField name="horaDeInicio" value="${reservaFiltro?.horaDeInicio}"/>-->
				    </div>
				    <div>
					    <label for="horasReservadas">Horas que quiere reservar:</label>
					    <g:textField name="horasReservadas" value="${reservaFiltro?.horasReservadas}"/>
				    </div>		    
					<div id="submit">
				    	<g:submitButton name="crear" value="Crear la Reserva" />
					</div>
				</g:form>
			</center>
		</div>
		<table border=1>
			<tr>
				<td width="80%" colspan="2">Reservas:</td>
			</tr>
	    <g:each in="${listadoReservas}" var="r">
		    <tr>
			    <td>
					Fecha: ${r.horaDeInicio}<br/>
					horasReservadas:${r.horasReservadas}<br/>
					estado:${r.estado}<br/>
				</td>

				<td>
					<g:link controller="reserva" action="borrar" id="${r.id}">Eliminar</g:link><br/>
				</td>

			</tr>	
	    </g:each>
		</table>
	--%>

	<div id="page-body" role="main">
		<center>
			<g:form class="formulario" action="alta">
				<div class="background" />
				<g:textField style="display:none" name="estacionamientoId"
					value="${estacionamientoId}" />
				<div>
					<label for="horaDeInicio">Fecha de Inicio:</label>
					<g:datePicker name="horaDeInicio"
						value="${reservaFiltro?.horaDeInicio}" precision="minute"
						years="[2014,2015]" />

					<!--<g:textField name="horaDeInicio" value="${reservaFiltro?.horaDeInicio}"/>-->
				</div>
				<div>
					<label for="horasReservadas">Horas que quiere reservar:</label>
					<g:textField name="horasReservadas"
						value="${reservaFiltro?.horasReservadas}" />
				</div>
				<div id="submit">
					<g:submitButton name="crear" value="Crear la Reserva" />
				</div>
			</g:form>
		</center>
	</div>
<%--
	<div id="page-body" role="main">
		<legend>Reservar cochera en estacionamiento</legend>
		<div class="well">
			<g:form class="formulario form-horizontal" action="alta">
				<fieldset>

					<g:textField style="display:none" name="estacionamientoId"
						value="${estacionamientoId}" />
					<%--
					<div>
						<label for="horaDeInicio">Fecha de Inicio:</label>
						<g:datePicker name="horaDeInicio"
							value="${reservaFiltro?.horaDeInicio}" precision="minute"
							years="[2014,2015]" />

						<!--<g:textField name="horaDeInicio" value="${reservaFiltro?.horaDeInicio}"/>-->
					</div>
					<%--Fecha y hora //TODO Cambiar por clases de bootstrap --%>
					<div class="form-group">
						<label for="horaDeInicio" class="col-lg-2 control-label">¿Para
							cuándo?</label>
						<div class="col-lg-10">
							<g:datePicker name="horaDeInicio"
								value="${reservaFiltro?.horaDeInicio}" precision="minute"
								years="[2014,2015]" />
						</div>
					</div>

					<div class="form-group">
						<%--<g:textField name="horasReservadas" value="${reservaFiltro?.horasReservadas}" />--%>
						<label for="horasReservadas" class="col-lg-2 control-label">
							¿Cuántas horas?</label>
						<div class="col-lg-10">
							<g:select id="horasReservadas" class="form-control"
								name="horasReservadas" value="${reservaFiltro?.horasReservadas}"
								from="${['1': '1', '2': '2', '3': '3']}" optionKey="key"
								optionValue="value" />
						</div>
					</div>

					<div id="submit">
						<g:submitButton name="crear" value="Crear la Reserva" />
					</div>

					<%--BOTÓN Submit--%>
					<div class="form-group">
						<div id="submit">
							<div class="col-lg-10 col-lg-offset-0">
								<g:submitButton name="crear" class="btn btn-primary"
									value="Crear la Reserva" />
							</div>
						</div>
					</div>
				</fieldset>
			</g:form>
		</div>
	</div>

	<table border=1>
		<tr>
			<td width="80%" colspan="2">Reservas:</td>
		</tr>
		<g:each in="${listadoReservas}" var="r">
			<tr>
				<td>Fecha: ${r.horaDeInicio}<br /> horasReservadas:${r.horasReservadas}<br />
					estado:${r.estado}<br />
				</td>

				<td><g:link controller="reserva" action="borrar" id="${r.id}">Eliminar</g:link><br />
				</td>

			</tr>
		</g:each>
	</table>
</body>
</html>