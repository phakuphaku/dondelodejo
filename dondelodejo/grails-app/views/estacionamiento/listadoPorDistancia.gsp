<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="primerLayout" />
<title>Estacionamientos cercanos</title>
</head>

<body>
	<%--//TODO Borrar el código comentado--%>
	<%--			<div id="page-body" role="main">--%>
	<%----%>
	<%--		<table border=1>--%>
	<%--			<tr>--%>
	<%--			<td width="100%" colspan="2">Resultados Encontrados para ${direccion}(dentro de los ${metros} metros) :</td>--%>
	<%--				--%>
	<%--			</tr>--%>
	<%--	    <g:each in="${resultados}" var="e">--%>
	<%--		    <tr>--%>
	<%--			    <td>--%>
	<%--					Nombre: ${e.nombre}<br/>--%>
	<%--					Direccion: ${e.ubicacion.direccionStr}<br/>--%>
	<%--					Alta: ${e.fechaAlta}<br/>--%>
	<%--					Edicion: ${e.fechaModificacion}<br/>--%>
	<%--					Borrado: ${e.fechaBaja}<br/>--%>
	<%--					CoordX: ${e.ubicacion.direccionX}<br/>--%>
	<%--					CoordY: ${e.ubicacion.direccionY}<br/>--%>
	<%--				</td>--%>
	<%--				<td>--%>
	<%--					<a href="mostrar/${e.id}">Ver Detalle</a>--%>
	<%--				</td>--%>
	<%--			</tr>	--%>
	<%--	    </g:each>--%>
	<%--		</table>--%>
	<%--		--%>
	<%--		</div>--%>
	<%--	<table border=1>--%>
	<%--		<tr>--%>
	<%--			<td width="100%" colspan="2">Resultados Encontrados para ${direccion}(dentro--%>
	<%--				de los ${metros} metros) :--%>
	<%--			</td>--%>
	<%----%>
	<%--		</tr>--%>
	<%--		<g:each in="${resultados}" var="e">--%>
	<%--			<tr>--%>
	<%--				<td>Nombre: ${e.nombre}<br /> Direccion: ${e.ubicacion.direccionStr}<br />--%>
	<%--					Alta: ${--%>
	<%--					e.fechaAlta--%>
	<%--				}<br /> Edicion: ${e.fechaModificacion}<br /> Borrado: ${--%>
	<%--				e.fechaBaja--%>
	<%--			}<br /> CoordX: ${e.ubicacion.direccionX}<br /> CoordY: ${e.ubicacion.direccionY}<br />--%>
	<%--				</td>--%>
	<%--				<td><a href="mostrar/${e.id}">Ver Detalle</a></td>--%>
	<%--			</tr>--%>
	<%--		</g:each>--%>
	<%--	</table>--%>

	<div id="page-body" role="main">

		<legend>
			Estacionamientos cercanos a <b> ${direccion}
			</b> <i>(radio ${metros} metros)
			</i>
		</legend>

		<table class="table table-striped table-hover table-clickable">

			<%--HEADER-------------------------------------%>
			<thead>
				<tr>
					<%--					<th>#</th>--%>
					<th>Nombre</th>
					<th>Dirección</th>
					<th>Coordenadas</th>
				</tr>
			</thead>

			<%--LINES--------------------------------------%>
			<tbody>
				<%--Clases: info, success, warning, danger, active--%>

				<g:each in="${resultados}" var="e">

					<%--//TODO Agregar TOOLTIP a la fila con info como warningns por malas calificaciones--%>
					<tr
						onclick='document.location = "<g:createLink action='mostrar' id='${e.id}'/>" '>
						<td>
							${e.nombre}
						</td>
						<td>
							${e.ubicacion.direccionStr}
						</td>
						<td>
							${e.ubicacion.direccionX} ; ${e.ubicacion.direccionY}
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</div>
</body>
</html>