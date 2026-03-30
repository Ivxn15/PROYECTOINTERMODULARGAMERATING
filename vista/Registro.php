<!doctype html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>GAMERATING</title>
<link rel="stylesheet" href="../web.css">
</head>
<body>
<?php
session_start();
if(isset($_SESSION['error'])){
    echo "<p style='color:red'>".$_SESSION['error']."</p>";
    unset($_SESSION['error']);
}
?>

<div id="fondo1" class="fondo"></div>
<div id="fondo2" class="fondo"></div>
<main class="contenedorInicio">
<form class="contenedorCaja" action="../controlador/registroControlador.php" method="POST">
    <img src="../Imagenes/GameratingLogo.png" alt="Logo Gamerating">
    <h1>Crear cuenta</h1>
    <label for="nombre">Usuario</label>
    <input type="text" name="nombre" id="nombre" required>
    <label for="email">Correo Electrónico</label>
    <input type="email" name="email" id="email" required>
    <label for="password">Contraseña</label>
    <input type="password" name="password" id="password" required>
    <label for="password2">Repetir contraseña</label>
    <input type="password" name="password2" id="password2" required>
    <button type="submit">Registrarse</button>
    <p class="registro">¿Ya tienes cuenta? <a href="../index.php">Inicia sesión</a></p>
</form>
</main>

<script>
var imagenes = [
  "../Imagenes/phblade.jpg",
  "../Imagenes/wow.jpg",
  "../Imagenes/fallout4.jpg",
  "../Imagenes/dispatch.jpg",
  "../Imagenes/gt6.jpg",
  "../Imagenes/oblivion.jpg",
  "../Imagenes/hogwart.jpeg",
  "../Imagenes/liesofp.jpg",
  "../Imagenes/balatro.jpg"
];

var i = 0;
var fondo1 = document.getElementById("fondo1");
var fondo2 = document.getElementById("fondo2");
fondo1.style.backgroundImage = "url(" + imagenes[0] + ")";
fondo1.style.opacity = 1;

setInterval(function() {
  i = (i + 1) % imagenes.length;
  if(fondo1.style.opacity == "1") {
    fondo2.style.backgroundImage = "url(" + imagenes[i] + ")";
    fondo2.style.opacity = 1;
    fondo1.style.opacity = 0;
  } else {
    fondo1.style.backgroundImage = "url(" + imagenes[i] + ")";
    fondo1.style.opacity = 1;
    fondo2.style.opacity = 0;
  }
}, 5000);
</script>
</body>
</html>