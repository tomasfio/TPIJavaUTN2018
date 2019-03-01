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
                    			<li><a href="#?user=<%=usu.getUsuario() %>"><span class="glyphicon glyphicon-shopping-cart"></span> Ver entregas</a></li>
								<li><a href="LogOut"><span class="glyphicon glyphicon-log-out"></span> Cerrar sesion</a></li>
							<%
						}
						else
						{
							%>
								<li><a href="registro-login.jsp">Registrarse</a></li>
	                            <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Iniciar sesion</a></li>
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

                <div class="row carousel-holder">

                    <div class="col-md-12">
                        <%
                    	if(request.getAttribute("libro") != null){
                    		Libro libro = (Libro)request.getAttribute("libro");
                   			%>
           				<h2><a href="#" ><%=libro.getTitulo() %></a></h2>
           				<img style="float:left; margin:10px;width : 181px;height : 278px;" alt="" src="./img/<%=libro.getImagen() %>">
           				
           				<p>Autor: <%=libro.getAutor() %></p>
           				<p>Descripcion: <%=libro.getDescripcion() %></p>
           				<p>Editorial: <%=libro.getEdicion() %></p>
           				<p>Categoria: <%=libro.getCategoria().getNombre() %>
           				<h4>Precio: <%=libro.getPrecio() %></h4>
           				</div>
           				<br>
           				<%
           					if(request.getSession().getAttribute("user") != null){
           						%>
            						<form class="form-inline" action="AgregarAlCarrito" method="post">
             							<div>
            								<input class="form-control" type="hidden" name="isbn" id="isbn" value=<%=libro.getISBN() %> />
           									<input class="form-control" type="number" name="cantidad" id="cantidad" placeholder="cantidad"/>
           									<button class="form-control" type="submit">Agregar al carrito</button>
             							</div>
            						</form>
           						<%
           					}
           				%> 
      				<div class="containrs">
       					<h3>Comentarios</h3>
	   					<div class="col-md-12">
							<table class="table table-bordered">
			          		<tbody> 
			           			<%
			           			for(Comentario com : libro.getComentario()){
			           				%>
						            <tr> 
						            	<th>
			              					<h4><%=com.getUsuario().getUsuario() %></h4>
			               				<p><%=com.getComentario() %></p>
			               				<p style="font: 10px Arial">Fecha del comentario: <%=com.getFechaHora() %></p>
			               				</th>
			              				</tr>
			           				<%
			           			}
			           			if(request.getSession().getAttribute("user") != null)
			           			{
			           			%>
            					</tbody>
            				</table>
       					</div>
         			</div>
           			<form class="form-inline" action="AgregarComentario" method="post">
 						<div>
							<input class="form-control" type="hidden" name="isbn" id="isbn" value=<%=libro.getISBN() %> />
							<textarea rows = "5" cols = "50" class="form-control" type="textarea" name="comentario" id="cometario" placeholder="Comentario..."></textarea>
							<br><br>
							<button class="form-control" type="submit">Agregar al comentario</button>
 						</div>
					</form>
	           			<%	
	           			}
	           		}
	                if(request.getAttribute("faltoComentario") != null){
	                	if((boolean)request.getAttribute("faltoComentario")){
	                		%>
	                		<p>Tiene que ingresar un comentario.</p>
	                		<%
	                	}
	                }
	                if(request.getAttribute("falloComentario") != null){
	                	if((boolean)request.getAttribute("falloComentario")){
	                		%>
	                		<p>No se pudo guardar el comentario por un error,trate nuevamente en unos minutos.</p>
	                		<%
	                	}
	                }
	                %>
      				</div>
				</div>
            </div>
        </div>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>