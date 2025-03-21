<?php
require 'db.php';

header('Content-Type: application/json');

$stmt = $pdo->prepare("
    SELECT ingatlanok.id, kategoriak.nev AS kategoria, ingatlanok.leiras, 
           ingatlanok.hirdetesDatuma, ingatlanok.tehermentes, 
           ingatlanok.ar, ingatlanok.kepUrl 
    FROM ingatlanok 
    INNER JOIN kategoriak ON ingatlanok.kategoria = kategoriak.id
");
$stmt->execute();

$ingatlanok = $stmt->fetchAll(PDO::FETCH_ASSOC);

echo json_encode($ingatlanok);
?>
