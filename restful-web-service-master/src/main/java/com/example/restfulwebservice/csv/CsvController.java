package com.example.restfulwebservice.csv;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.restfulwebservice.user.User;

import lombok.extern.slf4j.Slf4j;

//note - for simplicity we have removed the ui configuration.
//you're free to add the code changes for the ui (thymeleaf configuration).

//lombok annotation
@Slf4j
//spring annotations
@Controller
@RequestMapping("/api")
public class CsvController {

   @Autowired
   CsvService csvService; 

   @GetMapping("/download-common-list-csv")
   public ResponseEntity<byte[]> getCsv() {
       log.info("Downloading residents csv");
       final List<User> getUsers = csvService.getUsers();
       final byte[] resource = csvService.load(getUsers);
       String filename = "test";
       return ResponseEntity.ok()
               .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + ".csv\"")
               .contentType(MediaType.parseMediaType("application/csv;charset=MS949"))
               .body(resource);
   }
   
   @PostMapping(value = "/download-common-map-csv")
   public ResponseEntity<byte[]> csv() {

      HashMap<String,String> params = new HashMap<String, String>();
      params.put("USER_MAIL", "yunsd@xxxxxx.co.kr");
      params.put("URL_KEY", "e83db13911b9baa90bb04db734303b0f");

      HttpHeaders header = new HttpHeaders();
      header.setContentType(MediaType.parseMediaType("application/csv;charset=MS949"));
      header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=csvFile.csv");
      header.setContentLength((csvService.getCSVbyByte(params)).length);

      return new ResponseEntity<byte[]>(csvService.getCSVbyByte(params), header, HttpStatus.OK);
   }
   
   @GetMapping(value="/download-super-csv", produces = "text/csv; charset=MS949")
   public ModelAndView sampleExcelCsvDownload() throws Exception {
       
       ModelAndView mav = new ModelAndView();

       List<Map<String, String>> sampleList = new ArrayList<Map<String, String>>();
       Map<String, String> map1 = new HashMap<String, String>();
       map1.put("article_seq", "1");
       map1.put("title", "제목1");
       map1.put("contents", "내용1");
       Map<String, String> map2 = new HashMap<String, String>();
       map2.put("article_seq", "2");
       map2.put("title", "제목2");
       map2.put("contents", "내용2");
       sampleList.add(map1);
       sampleList.add(map2);
       
       mav.addObject("columnIds", "article_seq,title,contents");
       mav.addObject("columnNames", "게시판순번,제목,내용");
       mav.addObject("excelDataList", sampleList);
       
       mav.setViewName("excelCsvWriteView");
       return mav;
   }

}