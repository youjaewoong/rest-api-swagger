package com.example.restfulwebservice.builder.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.restfulwebservice.builder.service.ProgrmService;
import com.example.restfulwebservice.builder.util.AjaxResponseUtil;
import com.example.restfulwebservice.builder.vo.ProgrmManageVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@Transactional
public class ProgramController {
    
    public String getRestUrl(){
        return "localhost:8088";
    }
    
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	ProgrmService progrmService;
	
	/**
     * 프로그램목록을 등록
     * Statements
     *
     * @param map
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/admin/sym/prm/setProgrm.do")
    public ResponseEntity<String> setProgrm(@RequestParam Map<String, Object> map
            , HttpServletRequest request
            , ModelMap model) throws Exception {
        
        ProgrmManageVO progrmManageVO = objectMapper.convertValue(map, ProgrmManageVO.class);
            
            if (progrmManageVO.getProgrmDc() == null || progrmManageVO.getProgrmDc().equals("")) {
                progrmManageVO.setProgrmDc(" ");
            }
            progrmManageVO.setUrl(progrmManageVO.getUrl().replace("&#63;", "?"));
            int returnCnt = 0;
            try {
                returnCnt = progrmService.insertProgrm(progrmManageVO);
            } catch(Exception e) {
                e.printStackTrace();
                returnCnt = 0;
            }
            model.addAttribute("returnCnt", returnCnt);
        
        return AjaxResponseUtil.getResponseEntity(model);
    }
}