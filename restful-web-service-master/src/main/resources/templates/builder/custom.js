



/* M-NAV OPEN */
$(document).on('click','.m_nav_tab_box li[data-tab=m_tab_1]',function(e){
    $('.mobi_nav_box.flickity-enabled').toggleClass('m_nav_height_unset');
    $('.m_nav_tab_box').toggleClass('opened');
})


/* M-NAV CLOSE */
$(document).mouseup(function (e) {
    var is_m_btn = $(e.target).closest('li').attr('data-tab') == "m_tab_1";
    var is_sub_nav = $(e.target).closest('.mobi_nav_box').length;
    var has_class = $('.mobi_nav_box.flickity-enabled').hasClass('m_nav_height_unset');
    var is_wh_toggle = $(e.target).closest('.wh_toggle_box').length;

    if (!is_sub_nav && !is_m_btn && !is_wh_toggle && has_class) {
        $('.mobi_nav_box.flickity-enabled').toggleClass('m_nav_height_unset');
        $('.m_nav_tab_box').toggleClass('opened');
    }
});                             
    
    




/* MODAL */

function modalPositioning(e){
    var xPercent = (e.clientX/window.innerWidth).toFixed(2);
    var yPercent = (e.clientY/window.innerHeight).toFixed(2);

    e.pageX = (xPercent > 0.55) ? e.pageX = e.pageX - 450 : e.pageX;
    e.pageY = (yPercent > 0.5) ? e.pageY = e.pageY - 350 : e.pageY;
    return e;
}


$(document).on('click','a[href="#"]',function(e){
    // A앵커 클릭시 스크롤 위로 올라가는것 방지
    e.preventDefault();
});


// MODAL DRAG OPTION
var draggable_option = {
    handle: ".modal_header",
}


$(document).on('keydown','a',function(e){
    // 엔터키 A클릭 방지
    if(e.which === 13) e.preventDefault();
})


$(document).on('click','span.modal_close',function(e){
//MODAL CLOSE 버튼
    closeModal();
})  
    

function closeModal(){
    $('.modal_box').hide();
}



/* MODAL 전체 */

$(document).on('click', 'a.modal_open', function(e) {
    var id = $(this).attr('modal-id');
    $('.modal_box#'+id).show().find('.modal').draggable(draggable_option);
})

$(document).on('click','.btn_confirm',function(e){
    // MODAL 단순 확인버튼
    closeModal();
})  

/* custom override alert */
function alert(confirmMessage){
    var alertDiv = ''
        + '<div class="modal modal_confirm">'
        + '<div class="modal_header">' + getMessages('label.approval.53') + '<span class="modal_close">&times;</span>' + '</div>'
        + '<div class="modal_body">' + confirmMessage + '</div>'
        + '<div class="modal_footer">'
        + '<button class="trans_btn" onclick="javascript:closeModal(this);">'+getMessages('cmmn.cmmnServiceProviderTransferLayer.div.ok')+'</button>'
        + '</div>';
    $('.modal_box').show().empty().append(alertDiv).find('.modal');
}


/* custom callback alert */
function cbAlert(confirmMessage, callbackFunc){
    var alertDiv = ''
        + '<div class="modal modal_confirm">'
        + '<div class="modal_header">' + getMessages('label.approval.53') + '<span class="modal_close">&times;</span>' + '</div>'
        + '<div class="modal_body">' + confirmMessage + '</div>'
        + '<div class="modal_footer">'
        + '<button class="trans_btn" onclick="javascript:alertCbFunc(this);">'+getMessages('cmmn.cmmnServiceProviderTransferLayer.div.ok')+'</button>'
        + '</div>';

    alertCbFunc = function(obj){
        callbackFunc();
        closeModal(obj);
        alertCbFunc = null;
    }

    $('.modal_box').show().empty().append(alertDiv).find('.modal');  
}


/* custom callback confirm */
function confirm(confirmTitle, confirmMessage, trueCallbackFunc, falseCallbackFunc){
    
    var alertDiv = ''
        + '<div class="modal modal_confirm">'
        + '<div class="modal_header">' + confirmTitle + '<span class="modal_close">&times;</span>' + '</div>'
        + '<div class="modal_body">' + confirmMessage + '</div>'
        + '<div class="modal_footer">'
        + '<button class="trans_btn" onclick="javascript:customConfirmFalseFunc(this);">취소</button>'
        + '<button class="trans_btn" onclick="javascript:customConfirmTrueFunc(this);">확인</button>'
        + '</div>';

        customConfirmTrueFunc = function(obj){

            if(trueCallbackFunc != undefined && trueCallbackFunc != null){
                trueCallbackFunc();
            }          
            closeModal(obj);
            customConfirmTrueFunc = null;
        }

        customConfirmFalseFunc = function(obj){
            if(falseCallbackFunc != undefined && falseCallbackFunc != null){
                falseCallbackFunc();
            }
            closeModal(obj);
            customConfirmFalseFunc = null;
        }
    
    $('.modal_box').show().empty().append(alertDiv).find('.modal');  
}


