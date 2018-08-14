<!DOCTYPE html>
<html>
<head>
    <title>Formulario</title>
    <meta charset="utf-8">
    <link type="text/css" href="./../css/style.css" rel="stylesheet" />
</head>
 
<body>
    <div id="envoltura">
        <div id="contenedor">
 
            <div id="cabecera">
                <img src="./../css/images/logo.gif" >
            </div>
 
            <div id="cuerpo">
 
                <form id="form-login" action="#" method="post" >
                    <p><label for="nombre">Nombre:</label></p>
                        <p><input name="nombre" type="text" id="nombre" class="nombre" placeholder="Pon tu nombre"></p>
 
                    <p><label for="apellidos">Apellidos:</label></p>
                        <p><input name="apellidos" type="text" id="apellidos" class="apellidos" placeholder="Pon tus apellidos" ></p>
 
                    <p><label for="correo">Correo:</label></p>
                        <p><input name="correo" type="text" id="correo" class="correo" placeholder="Pon tu mail" ></p>
 
                    <p><label for="pass">Password:</label></p>
                        <p><input name="pass" type="password" id="pass" class="pass" placeholder="Pon tu contraseña" ></p>
 
                    <p><label for="repass">Repetir Password:</label></p>
                        <p><input name="repass" type="password" id="repass" class="repass" placeholder="Repite contraseña" ></p>
 
                    <p id="bot"><input name="submit" type="submit" id="boton" value="Registrar" class="boton">
                    <input name="submit" type="submit" id="boton" value="Cancelar" class="boton"></p>
                </form>
            </div>
 
            <div id="pie">Sistema de Login Y Registro</div>
        </div><!-- fin contenedor -->
 
    </div>
 
</body>
 
</html>