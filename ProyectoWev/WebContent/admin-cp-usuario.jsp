<%@page import="Main.Negocio.UsuarioLogic"%>
<%@page import="Main.Entidades.Usuario" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="es">
<head>
        <meta charset="utf-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="Entornos" content="Libreria">

        <title>StoreWare - CP</title>

		<style type="text/css">
	    	<%@include file="css/styles-css/cp-styles.css"%>
	    	<%@include file="css/bootstrap.min.css"%>
	    	<%@include file="css/shop-homepage.css"%>
	    	<%@include file="../../css/pagination.css"%>
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
                    <a class="navbar-brand" href="">StoreWare</a><a href="#" class="navbar-brand"></a><a class="navbar-brand" href="">Control Panel</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                      <li><a><span class="glyphicon glyphicon-user"></span><b> <%//Usurio %></b></a></li>
                        <li><a href=""><span class="glyphicon glyphicon-log-in"></span> Cerrar sesión</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- nav -->

        <!-- body of the main page -->
        <div class="container">
        <div class="row">
                <div class="col-md-3">
                    <p class="lead">Bienvenido </p>
                    <ul class="nav nav-pills nav-stacked">
                        <li role="presentation"><a href="">Listado de Categorias</a>
                    	<li role="presentation"><a href="admin-alta-categoria.jsp">Nueva Categoria</a>
                        <li role="presentation"><a href="">Listado de Libros</a></li>
                        <li role="presentation"><a href="Categoria">Nuevo Libro</a></li>
                        <li role="presentation"><a href="ListaUsuario">Listado de usuarios</a></li>
                        <li role="presentation" class="active"><a href="admin-alta-user.jsp">Nuevo usuario</a></li>
                    </ul>
                </div>

                <div class="col-md-7 col-md-offset-1">
                    <h1>Listado de usuarios</h1>

                    <form class="form-group" action="ListaUsuario" method="GET">
                        <div class="input-group">
                            <select name="tipo_usuario" class="form-control">
                                <option value = "0">Administradores</option>
                                <option value = "1">Clientes</option>
                            </select>
                            <span class="input-group-btn">
                                <input class="btn btn-primary" type="submit" name="btnListar" value="Listar">
                            </span>
                        </div>
                    </form><hr>
                     <form class="form-inline" action="UsuarioABM" method="GET">
                          <div class="form-group">
                              <a class="btn btn-success" href="UsuarioABM" role="button">Nuevo usuario</a>
                          </div>
                          <div class="form-group pull-right">
                              <input type="number" min='0' class="form-control" name="id_user" id="id_user" placeholder="Ingrese ID" required>
                              <button type="submit" class="btn btn-warning" id="btnUpdate" name="btnUpdate" value="update">Modificar</button>
                              <button type="submit" class="btn btn-danger" id="btnUpdate" name="btnDelete" value="delete">Eliminar</button>
                          </div>
                      </form>
                      <%
	                      if(request.getAttribute("existeUsuario") != null){
	                      	if(!(boolean)request.getAttribute("existeUsuario")){
                      %><p>El id ingresado no pertenece a ningun usuario registrado en el sistema</p><%
                      		}
                      	}
                      %>
                      <br>

                      <table class="table table-striped">
                          <thead>
                              <tr>
                                  <td><b>ID</b></td>
                                  <td><b>Usuario</b></td>
                                  <td><b>Nombre</b></td>
                                  <td><b>Apellido</b></td>
                                  <td><b>Email</b></td>
                              </tr>
                          </thead>
                          <tbody>
                          <%
                            if(request.getAttribute("listaUsuarios") != null){
                            	ArrayList<Usuario> usuarios = (ArrayList<Usuario>)request.getAttribute("listaUsuarios");
                            	for(Usuario usu : usuarios){
                            %>
                              <tr>
                                  <td><%=Integer.toString(usu.getIdUsuario()) %></td>
                                  <td><%=usu.getUsuario().toString() %></td>
                                  <td><%=usu.getNombre().toString() %></td>
                                  <td><%=usu.getApellido().toString() %></td>
                                  <td><%=usu.getEmail().toString() %></td>
                              </tr>
                              <%
                              	}
                              }
                              %>
                        </tbody>
                    </table>
            	</div>
            </div>
        </div>
        <hr>
        <footer>
            <div class="row" style="text-align:center">
                <div class="col-lg-12">
                    <p></p>
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
