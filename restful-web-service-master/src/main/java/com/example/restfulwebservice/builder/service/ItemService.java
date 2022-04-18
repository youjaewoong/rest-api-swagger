package com.example.restfulwebservice.builder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restfulwebservice.builder.dao.ItemDao;
import com.example.restfulwebservice.builder.vo.ItemTmplVO;

@Service
public class ItemService {
	
	@Autowired
    private ItemDao itemRestDao;

    public List<ItemTmplVO> selectItemTmplAll(ItemTmplVO itemTmplVo) {
        return itemRestDao.selectItemTmplAll(itemTmplVo);
    }
}
