insert into user values(90001, sysdate(), 'User1', 'test1111', '701010-1111111');
insert into user values(90002, sysdate(), 'User2', 'test2222', '801010-2222222');
insert into user values(90003, sysdate(), 'User3', 'test3333', '901010-1111111');

insert into post values(10001, 'My first post', 90001);
insert into post values(10002, 'My second post', 90001);

CREATE TABLE WM_ITEM_TMPL (
	COMPANY_CODE VARCHAR(50) NOT NULL,
	ITEM_TMPL_ID VARCHAR(33) NOT NULL,
	ITEM_TMPL_NAME VARCHAR(180) NOT NULL,
	ITEM_TYPE VARCHAR(20),
	HTML VARCHAR(12000),
	JSON VARCHAR(12000) NOT NULL,
	USE_AT CHAR(1) NOT NULL,
	CREATE_USER VARCHAR(20),
	CREATE_TIME DATE,
	UPDATE_USER VARCHAR(20),
	UPDATE_TIME DATE,
	CONSTRAINT PK_WM_ITEM_TMPL PRIMARY KEY (COMPANY_CODE,ITEM_TMPL_ID)
);


INSERT INTO WM_ITEM_TMPL (COMPANY_CODE,ITEM_TMPL_ID,ITEM_TMPL_NAME,ITEM_TYPE,HTML,JSON,USE_AT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) VALUES
	 ('H101','8a82336ccfc24da7bb529c025fb68a05','[공통] HIDDEN TEXT','INPUT','<div class="input_box col-12"><div class="input_content"><input type="hidden" component=""></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"hidden","component":"","html":""}
      ]}
  ]}','Y','HIST1118','2020-02-17 00:00:00',NULL,NULL),
	 ('H101','0a9644a46d4f49a997c592e921d62d0c','[공통] 라디오버튼','RADIO','<div class="input_box col-12"><label for="" component-type="label" component="">Label</label><div class="form_radio_box"><div class="reserve_checkbox_box"><input type="radio" id="a1" name="reserve" value="a1" component=""><label for="a1">option1</label></div><div class="reserve_checkbox_box"><input type="radio" id="a2" name="reserve" value="a2" component=""><label for="a2">option2</label></div></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"Label"},
    {"<>":"div","class":"form_radio_box","html":[
        {"<>":"div","class":"reserve_checkbox_box","html":[
            {"<>":"input","type":"radio","id":"a1","name":"reserve","value":"a1","component":"","html":""},
            {"<>":"label","for":"a1","html":"option1"}
          ]},
        {"<>":"div","class":"reserve_checkbox_box","html":[
            {"<>":"input","type":"radio","id":"a2","name":"reserve","value":"a2","component":"","html":""},
            {"<>":"label","for":"a2","html":"option2"}
          ]}
      ]}
  ]}','Y','HIST1118','2020-01-18 00:00:00','HIST1118','2020-02-05 00:00:00'),
	 ('H101','5ab462b997554e88941519866a6ba2c7','[공통] 라벨','USER_CUSTOM','<div class="input_box col-12"><label for="" component-type="label" component="">Label</label></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"Label"}
  ]}','Y','HIST1118','2020-02-20 00:00:00',NULL,NULL),
	 ('H101','360aab950a124fb8bf9ffc4c294e9c79','[공통] 버튼','BUTTON','<div class="input_content"><button class="trans_btn" id="" component-type="label">버튼</button></div>','{"<>":"div","class":"input_content","html":[
    {"<>":"button","class":"trans_btn","id":"","component-type":"label","html":"버튼"}
  ]}','Y','HIST1118','2020-02-12 00:00:00','HIST1118','2020-02-13 00:00:00'),
	 ('H101','4b9854961c51435986639e0f28346879','[공통] 빈영역','USER_CUSTOM','<div style="height: 100%; width: 100%"><div></div></div>','{"<>":"div","style":"height: 100%; width: 100%","html":[
    {"<>":"div","html":""}
  ]}','Y','HIST1118','2020-02-11 00:00:00','HIST1118','2020-02-13 00:00:00'),
	 ('H101','9aeae3a8029147e89f3e770d07d6301e','[공통] 셀렉트 박스','SELECT','<div class="input_box col-12"><label for="" component-type="label" component="">Label</label><div class="input_content"><select-custom class="select_option" component=""><option value="1">option</option></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"Label"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","html":[
            {"<>":"option","value":"1","html":"option"}
          ]}
      ]}
  ]}','Y','HIST1118','2019-12-11 00:00:00','HIST1118','2020-02-05 00:00:00'),
	 ('H101','27f134052225462aa134324416900169','[공통] 입력박스','INPUT','<div class="input_box col-12"><label for="" component-type="label" component="">Label</label><div class="input_content"><input type="text" component=""></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"Label"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","component":"","html":""}
      ]}
  ]}','Y','HIST1118','2020-01-17 00:00:00','HIST1118','2020-02-05 00:00:00'),
	 ('H101','74292a606829495cb17f2aabaf706705','[공통] 입력박스(검색)','INPUT','<div class="input_box col-12"><label for="" component-type="label" component="">Label</label><div class="input_content"><input type="text" component=""><i class="fas fa-search"></i></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"Label"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","component":"","html":""},
        {"<>":"i","class":"fas fa-search","html":""}
      ]}
  ]}','Y','HIST1118','2019-12-11 00:00:00','HIST1118','2020-02-05 00:00:00'),
	 ('H101','bc308be12f6141c68064dbd18332b8b0','[공통] 입력박스(날짜)','INPUT','<div class="input_box col-12">
    <label for="" component-type="label" component="">Label</label>
    <div class="input_content">
        <input type="text" data-toggle="datepicker" component="">
        <i class="far fa-calendar-alt"></i>
    </div>
</div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"Label"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","data-toggle":"datepicker","component":"","html":""},
        {"<>":"i","class":"far fa-calendar-alt","html":""}
      ]}
  ]}','Y','HIST1118','2020-01-29 00:00:00','HIST1118','2020-02-05 00:00:00'),
	 ('H101','df33a02b5d7949489541d76078fdb375','[공통] 체크박스','CHECKBOX','<div class="input_box col-12"><label for="" component-type="label" component>Label</label>
<div class="form_radio_box">
	<input type="checkbox" id="array1" component>
	<label for="array1" style="padding: 5px 15px 0 0;">option1</label>
	<input type="checkbox" id="array2" component>
	<label for="array2" style="padding: 5px 15px 0 0;">option2</label>
</div>
</div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"Label"},
    {"<>":"div","class":"form_radio_box","html":[
        {"<>":"input","type":"checkbox","id":"array1","component":"","html":""},
        {"<>":"label","for":"array1","style":"padding: 5px 15px 0 0;","html":"option1"},
        {"<>":"input","type":"checkbox","id":"array2","component":"","html":""},
        {"<>":"label","for":"array2","style":"padding: 5px 15px 0 0;","html":"option2"}
      ]}
  ]}','Y','HIST1118','2020-02-05 00:00:00',NULL,NULL);
