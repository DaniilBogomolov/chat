<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Зарегистрироваться</title>
</head>
<body>
<#if error??>
    <h1 style="color: red">${error}</h1>
</#if>
<form action="/signUp" method="post">
    <input name="name" type="text" placeholder="Ваше имя" required><br>
    <input name="password" type="password" placeholder="Пароль" required><br>
    <button type="submit">Зарегистрироваться</button>
</form>
</body>
</html>