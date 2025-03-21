<?php
require 'db.php';

header('Content-Type: application/json');

$data = json_decode(file_get_contents('php://input'), true);

if (!isset($data['kategoria']) || !isset($data['leiras']) || !isset($data['tehermentes']) || !isset($data['ar']) || !isset($data['kepUrl'])) {
    http_response_code(400);
    echo json_encode(['message' => 'Hiányos adatok.']);
    exit;
}

$stmt = $pdo->prepare("
    INSERT INTO ingatlanok (kategoria, leiras, hirdetesDatuma, tehermentes, ar, kepUrl) 
    VALUES (:kategoria, :leiras, :hirdetesDatuma, :tehermentes, :ar, :kepUrl)
");

$hirdetesDatuma = isset($data['hirdetesDatuma']) ? $data['hirdetesDatuma'] : date('Y-m-d');

$stmt->execute([
    ':kategoria' => $data['kategoria'],
    ':leiras' => $data['leiras'],
    ':hirdetesDatuma' => $hirdetesDatuma,
    ':tehermentes' => $data['tehermentes'],
    ':ar' => $data['ar'],
    ':kepUrl' => $data['kepUrl']
]);

http_response_code(201);
echo json_encode(['id' => $pdo->lastInsertId()]);
?>
