package com.restapi.file.controller;


import com.restapi.file.domian.FileItem;
import com.restapi.file.domian.FileUpload;
import com.restapi.file.service.FileService;
import com.restapi.file.util.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FileDownloadController {

    @GetMapping("/download/excel")
    public ResponseEntity<InputStreamResource> downloadExcel() throws IOException {
        ClassPathResource excelFile = new ClassPathResource("templates/excel/test-template.xlsx");
        InputStreamResource resource = new InputStreamResource(excelFile.getInputStream());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=test.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(excelFile.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
