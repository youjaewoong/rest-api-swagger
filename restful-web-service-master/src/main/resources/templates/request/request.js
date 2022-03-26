
$(document).ready(function() {
	
	init = function() {
		refreshProc();
		$('.loading_screen').hide();
	}
	
	listUser = function() {
		var params = {};
		for(var i=0; i < 3; i++) {
			params['users['+i+'].name'] = "윤재웅_"+i
			params['users['+i+'].email'] = "gos1004@nate.com_"+i
		}
		commonAjax("/request/list-user", params);
	}
	
	listCheckString = function() {
		var $checkAttr = $("input[name='ids']:checked");
		if($checkAttr.length == 0) {
			alert("대상을 체크해 주세요.");
			return false;
		}
		
		var params = {};
		$checkAttr.each(function(i, e) {
			var ids = $(this).val();
			params['ids['+i+']'] = ids; 
		});
		commonAjax("/request/list-string", params);
	}
	
	listMap = function() {
		var params = {};
		for(var i=0; i < 3; i++) {
			params['users['+i+'].names[NAME]'] = "윤재웅_"+i;
			params['users['+i+'].emails[EMAIL]'] = "gos1004@nate.com_"+i;
		}
		commonAjax("/request/list-map", params);
	}
	
	map = function() {
		var params = {};
		params['NAME'] = "윤재웅";
		params['EMAIL'] = "gos1004@nate.com";
		
		commonAjax("/request/map", params);
	}
	
	json = function() {
		var param = {};
		var params = new Array();
		for(var i=0; i < 3; i++) {
			param.name = "윤재웅_"+i
			param.email = "gos1004@nate.com_"+i
			param.addrs = addrSet();
			params.push(param);
		}
		
		var jsonData = JSON.stringify({
			users: params
		});
		commonAjax("/request/json", jsonData);
	}
	
	addrSet = function() {
		var params = new Array();
		var addr = {};
		for(var i=0; i < 3; i++) {
			addr.hp = '010xxxx_'+ i
			addr.add = '서울_' + i
			params.push(addr)
		}
		return params;
	}
	
	commonAjax = function(url, data) {
		var callBackId = url.split('/').reverse()[0];
		var contentType =  setContentType(callBackId);
		$.ajax({
			 url : url
			,type : "post"
			,data : data
			,dataType : "json"
			,contentType: "application/"+contentType+";charset=UTF-8"
			,beforSend: function() {
				$('.loading_screen').show();
			}
			,success : function(response) {
				callBackResult(response) 
			}
			,erorr : function(e) {
				alert("오류가 발생했습니다.");
				console.log(e.statusText);
			}
			,complate: function() {
				$('.loading_screen').hide();
			}
		})	
	};
	
	setContentType = function(callBackId) {
		if(callBackId != 'json') {
			callBackId = "x-www-form-urlencoded";
		}
		return callBackId;
	}
	
	callBackResult = function(response) {
		console.log("response ::", response);
	}
	
	refreshProc = function() {
		window.onkeydown = function(e) {
			if(e.keyCode == 116) {
				//새로고침
				window.localStorage.clear();
			}
		}
	}
	
	init();
});