<?php
try {
    $pdo = new PDO(
        'mysql:dbname=FDBASE;host=localhost;charset=utf8mb4',
        'fdadmin',
        'fdadminqAz*01',
        [
            PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
            PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
        ]
        );
    $stmt = $pdo->query('SELECT FM_Title FROM FM_Base');
    $row = $stmt->fetch();
    $title = $row['FM_Title'];
} catch (PDOException  $e) {
    header('Content-Type: text/plain; charset=UTF-8', true, 500);
    exit($e->getMessage()); 
}

/*$title='秀さんの情報工房';*/

require 'header.php';

?>