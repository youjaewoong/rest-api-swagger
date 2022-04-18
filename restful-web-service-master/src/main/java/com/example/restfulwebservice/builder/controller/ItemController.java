package com.example.restfulwebservice.builder.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.restfulwebservice.builder.service.BuilderService;
import com.example.restfulwebservice.builder.service.ItemService;
import com.example.restfulwebservice.builder.util.AjaxResponseUtil;
import com.example.restfulwebservice.builder.vo.ItemTmplVO;
import com.example.restfulwebservice.builder.vo.ItemVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ItemController  {
    
	@Autowired
    private ItemService itemService;
	
	@Autowired
	private BuilderService builderRestService;
	
	@Autowired
	private ObjectMapper objectMapper;

    /**
     * 아이템 목록 조회
     * Statements
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/admin/builder/item/getItemByTmpl.do")
    public ResponseEntity<String> getItemByTemplate(@RequestParam Map<String, Object> map
            , HttpServletRequest request
            , ModelMap model) throws Exception{
        
        ItemVO itemVo = objectMapper.convertValue(map, ItemVO.class);
        model.addAttribute("itemList", builderRestService.selectItemByTmpl(itemVo));
        return AjaxResponseUtil.getResponseEntity(model);
    }
    
    
    /**
     * 아이템 템플릿 목록 조회
     * Statements
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/admin/builder/item/getItemTmplAll.do")
    public ResponseEntity<String> getItemTmplAll(ModelMap model, HttpServletRequest request) throws Exception{
        
        
        String companyCode = (String)request.getParameter("companyCode");
        ItemTmplVO itemTmplVo = new ItemTmplVO();
        
        itemTmplVo.setCompanyCode(companyCode);
        
        List<ItemTmplVO> itemTmplList = new ArrayList<ItemTmplVO>();
        
        itemTmplList = itemService.selectItemTmplAll(itemTmplVo);
        model.addAttribute("itemTmplList", itemTmplList);
        
        return AjaxResponseUtil.getResponseEntity(model);
    }   
}