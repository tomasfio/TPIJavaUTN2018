<%@page import="Main.Entidades.*" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="es">
	<head>
		<style type="text/css">
		    	<%@include file="css/styles-css/custom-index.css" %>
		    	<%@include file="css/shop-homepage.css"%>
		    	<%@include file="css/edit-style.css"%>
	    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	</head>
    <body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Navegación</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="Index">Menu principal</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="Contacto">Contacto</a>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
					<%
						if(request.getSession().getAttribute("user") != null){
							Usuario usu = (Usuario)request.getSession().getAttribute("user");
							%> 
                    			<li><a href="CarritoCompra"><span class="glyphicon glyphicon-shopping-cart"></span> Ir al carro</a></li>                 			
								<li><a href="LogOut"><span class="glyphicon glyphicon-log-out"></span> Cerrar sesión</a></li>
							<%
						}
						else
						{
							%>
								<li><a href="registro-login.jsp">Registrarse</a></li>
	                            <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Iniciar sesión</a></li>
							<%
						}
					%>
                </ul>
            </div>
        </div>
    </nav>
    <!-- nav -->

    <!-- Page Content -->
    <div class="container">

        <div class="row">
            <div class="col-md-3">
            	<p class="lead">Buscar libro</p>
            	<div class="panel-group">
       				<form action="BuscarLibro" method="post">
               			<input type="text" class="form-control" name="busca" id="busca" placeholder="Buscar..." />
               		</form>
           		</div>
            	
                <p class="lead">Categorias</p>

                <div class="panel-group">
                  <div class="panel panel-default">
                     <ul class="list-group">
	                     <%
	                     	if(request.getAttribute("listaCategoria") != null){
	                     		ArrayList<Categoria> categorias = (ArrayList<Categoria>)request.getAttribute("listaCategoria");
	                     		for(Categoria cat : categorias){
	                     			%>
	                     			<div class="panel-heading">
	                     				<h4 class="panel-title"><a href="Index?idCat=<%=cat.getIdCategoria() %>" ><%=cat.getNombre() %></a></h4>
                     				</div>
	                     			<%
	                     		}
	                     	}
	                     %>
                     </ul>
                   </div>
                 </div>
          </div>

            <div class="col-md-9">
            
            <%
             if(request.getAttribute("falloEnvio") != null){
             	if((Boolean)request.getAttribute("falloEnvio") == true){
             		%>
             		<p>Hubo un problema en el envio de mail, intentelo nuevamente en unos minutos.</p>
             		<%
             	}
             }
             %>
             <%
             if(request.getAttribute("envioExitoso") != null){
             	if((Boolean)request.getAttribute("envioExitoso") == true){
             		%>
             		<p>Se ha enviado el mail exitosamente</p>
             		<%
             	}
             }
             %>
                <form action="EnviarMail" method="post">
                    <div class="row carousel-holder">
                        <div class="col-md-12">
                            <label for="email">Email del usuario: </label>
                            <%
                                if(request.getAttribute("email")!= null){
                                    %>
                                    <input class="form-control" type="email" name="email" id="email" value="<%=request.getAttribute("email") %>" readonly="readonly">
                                    <% 
                                }
                                else{
                                    %>
                                    <input class="form-control" type="email" name="email" id="email" required>
                                    <%
                                }
                            %>
                            <br>
                            <label for="asunto">Asunto: </label>
                            <input class="form-control" type="text" name="asunto" id="asunto" required>
                            <br>
                            <label for="texto">Texto: </label>
                            <textarea class="form-control" name="texto" id="texto" cols="50" rows="10" required></textarea>
                            <br>
                            <button type="submit" class="form-control">Enviar mail</button>
                            <%
                            if(request.getAttribute("faltaDatos") != null){
                            	if((Boolean)request.getAttribute("faltaDatos") == true){
                            		%>
                            		<p>Los campos email,asunto y texto no pueden estar vacios</p>
                            		<%
                            	}
                            }
                            %>
                        </div>
                    </div>
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