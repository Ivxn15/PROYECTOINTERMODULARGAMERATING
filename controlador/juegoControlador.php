<?php
session_start();

if (!isset($_SESSION['usuario_id'])) {
    header("Location: ../index.php");
    exit();
}

$data = [
    "usuario_id" => $_SESSION['usuario_id'],
    "juego_id" => $_POST['id_juego']
];

$ch = curl_init("http://localhost/api/API.php?entity=juego&action=addToUser");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_HTTPHEADER, ['Content-Type: application/json']);
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
curl_exec($ch);
curl_close($ch);

header("Location: ../vista/juegos.php");
exit();
