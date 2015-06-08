$(document).ready(
		function() {
			$("body").mousemove(
					function(e) {
						$("#info1").html(
								e.originalEvent.x || e.originalEvent.layerX
										|| 0);
						$("#info2").html(e.pageY);
						// 开始画直线,从坐标A(100,100)开始,在鼠标位置和A之间画直线
						$(".line").remove();
						var x1 = 300;
						var y1 = 300;
						var x2 = e.clientX - 10;
						var y2 = e.clientY - 10;
						var x = 0;
						var y = 0;
						var jx=0;
						var jy=0;
						line(x1, y1, x2, y2);
						function line(x1, y1, x2, y2) {
							for (var i = 0; i < Math.abs(x2 - x1); i++) {
								x = (x2 - x1 > 0 ? 1 : -1) * i;
								y = x * (y2 - y1) / (x2 - x1);
								$("#main").append(
										"<div class=line style=\"top: "
												+ (y + y1) + "px; left:"
												+ (x + x1) + "px\"></div>");
							}
							for (var i = 0; i < Math.abs(y2 - y1); i++) {
								y = (y2 - y1 > 0 ? 1 : -1) * i;
								x = y * (x2 - x1) / (y2 - y1);
								$("#main").append(
										"<div class=line style=\"top: "
												+ (y + y1) + "px; left:"
												+ (x + x1) + "px\"></div>");
							}

						}
					});
		});
