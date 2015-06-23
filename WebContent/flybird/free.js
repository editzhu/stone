$(document).ready(function() {
	var t = 0;
	var a = 0.5;
	var v = 10;
	var v2=5;
	setInterval(move, 30);

	$("#fly").click(function() {
		move();
	});

	function move() {
		t += 1;
		var t2 = t * t / 2;
		var top = 400 - (v * t - a * t2);
		if (top < 600 ) {
			$("#pai").css("left", 100+v2 * t );
			$("#pai").css("top", 400 - (v * t - a * t2));
		}
	}
});
