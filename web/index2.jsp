<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script language="JavaScript">
        var wsuri = "ws://localhost:8380/websocket/WebSocket2";
        var ws = null;

        function connectEndpoint() {
            ws = new WebSocket(wsuri);
            ws.onmessage = function(evt) {
                //alert(evt.data);
                document.getElementById("echo").value = evt.data;
            };

            ws.onclose = function(evt) {
                //alert("close");
                document.getElementById("echo").value = "end";
            };

            ws.onopen = function(evt) {
                //alert("open");
                document.getElementById("echo").value = "open";
            };
        }

        function sendmsg() {
            ws.send(document.getElementById("send").value);
        }
    </script>
</head>
<body onload="connectEndpoint()">
<input type="text" size="20" value="5" id="send">
<input type="button" value="send" onclick="sendmsg()">
<br>
<input type="text" id="echo">
</body>
</html>