/* custom callback confirm */
function cbConfirm(confirmTitle, confirmMessage, callbackFunc){
    var alertDiv = ''
        + '<div class="">'
        + '<div class="modal_header">' + confirmTitle + '<span class="modal_close">&times;</span>' + '</div>'
        + '<div class="modal_body">' + confirmMessage + '</div>'
        + '<div class="modal_footer">'
        + '<button class="trans_btn" onclick="javascript:customConfirmFalseFunc(this);">취소</button>'
        + '<button class="trans_btn" onclick="javascript:customConfirmTrueFunc(this);">확인</button>'
        + '</div>';

        customConfirmTrueFunc = function(obj){
            if(callbackFunc != undefined && callbackFunc != null){
                callbackFunc = callbackFunc(true);
            }
            //closeModal(obj);
            customConfirmTrueFunc = null;           
        }

        customConfirmFalseFunc = function(obj){
            if(callbackFunc != undefined && callbackFunc != null){
                callbackFunc = callbackFunc(false);
            } 
            closeModal(obj);
            customConfirmFalseFunc = null;
        }
    
    $('.modal_box').show().empty().append(alertDiv).find('.modal');  
}



/* TABLE */
$(document).on('change','.cb_all',function(e){
    $(this).closest('table').find('input:checkbox').prop('checked', $(this).prop("checked"));
});




$('[data-toggle="datepicker"]').datepicker({language: 'ko-KR',autoHide:true});





/* TAB */
$('ul.tabs li').click(function(){
    var tab_id = $(this).attr('data-tab');

    $('ul.tabs li').removeClass('active');
    $('.form_remark').removeClass('active');

    $(this).addClass('active');
    $("#"+tab_id).addClass('active');

});


$('.timetable input[type="checkbox"]').click(function(){
    var favor_check = $(this).prop('checked')
    if(favor_check == true){
        
        var target = $(this).closest('tr');
        $(target).insertBefore('table.timetable tbody tr:eq(2)');

    } else if(favor_check == false){
        var rows = $(".timetable input[type='checkbox']:not(:checked)").closest('tr').get();
        rows.sort(sortTable);
        $.each(rows, function(index, row){
            $("table.timetable tbody").append(row);
        });    
    }
});


function sortTable(a,b){
     var A = parseInt($(a).attr('data-sort'));
    var B = parseInt($(b).attr('data-sort'));
     
    if (A > B) return 1;
    if (A < B) return -1;

    return 0;
}
/*일간 체크박스 전체 */
$(document).on('change','#reserve_check_all',function(e){
        $(this).closest('.form_radio_box').find('input:checkbox').prop('checked', $(this).prop("checked"));
});


/* TIMETABLE */
/* TIMETABLE - TOOLTIP */
$(document).on({
    mouseenter: function (e) {
         var id = $(this).attr('id');
         var title = $(this).attr('data-title');
         var manager = $(this).attr('data-manager');
         var content = $(this).attr('data-content');
         var table_type = $(this).closest('table').attr('id');

         if(table_type=='meeting_room'){
             var div = ''
                 + '<div class="time_tooltip_header">'+title+'</div>'
                 + '<div class="time_tooltip_body">'
                 + '<div>회의 주관자: '+manager+'</div>'
                 + '<div>회의 내용: '+content+'</div>'
                 + '</div>';
         
         } else if(table_type=='fctry-calendar'){
             var div = ''
                 + '<div class="time_tooltip_header">'+title+'</div>'
                 + '<div class="time_tooltip_body">'
                 + '<div>회의 주관자: '+manager+'</div>'
                 + '<div>회의 내용: '+content+'</div>'
                 + '</div>';
         } else if(table_type=='public_room'){

             var div = ''
                 + '<div class="time_tooltip_box" data-id="'+id+'">'
                 + '<div class="time_tooltip_header">'+title+'</div>'
                 + '<div class="time_tooltip_body">'
                 + '<div>주관자: '+manager+'</div>'
                 + '<div>사용 일정: '+content+'</div>'
                 + '</div>'
                 + '</div>';

         } else if(table_type=='intrpr_sch'){
             
            var div = ''
                 + '<div class="time_tooltip_box" data-id="'+id+'">'
                 + '<div class="time_tooltip_header">'+title+'</div>'
                 + '<div class="time_tooltip_body">'
                 + '<div>요청구분: '+manager+'</div>'
                 + '</div>'
                 + '</div>';
         }

         if($('.time_tooltip_box').length < 1) {
             $(".timetable_date_box").after('<div class="time_tooltip_box"></div>');
         }
         
         $('.time_tooltip_box').html(div);
         $('.time_tooltip_box').css({
             left: e.pageX,
             top: (e.pageY+12) - $(document).scrollTop()
         }).css('display', 'block');
    },

    mouseleave: function (e) {
        $('.time_tooltip_box').hide();   
    } 
    
}, "td.blocked,td.my_reserve,td.my_intrpr,div.agenda_item,td.intrpr");






/* DATEPICKER CURRENT DATE */

$('#timetable_datepicker').datepicker('setDate', 'today');    

