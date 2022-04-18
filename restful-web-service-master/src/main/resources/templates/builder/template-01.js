$(document).ready(function() {
	eXcell_ronum = function(cell){
	    if (cell){
	        this.cell = cell;
	        this.grid = this.cell.parentNode.grid;
	    }
	    this.edit = function(){}
	    
	    this.isDisabled = function(){ return true; } 
	    this.setValue=function(val){
	    	if(isEmpty(val)) {
	    		val = 0;
	    	}else {
	    		val = numeral( val ).format( '0,0.[00]' );
	    	}
	        this.setCValue(val); 
	    }
	}
	eXcell_ronum.prototype = new eXcell;
	
	eXcell_rod = function(cell){
	    if (cell){
	        this.cell = cell;
	        this.grid = this.cell.parentNode.grid;
	    }
	    this.edit = function(){}
	    
	    this.isDisabled = function(){ return true; } 
	    this.setValue=function(val){
	    	if(!isEmpty(val)) {
		    	val = replaceAll(val, '-', '');
		    	val = replaceAll(val, '.', '');
		    	val = replaceAll(val, ' ', '');
		    	val = moment(val, 'YYYYMMDD').format('YYYY-MM-DD');
	    	}
	        this.setCValue(val); 
	    }
	}
	eXcell_rod.prototype = new eXcell;

	eXcell_button = function(cell){
		if (cell){                
	        this.cell = cell;
	        this.grid = this.cell.parentNode.grid;
	    }
	    this.edit = function(){} 
	    this.isDisabled = function(){ return true; }
	    this.setValue=function(val){
	        if(!isEmpty(val)) {
	        	this.setCValue("<button id='"+val+"' onclick='javascript:fnDownloadFileListPopup(\"popupDownloadFileListForm\", \""+val+"\");'>다운로드</button>",val);
	    	}
	    }
	    
	    this.getValue=function(){
	    	var result = "";
	        if(this.cell.childNodes.length > 0) {
	        	result = this.cell.childNodes[0].id; 
	        }else {
	        	result = "";
	        }
	        return result;
	    }
	}
	eXcell_button.prototype = new eXcell;	
	
    // trigger 발생순서 보장을 위한 멤버변수 
    var triggerBindField = [];
    
	//그리드 rowid배열을 파라메터로 받는다
	makeNewRowId = function(rowids){
	    var ids = rowids.split(",");
	    var max = 100;
	    for(var i=0; i<ids.length; i++){
	        if(max <= Number(ids[i])) {
	            max = Number(ids[i]);
	        }
	    }
	    return Number(max) + 1;
	}
	
	listToDetail = function(rowId, cInd){
	    var colNum = grdList.getColumnsNum();
	    for(i=0; i<colNum; i++) {
	    	var columnName = grdList.getColumnId(i);
	        var columnObjectType = $("#render [custom-input='true'][data-field="+columnName+"]").prop('type');
	        var columnValue = grdList.cells(rowId, i).getValue();

	        if("radio" == columnObjectType) {
		    	$("#render [custom-input='true'][data-field="+columnName+"][value=" + columnValue + "]").prop("checked", true);
	        }else {
	        	
	        	// 날짜 형식일경우 
	        	if(!isEmpty(columnValue) && (columnName.indexOf("_YMD") > -1 || columnName.indexOf("_DATE") > -1)) {
	        		$("#render [custom-input='true'][data-field="+columnName+"]").val(columnValue);
	        		
		        // 파일일경우 input value setting 제외 
	        	}else if(!isEmpty(columnValue) && (columnName.indexOf("FILE") > -1)) {	        		
	        	
	        	}else {
	        		$("#render [custom-input='true'][data-field="+columnName+"]").val(columnValue);
	        	}
	        }
	    }

	    $.each(fieldList,function(i,v){
	    	if(v.fieldPkAt == 'Y') {
	    		$("#render [custom-input='true'][data-field="+v.fieldId+"]").attr('disabled', true);
	    	}
	    });

	    
		// 임시 삭제파일 저장 변수 초기화 
		clearRemoveFilesTempObject();
		
	    //$("#render i[class='fas fa-search']").hide();
	    $('#builderForm #listRowid').val(rowId);
	    
	    $('#buttonListArea #INSERT').hide();
	    $('#buttonListArea #UPDATE').show();

        // 트리거 순서 정렬 -> 오름차순
        triggerBindField = triggerBindField.sort(function(a, b){
            return a.id < b.id ? -1 : a.id > b.id ? 1 : 0;
        });
        
        // 트리거가 순차적으로 실행되도록 설정
        for(var i = 0 ; i < triggerBindField.length ; i++ ){
            // 이벤트 트리거간 지연을 발생시켜 동작하도록 하였음,
            (function(x){
                setTimeout(function(){
                    var elementName = '#'+triggerBindField[x].field;
                    $(elementName).trigger('change');
                }, 10*x);
            })(i);
        }
	    
	    // 공통으로 사용(각업무페이지에서 처리) 
	    listToDetailAfter(rowId);
	    
	    // required 체크 해지
	    $('input.required_input').trigger('input');
	    $('select.required_input').trigger('change');
	}
	
	
    checkInputRequired = function(){
        var required_check = true;
        var all_required = $('.sub_body input, select, textarea').filter('[required]');
        all_required.each(function(){
    		if($(this).val() == ''){
    			alert(getMessages('CMMN.application.required_input_value'));
    			$(this).addClass('required_input');
    			required_check = false;
    			$('.loading_screen').hide();
    		}
        });
        
        return required_check;
    }
    
    // 함수 설정
    // 버튼 클릭시 (입력, 삭제, 신청.....)
    fnButtonClick = function(buttonId, actName){
    	$('.loading_screen').show();
    	
    	var validateResult = actionValidate(buttonId);
    	if(isEmpty(validateResult) || !validateResult) return false;
    	
    	if(buttonId == "delete") {
        	var ids = grdList.getCheckedRows(grdList.getColIndexById("Check"));
            if (ids){
    				var dataParams = selectDeleteData(buttonId, actName);
    				if( dataParams != false && !isEmpty(dataParams) && dataParams != '[]' ) {
    					ajaxDataProc(dataParams, buttonId, actName);
    				};
            }else {
            	alert(actName +"할 행을 선택하세요.");
            }
    	}else if(buttonId == "clear") {
    		clearFormFunc();
    	}else if(buttonId == "calc") {
			if(confirm("기존 데이터가 있는경우에는 기존데이터가 삭제되고 새로 생성됩니다.<br>계산하시겠습니까?")) {
				calcClickFunc();
		    } 
		    $('.modal_box').hide();
    	}else if(buttonId == "proc_end") {
	
			if(confirm("해지하시겠습니까?")) {
				procEndFunc();
		    }
		    $('.modal_box').hide();
		    
    	}else if(buttonId == "excel_down") {
    		var fileName = replaceAll($.trim($('#service_display_name').text()), "(", "_");
    		fileName = replaceAll(fileName, ")", "");
    		dhx.fileName = fileName + "_" + getToday("");
    		grdList.toExcel('/excelGenerator.do');    		
    	}else {
    	    var is_required_filled = checkInputRequired();
    	    if(!is_required_filled){
    	    	alert("필수 입력값을 확인하시기 바랍니다.");
    	    	return;
    	    }
    	    
    	    if(confirm(actName, "데이터를 " + actName + " 하시겠습니까?")) {
		   		var dataParams = selectData(buttonId, actName);
	    		if( dataParams != false && !isEmpty(dataParams) && dataParams != '[]' ) {
	    			ajaxDataProc(dataParams, buttonId, actName);
	    		}
		    } else {
				return;
			}
    	}
    	$('.loading_screen').hide();
    }

    clearFormFunc = function() {
    	// text 입력박스 clear
    	var obj = $("#render [custom-input='true'][type=text]");
    	for (var i = 0; i < obj.length; i++) {
    		$(obj[i]).val('');
    	}

    	// text number 입력박스 clear
    	var obj = $("#render [custom-input='true'][type=number]");
    	for (var i = 0; i < obj.length; i++) {
    		$(obj[i]).val('');
    	}
    	
    	// radio 버튼 첫번재값으로 set
        if(!isEmpty($("#render [custom-input='true']:radio:first"))) $("#render [custom-input='true']:radio:first").prop('checked', true);
        
        var obj = $("#render [custom-input='true'].select_option");
    	for (var i = 0; i < obj.length; i++) {
    		$(obj[i]).val('');
    	}
    	$('select.search_select').select2();
    	
    	// 입력값 setting
        initFormDataSetting();
    	
        // PK컬럼 입력박스 입력가능하게 처리
	    $.each(fieldList,function(i,v){
	    	if(v.fieldPkAt == 'Y') {
	    		$("#render [custom-input='true'][data-field="+v.fieldId+"]").attr('disabled', false);
	    	}
	    });
	    
        // 파일정보 clear
        initFileLists();
        clearRemoveFilesObject();
        
	    // 저장 버튼 표시, 수정버튼 숨김
	    $('#buttonListArea #INSERT').show();
	    $('#buttonListArea #UPDATE').hide();
	    
        // 트리거 순서 정렬 -> 오름차순
        triggerBindField = triggerBindField.sort(function(a, b){
            return a.id < b.id ? -1 : a.id > b.id ? 1 : 0;
        });
        // parents 의 value 값을 null 로 하여 child 요소들이 초기화 됨
        for(var i = 0 ; i < triggerBindField.length; i++){
            var elementName = '#'+ triggerBindField[i].field;
            $(elementName+' option:eq(0)').prop('selected', true);
            $(elementName).trigger('change');
        }
        
	    // 공통으로 사용(각업무페이지에서 처리) 
    	clearFormFuncAfter();
    }

    
    selectData = function() {
    	var dataParams = "";

        var iCnt = 0;
        var jsonArray = new Array();
        
        // jsonObject 생성
        var jsonDataObj = {}; 
        jsonDataObj['serviceId'] = $('#builderForm #serviceId').val();
        
        var obj = $("#render [custom-input='true']");

        for (var i = 0; i < obj.length; i++) {        	
        	var searchField = $(obj[i]).attr('data-field');
        	
        	if(!isEmpty(searchField)) {
	            var columnObjectType = $(obj[i]).prop('type');
	
	            if("radio" == columnObjectType) {
	            	var radioName= $(obj[i]).prop('name');
	            	jsonDataObj[searchField] = $('input:radio[name="'+$(obj[i]).prop('name')+'"]:checked').val();
	            }else if("select-one" == columnObjectType) {
	            	jsonDataObj[searchField] = $(obj[i]).val();
	            	
	            	if(searchField.indexOf("_CODE") > -1) {
	            		var nameColumn = replaceAll(searchField, "_CODE", "_NAME");
	            		var nameColumnText = $("#render [custom-input='true'][data-field='"+searchField+"'] option:selected").text()
	                	jsonDataObj[nameColumn] = nameColumnText;            		
	            	}
	            }else {
	            	// 날짜일경우 ".", " " 제거, 시간일경우 ":" 제거
	            	var columnValue = $(obj[i]).val();
	                if(!isEmpty(columnValue) && (searchField.indexOf('_YMD') > -1 || searchField.indexOf('_DATE') > -1)){
	                	columnValue = replaceAll(columnValue, "-", "");
	                	columnValue = replaceAll(columnValue, ".", "");
	                	columnValue = replaceAll(columnValue, " ", "");
	                }else if(!isEmpty(columnValue) && (searchField.indexOf('_TIME') > -1)){
	                	columnValue = replaceAll(columnValue, ":", "");
	                }else if(!isEmpty(columnValue) && (searchField.indexOf('PRICE') > -1 || searchField.indexOf('AMT') > -1)){
	                	columnValue = replaceAll(columnValue, ",", "");
	                }
	                
	            	jsonDataObj[searchField] = columnValue+'';
	            }
        	}
        }
        
        // 업무별 추가사항 
        var dataObj = selectDataAfter(jsonDataObj);        
        jsonArray[iCnt] = dataObj;
        dataParams = JSON.stringify(jsonArray);
        
        // 저장한 파일 Object Get
        var fileTemp = getFileTempArr(); 
        listFilesTempArr["row_1"] = fileTemp;
        removeFileDataFunc();
        // 저장한 파일 Object Get
        
        return dataParams;
    }
    
    selectDeleteData = function() {
    	var dataParams = "";

        var iCnt = 0;
        var jsonArray = new Array();
        
    	var ids = grdList.getCheckedRows(grdList.getColIndexById("Check"));
        if (ids){ 
            var checkData = ids.split(",");

            for (var i = 0; i < checkData.length; i++) {
            	if(!isEmpty(checkData[i])) {
	                // jsonObject 생성
	                var jsonObj = {}; 
	                
	                var columnNum = grdList.getColumnsNum();
	                for(columnIndex=0; columnIndex<columnNum; columnIndex++) {
	                    var columnName = grdList.getColumnId(columnIndex);   
						jsonObj[columnName] = grdList.cells(checkData[i], columnIndex).getValue()+'';
	                }
	                jsonArray[iCnt] = jsonObj;
	                iCnt++;
            	}
            }
            dataParams = JSON.stringify(jsonArray);
        }
        return dataParams;
    }
    

    // 그리드 json 데이터를 서버에 전달
    ajaxDataProc = function(param, buttonId, actName) {
    	//$('.loading_screen').show();
    	
        var frm = $("#dataForm")[0];
        var formData = new FormData(frm);
        
        formData.append("codeData", param);
        formData.append("serviceId", $('#builderForm #serviceId').val());

        var keyArray = Object.keys(listFilesTempArr);
        
        // 파일 데이터
        for ( var k in keyArray ) {
            var key = keyArray[k];
            var keyArray2 = Object.keys(listFilesTempArr[key]);
            var filesTempArrLen = keyArray2.length;
            for(var i=0; i<filesTempArrLen; i++) {
            	var key2 = keyArray2[i];
                formData.append(key+"-"+key2, listFilesTempArr[key][key2]);
            }
        }
        
        // 삭제파일 데이터
        var delfiles = getRemoveFilesObject();
        var delFileParams = JSON.stringify(delfiles);
        formData.append("delFileParams", delFileParams);
        
        $.ajax({
            type : "POST",
            url : "/user/service/"+buttonId+"/serviceCodeProcess.do",
            data : formData,
            dataType: "json",
            processData: false,
            contentType: false,
            beforeSend: function() {
            	$('.loading_screen').show();
            },
            success : function(result) {
                var returnObj = result.returnObj;
                if (returnObj.returnCode == "SUCCESS") {
                    cbAlert(actName +" 되었습니다.",
                            function() {
		                        // 리스트 조회
		                        ajaxListData();
		                        
		                        // 입력정보 clear
		                        clearFormFunc();
                            }
                        );                	
                }else if (returnObj.returnCode == "FAIL") {
                    alert(returnObj.returnMessage);
                }
                
            },
            error : function() {
                alert("Error 발생");
            },
            complete : function() {
            	$('.loading_screen').hide(); 
            }
        });
    }
    
    // 코드 정보 조회
    ajaxListData = function() {
        var serviceId = $('#builderForm #serviceId').val();
        var jsonSearchObj = {}; 
        
        // form에 있는 검색 조건 값
        var obj = $("#render [custom-search='true']");
        for (var i = 0; i < obj.length; i++) {
        	
        	var searchField = $(obj[i]).attr('data-field');
        	var searchType = $(obj[i]).attr('custom-search-type');
        	
        	if(isEmpty(searchType)) {
	        	if(!isEmpty($(obj[i]).val())) {
	        		var columnValue = $(obj[i]).val();
	        		
	                if(!isEmpty(columnValue) && (searchField.indexOf('_YMD') > -1 || searchField.indexOf('_DATE') > -1)){
	                	columnValue = replaceAll(columnValue, "-", "");
	                	columnValue = replaceAll(columnValue, ".", "");
	                	columnValue = replaceAll(columnValue, " ", "");
	                }else if(!isEmpty(columnValue) && (searchField.indexOf('_TIME') > -1)){
	                	columnValue = replaceAll(columnValue, ":", "");
	                }else if(!isEmpty(columnValue) && (searchField.indexOf('PRICE') > -1 || searchField.indexOf('AMT') > -1)){
	                	columnValue = replaceAll(columnValue, ",", "");
	                }	        		
	        		
	        		jsonSearchObj[searchField] = columnValue;
	        	}
        	}else {
        		if(searchType == "from" || searchType == "to") {
        			if(isEmpty(jsonSearchObj[searchField])) {
	        			var fromValue = replaceAll($("#render [data-field='"+searchField+"'][custom-search-type='from']").val(), "-", "");
	        			var toValue = replaceAll($("#render [data-field='"+searchField+"'][custom-search-type='to']").val(), "-", "");
	        			
	        			/*var requestDateDiff = dateDiff(fromValue, toValue);
	        		    if(requestDateDiff > 90) {
    		        		alert("90일까지 조회하실 수 있습니다.");
    		        		$('.loading_screen').hide();
    		        		return;
    		        	}*/
	        			if(isEmpty(toValue)) toValue = "99991231";
	        			if(!isEmpty(fromValue) && !isEmpty(toValue)) {
	        				jsonSearchObj[searchField] = fromValue + "AND" + toValue + "";
	        			}
        			}
        		}
        	}
        }
        
        jsonSearchObj = addSearchParam(jsonSearchObj);
        var searchParams = JSON.stringify(jsonSearchObj);
        
        $.ajax({
            type : "POST",
            url : "/user/service/code/getGridDataList.do",
            data : {
                "serviceId" : serviceId, 
                "searchParams" : searchParams
            },
            dataType: "json",
            beforeSend: function() {
            	$('.loading_screen').show();
            },            
            success : function(result) {
                var returnObj = result.returnObj;
                if (returnObj.returnCode == "SUCCESS") {
                    var jsonData = returnObj.templateDataList;

                    grdList.clearAll();
                    grdList.parse(jsonData,'js');  //그리드1 데이터 출력
                    
                    var count = grdList.getRowsNum();
                    $('#totalCountRows').text(numeral(count).format('0,0'));
                    
                    // 조회후 정렬 설정
                    setCustomSort();
                    
                    // 입력정보 clear
                    clearFormFunc();
                }
            },
            error : function() {
                alert("Error 발생");
            },
            complete : function() {
            	grdList.filterByAll();
            	$('.loading_screen').hide(); 
            }
        });
    }
    
	$(document).on('input','input.required_input',function(){
	    $(this).removeClass('required_input');
	});
	
	$(document).on('change','select.required_input',function(){
	    $(this).removeClass('required_input');
	});        
	
	getFileInfo = function(rowId, dbFieldId){
		var key = "row_"+rowId;
		
		// 첨부파일 리스트 set (DB에 저장된)
		var dbFileList = '';
		
		var fileColumnIndex = grdList.getColIndexById(dbFieldId);
		var fileId = "";
		if(!isEmpty(fileColumnIndex)) {
			fileId = grdList.cells(rowId, fileColumnIndex).getValue();
		}
        if(!isEmpty(fileId)) {
            $.ajax({
                type : "POST",
                url : "/user/service/approval/getServiceFileList.do",
                data : {
                    "fileId" : fileId
                },
                dataType: "json",
                async: false,
                success : function(result) {
                    var returnObj = result.returnObj;
                    if (returnObj.returnCode == "SUCCESS") {
                        dbFileList = returnObj.fileList;
                    }
                },
                error : function() {
                    alert("Error 발생");
                }
            });
        }
		
		// 첨부파일 리스트 set 
		if(isEmpty(listFilesTempArr[key]))
		    addFileListItemFunc(dbFileList, null, dbFieldId);
		else 
		    addFileListItemFunc(dbFileList, listFilesTempArr[key], dbFieldId);	  
	}
	
    /**  
     * 파일 관련 배열 및 리스트 초기화
     */
    initFileLists = function() {
    	clearFilesTempArr();
    	clearRemoveFilesTempObject();
    }
    
});

