

$('.sub_body').on('click', 'a.add_fcol_next', function(e) {
    var target_id = $(this).closest('.fcol').attr('data-item_id');  
    generateCreateModal(e,target_id,'next');
})

$('.sub_body').on('click', 'a.add_fcol_last', function(e) {
    var target_id = $(this).closest('.blank_fcol').attr('data-blank_id');
    generateCreateModal(e, target_id,'last');
})


function generateCreateModal(e,target_id,position){
    var new_item_id = generateNewItemId();
    

    $('#item_id').val(new_item_id);
    $('#target_id').val(target_id);
    $('#position').val(position);
    
    //$('#items').show().find('.modal').offset({top: e.pageY,left:e.pageX}).fadeIn(0).draggable(draggable_option);
    $('.modal_box_items').show().find('.modal').fadeIn(0).draggable(draggable_option);
}


// CREATE MODAL SAVE PART
$(document).on('click', '#modal_create .btn_save', function(e) {
    
    e.preventDefault();
    
    var itemIds =  $('input[name="itemId"]');
    
    var itemId = itemIds.filter(':checked').val();
    
    var itemIdIdx = itemIds.index(itemIds.filter(':checked'));
    
    if(itemId == null || itemId == '') {
        alert('아이템을 선택하십시오.');
        return false;
    }
    
    var jsonCodes = $('#templateByItems #jsonCode');
    
    var jsonCode = '';
    $.each(jsonCodes, function(i, e){
        if(itemIdIdx === i) {
            jsonCode = $(e).val();
        }        
    });
    
    /*
    var item_id = $('.modal_box input#item_id').val();
    var target_id = $('.modal_box input#target_id').val();
    var position = $('.modal_box input#position').val();
    */
    
    var item_id = $('#modal_create input#item_id').val();                                                             
    var target_id = $('#modal_create input#target_id').val();
    var position = $('#modal_create input#position').val();
    
    var div = '';
    div += '    <div class="fcol fcol-1 resizable" data-fcolsize="1" data-item_id="' + item_id + '">';
    div += '        <div class="fcol_box">';
    div += '            <div class="edit_delete_box">';
    div += '                <a href="#" class="edit_fcol">';
    div += '                    <span class="oi" data-glyph="cog"></span>';
    div += '                </a>';
    div += '                <a href="#" class="delete_fcol">';
    div += '                    <span class="oi" data-glyph="trash"></span>';
    div += '                </a>';
    div += '            </div>';
    div += '            <div class="input_box">';
    div += '                <div class="builder_box"></div>';
    div += '            </div>';
    div += '            <item id="itemId_' + item_id + '_' + target_id + '" name="itemId"></item>';
    div += '            <json id="jsonData_' + item_id + '_' + target_id + '" name="jsonData"></json>';
    div += '        </div>';
    div += '        <div class="add_fcol_next_box">';
    div += '            <a href="#" class="add_fcol_next">';
    div += '                <img src="/images/icon_plus_small.svg"/>';
    div += '            </a>';
    div += '        </div>';
    div += '    </div>';  
    
    insertFcol(position, target_id, div);
    
    $('#html-output').empty();

    //새 col 포지션
    var el = $('.fcol[data-item_id=' + item_id + ']');
    $('input[name=choice]:checked').closest('.col').find('label').clone().appendTo($(el).find('.input_label_box'));
    $('input[name=choice]:checked').closest('.col').find('.input_content').clone().appendTo($(el).find('.input_content_box'));
    
    $('#html-output').json2html({}, JSON.parse(tagReplaceAll(jsonCode)));
    
    // 팝업 인풋 옮기기
    $('#html-output').children().clone().appendTo($(el).find('.builder_box'));
    
    $('#itemId_' + item_id + '_' + target_id).val(itemId);
    $('#jsonData_' + item_id + '_' + target_id).val(jsonCode);
    
    // fcol resize 활성화
    initializeFcol();
    
    // 모달닫기
    //$('.modal_box').hide();
    $('.modal_box_items').hide()
    
    // itemid 업데이트
    updateItemId();

    initializeSortFcol();

    // fcol 전부 차면 새행추가
    checkBlankFcolAddLineRow(el);
});


function checkBlankFcolAddLineRow(el) {
    var now_blank_fcol = $(el).closest(".line_row").find(".blank_fcol").attr("data-fcolsize");
    var is_next_row = $(el).closest(".line_row").nextAll('.line_row').length;
    if (now_blank_fcol == 0 && !is_next_row) {
      addLineRow(el, "last");
    }
}


