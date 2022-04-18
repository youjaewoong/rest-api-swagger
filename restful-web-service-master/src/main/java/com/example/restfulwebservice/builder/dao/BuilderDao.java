package com.example.restfulwebservice.builder.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.restfulwebservice.builder.vo.ItemVO;
import com.example.restfulwebservice.builder.vo.WfServiceVO;
import com.example.restfulwebservice.builder.vo.WmFormButtonVO;
import com.example.restfulwebservice.builder.vo.WmFormFieldVO;
import com.example.restfulwebservice.builder.vo.WmFormItemVO;
import com.example.restfulwebservice.builder.vo.WmFormsVO;

@Mapper
public interface BuilderDao {

	/**
	 * 폼빌더 기본환경 등록
	 * Statements
	 *
	 * @param wfServiceVo
	 */
	public int insertService(WfServiceVO wfServiceVo);


	/**
	 * 업무테이블 생성
	 * Statements
	 *
	 * @param map
	 */
	public int createBizTable(Map<String, Object> map);


	/**
	 * 업무테이블 코멘트 생성
	 * Statements
	 *
	 * @param map
	 */
	public int createBizTableComment(Map<String, Object> map);


	/**
	 * 업무테이블 Grant 생성
	 * Statements
	 *
	 * @param map
	 */
	public int createBizTableGrant(Map<String, Object> map);


	/**
	 * 폼빌더 아이템 등록
	 * Statements
	 *
	 * @param wmFormItemVo
	 */
	public int insertFormItem(WmFormItemVO wmFormItemVo);


	/**
	 * 폼빌더 폼 정보 조회
	 * Statements
	 *
	 * @param wmFormsVo
	 */
	public int insertForms(WmFormsVO wmFormsVo);



	/**
	 * 폼빌더 버튼 정보 등록
	 * Statements
	 *
	 * @param wmFormButtonVo
	 */
	public int insertWmFormButton(WmFormButtonVO wmFormButtonVo);
	
	
    /**
     * 폼빌더 데이터 필드 등록
     * Statements
     *
     * @param wmFormFieldVo
     */
    public int insertFormField(WmFormFieldVO wmFormFieldVo);


    /**
     * 아이템 템플릿 목록 조회
     * Statements
     *
     * @param itemVo
     * @return
     */
	public List<ItemVO> selectItemByTmpl(ItemVO itemVo);

}
