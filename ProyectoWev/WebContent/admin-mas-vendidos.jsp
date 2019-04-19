<%@page import="Main.Entidades.Libro" %>
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
                        <li role="presentation"><a href="ListaMasVendidos">Lista de Libros mas vendidos</a></li>
                        <li role="presentation"><a href="ListaEntregas">Lista de entregas pendientes</a></li>
                    </ul>
                </div>

                <div class="col-md-7 col-md-offset-1" style="margin:0px 0px 0px 0px; width:75%;">
                    <h1>Libros mas vendidos</h1>
                    <table class="table table-responsive table-hover">
                        <thead>
                            <tr>
                                <td align="center"><strong>ISBN</strong></td>
                                <td align="center"><strong>Titulo</strong></th>
                                <td align="center"><strong>Autor</strong></td>
                                <td align="center"><strong>Cantidad</strong></td>
                            </tr>
                        </thead>
                        <%
                        	if(request.getAttribute("listaLibros") != null){
                        		ArrayList<Libro> libros = (ArrayList<Libro>)request.getAttribute("listaLibros");
                        		String[] cantidad = (String[])request.getAttribute("ventas");
                        		int i = 0;
                        		for(Libro lib : libros){
                        			%>
                        			<tbody>
	                        			<tr class="clickable" data-toggle="collapse" data-target="#group-of-rows-<%=i%>">
		                                    <td align="center"><%=lib.getISBN() %></td>
		                                    <td align="center"><%=lib.getTitulo() %></td>
		                                    <td align="center"><%=lib.getAutor() %></td>
		                                    <td align="center"><%=cantidad[i] %></td>
		                                    <td></td>
		                                </tr>
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
