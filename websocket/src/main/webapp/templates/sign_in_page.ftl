<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<#if error??>
    <h1 style="color: red">${error}</h1>
</#if>
<form action="/signIn" method="post">
    <input name="name" type="text" placeholder="Ваше имя" required>
    <input name="password" type="password" placeholder="Ваш пароль" required>
    <button type="submit">Войти</button>
</form>
</body>
</html>