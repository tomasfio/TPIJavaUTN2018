<%@page import="Main.Negocio.CategoriaLogic"%>
<%@page import="Main.Entidades.Categoria"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="es">
<head>
        <title>Libreria - CP - ABM Categoria</title>
		<style type="text/css">
	    	<%@include file="css/styles-css/cp-styles.css"%>
	    	<%@include file="css/bootstrap.min.css"%>
	    	<%@include file="css/shop-homepage.css"%>
	    </style>

    </head>

    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Navegación</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Libreria</a><a href="#" class="navbar-brand">•</a><a class="navbar-brand" href="#">Control Panel</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                      <li><a><span class="glyphicon glyphicon-user"></span><b> </b></a></li>
                        <li><a href="php/logout.php"><span class="glyphicon glyphicon-log-in"></span> Cerrar sesión</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- nav -->

        <!-- body of the main page -->
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <p class="lead">Bienvenido <%//Usuario%></p>
                    <ul class="nav nav-pills nav-stacked">
                        <li role="presentation"><a href="ListaCategoria">Listado de Categorias</a>
                    	<li role="presentation"><a href="admin-alta-categoria.jsp">Nueva Categoria</a>
                        <li role="presentation"><a href="">Listado de Libros</a></li>
                        <li role="presentation"><a href="admin-alta-libros.jsp">Nuevo Libro</a></li>
                        <li role="presentation"><a href="ListaUsuario">Listado de usuarios</a></li>
                        <li role="presentation" class="active"><a href="admin-alta-user.jsp">Nuevo usuario</a></li>
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
                        <h1>Modificación de categoria</h1>
                        <hr>
                            <div class="form-group">
                                <label for="id">ID seleccionado:</label>
                                <input type="number" class="form-control" name="id_modificar" id="id_modificar" value="<%=cat.getIdCategoria() %>" disabled>
                            </div>
                            <div class="form-group">
                                <label for="nombre">Nombre:</label>
                                <input type="text" class="form-control" id="nombre_modificar" value="<%=cat.getNombre() %>" required>
                            </div>
                            <div class="form-group">
                                <label for="descripcion">Descripcion:</label>
                                <input type="text" class="form-control" id="descripcion_modificar" value="<%=cat.getDescipcion() %>" required>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-warning pull-right" name="btnModificar" value="Modificar">
                            </div>
                    <% } else if(request.getAttribute("accion") == "delete"){ %>
	                        <h1>Baja de categoria</h1>
	                        <hr>
                            <div class="form-group">
                                <label for="id">ID seleccionado:</label>
                                <input type="number" class="form-control" name="id_baja" id="id_baja" value="<%=cat.getIdCategoria() %>" disabled>
                            </div>
                            <div class="form-group">
                                <label for="nombre">Nombre:</label>
                                <input type="text" class="form-control" id="nombre_baja" value="<%=cat.getNombre() %>" disabled>
                            </div>
                            <div class="form-group">
                                <label for="descripcion">Descripcion:</label>
                                <input type="text" class="form-control" id="descripcion_baja" value="<%=cat.getDescipcion() %>" disabled>
                            </div>
	                        <div class="form-group">
                                <input type="submit" class="btn btn-warning pull-right" name="btnDelete" value="Eliminar">
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

        <!-- end body of main page -->

        <!-- Footer -->
        <hr>
        <footer>
            <div class="row" style="text-align:center">
                <div class="col-lg-12">
                    <p>Copyright &copy; Tienda 2018 - All rights reserved.</p>
                </div>
            </div>
        </footer>
        <!-- end footer -->

        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>