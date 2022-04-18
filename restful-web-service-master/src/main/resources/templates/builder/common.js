$(document).ready(function() {
    var triggerBindField = [];
    /**
    * logout function
    */
    logoutFunc = function() {
        window.location.href = "/logout.do";
        return false;
    }
    
    partnerLogoutFunc = function() {
        window.location.href = "/partnerLogout.do";
        return false;
    }    
    
    var filesTempArr = [];
    var filesTempArr2 = [];
    var removeFilesTempObject = [];
    var removeFilesObject = [];
    
    /**
     * add multi file upload
     * Statements
     * @param obj
     */
    addFileItemFunc = function(obj) {
        var formFileID = $(obj).parent().parent().find('input').attr('id');
        var dataFieldID = $(obj).parent().parent().find('input').attr('data-field');
        var fileName = $('#' + formFileID).val();
        //console.dir("formFileID : " + formFileID);
		if(fileName === '') {
		    alert('선택된 파일이 없습니다.');
		    return;
		}
		
		if(fileName.indexOf("#")>-1) {
		    alert("파일명에 '#'을 제거하고 업로드해주시기 바랍니다.");
		    return;
		}

		var attachFiles = $(obj).parent().parent().parent().parent().find('#attachFiles');
		var fileList = $(attachFiles).find('.img_file_box');
        
        // 동일한 파일이 있는지 확인		
		var flag = true;
		$.each(fileList, function(){
			var listFilename = $(this).find('#fileList').text();
			fileName = fileName.substr(fileName.lastIndexOf("\\")+1);
		 	if(listFilename == fileName) {
	                flag = false;
	                alert('동일한 파일이 있습니다.');
	                return false;
	            } 		 	
		})
		
        if((fileList.length+1) > 5)  {
            alert('첨부파일은 5개만 가능합니다.');
            return;        	
        }
        
        if(flag == false) return;
        
        // 업로드 가능한 확장자 체크
        if(!/\.(gif|jpg|jpeg|png|zip|7z|ppt|pptx|doc|docx|xls|xlsx|pdf|mp4|hwp|wmv)$/i.test(fileName)) {
            $('#' + formFileID).val('');
            alert('gif, jpg, png, zip, 7z, ppt, doc, xls, txt, pdf, wmv 파일만 선택해 주세요.\n\n현재 파일 : ' + fileName);
            return;
        }
        
        // 업로드한 파일 정보 저장(filesTempArr)
        $('#' + formFileID)[0].files[0].filedId = dataFieldID;
        var file = $('#' + formFileID)[0].files[0];

        var key = dataFieldID + "-" + fileList.length;
        filesTempArr[key] = file;
		//console.dir("key : "  + key);
		//console.dir(filesTempArr[key]);
        // 파일 리스트 생성 html
        var html = '';
		
        var fileExt = getExtensionOfFilename(file.name);
        var iconImage = getIconOfExtension(fileExt);
        
		html += '  <div class="img_file_box">';
    	html += '  		<div class="img_file">';
		html += '  			<a href="#">';
		html += '  				<img src="/images/ico/'+iconImage+'">';
		html += '  			</a>';
		html += '  			<div class="img_file_del_box">';
		html += '  				<i class="fas fa-times-circle" onclick="javascript:removeFileItemFunc(this, \''+key+'\')"></i>';
		html += '  			</div>';
		html += '  		</div>';
		html += '  		<div class="img_text" id="fileList">';
		html += '  			<a href="#">' + file.name + '</a>';
		html += '  		</div>';
		html += '  </div>';
        $(attachFiles).append(html);
        
        $('#' + formFileID).val('');
    }

    
    getExtensionOfFilename = function(filename) {
        var _fileLen = filename.length;
     
        /** 
         * lastIndexOf('.') 
         * 뒤에서부터 '.'의 위치를 찾기위한 함수
         * 검색 문자의 위치를 반환한다.
         * 파일 이름에 '.'이 포함되는 경우가 있기 때문에 lastIndexOf() 사용
         */
        var _lastDot = filename.lastIndexOf('.');
     
        // 확장자 명만 추출한 후 소문자로 변경
        var _fileExt = filename.substring(_lastDot+1, _fileLen).toLowerCase();
     
        return _fileExt;
    }
    
    getIconOfExtension = function(fileExt) {
    	var _iconImage = "";
        if(fileExt == 'xls' || fileExt == 'xlsx') {
        	_iconImage = "ico_excel.png";
        }else if(fileExt == 'doc' || fileExt == 'docx') {
        	_iconImage = "ico_word.png";
        }else if(fileExt == 'ppt' || fileExt == 'pptx') {
        	_iconImage = "ico_ppt.png";
        }else if(fileExt == 'pdf') {
        	_iconImage = "ico_pdf.png";
        }else if(fileExt == 'txt') {
        	_iconImage = "ico_txt.png";
        }else if(fileExt == 'zip' || fileExt == '7z') {
        	_iconImage = "ico_zip.png";
        }else {
        	_iconImage = "ico_file.png";
        }
    	
    	return _iconImage;
    }
    
    /** 
     * 파일 리스트 생성 
     * dbFileList : DB에 저장되어 있는 파일 목록
     * rowFileList : 배열에 저장되어 있은 파일 목록(업로드전 파일목록)
     */
    addFileListItemFunc = function(dbFileList, rowFileList, dbFieldId) {
    	var targetObj = $("#render [data-field='"+dbFieldId+"']");
    	var attachFiles = $(targetObj).parent().parent().parent().parent().find('#attachFiles');
        $(attachFiles).html('');
        
        //console.dir("===== dbFileList =====");
        //console.dir(dbFileList);
        //console.dir("===== rowFileList =====");
        //console.dir(rowFileList);
        //console.dir("===== removeFilesObject =====");
        //console.dir(removeFilesObject);
        
        // DB에 저장된 파일리스트 생성
        if(!isEmpty(dbFileList)) {
            for(var i=0; i<dbFileList.length; i++) {
                var fileData = JSON.stringify(dbFileList[i]);
                fileData = fileData.replace(/"/gi, "'");

                // DB에 저장된 파일중 삭제한 파일 목록 화면에서 제거
                // 저장 시 removeFilesObject에 들어있는 파일 정보로 DB 및 파일 삭제
                var delYn = "N";
                if(!isEmpty(removeFilesObject)) {
                    for(var j=0; j<removeFilesObject.length; j++) {
                        if(removeFilesObject[j].companyCode == dbFileList[i].companyCode && 
                                removeFilesObject[j].fileId == dbFileList[i].fileId && 
                                removeFilesObject[j].fileOrder == dbFileList[i].fileOrder ) {
                            delYn = "Y";
                        }
                    }
                }
                
                if(delYn != "Y") {
                    var html = '';

	                var fileExt = getExtensionOfFilename(dbFileList[i].originalFileName);
	                var iconImage = getIconOfExtension(fileExt);

	        		html += '  <div class="img_file_box">';
	            	html += '  		<div class="img_file">';
	        		html += '  			<a href="#">';
	                if(fileExt == "png" || fileExt == "jpg" || fileExt == "gif" || fileExt == "jpeg") {
	                	html += '    <a href="#" onclick="popupImageView(\'/service/imageDownload.do?fileId='+dbFileList[i].fileId+'&fileOrder='+dbFileList[i].fileOrder+'\');">';
	                	html += '    			<img src="/service/imageDownload.do?fileId='+dbFileList[i].fileId+'&fileOrder='+dbFileList[i].fileOrder+'" width="128px;" height="128px;">';
	                	html += '    </a>';
	                }else {
	                	html += '  				<a href="#" onclick="javascript:fileDownload(\''+dbFileList[i].fileId+'\',\''+dbFileList[i].fileOrder+'\')"><img src="/images/ico/'+iconImage+'"></a>';	
	                }
	        		
	        		html += '  			</a>';
	        		html += '  			<div class="img_file_del_box">';
	        		html += '  				<i class="fas fa-times-circle" onclick="javascript:removeFileDataTempFunc(this, '+fileData+', \''+dbFieldId+'\')"></i>';
	        		html += '  			</div>';
	        		html += '  		</div>';
	        		html += '  		<div class="img_text" id="fileList">';
	        		html += '  			<a href="#" onclick="javascript:fileDownload(\''+dbFileList[i].fileId+'\',\''+dbFileList[i].fileOrder+'\')">' + "(" + dbFileList[i].createTime + ")" + dbFileList[i].originalFileName + '</a>';
	        		html += '  		</div>';
	        		html += '  </div>';	        		
                    $(attachFiles).append(html);
                }
            }
        }
        
        if(rowFileList != null) {
	        // 업로드전 저장된 파일리스트 생성
	        var keyArray2 = Object.keys(rowFileList);
	        var filesTempArrLen = keyArray2.length;
	        
	        filesTempArr = [];
	        if(filesTempArrLen > 0) {
	            filesTempArr = rowFileList;

	            for(var i=0; i<filesTempArrLen; i++) {
	            	var key2 = keyArray2[i];
            		if(key2.indexOf(dbFieldId) == 0) {
    	                var fileExt = getExtensionOfFilename(rowFileList[key2].name);
    	                var iconImage = getIconOfExtension(fileExt);
    	                
		                var html = '';
		        		html += '  <div class="img_file_box">';
		            	html += '  		<div class="img_file">';
		        		html += '  			<a href="#">';
		        		html += '  				<img src="/images/ico/'+iconImage+'">';
		        		html += '  			</a>';
		        		html += '  			<div class="img_file_del_box">';
		        		html += '  				<i class="fas fa-times-circle" onclick="javascript:removeFileItemFunc(this, \''+key2+'\')"></i>';
		        		html += '  			</div>';
		        		html += '  		</div>';
		        		html += '  		<div class="img_text" id="fileList">';
		        		html += '  			<a href="#">' + rowFileList[key2].name + '</a>';
		        		html += '  		</div>';
		        		html += '  </div>';
		        		
		        		$(attachFiles).append(html);
            		}
	            }
	        }
        }
    }
    
    
    /**
     * 파일(업로드전) 삭제
     * 배열(filesTempArr <- 리스트(1 row)에 대한 파일목록)에 정보만 저장된 상태,  저장된 정보 삭제, 리스트 삭제처리
     * @param obj, fileNmae
     */
    removeFileItemFunc = function(obj, key) {
    	cbConfirm("삭제", "파일(업로드전)을 삭제하시겠습니까?", 
    			function(result){
					if(result) {
			            delete filesTempArr[key];
			            $(obj).parent().parent().parent().remove();
			            //$(obj).closest("div").remove();
			            $('.modal_box').hide();
        			}else {
        				return;
        			}
                }
        );
    }

    
    /**  
     * DB에 저장되어 있는 파일 삭제하기 위해 삭제정보 저장(임시) 
     * 리스트 담기전(removeFilesTempObject 파일정보 배열)
     * 리스트 담기를 안할경우 초기화됨 
     */
    removeFileDataTempFunc = function(obj, fileObj, dbFieldId) {
    	cbConfirm("삭제", "파일(저장된)을 삭제하시겠습니까?", 
    			function(result){
					if(result) {
			            var delFile = {
			                    "companyCode" : fileObj.companyCode,
			                    "dbFieldId" : dbFieldId,
			                    "fileId" : fileObj.fileId,
			                    "fileOrder" : fileObj.fileOrder+""
			                };
			            removeFilesTempObject.push(delFile);
			            
			            $(obj).parent().parent().parent().remove();
//			            $(obj).closest("div").remove();
			            $('.modal_box').hide();
        			}else {
        				return;
        			}
                }
        );
    }
    
    /**  
     * DB에 저장되어 있는 파일 삭제하기 위해 삭제정보 저장 
     * 리스트 담기 후(removeFilesObject 파일정보 배열)
     * 리스트 담기 후 임시저장(removeFilesTempObject)된 파일정보 removeFilesObject 배열에 저장   
     */
    removeFileDataFunc = function() {
    	if(removeFilesTempObject != null) {
            for(var i=0; i<removeFilesTempObject.length; i++) {
                removeFilesObject.push(removeFilesTempObject[i]);
            }
    	}
    }
    
    /**  
     * 파일 다운로드
     */
    /*
    fileDownload = function(fileObj) {
        $('#fileDownloadForm #companyCode').val(fileObj.companyCode);
        $('#fileDownloadForm #fileId').val(fileObj.fileId);
        $('#fileDownloadForm #fileOrder').val(fileObj.fileOrder);
        $('#fileDownloadForm').submit();
    }

    
    fileDownload = function(fileId, fileOrder) {
        //$('#fileDownloadForm #companyCode').val(companyCode);
        $('#fileDownloadForm #fileId').val(fileId);
        $('#fileDownloadForm #fileOrder').val(fileOrder);
        $('#fileDownloadForm').submit();
    }
     */
    
	jQuery.download = function(url, data, method){
        // url과 data를 입력받음
        if( url && data ){ 
            // data 는  string 또는 array/object 를 파라미터로 받는다.
            data = typeof data == 'string' ? data : jQuery.param(data);
            // 파라미터를 form의  input으로 만든다.
            var inputs = '';
            jQuery.each(data.split('&'), function(){ 
                var pair = this.split('=');
                inputs += '<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />'; 
            });
            // request를 보낸다.
            jQuery('<form action="'+ url +'" method="post">' + inputs + '</form>').appendTo('body').submit().remove();
        };
    };
    
    fileDownload = function(fileId, fileOrder) {
        $.download("/service/downloadFile.do", "fileId=" + fileId + "&fileOrder=" + fileOrder, "post");
    }    
    
    commFileDownload = function(fileId, fileOrder) {
        $.download("/notice/filedown.do", "fileId=" + fileId + "&fileOrder=" + fileOrder, "post");
    }
    
    
    /**  
     * 파일 업로드 저장용 배열
     */
    getFileTempArr = function() {
        return filesTempArr;
    }
    
    setFileTempArr = function(file) {
    	filesTempArr.push(file);
    }

    clearFilesTempArr = function() {
    	filesTempArr = [];
    }    
    
    /**  
     * 파일 삭제 저장용 배열
     */
    getRemoveFilesObject = function() {
        return removeFilesObject;
    }

    /**  
     * 파일 삭제 저장용 배열 초기화
     */
    clearRemoveFilesTempObject = function() {
        removeFilesTempObject = [];
    }
    
    /**  
     * 파일 삭제 저장용 배열
     */    
    getRemoveFilesTempObject = function() {
    	return removeFilesTempObject;
    }
    
    /**  
     * 파일 삭제 배열 초기화
     */
    clearRemoveFilesObject = function() {
    	removeFilesObject = [];
    }
    
    /**  
     * 파일 관련 배열 및 리스트 초기화
     */
    initFileLists = function() {
        filesTempArr = [];
        removeFilesTempObject = [];
        //removeFilesObject = [];
    }

    clearFileListHtml = function(fileListId) {
    	if(!isEmpty(fileListId) && fileListId.length>0) {
    		for(i=0; i<fileListId.length; i++) {
    	    	var attachFiles = $("#render [data-field='"+fileListId[i]+"']").parent().parent().parent().parent().find('#attachFiles');
    	        $(attachFiles).html('');    			
    		}
    	}
    }
    
    /**  
     * 버튼 클릭시 이벤트
     */
    retrieve = function(buttonId) {
        switch(buttonId){
            case "SEARCH" :
                //alert("조회버튼을 클릭했습니다.");
                ajaxListData();
                break;
            case "INSERT" :
                //alert("저장버튼을 클릭했습니다.");
                fnButtonClick(buttonId.toLowerCase(), "저장");
                //fnInsertButtonClick();
                break;
            case "UPDATE" :
                //alert("수정버튼을 클릭했습니다.");
            	fnButtonClick(buttonId.toLowerCase(), "수정");
                //fnUpdateButtonClick();
                break;
            case "DELETE" :
                //alert("삭제버튼을 클릭했습니다.");
                fnButtonClick(buttonId.toLowerCase(), "삭제");
                //fnDeleteButtonClick();
                break;
            case "APPLY" :
                //alert("신청버튼을 클릭했습니다.");
                fnButtonClick(buttonId.toLowerCase(), "신청");
                break;
            case "APPLY_CANCEL" :
                //alert("신청취소버튼을 클릭했습니다.");
                fnButtonClick(buttonId.toLowerCase(), "신청취소");
                break;
            case "RECEIVE" :
                //alert("접수버튼을 클릭했습니다.");
                fnButtonClick(buttonId.toLowerCase(), "접수");
                break;
            case "RECEIVE_CANCEL" :
                //alert("접수취소버튼을 클릭했습니다.");
                fnButtonClick(buttonId.toLowerCase(), "접수취소");
                break;
            case "RETURN" :
                //alert("반송버튼을 클릭했습니다.");
                fnButtonClick(buttonId.toLowerCase(), "반송");
                break;
            case "END_CONFIRM" :
                //alert("마감버튼을 클릭했습니다.");
                fnButtonClick(buttonId.toLowerCase(), "마감");
                break;
            case "PAYMENT" :
                //alert("지급버튼을 클릭했습니다.");
                fnButtonClick(buttonId.toLowerCase(), "지급");
                break;
            case "COMPLETE" :
                //alert("확정버튼을 클릭했습니다.");
            	var procName = $('#'+buttonId).attr("codeName");
            	if(isEmpty(procName)) procName = "처리";
                fnButtonClick(buttonId.toLowerCase(), procName);
                break;
            case "SIGN" :
                //alert("날인버튼을 클릭했습니다.");
            	fnButtonClick(buttonId.toLowerCase(), "날인");
                break;
            case "USE_APPROVE" :
                //alert("사용승인버튼을 클릭했습니다.");
                fnButtonClick(buttonId.toLowerCase(), "사용승인");
                break;
            case "APPROVE_CANCEL" :
                //alert("승인취소버튼을 클릭했습니다.");
                fnButtonClick(buttonId.toLowerCase(), "승인취소");
                break;    
            case "RECEIVE_EXCEPTION" :
                //alert("예외접수/반송버튼을 클릭했습니다.");
            	fnButtonClick(buttonId.toLowerCase(), "예외접수/반송");
                break;
            case "FORM_DOWN" :
                //alert("양식다운로드버튼을 클릭했습니다.");
                break;
            case "UPLOAD_FILE" :
                //alert("일괄업로드버튼을 클릭했습니다.");
                break;
            case "EXCEL_DOWN" :
                //alert("엑셀다운로드버튼을 클릭했습니다.");
            	fnButtonClick(buttonId.toLowerCase(), "엑셀다운로드");
                break;
            case "PRINT" :
            	fnButtonClick(buttonId.toLowerCase(), "인쇄");
                break;
            case "CLEAR" :
            	//clearFormFunc();
            	fnButtonClick(buttonId.toLowerCase(), "초기화");
            	break;
            case "CALC" :
            	fnButtonClick(buttonId.toLowerCase(), "계산");
            	break;
            case "PROC_END" :
            	fnButtonClick(buttonId.toLowerCase(), "해지");
            	break;
            case "TOTAL_RETURN" :
                fnButtonClick(buttonId.toLowerCase(), "전액환수");
                break;
            case "PARTIAL_RETURN" :
                fnButtonClick(buttonId.toLowerCase(), "일부환수");
                break;
            case "RETURN_FINISH" :
                fnButtonClick(buttonId.toLowerCase(), "환수조치");
                break;
            case "CHECK_FINISH" :
                fnButtonClick(buttonId.toLowerCase(), "검토완료");
                break;    
            case "RPA_RECEIVE" :
                //alert("RPA접수 버튼을 클릭했습니다."); 
                fnButtonClick(buttonId.toLowerCase(), "RPA접수");
                break;
            case "RPA_RETURN" :
                //alert("RPA보류 버튼 클릭했습니다.");
                fnButtonClick(buttonId.toLowerCase(), "RPA보류");
                break;
            default :
                //alert("기타버튼을 클릭했습니다.");
                break;
        }
    }

	setCommonCode = function(){
		var datas = $('#render select[commclcode]');
		$.each(datas, function(i, e){
			ajaxCommClCodeData(e, "", "");
		});
		
		var datas = $('#render select[commcode]');
		$.each(datas, function(i, e){
			ajaxCommCodeData(e, "", "");
		});

		var datas = $('#render select[bizcode]');
		$.each(datas, function(i, e){
			ajaxBizCodeData(e, "", "");
		});
	}
    
    // selectBox Trigger Template
    /**
     * selectBoxTrigger() 파리미터
     * parentId         -> 이벤트 발생 주체
     * childId          -> 이벤트 전달받는 객체
     * bizDetailCodeId  -> WM_BIZDETAILCODE 테이블의 참조쿼리 ( CODE_ID ) 를 넘긴다.
     * triggerOrder     -> 트리거 발생 순서
     */
    selectBoxTrigger = function (parentId, childId, bizDetailCodeId, triggerOrder ) {
        // triggerOrder 파라미터가 존재할 경우 triggerBindField 멤버변수(Array) 로 push
        // 순서보장은 못함
        if(triggerOrder){
            triggerBindField.push({ 
                id : triggerOrder,
                field : parentId
            });                
        }

        // selectbox change 이벤트 바인딩
        $('#render #'+parentId).on('change', function(){
            
            $('#render #'+childId).find('option').remove();
            var obj = $('#render #'+childId);
            
            // search 조건절일 경우
            if(obj.attr("custom-search")){
                getWhereParamObj(bizDetailCodeId);
                ajaxBizCodeData("", bizDetailCodeId, obj);
                obj.trigger('change');
                
            // update 또는 input 조건일 경우
            }else{
                if(!$('#render #'+parentId).val()){
                    obj.remove('option');
                    obj.trigger('change');
                }
                if(!$('#render #'+parentId).find('option:selected').val()){
                    obj.remove('option');
                    obj.trigger('change');
                }
                
                getWhereParamObj(bizDetailCodeId);
                ajaxBizCodeData("", bizDetailCodeId, obj);
                var detailRowId = $('#builderForm #listRowid').val();
                if(!isEmpty(detailRowId)) {
                    var dataField = $('#render #'+childId).attr("data-field");
                    code = grdList.cells(detailRowId, grdList.getColIndexById(dataField)).getValue();
                    $('#render #'+childId).val(code);
                }
            }
        });
    };    
    
    
    // 문자열 관련
    isEmpty = function(value){ 
        if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ) { 
            return true 
        }else{ 
            return false 
        } 
    }
    
    // 배열 관련
    // return an array of values that match on a certain key
    getValues = function(obj, key) {
        var objects = [];
        for ( var i in obj) {
            if (!obj.hasOwnProperty(i))
                continue;
            if (typeof obj[i] == 'object') {
                objects = objects
                        .concat(getValues(obj[i], key));
            } else if (i == key) {
                obj[i] = obj[i].toUpperCase();
                objects.push(obj[i]);
            }
        }
        return objects;
    }

    // return an array of keys that match on a certain value
    getKeys = function(obj, val) {
        var objects = [];
        for ( var i in obj) {
            if (!obj.hasOwnProperty(i))
                continue;
            if (typeof obj[i] == 'object') {
                objects = objects.concat(getKeys(obj[i], val));
            } else if (obj[i] == val) {
                objects.push(i);
            }
        }
        return objects;
    }
    
    //숫자양식 관련
	commify = function(n) {
		var reg = /(^[+-]?\d+)(\d{3})/;   // 정규식
		n += '';                          // 숫자를 문자열로 변환
		while (reg.test(n))
		 n = n.replace(reg, '$1' + ',' + '$2');
		return n;
	}
    
    
    /**  
     * 오늘 날짜 YYYYMMDD  출력
     */
    getCurrentDateTimes = function(){
        /*날짜 구하기*/
        var date = new Date();

        var year = date.getFullYear();
        var month = date.getMonth()+1
        var day = date.getDate();
        var hours = date.getHours();
        var minutes = date.getMinutes();  // 분
        var seconds = date.getSeconds();  // 초
        
        if(month < 10){
            month = "0"+month;
        }
        if(day < 10){
            day = "0"+day;
        }
     
        return year+""+month+""+day+""+hours+""+minutes+""+seconds;
    }    
    
    /**  
     * 오늘 날짜 YYYY-MM-DD 출력
     */
    getToday = function(delimiter){
        /*날짜 구하기*/
        var date = new Date();

        var year = date.getFullYear();
        var month = date.getMonth()+1
        var day = date.getDate();

        if(month < 10){
            month = "0"+month;
        }
        if(day < 10){
            day = "0"+day;
        }
     
        if(isEmpty(delimiter))
            return year+""+month+""+day;
        else 
            return [year, month, day].join(delimiter);
        
    }
    
    getYear = function(){
    	/*날짜 구하기*/
    	var date = new Date();
    	var year = date.getFullYear();
    	return year;
    }
    
    getMonth = function(){
    	/*날짜 구하기*/
    	var date = new Date();
    	var month = date.getMonth()+1
        if(month < 10){
            month = "0"+month;
        }
    	return month;
    }

    /*getFormatData = function(value, pattern) {
    	  var i = 0,
    	  	  data = value.toString();
    	  return pattern.replace(/#/g, _ => data[i++]);
    }*/
    getFormatData = function getFormatData(value, pattern) {
        var i = 0,
            data = value.toString();
        return pattern.replace(/#/g, function (_) {
          return data[i++];
        });
    };
    
    getAddDate = function(date, nNum, type, delimiter) {
        var d = new Date();
        
        if(date != "") {
        	date = replaceAll(date, ".", "-");
            d = new Date(date);
        }
        
        if (type == "d") {
            d.setDate(d.getDate() + nNum);
        }
        else if (type == "m") {
            d.setMonth(d.getMonth() + nNum);
        }
        else if (type == "y") {
            d.setFullYear(d.getFullYear() + nNum);
        }
     
        yy = d.getFullYear();
        mm = d.getMonth() + 1; mm = (mm < 10) ? '0' + mm : mm;
        dd = d.getDate(); dd = (dd < 10) ? '0' + dd : dd;
     
        if(isEmpty(delimiter))
            return yy+""+mm+""+dd;
        else 
            return [yy, mm, dd].join(delimiter);
    }
    


    
    // 두개의 날짜를 비교하여 차이를 알려준다.
    dateDiff = function(_date1, _date2) {
    	if(_date1.length<10) _date1 = getFormatData(_date1, "####-##-##");
    	if(_date2.length<10) _date2 = getFormatData(_date2, "####-##-##");
    	_date1 = replaceAll(_date1, ".", "-");
    	_date2 = replaceAll(_date2, ".", "-");
    	
    	var diffDate_1 = _date1 instanceof Date ? _date1 :new Date(_date1);
        var diffDate_2 = _date2 instanceof Date ? _date2 :new Date(_date2);
        
        diffDate_1 = new Date(diffDate_1.getFullYear(), diffDate_1.getMonth(), diffDate_1.getDate());
        diffDate_2 = new Date(diffDate_2.getFullYear(), diffDate_2.getMonth(), diffDate_2.getDate());
     
        var diff = Math.abs(diffDate_2.getTime() - diffDate_1.getTime());
        
        diff = Math.ceil(diff / (1000 * 3600 * 24));
     
        return diff;
    }
    
    holidayYn = function(date){
    	
    	var isHoliday = "N";
    	var date = date;
    	var solarHoliday = "0101|0301|0501|0505|0606|0815|1003|1009|1225|";
    	
    	//음력은 계산 어려뭐서 일단 전체 넣음(2021~)
    	var lunarHoliday = "20210211|20210212|20210213|20210519|20210920|20210921|20210922|";   //2021년 음력 휴일
    				     + "20220131|20220201|20220202|20220508|20220909|20220910|20220911|";   //2022년 음력 휴일
    				     + "20230121|20230122|20230123|20230527|20230928|20230929|20230930|";   //2023년 음력 휴일
    				     + "20240209|20240210|20240211|20240515|20240916|20240917|20240918|";   //2024년 음력 휴일
    				     + "20250128|20250129|20250130|20250505|20251005|20251006|20251007|";   //2025년 음력 휴일
    				     + "20260216|20260217|20260218|20260524|20260924|20260925|20260926|";   //2026년 음력 휴일
    				     + "20270206|20270207|20270208|20270513|20270914|20270915|20270916|";   //2027년 음력 휴일
    				     + "20280126|20280127|20280128|20280502|20281002|20281003|20281004|";   //2028년 음력 휴일
    				     + "20290212|20290213|20290214|20290520|20290921|20290922|20290923|";   //2029년 음력 휴일
    	
	    var spcHoliday = "20210816|20211004|20211011|";
    						
		var factoryHoliday = "20210923|20211231|";
    	
    	if(solarHoliday.indexOf(date.substr(4, 4)) > -1){
    		isHoliday = "Y";
    	} else if (lunarHoliday.indexOf(date) > -1){
    		isHoliday = "Y";
    	} else if (spcHoliday.indexOf(date) > -1){
    		isHoliday = "Y";
    	} else if (factoryHoliday.indexOf(date) > -1){
    		isHoliday = "Y";
    	}
    	
    	return isHoliday;
    	
    }

    
    htmlToJson = function(obj) {
        var jsonObj = JSON.parse(obj, function (key, value){
            if (value && typeof value === "string" && value.substr(0,8) === "function") {
                var startBody = value.indexOf('{') + 1;
                var endBody = value.lastIndexOf('}');
                var startArgs = value.indexOf('(') + 1;
                var endArgs = value.indexOf(')');

                return new Function(value.substring(startArgs, endArgs), value.substring(startBody, endBody));
            }
            return value;           
        });
        return jsonObj;           
    }
    
    tagReplaceAll = function(e) {
        return e.replace(/select-custom/g, "select"); 
    }
    
    tagReverseAll = function(e) {
        return e.replace(/select/g, "select-custom"); 
    }
    
    replaceAll = function(str, searchStr, replaceStr) {
        return str.split(searchStr).join(replaceStr);
    }
    
    
    randomString = function() {
        var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
        var string_length = 12;
        var randomstring = '';
        for (var i=0; i<string_length; i++) {
            var rnum = Math.floor(Math.random() * chars.length);
            randomstring += chars.substring(rnum, rnum + 1);
        }
        
        return randomstring;
    }
    
    itemDisabledFnc = function() {
	    $('select,input').each(function(k,v){
	        var disabled_check = this.disabled;
	        var readonly_check = this.readOnly;
            
            if(disabled_check || readonly_check){
				$(this).closest('.input_box').addClass('disabled');
            } else {
				$(this).closest('.input_box').removeClass('disabled');
            } 
	    })
	}
    
    goTotalRequestList = function(){
    	//var paramData = sStorage.getItem('paramData');    
    	
        // 파라미터를 form의  input으로 만든다.
        //var inputs = '<input type="hidden" name="paramData" value="'+ paramData +'" />';
        
        // request를 보낸다.
        jQuery('<form action="/user/service/approval/totalRequestList.do" method="post"></form>').appendTo('body').submit().remove();
    }

    $(document).on('click','ul.progressbar li.active .popover_link',function(i){
    	$('.gpopover').remove();

    	var title = $(this).attr('data-title');
    	var team = $(this).attr('data-team');
    	var time = $(this).attr('data-time'); 

    	var div = ''
    	+'	<div id="popover-content" class="gpopover">'
    	+'		<div class="pop_header">'+ title +'</div>'
    	+'		<div class="pop_body">'
    	+'			<div>'+team+'</div>'
    	+'			<div>'+time+'</div>'
    	+'		</div>'
    	+'	</div>';

    	$(this).closest('li').append(div);

    	$('body').click(function(e){
    		var tooltip_checker = $(e.toElement).closest('.gpopover');
    		if(tooltip_checker.attr('class') !== "gpopover"){
    			$('.gpopover').remove();
    		}
    	})
    })
    
    String.prototype.trim = function() { 
    	return this.replace(/^\s+|\s+$/g,""); 
    }
    
    $(document).on('click','.far.fa-calendar-alt',function(i){
    	$(this).parent().find("input").trigger("select");
    });
    
    
    setCookie = function(cookieName, cookieValue, cookieExpire, cookiePath, cookieDomain, cookieSecure){
    	var date = new Date();
    	date.setTime(date.getTime() + cookieExpire*24*60*60*1000)
	    var cookieText=escape(cookieName)+'='+escape(cookieValue);
	    cookieText+=(cookieExpire ? '; EXPIRES='+date.toGMTString() : '');
	    cookieText+=(cookiePath ? '; PATH='+cookiePath : '');
	    cookieText+=(cookieDomain ? '; DOMAIN='+cookieDomain : '');
	    cookieText+=(cookieSecure ? '; SECURE' : '');
	    document.cookie=cookieText;
	}
	 
	getCookie = function(cookieName){
	    var cookieValue=null;
	    if(document.cookie){
	        var array=document.cookie.split((escape(cookieName)+'='));
	        if(array.length >= 2){
	            var arraySub=array[1].split(';');
	            cookieValue=unescape(arraySub[0]);
	        }
	    }
	    return cookieValue;
	}
	 
	deleteCookie = function(cookieName){
	    var temp=getCookie(cookieName);
	    if(temp){
	        setCookie(cookieName,temp,(new Date(1)));
	    }
	}    
    
});