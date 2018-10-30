<!DOCTYPE html>
<html lang="es">
<head>
        <meta charset="utf-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="Entornos" content="StoreWare">

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
                    <a class="navbar-brand" href="">StoreWare</a><a href="#" class="navbar-brand">•</a><a class="navbar-brand" href="">Control Panel</a>
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
                        <li role="presentation"><a href="">Listado de usuarios</a></li>
                        <li role="presentation" class="active"><a href="admin-alta-user.jsp">Nuevo usuario</a></li>
                    </ul>
                </div>

                <div class="col-md-7 col-md-offset-1">
                    <h1>Listado de usuarios</h1>

                    <form class="form-group" action="admin-cp-user.php" method="GET">
                        <div class="input-group">
                            <select name="cliente" class="form-control">
                                <option>Administradores</option>
                                <option>Clientes</option>
                            </select>
                            <span class="input-group-btn">
                                <input class="btn btn-primary" type="submit" name="btnListar" value="Listar">
                            </span>
                        </div>
                    </form><hr>

                    <?php
                        include("./php/conexion.inc");

                        if (isset($_GET['cliente'])) {

                            $selectedcat = $_GET['cliente'];

                            if ($selectedcat == "Administradores")
                            {
                                $subcat=1;
                            }

                            if ($selectedcat == "Clientes")
                            {
                                $subcat=0;
                            }

                            $TAMANO_PAGINA = 3;
                            if (isset($_GET["pagina"])) {
                              $pagina = $_GET["pagina"];
                            }
                            else {
                              $pagina = 1;
                            }
                            //Comprueba si está seteado el GET de HTTP
                            if (!$pagina) {
                              $inicio = 0;
                              $pagina=1;
                            }
                            else {
                                $inicio = ($pagina - 1) * $TAMANO_PAGINA;
                            }
                            //miro a ver el número total de campos que hay en la tabla con esa búsqueda
                            //miro a ver el número total de campos que hay en la tabla con esa búsqueda
                            $sql = "SELECT * FROM cliente WHERE tipo_usu LIKE '$subcat%'";
                            $resultado = mysqli_query($con, $sql);
                            $total_registros = mysqli_num_rows($resultado);
                            //calculo el total de páginas
                            $total_paginas = ceil($total_registros / $TAMANO_PAGINA);

                            //pongo el número de registros total, el tamaño de página y la página que se muestra
                            /*echo "Número de registros encontrados: " . $total_registros . "<br>";
                            echo "Se muestran páginas de " . $TAMANO_PAGINA . " registros cada una<br>";
                            echo "Mostrando la página " . $pagina . " de " . $total_paginas . "<p>";*/

                            //construyo la sentencia SQL
                            $sql = "SELECT * FROM cliente WHERE tipo_usu LIKE '$subcat%' LIMIT " . $inicio . "," . $TAMANO_PAGINA;
                            $resultado = mysqli_query($con, $sql);
                            ?>

                            <form class="form-inline" action="admin-baja-modif-user.php" method="post">
                                <div class="form-group">
                                    <a class="btn btn-success" href="admin-alta-user.php" role="button">Nuevo usuario</a>
                                </div>
                                <div class="form-group pull-right">
                                    <input type="number" min='0' class="form-control" name="id_user" id="id_user" placeholder="Ingrese ID" required>
                                    <button type="submit" class="btn btn-warning" name="update" value="update">Modificar</button>
                                    <button type="submit" class="btn btn-danger" name="delete" value="delete">Eliminar</button>
                                </div>
                            </form>
                            <br>

                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <td><b>ID</b></td>
                                        <td><b>Usuario</b></td>
                                        <td><b>Nombre</b></td>
                                        <td><b>Apellido</b></td>
                                        <td><b>Direccion</b></td>
                                        <td><b>Telefono</b></td>
                                        <td><b>Email</b></td>
                                    </tr>
                                </thead>

                                <tbody>
                                    <?php
                                        while ($fila = mysqli_fetch_array($resultado))
                                        {
                                    ?>

                                    <tr>
                                        <td><?php echo ($fila['id_cliente']); ?></td>
                                        <td><?php echo ($fila['usuario']); ?></td>
                                        <td><?php echo ($fila['nombre']); ?></td>
                                        <td><?php echo ($fila['apellido']); ?></td>
                                        <td><?php echo ($fila['direccion']); ?></td>
                                        <td><?php echo ($fila['telefono']); ?></td>
                                        <td><?php echo ($fila['email']); ?></td>

                                    </tr>

                                    <?php
                                        }
                                        // Liberar conjunto de resultados
                                        mysqli_free_result($resultado);
                                        // Cerrar la conexion
                                        mysqli_close($con);
                                        //Crea un bucle donde $i es igual 1, y hasta que $i sea menor o igual a X, a sumar (1, 2, 3, etc.)
                                        //Nota: X = $total_paginas
                            }
                            ?>
                        </tbody>
                    </table>
                    <div class="center">
                      <ul class="pagination">
                    <?php
                    if (isset($total_paginas)) {
                      for ($i=1; $i<=$total_paginas; $i++) {
                        //En el bucle, muestra la paginación
                        if ($i == $pagina) {
                          echo "<li class="."active"."><a href='admin-cp-user.php?pagina=".$i."&cliente=".$selectedcat."'>".$i."</a></li> ";
                        }
                        else {
                          echo "<li><a href='admin-cp-user.php?pagina=".$i."&cliente=".$selectedcat."'>".$i."</a></li> ";
                        }
                      }
                    }
                     ?>
                      </ul>
                    </div>
                </div>
            </div>
        </div>

        <!-- end body of main page -->

        <!-- Footer -->
        <hr>
        <footer>
            <div class="row" style="text-align:center">
                <div class="col-lg-12">
                    <p>Copyright &copy; StoreWare 2017 - All rights reserved • Created by Andres, Mauricio, Julian and Tomas.</p>
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