INSERT INTO WM_ITEM_TMPL (COMPANY_CODE,ITEM_TMPL_ID,ITEM_TMPL_NAME,ITEM_TYPE,HTML,JSON,USE_AT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) VALUES
	 ('H101','58cec919c97349a7b660f9b0c3bbdf66','[공통] 파일업로드','INPUT','<div class="input_box col-12"><label for="" component-type="label" component="">LABEL</label><div class="input_content upload_solo_box"><input type="file" class="upload_solo" id="upload_solo" component=""><button class="trans_btn" type="button">+</button>
</div>
<div class="input_content" id="attachFiles"> </div>
</div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"LABEL"},
    {"<>":"div","class":"input_content upload_solo_box","html":[
        {"<>":"input","type":"file","class":"upload_solo","id":"upload_solo","component":"","html":""},
        {"<>":"button","class":"trans_btn","type":"button","html":"+"}
      ]},
    {"<>":"div","class":"input_content","id":"attachFiles","html":" "}
  ]}','Y','HIST1118','2020-01-21 00:00:00','HIST1118','2020-02-05 00:00:00'),
	 ('H101','ef5ce7832c944c3483f91242c950122f','[버튼] 미리보기','BUTTON','<div class="input_content"><button class="trans_btn" id="" component="" component-type="label">미리보기</button></div>','{"<>":"div","class":"input_content","html":[
    {"<>":"button","class":"trans_btn","id":"","component":"","component-type":"label","html":"미리보기"}
  ]}','Y','HIST1118','2020-02-13 00:00:00','HIST1118','2020-02-20 00:00:00'),
	 ('H101','0bddeefe082a47a6a3b205c1f599590c','textarea','TEXTAREA','<div class="input_box col-12"><label for="" component-type="label" component="">소분류 코드</label><div class="input_content"><textarea id="" class="left-html" rows="10" cols="50" style="border:1px solid rgba(255,255,255,0.3);padding:5px 12px;"  component=""></textarea></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"소분류 코드"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"textarea","id":"","class":"left-html","rows":"10","cols":"50","style":"border:1px solid rgba(255,255,255,0.3);padding:5px 12px;","component":"","html":""}
      ]}
  ]}','Y','HIST1118','2020-02-19 00:00:00','HIST1118','2020-02-19 00:00:00'),
	 ('H101','9a12efa3fc024035b835e0aaae98a234','이미지','USER_CUSTOM','<div style="height: 100%; width: 100%"><div>
<a target="_blank" href=""><img src="" border="0" alt=""></a>
</div></div>','{"<>":"div","style":"height: 100%; width: 100%","html":[
    {"<>":"div","html":[
        {"<>":"a","target":"_blank","href":"","html":[
            {"<>":"img","src":"","border":"0","alt":"","html":""}
          ]}
      ]}
  ]}','Y','HIST1118','2020-05-27 00:00:00',NULL,NULL),
	 ('H101','7bb5ec72ddd94091803a5a9c27793d2f','입력박스','INPUT','<div class="input_box col-4"><label for="" component-type="label" component="">label</label><div class="input_content"><input type="text" component=""></div></div>','{"<>":"div","class":"input_box col-4","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"label"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","component":"","html":""}
      ]}
  ]}','Y','HIST1118','2020-04-10 00:00:00',NULL,NULL);





  
CREATE TABLE WM_ITEM (
	COMPANY_CODE VARCHAR(50) NOT NULL,
	ITEM_ID VARCHAR(33) NOT NULL,
	ITEM_NAME VARCHAR(180) NOT NULL,
	ITEM_TMPL_ID VARCHAR(33) NOT NULL,
	ITEM_DESC VARCHAR(1500),
	HTML TEXT,
	JSON TEXT,
	ORIGIN_JSON TEXT,
	USE_AT CHAR(1) NOT NULL,
	ATTR_LABEL VARCHAR(1500),
	ATTR_CLASS VARCHAR(60),
	ATTR_NAME VARCHAR(60),
	ATTR_REQUIRED_AT CHAR(1),
	ATTR_HELPTEXT VARCHAR(1500),
	ATTR_PLACEHOLDER VARCHAR(1500),
	ATTR_VALUE VARCHAR(180),
	ATTR_READONLY_AT CHAR(1),
	ATTR_OPTIONS VARCHAR(1500),
	ATTR_TYPE VARCHAR(30),
	ATTR_STYLE VARCHAR(60),
	ATTR_TOGGLE_AT CHAR(1),
	ATTR_INLINE_AT CHAR(1),
	ATTR_MAXLENGTH VARCHAR(4),
	ATTR_ROWS VARCHAR(4),
	ATTR_MIN VARCHAR(4),
	ATTR_MAX VARCHAR(4),
	ATTR_STEP VARCHAR(4),
	ATTR_ACCESS_AT CHAR(1),
	ATTR_ACCESS_ID VARCHAR(500),
	CREATE_USER VARCHAR(20),
	CREATE_TIME DATE,
	UPDATE_USER VARCHAR(20),
	UPDATE_TIME DATE,
	CONSTRAINT PK_WM_ITEM PRIMARY KEY (COMPANY_CODE,ITEM_ID)
);


