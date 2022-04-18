package com.example.restfulwebservice.builder.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.restfulwebservice.builder.vo.ItemTmplVO;


@Mapper
public interface ItemDao {
	public List<ItemTmplVO> selectItemTmplAll(ItemTmplVO itemTmplVo);
}
