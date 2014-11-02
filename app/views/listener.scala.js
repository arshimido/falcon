@(username: String)

$(function() {
    var WS = window['MozWebSocket'] ? MozWebSocket : WebSocket
    var chatSocket = new WS("@routes.Application.realTime().webSocketURL(request)")

    var receiveEvent = function(event) {
    	alert(event.data);
    	var data = JSON.parse(event.data)
    	
        // Create the message element
        var el = $('<div class="message">' + data.message + '</div>')
        $('#messages').append(el);
        $(el).animate({ color: "black"}, 4000);

    }

    chatSocket.onmessage = receiveEvent

})
