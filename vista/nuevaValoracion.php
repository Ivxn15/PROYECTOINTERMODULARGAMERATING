<?php
session_start();
require_once("../controlador/apiHelper.php");

if(!isset($_SESSION['usuario_id'])){
    header("Location: ../index.php"); exit();
}

$usuario_nombre = $_SESSION['usuario_nombre'] ?? 'Usuario';
?>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Añadir Valoración - Gamerating</title>
<link rel="stylesheet" href="../web.css">
</head>
<body>
<header>
    <h1>GAMERATING⭐</h1>
    <nav>
        <a href="Principal.php">Volver</a>
    </nav>
</header>

<div id="fondo1" class="fondo"></div>
<div id="fondo2" class="fondo"></div>

<main class="contenedorInicio">
    <form class="contenedorCaja" action="../controlador/agregarValoracion.php" method="POST">
        <h2>Añadir Valoración</h2>
        <label for="juego">Nombre del juego</label>
        <input type="text" name="juego" id="juego" required>

        <label for="comentario">Comentario</label>
        <textarea name="comentario" id="comentario" required></textarea>

        <label for="puntuacion">Puntuación (0-10)</label>
        <input type="number" name="puntuacion" id="puntuacion" min="0" max="10" step="1" required>

        <button type="submit">Añadir valoración</button>
    </form>
</main>
<footer>Gamerating 2026</footer>
<script>

var imagenes = ["../Imagenes/alien.jpg","../Imagenes/wukong.jpg","../Imagenes/sonic.jpeg","../Imagenes/bo6.jpg"];
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