var insertFcol = function(position,target_id,div){

    var start_fcol = $('.fcol[data-item_id='+target_id+']');
    var start_line_row = start_fcol.closest('.line_row');
    var start_blank = start_line_row.find('.blank_fcol');
    var start_blank_fcol = Number(start_blank.attr('data-fcolsize'));

    if(position == 'next'){

        var now_last_target = start_line_row.find('.fcol:last');
        var now_last_fcol = now_last_target.attr('data-fcolsize');

        /* 우선 삽입 */
        var target = $('.fcol[data-item_id=' + target_id + ']');
        $(target).before(div);
        
        if(start_blank_fcol == 0){
            /* BLANKCOL이 없으니 다음행 검색*/

            var next_row_check = $(start_fcol).closest('.line_row').nextAll('.line_row:first'); 
            var is_next_row = next_row_check.length;
            var next_row_blank_fcol_check = Number(next_row_check.find('.blank_fcol').attr('data-fcolsize')); 

            if(!is_next_row || next_row_blank_fcol_check < now_last_fcol){               
                // 1) 다음행이 없거나, 2) 다음행 blank-fcol에 여유가 없다. > 새행삽입
                addLineRow(start_fcol,'next');      
            }
            
            // 이쪽 next_row는 만들어졌건 원래있건 공용이다.
            next_row = $(start_fcol).closest('.line_row').nextAll('.line_row:first');
            next_row_blank = next_row.find('.blank_fcol');
            next_row_blank_fcol = Number(next_row_blank.attr('data-fcolsize'));

            $( now_last_target ).prependTo(next_row);

            // 이동 fcol1 이면 추가안해줘도 됨 / 이동 fcol2 이상이면 나가는 콜에서 들어가는콜(1)을 빼주기       
            var new_start_blank_fcol = (now_last_fcol == 1) ? 0 : now_last_fcol - 1             
            updateClass(start_blank, new_start_blank_fcol);
            updateClass(next_row_blank, next_row_blank_fcol - now_last_fcol);

        } else if( start_blank_fcol > 0){

            // blankcol이 있으면 정상적으로 삽입
            var target_total_fcol = getTotalFcol(start_fcol);           
            updateClass(start_blank, fcolumns - target_total_fcol);
        }

    } else if(position == 'last'){

        /* LAST ADD는 이걸로 마무리 */
        var blank_target = $('.blank_fcol[data-blank_id=' + target_id + ']');
        var blank_target_fcol = Number(blank_target.attr('data-fcolsize'));

        updateClass(blank_target, blank_target_fcol - 1);
        $(blank_target).before(div);                    

    }

}


function addLineRow(target, direction){

    var new_blank_id = generateNewBlankId();

    var div = '';
    div += '    <div class="line_row">';
    div += '        <div class="blank_fcol fcol-6 resizable" data-fcolsize="6" data-blank_id="' + new_blank_id + '">';
    div += '            <div class="blank_add_box">';
    div += '                <a href="#" class="add_fcol_last"><div class="icon_round plus_darkgray"></div></a>';
    div += '            </div>';
    div += '            <div class="blank_add_box"><div class="add_fcol_last"><div class="icon_round plus_lightgray"></div></div></div>';
    div += '            <div class="blank_add_box"><div class="add_fcol_last"><div class="icon_round plus_lightgray"></div></div></div>';
    div += '            <div class="blank_add_box"><div class="add_fcol_last"><div class="icon_round plus_lightgray"></div></div></div>';
    div += '            <div class="blank_add_box"><div class="add_fcol_last"><div class="icon_round plus_lightgray"></div></div></div>';
    div += '            <div class="blank_add_box"><div class="add_fcol_last"><div class="icon_round plus_lightgray"></div></div></div>';
    div += '        </div>';
    div += '    </div>';

    if(direction == 'next'){
        var next_target = target.closest('.line_row');
        $(next_target).after(div);

    } else if (direction == 'last'){
        var last_target = $(target).closest('.line_box').find('.line_body');
        $(last_target).append(div);
    }

    initializeSortFcol();
    return new_blank_id;  
}


$('.sub_body').on('click','.delete_fcol',function(){
    var target = $(this).closest('.fcol');
    cbConfirm(
        "삭제",
        "컬럼을 삭제하시겠습니까?",
        function(result) {
            if(result) {
                
                var target_fcol = Number(target.attr('data-fcolsize'));
                var target_blank = target.closest('.line_row').find('.blank_fcol');
                var target_blank_fcol = Number(target_blank.attr('data-fcolsize'));

                updateClass(target_blank, target_blank_fcol + target_fcol);

                target.remove();

                updateItemId();
            }
            $('.modal_box').hide();
        }
    );       
})


$(document).on('click','a.new_line_box_btn',function(e){
        var line_box_count = $('.line_box').length;
        if(line_box_count > 2) {
            var obj = $(this);
            cbConfirm(
                "추가",
                "행그룹을 추가하시겠습니까?",
                function(result) {
                    if(result) {
                        addNewLineBox(e, obj);
                    }
                    $('.modal_box').hide();
                }                
            );
        } else {
            addNewLineBox(e,$(this));
        }
})

