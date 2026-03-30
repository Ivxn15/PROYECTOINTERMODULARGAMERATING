<?php
session_start();
require_once("apiHelper.php");

if(!isset($_SESSION['usuario_id'])){
    header("Location: ../index.php"); exit();
}

$id = (int)($_POST['id'] ?? 0);
$usuarioId = $_SESSION['usuario_id'];

$respuesta = llamarAPI("http://localhost:8080/tema5maven/rest/usuarios/resenas/$id?usuarioId=$usuarioId", "DELETE");

if($respuesta){
    $_SESSION['success'] = "";
}else{
    $_SESSION['error'] = "";
}

header("Location: ../vista/Principal.php");
exit();
