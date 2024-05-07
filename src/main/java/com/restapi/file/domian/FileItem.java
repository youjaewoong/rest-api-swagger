package com.restapi.file.domian;

import lombok.Data;

import java.util.List;

@Data
public class FileItem {

    private Long id;
    private String itemName;
    private FileUpload attachFile;
    private List<FileUpload> imageFiles;
}
