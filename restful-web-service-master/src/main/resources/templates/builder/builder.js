$(function(){


isEmpty = function(value){ 
    if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ) { 
        return true 
    }else{ 
        return false 
    } 
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

hideThInput = function() {
    $('.hdr tr:eq(0) th').each(function(k,v){                    
        var display= $(this).css('display');                
        if( display == 'none' ){
            $('.hdr tr:eq(2) td').eq(k).css('display','none');
        }
    })
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

tagReplaceAll = function(e) {
    return e.replace(/select-custom/g, "select"); 
}

tagReverseAll = function(e) {
    return e.replace(/select/g, "select-custom"); 
}

replaceAll = function(str, searchStr, replaceStr) {
    return str.split(searchStr).join(replaceStr);
}


/* custom override alert */
function alert(confirmMessage){
    var alertDiv = ''
        + '<div class="modal modal_confirm">'
        + '<div class="modal_header">알림<span class="modal_close">&times;</span>' + '</div>'
        + '<div class="modal_body">' + confirmMessage + '</div>'
        + '<div class="modal_footer">'
        + '<button class="trans_btn" onclick="javascript:closeModal(this);">확인</button>'
        + '</div>';
    $('.modal_box').show().empty().append(alertDiv).find('.modal');
}


/* custom callback alert */
function cbAlert(confirmMessage, callbackFunc){
    var alertDiv = ''
        + '<div class="modal modal_confirm">'
        + '<div class="modal_header">알림<span class="modal_close">&times;</span>' + '</div>'
        + '<div class="modal_body">' + confirmMessage + '</div>'
        + '<div class="modal_footer">'
        + '<button class="trans_btn" onclick="javascript:alertCbFunc(this);">확인</button>'
        + '</div>';

    alertCbFunc = function(obj){
        callbackFunc();
        closeModal(obj);
        alertCbFunc = null;
    }

    $('.modal_box').show().empty().append(alertDiv).find('.modal');  
}



    // Html 코드를 Json으로 변환
    settingJsonFormatFunc = function() {
        var html = $('#htmlCode').val();
        $('#html-output').html(html);
        
        var json = '';
        try {
            json = formatJSON(toTransform($('#html-output').children())); 
        } catch(e) {
            json = '';
        }
        return json;
    }
    
    
    // 아이템 템플릿 관리, 미리보기 버튼 클릭 시 Html Code Validation 후 preview
    previewItemTmplFunc = function() {
        var jsonCode = settingJsonFormatFunc();
        if(jsonCode == '') {
            alert('<spring:message code="hbsl.admin.message.item.pattern.fail"/>');
        } else {
            previewFunc();
        }            
    }
    
    
    // 아이템 템플릿 관리, 아이템 관리 미리보기
    previewFunc = function() {
        var json = settingJsonFormatFunc();
        
        try {
            jsonData = htmlToJson(tagReplaceAll(json));
        } catch(e){
            jsonData = undefined;
        }

        $("#output").empty();
        $("#output").json2html({}, jsonData);
    }
    
    
    // 공통 - 아이템 템플릿 json data를 html 코드로 변경 후 htmlCode에 넣기
    settingItemTmplFunc = function() {
        var jsonData = $('#jsonCode').val();

        try {
            json = JSON.parse(jsonData);
        } catch(e){
            json = undefined;
            alert("e1 : " + e.message);
        }

        var $obj = $('<div/>');
        try {
            toTemplate(json, $obj);
        } catch(e){
            alert("e2 : " + e.message);
            _error = true;
        }

        $('#htmlCode').val($obj.html());

    }
    
    // 공통 - html 트래킹 후 속성창에 값 넣기
    setAttributeFunc = function() {

        $active_component = $('#html-output');
        
        // class에 valtype을 포함하고 있는 모든 컨포넌트 리스트업
        //var valtypes = $active_component.find(".valtype");
        var component = $active_component.find("[component]");
        
        // 컨포넌트에서 관리하는 attribute 정의
        var attributes = ['id', 'name', 'class', 'style', 'placeholder', 'maxlength', 'value'];
        
        $.each(component, function(i, e){
            var valID = $(e).attr("component-type");  
            var val;
            
            if(valID !== '' && typeof valID !== 'undefined') {
                // component-type은 label, helptext에만 관여함
                val = $(e).text();
                $('#' + $('[control="' + valID + '"]').attr('id')).val(val);
            } else {
                // label, helptext를 제외한 input, textarea 등 처리
                $.each(attributes, function(i, element){    
                    val = $(e).attr(element);

                    attrID = '#' + $('[control="' + element + '"]').attr('id');
                    if(typeof val !== 'undefined') { 
                        $(attrID).val(val);    
                    } else {
                        $(attrID).val('');
                    }
                });
            }
        });
    }
    
    
    // 회사별 아이템 템플릿 목록 조회
    getItemTmplByCompanyFunc = function() {
         
        $('#itemTmplId').find('option').each(function(){
            $(this).remove(); 
        });

        var companyCode = $("#companyCode option:selected").val();
        $.ajax({
            method: "POST", 
            url: "/admin/builder/item/getItemTmplAll.do", 
            dataType: "json",
            data: {
                "companyCode": companyCode   
            }, 
            success: function(response) {
                var itemTmplList = response.itemTmplList;

                if(itemTmplList == null) {
                    itemTmplList = 0;
                }
                $('#itemTmplId').append("<option value=''>Select...</option>");
                for(var i=0; i<itemTmplList.length; i++) {
                    var option = $("<option/>").attr("value", itemTmplList[i].itemTmplId).attr("type11", itemTmplList[i].itemType).text(itemTmplList[i].itemTmplName);
                    $('#itemTmplId').append(option);
                }                    
            }, 
            error: function(a) {
                alert(a.message);
            }
        });  
    }
    
    // 아이템 관리 - 속성창 display option
    toggleAttributeFunc = function(tag) {
        if(tag == 'SELECT') {
            $("#input_attr").addClass("hide-soft");
            $("#select_attr").removeClass("hide-soft");
            $("#check_attr").addClass("hide-soft");
        } else if(tag == 'RADIO' || tag == 'CHECKBOX') {
            $("#input_attr").addClass("hide-soft");
            $("#select_attr").addClass("hide-soft");
            $("#check_attr").removeClass("hide-soft");;
        } else {
            $("#input_attr").removeClass("hide-soft");
            $("#select_attr").addClass("hide-soft");
            $("#check_attr").addClass("hide-soft");
        }
    }
    
    // 아이템 관리 - 등록화면 Select option 추가
    addSelectItemFunc = function() {
        $('#custom_select_option').tmpl().appendTo("#custom_select_attr");
    }
    
    // 아이템 관리 - 등록화면 Checkbox, Radio button option 추가
    addRadioItemFunc = function() {
        $('#custom_select_option').tmpl().appendTo("#custom_check_attr");
    }
    
    // 아이템 관리 - 추가된 option 삭제
    removeItemFunc = function(obj) {
        $(obj).parents("tr").remove();
    }
    
    // 아이템 관리 - 아이템 템플릿 format 가져와 보여주기
    getTemplateFormat = function() {

        var itemTmplId = $("#itemTmplId option:selected").val();
        
        // toggle
        var itemTmplTag = $("#itemTmplId option:selected").attr('type');
        
        if(itemTmplTag == 'SELECT') {
        	$("#type_code").prop("checked", true);
        } else {
        	$("#type_code").prop("checked", false);
        }

        $.ajax({
            method: "POST", 
            url: '/admin/builder/item/getItemTmplFormat.do', 
            dataType: "json",
            data: {
                "itemTmplId": itemTmplId   
            }, 
            success: function(response) {
                var itemTmplInfo = response.itemTmplInfo;

                if(itemTmplInfo != null) {                   
                    // 아이템 템플릿 json 데이터를 jsonCode에 바인딩                        
                    $('#jsonCode').empty().val(itemTmplInfo.json);
                    
                    // json data를 html로 컨버팅 후 html-output에 바인딩
                    settingItemTmplFunc();
                    
                    // html-output 바인딩 후 미리보기
                    previewFunc();
                } else {
                    // 아이템 템플릿 정보가 없을 경우 미리보기/HTML코드 초기화
                    $("#output").empty();
                    $("#htmlCode").val('');
                    $("#jsonCode").val('');
                }
                
                // html코드 트래킹하면서 attibute 속성에 값 넣기
                setAttributeFunc();
                toggleAttributeFunc(itemTmplTag);
            }, 
            error: function(a) {
                alert(a.message);
            }
        });   
    } 
    

    // 아이템 관리 - 설정된 속성을 html 코드에 바인딩하기
    saveAttributeFunc = function() {
        
        var $active_component =  $('#html-output').html($('#htmlCode').val());
        
        var component = $active_component.find("[component]");
        
        // attribute를 적용할 대상 element
        var tags = ['input', 'select-custom', 'textarea'];
        
        // 아이템 템플릿 타입이 SELECT, CHECKBOX, RADIO BUTTON일 경우 VALUE/OPTION 설정 화면을 보여주기 위한 flag
        var itemTmplTag = $("#itemTmplId option:selected").attr('type');
        
        // attribute 입력폼 목록
        var inputs = $(".attributes input");
        
        $(inputs.get().reverse()).each(function(i, e){
            
            var custom = $(e).attr("control");

            var label = $active_component.find('[component-type="' + custom + '"]');

            // LABEL, HELPTEXT 적용
            if(label != '' && typeof label != 'undefined') {
                $(label).text($(e).val());
            }
            
            var tagName;
            $.each(component, function(i, el){
                var tmpTagName = el.tagName.toLowerCase();

                tags.forEach(function(tag){
                    if(tmpTagName == tag) {
                        tagName = tag;
                        return true;
                    }
                });
            });

            var element = $active_component.find(tagName);
            
            if(custom == 'label') { // select-custom일 경우에만 label 속성 추가
                if(tagName == 'select-custom') {
                    $(element).attr("label", $(e).val());    
                }
            } else if(custom == "name") { // id, name 속성 추가
                var tmp = $active_component.find(".form_radio_box").html();
                if(typeof tmp == 'undefined') {
                     
                    if($(e).val() != '') {
                        $(element).attr("id", $(e).val());
                        $(element).attr("name", $(e).val());    
                    } else {
                        $(element).removeAttr("id");
                        $(element).removeAttr("name");
                    }
                }     
            } else if(custom == "class") { // class 속성 추가
                if($(e).val() != '') {
                    $(element).attr("class", $(e).val());    
                } else {
                    $(element).removeAttr("class");
                }
            } else if(custom == "style") { // style 속성 추가
                if($(e).val() != '') {
                    $(element).attr("style", $(e).val());    
                } else {
                    $(element).removeAttr("style");
                }
            } else if(custom == "placeholder"){ // placeholder 속성 추가
                if($(e).val() != '') {
                    $(element).attr("placeholder", $(e).val());
                } else {
                    $(element).removeAttr("placeholder");
                }                  
            } else if(custom == "maxlength") { // maxlength 속성 추가
                if($(e).val() != '') {
                    $(element).attr("maxlength", $(e).val());
                } else {
                    $(element).removeAttr("maxlength");
                }                   
            } else if(custom == "value") { // value 속성 추가
                if(tagName == 'textarea') {
                    if($(e).val() != '') {
                        $(element).text($(e).val()); 
                    } else {
                        $(element).text(''); 
                    }
                } else {
                    var tmp = $active_component.find(".form_radio_box").html();
                    if(typeof tmp == 'undefined') {
                        if($(e).val() !== '') {
                            $(element).attr("value", $(e).val());
                        } else {
                            $(element).removeAttr("value");
                        }    
                    }
                }
            } else if(custom == "required") {
                attrID = $('[control="' + custom + '"]').attr('id');
                if($('input:checkbox[id="' + attrID + '"]').is(":checked") == true) {
                    $(element).attr("required", true);
                } else {
                    $(element).attr("required", false);
                }                   
            } else if(custom == "readonly") {
                attrID = $('[control="' + custom + '"]').attr('id');
                if($('input:checkbox[id="' + attrID + '"]').is(":checked") == true) {
                    $(element).attr("readonly", true);
                } else {
                    $(element).attr("readonly", false);
                }                   
            } else if(tagName == 'select-custom') { // selectbox option 추가
                $(element).empty();
                 
                var radioVal = $('input[name="type"]:checked').val();
                 
                if(radioVal == 'CODE') {
                    var commonCode = $("#commonCodeList option:selected").val();
                    
                    var commonCodeType = $("#commonCodeType option:selected").val();
                    if(commonCodeType == 'COMMON_CL_CODE') {
                        $(element).removeAttr("bizCode");
                        $(element).attr("commclcode", commonCode);
                    } else if(commonCodeType == 'COMMON_CODE') {
                        $(element).removeAttr("bizCode");
                        $(element).attr("commcode", commonCode);
                    } else {
                        $(element).removeAttr("commcode");
                        $(element).attr("bizcode", commonCode);
                    }
                } else {
                    $(element).removeAttr("commclcode");
                    $(element).removeAttr("commcode");
                    $(element).removeAttr("bizcode");
                    $('#custom_select_attr tr').each(function(i, ele) {
                        var text = $(ele).find("#option-text").val();
                        var value = $(ele).find("#option-value").val();
             
                        if(text != '' && value != '') {
                            var option = $("<option/>").attr("value", value).text(text);
                            $(element).append(option);
                        } 
                    });
                }
            } else if(itemTmplTag == "CHECKBOX") {
                element = $active_component.find('.form_radio_box');

                if(element !== '' && typeof element !== 'undefined') {
                    element.empty();
                }
                 
                nameAttrID  = $('[control="name"]').attr('id');
                labelAttrID = $('[control="label"]').attr('id');
                 
                $('#custom_check_attr tr').each(function(i, ele) {
                    var text = $(ele).find("#option-text").val();
                    var value = $(ele).find("#option-value").val();
                     
                    var name  = $('#' + nameAttrID).val() + '_' + i;
                    var label = $('#' + labelAttrID).val();

                    if(text != '' && value != '') {
                        $(element).append('\n<input type="checkbox" id="' + name + '" name="' + name + '" label="' + label + '" value="' + value + '" component><label for="' + name + '" style="padding: 5px 15px 0 0;">' + text + '</label>');
                    }
                });
            } else if(itemTmplTag === "RADIO") {
                element = $active_component.find('.form_radio_box');

                if(element !== '' && typeof element !== 'undefined') {
                    element.empty();
                }
                 
                // 같은 화면에 radio 그룹이 여러개일 경우 아이디가 중복되면 안되기 때문에 난수로 아이디 생성 
                //var id = randomString();
                nameAttrID  = $('[control="name"]').attr('id');
                
                $('#custom_check_attr tr').each(function(i, ele) {
                    var text = $(ele).find("#option-text").val();
                    var value = $(ele).find("#option-value").val();
                    
                    var name  = $('#' + nameAttrID).val();
                     
                    if(text != '' && value != '') {
                        $(element).append('<div class="reserve_checkbox_box"><input type="radio" id="' + name + '_' + (i + 1) + '" name="' + name + '" value="' + value + '" component><label for="' + name + '_' + (i + 1) + '">' + text + '</label></div>');
                    }
                });
            }
        });
        
        $("#htmlCode").empty().val($('#html-output').html().replace(/\n\ \ \ \ \ \ \ \ \ \ \ \ /g,"\n"));
        
        previewFunc();
    }
    
    
    
    ///////// Builder
    // Step2. 필드설정 - 보기
    showFieldItem = function() {
        $('#fieldItemList .basic_field_tr').show();
        $('#fieldShowBtn').hide();
        $('#fieldHideBtn').show();
    }
    
    // Step2. 필드설정 - 숨기기
    hideFieldItem = function() {
    	$('#fieldItemList .basic_field_tr').hide();
    	$('#fieldShowBtn').show();
    	$('#fieldHideBtn').hide();
    }
    
    // Step3. 아이템 설정 - SearchType 설정    
    $("#attrSearchAt").on("change", function(){
		if($(this).is(":checked") == true) {
			$("#attrSearchType").show();
		} else {
			$("#attrSearchType").hide();
		}
	});
    
    // Step2. 필드설정 - 행추가
    addFieldItem = function() {
        	
    	$('#data_field_form').tmpl().appendTo("#fieldItemList");
        var inputs = $('.autocomplete');
        
        $.each(inputs, function(i, e){
            $(e).autocomplete({
                source: function(request, response) { 
                    $.ajax({ 
                        type: "post",
                        url: "/admin/builder/item/getFormFieldAutoComplete.do", 
                        dataType: "json", 
                        data: {
                            fieldName : request.term
                        },
                        success: function(data) { 
                            response( 
                                    $.map(data.wmFormFieldInfoList, function(item) { 
                                        return {
                                            label: item.fieldName,
                                            fieldId: item.fieldId,
                                            fieldType: item.fieldType,
                                            fieldLength: item.fieldLength,
                                            fieldDefault: item.fieldDefault,
                                            fieldPkAt: item.fieldPkAt,
                                            fieldNullableAt: item.fieldNullableAt,
                                            fieldUseAt: item.fieldUseAt,
                                            fieldOrder: item.fieldOrder
                                        } 
                                    }) 
                                );                         
                        } 
                    });
                },
                minLength: 2,
                select: function(event, ui) {
                    var $tr = $(this).closest('tr');
                    var index = $tr.index() + 1;
                    var currentAttr = $('#attributeTable tr');
                    $(currentAttr[index]).find('#fieldId').val(ui.item.fieldId);
                    $(currentAttr[index]).find('#fieldType').val(ui.item.fieldType);
                    $(currentAttr[index]).find('#fieldLength').val(ui.item.fieldLength);
                    $(currentAttr[index]).find('#fieldDefault').val(ui.item.fieldDefault);
                    $(currentAttr[index]).find('#fieldPkAt').val(ui.item.fieldPkAt);
                    $(currentAttr[index]).find('#fieldNullableAt').val(ui.item.fieldNullableAt);
                    $(currentAttr[index]).find('#useAt').val(ui.item.fieldUseAt);
                    $(currentAttr[index]).find('#sortOrder').val(ui.item.fieldOrder);
                }
            });
        });
    }
    
    settingJsonFormatForBuilderFunc = function(obj) {
        var oHtml = obj.html();
        
        $('#html-output').empty();
        $('#html-output').html(tagReverseAll(oHtml));
        return formatJSON(toTransform($('#html-output').children()));
    }
    
    
    $('#commonCodeType').on('change', function(){
        var commonCodeType = $("#commonCodeType option:selected").val();
        
        var url = '';
        if(commonCodeType == 'COMMON_CL_CODE') {
            url = '/admin/builder/item/getCommBizClCodeList.do'; 
        } else if(commonCodeType == 'COMMON_CODE') {
            url = '/admin/builder/item/getCommBizCodeList.do'; 
        } else {
            url = '/common/getClCodeByBizCodeList.do'; 
        }
        
        $.ajax({
            method: "POST", 
            url: url, 
            dataType: "json",
            data: {
                "clCode": commonCodeType   
            }, 
            success: function(response) {
                var cmmnBizCodeList = response.cmmnBizCodeList;

                if(cmmnBizCodeList == null) {
                    cmmnBizCodeList = 0;
                } 
                
                $('#commonCodeList').empty();
                for(var i=0; i<cmmnBizCodeList.length; i++) {
                    $('#commonCodeList').append("<option value='" + cmmnBizCodeList[i].codeId + "'>" + cmmnBizCodeList[i].codeIdName + "</option>");
                }
            }, 
            error: function(a) {
                alert(a.message);
            }
        });  
    });
    
    
    // Step1. 업무코드 입력 시 테이블명 항목에 바인딩 
    $('#bizCode').focusout(function(){
        $('#preDbTable').empty().val($("#companyCode option:selected").val());
        $('#midDbTable').empty().val($('#bizCode').val());
    });
    
    
    // Step1. 테이블명 입력 시 dbTable 항목에  바인딩
    generatorDbTableName = function() {
        var tableName = '';
        $('#dbTable').val(tableName.concat($('#preDbTable').val(), "_", $('#midDbTable').val(), "_", $('#postDbTable').val()));
    }
    
    
    // Step2. 필드설정 - Enter key 방지
    $("#attributeTable").keydown(function(event) {
        if (event.keyCode == 13) {
            return false;
        }
    });
 
    
    // Step2. 필드설정 - 행삭제
    $('#fieldItemList').on("click", "button", function() {
        $(this).closest("tr").remove()
    });
    
	SortByName = function(a, b){
		  var aName = a[1].toLowerCase();
		  var bName = b[1].toLowerCase(); 
		  return ((aName < bName) ? -1 : ((aName > bName) ? 1 : 0));
	}    
    
    // Step3. Step2에서 정의한 data-filed를 가져와 item에 매핑할 selectbox 생성
    createTableToSelectBoxFunc = function() {
        var fieldItemTable = document.getElementById('fieldItemList');
        
        var rowCount = $('#fieldItemList tr').length;

        var fieldArray = [];
        for(var i=0; i<rowCount; i++){
        	var input = fieldItemTable.rows.item(i).getElementsByTagName("input");
        	fieldArray[i] = [input.fieldId.value, input.fieldName.value];
        }
        fieldArray.sort(SortByName);
        
        var selectBox;
        var fieldCount = fieldArray.length;
        
        $('#attrDataField').empty();
        $('#attrDataField').append($("<option/>").attr("value", "").text("선택하세요."));
        for(var i=0; i<fieldCount; i++){
            var input = fieldItemTable.rows.item(i).getElementsByTagName("input");
            var option = $("<option/>").attr("value", fieldArray[i][0]).text(fieldArray[i][1]);
            $('#attrDataField').append(option);
        } 
    }
    
    
    // Step3. 아이템 타입 조회
    getItemListAllFunc = function() {
        // Item List
        var companyCode = $("#companyCode option:selected").val();
        $.ajax({
            method: "POST", 
            url: "/admin/builder/item/getItemTmplAll.do", 
            dataType: "json",
            data: {
                "companyCode": companyCode   
            }, 
            success: function(response) {
                var itemTmplList = response.itemTmplList;

                if(itemTmplList == null) {
                    itemTmplList = 0;
                }
                
                $('#searchcondition').empty(); // itemTmplId
                $('#searchcondition').append("<option value=''>Select...</option>");
                for(var i=0; i<itemTmplList.length; i++) {
                    $('#searchcondition').append("<option value='" + itemTmplList[i].itemTmplId + "' type='" + itemTmplList[i].itemType + "'>" + itemTmplList[i].itemTmplName + "</option>");
                }
            }, 
            error: function(a) {
                alert(a.message);
            }
        }); 
    }
    
    
    // Step3. 아이템 조회
    searchItemListFunc = function() {
        
        var companyCode = $("#companyCode option:selected").val();
        var searchcondition = $("#searchcondition option:selected").val();
        var searchkeyword = $("#searchkeyword").val();
        
        alert(1);
        
        $.ajax({
            method: "POST", 
            url: "/admin/builder/item/getItemByTmpl.do", 
            dataType: "json",
            data: {
                "companyCode": companyCode,
                "searchcondition": searchcondition,
                "searchkeyword": searchkeyword   
            }, 
            success: function(response) {
                var itemList = response.itemList;
                
                $('#templateByItems').empty();

                if(itemList.length == 0) {
                    itemList = 0;
                    $('#templateByItems').append('<div class="col col-12 no_items">no items</div>');
                }
                
                var html = "";
                var tmpId;
                
                for(var i=0; i<itemList.length; i++) {
                    tmpId = "tmpId_" + i;
                    
                    var icon = '';
                    if(itemList[i].itemType == 'INPUT') {
                        icon = '<img class="svg_icon" src="/images/icon_input.svg" />';
                    } else if(itemList[i].itemType == 'CHECKBOX') {
                        icon = '<img class="svg_icon" src="/images/icon_checkbox.svg" />';
                    } else if(itemList[i].itemType == 'MULTICHECKBOX') {
                        icon = '<img class="svg_icon" src="/images/icon_multicheck.svg" />';
                    } else if(itemList[i].itemType == 'FILE') {
                        icon = '<img class="svg_icon" src="/images/icon_file.svg" />';
                    } else if(itemList[i].itemType == 'CALENDAR') {
                        icon = '<img class="svg_icon" src="/images/icon_calendar.svg" />';
                    } else if(itemList[i].itemType == 'PASSWORD') {
                        icon = '<img class="svg_icon" src="/images/icon_password.svg" />';
                    } else if(itemList[i].itemType == 'MULTIRADIO') {
                        icon = '<img class="svg_icon" src="/images/icon_multiradio.svg" />';
                    } else if(itemList[i].itemType == 'RADIO') {
                        icon = '<img class="svg_icon" src="/images/icon_radio.svg" />';
                    } else {
                        icon = '<img class="svg_icon" src="/images/icon_button.svg" />';
                    }
                    
                    html += "<div class='col col-6'>";
                    html += "   <div class='radio_box'>";
                    html += "       <input type='radio' id='itemId_" + i + "' name='itemId' value='" + itemList[i].itemId + "'>";
                    html += "       <label for='itemId_" + i + "'></label>";
                    html += "   </div>";
                    html += "   <div class='item_icon_box'>";
                    html += "       <div class='item_icon'>";
                    html += "           " + icon;
                    html += "           <div class='item_icon_label'>" + itemList[i].itemType + "</div>";
                    html += "       </div>";
                    html += "   </div>";
                    html += "   <div class='input_box item_tooltip' id='" + tmpId + "'>";
                    html += "       <span class='tooltiptext'>" + itemList[i].itemName + "</span>";
                    html += "       <input type='hidden' id='jsonCode' name='jsonCode' value='" + itemList[i].json + "'>";
                    html += "   </div>";
                    html += "</div>";                        
                    
                    $('#templateByItems').append(html);
                    
                    previewItemFunc(itemList[i].json, tmpId);
                    html = '';
                }
                
                
            }, 
            error: function(a) {
                alert(a.message);
            }
        }); 
    }
    
    
    // Step3. 아이템 리스트 팝업창에서 아이템 목록 미리보기
    previewItemFunc = function(json, tmpId) {
        
        try {
            jsonData = htmlToJson(tagReplaceAll(json));
        } catch(e){
            jsonData = undefined;
        }
         
        $('#' + tmpId).json2html({}, jsonData);
    }
    
    
    // Step4. 권한 및 상세설정 화면에서 사용자 Role 추가 
    addRoleFunc = function() {
        
        var roleCode = $("#roleCode option:checked").val();
        var roleName = $("#roleCode option:checked").text();

        var roleList = $('#roleList .authorBox');
        
        var flag = true;
        $.each(roleList, function(i, e){
            var tmpRoleCode = $(e).find('#authorCode').val();

            if(roleCode === tmpRoleCode) {
                alert('이미 추가되었습니다.');
                flag = false;
            }
        });
        
        if (flag) {
            var html = '';
            html += "   ";
            html += "       <div class='authorBox'><input type='hidden' name='authorCode' id='authorCode' value='" + roleCode + "' readOnly>";
            html += "       <input type='text' name='authorNm' id='authorNm' value='" + roleName + "' readOnly><button class='plus_btn' onclick='javascript:removeAuthFunc(this);'> &times; </button></div>";
            html += "     ";
            $('.authorList').append(html);
        }
        
    }
    
    
    // Step4. 권한 및 상세설정 화면에서 사용자 Role 삭제
    removeAuthFunc = function(obj) {
        $(obj).closest(".authorBox").remove();
    }
    
    
    // 빌더 미리보기
    builderPreviewFunc = function() {
        
        var jsonStr = createJsonData();
        var serviceName = $("#serviceName").val();

        var $form = $('<form></form>');

        $form.attr("action", "/admin/builder/builder/preview.do");
        $form.attr('method', 'post');
        $form.attr('target', 'previewForm');
        
        $form.appendTo('body');

        var json = $("<input type='hidden' name='json' id='json' value='" + jsonStr + "'>");
        var serviceName = $("<input type='hidden' name='serviceName' id='serviceName' value='" + serviceName + "'>");
        
        $form.append(json);           
        $form.append(serviceName);   
        
        window.open("" ,"previewForm", "toolbar=no, width=1280, height=900, directories=no, status=no, scrollorbars=no, resizable=no");
        
        $form.submit();           
    }
    
    
    // Step5. 렌더링할 화면을 json 현태로 변환
    createJsonData = function() {
        
        var builder = $('#sub_body');
        
        $('#build-output').empty();
        
        builder.clone().appendTo($('#build-output'));
        
        var conts = $('#build-output');
        
        // edit, delete button 삭제
        conts.find('.edit_delete_box').remove();
        
        // box 경계에 있는 추가 버튼 삭제
        conts.find('.add_fcol_next_box').remove();
        conts.find('.move_delete_row_box').remove();
        
        conts.find('[name="itemId"]').remove();
        conts.find('[name="jsonData"]').remove();
        
        // row blank box 삭제
        conts.find('.blank_add_box').remove();
        
        // blank row 삭제
        conts.find('.blank_line_box').remove();
        conts.find('.blank_fcol').remove();

        
        return settingJsonFormatForBuilderFunc(conts);
    }
    
    
    // Step5. 데이터 필드를 json 형식으로 변환
    createFieldToJsonData = function() {
        var fieldItemTable = document.getElementById('fieldItemList');
     
        var rowCount = $('#fieldItemList tr').length;
        
        var jsonArray = new Array();
         
        for(var i=0; i<rowCount; i++){
             
            var jsonObject = new Object();
        
            var input = fieldItemTable.rows.item(i).getElementsByTagName("input");
            var inputLen = input.length;                
             
            for(var j = 0; j < inputLen; j++) {
                jsonObject[input[j].id] = input[j].value;
            }   
        
            var select = fieldItemTable.rows.item(i).getElementsByTagName("select");
            var selectLen = select.length;              
             
            for(var j = 0; j < selectLen; j++) {
                jsonObject[select[j].id] = select[j].value;
            }
            
            jsonArray.push(jsonObject);
        }
         
         return jsonArray;
    }
    
    
    // Step5. 빌더에 추가된 아이템들을 json 형식으로 변환
    createItemToJsonData = function() {
        var items = $('.fcol_box');

        var jsonArray = new Array();

        $.each(items, function(i, e){
            
            var itemId = $(e).find("[name=itemId]").val();
            var json = $(e).find("[name=jsonData]").val();
            var html = $(e).find('.builder_box').html();

            if(itemId !== '') {
        
                try {
                    jsonData = htmlToJson(json);
                } catch(e){
                    jsonData = undefined;
                }

                var jsonObject = new Object();
                
                jsonObject = convertJsonToObject(jsonData, jsonObject);

                jsonObject['itemId'] = itemId;
                jsonObject['json'] = json;
                jsonObject['html'] = html;

                jsonArray.push(jsonObject);
               
            }
            
        });
       
        return jsonArray;
    }
    
    
    convertJsonToObject = function(json, jsonObj) {

        const tags = ['input', 'select', 'select-custom', 'textarea'];
        
        for(var prop in json){
        
            var _prop = prop.toLowerCase();

            switch(_prop){
                case '<>': 
                    switch(json[_prop]){             
                        case 'label' :
                            jsonObj['attrLabel'] = json['html'];
                            break;
                        case 'p' :
                            jsonObj['attrHelpText'] = json['html'];
                            break;
                        default:                              
                            tags.forEach(function(tag){
                                if(json[_prop] === tag) {
                                    for(key in json) {
                                        if(key === 'id') {
                                            jsonObj['elementId'] = json[key];
                                        } else if(key === 'data-field') {
                                            jsonObj['fieldId'] = json[key];
                                        }
                                    }
                                }
                            });                            
                        break; 
                    }                              
                    break;
                case 'html': 
                    recursiveJsonTemplate(json['html'], jsonObj);
                    break;
                default:                        
                    break;
            }
        }  
        return jsonObj;
    }
    
    
    function recursiveJsonTemplate(json, jsonObj) {        
        if(!Array.isArray(json)) {
            convertJsonToObject(json, jsonObj);
        } else {
            for(var i=0; i < json.length; i++) {
                convertJsonToObject(json[i], jsonObj);
            }
        } 
    }
    
    
    // Step5. 빌더 수정화면으로 json 형태로 변환
    createEditorJsonData = function() {
        var oHtml = $('#sub_body');     
        return settingJsonFormatForBuilderFunc(oHtml);
    }
    
    
    // Step5. 렌더링 할 화면을 html 형태로 변환
    settingHtmlFormatFunc = function(jsonData) {
        
        try {
            json = JSON.parse(jsonData);
        } catch(e){
            json = undefined;
            alert("e1 : " + e.message);
        }
        
        if(json){
            var _error = false;
            var $obj = $('<div/>');
            
            try {
                toTemplate(json, $obj);
            } catch(e){
                alert("e2 : " + e.message);
                _error = true;
            }
            
            return $obj.html();
        }
    }
    
    
    // Step5. 권한을 json 형태로 변환 
    createPermissionToJsonData = function() {

        var clazz = $('.permission');

        var jsonArray = new Array();
        
        $.each(clazz, function(i, e){
            var permissionType = $(e).attr('id');

            var permissionTable = document.getElementById(permissionType);
            
            var permissionRows = $('#' + permissionType + ' tr');
            var rowCount = permissionRows.length;   
            
            var roleName = permissionRows.find('#roleName').val();
            
            for(var i=0; i<rowCount; i++){
                var jsonObject = new Object();
                
                var input = permissionTable.rows.item(i).getElementsByTagName("input");
                for(var j=0; j<input.length; j++) {
                    var checked = input[j].checked ? "Y" : "N";

                    jsonObject['permission'] = roleName + ':' + input[j].id + ":" + checked;
                } 
                jsonArray.push(jsonObject);
            }
        });
        return jsonArray;
    }
    
    
    // Step5. role을 json 형태로 변환
    createRoleToJsonData = function() {
        var tRoleCode = '';
        
        var roleList = $('#roleList .authorBox');
        
        $.each(roleList, function(i, e){
            var tmpRoleCode = $(e).find('#authorCode').val();
            tRoleCode += tmpRoleCode + ':';
        });      
        return tRoleCode;
    }
    
    
});


function toTemplate(json, $html) {
    if(!Array.isArray(json)) {
        $html.append(obj2Template(json));
    } else {
        for(var i=0; i < json.length; i++) {
            $html.append(obj2Template(json[i]));
        }
    } 
}


function obj2Template(json) {
    
    var tag = json['<>'] || json['tag']; // input, label, div, 
    
    if(!tag) throw "Missing required attribute <>";
    
    var $obj = $("<" + tag + "/>");
    
    for(var prop in json){
        
        var _prop = prop.toLowerCase();  // class, type, <>
        
        switch(_prop){
            case '<>':
                //Do nothing
            break;
            
            case 'html':
                if(typeof(json['html']) === 'string') {
                    $obj.html(json['html']);
                } else {
                    toTemplate(json['html'],$obj);
                }
            break;
            
            default:
                $obj.attr(_prop, json[prop]);
            break;
        }
    }
    
    return($obj);
}


function toTransform(obj) {

    var json;

    if( obj.length > 1 ) {
        json = [];

        for(var i = 0; i < obj.length; i++) {
            json[json.length++] = obj2Transform(obj[i]);
        }
    } else {
        json = obj2Transform(obj);
    }

    return(json);
}


function obj2Transform(obj) {
    
    var el = $(obj).get(0);

    var json = {'<>':el.nodeName.toLowerCase()};

    for (var attr, i=0, attrs=el.attributes, l=attrs.length; i<l; i++){
        attr = attrs[i];
        json[attr.nodeName] = escapeJSON(attr.value);
    }
    
    var children = $(obj).children();

    if( children.length > 0 ) json['html'] = [];
    else json['html'] = escapeJSON($(obj).text());

    for(var c = 0; c < children.length; c++) {
        json['html'][json['html'].length++] = toTransform(children[c]);
    }

    return(json);
}


function formatJSON(oData, sIndent) {

    if (arguments.length < 2) {
        var sIndent = "";
    }
    
    var sIndentStyle = "  ";
    var sDataType = RealTypeOf(oData);

    if (sDataType == "array") {
        if (oData.length == 0) {
            return "[]";
        }
        var sHTML = "[";
    } else {
        var iCount = 0;
        $.each(oData, function() {
            iCount++;
            return;
        });
        if (iCount == 0) { 
            return "{}";
        }
        var sHTML = "{";
    }

    var iCount = 0;
    $.each(oData, function(sKey, vValue) {
        if (iCount > 0) {
            sHTML += ",";
        }
        if (sDataType == "array") {
            sHTML += ("\n" + sIndent + sIndentStyle);
        } else {
            sHTML += ("\"" + sKey + "\"" + ":");
        }

        switch (RealTypeOf(vValue)) {
            case "array":
            case "object":
                sHTML += formatJSON(vValue, (sIndent + sIndentStyle));
                break;
            case "boolean":
            case "number":
                sHTML += vValue.toString();
                break;
            case "null":
                sHTML += "null";
                break;
            case "string":
                sHTML += ("\"" + escapeJSON(vValue) + "\"");
                break;
            default:
                sHTML += ("TYPEOF: " + typeof(vValue));
        }

        iCount++;
    });

    if (sDataType == "array") {
        sHTML += ("\n" + sIndent + "]");
    } else {
        sHTML += ("}");
    }

    // return
    return sHTML;
}

function RealTypeOf(v) {
    if (typeof(v) == "object") {
        if (v === null) return "null";
        if (v.constructor == (new Array).constructor) return "array";
        if (v.constructor == (new Date).constructor) return "date";
        if (v.constructor == (new RegExp).constructor) return "regex";
        return "object";
    }
    return typeof(v);
}

function escapeJSON(str) {
    return str.replace(/[\n\r]/g, "\\n")
               .replace(/\\'/g, "\\'")
               .replace(/\\"/g, '\\"')
               .replace(/\\&/g, "\\&")
               .replace(/\\r/g, "\\r")
               .replace(/\\t/g, "\\t")
               .replace(/\\b/g, "\\b")
               .replace(/\\f/g, "\\f");
    
}

function htmlToJson(obj) {
    var jsonObj = JSON.parse(obj, function (key, value){
        if (value && typeof value === "string" && value.substr(0,8) == "function") {
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

function jsonToHtml(obj) {
    var htmlObj = JSON.stringify(obj, function (key, value){
        if (typeof value === 'function') {
            return value.toString();
        }
        return value;            
    });
    
    return htmlObj;
} 