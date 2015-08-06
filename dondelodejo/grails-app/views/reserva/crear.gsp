<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="primerLayout" />
<title>Reservar</title>
</head>

<body>
<%----%>
<%--	<div id="page-body" role="main" align="center">--%>
<%--		<center>--%>
<%--		<div class="well">--%>
<%--			<g:form class="formulario" action="alta">--%>
<%--			<div class="background" />--%>
<%--				<g:textField style="display:none" name="estacionamientoId" value="${estacionamientoId}" />--%>
<%--				<div>--%>
<%--					<label for="horaDeInicio">Fecha de Inicio:</label>--%>
<%--					<g:datePicker name="horaDeInicio" value="${reservaFiltro?.horaDeInicio}" precision="minute" years="[2015,2016]" />--%>
<%--					<!--<g:textField name="horaDeInicio" value="${reservaFiltro?.horaDeInicio}"/>-->--%>
<%--				</div>--%>
<%--				<div>--%>
<%--					<label for="cantidadHorasReservadas">Horas que quiere reservar:</label>--%>
<%--					<g:textField name="cantidadHorasReservadas" value="${reservaFiltro?.cantidadHorasReservadas}" />--%>
<%--				</div>--%>
<%--				<div id="submit">--%>
<%--					<g:submitButton name="crear" value="Crear la Reserva" />--%>
<%--				</div>--%>
<%--			</div>--%>
<%--			</g:form>--%>
<%----%>
<%--		</div>--%>
<%--	</center>--%>
<%--	</div>--%>

	<div class="panel panel-info">
		<div class="panel-heading">	
	    	<h3 class="panel-title">Crear reserva</h3>
	  	</div>

	  	<div class="panel-body">
			<g:form class="formulario form-horizontal" action="alta">
				<fieldset>
	  				
	  				<g:textField style="display:none" name="estacionamientoId" value="${estacionamientoId}" />
	
	  				<div class="form-group">
						<label for="horaDeInicio" class="col-lg-2 control-label">Fecha de Inicio</label>
			    		<div class="col-lg-10">
							<g:datePicker name="horaDeInicio" value="${reservaFiltro?.horaDeInicio}" precision="minute" years="[2015,2016]" />
			    		</div>
					</div>
						
					<div class="form-group">
						<label for="cantidadHorasReservadas" class="col-lg-2 control-label">¿Cuántas horas?</label>
		      			<div class="col-lg-1">
							<g:textField class="form-control" name="cantidadHorasReservadas" value="${reservaFiltro?.cantidadHorasReservadas}" />
						</div>
					</div>
				
					<div class="form-group">
						<div class="col-lg-10 col-lg-offset-2">
							<div id="submit"><g:submitButton name="crear" value="Crear la Reserva" class="btn btn-info"/></div>
						</div>
					</div>
				
				</fieldset>
			</g:form>
			
	  	</div>
	</div>

	<%--Reservas--%>
	<legend>
		Reservas (<g:link controller="reserva" action="cambiarEstadoListadoCompletoCrear" id="${estacionamientoId}">Ver todo</g:link>)
	</legend>

	<g:render template="listadoReservasParaCliente" />

</body>
</html>