function addNewLineBox(e,target){

    var new_blank_id = generateNewBlankId();
    
    var div = '';
    
    div += '<div class="line_box">';
    div += '    <div class="line_head">';
    div += '        <div class="line_head_title">';
    div += '            <div class="new_line_box_btn">';
    div += '                <textarea rows="1" class="line_head_txt_box">직접입력</textarea>';
    div += '            </div>';
    div += '        </div>';
    div += '    </div>';
    div += '    <div class="line_body">';
    div += '        <div class="move_delete_row_box">';
    div += '            <a href="#" class="move_line_row"><span class="oi" data-glyph="move"></span></a>';
    div += '            <a href="#" class="delete_line_box"><span class="oi" data-glyph="trash"></span></a>';
    div += '        </div>';
    div += '        <div class="line_row">';
    div += '            <div class="blank_fcol fcol-6 resizable" data-fcolsize="6" data-blank_id="' + new_blank_id + '">';
    div += '                <div class="blank_add_box">';
    div += '                    <a href="#" class="add_fcol_last"><div class="icon_round plus_darkgray"></div></a>';
    div += '                </div>';
    div += '                <div class="blank_add_box"><div class="add_fcol_last"><div class="icon_round plus_lightgray"></div></div></div>';
    div += '                <div class="blank_add_box"><div class="add_fcol_last"><div class="icon_round plus_lightgray"></div></div></div>';
    div += '                <div class="blank_add_box"><div class="add_fcol_last"><div class="icon_round plus_lightgray"></div></div></div>';
    div += '                <div class="blank_add_box"><div class="add_fcol_last"><div class="icon_round plus_lightgray"></div></div></div>';
    div += '                <div class="blank_add_box"><div class="add_fcol_last"><div class="icon_round plus_lightgray"></div></div></div>';
    div += '            </div>';
    div += '        </div>';
    div += '    </div>';
    div += '</div>';
    
    $(target).closest('.blank_line_box').before(div);
    var el = $('.blank_fcol[data-blank_id=' + new_blank_id + ']');
    var target_id = $(el).closest('.blank_fcol').attr('data-blank_id');

    initializeFcol();
    initializeVcol();

}


$(document).on('click','a.new_hidden_line_box_btn',function(e){
	var line_box_count = $('.line_box').length;
	if(line_box_count > 2) {
		var obj = $(this);
		cbConfirm(
				"추가",
				"HIDDEN 행그룹을 추가하시겠습니까?",
				function(result) {
					if(result) {
						addNewHiddenLineBox(e, obj);
					}
					$('.modal_box').hide();
				}                
		);
	} else {
		addNewHiddenLineBox(e,$(this));
	}
})

function addNewHiddenLineBox(e,target){
	
	var new_blank_id = generateNewBlankId();
	
	var div = '';
	
	div += '<div class="line_box" id="hiddenLineBox">';
	div += '    <div class="line_head">';
	div += '        <div class="line_head_title">';
	div += '            <div class="new_line_box_btn">';
	div += '                <textarea rows="1" class="line_head_txt_box">HIDDEN</textarea>';
	div += '            </div>';
	div += '        </div>';
	div += '    </div>';
	div += '    <div class="line_body">';
	div += '        <div class="move_delete_row_box">';
	div += '            <a href="#" class="move_line_row"><span class="oi" data-glyph="move"></span></a>';
	div += '            <a href="#" class="delete_line_box"><span class="oi" data-glyph="trash"></span></a>';
	div += '        </div>';
	div += '        <div class="line_row">';
	div += '            <div class="blank_fcol fcol-6 resizable" data-fcolsize="6" data-blank_id="' + new_blank_id + '">';
	div += '                <div class="blank_add_box">';
	div += '                    <a href="#" class="add_fcol_last"><div class="icon_round plus_darkgray"></div></a>';
	div += '                </div>';
	div += '                <div class="blank_add_box"><div class="add_fcol_last"><div class="icon_round plus_lightgray"></div></div></div>';
	div += '                <div class="blank_add_box"><div class="add_fcol_last"><div class="icon_round plus_lightgray"></div></div></div>';
	div += '                <div class="blank_add_box"><div class="add_fcol_last"><div class="icon_round plus_lightgray"></div></div></div>';
	div += '                <div class="blank_add_box"><div class="add_fcol_last"><div class="icon_round plus_lightgray"></div></div></div>';
	div += '                <div class="blank_add_box"><div class="add_fcol_last"><div class="icon_round plus_lightgray"></div></div></div>';
	div += '            </div>';
	div += '        </div>';
	div += '    </div>';
	div += '</div>';
	
	$(target).closest('.blank_line_box').before(div);
	
	var el = $('.blank_fcol[data-blank_id=' + new_blank_id + ']');
	
	var target_id = $(el).closest('.blank_fcol').attr('data-blank_id');
	
	initializeFcol();
	initializeVcol();
	
}






$(document).on('click','.delete_line_box',function(e){
    var obj = $(this).closest('.line_box');
    cbConfirm(
        "삭제",
        "행그룹을 삭제하시겠습니까?",
        function(result) {
            if(result) {
                obj.remove();
                updateItemId();
            }
            $('.modal_box').hide();
        }
    );
})





var updateClass = function(el, fcol) {  
    el.css('width','').removeClass(function(index, className) {
        return (className.match(/(^|\s)fcol-\S+/g) || []).join(' ');
    }).addClass('fcol-' + fcol).attr('data-fcolsize',fcol);
};




var getTotalFcol = function(target_div){
    var val = 0;
    $( target_div.closest('.line_row').find('.fcol') ).each(function (e) {
        val += Number($(this).attr('data-fcolsize'));
    });
    return val;
}

