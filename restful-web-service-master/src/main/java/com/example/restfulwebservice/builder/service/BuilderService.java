package com.example.restfulwebservice.builder.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restfulwebservice.builder.dao.BuilderDao;
import com.example.restfulwebservice.builder.vo.ItemVO;
import com.example.restfulwebservice.builder.vo.WfServiceVO;
import com.example.restfulwebservice.builder.vo.WmFormButtonVO;
import com.example.restfulwebservice.builder.vo.WmFormFieldVO;
import com.example.restfulwebservice.builder.vo.WmFormItemVO;
import com.example.restfulwebservice.builder.vo.WmFormsVO;

@Service
public class BuilderService {

    @Autowired
    private BuilderDao builderDao;
    
    
    /**
     * 폼빌더 기본환경 등록
     */
    
    public int insertService(WfServiceVO wfServiceVo) {
        return builderDao.insertService(wfServiceVo);
    }
    
 
    /**
     * 업무테이블 생성
     */
    
    public int createBizTable(Map<String, Object> map) {
        return builderDao.createBizTable(map);
    }
    
    /**
     * 업무테이블 코멘트 생성
     */
    
    public int createBizTableComment(Map<String, Object> map) {
    	return builderDao.createBizTableComment(map);
    }
    
    /**
     * 업무테이블 Grant 생성
     * Statements
     *
     * @param map
     */
    
    public int createBizTableGrant(Map<String, Object> map) {
        return builderDao.createBizTableGrant(map);
    }
    
    /**
     * 폼빌더 아이템 등록    
     */
    
    public int insertFormItem(WmFormItemVO wmFormItemVo) {
        return builderDao.insertFormItem(wmFormItemVo);
    }
    
    /**
     * 폼빌더 폼 정보 조회
     */
    
    public int insertForms(WmFormsVO wmFormsVo) {
        return builderDao.insertForms(wmFormsVo);
    }
    
    
    /**
     * 폼빌더 버튼 정보 등록
     */
    
    public int insertWmFormButton(WmFormButtonVO wmFormButtonVo) {
        return builderDao.insertWmFormButton(wmFormButtonVo);
    }
    
    
    /**
     * 폼빌더 데이터 필드 등록
     */
    public int insertFormField(WmFormFieldVO wmFormFieldVo) {
        return builderDao.insertFormField(wmFormFieldVo);
    }
    
    /**
     * 아이템 템플릿 목록 조회
     */
    public List<ItemVO> selectItemByTmpl(ItemVO itemVo) {
        return builderDao.selectItemByTmpl(itemVo);
    }
}