INSERT INTO WM_ITEM (COMPANY_CODE,ITEM_ID,ITEM_NAME,ITEM_TMPL_ID,ITEM_DESC,HTML,JSON,ORIGIN_JSON,USE_AT,ATTR_LABEL,ATTR_CLASS,ATTR_NAME,ATTR_REQUIRED_AT,ATTR_HELPTEXT,ATTR_PLACEHOLDER,ATTR_VALUE,ATTR_READONLY_AT,ATTR_OPTIONS,ATTR_TYPE,ATTR_STYLE,ATTR_TOGGLE_AT,ATTR_INLINE_AT,ATTR_MAXLENGTH,ATTR_ROWS,ATTR_MIN,ATTR_MAX,ATTR_STEP,ATTR_ACCESS_AT,ATTR_ACCESS_ID,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) VALUES
	 ('H101','89ae6e6771e54b52953beee7791aabf9','[공통] 2Depth 셀렉트박스','9aeae3a8029147e89f3e770d07d6301e','[공통] 2Depth 셀렉트박스','<div class="input_box col-12"><label for="" component-type="label" component="">Label</label><div class="input_content"><select-custom class="select_option" component="" label="Label" id="2depthSelect" name="2depthSelect"></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"Label"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","label":"Label","id":"2depthSelect","name":"2depthSelect","html":""}
      ]}
  ]}',NULL,'Y','Label','select_option','2depthSelect',NULL,NULL,NULL,NULL,NULL,'SELECT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST2111','2020-02-05 00:00:00',NULL,NULL),
	 ('H101','4d971ce9a630499d8fca56cce70bfdd3','[공통] TEXTAREA','0bddeefe082a47a6a3b205c1f599590c',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">TEXTAREA</label><div class="input_content"><textarea id="textArea" class="left-html" rows="10" cols="50" style="border:1px solid rgba(255,255,255,0.3);padding:5px 12px;" component="" name="textArea"></textarea></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"TEXTAREA"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"textarea","id":"textArea","class":"left-html","rows":"10","cols":"50","style":"border:1px solid rgba(255,255,255,0.3);padding:5px 12px;","component":"","name":"textArea","html":""}
      ]}
  ]}',NULL,'Y','TEXTAREA','left-html','textArea',NULL,NULL,NULL,NULL,NULL,'TEXTAREA|',NULL,'border:1px solid rgba(255,255,255,0.3);padding:5px 12px;',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-19 00:00:00',NULL,NULL),
	 ('H101','b6bd7558c2a7404c99ffb843cb301e0c','[ 공통 ] 직급','9aeae3a8029147e89f3e770d07d6301e',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">직급</label><div class="input_content"><select-custom class="select_option" component="" commcode="POSITION_CODE" label="직급" id="posCode" name="posCode"></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"직급"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","commcode":"POSITION_CODE","label":"직급","id":"posCode","name":"posCode","html":""}
      ]}
  ]}',NULL,'Y','직급','select_option','posCode',NULL,NULL,NULL,NULL,NULL,'CODE|COMMON_CODE:POSITION_CODE',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-24 00:00:00',NULL,NULL),
	 ('H101','ab32708920544499982271e43cb5b100','[공통] 입력박스(검색)','74292a606829495cb17f2aabaf706705','[공통] 입력박스(검색)','<div class="input_box col-12"><label for="" component-type="label" component="">LABEL</label><div class="input_content"><input type="text" autocomplete="off" component="" id="schLabel" name="schLabel"><i class="fas fa-search"></i></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"LABEL"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","autocomplete":"off","component":"","id":"schLabel","name":"schLabel","html":""},
        {"<>":"i","class":"fas fa-search","html":""}
      ]}
  ]}',NULL,'Y','LABEL',NULL,'schLabel',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-05 00:00:00','HIST1118','2020-02-05 00:00:00'),
	 ('H101','1ebb0393e2474737833d2ba85d2a112a','[공통] 이름','27f134052225462aa134324416900169',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">이름</label><div class="input_content"><input type="text" autocomplete="off" component="" id="UserName" name="UserName"></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"이름"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","autocomplete":"off","component":"","id":"UserName","name":"UserName","html":""}
      ]}
  ]}',NULL,'Y','이름',NULL,'UserName',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-11 00:00:00','HIST1118','2020-03-14 00:00:00'),
	 ('H101','8b21825efcc440d6b37bd197e8853875','[공통] 직급','27f134052225462aa134324416900169',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">직급</label><div class="input_content"><input type="text" autocomplete="off" component="" id="PosName" name="PosName"></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"직급"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","autocomplete":"off","component":"","id":"PosName","name":"PosName","html":""}
      ]}
  ]}',NULL,'Y','직급',NULL,'PosName',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-11 00:00:00','HIST1118','2020-02-25 00:00:00'),
	 ('H101','0c604f302db84b7ab65e281ac5ec08e5','[공통] 근무지역','27f134052225462aa134324416900169',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">근무지역</label><div class="input_content"><input type="text" autocomplete="off" component="" id="WorkplaceName" name="WorkplaceName"></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"근무지역"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","autocomplete":"off","component":"","id":"WorkplaceName","name":"WorkplaceName","html":""}
      ]}
  ]}',NULL,'Y','근무지역',NULL,'WorkplaceName',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-11 00:00:00','HIST1118','2020-02-25 00:00:00'),
	 ('H101','ed773ca5019247f1b48dbddfd636e83d','[공통] 부서코드','27f134052225462aa134324416900169',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">부서코드</label><div class="input_content"><input type="text" autocomplete="off" component="" id="DeptCode" name="DeptCode"></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"부서코드"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","autocomplete":"off","component":"","id":"DeptCode","name":"DeptCode","html":""}
      ]}
  ]}',NULL,'Y','부서코드',NULL,'DeptCode',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-11 00:00:00','HIST1118','2020-02-25 00:00:00'),
	 ('H101','cca9494e2704447f94cdf4f24bc8ee1b','[공통] 부서','27f134052225462aa134324416900169',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">부서</label><div class="input_content"><input type="text" autocomplete="off" component="" id="DeptName" name="DeptName"></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"부서"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","autocomplete":"off","component":"","id":"DeptName","name":"DeptName","html":""}
      ]}
  ]}',NULL,'Y','부서',NULL,'DeptName',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-11 00:00:00','HIST1118','2020-02-25 00:00:00'),
	 ('H101','c61f8825aa37478b8804dbfcb7e74ed4','[공통] 여부','9aeae3a8029147e89f3e770d07d6301e',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">여부</label><div class="input_content"><select-custom class="select_option" component="" label="여부" id="commonAt" name="commonAt"><option value>선택하세요</option><option value="Y">Y</option><option value="N">N</option></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"여부"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","label":"여부","id":"commonAt","name":"commonAt","html":[
            {"<>":"option","value":"","html":"선택하세요"},
            {"<>":"option","value":"Y","html":"Y"},
            {"<>":"option","value":"N","html":"N"}
          ]}
      ]}
  ]}',NULL,'Y','여부','select_option','commonAt',NULL,NULL,NULL,NULL,NULL,'CUSTOM|Y:Y,N:N,',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-25 00:00:00',NULL,NULL);