$('#timetable_today').click(function(){

    current_datepicker = $("#timetable_datepicker").datepicker('setDate', 'today');
    //selectTimeTableList();
});
var initTimetable =  function(){

    var id = $('.timetable').attr('id');
    var max_td = 24;    
    var expand_mode = false;
    
    //헤더 숨기기
    if(id == "meeting_room"){
        var $th_1 = '.timetable tr:nth-child(1) th:nth-last-child(-n+4)';
        var $th_2 = '.timetable tr:nth-child(2) th:nth-last-child(-n+8)';
    } else if(id == "public_room"){
        var $th_1 = '.timetable tr:nth-child(1) th:nth-last-child(-n+3)';
        var $th_2 = '.timetable tr:nth-child(2) th:nth-last-child(-n+6)';
    }
    $($th_1).add($th_2).hide();
    setHideTd();
    /* TIMETABLE HIDE AND SHOW */
    $(document).on('click','.expand_btn_box',function(e){
        if(expand_mode){
            /* 축소 */
            $($th_1).add($th_2).hide();
            $('.hidden_td').not('.start.end').hide();
            $('table.timetable').removeClass('x_scroll');
            expand_mode = false;
        
        } else if(!expand_mode) {
        
            /* 확장 */
            $($th_1).add($th_2).show();
            $('.hidden_td').show();
            $('table.timetable').addClass('x_scroll');
            expand_mode = true;
        }
        setStickyWidths();
    });
    
    
    function setHideTd(){
    
    $('.timetable td.cell').each(function(){
            var prev_td = $(this).prevAll('.cell').not('.blocked').length;
            var prev_colspan = 0;
            $(this).prevAll(".blocked").each(function() {
                prev_colspan += this.colSpan;
            });
    
            var full_prev_td = prev_colspan + prev_td;
            if(full_prev_td >= max_td){
    
                if(!expand_mode){               
                    $(this).hide().addClass('hidden_td');
                } else if(expand_mode){
                    $(this).addClass('hidden_td');
                }
            }
        });       
    }
}


$('.datepicker_arrow').click(function(){

    var direction = $(this).attr('id');
    var current_datepicker = $("#timetable_datepicker").datepicker('getDate');
    var new_date = new Date( current_datepicker );


    if(direction == "date_prev"){
        new_date.setDate( new_date.getDate( ) - 1 );
    } else if(direction == "date_next"){
        new_date.setDate( new_date.getDate( ) + 1 );
    }
    
    $('#timetable_datepicker').datepicker("setDate", new_date);
    //selectTimeTableList();
})



/* TIMETABLE DATEPICKER */
$('#timetable_datepicker').on('pick.datepicker', function (e) { 
    
    
    // 회의실에서 건물선택 안할 시 달력날짜 클릭 검색 중단 
    var tableId = $('table.timetable').attr('id');
    if( tableId == "meeting_room" && $('#buldCode').val() === "") {
        return false;
    }
    
    var selected_date = $('#timetable_datepicker').datepicker('getDate', true);
    $("#timetable_datepicker").val(selected_date);
    $("#resveDate").val(selected_date);
    selectTimeTableList();
});
/*
$('#timetable_datepicker').datepicker({ 
            language: 'ko-KR',
            autoHide: true,                 
}).datepicker("setDate", 'today');
*/

//$('#timetable_datepicker').datepicker("setDate", 'today');





/* DATEPICKER MONTH ONLY */
$('input.month_only').datepicker({
            format: 'yyyy-mm',
            language: 'ko-KR',
            autoHide: true,
});


/* true or false boolean value return */
function returnTrueOrFalseFunc(bool) {
    return bool;
}

validateForms = function(form) {
    var result = true;
    $(form).find("input, select, textarea").each(function(i) {
        $t = jQuery(this);

        if($t.prop("required") == true) {
            if(!jQuery.trim($t.val())) {
                var t = $t.attr("label");
                result = false;  
                $t.focus();
                alert(t + ' 은(는) 필수항목입니다.');
                return false;
            }
        }
    }); 

    return result;
}   



$(document).on('click', '.btn_menu_save', function (e) {
    var id = $(this).closest('.modal_box').attr('data-id');
    console.log(id + " menu saved");
    closeModalFile();
})




$(document).on('click', '.btn_menu_cancel,.modal_close_menu', function (e) {
    closeModalFile();
})


function closeModalFile() {
    $('.modal_box.modal_menu').remove();
}


/*
function popZip() {
    new daum.Postcode({
        oncomplete: function (data) {
            $("#zipCode").val(data.zonecode); // 구 우편번호 삭제 후 신 지번번호로 변경
    
            if (data.userSelectedType == "J") {   // 사용자가 지번을 클릭했다면
                $("#postReceiverAddress").val(data.jibunAddress);
            } else {
                if (data.buildingName != "") {
                       $("#postReceiverAddress").val(data.roadAddress + "(" + data.buildingName + ")");
                } else {
                       $("#postReceiverAddress").val(data.roadAddress);
                }
            }
        },
        shorthand: false
    }).open();
    
    $("#addressDetail").focus();
}
*/

function popZip(codeObj, addressObj, nextObj) {
    new daum.Postcode({
        oncomplete: function (data) {
            
            if(data.userLanguageType == "K"){
                if(!isEmpty(codeObj))
                    $("#"+codeObj).val(data.zonecode); // 구 우편번호 삭제 후 신 지번번호로 변경
                
                if (data.userSelectedType == "J") {   // 사용자가 지번을 클릭했다면
                    $("#"+addressObj).val(data.jibunAddress);
                } else {
                    if (data.buildingName != "") {
                        $("#"+addressObj).val(data.roadAddress + "(" + data.buildingName + ")");
                    } else {
                        $("#"+addressObj).val(data.roadAddress);
                    }
                }
            } else if(data.userLanguageType == "E"){
                if(!isEmpty(codeObj))
                    $("#"+codeObj).val(data.zonecode); // 구 우편번호 삭제 후 신 지번번호로 변경
                
                if (data.userSelectedType == "J") {   // 사용자가 지번을 클릭했다면
                    $("#"+addressObj).val(data.jibunAddressEnglish);
                } else {
                    $("#"+addressObj).val(data.roadAddressEnglish);
                }
            }
            
            // required 체크 해지
            $("#"+addressObj).trigger('input');
            
            if(!isEmpty(nextObj)) {
                $("#"+nextObj).focus();
            }
            
        },
        shorthand: false
    }).open();
    

}

