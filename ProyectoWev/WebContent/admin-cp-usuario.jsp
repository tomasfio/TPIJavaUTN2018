<%@page import="Main.Negocio.UsuarioLogic"%>
<%@page import="Main.Entidades.Usuario" %>
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

                <div class="col-md-9">
                	<%
	                	if(request.getAttribute("error") != null)
	                	{
	                		%>
	                		<p><%=request.getAttribute("error") %></p>
	                		<%
	               		} 
               		%>
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
                                  <td></td>
                                  <td></td>
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
                                  <td><a type="button" class="btn btn-warning" href="UsuarioABM?id_user=<%=usu.getIdUsuario() %>&btnUpdate=update">Modificar</a></td>
                                  <td><a type="button" class="btn btn-danger" href="UsuarioABM?id_user=<%=usu.getIdUsuario() %>&btnDelete=delete">Eliminar</a></td>
                              </tr>
                              <%
                              	}
                              }
                              %>
                        </tbody>
                    </table>
            	</div>
            	<%
            		if(request.getAttribute("modificarUsuario") != null) {
            			if((boolean)request.getAttribute("modificarUsuario")){
            				%><p>El usuario ha sido modificado exitosamente</p><% 
            			}
            		}
           		%>
           		<%
           		if(request.getAttribute("bajaUsuario") != null) {
        			if((boolean)request.getAttribute("bajaUsuario")){
        				%><p>El usuario ha sido dado de baja exitosamente</p><% 
        			}
        		}
           		%>
            </div>
        </div>
        <hr>

        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