INSERT INTO WM_ITEM (COMPANY_CODE,ITEM_ID,ITEM_NAME,ITEM_TMPL_ID,ITEM_DESC,HTML,JSON,ORIGIN_JSON,USE_AT,ATTR_LABEL,ATTR_CLASS,ATTR_NAME,ATTR_REQUIRED_AT,ATTR_HELPTEXT,ATTR_PLACEHOLDER,ATTR_VALUE,ATTR_READONLY_AT,ATTR_OPTIONS,ATTR_TYPE,ATTR_STYLE,ATTR_TOGGLE_AT,ATTR_INLINE_AT,ATTR_MAXLENGTH,ATTR_ROWS,ATTR_MIN,ATTR_MAX,ATTR_STEP,ATTR_ACCESS_AT,ATTR_ACCESS_ID,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) VALUES
	 ('H101','2282866e98fa457a9031808d32271006','[공통] 첨부여부','9aeae3a8029147e89f3e770d07d6301e',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">첨부여부</label><div class="input_content"><select-custom class="select_option" component="" commcode="USE_AT" label="첨부여부" id="atchFileUseAt" name="atchFileUseAt"></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"첨부여부"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","commcode":"USE_AT","label":"첨부여부","id":"atchFileUseAt","name":"atchFileUseAt","html":""}
      ]}
  ]}',NULL,'Y','첨부여부','select_option','atchFileUseAt',NULL,NULL,NULL,NULL,NULL,'CODE|COMMON_CODE:USE_AT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-25 00:00:00',NULL,NULL),
	 ('H101','d770fbdb6dba4a52923e512f75f3798e','[공통] 부서코드','74292a606829495cb17f2aabaf706705',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">부서코드</label><div class="input_content"><input type="text" autocomplete="off" component="" id="deptCode" name="deptCode"><i class="fas fa-search"></i></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"부서코드"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","autocomplete":"off","component":"","id":"deptCode","name":"deptCode","html":""},
        {"<>":"i","class":"fas fa-search","html":""}
      ]}
  ]}',NULL,'Y','부서코드',NULL,'deptCode',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-25 00:00:00',NULL,NULL),
	 ('H101','463349f7e53341539ddffb705580b4f0','[공통] 사번','74292a606829495cb17f2aabaf706705',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">사번</label><div class="input_content"><input type="text" autocomplete="off" component="" id="UserId" name="UserId"><i class="fas fa-search"></i></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"사번"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","autocomplete":"off","component":"","id":"UserId","name":"UserId","html":""},
        {"<>":"i","class":"fas fa-search","html":""}
      ]}
  ]}',NULL,'Y','사번',NULL,'UserId',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-25 00:00:00',NULL,NULL),
	 ('H101','bc965f6fc1e54f7abfd06da9275b0d67','[공통] 직급코드','27f134052225462aa134324416900169',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">직급코드</label><div class="input_content"><input type="text" autocomplete="off" component="" id="PosCode" name="PosCode"></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"직급코드"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","autocomplete":"off","component":"","id":"PosCode","name":"PosCode","html":""}
      ]}
  ]}',NULL,'Y','직급코드',NULL,'PosCode',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-25 00:00:00',NULL,NULL),
	 ('H101','087c9075e1cf4c86a93cde5eb16ed1d4','[공통] 근무지역코드','27f134052225462aa134324416900169',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">근무지역코드</label><div class="input_content"><input type="text" autocomplete="off" component="" id="WorkplaceCode" name="WorkplaceCode"></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"근무지역코드"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","autocomplete":"off","component":"","id":"WorkplaceCode","name":"WorkplaceCode","html":""}
      ]}
  ]}',NULL,'Y','근무지역코드',NULL,'WorkplaceCode',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-25 00:00:00',NULL,NULL),
	 ('H101','be495d6542c046379862706930c24215','[공통] HIDDEN_INPUT','27f134052225462aa134324416900169',NULL,'<div class="input_box col-12" style="display:none;" ><label for="" component-type="label" component="">Label</label><div class="input_content"><input type="text" autocomplete="off" component=""></div></div>','{"<>":"div","class":"input_box col-12","style":"display:none;","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"Label"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","autocomplete":"off","component":"","html":""}
      ]}
  ]}',NULL,'Y','숨김',NULL,'hiddenInput',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-12 00:00:00',NULL,NULL),
	 ('H101','43b86b9c1d9541ccb97b4cb0eee9a621','[공통] HIDDEN TEXT','8a82336ccfc24da7bb529c025fb68a05',NULL,'<div class="input_box col-12"><div class="input_content"><input type="hidden" component="" id="hiddenText" name="hiddenText"></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"hidden","component":"","id":"hiddenText","name":"hiddenText","html":""}
      ]}
  ]}',NULL,'Y','HIDDEN TEXT',NULL,'hiddenText',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-17 00:00:00',NULL,NULL),
	 ('H101','182039dbf8804f8dbbeaa737f4efd40b','[공통]반송사유','27f134052225462aa134324416900169',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">반송사유</label><div class="input_content"><input type="text" component="" placeholder="반송시 입력" id="returnReason" name="returnReason"></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"반송사유"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","component":"","placeholder":"반송시 입력","id":"returnReason","name":"returnReason","html":""}
      ]}
  ]}',NULL,'Y','반송사유',NULL,'returnReason',NULL,NULL,'반송시 입력',NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-05-06 00:00:00',NULL,NULL),
	 ('H101','505f603a67694bc7b522305d6dd3482f','[공통]파일업로드3','58cec919c97349a7b660f9b0c3bbdf66',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">파일업로드</label><div class="input_content upload_solo_box"><input type="file" class="upload_solo" id="fileUpload3" component="" name="fileUpload3"><button class="trans_btn sm_btn" type="button" id="addFileItemBtn3">추가</button></div><div class="img_file_list_box" id="attachFiles"> </div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"파일업로드"},
    {"<>":"div","class":"input_content upload_solo_box","html":[
        {"<>":"input","type":"file","class":"upload_solo","id":"fileUpload3","component":"","name":"fileUpload3","html":""},
        {"<>":"button","class":"trans_btn sm_btn","type":"button","id":"addFileItemBtn3","html":"추가"}
      ]},
    {"<>":"div","class":"img_file_list_box","id":"attachFiles","html":" "}
  ]}',NULL,'Y','파일업로드','upload_solo','fileUpload3',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-05 00:00:00','HIST1118','2020-04-20 00:00:00'),
	 ('H101','37c789e3fc8d48bba40dce000a6ab818','[공통] TEXTAREA_라벨X','0bddeefe082a47a6a3b205c1f599590c',NULL,'<div class="input_box col-12"><label for="" component-type="label" component=""></label><div class="input_content"><textarea id="textArea_x" class="left-html" rows="10" cols="50" style="border:1px solid rgba(255,255,255,0.3);padding:5px 12px; height:200px" component="" name="textArea_x"></textarea></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":""},
    {"<>":"div","class":"input_content","html":[
        {"<>":"textarea","id":"textArea_x","class":"left-html","rows":"10","cols":"50","style":"border:1px solid rgba(255,255,255,0.3);padding:5px 12px; height:200px","component":"","name":"textArea_x","html":""}
      ]}
  ]}',NULL,'Y',NULL,'left-html','textArea_x',NULL,NULL,NULL,NULL,NULL,'TEXTAREA|',NULL,'border:1px solid rgba(255,255,255,0.3);padding:5px 12px;',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-04-14 00:00:00','HIST1118','2020-04-14 00:00:00');
