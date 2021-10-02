<?php
try {
    $pdo = new PDO(
        'mysql:dbname=MYPAGE;host=localhost;charset=utf8mb4',
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
?>

<DOCTYPE html><html lang="ja"><head>
<meta content='width=device-width,initial-scale=1.0, 
minimum-scale=1.0,maximum-scale=1.0' name='viewport'/>
<link type='text/css' rel='stylesheet' href='style.css'>
<title><?php echo $title; ?></title>
<body>
</body>
<div id="header">
	<h1><?php echo $title; ?></h1>
	<hr size="5">
</div>
<div id="content">
	<div id="main">
		<h2>TEST</h2>
	</div>
	<div id="menu">
		<ul>
			<li>JavaFxで画面を作る。</li>
	</div>
</div>
</html>