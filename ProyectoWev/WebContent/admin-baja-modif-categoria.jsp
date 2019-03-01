<%@page import="Main.Negocio.CategoriaLogic"%>
<%@page import="Main.Entidades.Categoria"%>
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
						<li><a href="LogOut"><span class="glyphicon glyphicon-log-out"></span> Cerrar sesi�n</a></li>
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
                    	if(request.getAttribute("categoria") != null)
                    	{
                    %>
                    <form class="form-group" action="BajaModifCategoria" method="GET">
                    <%
                    		Categoria cat = (Categoria)request.getAttribute("categoria");  
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
                                <label for="id">ID seleccionado:</label>
                                <input type="number" class="form-control" name="id_modificar" id="id_modificar" value="<%=cat.getIdCategoria() %>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="nombre">Nombre:</label>
                                <input type="text" class="form-control" name="nombre_modificar" value="<%=cat.getNombre() %>" required>
                            </div>
                            <div class="form-group">
                                <label for="descripcion">Descripcion:</label>
                                <input type="text" class="form-control" name="descripcion_modificar" value="<%=cat.getDescipcion() %>" required>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-warning" name="btnUpdate" value="Modificar categoria">
                            </div>
                    <% } else if(request.getAttribute("accion") == "delete"){ %>
	                        <h1>Baja de categoria</h1>
	                        <hr>
                            <div class="form-group">
                                <label for="id">ID seleccionado:</label>
                                <input type="number" class="form-control" name="id_baja" id="id_baja" value="<%=cat.getIdCategoria() %>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="nombre">Nombre:</label>
                                <input type="text" class="form-control" id="nombre_baja" value="<%=cat.getNombre() %>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="descripcion">Descripcion:</label>
                                <input type="text" class="form-control" id="descripcion_baja" value="<%=cat.getDescipcion() %>" readonly="readonly">
                            </div>
	                        <div class="form-group">
                              <input type="submit" class="btn btn-danger" name="btnDelete" value="Eliminar categoria">
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
                        <h1>Gestión de categoria</h1>
                        <hr>
                        <div class="alert alert-danger">No existe un categoria con el ID ingresado, o ha ocurrido un error en la transacción.</div>
                        <a class="btn btn-primary" href="ListaCategoria" role="button">Volver al listado</a>
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