INSERT INTO WM_ITEM (COMPANY_CODE,ITEM_ID,ITEM_NAME,ITEM_TMPL_ID,ITEM_DESC,HTML,JSON,ORIGIN_JSON,USE_AT,ATTR_LABEL,ATTR_CLASS,ATTR_NAME,ATTR_REQUIRED_AT,ATTR_HELPTEXT,ATTR_PLACEHOLDER,ATTR_VALUE,ATTR_READONLY_AT,ATTR_OPTIONS,ATTR_TYPE,ATTR_STYLE,ATTR_TOGGLE_AT,ATTR_INLINE_AT,ATTR_MAXLENGTH,ATTR_ROWS,ATTR_MIN,ATTR_MAX,ATTR_STEP,ATTR_ACCESS_AT,ATTR_ACCESS_ID,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) VALUES
	 ('H101','3a148614d50f4f8cbb9bdfa5c4cb671c','[공통] 미리보기','ef5ce7832c944c3483f91242c950122f','[공통] 미리보기','<div class="input_content"><button class="trans_btn" id="" component="" component-type="label">미리보기</button></div>','{"<>":"div","class":"input_content","html":[
    {"<>":"button","class":"trans_btn","id":"","component":"","component-type":"label","html":"미리보기"}
  ]}',NULL,'Y','미리보기',NULL,'btn',NULL,NULL,NULL,NULL,NULL,'BUTTON|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-03-31 00:00:00',NULL,NULL),
	 ('H101','f66df0f1e43443bab5758213db5ac4de','[공통] 일자','9aeae3a8029147e89f3e770d07d6301e',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">일자</label><div class="input_content"><select-custom class="select_option" component="" label="일자" id="dayCnt" name="dayCnt"><option value>선택하세요</option><option value="1">1일</option><option value="2">2일</option><option value="3">3일</option><option value="4">4일</option><option value="5">5일</option><option value="6">6일</option><option value="7">7일</option><option value="8">8일</option><option value="9">9일</option><option value="10">10일</option><option value="11">11일</option><option value="12">12일</option><option value="13">13일</option><option value="14">14일</option><option value="15">15일</option><option value="16">16일</option><option value="17">17일</option><option value="18">18일</option><option value="19">19일</option><option value="20">20일</option><option value="21">21일</option><option value="22">22일</option><option value="23">23일</option><option value="24">24일</option><option value="25">25일</option><option value="26">26일</option><option value="27">27일</option><option value="28">28일</option><option value="29">29일</option><option value="30">30일</option><option value="31">31일</option></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"일자"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","label":"일자","id":"dayCnt","name":"dayCnt","html":[
            {"<>":"option","value":"","html":"선택하세요"},
            {"<>":"option","value":"1","html":"1일"},
            {"<>":"option","value":"2","html":"2일"},
            {"<>":"option","value":"3","html":"3일"},
            {"<>":"option","value":"4","html":"4일"},
            {"<>":"option","value":"5","html":"5일"},
            {"<>":"option","value":"6","html":"6일"},
            {"<>":"option","value":"7","html":"7일"},
            {"<>":"option","value":"8","html":"8일"},
            {"<>":"option","value":"9","html":"9일"},
            {"<>":"option","value":"10","html":"10일"},
            {"<>":"option","value":"11","html":"11일"},
            {"<>":"option","value":"12","html":"12일"},
            {"<>":"option","value":"13","html":"13일"},
            {"<>":"option","value":"14","html":"14일"},
            {"<>":"option","value":"15","html":"15일"},
            {"<>":"option","value":"16","html":"16일"},
            {"<>":"option","value":"17","html":"17일"},
            {"<>":"option","value":"18","html":"18일"},
            {"<>":"option","value":"19","html":"19일"},
            {"<>":"option","value":"20","html":"20일"},
            {"<>":"option","value":"21","html":"21일"},
            {"<>":"option","value":"22","html":"22일"},
            {"<>":"option","value":"23","html":"23일"},
            {"<>":"option","value":"24","html":"24일"},
            {"<>":"option","value":"25","html":"25일"},
            {"<>":"option","value":"26","html":"26일"},
            {"<>":"option","value":"27","html":"27일"},
            {"<>":"option","value":"28","html":"28일"},
            {"<>":"option","value":"29","html":"29일"},
            {"<>":"option","value":"30","html":"30일"},
            {"<>":"option","value":"31","html":"31일"}
          ]}
      ]}
  ]}',NULL,'Y','일자','select_option','dayCnt',NULL,NULL,NULL,NULL,NULL,'CUSTOM|1:1일,2:2일,3:3일,4:4일,5:5일,6:6일,7:7일,8:8일,9:9일,10:10일,11:11일,12:12일,13:13일,14:14일,15:15일,16:16일,17:17일,18:18일,19:19일,20:20일,21:21일,22:22일,23:23일,24:24일,25:25일,26:26일,27:27일,28:28일,29:29일,30:30일,31:31일,',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-25 00:00:00',NULL,NULL),
	 ('H101','191c359702a643c792c595aaa194f03f','[BIZCODE]업무공통코드 관리 TEXTAREA','0bddeefe082a47a6a3b205c1f599590c',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">업무 QUERY</label><div class="input_content"><textarea id="textAttr" class="left-html" rows="10" cols="30" style="border:1px solid rgba(255,255,255,0.3);padding:5px 12px;" component="" name="textAttr" maxlength="1000"></textarea></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"업무 QUERY"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"textarea","id":"textAttr","class":"left-html","rows":"10","cols":"30","style":"border:1px solid rgba(255,255,255,0.3);padding:5px 12px;","component":"","name":"textAttr","maxlength":"1000","html":""}
      ]}
  ]}',NULL,'Y','업무 QUERY','left-html','textAttr',NULL,NULL,NULL,NULL,NULL,'TEXTAREA|',NULL,'border:1px solid rgba(255,255,255,0.3);padding:5px 12px;',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-04-03 00:00:00','HIST1118','2020-04-03 00:00:00'),
	 ('H101','0f85fec286924052ae5d48ea3729a887','[BIZCODE]업무공통코드(소분류)','9aeae3a8029147e89f3e770d07d6301e',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">소분류 코드</label><div class="input_content"><select-custom class="select_option" component="" bizcode="BIZ_DTLCODE" label="소분류 코드" id="codeSearch" name="codeSearch"></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"소분류 코드"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","bizcode":"BIZ_DTLCODE","label":"소분류 코드","id":"codeSearch","name":"codeSearch","html":""}
      ]}
  ]}',NULL,'Y','소분류 코드','select_option','codeSearch',NULL,NULL,NULL,NULL,NULL,'CODE|BIZ_CODE:BIZ_DTLCODE',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-04-03 00:00:00',NULL,NULL),
	 ('H101','601384f4cb574c1c9ae47b0dc122e207','[공통] 사용여부','9aeae3a8029147e89f3e770d07d6301e','[공통] 사용여부','<div class="input_box col-12"><label for="" component-type="label" component="">사용여부</label><div class="input_content"><select-custom class="select_option" component="" commcode="USE_AT" label="사용여부" id="useAt" name="useAt"></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"사용여부"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","commcode":"USE_AT","label":"사용여부","id":"useAt","name":"useAt","html":""}
      ]}
  ]}',NULL,'Y','사용여부','select_option','useAt',NULL,NULL,NULL,NULL,NULL,'CODE|COMMON_CODE:USE_AT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-05 00:00:00',NULL,NULL),
	 ('H101','41fb725c1ea1497b9588f1628749bb7a','[공통] 근무지역','9aeae3a8029147e89f3e770d07d6301e','[공통] 근무지역','<div class="input_box col-12"><label for="" component-type="label" component="">근무지역</label><div class="input_content"><select-custom class="select_option" component="" label="근무지역" id="workplaceCode" name="workplaceCode" commcode="WORKPLACE_CODE"></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"근무지역"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","label":"근무지역","id":"workplaceCode","name":"workplaceCode","commcode":"WORKPLACE_CODE","html":""}
      ]}
  ]}',NULL,'Y','근무지역','select_option','workplaceCode',NULL,NULL,NULL,NULL,NULL,'CODE|COMMON_CODE:WORKPLACE_CODE',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-05 00:00:00','HIST1118','2020-02-26 00:00:00'),
	 ('H101','1fc68b9f583f459f9510a99cde18bb91','[공통]파일업로드1','58cec919c97349a7b660f9b0c3bbdf66',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">파일업로드</label>
<div class="input_content upload_solo_box"><input type="file" class="upload_solo" id="fileUpload1" component="" name="fileUpload1">
<button class="trans_btn sm_btn" type="button" id="addFileItemBtn1">추가</button></div>
<div class="img_file_list_box" id="attachFiles"> </div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"파일업로드"},
    {"<>":"div","class":"input_content upload_solo_box","html":[
        {"<>":"input","type":"file","class":"upload_solo","id":"fileUpload1","component":"","name":"fileUpload1","html":""},
        {"<>":"button","class":"trans_btn sm_btn","type":"button","id":"addFileItemBtn1","html":"추가"}
      ]},
    {"<>":"div","class":"img_file_list_box","id":"attachFiles","html":" "}
  ]}',NULL,'Y','파일업로드','upload_solo','fileUpload1',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-05 00:00:00','HIST1118','2020-04-20 00:00:00'),
	 ('H101','e762b7295ff249fcbf7e01a221720cc8','[공통]파일업로드2','58cec919c97349a7b660f9b0c3bbdf66',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">파일업로드</label><div class="input_content upload_solo_box"><input type="file" class="upload_solo" id="fileUpload2" component="" name="fileUpload2"><button class="trans_btn sm_btn" type="button" id="addFileItemBtn2">추가</button></div><div class="img_file_list_box" id="attachFiles"> </div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"파일업로드"},
    {"<>":"div","class":"input_content upload_solo_box","html":[
        {"<>":"input","type":"file","class":"upload_solo","id":"fileUpload2","component":"","name":"fileUpload2","html":""},
        {"<>":"button","class":"trans_btn sm_btn","type":"button","id":"addFileItemBtn2","html":"추가"}
      ]},
    {"<>":"div","class":"img_file_list_box","id":"attachFiles","html":" "}
  ]}',NULL,'Y','파일업로드','upload_solo','fileUpload2',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-05 00:00:00','HIST1118','2020-04-20 00:00:00'),
	 ('H101','cf0589c34fca48a18677e77caa68e2b0','[BIZCODE]업무공통코드(대분류)','9aeae3a8029147e89f3e770d07d6301e',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">대분류 코드</label><div class="input_content"><select-custom class="select_option" component="" bizcode="CL_CODE" label="대분류 코드" id="clCodeSearch" name="clCodeSearch"></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"대분류 코드"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","bizcode":"CL_CODE","label":"대분류 코드","id":"clCodeSearch","name":"clCodeSearch","html":""}
      ]}
  ]}',NULL,'Y','대분류 코드','select_option','clCodeSearch',NULL,NULL,NULL,NULL,NULL,'CODE|BIZ_CODE:CL_CODE',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-04-03 00:00:00',NULL,NULL),
	 ('H101','d1fe2b7514044c8097ea0202200de994','[ 공통 ] 월 (01,02..)','9aeae3a8029147e89f3e770d07d6301e',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">월</label><div class="input_content"><select-custom class="select_option" component="" label="월" id="monthSelect" name="monthSelect"><option value="01">1월</option><option value="02">2월</option><option value="03">3월</option><option value="04">4월</option><option value="05">5월</option><option value="06">6월</option><option value="07">7월</option><option value="08">8월</option><option value="09">9월</option><option value="10">10월</option><option value="11">11월</option><option value="12">12월</option></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"월"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","label":"월","id":"monthSelect","name":"monthSelect","html":[
            {"<>":"option","value":"01","html":"1월"},
            {"<>":"option","value":"02","html":"2월"},
            {"<>":"option","value":"03","html":"3월"},
            {"<>":"option","value":"04","html":"4월"},
            {"<>":"option","value":"05","html":"5월"},
            {"<>":"option","value":"06","html":"6월"},
            {"<>":"option","value":"07","html":"7월"},
            {"<>":"option","value":"08","html":"8월"},
            {"<>":"option","value":"09","html":"9월"},
            {"<>":"option","value":"10","html":"10월"},
            {"<>":"option","value":"11","html":"11월"},
            {"<>":"option","value":"12","html":"12월"}
          ]}
      ]}
  ]}',NULL,'Y','월','select_option','monthSelect',NULL,NULL,NULL,NULL,NULL,'CUSTOM|01:1월,02:2월,03:3월,04:4월,05:5월,06:6월,07:7월,08:8월,09:9월,10:10월,11:11월,12:12월,',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-03-04 00:00:00','HIST1118','2020-03-04 00:00:00');
