<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
        var wsuri = "ws://localhost:8380/websocket/WebSocket1?name=shang&password=123456";
        var ws = null;
        function startWebSocket() {
            if ('WebSocket' in window) {
                ws = new WebSocket(wsuri);
            } else if ('MozWebSocket' in window) {
                ws = new MozWebSocket(wsuri);
            } else {
                alert("not support");
            }

            ws.onmessage = function(evt) {
                alert(evt.data);
            };

            ws.onclose = function(evt) {
                alert("close");
            };

            ws.onopen = function(evt) {
                alert("open");
            };
        }

        function sendMsg() {
            ws.send(document.getElementById('writeMsg').value);
        }
    </script>
</head>
<body onload="startWebSocket();">
<input type="text" id="writeMsg" />
<input type="button" value="send" onclick="sendMsg()" />
</body>
</html>