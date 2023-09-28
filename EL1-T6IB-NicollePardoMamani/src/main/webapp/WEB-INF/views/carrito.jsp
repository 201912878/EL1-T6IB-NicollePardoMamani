<%@page import="pe.cibertec.evaluacion.model.Carrito"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Citas</title>
</head>
<body>

	<%@include file="template/menu.jsp"%>

	<%
	List<Carrito> data = (List<Carrito>) request.getAttribute("articulosCarrito");
	double subtotal = 0;
	%>

	<div class="container-fluid">
		<h1>Carrito</h1>
		<hr>

		<%@include file="template/message-box.jsp"%>

		<div class="row col-12">
			<div class="col-10">
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th>Item</th>
								<th>Imagen</th>
								<th>Articulo</th>
								<th>Descripcion</th>
								<th>Precio</th>
								<th>Cantidad</th>
								<th>Total</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							<%
							if (data != null) {
								for (Carrito item : data) {
									subtotal += item.getPrecio() * item.getCantidad();
							%>
							<tr>
								<td><%=item.getId()%></td>
								<td><img class="img-responsive" width="100"
									src="<%=item.getArticulo().getImagen()%>" alt="Card image"></td>
								<td><%=item.getArticulo().getNombre()%></td>
								<td><%=item.getArticulo().getDescripcion()%></td>
								<td><%=item.getPrecio()%></td>
								<td><input class="form-control"
									value="<%=item.getCantidad()%>" style="width: 100px"></td>
								<td><%=item.getPrecio() * item.getCantidad()%></td>
								<td><a href="eliminar-articulo?id=<%=item.getId()%>">Eliminar</a></td>
							</tr>
							<%
							}
							}
							%>
						</tbody>
					</table>
				</div>
			</div>

			<div class="col-2">

				<form action="comprar" method="post">
					<div class="card">
						<div class="card-header">Generar Compra</div>
						<div class="card-body">
							<div class="form-group">
								<label for="subtotal">Subtotal <b class="text-danger">*</b></label>
								<input class="form-control" type="number" min="0" step="0.01" name="subtotal" value="<%=subtotal %>" required>
							</div>
							
							<div class="form-group">
								<label for="precio_envio">Precio envío <b class="text-danger">*</b></label>
								<input class="form-control" type="number" min="0" step="0.01" name="precio_envio" value="0" required>
							</div>
							
							<div class="form-group">
								<label for="descuento">Descuento <b class="text-danger">*</b></label>
								<input class="form-control" type="number" min="0" step="0.01" name="descuento" value="0" required>
							</div>
							
							<div class="form-group">
								<label for="total">Total a pagar <b class="text-danger">*</b></label>
								<input class="form-control" type="number" min="0" step="0.01" name="total" value="<%=subtotal %>" required>
							</div>
							
						</div>
						<div class="card-footer">
							<a class="btn btn-danger col-12 m-1">Actualiza el carrito</a> 
							<input
								class="btn btn-primary col-12 m-1" type="submit"
								value="Realiza pago y registra">

						</div>
					</div>
				</form>

			</div>

		</div>


	</div>

</body>
</html>