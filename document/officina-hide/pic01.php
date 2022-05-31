<?php
try {
    $pdo = new PDO(
        'mysql:dbname=PICTURE;host=localhost;charset=utf8mb4',
        'fdadmin',
        'fdadminqAz*01',
        [
            PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
            PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
        ]
        );
    $stmt = $pdo->query('SELECT * FROM FD_FileData');
    $row = $stmt->fetch();
    $data = $row['FD_File_Data'];
} catch (PDOException  $e) {
    header('Content-Type: text/plain; charset=UTF-8', true, 500);
    exit($e->getMessage()); 
}

header('Content-type:  image/jpg');
echo $row['FD_File_Data'];

?>