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
<#if user?has_content>
    <a href="/room">Мои комнаты</a>
    <#else>
        <a style="text-decoration: none" href="/signUp">Зарегистрироваться</a>
        <a href="/signIn">Войти</a>
</#if>
</body>
</html>