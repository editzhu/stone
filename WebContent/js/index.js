$(document).ready(
		function() {
//			hide();
			show();
//			setInterval(down1,100000);
			function down1(){
				console.log("adf");
				send("flash");
			}
			function hide(){
//				$("button").attr({"disabled":"disabled"});
				showOverlay();
			}
			function show(){
//				$("button").removeAttr("disabled");
				hideOverlay();
			}

			$("#b01").click(function() {
				send("flash");
			});
			$("#zhuapai").click(function() {
				send("zhuapai");
			});
			$("#dachu").click(function() {
				send("dachu&frompai=" + $("#dachu_index")[0].value);
			});
			$("#ini").click(function() {
				send("ini");
			});
			$("#attack").click(function() {
				send("attack&frompai=" + $("#att")[0].value+"&topai="+$("#byatt")[0].value);
			});
			$("#stop").click(function() {
				send("stop");
			});
			function send(para) {
				htmlobj = $.ajax({
					url : "/stone/Fapai?para=" + para,
					async : false
				});

				console.log(htmlobj.responseText);

				var obj = jQuery.parseJSON(htmlobj.responseText);
				var isreturn = false;
				$.each(obj, function(key, value) {
					if (key.trim() == "error") {
						$("#myDiv").html(obj.error);
						isreturn = true;
					}
				});
				if (isreturn) {
					return;
				}
				
				if(obj.stage==0){
//					hide();
					return;
				}
				show();
				$(".l1").html(
						'<div class="l">' + obj.l1 + "</div>"
								+ '<div class="f">' + obj.f1 + "</div>");
				$(".l2").html(
						'<div class="l">' + obj.l2 + "</div>"
								+ '<div class="f">' + obj.f2 + "</div>");

				$(".session1").html(obj.session1);
				$(".session2").html(obj.session2);
				$(".pk1").html(obj.pk1Size);
				$(".pk2").html(obj.pk2Size);
				$(".stage").html("stage:"+obj.stage);
				$(".jiji").html("no Player");
				if (obj.is1 == 1)
					$(".jiji").html("1 Player");
				else if (obj.is1 == 2)
					$(".jiji").html("2 Player");

				$(".sp1").html("");// 清空
				for (var i = 0; i < obj.sp1.length; i++) {
					$(".sp1").append(
							"<div class=pai id=" + i + ">"
									+ "<div class=attack>" + obj.sp1[i].attack
									+ "</div>" + "<div class=life>"
									+ obj.sp1[i].life + "</div>"
									+ "<div class=name>" + obj.sp1[i].name
									+ "</div>" + "<div class=price>"
									+ obj.sp1[i].price + "</div>" + "</div>");
				}

				$(".sp2").html("");// 清空
				for (var i = 0; i < obj.sp2.length; i++) {
					$(".sp2").append(
							"<div class=pai>" + "<div class=attack>"
									+ obj.sp2[i].attack + "</div>"
									+ "<div class=life>" + obj.sp2[i].life
									+ "</div>" + "<div class=name>"
									+ obj.sp2[i].name + "</div>"
									+ "<div class=price>" + obj.sp2[i].price
									+ "</div>" + "</div>");
				}

				$(".cp1").html("");// 清空
				for (var i = 0; i < obj.cp1.length; i++) {
					//判断是否setp=0,改变css
					var isos="";
					if(obj.cp1[i].step==0){
						isos="os";
					}
					
					$(".cp1").append(
							"<div class="+isos+"pai idtt="+i+">" + "<div class=attack>"
									+ obj.cp1[i].attack + "</div>"
									+ "<div class=life>" + obj.cp1[i].life
									+ "</div>" + "<div class=name>"
									+ obj.cp1[i].name + "</div>"
									+ "<div class=price>" + obj.cp1[i].price
									+ "</div>" + "</div>");
				}

				$(".cp2").html("");// 清空
				for (var i = 0; i < obj.cp2.length; i++) {
					//判断是否setp=0,改变css
					var isos="";
					if(obj.cp2[i].step==0){
						isos="os";
					}
					
					$(".cp2").append(
							"<div class="+isos+"pai idtt="+i+">" + "<div class=attack>"
									+ obj.cp2[i].attack + "</div>"
									+ "<div class=life>" + obj.cp2[i].life
									+ "</div>" + "<div class=name>"
									+ obj.cp2[i].name + "</div>"
									+ "<div class=price>" + obj.cp2[i].price
									+ "</div>" + "</div>");
				}
				$("#myDiv").html("response OK");
			}
		});

