package com.example.restfulwebservice.builder.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.restfulwebservice.builder.vo.ProgrmManageVO;

@Mapper
public interface ProgrmDao {

	/**
	 * 프로그램 기본정보 및 URL을 등록
	 * @param vo ProgrmManageVO
	 * @exception Exception
	 */
	int insertProgrm(ProgrmManageVO vo)throws Exception;
}
