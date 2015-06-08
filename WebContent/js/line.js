$(document).ready(
		function() {
			var isbegin = false;
			var x1 = 0;
			var y1 = 0;
			$(".pai").live('mousedown', function(e) {
				
				if (isbegin) {
					$("#byatt").val($(e.target).attr('idtt'));
					$("#attack").trigger("click");
					$(".line").remove();
				} else {
					x1=e.clientX;
					y1=e.clientY;
					$("#att").val($(e.target).attr('idtt'));
				}
				isbegin = !isbegin;
			});
			$(".pai").live('mouseenter', function(e) {
//				$(e.target).css("background-color", "rgb(1, 1, 1)");
			});
			$(".pai").live('mouseleave', function(e) {
//				$(e.target).css("background-color", "rgb(109, 143, 41)");
			});
			$("body").live('mousemove', function(e) {
				if (isbegin) {

					// 开始画直线,从坐标A(100,100)开始,在鼠标位置和A之间画直线
					$(".line").remove();

					var x2 = e.clientX - 10;
					var y2 = e.clientY - 10;
					line(x1, y1, x2, y2);
				}
//				event.stopPropagation();
			});
			function line(x1, y1, x2, y2) {
				var x = 0;
				var y = 0;
				for (var i = 0; i < Math.abs(x2 - x1); i++) {
					x = (x2 - x1 > 0 ? 1 : -1) * i;
					y = x * (y2 - y1) / (x2 - x1);
					$("#main").append(
							"<div class=line style=\"top: " + (y + y1)
									+ "px; left:" + (x + x1) + "px\"></div>");
				}
				for (var i = 0; i < Math.abs(y2 - y1); i++) {
					y = (y2 - y1 > 0 ? 1 : -1) * i;
					x = y * (x2 - x1) / (y2 - y1);
					$("#main").append(
							"<div class=line style=\"top: " + (y + y1)
									+ "px; left:" + (x + x1) + "px\"></div>");
				}

			}
		});
