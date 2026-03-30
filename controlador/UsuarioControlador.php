<?php
session_start();

if (!isset($_SESSION['usuario_id'])) {
    header("Location: ../index.php");
    exit();
}

$data = ["id" => $_SESSION['usuario_id']];

$ch = curl_init("http://localhost/api/API.php?entity=usuario&action=delete");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE");
curl_setopt($ch, CURLOPT_HTTPHEADER, ['Content-Type: application/json']);
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
curl_exec($ch);
curl_close($ch);

session_destroy();
header("Location: ../index.php");
exit();