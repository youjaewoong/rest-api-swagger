package com.example.restfulwebservice.builder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restfulwebservice.builder.dao.ProgrmDao;
import com.example.restfulwebservice.builder.vo.ProgrmManageVO;

@Service
public class ProgrmService {

    @Autowired
    private ProgrmDao progrmManageDAO;

	public int insertProgrm(ProgrmManageVO vo) throws Exception {
		return progrmManageDAO.insertProgrm(vo);
	}
}