// 상세 정보 조회 (공통)
callDetailInfoFunc = function(code) {
    if(!isEmpty(code)) {
        var whereParamObj = {};
        
        if(code == 'COMM_DEPT_INFO_SEARCH') {
            whereParamObj['a.DEPT_CODE'] = $("#render #searchDeptCode").val()
            
        }else if(code == 'COMM_DEPT_INFO_SEARCH') {
            whereParamObj['WORKPLACE_CODE'] = $("#render #workplaceCode").val();
            whereParamObj['CAR_CODE'] = $("#render #carCode").val();
            whereParamObj['GROUP_CODE'] = $("#render #groupCode").val();
            whereParamObj['CMPNT_NO'] = $("#render #cmpntCode").val();
            
        }else if(code == 'HC008_ACMSLT_INFO_SEARCH') {
            whereParamObj['YEAR'] = $("#render #year").val();
            whereParamObj['TARGET_USER_ID'] = $("#render #targetUserId").val();
            
        }else if(code == 'POSMAN_INPUT') {
            whereParamObj['POS_CODE'] = $("#render #targetPosCode").val();
            
        }else if(code == 'ADDRESS_DETAIL_INPUT') {
            whereParamObj['TARGET_WORKPLACE_CODE'] = $("#render #addressWorkplaceCode").val();
            whereParamObj['TARGET_DTL_WORKPLACE_CODE'] = $("#render #addressCodeInput").val();
            
        }else if(code == 'BUS_ROUTE_INFO_SEARCH') {
            whereParamObj['REQUEST_REGION_CODE'] = $("#render #requestRegionCode").val()+'%';
            whereParamObj['ROUTE_CODE'] = $("#render #routeCode").val()+'%';
            
        }else if(code == 'BUS_ROUTE_INFO_SEARCH_INPUT') {
            whereParamObj['REQUEST_REGION_CODE'] = grdList.cells(rowId, grdList.getColIndexById("REQUEST_REGION_CODE")).getValue()+'%';
            whereParamObj['ROUTE_CODE'] = grdList.cells(rowId, grdList.getColIndexById("ROUTE_CODE")).getValue()+'%';
            
        }else if(code == 'HB005_PRESS_INFO') {
            whereParamObj['WORKPLACE_CODE'] = $('#builderForm #workplaceCode').val(); 
            whereParamObj['PRESS_CODE'] = $('#render #pressCode').val();
            
        }else if(code == 'HD001_TONER_DETAIL') {
            whereParamObj['A.PRINTER_NAME'] = $('#render #printModel').val(); 
            whereParamObj['B.TONER_CODE'] = $('#render #tonerModel').val(); 

        }else if(code == 'CMPNT_INFO_SEARCH') {
            whereParamObj['WORKPLACE_CODE'] = $('#render #targetWorkplaceCode').val()+'%'; 
            whereParamObj['CAR_CODE'] = $('#render #carCode').val()+'%'; 
            whereParamObj['GROUP_CODE'] = $('#render #groupCode').val()+'%'; 
            whereParamObj['CMPNT_CODE'] = $('#render #cmpntCode').val()+'%';
            
        }else if(code == 'CAR_REPAIR_CAR_INFO') {
            whereParamObj['VIN'] = $('#render #vin').val()+'%';
            
        }else if(code == 'HB015_PAPER_INFO') {
            whereParamObj['GRC_PAPERS_CODE'] = $('#render #grcPaper').val();
            
        }else if(code == 'UNIFORM_SEXCODE_INFO') {
            whereParamObj['UNIFORM_LCLASS_CODE'] = $('#render #uniformLclassCode').val();
            whereParamObj['UNIFORM_MCLASS_CODE'] = $('#render #uniformMclassCode').val(); 
            whereParamObj['UNIFORM_SCLASS_CODE'] = $('#render #uniformSclassCode').val();
            
        }else if(code == 'CFRM_SUER_MAX_SEQ') {
            whereParamObj['AUTH_CODE'] = $('#render #authCode').val();
            whereParamObj['USER_ID'] = $('#render #userId').val();
            
        }else if(code == 'SELF_CAR_MAX_SEQ') {
            whereParamObj['USER_ID'] = $('#render #userId').val();
            
        }else if(code == 'CFRM_INFO_SEARCH') {
            whereParamObj['WORKPLACE_CODE'] = $('#render #workplaceCode').val(); 
            whereParamObj['DTL_WORKPLACE_CODE'] = $('#render #requestDtlWorkplaceCode').val(); 
            whereParamObj['BULD_CODE'] = $('#render #requestBuldCode').val(); 
            whereParamObj['FL_CODE'] = $('#render #requestFlCode').val(); 
            whereParamObj['CFRM_CODE'] = $('#render #requestCfrmCode').val();
            
        }else if(code == 'CLB_LEADER_INFO') {
            whereParamObj['A.CLB_CODE'] = $('#render #clbCode').val();
        
        }else if(code == 'CLB_ADVISER_INFO') {
            whereParamObj['A.CLB_CODE'] = $('#render #clbCode').val();
        
        }else if(code == 'CLB_GASC_INFO') {
            whereParamObj['A.CLB_CODE'] = $('#render #clbCode').val();
                    
        }else if(code == 'CLB_MAN_CNT_INFO') {
            whereParamObj['CLB_CODE'] = $('#render #clbCode').val();
            
        }else if(code == 'CLB_TOTAL_CNT_INFO') {
            whereParamObj['CLB_CODE'] = $('#render #clbCode').val();
            
        }else if(code == 'CLB_WOMAN_CNT_INFO') {
            whereParamObj['CLB_CODE'] = $('#render #clbCode').val(); 
        
        }else if(code == 'UNIFORM_TME_SEQ') {
            whereParamObj['WORKPLACE_CODE'] = $('#render #workplaceCode').val(); 
            whereParamObj['REQUEST_FLAG_CODE'] = $('#render #requestFlagCode').val(); 
            whereParamObj['YEAR'] = $('#render #year').val(); 
        
        // 주거지원금 일련번호 조회 
        }else if(code == 'LON_SEQ') {
            whereParamObj['USER_ID'] = $('#render #userId').val(); 
            whereParamObj['LON_KND_CODE'] = $('#render #lonKndCodeInput').val(); 
            
        // 인장 관릴번호 조회
        }else if(code == 'HD004_SEAL_INFO') {
            whereParamObj['LEAD_DEPT_CODE'] = $('#render #leadDeptInputSelect').val(); 
            whereParamObj['HOLD_DEPT_CODE'] = $('#render #holdDeptInputSelect').val(); 
            whereParamObj['SEAL_KND_CODE'] = $('#render #sealKndFCode').val(); 
        }else if(code == 'HD004_SEAL_INFO2') {
            whereParamObj['LEAD_DEPT_CODE'] = $('#render #leadDeptInputSelect').val(); 
            whereParamObj['HOLD_DEPT_CODE'] = $('#render #holdDeptInputSelect').val(); 
            whereParamObj['SEAL_KND_CODE'] = $('#render #sealKndSCode').val(); 
        //인장등록 부서정보 조회
        }else if(code == 'HD005_HOLD_DEPT_INFO'){
            //alert($('#render #targetDeptCode').val());
            whereParamObj['HOLD_DEPT_CODE'] = $('#render #targetDeptCode').val();
                //$('#render #targetDeptCode').val();
            
        // 총무용품 신청사유
        }else if(code == 'HB006_RESN_INFO'){
            whereParamObj['REQUEST_REASON_CODE'] = $('#render #requestReason').val();
        }else if(code == 'UNIV_SCHUL_INFO'){
            whereParamObj['SCHUL_CODE'] = $('#render #schulCode').val();
            whereParamObj['MAJOR_CODE'] = $('#render #schulMajorCode').val();
        }else if(code == 'SCHUL_MESSAGE_INFO'){
            whereParamObj['WORKPLACE_CODE']  = $('#render #targetWorkplaceCode').val();
            whereParamObj['SCHUL_TYPE_CODE'] = $('#render #schulTypeCode').val();   
        //사업자등록증 파일아이디 조회
        }else if(code == 'BIZ_CERT_FILE_INFO'){
            whereParamObj['CERT_TYPE_CODE']  = $('#render #certTypeCode').val();
            whereParamObj['STD_WORKPLACE_TYPE_CODE'] = $('#render #stdWorkplaceTypeCode').val();    
        }else if(code == 'CAR_REGIST_NO_INFO'){
            whereParamObj['TARGET_USER_ID']  = $('#render #targetUserId').val();    
        }else if(code == 'SNK_MESSAGE_INFO'){
            whereParamObj['WORKPLACE_CODE']  = $('#snkRequestForm #targetWorkplaceCode').val(); 
        }else if(code == 'HA020_TME_INFO'){
            whereParamObj['TME']  = $('#render #tme').val();
            whereParamObj['REQUEST_FLAG_CODE']  = $('#builderForm #bizCode').val();
        }else if(code == 'HA023_UNIFORM_INFO'){
            whereParamObj['WORKPLACE_CODE']  = $('#render #targetWorkplaceCode').val();
            whereParamObj['UNIFORM_LCLASS_CODE']  = $('#render #uniformLclassCode').val();
            whereParamObj['UNIFORM_MCLASS_CODE']  = $('#render #uniformMclassCode').val();
            whereParamObj['UNIFORM_SCLASS_CODE']  = $('#render #uniformSclassCode').val();
        }else if(code == 'HC015_TERM_INFO'){
            whereParamObj['WORKPLACE_CODE']  = $('#render #carWorkplaceCode').val();
            whereParamObj['REQUEST_YM']  = $('#render #requestYm').val();
        }else if(code == 'HC019_TERM_INFO'){
            whereParamObj['WORKPLACE_CODE']  = $('#render #carWorkplaceCode').val();
            whereParamObj['REQUEST_YM']  = $('#render #requestYm').val();
        }
        
        //console.dir(whereParamObj);
        whereParams = JSON.stringify(whereParamObj);
    }
    
    var item = '';
    $.ajax({
        method: "POST", 
        url: "/common/getCodeByBizDetailCode.do", 
        dataType: "json",
        async: false,
        data: {
            "code": code,
            "whereParams":whereParams
        }, 
        success: function(response) {
            item = response.data;
        }, 
        error: function(a) {
            alert(code + ", " +a.message);
        }
    }); 
    return item;
}