var getTotalNextFcol = function(target_div){
    var val = 0;
    $( target_div.nextAll('.fcol') ).each(function (e) {
        val += Number($(this).attr('data-fcolsize'));
    });
    return val;
}

var getTotalPrevFcol = function(target_div){
    var val = 0;
    $( target_div.prevAll('.fcol') ).each(function (e) {
        val += Number($(this).attr('data-fcolsize'));
    });
    return val;         
}



function updateItemId(){
    var i = 1;
    $('.fcol').each(function(i,value){
            i = i+1;    
            $(this).attr('data-item_id',i);
    });
}



function generateNewItemId(){
    var max_id = null;
    $('.fcol').each(function(){
        var value = parseFloat($(this).attr('data-item_id'));       
        max_id = (value > max_id) ? value : max_id;
    });
    return max_id + 10;
}





function generateNewBlankId(){
    var max_id = null;
    $('.blank_fcol').each(function(){
        var value = parseFloat($(this).data('blank_id'));
        max_id = (value > max_id) ? value : max_id;
    });
    var new_blank_id = max_id + 1;
    return new_blank_id;
}






$('.sub_body').on('click','.edit_fcol',function(e){
    var target_id = $(this).closest('.fcol').attr('data-item_id');
    generateEditModal(e, target_id);
})



