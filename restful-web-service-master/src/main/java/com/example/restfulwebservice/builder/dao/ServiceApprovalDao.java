package com.example.restfulwebservice.builder.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.restfulwebservice.builder.vo.FormButtonVO;
import com.example.restfulwebservice.builder.vo.FormFieldVO;
import com.example.restfulwebservice.builder.vo.ServiceFormVO;
import com.example.restfulwebservice.builder.vo.TemplateDataVO;

@Mapper
public interface ServiceApprovalDao {
	
    public ServiceFormVO selectServiceInfo(ServiceFormVO serviceFormVO);
    
    public List<FormButtonVO> selectFormButtonList(FormButtonVO formButtonVO);
    
    public List<FormFieldVO> selectFormFieldList(FormFieldVO formFieldVo);
    
    public List<Map<String,Object>> selectCodeTemplateDataList(TemplateDataVO templateDataVo);
    
    public void insertCodeTemplateData(TemplateDataVO templateDataVo) throws Exception;

    public void updateCodeTemplateData(TemplateDataVO templateDataVo);
    
    public void deleteCodeTemplateData(TemplateDataVO templateDataVo);
    
}
