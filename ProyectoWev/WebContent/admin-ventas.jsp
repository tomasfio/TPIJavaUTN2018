<%@page import="Main.Entidades.Venta" %>
<%@page import="Main.Entidades.DetalleVenta" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <style type="text/css">
	    	<%@include file="css/styles-css/cp-styles.css"%>
	    	<%@include file="css/shop-homepage.css"%>
	    	<%@include file="../../css/pagination.css"%>
        </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
	   <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	       <div class="container">
	           <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	               <ul class="nav navbar-nav navbar-right">
						<li><a href="LogOut"><span class="glyphicon glyphicon-log-out"></span> Cerrar sesi�n</a></li>
	               </ul>
	           </div>
	       </div>
	   </nav>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <p class="lead">Bienvenido </p>
                    <ul class="nav nav-pills nav-stacked">
                        <li role="presentation"><a href="ListaCategorias">Listado de Categorias</a>
                        <li role="presentation"><a href="ListaLibros">Listado de Libros</a></li>
                        <li role="presentation"><a href="ListaUsuario">Listado de Usuarios</a></li>
                        <li role="presentation"><a href="ListaVenta">Lista de Ventas</a></li>
                        <li role="presentation"><a href="ListaMasVendidos">Lista de Libros mas vendidos</a></li>
                        <li role="presentation"><a href="ListaEntregas">Lista de entregas pendientes</a></li>
                    </ul>
                </div>

                <div class="col-md-7 col-md-offset-1" style="margin:0px 0px 0px 0px; width:75%;">
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
		                                    <td align="center"><%=ven.getFecha() %></td>
		                                    <td align="center"><%=ven.getUsuario().getUsuario() %></td>
		                                    <td align="center"><%=ven.getImporte() %></td>
		                                    <td></td>
		                                </tr>
                               		</tbody>
                       				<tbody id="group-of-rows-<%=i%>" class="collapse">
	                       				<tr>
		                                    <td align="center"><b>Titulo</b></td>
		                                    <td align="center"><b>Precio libro</b></td>
		                                    <td align="center"><b>Cantidad</b></td>
		                                    <td align="center"><b>Subtotal</b></td>
		                                </tr>
                        			<%
                        			for(DetalleVenta det : ven.getDetallesVentas()){
                        				%>
		                                <tr>
		                                    <td align="center"><i><%=det.getLibro().getTitulo() %></i></td>
		                                    <td align="center"><i><%=det.getSubTotal()/det.getCantidad() %></i></td>
		                                    <td align="center"><i><%=det.getCantidad() %></i></td>
		                                    <td align="center"><i><%=det.getSubTotal() %></i></td>
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
