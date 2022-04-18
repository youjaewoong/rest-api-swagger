package com.example.restfulwebservice.builder.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restfulwebservice.builder.dao.ServiceApprovalDao;
import com.example.restfulwebservice.builder.vo.FormButtonVO;
import com.example.restfulwebservice.builder.vo.FormFieldVO;
import com.example.restfulwebservice.builder.vo.ServiceFormVO;
import com.example.restfulwebservice.builder.vo.TemplateDataVO;

@Service
public class ServiceApprovalService {

    @Autowired
    private ServiceApprovalDao serviceApprovalDao;

    public ServiceFormVO selectServiceInfo(ServiceFormVO paramVo) {
		return serviceApprovalDao.selectServiceInfo(paramVo);
	}
    
    public List<FormButtonVO> selectFormButtonList(FormButtonVO formButtonVO) {
        return serviceApprovalDao.selectFormButtonList(formButtonVO);
    }
    
    public List<FormFieldVO> selectFormFieldList(FormFieldVO formFieldVo) {
        return serviceApprovalDao.selectFormFieldList(formFieldVo);
    }
    
    public List<Map<String, Object>> selectCodeTemplateDataList(TemplateDataVO templateDataVo) {
    	return serviceApprovalDao.selectCodeTemplateDataList(templateDataVo);
    }
    
    public void insertCodeTemplateData(TemplateDataVO templateDataVo) throws Exception {
    	serviceApprovalDao.insertCodeTemplateData(templateDataVo);
    }
    
    public void deleteCodeTemplateData(TemplateDataVO templateDataVo) {
    	serviceApprovalDao.deleteCodeTemplateData(templateDataVo);
    }
    
    public void updateCodeTemplateData(TemplateDataVO templateDataVo) {
    	serviceApprovalDao.updateCodeTemplateData(templateDataVo);
    }
    
}