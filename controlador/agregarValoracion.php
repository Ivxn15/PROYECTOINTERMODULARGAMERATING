<?php
session_start();
require_once("../controlador/apiHelper.php");

if(!isset($_SESSION['usuario_id'])){
    header("Location: ../index.php"); 
    exit();
}

$juego = $_POST['juego'] ?? '';
$puntuacion = (int)($_POST['puntuacion'] ?? 0);
$comentario = $_POST['comentario'] ?? '';
$fecha = date("Y-m-d H:i:s");


if(empty($juego) || $puntuacion < 0 || $puntuacion > 10){
    $_SESSION['error'] = "El juego y la puntuación son obligatorios y la puntuación debe estar entre 0 y 10.";
    header("Location: ../vista/nuevaValoracion.php"); 
    exit();
}


$datos = [
    "usuario_id" => $_SESSION['usuario_id'],
    "juego" => $juego,
    "puntuacion" => $puntuacion,
    "comentario" => $comentario,
    "fecha" => $fecha
];


$respuesta = llamarAPI("http://localhost:8080/tema5maven/rest/usuarios/resenas", "POST", $datos);


if($respuesta){
    $_SESSION['success'] = "";
} else {
    $_SESSION['error'] = "";
}

header("Location: ../vista/Principal.php");
exit();