//사용자 정보 조회 (공통)
callUserInfoFunc = function(userId) {
    var item = '';
    var serviceId = $('#builderForm #serviceId').val();
    
    userId = $.trim(userId);
    if(userId.length < 2) {
        alert("사용자 아이디를 입력하세요.");
        $('.loading_screen').hide();
    }else {
        $.ajax({
            method: "POST", 
            url: "/common/getCmmnUserInfo.do", 
            dataType: "json",
            async: false,
            data: {
                "serviceId" : serviceId,
                "searchUserId" : userId
            }, 
            success: function(response) {
                item = response.userInfo;
            }, 
            error: function(a) {
                alert(a.message);
            }
        }); 
    }
    
    return item;
}

//사용자 정보 조회 (공통 - 콜백함수추가)
callbackUserInfoFunc = function(userId, callbackFunc) {
    var item = '';
    var serviceId = $('#builderForm #serviceId').val();
    
    userId = $.trim(userId);
    if(userId.length < 2) {
        alert("사용자 아이디를 입력하세요.");
        $('.loading_screen').hide();
    }else {
        $.ajax({
            method: "POST", 
            url: "/common/getCmmnUserInfo.do", 
            dataType: "json",
            async: false,
            data: {
                "serviceId" : serviceId,
                "searchUserId" : userId
            }, 
            success: function(response) {
                item = response.userInfo;
                if(callbackFunc != null) {
                    callbackFunc(item);
                }
            }, 
            error: function(a) {
                alert(a.message);
            }
        }); 
    }

    return item;
}