function generateEditModal(e, target_id){
    
    e.preventDefault();
    
    $active_component = $('.fcol[data-item_id=' + target_id + ']');

    var component = $active_component.find("[component]");
    
    var tags = ['input', 'select', 'textarea'];
    var attributes = ['id', 'name', 'placeholder', 'maxlength', 'data-field', 'event', 'function', 'required', 'readonly', 'search', 'input', 'searchtype'];
    var events = ['onclick', 'onchange', 'onkeypress', 'onkeydown'];

    $.each(component, function(i, e){
        
        //$('#attributes input:text').val('');
        $('#attributes input:checkbox').prop('checked', false); 
        $('#attributes select').val('').prop('selected', true);
        $('#attributes textarea').val('');
        $('#attributes #attrPlaceHolder').val('');
        
        var valID = $(e).attr("component-type");
        var val;

        if(valID !== '' && typeof valID !== 'undefined') { 
            val = $(e).text();
            if(val != '') {
                $('#' + $('[control="' + valID + '"]').attr('id')).val(val);
            } else {
                $('#' + $('[control="' + valID + '"]').attr('id')).val('');
            }
        } else {
            attributes.forEach(function(element){
                
                val = $(e).attr(element);

                attrID = '#' + $('[control="' + element + '"]').attr('id');

                if(element == 'data-field') {
                    if(typeof val != 'undefined') {
                        $(attrID).val(val).prop("selected", true);                    
                    } else {
                        $(attrID).val(val).prop("selected", false);
                    }
                } else if(element == 'required' || element == 'readonly') {
                    if(typeof val != 'undefined') {
                        $(attrID).prop("checked", true);
                    }
                } else if(element == 'required' || element == 'readonly') {
                    $(attrID).prop("checked", true);
                } else if(element == 'search') {
                    var event = $(e).attr('custom-search');
                    if(event == 'true') {
                        $(attrID).prop("checked", true);
                        $("#attrSearchType").show();
                    } else {
                    	$("#attrSearchType").hide();
                    }
                } else if(element == 'searchtype') {
                	var event = $(e).attr('custom-search-type'); // from
                	if(event != '' && typeof event != 'undefined') {
                		$(attrID).val(event).prop("selected", true);
                	}
                } else if(element == 'input') {
                    var event = $(e).attr('custom-input');
                    if(event == 'true') {
                        $(attrID).prop("checked", true);
                    }
                } else if(element == 'id' || element == 'name') {
                    if(typeof val != 'undefined') {
                        $(attrID).val(val);
                    } else {
                        $(attrID).val('');
                    }
                } else if(element == 'placeholder') {
                    if(typeof val != 'undefined') {
                        $(attrID).val(val);
                    } else {
                        $(attrID).val('');
                    }
                } else if(element == 'maxlength') {
                    if(typeof val != 'undefined') {
                        $(attrID).val(val);
                    } else {
                        $(attrID).val('');
                    }
                } else if(element == 'event') {
                    var event = $(e).attr('data-event');
                    $(attrID).val(event).prop("selected", true);
                } else if(element == 'function') {
                    var event = $(e).attr('data-event');
                    var func = $(e).attr('' + event);
                    $(attrID).val(func);
                }
            });
        }
    });
    
    $("#attributes").delegate(".btn_save", "click", function(e){
        
        e.preventDefault();
        
        var inputs = $("#attributes input, select");

        var component = $active_component.find("[component]");
        
        $(inputs.get().reverse()).each(function(i, e){
            
            var custom = $(e).attr("control");
            
            var value = $active_component.find('[component-type="' + custom + '"]');
            if(value !== '' && typeof value !== 'undefined') {
                $(value).text($(e).val());
            }
            
            var tagName;
            $.each(component, function(i, ee){
                
                var tmpTagName = component[i].tagName.toLowerCase();
                
                tags.forEach(function(tag){
                    if(tmpTagName == tag) {
                        tagName = tag;
                        return true;
                    }
                });
            });

            var element = $active_component.find(tagName);

            if(custom == "name") {
                if($(e).val() !== '') {
                    $(element).attr("id", $(e).val());
                    $(element).attr("name", $(e).val());    
                } else {
                    $(element).removeAttr("id");
                    $(element).removeAttr("name");
                } 
            } else if(custom == "data-field") {
                if($(e).val() != '') {
                    $(element).attr("data-field", $(e).val());
                } else {
                    $(element).removeAttr("data-field");
                }
            } else if(custom == 'placeholder') {
            	if($(e).val() != '') {
                    $(element).attr("placeholder", $(e).val());
                } else {
                    $(element).removeAttr("placeholder");
                }
            } else if(custom == 'maxlength') {
            	if($(e).val() != '') {
                    $(element).attr("maxlength", $(e).val());
                } else {
                    $(element).removeAttr("maxlength");
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
            } else if(custom == "input") {
                attrID = $('[control="' + custom + '"]').attr('id');
                if($('input:checkbox[id="' + attrID + '"]').is(":checked") == true) {
                    $(element).attr("custom-input", true);
                } else {
                    $(element).removeAttr("custom-input");
                } 
            } else if(custom == "search") {
                attrID = $('[control="' + custom + '"]').attr('id');
                if($('input:checkbox[id="' + attrID + '"]').is(":checked") == true) {
                    $(element).attr("custom-search", true);
                    var searchType = $("#attrSearchType option:selected").val();
                    if(searchType != '') {
                    	$(element).attr("custom-search-type", searchType);
                    } else {
                    	$(element).removeAttr("custom-search-type");
                    }
                } else {
                    $(element).removeAttr("custom-search");
                    $(element).removeAttr("custom-search-type");
                }  
            } else if(custom == 'event') {
                
                events.forEach(function(event){
                    $(element).removeAttr(event);
                });
                $(element).removeAttr('data-event');
                
                attrID = $('[control="' + custom + '"]').attr('id');
                var tmpValue = $('#' + attrID + ' option:selected').val();

                if(tmpValue != '') {
                    if($('#attrEventFunction').val() == '') {
                        alert('함수를 입력하십시오.');
                        return false;
                    }
                    
                    $(element).attr('data-event', tmpValue);
                    var button = $active_component.find('.btn').html();
                    if(typeof button != 'undefined') {
                        $active_component.find('.btn').attr(tmpValue, $('#attrEventFunction').val());
                    } else {
                        $(element).attr(tmpValue, $('#attrEventFunction').val());
                    }
                    
                } 
                
            }

        });
        
        //var html = $active_component.find('.builder_box').html();       
        var html = $active_component.find('.builder_box');
        var jsonData = settingJsonFormatForBuilderFunc(html);
        $active_component.find('[name="jsonData"]').val(jsonData); 
        
        //$('.modal_box').hide();
        var div = $('#attributes');
    });
       

    // 체크박스 토글화
    //$.switcher('.modal_checkbox_box1 input[type=checkbox]');
    //$('.modal_box').show().empty().append(div).find('#modal').offset({top: e.pageY,left:e.pageX}).fadeIn(0).draggable(draggable_option);    
    //$('.modal_box').show().empty().append(div).find('.modal').offset({top: e.pageY,left:e.pageX}).fadeIn(0).draggable(draggable_option);
    //$('#attributes').show().find('#modal_edit').offset({top: e.pageY,left:e.pageX}).fadeIn(0).draggable(draggable_option);
    
    $('.modal_box_attributes').show().find('#modal_edit').fadeIn(0).draggable(draggable_option);
}


var initializeSortRow = function(){
    $( ".sub_body" ).sortable({
        handle: ".move_line_row",
        items: ".line_box:not(.blank_line_box)",
        start: function( e, ui ) {
            clone = $(ui.item[0].outerHTML).clone();
        },
        placeholder: {
            element: function(clone, ui) {
              return $('<div class="line_box placeholder_box"><div class="line_box_screen"></div>'+clone[0].innerHTML+'</div>');
            },
            update: function() {
              return;
            }
        },
        stop: function(e,ui){
            updateItemId();
        }
    });
}








var initializeFcol = function(){
    
var
    resizableEl = $('.resizable').not(':last-child'),
     fullWidth,
    fullWidth,
     fcolumnWidth,
    totalFcol,
     blankFcol_Start;

    resizableEl.resizable({
    handles: 'e',      
    start: function(event, ui) {

                //반응형에 맞추어 START에서 FULLWIDTH 재조정
                fullWidth = resizableEl.parent().width();
                fcolumnWidth = fullWidth / fcolumns;

                target = ui.element;
                blank = target.closest('.line_row').find('.blank_fcol');
                blankFcol_Start = Math.round(blank.width() / fcolumnWidth);
                targetFcol = Math.round(target.width() / fcolumnWidth);
                totalFcol = targetFcol + blankFcol_Start;

                if(blankFcol_Start == 0){
                    
                    next = target.next();       
                    nextFcol = Math.round(next.width() / fcolumnWidth);             
                    totalFcol = targetFcol + nextFcol;
                    target.resizable('option', 'minWidth', fcolumnWidth - 0.1);

                    if(totalFcol==1){
                        // MAX값 / 만약 마지막쪽 셀이고 블랭크가 없을경우 : 0 방지함
                       target.resizable('option', 'maxWidth', (1 * fcolumnWidth));
                    } else {
                        target.resizable('option', 'maxWidth', ((totalFcol - 1) * fcolumnWidth));
                    }

                } else {                    
                    target.resizable('option', 'minWidth', fcolumnWidth);
                    target.resizable('option', 'maxWidth', ((totalFcol - 0) * fcolumnWidth));

                }

                // 타겟 col 하이라이트 스타트
                target.find('.fcol_box').addClass('highlight');
                // 우측 col 호버시 하이라이트 방지
                target.next().find('.fcol_box').addClass('no_highlight');


    },

    resize: function(event, ui) {
                
                target = ui.element;
                blank = target.closest('.line_row').find('.blank_fcol');
                targetFcolumnCount = Math.round(target.width() / fcolumnWidth);
                blankFcolumnCount = Math.round(blank.width() / fcolumnWidth);                           
                targetSet = totalFcol - blankFcolumnCount;
                blankSet = totalFcol - targetFcolumnCount;

                if(blankFcol_Start == 0){
                    next = target.next();       
                    nextFcolumnCount = Math.round(next.width() / fcolumnWidth);
                    targetSet = totalFcol - nextFcolumnCount;
                    nextSet = totalFcol - targetFcolumnCount;

                    updateClass(next,nextSet);
                    updateClass(target,targetSet);
                } else {
                    updateClass(blank,blankSet);
                    updateClass(target,targetSet);

                }
    },

     stop:function(event,ui){
            target = ui.element;
            blank = target.closest('.line_row').find('.blank_fcol');
            targetFcolumnCount = Math.round(target.width() / fcolumnWidth);
            blankFcolumnCount = Math.round(blank.width() / fcolumnWidth);
            blankSet = totalFcol - targetFcolumnCount;
            targetSet = totalFcol - blankFcolumnCount;

                if(blankFcol_Start == 0){
                    next = target.next();       
                    nextFcolumnCount = Math.round(next.width() / fcolumnWidth),                         
                    targetSet = totalFcol - nextFcolumnCount,
                    nextSet = totalFcol - targetFcolumnCount;
                    updateClass(next,nextSet);

                } else {
                    updateClass(blank,blankSet);
                }

                // 마지막이면 row 추가
                var is_next_row = $(target).closest('.line_row').nextAll('.line_row').length;
                if(blankSet == 0 && !is_next_row){
                    addLineRow(target,'last');
                }



                // 타겟 col 하이라이트 제거
                target.find('.fcol_box').removeClass('highlight');

                // 우측 col 호버시 하이라이트 방지 해제
                target.next().find('.fcol_box').removeClass('no_highlight');



        }
  });
}








var initializeVcol = function(){
    var lineBodyEl = $('.line_box .line_body').not('.blank_line_box .line_body'),   
    new_pageY = 0,
    saved_pageY = 0;

    $(lineBodyEl).attr('style', 'height:  !important');



    lineBodyEl.resizable({
        handles: 's',     
        grid: 90,
        start: function(e,ui){
                var target = ui.element;

                var last_fcol_position_index = $(this).find('.fcol_box')
                                                        .last().closest('.line_row').prevAll('.line_row').length + 1;
    
                target.resizable('option', 'minHeight', 90 * last_fcol_position_index);     
                target.resizable('option', 'maxHeight', (90 * last_fcol_position_index) + 90);
                //Max Height 제한 1개      

                // PAGEY 기준점 설정
                saved_pageY = e.pageY;
        },

        resize: function(e,ui){
                var target = ui.element;
                new_pageY = e.pageY;

                if(new_pageY > saved_pageY){
                    //EXPAND    
                    addLineRow(target,'last');
                
                } else if(new_pageY < saved_pageY) {                    
                    // SHRINK           
                    if( $(this).find('.line_row:last .blank_fcol').data('fcolsize') == fcolumns ){
                        $(this).find('.line_row:last').remove();
                    }
                }

                //UPDATE
                saved_pageY = new_pageY;

        },
        stop: function(e,ui){
            /*
            1. 확장 후 한행 추가시 height가 고정되어 뒤에 line_body가 확장안되게 고정되는것 해제
            2. 지나치게 빠르게 드래그했을 때 2개이상 붙었을 시에 제거해줌
            */
            $(lineBodyEl).attr('style', 'height:  !important');
            

        }
     })
}




var initializeSortFcol = function () {

    var target_div,
        getIndex = function (el) {
            start_row_index = el.closest('.line_row').index('.line_row');
            start_fcol_index = el.index();
            return start_row_index + '' + start_fcol_index;
        },
        revertFcol = function (el, start_blank, start_blank_fcol) {
            $(el).sortable("cancel");
            updateClass(start_blank, start_blank_fcol);
        },
        placeholderGreen = function (el) {
            $(el).find('.fcol_placeholder').css('background-color', '#44C8F5AA');
        },
        placeholderRed = function (el) {
            $(el).find('.fcol_placeholder').css('background-color', '#ff7878');
        };

    $(".line_row").sortable({
        items: ".fcol",
        connectWith: ".line_row",
        start: function (e, ui) {

            start_div = $(ui.item);
            start_index = getIndex(start_div);
            start_prev_fcol = getTotalPrevFcol(start_div);

        },
        placeholder: {
            element: function (clone, ui) {

                start_fcol = Number($(clone).attr('data-fcolsize'));
                start_blank = $(clone).closest('.line_row').find('.blank_fcol');
                start_blank_fcol = Number(start_blank.attr('data-fcolsize'));
                start_blank_id = Number(start_blank.attr('data-blank_id'));

                var place_div =
                    '<div class="fcol_placeholder_box"><div class="fcol_placeholder"></div></div>';
                return $(place_div);
            },
            update: function (e, ui, clone) {
                updateClass(start_blank, start_blank_fcol + start_fcol);
                return;
            }
        },

        change: function (e, ui) {

            target_div = $(ui.placeholder);
            target_index = getIndex(target_div);
            target_blank = $(target_div).closest('.line_row').find('.blank_fcol');
            target_blank_fcol = Number(target_blank.attr('data-fcolsize'));

            //이동 당하는 col들 값얻기
            var target_next_fcol = getTotalNextFcol(target_div);

            if (start_fcol <= target_blank_fcol) {
                placeholderGreen(ui.placeholder);
            } else if (start_fcol > target_blank_fcol) {
                var remain_fcol = target_next_fcol + target_blank_fcol;
                if (start_fcol > remain_fcol) {
                    placeholderRed(ui.placeholder);
                } else {
                    if (start_index > target_index) {
                        placeholderGreen(ui.placeholder);
                    } else if (start_index < target_index) {
                        placeholderRed(ui.placeholder)
                    }
                }
            }

            return;
        },

        stop: function (e, ui) {

            target_div = $(ui.item);
            target_index = getIndex(target_div);
            target_blank = $(target_div).closest('.line_row').find('.blank_fcol');
            target_blank_fcol = Number(target_blank.attr('data-fcolsize'));
            target_blank_id = Number(target_blank.attr('data-blank_id'));

            //이동 당하는 col들 값얻기
            var target_next_fcol = getTotalNextFcol(target_div);

            if (start_fcol <= target_blank_fcol) {
                // 블랭크 여유가 있는경우 블랭크에 삽입

                updateClass(target_blank, target_blank_fcol - start_fcol);

            } else if (start_fcol > target_blank_fcol) {
                // 블랭크 여유가 없는경우

                var remain_fcol = target_next_fcol + target_blank_fcol;
                if (start_fcol > remain_fcol) {
                    //자리없음  
                    revertFcol(this, start_blank, start_blank_fcol);
                } else {
                    //자리 만들 수 있음

                    if (start_index > target_index) {
                        //방향 뒤로옴

                        var next_blank;
                        var target_prev_fcol = getTotalPrevFcol(target_div);
                        var new_target_blank_fcol = (fcolumns - start_fcol) - target_prev_fcol;

                        $(target_div.nextAll('.fcol')).each(function (e) {
                            var current_fcol = $(this).attr('data-fcolsize');
                            if ((new_target_blank_fcol - current_fcol) < 0) {

                                var next_row = $(this).closest('.line_row').nextAll('.line_row:first');
                                next_blank = next_row.find('.blank_fcol');

                                var cnt_start_line_fcol = $(start_blank).closest('.line_row').find('.fcol').length
                                if (cnt_start_line_fcol > 0) {
                                    // 시작행에 fcol이 있으므로 fcol 붙이기
                                    $(this).insertBefore(next_row.find('.fcol:last'));
                                } else if (cnt_start_line_fcol == 0) {
                                    // 시작행에 fcol이 없으므로 blank 붙이기
                                    $(this).insertBefore(next_row.find('.blank_fcol'));

                                }
                            }
                            new_target_blank_fcol = new_target_blank_fcol - current_fcol;
                        });

                        updateClass(target_blank, fcolumns - getTotalPrevFcol(target_blank));
                        updateClass(next_blank, fcolumns - getTotalPrevFcol(next_blank));

                    } else if (start_index < target_index) {
                        //방향 앞으로감
                        revertFcol(this, start_blank, start_blank_fcol);
                    }
                }
            }

            if (target_blank_fcol == fcolumns) {
                // 목적지에 콜이 없는 경우 우측으로 가는것 방지    
                var move_left = $(start_div).closest('.line_row');
                $(start_div).prependTo(move_left);
            }

            //ID 재조정
            updateItemId();
        }
    });
}


$(document).on('focus','#modal_create select, #modal_create input', function(e){
// 팝업에서 INPUT,SELECT 포커스시에 자동으로 RADIO버튼 활성화
    $(this).closest('.input_box').find('input[type=radio]').prop("checked",true);
})



$(document).on('click', '#modal_edit .btn_save', function(e) {
    // EDIT-MODAL SAVE PART
    $('.modal_box_attributes').hide();
})



$(document).on('click', '.modal_close_items', function(e) {
    $('.modal_box_items').hide();   
})

$(document).on('click', '.modal_close_attributes', function(e) {
    $('.modal_box_attributes').hide();
})




 
/* GLOBAL VARIABLE */
var fcolumns = 6;

// STARTUP
$(function() {

     initializeVcol();
     initializeFcol();
     initializeSortRow();
     initializeSortFcol();

});






/* ITEM - STEP TAB */
$(document).on('click','ul.item_step li',function(e){

    var old_id = $('ul#item_step').find('li.active').attr('data-tab_id');
    var new_id = $(this).attr('data-tab_id');

    if($(this).hasClass('next_complete')){

        if(new_id > old_id){
            var is_required_filled = checkRequired(old_id,new_id);
            if(!is_required_filled){
                e.preventDefault();
            }
        }

        clearStepTab();
        stepActiveClass(new_id,old_id);

        checkFinalAndChangeNextToComplete(new_id);
    }

})



$(document).on('click','.item_nav',function(e){

    var direction = $(this).attr('data-direction');
    var old_id = parseFloat($('ul#item_step').find('li.active').attr('data-tab_id'));
    var next_id = parseFloat($('ul#item_step').find('li.active').next().attr('data-tab_id'));
    var prev_id = parseFloat($('ul#item_step').find('li.active').prev().attr('data-tab_id'));
    var serviceTypeCode = $('select#serviceTypeCode').val();

    if( direction == 'next'){

            var is_required_filled = checkRequired(old_id,next_id);
            if(!is_required_filled){            
                e.preventDefault();
            } else if(old_id == 5) {
                e.preventDefault();
            } else if(old_id !== 5) {

                    clearStepTab();

                    if( old_id == 2 && serviceTypeCode == "GENERAL" ){

                        stepActiveClass(4,2);   
                        $('ul#item_step li[data-tab_id='+ 4 +']').addClass('next_complete');
                        $('ul#item_step li[data-tab_id='+ 2 +']').addClass('next_complete');
                    
                    } else {
                    
                        stepActiveClass(next_id,old_id);    
                        $('ul#item_step li[data-tab_id='+old_id+']').addClass('next_complete');
                        $('ul#item_step li[data-tab_id='+next_id+']').addClass('next_complete');
                    }
            }
            checkFinalAndChangeNextToComplete(next_id);

    } else if(direction == 'prev'){
            if(old_id == 1){
                e.preventDefault();
            } else if(old_id !== 1) {
                clearStepTab();
                if( old_id == 4 && serviceTypeCode == "GENERAL" ){
                    stepActiveClass(2,4);
                } else {
                    stepActiveClass(prev_id,old_id);
                }
            }
            checkFinalAndChangeNextToComplete(prev_id);
    }

})



function checkRequired(old_id){
    var required_check = true;
    if( old_id == 1 | old_id == 2 ){
        var all_required = $('.step_content[data-tab_content_id='+old_id+'] input').filter('[required]');
        all_required.each(function(){
                if($(this).val() == ''){
                        $(this).addClass('required_input');
                        required_check = false;
                }
        });
    }
    return required_check;
}


$(document).on('input','input.required_input',function(){
        $(this).removeClass('required_input');
})



$(document).on('change','select#serviceTypeCode',function(){
    if(this.value == "GENERAL"){
        $('li.step_tab[data-tab_id=3]').removeClass('next_complete');
    }
})




function stepActiveClass(new_id,old_id){

    $('.step_tab[data-tab_id='+new_id+']').addClass('active');
    $('.step_content[data-tab_content_id='+old_id+']').removeClass('active');
    $('.step_content[data-tab_content_id='+new_id+']').addClass('active');

    $('.step_tab').each(function(i){
        var id = $(this).attr('data-tab_id')
        if(new_id > id){
            $(this).addClass('done')
        }
    });


    var serviceTypeCode = $('select#serviceTypeCode').val();
    if(serviceTypeCode == "GENERAL"){
        $('li.step_tab[data-tab_id=3]').removeClass('done');
    }
}





function clearStepTab(){
    $('.step_tab').removeClass('active');
    $('.step_tab').removeClass('done');
}



function checkFinalAndChangeNextToComplete(new_id){
    if(new_id == 5){
        var div = '<button class="item_nav trans_btn" data-direction="complete" onclick="javascript:onComplete();">완료</button>';
        $('.item_nav[data-direction="next"]').replaceWith(div);
    } else {
        var div = '<button class="item_nav trans_btn" data-direction="next">다음 ▶</button>';
        $('.item_nav[data-direction="complete"]').replaceWith(div);
    }
}




/* ITEM - FULL PAGE TAB */

$('ul.item_builder_tabs li').click(function(){
    
    var tab_id = $(this).attr('data-tab');
    $('ul.item_builder_tabs li').removeClass('active');
    $('.item_builder_content').removeClass('active');
    $(this).addClass('active'); 
    $("#"+tab_id).addClass('active');

})



