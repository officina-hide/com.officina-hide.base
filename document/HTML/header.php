<?php 
try{
    $dsn = 'mysql:dbname=MYPAGE;host=localhost;charset=utf8mb4';
    $username = 'fdadmin';
    $passworf = 'fdadminqAz*01';
    $options = '';
//    $DB = new PDO($dsn, $username, $password, $options);
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
<style type="text/css">
    <!--
    .title {font-size: 24px;}
    -->
</style>
</head><body>
<div id="header">
	<table width="100%">
		<tr>	<td width="30%" class=title><?php echo $title; ?></td>
				<td width="70%"><a href='main.php'>main</a></td>
		</tr>
	</table>
	<hr size="5">
</div>
<div id="content">
	<div id="main">
		<h2>メインページ</h2>
	</div>
	<div id="menu">
		<ul>
			<li>TEST</li>
			<li><a href='JavaFx.php'>JavaFx</a></li>
	</div>
</div>