callbackAllUserInfoFunc = function(userId, callbackFunc, retireYn, searchAllYn) {
    var item = '';
    var serviceId = $('#builderForm #serviceId').val();
    userId = $.trim(userId);
    if(userId.length < 2) {
        alert("사용자 아이디를 입력하세요.");
        $('.loading_screen').hide();
    }else {
        $.ajax({
            method: "POST", 
            url: "/common/getCmmnUserInfo.do", 
            dataType: "json",
            async: false,
            data: {
                "serviceId" : serviceId,
                "searchUserId" : userId,
                "retireYn" : retireYn,
                "searchAllYn" : searchAllYn
            }, 
            success: function(response) {
                item = response.userInfo;
                if(callbackFunc != null) {
                    callbackFunc(item);
                }
            }, 
            error: function(a) {
                alert(a.message);
            }
        });
    }
    
    return item;
}


//부서 정보 조회 (공통)
callDeptInfoFunc = function(deptCode) {
    var item = '';
    var serviceId = $('#builderForm #serviceId').val();
    var targetUserId = $('#searchCondition #targetUserId').val();
    if(isEmpty(targetUserId)) targetUserId = $('#render #targetUserId').val();

    deptCode = $.trim(deptCode);
    if(deptCode.length < 2) {
        alert("부서코드를 입력하세요.");
        $('.loading_screen').hide();
    }else {
        $.ajax({
            method: "POST", 
            url: "/common/getCmmnDeptInfo.do", 
            dataType: "json",
            async: false,
            data: {
                "serviceId":serviceId,
                "targetUserId" : targetUserId,
                "deptCode" : deptCode
            }, 
            success: function(response) {
                item = response.deptInfo;
            }, 
            error: function(a) {
                alert(a.message);
            }
        }); 
    }
    
    return item;
}

//부서 정보 조회 (공통 - 콜백함수추가)
callbackDeptInfoFunc = function(deptCode, callbackFunc) {
    var item = '';

    var serviceId = $('#builderForm #serviceId').val();
    var targetUserId = $('#searchCondition #targetUserId').val();
    if(isEmpty(targetUserId)) targetUserId = $('#render #targetUserId').val();
    
    deptCode = $.trim(deptCode);
    if(deptCode.length < 2) {
        alert("부서코드를 입력하세요.");
        $('.loading_screen').hide();
    }else {
        $.ajax({
            method: "POST", 
            url: "/common/getCmmnDeptInfo.do", 
            dataType: "json",
            async: false,
            data: {
                "serviceId":serviceId,
                "targetUserId" : targetUserId,
                "deptCode" : deptCode
            }, 
            success: function(response) {
                item = response.deptInfo;
                callbackFunc(item);
            }, 
            error: function(a) {
                alert(a.message);
            }
        });
    }
    return item;
}

callbackAllDeptInfoFunc = function(deptCode, callbackFunc) {
    var item = '';
    
    deptCode = $.trim(deptCode);
    if(deptCode.length < 2) {
        alert("부서코드를 입력하세요.");
        $('.loading_screen').hide();
    }else {
        $.ajax({
            method: "POST", 
            url: "/common/getCmmnDeptInfo.do", 
            dataType: "json",
            async: false,
            data: {
                "searchDeptCode" : deptCode
            }, 
            success: function(response) {
                item = response.deptInfo;
                callbackFunc(item);
            }, 
            error: function(a) {
                alert(a.message);
            }
        });
    }

    return item;
}

getDepartmentInfo = function(deptCode, divisionCode){
	
	var departmentInfo = "";
	
    $.ajax({
        method: "POST",
        url : "/common/user/getDepartmentInfo.do",
        data : {
        	"deptCode" : deptCode,
        	"divisionCode" : divisionCode
        },
        dataType: "json",
        async: false,
        success : function(response){
        	departmentInfo = response.departmentInfo;

        }, 
        error: function(a) {
            alert(a.message);
        }
    });
    
    return departmentInfo;
};

mergeFmyInfo = function(targetUserId){
	
	var targetUserId = targetUserId;
	
    $.ajax({
        method: "POST",
        url : "/common/user/mergeFmyInfo.do",
        data : {
        	"targetUserId" : targetUserId
        },
        dataType: "json",
        async: false,
        success : function(response){

        }, 
        error: function(a) {
            alert(a.message);
        }
    });
};