INSERT INTO WM_ITEM (COMPANY_CODE,ITEM_ID,ITEM_NAME,ITEM_TMPL_ID,ITEM_DESC,HTML,JSON,ORIGIN_JSON,USE_AT,ATTR_LABEL,ATTR_CLASS,ATTR_NAME,ATTR_REQUIRED_AT,ATTR_HELPTEXT,ATTR_PLACEHOLDER,ATTR_VALUE,ATTR_READONLY_AT,ATTR_OPTIONS,ATTR_TYPE,ATTR_STYLE,ATTR_TOGGLE_AT,ATTR_INLINE_AT,ATTR_MAXLENGTH,ATTR_ROWS,ATTR_MIN,ATTR_MAX,ATTR_STEP,ATTR_ACCESS_AT,ATTR_ACCESS_ID,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) VALUES
	 ('H101','1c3d4ee4b2aa411bb652725b7f2de780','[공통] 라벨','5ab462b997554e88941519866a6ba2c7',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">LABEL</label></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"LABEL"}
  ]}',NULL,'Y','LABEL',NULL,'label',NULL,NULL,NULL,NULL,NULL,'USER_CUSTOM|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-20 00:00:00',NULL,NULL),
	 ('H101','a2be3bf7b27f40d9a760c3173b3bef91','[BIZCODE]업무공통코드(중분류)','9aeae3a8029147e89f3e770d07d6301e',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">중분류 코드</label><div class="input_content"><select-custom class="select_option" component="" bizcode="BIZ_CODE" label="중분류 코드" id="codeIdSearch" name="codeIdSearch"></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"중분류 코드"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","bizcode":"BIZ_CODE","label":"중분류 코드","id":"codeIdSearch","name":"codeIdSearch","html":""}
      ]}
  ]}',NULL,'Y','중분류 코드','select_option','codeIdSearch',NULL,NULL,NULL,NULL,NULL,'CODE|BIZ_CODE:BIZ_CODE',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-04-03 00:00:00',NULL,NULL),
	 ('H101','43bc6dd93f2b47dfa4c3d112e4a77f25','[공통] 숫자','27f134052225462aa134324416900169',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">숫자</label><div class="input_content"><input type="text" autocomplete="off" component="" id="textNumber" name="textNumber" class="number"></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"숫자"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","autocomplete":"off","component":"","id":"textNumber","name":"textNumber","class":"number","html":""}
      ]}
  ]}',NULL,'Y','숫자','number','textNumber',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-10 00:00:00','HIST1118','2020-02-10 00:00:00'),
	 ('H101','90a7208e034c4c05ad4cd386339989d5','[공통] 시작일자','bc308be12f6141c68064dbd18332b8b0','[공통] 시작일자','<div class="input_box col-12"><label for="" component-type="label" component="">시작일자</label><div class="input_content"><input type="text" autocomplete="off" autocomplete="off" data-toggle="datepicker" component="" id="startDate" name="startDate"><i class="far fa-calendar-alt"></i></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"시작일자"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","autocomplete":"off","autocomplete":"off","data-toggle":"datepicker","component":"","id":"startDate","name":"startDate","html":""},
        {"<>":"i","class":"far fa-calendar-alt","html":""}
      ]}
  ]}',NULL,'Y','시작일자',NULL,'startDate',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-05 00:00:00',NULL,NULL),
	 ('H101','516c37b7c2f142f29ed53386148ec182','[공통] 입력박스','27f134052225462aa134324416900169','[공통] 입력박스','<div class="input_box col-12"><label for="" component-type="label" component="">LABEL</label><div class="input_content"><input type="text" autocomplete="off" component="" id="txtLabel" name="txtLabel"></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"LABEL"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"input","type":"text","autocomplete":"off","component":"","id":"txtLabel","name":"txtLabel","html":""}
      ]}
  ]}',NULL,'Y','LABEL',NULL,'txtLabel',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-05 00:00:00','HIST1118','2020-02-05 00:00:00'),
	 ('H101','c80c19ec19144eebbbc451d82b9383ed','[ 공통 ] 월','9aeae3a8029147e89f3e770d07d6301e',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">월</label><div class="input_content"><select-custom class="select_option" component="" label="월" id="monthSelect" name="monthSelect"><option value="01">1월</option><option value="02">2월</option><option value="03">3월</option><option value="04">4월</option><option value="05">5월</option><option value="06">6월</option><option value="07">7월</option><option value="08">8월</option><option value="09">9월</option><option value="10">10월</option><option value="11">11월</option><option value="12">12월</option></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"월"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","label":"월","id":"monthSelect","name":"monthSelect","html":[
            {"<>":"option","value":"01","html":"1월"},
            {"<>":"option","value":"02","html":"2월"},
            {"<>":"option","value":"03","html":"3월"},
            {"<>":"option","value":"04","html":"4월"},
            {"<>":"option","value":"05","html":"5월"},
            {"<>":"option","value":"06","html":"6월"},
            {"<>":"option","value":"07","html":"7월"},
            {"<>":"option","value":"08","html":"8월"},
            {"<>":"option","value":"09","html":"9월"},
            {"<>":"option","value":"10","html":"10월"},
            {"<>":"option","value":"11","html":"11월"},
            {"<>":"option","value":"12","html":"12월"}
          ]}
      ]}
  ]}',NULL,'Y','월','select_option','monthSelect',NULL,NULL,NULL,NULL,NULL,'CUSTOM|01:1월,02:2월,03:3월,04:4월,05:5월,06:6월,07:7월,08:8월,09:9월,10:10월,11:11월,12:12월,',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-22 00:00:00','HIST1118','2020-04-14 00:00:00'),
	 ('H101','94c3345ddc184e82b1f0e14e2e4bdfa0','[공통] 에디터','4b9854961c51435986639e0f28346879',NULL,'<div style="height: 100%; width: 100%"><div id="editor"></div></div>','{"<>":"div","style":"height: 100%; width: 100%","html":[
    {"<>":"div","id":"editor","html":""}
  ]}',NULL,'Y',NULL,NULL,'editor',NULL,NULL,NULL,NULL,NULL,'USER_CUSTOM|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2020-02-26 00:00:00',NULL,NULL),
	 ('H101','1b5af353fc4044d291ce88a3208dd663','[공통]파일업로드5','58cec919c97349a7b660f9b0c3bbdf66',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">파일업로드</label><div class="input_content upload_solo_box"><input type="file" class="upload_solo" id="fileUpload5" component="" name="fileUpload5"><button class="trans_btn sm_btn" type="button" id="addFileItemBtn5">추가</button></div><div class="img_file_list_box" id="attachFiles"> </div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"파일업로드"},
    {"<>":"div","class":"input_content upload_solo_box","html":[
        {"<>":"input","type":"file","class":"upload_solo","id":"fileUpload5","component":"","name":"fileUpload5","html":""},
        {"<>":"button","class":"trans_btn sm_btn","type":"button","id":"addFileItemBtn5","html":"추가"}
      ]},
    {"<>":"div","class":"img_file_list_box","id":"attachFiles","html":" "}
  ]}',NULL,'Y','파일업로드','upload_solo','fileUpload5',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2021-12-13 09:32:45','HIST1118','2021-12-13 13:41:34'),
	 ('H101','1479683650c94dc5ab25ac7ae3c8802c','[공통]회계지역','9aeae3a8029147e89f3e770d07d6301e','지역','<div class="input_box col-12"><label for="" component-type="label" component="">회계지역</label><div class="input_content"><select-custom class="select_option" component="" commcode="ACCOUNT_REGION" label="회계지역" id="regionCode" name="regionCode"></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"회계지역"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","commcode":"ACCOUNT_REGION","label":"회계지역","id":"regionCode","name":"regionCode","html":""}
      ]}
  ]}',NULL,'Y','회계지역','select_option','regionCode',NULL,NULL,NULL,NULL,NULL,'CODE|COMMON_CODE:ACCOUNT_REGION',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'E660087','2021-02-24 13:11:29',NULL,NULL),
	 ('H101','9fb77977bf7247f9b0aa6705d641f2d6','[공통]여부OX','9aeae3a8029147e89f3e770d07d6301e',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">여부</label><div class="input_content"><select-custom class="select_option" component="" label="여부" commcode="TRUEFALSE_SELECT" id="truefalseSelect" name="truefalseSelect"></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"여부"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","label":"여부","commcode":"TRUEFALSE_SELECT","id":"truefalseSelect","name":"truefalseSelect","html":""}
      ]}
  ]}',NULL,'Y','여부','select_option','truefalseSelect',NULL,NULL,NULL,NULL,NULL,'CODE|COMMON_CODE:TRUEFALSE_SELECT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'E660087','2021-11-15 14:44:38',NULL,NULL);
