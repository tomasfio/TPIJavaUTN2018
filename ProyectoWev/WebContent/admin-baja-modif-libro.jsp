<%@page import="Main.Entidades.Libro"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<style type="text/css">
    	<%@include file="css/styles-css/cp-styles.css"%>
    	<%@include file="css/shop-homepage.css"%>
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
                    <p class="lead">Bienvenido <%//Usuario%></p>
                    <ul class="nav nav-pills nav-stacked">
                        <li role="presentation"><a href="ListaCategorias">Listado de Categorias</a>
                        <li role="presentation"><a href="ListaLibros">Listado de Libros</a></li>
                        <li role="presentation"><a href="ListaUsuario">Listado de Usuarios</a></li>
                        <li role="presentation"><a href="ListaVenta">Lista de Ventas</a></li>
                        <li role="presentation"><a href="ListaMasVendidos">Lista de Libros mas vendidos</a></li>
                        <li role="presentation"><a href="ListaEntregas">Lista de entregas pendientes</a></li>
                    </ul>
                </div>

                <div class="col-md-7 col-md-offset-1">
                    <% 
                    	if(request.getAttribute("libro") != null)
                    	{
                    %>
                    <form class="form-group" action="BajaModifLibro" enctype="multipart/form-data" method="GET">
                    <%
                    		Libro lib = (Libro)request.getAttribute("libro");  
                    		if(request.getAttribute("accion") == "update")
                    		{
                     %>
                        <h1>Modificacion de categoria</h1>
                        <%
		                	if(request.getAttribute("error") != null)
		                	{
		                		%>
		                		<p><%=request.getAttribute("error") %></p>
		                		<%
		               		} 
	               		%>
                        <hr>
                            <div class="form-group">
                                <label for="isbn">ISBN seleccionado:</label>
                                <input type="text" class="form-control" name="isbn_modificar" id="isbn_modificar" value="<%=lib.getISBN() %>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="nombre">Titulo:</label>
                                <input type="text" class="form-control" name="titulo_modificar" value="<%=lib.getTitulo() %>" required>
                            </div>
                            <div class="form-group">
                                <label for="descripcion">Descripcion:</label>
                                <textarea cols="88" rows="5" name="descripcion_modificar" class="from-control" value="" required><%=lib.getDescripcion() %></textarea>
                            </div>
                            <div class="form-group">
                                <label for="autor">Autor:</label>
                                <input type="text" class="form-control" name="autor_modificar" value="<%=lib.getAutor() %>" required>
                            </div>
                            <div class="form-group">
                                <label for="fecha">Fecha publicacion:</label>
                                <input type="date" class="form-control" name="fecha_modificar" value="<%=lib.getFecha().toString() %>" required>
                            </div>
                            <div class="form-group">
                                <label for="edicion">Edicion:</label>
                                <input type="text" class="form-control" name="edicion_modificar" value="<%=lib.getEdicion() %>" required>
                            </div>
                            <div class="form-group">
                                <label for="precio">Precio:</label>
                                <input type="number" step="any" min="0" class="form-control" name="precio_modificar" value="<%=lib.getPrecio() %>" required>
                            </div>
                            <div class="form-group">
                        		<input type="file" class="form-control" name="imagen" value="<%=lib.getImagen() %>"/>
                        	</div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-warning" name="btnUpdate" value="Modificar libro">
                            </div>
                    <% } else if(request.getAttribute("accion") == "delete"){ %>
	                        <h1>Baja de categoria</h1>
	                        <hr>
                            <div class="form-group">
                                <label for="isbn">ISBN seleccionado:</label>
                                <input type="text" class="form-control" name="isbn_baja" id="isbn_baja" value="<%=lib.getISBN() %>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="nombre">Titulo:</label>
                                <input type="text" class="form-control" name="titulo_baja" value="<%=lib.getTitulo() %>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="descripcion">Descripcion:</label>
                                <textarea cols="88" rows="5" name="descripcion_baja" class="from-control" readonly="readonly"><%=lib.getDescripcion() %></textarea>
                            </div>
                            <div class="form-group">
                                <label for="autor">Autor:</label>
                                <input type="text" class="form-control" name="autor_baja" value="<%=lib.getAutor() %>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="fecha">Fecha publicacion:</label>
                                <input type="date" class="form-control" name="fecha_baja" value="<%=lib.getFecha().toString() %>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="edicion">Edicion:</label>
                                <input type="text" class="form-control" name="edicion_baja" value="<%=lib.getEdicion() %>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="precio">Precio:</label>
                                <input type="number" step="any" min="0" class="form-control" name="precio_baja" value="<%=lib.getPrecio() %>" readonly="readonly">
                            </div>
	                        <div class="form-group">
                              <input type="submit" class="btn btn-danger" name="btnDelete" value="Eliminar libro">
	                        </div>
                   	 	<% 
	                    	} 
						%>
                            </form>
                        <%
	                    } 
                   		else 
                   		{ 
                   		%>
                        <h1>Gestion de libros</h1>
                        <hr>
                        <div class="alert alert-danger">No existe un libro con el ISBN ingresado, o ha ocurrido un error en la transaccion.</div>
                        <a class="btn btn-primary" href="ListaLibros" role="button">Volver al listado</a>
                    	<% 
                   		} 
                    	%>
                </div>
        	</div>
        </div>
        
        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>