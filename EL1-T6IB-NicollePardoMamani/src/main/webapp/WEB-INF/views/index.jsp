<%@page import="pe.cibertec.evaluacion.model.Articulo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>

	<%@include file="template/menu.jsp"%>

	<%
	List<Articulo> data = (List<Articulo>) request.getAttribute("articulos");
	%>

	<div class="container">
		<%@include file="template/message-box.jsp"%>

		<div class="row">
			<%
			for (Articulo item : data) {
			%>
			<div class="col-md-4">
				<div class="card">
					<div class="card-header"><%=item.getNombre()%></div>
					<div class="card-body">
						<img class="card-img-top img-responsive"
							src="<%=item.getImagen()%>" alt="Card image">
					</div>
					<div class="card-footer">
						<p><%=item.getDescripcion()%>
						</p>
						<a href="agregar-carrito?id=<%=item.getId()%>"
							class="btn btn-outline-primary">Agregar a Carrito</a> <a
							href="carrito" class="btn btn-danger">Comprar</a>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>

</body>
</html>