/* notice 공지사항  */

$(document).on('click','#select_all_office',function(){
    $(this).closest('.row-2').find('input:checkbox').prop('checked', $(this).prop("checked"));
})


$('.upload_box input[type="file"]').on('change', function() {
        var div = '<a href="#" class="file_item">'+$(this).val()+'</a>';
        $(this).closest('.upload_box').find('.file_txt').empty().append(div);
});



$('#banner_show_check').change(function(){
    var status = $(this).prop('checked')
    if(status == true){
        $('.banner_row').removeClass('inactive');
        $('.banner_row input:text,textarea').prop( "disabled", false );
    } else if(status == false){
        $('.banner_row').addClass('inactive');
        $('.banner_row input:text,textarea').prop( "disabled", true );      
    }
})



$(document).on('click','.inactive',function(e){
    e.preventDefault();
})  




ajaxCommClCodeData = function(e, clCode) {
    var code = $(e).attr('commclcode');
    if(isEmpty(code)) code = clCode;
    
    if(!isEmpty(code)) {
        $.ajax({
            method: "POST",
            async: false,
            url: "/common/getClCodeByBizCodeList.do", 
            dataType: "json",
            data: {
                clCode : code
            }, 
            success: function(response) {
                var cmmnBizCodeList = response.cmmnBizCodeList;
                
                $(e).find('option').remove();
                if($(e).attr("custom-search")){
                    $(e).append("<option value=''>전체</option>");
                }else {
                    $(e).append("<option value=''>선택하세요</option>");
                }
                
                for(var i=0; i<cmmnBizCodeList.length; i++) {
                    $(e).append("<option value='" + cmmnBizCodeList[i].codeId + "'>" + cmmnBizCodeList[i].codeIdName + "</option>");
                }
            }, 
            error: function(a) {
                alert(a.message + "(ajaxCommClCodeData:"+code+")");
            }
        });
    }
}   

ajaxCommCodeData = function(e, codeId, targetObj) {
    var code = $(e).attr('commcode');
    if(isEmpty(code)) {
        code = codeId;
        e = targetObj;
    }

    if(!isEmpty(code)) {
        
        $.ajax({
            method: "POST",
            async: false,
            url: "/common/getCodeByBizDetailCodeList.do", 
            dataType: "json",
            data: {
                "codeId": code
            }, 
            success: function(response) {
                var cmmnBizCodeList = response.cmmnBizDetailCodeList;
                
                if(cmmnBizCodeList == null) {
                    cmmnBizCodeList = 0;
                }
                
                $(e).find('option').remove();
                if($(e).attr("custom-search")){
                    $(e).append("<option value=''>전체</option>");
                }else {
                    $(e).append("<option value=''>선택하세요</option>");
                }
                
                for(var i=0; i<cmmnBizCodeList.length; i++) {
                    var textAttr1 = (isEmpty(cmmnBizCodeList[i].textAttr1))?"0":cmmnBizCodeList[i].textAttr1;
                    var textAttr2 = (isEmpty(cmmnBizCodeList[i].textAttr2))?"0":cmmnBizCodeList[i].textAttr2;
                    var textAttr3 = (isEmpty(cmmnBizCodeList[i].textAttr3))?"0":cmmnBizCodeList[i].textAttr3;
                    var textAttr4 = (isEmpty(cmmnBizCodeList[i].textAttr4))?"0":cmmnBizCodeList[i].textAttr4;
                    var textAttr5 = (isEmpty(cmmnBizCodeList[i].textAttr5))?"0":cmmnBizCodeList[i].textAttr5;
                    var textAttr = "textAttr1='"+ textAttr1 + "' "
                                  +"textAttr2='"+ textAttr2 + "' "
                                  +"textAttr3='"+ textAttr3 + "' "
                                  +"textAttr4='"+ textAttr4 + "' "
                                  +"textAttr5='"+ textAttr5 + "' ";
                    $(e).append("<option value='" + cmmnBizCodeList[i].code + "'"+textAttr+">" + cmmnBizCodeList[i].codeName + "</option>");
                }
            }, 
            error: function(a) {
                alert(a.message + "(ajaxCommCodeData:"+code+")");
            }
        });
    }
}

ajaxBizCodeData = function(e, codeId, targetObj) {
    var code = $(e).attr('bizcode');
    if(isEmpty(code)) {
        code = codeId;
        e = targetObj;
    }
    
    if(!isEmpty(code)) {
        var whereParams = getWhereParamObj(code);
        var option = '';
        
        $.ajax({
            method: "POST",
            async: false,
            url: "/common/getBizCodeList.do", 
            dataType: "json",
            data: {
                "code": code,
                "whereParams":whereParams
            }, 
            success: function(response) {
                var cmmnBizCodeList = response.cmmnBizCodeList;

                if(cmmnBizCodeList == null) {
                    cmmnBizCodeList = 0;
                }
                
                $(e).find('option').remove();
                if($(e).attr("custom-search")){
                    $(e).append("<option value=''>전체</option>");
                }else {
                    $(e).append("<option value=''>선택하세요</option>");
                }
                
                for(var i=0; i<cmmnBizCodeList.length; i++) {
                    $(e).append("<option value='" + cmmnBizCodeList[i].codeId + "'>" + cmmnBizCodeList[i].codeName + "</option>");
                }
            }, 
            error: function(a) {
                alert(a.message + "(ajaxBizCodeData:"+code+")");
            }
        });
    }
}






