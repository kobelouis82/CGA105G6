<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.1.js"
        integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
<title>客服中心</title>
<style>
		/* 設定版型與背景 */
		html, body, section {
 			height: 100%; 
            background-image: linear-gradient(0deg, #FFDEE9 0%, #0077 100%);
            background-color: #FFDEE9;
            background-repeat: no-repeat;
            background-size: cover;
        }

		/*整體設定 */
		.container {
			display: flex;
			width: 75%;
			margin-top:20px;
		}
		
		#row {
			width: 25%;
			max-height: 450px;
			overflow-x:hidden;
			overflow-y:auto;
			margin-left: 50px;
		}
		
        #statusOutput {
        	float:right;
            width: 75%;
            text-align: center;
            font-size:18px;
            font-weight: 700;
            color: #00435b;
        }
        
        .none {
        	width: 50%
        }
        
		/* 客戶列表設定 */
		.text{
			display: inline-block;
			width: 100%;
			text-align: center;
			font-size: 18px;
			font-weight: 700;
			color: #00435b;
			margin-bottom: 15px;
		}
		.column {
			font-size: 18px;
			padding: 5px;
		}
		
		.columnBlock:hover {
			cursor: pointer;
			background-color: white;
			opacity: 0.8;
		}

        /* 聊天室設定 */
        .chat{
        	width: 70%;
        	margin-right: 70px;
        }
        .panel {
            width: 70%;
            float: right;
            display: flex;
            align-item: center;
        }

        .chatroom {
            width: 60%;
            float: right;
        }
        
        #messagesArea {
        	height: 400px;
        	border-radius: 5px;
	        padding: 5px 0;
	        background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
            background-color: rgba(100, 100, 100, 0.7);
            background-blend-mode: multiply;
            opacity: 60%;
        }
        
        .input-area{
        	height: 50px;
        	padding: 5px 0;
        }
        
        #area {
        	width: 100%;
        	padding: 5px 0;
        	overflow-x:hidden;
			overflow-y:auto;
        }
        
        #message {
        	height: 36px;
        	width: 85%;
        	border: none;
        	border-radius: 5px;
        }
        
        #sendMessage{
        	float: right;
        	height: 36px;
        }
		
		ul{
			list-style: none;
			margin: 0;
			padding: 0;
		}

		ul li{
			display:inline-block;
			clear: both;
			padding: 5px 15px;
			border-radius: 30px;
			margin-bottom: 2px;
			font-family: Helvetica, Arial, sans-serif;
			}
			
	    .friend{
  			background: #eee;
 			 float: left;
		}

		.me{
			float: right;
			background: #0084ff;
			color: #fff;
		}
		
		/* 設定聊天室捲軸 */
		::-webkit-scrollbar {
  			width: 10px;
		}

		::-webkit-scrollbar-track {
		  background: #e1e5e8;
		}

		::-webkit-scrollbar-thumb {
		  background: #33b5e5;
		}

		::-webkit-scrollbar-thumb:hover {
		  background: #fff;
		}
    </style>
</head>
<body onload="connect();" onunload="disconnect();">
	<main>
		<section>
			
				<div class="container">
					<div id="row">
					<h4 class="text"><a href="<%=request.getContextPath()%>/back-end/main.jsp"><i class="fa-solid fa-circle-chevron-left">回首頁</i></a></h4>
					<span class="text">客服清單</span>
					</div>
					<div class="chat">
					<h3 id="statusOutput" class="statusOutput"></h3>
					<div id="messagesArea" class="panel message-area" ></div>
					<div class="panel input-area">
						<input id="message" class="text-field" type="text" placeholder="請輸入訊息" onkeydown="if (event.keyCode == 13) sendMessage();" />&ensp; 
						<input type="submit" id="sendMessage" class="button btn btn-info" value="送出" onclick="sendMessage();" /> 
					</div>
					</div>
				</div>
			</body>
		</section>
	</main>


<script>
	var MyPoint = "/FriendWS/${userName}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var self = '${userName}';
	var webSocket;

	function connect() {
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			console.log("Connect Success!");
			document.getElementById('sendMessage').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("open" === jsonObj.type) {
			} else if ("history" === jsonObj.type) {
				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var li = document.createElement('li');
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
					historyData.sender === self ? li.className += 'me' : li.className += 'friend';
					li.innerHTML = showMsg;
					ul.appendChild(li);
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				var li = document.createElement('li');
				jsonObj.sender === self ? li.className += 'me' : li.className += 'friend';
				li.innerHTML = jsonObj.message;
				console.log(li);
				document.getElementById("area").appendChild(li);
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("close" === jsonObj.type) {
			}
			
		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	
	function sendMessage() {
		var inputMessage = document.getElementById("message");
		var friend = statusOutput.textContent;
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("請在對話框中輸入文字");
			inputMessage.focus();
		} else if (friend === "") {
			alert("請先選擇客服對象");
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : friend,
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			showaMessage();
			inputMessage.value = "";
			inputMessage.focus();
		}
		var scrollHeight = $('#area').prop("scrollHeight");
	      $('#area').scrollTop(scrollHeight,5000);
	}

	//取得客服清單
	function loadGuest() {
		var row = document.getElementById("row");
		row.innerHTML += `
			<c:forEach items="${requestScope.keys}" var="keys">
			<div class="columnBlock"><span class = column id="${keys}">${keys}</span></div><hr>
			</c:forEach>
		`;		
		addListener();
	}
	
	$(document).ready(loadGuest());

	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener() {
		var container = document.getElementById("row");
		container.addEventListener("click", function(e) {
			var friend = e.srcElement.textContent;
			updateFriendName(friend);
			var jsonObj = {
					"type" : "history",
					"sender" : self,
					"receiver" : friend,
					"message" : ""
				};
			webSocket.send(JSON.stringify(jsonObj));
		});
	}
	
	//離線時顯示傳送訊息
	function showMessage() {
		var friend = "admin";
		updateFriendName(friend);
		var jsonObj = {
				"type" : "history",
				"sender" : self,
				"receiver" : friend,
				"message" : ""
			};
		setTimeout(function(){
			webSocket.send(JSON.stringify(jsonObj));
		}, 50)
	}
	
	function showaMessage() {
		var friend = statusOutput.textContent;
		updateFriendName(friend);
		var jsonObj = {
				"type" : "history",
				"sender" : self,
				"receiver" : friend,
				"message" : ""
			};
		setTimeout(function(){
			webSocket.send(JSON.stringify(jsonObj));
		}, 50)
	}
	
	
	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
	}
	
	function updateFriendName(name) {
		statusOutput.innerHTML = name;
	}
</script>
</html>