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
	   <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	       <div class="container">
	           <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	               <ul class="nav navbar-nav navbar-right">
						<li><a href="LogOut"><span class="glyphicon glyphicon-log-out"></span> Cerrar sesión</a></li>
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
                        <li role="presentation"><a href="ListaVenta">Lista de Libros mas vendidos</a></li>
                        <li role="presentation"><a href="ListaEntregas">Lista de entregas pendientes</a></li>
                    </ul>
                </div>

                <div class="col-md-7 col-md-offset-1" style="margin:0px 0px 0px 0px; width:75%;">
                <%
                	if(request.getAttribute("sinEnvios") != null && (boolean)request.getAttribute("sinEnvios")){
                		%><p>Tiene que seleccionar un envio para registrarlo<% 
                	}
                
                	if(request.getAttribute("registrado") != null ){
                		if((boolean)request.getAttribute("registrado")){
                			%><p>Se ha registrado exitosamente todos los envios<% 
                		}
                		else
                		{
                			%><p>Hubo un error,y algunos o todos los envios no pudieron registrarse<% 
                		}
                	}
                %>
                    <h1>Entregas pendientes</h1>
                    <form action="RealizarEntrega" method="get">
                    	<table class="table table-responsive table-hover">
	                        <thead>
	                            <tr>
	                                <td align="center">Fecha venta</th>
	                                <td align="center">Nro. venta</th>
	                                <td align="center">Usuario</th>
	                                <td align="center">Total</th>
	                                <td align="center">Enviar?</th>
	                            </tr>
	                        </thead>
	                        <%
	                        	if(request.getAttribute("listaEntregas") != null){
	                        		int i = 1;
	                        		ArrayList<Venta> ventas = (ArrayList<Venta>)request.getAttribute("listaEntregas");
	                        		for(Venta ven : (ArrayList<Venta>)request.getAttribute("listaEntregas")){
	                        			%>
	                        			<tbody>
		                        			<tr class="clickable" data-toggle="collapse" data-target="#group-of-rows-<%=i%>">
			                                    <td align="center"><%=ven.getFecha() %></td>
			                                    <td align="center"><%=ven.getIdVenta() %></td>
			                                    <td align="center"><%=ven.getUsuario().getUsuario() %></td>
			                                    <td align="center"><%=ven.getImporte() %></td>
			                                    <td align="center"><input type="checkbox" value="true" id="envio<%=ven.getEntrega().getIdEntrega()%>" name="envio<%=ven.getEntrega().getIdEntrega()%>"/></td>
			                                    <td></td>
			                                </tr>
	                               		</tbody>
		                        		<%
	                        			i++;
	                        		}
	                        	}
	                        %>
	                    </table>
                        <input type="submit" class="btn btn-primary pull-right" name="submit" value="Enviar entregas">
                    </form>
                </div>
            </div>
        </div>
        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>