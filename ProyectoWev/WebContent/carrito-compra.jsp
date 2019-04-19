<%@page import="Main.Entidades.*" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="es">
	<head>

			<script type="text/javascript">
			
				function cambioCheckBox(){
					var obj = document.getElementById("envio");
					var text = document.getElementById("direccion");
					if(obj.checked == true){
						text.type = "text";
						text.required = "true";
					}
					else{
						text.type = "hidden";
						text.required = "false";
					}
				}

				function modificarCantidad(isbn, fecha){
					let tr = document.getElementById('det' + isbn + fecha);
					let titulo = tr.getElementsByTagName('td')[0];
					let cantidad = tr.getElementsByTagName('td')[1];
					tr.innerHTML = '<td>' + titulo.innerHTML + '</td>' +
						'<td><input style="width: 70px" class="form-control" type="number" id="cantidad" value="' + cantidad.innerHTML + '" /></td>' +
						'<td><a id="ref" style="displat:none; visibility: hidden"></a></td>' +
						'<td><button type="submit" class="btn btn-primary" onClick="Modificar(\'' + isbn + '\',\'' + fecha +  '\')">Aceptar</button></td>	' +
						'<td><a type="button" class="btn btn-danger" href="CarritoCompra">Cancelar</a></td>';
				}
				
				function Modificar(isbn, fecha){
					console.log('Modificar');
					let tr = document.getElementById('det' + isbn + fecha);
					let cantidad = tr.getElementsByTagName('input')[0].value;
					let href = tr.getElementsByTagName('td')[2];
					href.innerHTML = '<a id="ref" style="displat:none; visibility: hidden" href="ModificarVenta?isbn='+ isbn +'&fecha=' + fecha + '&cantidad=' + cantidad +'"></a>';
					href.getElementsByTagName('a')[0].click();
				}

			</script>



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
                    <span class="sr-only">Navegaci�n</span>
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
								<li><a href="LogOut"><span class="glyphicon glyphicon-log-out"></span> Cerrar sesion</a></li>
							<%
						}
						else
						{
							%>
								<li><a href="registro-login.jsp">Registrarse</a></li>
	                            <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Iniciar sesi�n</a></li>
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
	                     			<div div class="panel-heading">
	                     				<h4 class="panel-title"><a href="Index?idCat=<%=cat.getIdCategoria() %>" ><%=cat.getNombre() %></a></h4>
                     				</div>
	                     			<%
	                     		}
	                     		
	                     	}else{
	                     		request.getRequestDispatcher("Index").forward(request, response);
	                     	}
	                     %>
                     </ul>
                   </div>
                 </div>
          </div>

            <div class="col-md-9">

                <div class="row carousel-holder">

                    <div class="col-md-12">
                        <h1 align="center">Carrito de compras</h1>
                        	<table class="table table-striped">
                        		<thead>
                        			<tr>
                        				<td><b>Titulo</b></td>
                        				<td><b>Cantidad</b></td>
                        				<td><b>Subtotal</b></td>
                        				<td><b></b></td>
                        				<td><b></b></td>
                       				</tr>
                   				</thead> 
                   				<tbody>
                   					<%
                   					if(request.getAttribute("listaDetalleVenta") != null)
                   					{

                   						ArrayList<DetalleVenta> detallesVentas = (ArrayList<DetalleVenta>)request.getAttribute("listaDetalleVenta");
                   						for(DetalleVenta detVenta : detallesVentas)
                   						{
                   							%>
      										<tr id="det<%=detVenta.getLibro().getISBN() %><%=detVenta.getFechaVenta() %>">
												<td id="titulo"><%=detVenta.getLibro().getTitulo() %></td>
												<td id="cantidad"><%=detVenta.getCantidad() %></td>
												<td id="subtotal"><%=detVenta.getCantidad()*detVenta.getLibro().getPrecio() %></td>
												<td><button type="button" class="btn btn-warning" name="btnModificar" value="modificar" onClick="modificarCantidad('<%=detVenta.getLibro().getISBN() %>', '<%=detVenta.getFechaVenta() %>')">Modificar</button></td>
												<td><button type="button" class="btn btn-danger" name="btnEliminar" value="eliminar" onClick="eliminarProducto('<%=detVenta.getLibro().getISBN() %>', '<%=detVenta.getFechaVenta() %>')">Eliminar</button></td>														      										
                							</tr>
                   							<%
                   						}
                   					}
                   					%>
                   				</tbody>
                   				<tfoot>
                   					<tr>
                   						<td>Total <%=request.getAttribute("importeTotal") !=null ? request.getAttribute("importeTotal") : 0 %></td>
                   					</tr>
                   				</tfoot>
                        	</table>
                        	<form action="RegistrarVenta" method="get" class="form-inline" enctype="multipart/form-data">
                       			<input class="form-control" type="checkbox" id="envio" name="envio" value="true" onClick="cambioCheckBox()"/>Envio a domicilio<br>
                       			<input class="form-control" type="hidden" name="direccion" id="direccion" placeholder="direccion"/><br><br>
                       			<button class="btn btn-warning" type="submit" name="btnRegistrar" value="registrar">Registrar venta</button>
                      			<div class="form-group pull-right">
                            		 <button type="submit" class="btn btn-danger" name="btnDelete" value="delete">Vaciar carrito</button>
                       	 		</div>
                        	</form>
                    </div>
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