$(document)
		.ready(
				function() {
					var zhen = 30;
					var time = parseInt(1000 / 30);
					var isd = false;
					var obj;
					$("#main").html("");
					for (var i = 0; i < 10; i++) {
						for (var j = 0; j < 10; j++)
							$("#main").append(
									"<div class=pai id=\"" + (i * 10 + j)
											+ "\" style=\"top: " + i * 21
											+ "px; left:" + j * 21
											+ "px\"></div>");
					}

					$("#fly").click(function(event) {
						// setInterval(move, time);

					});
					$(".pai").click(function(e) {
						$("#info").html($(e.target).attr('id'));
					});
					$(".pai").mouseenter(function(e) {
						$("#info").html($(e.target).css("left"));
						$(e.target).css("background-color", "rgb(1, 143, 41)");
					});
					$(".pai").mouseleave(
							function(e) {
								$(e.target).css("background-color",
										"rgb(109, 143, 41)");
							});
					$(".pai").mousedown(function(e) {
						isd = !isd;
						if (isd) {
							obj = $(e.target);
						} else {
							obj = null;
						}
						event.stopPropagation();
					});
					// $("body").mousedown(function(e) {
					// alert("body")
					// isd = false;
					// obj = null;
					// });
					$("body")
							.mousemove(
									function(e) {
										$("#info1")
												.html(
														e.originalEvent.x
																|| e.originalEvent.layerX
																|| 0);
										$("#info2").html(e.pageX);
										if (isd) {
											obj.css("left", e.clientX - 10
													+ "px");
											obj.css("top", e.clientY - 10
													+ "px");
										}
										//开始画直线
									});
					function step(para, time) {
						console.log("para:" + para);
						console.log("time:" + time);
						setInterval(move(), time);
					}
					function move() {
						for (var i = 0; i < 100; i++) {
							var left = parseInt(document.getElementById(i).style.left) + 10;
							console.log("left:" + left);
							// alert(event.clientX);
							document.getElementById(i).style.left = left + "px";
						}
					}
				});