INSERT INTO WM_ITEM (COMPANY_CODE,ITEM_ID,ITEM_NAME,ITEM_TMPL_ID,ITEM_DESC,HTML,JSON,ORIGIN_JSON,USE_AT,ATTR_LABEL,ATTR_CLASS,ATTR_NAME,ATTR_REQUIRED_AT,ATTR_HELPTEXT,ATTR_PLACEHOLDER,ATTR_VALUE,ATTR_READONLY_AT,ATTR_OPTIONS,ATTR_TYPE,ATTR_STYLE,ATTR_TOGGLE_AT,ATTR_INLINE_AT,ATTR_MAXLENGTH,ATTR_ROWS,ATTR_MIN,ATTR_MAX,ATTR_STEP,ATTR_ACCESS_AT,ATTR_ACCESS_ID,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) VALUES
	 ('H101','903df546b1804351a741770a8f69ca0c','[공통]파일업로드4','58cec919c97349a7b660f9b0c3bbdf66',NULL,'<div class="input_box col-12"><label for="" component-type="label" component="">파일업로드</label><div class="input_content upload_solo_box"><input type="file" class="upload_solo" id="fileUpload4" component="" name="fileUpload4"><button class="trans_btn sm_btn" type="button" id="addFileItemBtn4">추가</button></div><div class="img_file_list_box" id="attachFiles"> </div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"파일업로드"},
    {"<>":"div","class":"input_content upload_solo_box","html":[
        {"<>":"input","type":"file","class":"upload_solo","id":"fileUpload4","component":"","name":"fileUpload4","html":""},
        {"<>":"button","class":"trans_btn sm_btn","type":"button","id":"addFileItemBtn4","html":"추가"}
      ]},
    {"<>":"div","class":"img_file_list_box","id":"attachFiles","html":" "}
  ]}',NULL,'Y','파일업로드','upload_solo','fileUpload4',NULL,NULL,NULL,NULL,NULL,'INPUT|',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'HIST1118','2021-12-13 09:31:17','HIST1118','2021-12-13 13:41:09'),
	 ('H101','e92317a1f6f8402993745c6b02b2398b','[공통]월','9aeae3a8029147e89f3e770d07d6301e','[공통]월','<div class="input_box col-12"><label for="" component-type="label" component="">월</label><div class="input_content"><select-custom class="select_option" component="" commcode="MONTH_SELECT" label="월" id="targetMonth" name="targetMonth"></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"월"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","commcode":"MONTH_SELECT","label":"월","id":"targetMonth","name":"targetMonth","html":""}
      ]}
  ]}',NULL,'Y','월','select_option','targetMonth',NULL,NULL,NULL,NULL,NULL,'CODE|COMMON_CODE:MONTH_SELECT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'E660087','2021-04-08 16:31:48',NULL,NULL),
	 ('H101','b06b65dbfd054b1599256028f53f4e4e','[공통]O/X','9aeae3a8029147e89f3e770d07d6301e','[공통]O/X','<div class="input_box col-12"><label for="" component-type="label" component="">여부</label><div class="input_content"><select-custom class="select_option" component="" label="여부" id="trueFalse" name="trueFalse"><option value="X">X</option><option value="O">O</option></select-custom></div></div>','{"<>":"div","class":"input_box col-12","html":[
    {"<>":"label","for":"","component-type":"label","component":"","html":"여부"},
    {"<>":"div","class":"input_content","html":[
        {"<>":"select-custom","class":"select_option","component":"","label":"여부","id":"trueFalse","name":"trueFalse","html":[
            {"<>":"option","value":"X","html":"X"},
            {"<>":"option","value":"O","html":"O"}
          ]}
      ]}
  ]}',NULL,'Y','여부','select_option','trueFalse',NULL,NULL,NULL,NULL,NULL,'CUSTOM|X:X,O:O,',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'E660087','2021-11-10 13:06:43',NULL,NULL);


