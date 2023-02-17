var count = 121;

function timesCount() {
	count--;
	self.postMessage(count);

	var timerId = setTimeout(function() {
		timesCount();
	}, 1000);

	if (count == 0){
		clearInterval(timerId);
		console.log("安安");
//		location.href = "https://tw.yahoo.com";
//		location.href = "../../back-end/backstageGetPassword/passwordForgotten.jsp";
	}
}
timesCount();