<%@page import="Main.Negocio.UsuarioLogic"%>
<%@page import="Main.Entidades.Usuario"%>
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
                    <p class="lead">Bienvenido</p>
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
                    	if(request.getAttribute("usuario") != null)
                    	{
                    %>
                    <form class="form-group" action="BajaModifUsuario" method="GET">
                    <%
                    		Usuario usu = (Usuario)request.getAttribute("usuario");  
                    		if(request.getAttribute("accion") == "update")
                    		{
                     %>
                        <h1>Modificacion de usuario</h1>
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
                                <input type="number" class="form-control" name="id_modificar" id="id_modificar" value="<%=usu.getIdUsuario() %>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="nombre">Nombre:</label>
                                <input type="text" class="form-control" name="nombre_modificar" value="<%=usu.getNombre() %>" required>
                            </div>
                            <div class="form-group">
                                <label for="apellido">Apellido:</label>
                                <input type="text" class="form-control" name="apellido_modificar" value="<%=usu.getApellido() %>" required>
                            </div>
                            <div class="form-group">
                                <label for="email">E-Mail:</label>
                                <input type="email"  class="form-control" name="email_modificar" value="<%=usu.getEmail() %>" required>
                            </div>
                            <div class="form-group">
                                <label for="usuario">Usuario:</label>
                                <input type="text"  class="form-control" name="usuario_modificar" value="<%=usu.getUsuario() %>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-warning pull-right" name="btnUpdate" value="Modificar usuario">
                            </div>
                    <% } else if(request.getAttribute("accion") == "delete"){ %>
	                        <h1>Baja de un usuario</h1>
	                        <hr>
                            <div class="form-group">
                                <label for="id">ID seleccionado:</label>
                                <input type="number" class="form-control" name="id_baja" id="id_baja" value="<%=usu.getIdUsuario() %>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="nombre">Nombre:</label>
                                <input type="text" class="form-control" id="nombre_baja" value="<%=usu.getNombre() %>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="apellido">Apellido:</label>
                                <input type="text" class="form-control" id="apellido_baja" value="<%=usu.getApellido() %>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="email">E-Mail:</label>
                                <input type="email" class="form-control" id="email_baja" value="<%=usu.getEmail() %>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="usuario">Usuario:</label>
                                <input type="text" class="form-control" id="usuario_baja" value="<%=usu.getUsuario() %>" readonly="readonly">
                            </div>
	                        <div class="form-group">
	                                <input type="submit" class="btn btn-warning pull-right" name="btnDelete" value="Eliminar usuario">
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
                        <h1>Gestión de un producto</h1>
                        <hr>
                        <div class="alert alert-danger">No existe un usuario con el ID ingresado, o ha ocurrido un error en la transacción.</div>
                        <a class="btn btn-primary" href="" role="button">Volver al listado</a>
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
