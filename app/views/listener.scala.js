@(username: String)

$(function() {
    var WS = window['MozWebSocket'] ? MozWebSocket : WebSocket
    var chatSocket = new WS("@routes.Application.realTime().webSocketURL(request)")

    var receiveEvent = function(event) {
    	alert(event);
    	alert(event.data);
    	var data = JSON.parse(event.data)
    	
        // Create the message element
        var el = $('<div class="message"><p></p></div>')
        $("p", el).text(data.message)
        $('#messages').append(el);
        $('#messages').append("<br ><br >");

    }

    chatSocket.onmessage = receiveEvent

})
