<%@page import="Main.Entidades.Venta" %>
<%@page import="Main.Entidades.DetalleVenta" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <style type="text/css">
	    	<%@include file="css/styles-css/cp-styles.css"%>
	    	<%@include file="css/bootstrap.min.css"%>
	    	<%@include file="css/shop-homepage.css"%>
	    	<%@include file="../../css/pagination.css"%>
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <p class="lead">Bienvenido </p>
                    <ul class="nav nav-pills nav-stacked">
                        <li role="presentation"><a href="ListaCategorias">Listado de Categorias</a>
                        <li role="presentation"><a href="ListaLibros">Listado de Libros</a></li>
                        <li role="presentation"><a href="ListaUsuario">Listado de Usuarios</a></li>
                        <li role="presentation"><a href="ListaVenta">Lista de ventas</a></li>
                    </ul>
                </div>

                <div class="col-md-7 col-md-offset-1">
                    <h1>Ultimas ventas</h1>
                    <table class="table table-responsive table-hover">
                        <thead>
                            <tr>
                                <th align="center">Fecha venta</th>
                                <th align="center">Usuario</th>
                                <th align="center">Total</th>
                            </tr>
                        </thead>
                        <%
                        	if(request.getAttribute("ventas") != null){
                        		int i = 1;
                        		ArrayList<Venta> ventas = (ArrayList<Venta>)request.getAttribute("ventas");
                        		for(Venta ven : (ArrayList<Venta>)request.getAttribute("ventas")){
                        			%>
                        			<tbody>
	                        			<tr class="clickable" data-toggle="collapse" data-target="#group-of-rows-<%=i%>">
		                                    <td><i class="fa fa-plus" aria-hidden="true"></i></td>
		                                    <td align="center"></td>
		                                    <td align="center"><%=ven.getUsuario().getUsuario() %></td>
		                                    <td align="center"><%=ven.getImporte() %></td>
		                                </tr>
                               		</tbody>
                        				<tbody id="group-of-rows-<%=i%>" class="collapse">
                        			<%
                        			for(DetalleVenta det : ven.getDetallesVentas()){
                        				%>
			                                <tr>
			                                    <td align="center"><%=det.getLibro().getTitulo() %></td>
			                                    <td align="center"><%=det.getSubTotal()/det.getCantidad() %></td>
			                                    <td align="center"><%=det.getCantidad() %></td>
			                                    <td align="center"><%=det.getSubTotal() %></td>
			                                </tr>
                        				<%
                        			}
                        			%>
		                        		</tbody>
	                        		<%
                        			i++;
                        		}
                        	}
                        %>
                    </table>
                </div>
            </div>
        </div>
        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