/* SWAP TABLE TAB */
$(document).on('click','ul.tab_table_head li',function(e){
    var tab_id = $(this).attr('data-tab');

    $('ul.tab_table_head li').removeClass('active');
    $('.search_table').removeClass('active');

    $(this).addClass('active');
    $("#"+tab_id).addClass('active');
})


/* 글자수 바이트 체크 */
byteCheck = function(el){
    var codeByte = 0;
    for (var idx = 0; idx < el.val().length; idx++) {
        var oneChar = escape(el.val().charAt(idx));
        if ( oneChar.length == 1 ) {
            codeByte ++;
        } else if (oneChar.indexOf("%u") != -1) {
            codeByte += 2;
        } else if (oneChar.indexOf("%") != -1) {
            codeByte ++;
        }
    }
    return codeByte;
}

/* 드롭다운 버튼 메뉴 */
$(document).on("click", ".dropdown_btn",function(e){
    
    $(".dropdown_menus").hide();
    $(this).closest('.dropdown_btn_box').find('.dropdown_menus').fadeIn(100);

    $(document).on("click", function(e){
        var $trigger = $(".dropdown_btn_box");
        if($trigger !== e.target && !$trigger.has(e.target).length){
            $(".dropdown_menus").hide();
        }
    });
})


$('.remark_expand_btn').click(function(e){
    $(this).closest('.remark_expand_btn_box').prev().toggleClass('expand_form_remark')
    var remark_expand_btn_icon = $(this).find('i').attr('class');
    if(remark_expand_btn_icon == "fas fa-angle-down fas_custom"){
        $(this).empty().append('<i class="fas fa-angle-up fas_custom"></i>');
    } else {
        $(this).empty().append('<i class="fas fa-angle-down fas_custom"></i>');
    }
})



// F12 버튼 방지
/*
$(document).bind('keydown',function(e){
    if ( e.keyCode == 123 ) {
        e.preventDefault();
        e.returnValue = false;
    }
});
*/

//BackSpace 키 방지 이벤트
/*
$(document).bind('keydown',function(e){
    if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){
        if(e.keyCode === 8){
            e.preventDefault();
            e.returnValue = false;
        }
    }
    
    // ie에서 readonly일때 처리
    if((e.target.nodeName == "INPUT" || e.target.nodeName == "TEXTAREA") && e.target.readOnly == true){
        if(e.keyCode === 8){
            e.preventDefault();
            e.returnValue = false;
        }
    }
});
*/

// 우측 클릭 방지
/*
document.onmousedown=disableclick;
mouseClickStatus="마우스 오른쪽클릭을 사용하실 수 없습니다.";

function disableclick(event){
    if (event.button==2) {
        //alert(mouseClickStatus);
        return false;
    }
}
*/

// Date Formatter 
function DateFomatter(num){
    if(!num) return "";
    var formatNum = '';
    
    num = num.replace(/\s/gi, "");

    try {
        if(num.length == 8) {
             formatNum = num.replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3');
        }
    } catch(e) {
        formatNum = num;
        console.log(e);
    }
    return formatNum;
}

// 콤마제거
function getNumString(s) {
    var rtn = parseFloat(s.replace(/,/gi, ""));
    if (isNaN(rtn)) {
        return 0;
    } else {
        return rtn;
    }
}

// 콤마 넣기
function setComma(n) {
    var reg = /(^[+-]?\d+)(\d{3})/; 
    n += '';   
    while (reg.test(n)) {
       n = n.replace(reg, '$1' + ',' + '$2');
    }         
    return n;
}

function hideThInput(){
    $('.hdr tr:eq(0) th').each(function(k,v){                    
        var display= $(this).css('display');                
        if( display == 'none' ){
            $('.hdr tr:eq(2) td').eq(k).css('display','none');
        }
    })
}

//주거지원금 > 주택임차지원금 - 우선순위 조회
ajaxHouseLeaseRankInfo = function(tme, totalScore, targetPosCode, targetChangeCode) {
    var totalRank = '0';
    
    if(tme.length < 2) {
        alert("회차 정보가 없습니다.");
        $('.loading_screen').hide();
    }else {
        $.ajax({
            method: "POST", 
            url: "/biz/ha021/getHouseLeaseRankInfo.do", 
            dataType: "json",
            async: false,
            data: {
                "tme" : tme,
                "totalScore" : totalScore,
                "targetPosCode" : targetPosCode,
                "targetChangeCode" : targetChangeCode
            }, 
            success: function(response) {
                totalRank = response.TOTAL_RANK;
            }, 
            error: function(a) {
                alert(a.message);
            }
        }); 
    }

    return totalRank;
}

//주거지원금 > 주택구입지원금 - 우선순위 조회
ajaxHousePurchaseRankInfo = function(tme, totalScore, targetPosCode, targetChangeCode) {
    var totalRank = '0';
    
    if(tme.length < 2) {
        alert("회차 정보가 없습니다.");
        $('.loading_screen').hide();
    }else {
        $.ajax({
            method: "POST", 
            url: "/biz/ha022/getHousePurchaseRankInfo.do", 
            dataType: "json",
            async: false,
            data: {
                "tme" : tme,
                "totalScore" : totalScore,
                "targetPosCode" : targetPosCode,
                "targetChangeCode" : targetChangeCode
            }, 
            success: function(response) {
                totalRank = response.TOTAL_RANK;
            }, 
            error: function(a) {
                alert(a.message);
            }
        }); 
    }

    return totalRank;
}