CREATE TABLE WF_SERVICE (
	COMPANY_CODE VARCHAR(50) NOT NULL,
	SERVICE_ID VARCHAR(33) NOT NULL,
	BIZ_CODE VARCHAR(20) NOT NULL,
	SERVICE_NAME VARCHAR(180),
	SERVICE_TYPE_CODE VARCHAR(20),
	FORM_ID VARCHAR(33),
	SERVICE_URL VARCHAR(100),
	PRE_PROCESS_URL VARCHAR(100),
	SERVICE_DISPLAY_NAME VARCHAR(180),
	SERVICE_DISPLAY_NAME_ENG VARCHAR(180),
	MOTO_NAME VARCHAR(60),
	MOTO_POSITION VARCHAR(20),
	CI_DISPLAY_AT VARCHAR(1),
	CHARGE_APPROVAL_AT VARCHAR(1),
	MANAGER_APPROVAL_AT VARCHAR(1),
	APPROVAL_DECISION_CODE VARCHAR(10),
	SIGN_DISPLAY_TYPE VARCHAR(30),
	SIGN_SEND_COUNT INTEGER,
	SIGN_RECEIVE_COUNT INTEGER,
	PRINT_AT VARCHAR(1),
	PERSONINFO_LIST VARCHAR(200),
	ACCEPT_MAIL_AT VARCHAR(1),
	ACCEPT_MAIL_ADDRESS VARCHAR(30),
	GUIDEBOX_CONTENTS VARCHAR(12000),
	NOTICE_POPUP_AT VARCHAR(1),
	PRIVATEINFO_AGREEMENT_AT VARCHAR(1),
	RETIRER_SEPRAT_AT VARCHAR(1),
	DOC_EXPIRE_YEAR VARCHAR(10),
	DB_HOST VARCHAR(30),
	DB_SCHEMA VARCHAR(30),
	DB_TABLE VARCHAR(30),
	ROLE_CHARGE VARCHAR(255),
	ROLE_SUPPORT VARCHAR(255),
	USE_AT VARCHAR(1),
	LIST_USE_AT VARCHAR(1),
	ROLE_USER_USE_AT VARCHAR(1),
	ROLE_SUPPORT_USE_AT VARCHAR(1),
	LINK_SERVICE_ID VARCHAR(33),
	CREATE_USER VARCHAR(20),
	CREATE_TIME DATE,
	UPDATE_USER VARCHAR(20),
	UPDATE_TIME DATE,
	CONSTRAINT PK_WF_SERVICE PRIMARY KEY (COMPANY_CODE,SERVICE_ID)
);
CREATE INDEX IX01_WF_SERVICE ON WF_SERVICE (COMPANY_CODE,FORM_ID);
CREATE INDEX IX02_WF_SERVICE ON WF_SERVICE (BIZ_CODE);



CREATE TABLE WM_FORM_FIELD (
	COMPANY_CODE VARCHAR(50) NOT NULL,
	FORM_ID VARCHAR(33) NOT NULL,
	FIELD_ID VARCHAR(32) NOT NULL,
	FIELD_NAME VARCHAR(64),
	FIELD_NAME_ENG VARCHAR(200),
	SORT_ORDER INTEGER,
	FIELD_TYPE VARCHAR(20) NOT NULL,
	FIELD_LENGTH INTEGER,
	FIELD_DEFAULT VARCHAR(192),
	FIELD_NULLABLE_AT CHAR(1),
	FIELD_PK_AT CHAR(1),
	FIELD_CAT VARCHAR(10) NOT NULL,
	USE_AT CHAR(1) NOT NULL,
	CREATE_USER VARCHAR(20),
	CREATE_TIME DATE,
	UPDATE_USER VARCHAR(20),
	UPDATE_TIME DATE,
	CONSTRAINT PK_WM_FORM_FIELD PRIMARY KEY (COMPANY_CODE,FORM_ID,FIELD_ID)
);
CREATE INDEX IX01_WM_FORM_FIELD ON WM_FORM_FIELD (COMPANY_CODE,FORM_ID);




CREATE TABLE WM_FORM_ITEM (
	COMPANY_CODE VARCHAR(50) NOT NULL,
	FORM_ID VARCHAR(33) NOT NULL,
	ITEM_ID VARCHAR(33) NOT NULL,
	ELEMENT_ID VARCHAR(60) NOT NULL,
	FIELD_ID VARCHAR(60),
	EVENT_FUNCTION VARCHAR(3000),
	COLSPAN INTEGER NOT NULL,
	HTML TEXT,
	JSON TEXT,
	ORIGIN_JSON TEXT,
	DISPLAY_AT CHAR(1) NOT NULL,
	USE_AT CHAR(1) NOT NULL,
	ATTR_LABEL VARCHAR(1500),
	ATTR_REQUIRED_AT CHAR(1),
	ATTR_HELPTEXT VARCHAR(1500),
	ATTR_PLACEHOLDER VARCHAR(1500),
	ATTR_VALUE VARCHAR(180),
	ATTR_READONLY_AT CHAR(1),
	ATTR_OPTIONS VARCHAR(1500),
	ATTR_STYLE VARCHAR(60),
	ATTR_TOGGLE_AT CHAR(1),
	ATTR_INLINE_AT CHAR(1),
	ATTR_ACCESS_AT CHAR(1),
	ATTR_ACCESS_ID VARCHAR(500),
	CREATE_USER VARCHAR(20),
	CREATE_TIME DATE,
	UPDATE_USER VARCHAR(20),
	UPDATE_TIME DATE,
	CONSTRAINT PK_WM_FORM_ITEM PRIMARY KEY (COMPANY_CODE,FORM_ID,ITEM_ID,ELEMENT_ID)
);



CREATE TABLE WM_FORMS (
	COMPANY_CODE VARCHAR(50) NOT NULL,
	FORM_ID VARCHAR(33) NOT NULL,
	FORM_NAME VARCHAR(360),
	FORM_DESC VARCHAR(1500),
	REVISION VARCHAR(10),
	FORM_TMPL_ID VARCHAR(33) NOT NULL,
	LOCATED_PATH VARCHAR(60),
	HTML TEXT,
	JSON TEXT,
	ORIGIN_JSON TEXT,
	JSCRIPT TEXT,
	CREATE_USER VARCHAR(20),
	CREATE_TIME DATE,
	UPDATE_USER VARCHAR(20),
	UPDATE_TIME DATE,
	CONSTRAINT PK_WM_FORMS PRIMARY KEY (COMPANY_CODE,FORM_ID)
);
CREATE INDEX IX01_WM_FORMS ON WM_FORMS (COMPANY_CODE,FORM_TMPL_ID);
CREATE INDEX IX02_WM_FORMS ON WM_FORMS (FORM_ID);



CREATE TABLE WM_FORM_BUTTON (
	COMPANY_CODE VARCHAR(50) NOT NULL,
	FORM_ID VARCHAR(33) NOT NULL,
	ROLE_ID VARCHAR(33) NOT NULL,
	BUTTON_CODE VARCHAR(20) NOT NULL,
	USE_AT VARCHAR(1),
	CREATE_USER VARCHAR(20),
	CREATE_TIME DATE,
	UPDATE_USER VARCHAR(20),
	UPDATE_TIME DATE,
	CONSTRAINT PK_WM_FORM_BUTTON PRIMARY KEY (COMPANY_CODE,FORM_ID,ROLE_ID,BUTTON_CODE)
);
CREATE INDEX IX01_WM_FORM_BUTTON ON WM_FORM_BUTTON (COMPANY_CODE,FORM_ID);




CREATE TABLE CM_PROGRMLIST (
	COMPANY_CODE VARCHAR(50) NOT NULL,
	PROGRM_FILE_NM VARCHAR(60) NOT NULL,
	PROGRM_STRE_PATH VARCHAR(100) NOT NULL,
	PROGRM_KOREAN_NM VARCHAR(60),
	PROGRM_DC VARCHAR(200),
	URL VARCHAR(100),
	USE_AT VARCHAR(1),
	CONSTRAINT PK_CM_PROGRMLIST PRIMARY KEY (COMPANY_CODE,PROGRM_FILE_NM)
);
