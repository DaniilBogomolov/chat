<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Комната</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</head>
<body>
<h1>Это комната ${room.name}</h1>
<ul id="messages" style="border: 1px gray solid; min-height: 20em; max-height: 20em; overflow: scroll; list-style-type: none;">
    <#list room.messages>
        <#items as message>
            <li>${message.senderName}: ${message.text}</li>
        </#items>
    </#list>
</ul>
<hr>
<form>
    <input id="text" type="text" placeholder="Ваше сообщение">
    <input type="button" value="Отправить" onclick="sendMessage()">
</form>
<script>
    const messages = document.getElementById("messages");
    const text = document.getElementById("text");
    const socket = new SockJS("/websocket");
    const stomp = Stomp.over(socket);

    stomp.connect({}, function () {
        stomp.subscribe("/topic/room/${room.generatedName}", function (data) {
            insertMessage(JSON.parse(data.body));
        });
    });

    function insertMessage(message) {
        $('#messages').append('<li>' + message.senderName + ': ' + message.text + '</li>');
    }

    function sendMessage() {

        const body = {
            "text": text.value,
            "sender": '${userCookie}'
        };

        text.value="";
        stomp.send("/app/room/${room.generatedName}", {}, JSON.stringify(body));
    }

</script>
</body>
</html>