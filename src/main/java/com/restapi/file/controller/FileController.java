package com.restapi.file.controller;


import com.restapi.file.domian.FileItem;
import com.restapi.file.domian.FileItemForm;
import com.restapi.file.domian.FileUpload;
import com.restapi.file.service.FileService;
import com.restapi.file.util.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
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
public class FileController {

    private final FileService fileService;
    private final FileStore fileStore;


    /**
     * 파일정보 저장
     * @param itemId 고유 ID
     * @param itemName 파일정보명
     * @param attachFile 단건 파일
     * @param imageFiles 여러 파일
     */
    @ResponseBody
    @PostMapping("/items/new")
    public ResponseEntity<Void> saveItem(
            @RequestParam("itemId") Long itemId,
            @RequestParam("itemName") String itemName,
            @RequestParam("attachFile") MultipartFile attachFile,
            @RequestParam("imageFiles") List<MultipartFile> imageFiles) throws IOException {

        List<FileUpload> storeImageFiles = fileStore.storeFiles(imageFiles);

        //데이터베이스에 저장
        FileItem fileItem = new FileItem();
        fileItem.setId(itemId);
        fileItem.setItemName(itemName);
        fileItem.setAttachFile(fileStore.storeFile(attachFile));
        fileItem.setImageFiles(storeImageFiles);
        fileService.fileSave(fileItem);

        return ResponseEntity.ok().build();
    }

    /**
     * 아이템정보
     * @param id 고유번호
     * @return 아이템정보 반환
     */
    @ResponseBody
    @GetMapping("/items/{id}")
    public ResponseEntity<FileItem> items(@PathVariable Long id) {
        return ResponseEntity.ok().body(fileService.fileFindById(id));
    }

    /**
     * 이미지 확인
     * @param filename 파일명
     * @return 이미지 객체정보 반환
     */
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    /**
     * 파일다운로드
     * @param itemId 아이템번호
     * @return 파일다운로드 반환
     */
    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long itemId) throws MalformedURLException {
        FileItem fileItem = fileService.fileFindById(itemId);
        String storeFileName = fileItem.getAttachFile().getStoreFileName();
        String uploadFileName = fileItem.getAttachFile().getUploadFileName();

        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        log.info("uploadFileName={}", uploadFileName);

        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

}
